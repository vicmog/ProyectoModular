package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectomodular.R;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MostrarPuntuacionFragment extends Fragment {

    private FloatingActionButton btJugarOtra,btSalir;
    private TextView tvTexto,tvPuntuacioJugador,tvPuntuacionTotal;
    private ViewModel miViewModel;
    private NavController navController;
    private CardView cardView;
    private Animation animationScale;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mostrar_puntuacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        miViewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);



        navController = Navigation.findNavController(getView());
        cardView = getView().findViewById(R.id.cardViewMostrarPuntuacion);


        animationScale = AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
        cardView.startAnimation(animationScale);

        tvTexto = getView().findViewById(R.id.tvTextoPuntuacion);
        tvPuntuacioJugador = getView().findViewById(R.id.tvPuntuacionJugador);
        tvPuntuacionTotal = getView().findViewById(R.id.tvPuntuacionTotal);

        tvTexto.setText(R.string.str_puntuacion_resultado);
        tvPuntuacioJugador.setText(miViewModel.getPuntuacionPartidaActual()+"");
        tvPuntuacionTotal.setText(miViewModel.getNumeroRespuestasTotales()+"");



        btJugarOtra = getView().findViewById(R.id.btVolverJugarJuegoPuntuacion);
        btJugarOtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.popBackStack(R.id.juegoJugarFragment,false);
            }
        });
        btSalir = getView().findViewById(R.id.btSalirJuegoPuntuacion);
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack(R.id.juegoFragment,false);
            }
        });
    }
}