package com.example.proyectomodular;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdminFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = view.findViewById(R.id.bottom_navigation_admin);

        bottomNavigationView.setSelectedItemId(R.id.adminArea);
        navController = Navigation.findNavController(view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.players:navController.navigate(R.id.hallFameFragment);
                        break;
                    case R.id.jugarPartida:navController.navigate(R.id.juegoFragment);
                        break;
                    case R.id.adminArea:navController.navigate(R.id.adminFragment);
                        break;
                }
                return true;
            }
        });

    }
}