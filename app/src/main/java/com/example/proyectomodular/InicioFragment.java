package com.example.proyectomodular;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class InicioFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        navController = Navigation.findNavController(getView());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //si hay datos en shared preferences carga el halloffame ,sinio carga la pantalla de contraseña
                navController.navigate(R.id.adminPasswordInicioFragment);
            }
        },3000);

    }
}