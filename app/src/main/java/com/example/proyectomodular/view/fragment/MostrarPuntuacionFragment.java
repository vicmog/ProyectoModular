package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mostrar_puntuacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        miViewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        navController = Navigation.findNavController(getView());

        tvTexto = getView().findViewById(R.id.tvTextoPuntuacion);
        tvPuntuacioJugador = getView().findViewById(R.id.tvPuntuacionJugador);
        tvPuntuacionTotal = getView().findViewById(R.id.tvPuntuacionTotal);

        tvTexto.setText("Tu puntuacion ha sido :");
        tvPuntuacioJugador.setText(miViewModel.getPuntuacionPartidaActual()+"");
        tvPuntuacionTotal.setText(miViewModel.getNumeroRespuestasTotales()+"");



        btJugarOtra = getView().findViewById(R.id.btVolverJugarJuegoPuntuacion);
        btJugarOtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.juegoJugarFragment);
            }
        });
        btSalir = getView().findViewById(R.id.btSalirJuegoPuntuacion);
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.juegoFragment);
            }
        });
    }
}