package com.example.proyectomodular.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectomodular.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminPassFragment extends Fragment {

    private TextInputEditText etPassword ,etRetryPassword;
    private TextInputLayout tilPassword,tilRetryPassword;
    private NavController navController;
    private SharedPreferences spPasswordAdmin;
    private final String KEY ="passwordAdmin";
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_pass, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spPasswordAdmin = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = spPasswordAdmin.edit();


        navController = Navigation.findNavController(getView());
        etPassword = getView().findViewById(R.id.tiePassAdmin);
        etRetryPassword = getView().findViewById(R.id.tieRetryPassAdmin);
        tilPassword = getView().findViewById(R.id.tilPassAdmin);
        tilRetryPassword = getView().findViewById(R.id.tilRetryPassAdmin);

        Button btSalir = getView().findViewById(R.id.btSalirAdmin);
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        Button btEntrar = getView().findViewById(R.id.btEntrarAdmin);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              compruebaClaves();
            }
        });
    }
    private boolean compruebaClaves() {
        String password = etPassword.getText().toString().trim();
        String retryPassword = etRetryPassword.getText().toString().trim();



            if(password.compareTo(retryPassword) == 0){
                tilRetryPassword.setErrorEnabled(false);
                    if(password.compareToIgnoreCase(spPasswordAdmin.getString(KEY,""))==0){
                        tilRetryPassword.setErrorEnabled(false);
                        navController.navigate(R.id.adminFragment);
                    }else{
                        tilRetryPassword.setError("La contraseña no es correcta");
                    }



                return true;

            }else{
                tilRetryPassword.setError("Las contraseñas no coinciden");
            }



        return false;
    }
}