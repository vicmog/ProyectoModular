package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;


public class JuegoJugarFragment extends Fragment {

    private TextView tvNombreJugador,tvScoreJugador,tvNombreCarta,tvDescripcionCarta;
    private ImageView imgCarta;
    private ViewModel miViewModel;
    private  long numeroCartas ;
    private int idCartaAleatoria;
    private Carta cartaAleatoria = new Carta() ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_juego_jugar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        miViewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);


        tvNombreJugador = getView().findViewById(R.id.tvNombreJugador);
        tvScoreJugador = getView().findViewById(R.id.tvScorePlayer);
        tvNombreCarta = getView().findViewById(R.id.tvNombreCarta);
        tvDescripcionCarta = getView().findViewById(R.id.tvDescripcionCarta);
        imgCarta = getView().findViewById(R.id.imgCartaJuego);

        Usuario usuario =  miViewModel.getUsuarioJuegoJugador();
        tvNombreJugador.setText(usuario.getNombre());
        tvScoreJugador.setText(usuario.getNRespuestasCorrectas()+"");

        //miViewModel.insertCarta(new Carta(R.drawable.profile1,"perro","descripcion"));

        obtencionIdCartaAleatoria();

        miViewModel.getCarta(idCartaAleatoria);
        miViewModel.getCartaAleatoria().observe(getActivity(), new Observer<Carta>() {
            @Override
            public void onChanged(Carta carta) {
                cartaAleatoria = carta;
                tvNombreCarta.setText(cartaAleatoria.getNombreAnimal());
                tvDescripcionCarta.setText(cartaAleatoria.getDescripcion());
                imgCarta.setImageResource(cartaAleatoria.getUrlFoto());

            }
        });



        


    }
    private int numeroAleatoriodeCarta() {

        int numero_mínimo = 1;
        int n;
        int numero_maximo = 0;

        miViewModel.getNumeroCartas();
        miViewModel.getNumeroCartasDisponibles().observe(getActivity(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                numeroCartas = aLong;


            }
        });

        numero_maximo = (int) numeroCartas;
        n = (int) (Math.random() * (numero_maximo + 1 - numero_mínimo)) + numero_mínimo;

    return n;
    }

    private void obtencionIdCartaAleatoria() {
        idCartaAleatoria = numeroAleatoriodeCarta();
        if(idCartaAleatoria==miViewModel.getIdCartaAnteriorJugada()){

            idCartaAleatoria = numeroAleatoriodeCarta();
            miViewModel.setIdCartaAnteriorJugada(idCartaAleatoria);

        }else{
            miViewModel.setIdCartaAnteriorJugada(idCartaAleatoria);
        }
    }
}