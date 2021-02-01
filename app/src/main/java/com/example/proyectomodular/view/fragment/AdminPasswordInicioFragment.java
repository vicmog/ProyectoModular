package com.example.proyectomodular.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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

                    navController.navigate(R.id.nuevoJugadorFragment);
                    Carta carta1 = new Carta("https://animalmascota.com/wp-content/2015/08/leon-curiosidades.jpg","Leon","El cuerpo del león es musculoso y viene acompañado de una cabeza grande con un hocico corto y ancho, de donde sobresalen sus largos y afilados caninos y sus bigotes esenciales para que puedan guiarse en la oscuridad.");
                    miViewModel.insertCarta(carta1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    miViewModel.insertPregunta(new Pregunta(1,"¿Cuanto vive en cautividad?","Hasta 15 años","Hasta 25 años","Hasta 10 años","Hasta 40 años"));
                    miViewModel.insertPregunta(new Pregunta(1,"¿Cuanto vive un leon en libertad?","De 12 a 16 años","Es inmortal","De 20 a 30 años","De 10 a 40 años"));
                    miViewModel.insertPregunta(new Pregunta(1,"¿Cuanto llega a medir el león africano?","Hasta 3 metos","Hasta 10 metros","Hasta 5 metros","Hasta 1 metro"));
                    miViewModel.insertPregunta(new Pregunta(1,"¿Cuanto llega a pesar el león africano?","Hasta 250 kg","Hasta 100 kg","Hasta 400 kg","Hasta 1 tonelada"));


                    Carta carta2 = new Carta("https://kids.sandiegozoo.org/sites/default/files/2019-07/animal-hero-bonoborighte.jpg","Bonobo","Los bonobos se ven raramente fuera de su hábitat natural, por lo que no son tan conocidos como los chimpancés comunes. A primera vista son muy parecidos a estos, pero suelen tener la cara negra, las orejas más pequeñas y las piernas más largas.");
                    miViewModel.insertCarta(carta2);
                    miViewModel.insertPregunta(new Pregunta(2,"¿Cuantos existen?","Unos 10.000","Unos 25.000","Menos de 3.000","Mas de 50.000"));
                    miViewModel.insertPregunta(new Pregunta(2,"¿Donde se ubican?","En el congo","En Zambia","En el sudeste asiatico","En Madagascar"));
                    miViewModel.insertPregunta(new Pregunta(2,"¿Como se organia su sociedad?","Es matriarcal","Es patriarcal","Todos son iguales","Son democraticos"));
                    miViewModel.insertPregunta(new Pregunta(2,"¿Cual de las siguientes es correcta?","Son mas pacificos que los chimpances","Se parecen fisicamente a los orangutanes","Los ojos son de color rojo","Los bonobos estan siempre en guerra"));


                }

            }
        });

        String posibleClave = spPasswordAdmin.getString(KEY,"");
        if(posibleClave.length() != 0 ){
            navController.navigate(R.id.juegoFragment);
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