package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectomodular.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminPasswordInicioFragment extends Fragment {

private TextInputEditText etPassword ,etRetryPassword;
private TextInputLayout tilPassword,tilRetryPassword;
private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin_password_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getView());
        etPassword = getView().findViewById(R.id.tietPasswordAdmin);
        etRetryPassword = getView().findViewById(R.id.tietRetryPasswordAdmin);
        tilPassword = getView().findViewById(R.id.tilPasswordAdmin);
        tilRetryPassword = getView().findViewById(R.id.tilRetryPasswordAdmin);
        Button btAceptar = getView().findViewById(R.id.btAceptarPasswordAdmin);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.hallFameFragment);
            }
        });



    }
}