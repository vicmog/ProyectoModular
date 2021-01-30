package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.viewmodel.ViewModel;

public class EditPreguntaConcretaFragment extends Fragment {
    private Carta carta;
    private Pregunta pregunta;
    private EditText etPregunta;
    private EditText res1;
    private EditText res2;
    private EditText res3;
    private EditText res4;
    private Button atras;
    private Button guardar;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_pregunta_concreta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(ViewModel.class);
        carta = vm.getEditarCarta();
        pregunta = vm.getEditarPregunta();

        etPregunta = view.findViewById(R.id.etPreguntaEdit);
        res1 = view.findViewById(R.id.etResCorrectaEdit);
        res2 = view.findViewById(R.id.etRes2);
        res3 = view.findViewById(R.id.etRes3);
        res4 = view.findViewById(R.id.etRes4);
        atras = view.findViewById(R.id.btBack);
        guardar = view.findViewById(R.id.btSave);

        etPregunta.setText(pregunta.getPregunta());
        res1.setText(pregunta.getOpcion1());
        res2.setText(pregunta.getOpcion2());
        res3.setText(pregunta.getOpcion3());
        res4.setText(pregunta.getOpcion4());

        navController = Navigation.findNavController(view);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.editCardFragment);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregunta.setPregunta(etPregunta.getText().toString());
                pregunta.setOpcion1(res1.getText().toString());
                pregunta.setOpcion2(res2.getText().toString());
                pregunta.setOpcion3(res3.getText().toString());
                pregunta.setOpcion4(res4.getText().toString());
                vm.updatePregunta(pregunta);
                Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();


                navController.navigate(R.id.editCardFragment);
            }
        });



    }
}