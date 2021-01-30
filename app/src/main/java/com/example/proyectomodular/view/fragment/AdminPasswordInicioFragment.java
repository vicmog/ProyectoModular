package com.example.proyectomodular.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminPasswordInicioFragment extends Fragment {

private TextInputEditText etPassword ,etRetryPassword;
private TextInputLayout tilPassword,tilRetryPassword;
private NavController navController;
private SharedPreferences spPasswordAdmin;
private final String KEY ="passwordAdmin";
private SharedPreferences.Editor editor;
private ViewModel miViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin_password_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spPasswordAdmin = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = spPasswordAdmin.edit();

        miViewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        navController = Navigation.findNavController(getView());
        etPassword = getView().findViewById(R.id.tietPasswordAdmin);
        etRetryPassword = getView().findViewById(R.id.tietRetryPasswordAdmin);
        tilPassword = getView().findViewById(R.id.tilPasswordAdmin);
        tilRetryPassword = getView().findViewById(R.id.tilRetryPasswordAdmin);
        Button btAceptar = getView().findViewById(R.id.btAceptarPasswordAdmin);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(compruebaClaves()){
                    miViewModel.insertUsuario(new Usuario("Pepe",R.drawable.profile1,1,1));
                    navController.navigate(R.id.hallFameFragment);
                    Carta carta1 = new Carta("https://informatica.ieszaidinvergeles.org:9033/gato-atigrado-triste_0.jpg","Gato","Gato atigrado");
                    miViewModel.insertCarta(carta1);

                    Carta carta2 = new Carta("https://informatica.ieszaidinvergeles.org:9033/_107435681_perro1.jpg","Perro","Perro Grande");
                    miViewModel.insertCarta(carta2);
                    //Log.v("ZZZ",carta1.toString());
                   // miViewModel.insertPregunta(new Pregunta(1,"De que color es","Naranja","Naranja","Verde","Marron","Azul"));
                    //miViewModel.insertPregunta(new Pregunta(1,"Es pequeño","Grande","Pequeño","Enorme","Grande","Muy grande"));

                }

            }
        });

        String posibleClave = spPasswordAdmin.getString(KEY,"");
        if(posibleClave.length() != 0 ){
            navController.navigate(R.id.hallFameFragment);
        }



    }

    private boolean compruebaClaves() {
        String password = etPassword.getText().toString().trim();
        String retryPassword = etRetryPassword.getText().toString().trim();

        if(password.length() >= 4){
            tilPassword.setErrorEnabled(false);
                if(password.compareTo(retryPassword) == 0){
                    tilRetryPassword.setErrorEnabled(false);


                    editor.putString(KEY,retryPassword);
                    editor.apply();
                    editor.commit();

                    return true;

                }else{
                    tilRetryPassword.setError("Las contraseñas no coinciden");
                }

        }else{
            tilPassword.setError("La contraseña debe tener al menos 4 caracteres");
        }

        return false;
    }
}