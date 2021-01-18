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

    private TextView tvNombreJugador, tvScoreJugador, tvNombreCarta, tvDescripcionCarta,tvPreguntaJuego;
    private ImageView imgCarta;
    private ViewModel miViewModel;
    private NavController navController;
    private List<Pregunta> preguntas = new ArrayList<>();
    private FloatingActionButton btSiguiente,btCancelar;
    private RecyclerView recyclerPreguntas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_juego_jugar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                Log.v("ZZZ",respuestasCorrectas+"correctas");
                Log.v("ZZZ",miViewModel.getNumeroRespuestasTotales()+"totales");
                usuarioPartida.setNRespuestasCorrectas(usuarioPartida.getNRespuestasCorrectas()+ respuestasCorrectas);
                usuarioPartida.setNRespuestas(usuarioPartida.getNRespuestas() + miViewModel.getNumeroRespuestasTotales());

                miViewModel.updateUsuario(usuarioPartida);
                navController.navigate(R.id.mostrarPuntuacionFragment);

            }
        });

        btCancelar = getView().findViewById(R.id.btCancelarJuego);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //navController.navigate(R.id.juegoFragment);
                navController.popBackStack();

            }
        });

        //java.lang.NullPointerException: You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).
        /*miViewModel.insertCarta(new Carta("https://informatica.ieszaidinvergeles.org:9033/_107435681_perro1.jpg", "perro", "perro grande marron y vive en barcelona"));
        miViewModel.insertCarta(new Carta("https://informatica.ieszaidinvergeles.org:9033/gato-atigrado-triste_0.jpg", "gato", "gato pequeño y gris y vive en granada"));
        miViewModel.insertPregunta(new Pregunta(1,"¿De que color es?","marron","gris","verde","marron","azul"));
        miViewModel.insertPregunta(new Pregunta(1,"¿Como es su tamaño?","grande","grande","enorme","pequeño","mediano"));
        miViewModel.insertPregunta(new Pregunta(1,"¿Donde vive?","barcelona","Madrid","Granada","Malaga","Barcelona"));
        miViewModel.insertPregunta(new Pregunta(2,"¿De que color es?","gris","verde","marron","rosa","gris"));
        miViewModel.insertPregunta(new Pregunta(2,"¿Como es su tamaño?","pequeño","muy pequeño","pequeño","grande","muy grande"));
        miViewModel.insertPregunta(new Pregunta(2,"¿Donde vive?","granada","Granada","Sevilla","Huelva","Cadiz"));*/


        miViewModel.getCarta();
        miViewModel.getCartaAleatoria().observe(getActivity(), new Observer<Carta>() {
            @Override
            public void onChanged(Carta carta) {
                tvNombreCarta.setText(carta.getNombreAnimal());
                tvDescripcionCarta.setText(carta.getDescripcion());

                if(getActivity()!=null){
                    Glide.with(getActivity()).load(carta.getUrlFoto()).into(imgCarta);
                    obtenerPreguntas(carta.getId());
                }



            }
        });


    }

    private void obtenerPreguntas(long id) {

        AdaptadorPreguntasJuego adaptadorPreguntas = new AdaptadorPreguntasJuego(preguntas,getView(),getActivity());
        recyclerPreguntas.setAdapter(adaptadorPreguntas);
        recyclerPreguntas.setLayoutManager(new LinearLayoutManager(getActivity()));

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

