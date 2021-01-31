package com.example.proyectomodular.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.view.adapters.AdaptadorPreguntasJuego;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class JuegoJugarFragment extends Fragment {

    private TextView tvNombreJugador, tvScoreJugador, tvNombreCarta, tvDescripcionCarta;
    private ImageView imgCarta;
    private ViewModel miViewModel;
    private NavController navController;
    private List<Pregunta> preguntas = new ArrayList<>();
    private FloatingActionButton btSiguiente,btCancelar;
    private RecyclerView recyclerPreguntas;
    private Animation animationScale;
    private Animation animationTrans;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_juego_jugar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {

        animationScale = AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
        animationTrans = AnimationUtils.loadAnimation(getActivity(),R.anim.trans);
        miViewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        miViewModel.setPuntuacionPartidaActual(0);
        miViewModel.setNumeroRespuestasTotales(0);

        navController = Navigation.findNavController(getView());

        recyclerPreguntas = getView().findViewById(R.id.recyclerPreguntas);

        tvNombreJugador = getView().findViewById(R.id.tvNombreJugador);
        tvScoreJugador = getView().findViewById(R.id.tvScorePlayer);
        tvNombreCarta = getView().findViewById(R.id.tvNombreCarta);
        tvDescripcionCarta = getView().findViewById(R.id.tvDescripcionCarta);
        imgCarta = getView().findViewById(R.id.imgCartaJuego);

        Usuario usuario = miViewModel.getUsuarioJuegoJugador();
        tvNombreJugador.setText(usuario.getNombre());
        tvScoreJugador.setText(usuario.getNRespuestasCorrectas() + "");

        btSiguiente = getView().findViewById(R.id.btSiguienteJuego);
        btSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuarioPartida = miViewModel.getUsuarioJuegoJugador();
                int respuestasCorrectas = miViewModel.getPuntuacionPartidaActual() ;

                usuarioPartida.setNRespuestasCorrectas(usuarioPartida.getNRespuestasCorrectas()+ respuestasCorrectas);
                usuarioPartida.setNRespuestas(usuarioPartida.getNRespuestas() + miViewModel.getNumeroRespuestasTotales());

                miViewModel.updateUsuario(usuarioPartida);

                miViewModel.setCartaActualRotacion(null);
                navController.navigate(R.id.mostrarPuntuacionFragment);

            }
        });

        btCancelar = getView().findViewById(R.id.btCancelarJuego);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                navController.popBackStack();

            }
        });

        cargaCarta();
    }

    private void cargaCarta() {
        if(miViewModel.getCartaActualRotacion()!=null){
            Carta cartaActual = miViewModel.getCartaActualRotacion();
            miViewModel.setIdCartaAnterior(cartaActual.getId());
            tvNombreCarta.setText(cartaActual.getNombreAnimal());
            tvDescripcionCarta.setText(cartaActual.getDescripcion());

            if(getActivity()!=null){
                Glide.with(getActivity()).load(cartaActual.getUrlFoto()).into(imgCarta);
                obtenerPreguntas(cartaActual.getId());
            }

        }else {
            miViewModel.getCarta(miViewModel.getIdCartaAnterior());
            miViewModel.getCartaAleatoria().observe(getActivity(), new Observer<Carta>() {
                @Override
                public void onChanged(Carta carta) {
                    miViewModel.setIdCartaAnterior(carta.getId());
                    miViewModel.setCartaActualRotacion(carta);
                    tvNombreCarta.setText(carta.getNombreAnimal());
                    tvDescripcionCarta.setText(carta.getDescripcion());

                    if(getActivity()!=null){
                        Glide.with(getActivity()).load(carta.getUrlFoto()).into(imgCarta);
                        obtenerPreguntas(carta.getId());
                    }



                }
            });

        }
        tvNombreCarta.startAnimation(animationScale);
        imgCarta.startAnimation(animationScale);
        tvDescripcionCarta.startAnimation(animationScale);
        btCancelar.startAnimation(animationTrans);
        btSiguiente.startAnimation(animationTrans);



    }

    private void obtenerPreguntas(long id) {

        AdaptadorPreguntasJuego adaptadorPreguntas = new AdaptadorPreguntasJuego(preguntas,getView(),getActivity());
        recyclerPreguntas.setAdapter(adaptadorPreguntas);
        recyclerPreguntas.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerPreguntas.startAnimation(animationScale);

        miViewModel.getAllPreguntas(id).observe(getActivity(), new Observer<List<Pregunta>>() {
            @Override
            public void onChanged(List<Pregunta> pre) {

                preguntas.clear();
                preguntas.addAll(pre);
                adaptadorPreguntas.notifyDataSetChanged();

          }
        });

    }
}

