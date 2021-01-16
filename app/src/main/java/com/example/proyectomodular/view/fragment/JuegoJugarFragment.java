package com.example.proyectomodular.view.fragment;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private FloatingActionButton btSiguiente;
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
                Log.v("ZZZ",miViewModel.getPuntuacionPartidaActual()+"");
            }
        });


        /*miViewModel.insertCarta(new Carta(R.drawable.profile1, "perro", "descripcion"));
        miViewModel.insertCarta(new Carta(R.drawable.profile1, "gato", "poi"));
        miViewModel.insertPregunta(new Pregunta(1,"hola??","si","si","alomejor","no","puede"));
        miViewModel.insertPregunta(new Pregunta(1,"holaaaaaaaaa??","si","si","alomejor","no","puede"));
        miViewModel.insertPregunta(new Pregunta(1,"holaaaaaaaaaaaaaaaaa??","si","si","alomejor","no","puede"));
        miViewModel.insertPregunta(new Pregunta(2,"adios??","si","si","alomejor","no","puede"));
        miViewModel.insertPregunta(new Pregunta(2,"adiosssss??","si","si","alomejor","no","puede"));
        miViewModel.insertPregunta(new Pregunta(2,"adiossssssssssssssss?","si","si","alomejor","no","puede"));*/


        miViewModel.getCarta();
        miViewModel.getCartaAleatoria().observe(getActivity(), new Observer<Carta>() {
            @Override
            public void onChanged(Carta carta) {
                tvNombreCarta.setText(carta.getNombreAnimal());
                tvDescripcionCarta.setText(carta.getDescripcion());
                imgCarta.setImageResource(carta.getUrlFoto());
                obtenerPreguntas(carta.getId());

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

