package com.example.proyectomodular.view.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.io.PrintWriter;

public class PreguntaFragment extends Fragment {

    private TextView numPregunta;
    private EditText res1;
    private EditText res2;
    private EditText res3;
    private EditText res4;
    private Button btSiguiente;
    private EditText etPregunta;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregunta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numPregunta = view.findViewById(R.id.tvNumPregunta);
        res1 = view.findViewById(R.id.edRes1);
        res2 = view.findViewById(R.id.edRes2);
        res3 = view.findViewById(R.id.edRes3);
        res4 = view.findViewById(R.id.edRes4);
        btSiguiente = view.findViewById(R.id.btSiguiente);
        etPregunta = view.findViewById(R.id.etPregunta);


        btSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if      (res1.getText().toString().equals("") ||
                        res2.getText().toString().equals("") ||
                        res3.getText().toString().equals("") ||
                        res4.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();

                }else{


                    if(Integer.parseInt(numPregunta.getText().toString())==4){
                        ViewModel vm = new ViewModelProvider(getActivity()).get(ViewModel.class);
                        long cardID = vm.getIdCarta();

                        Pregunta pregunta = new Pregunta(cardID, etPregunta.getText().toString(),
                                res1.getText().toString(),
                                res2.getText().toString(),
                                res3.getText().toString(),
                                res4.getText().toString());
                        vm.insertPregunta(pregunta);

                        int i = Integer.parseInt(numPregunta.getText().toString());
                        i++;
                        numPregunta.setText(i+"");
                        res1.setText("");
                        res2.setText("");
                        res3.setText("");
                        res4.setText("");
                        etPregunta.setText("");

                        navController = Navigation.findNavController(view);
                        navController.navigate(R.id.adminFragment);

                    }else{
                        ViewModel vm = new ViewModelProvider(getActivity()).get(ViewModel.class);
                        long cardID = vm.getIdCarta();

                        Pregunta pregunta = new Pregunta(cardID, etPregunta.getText().toString(),
                                                                 res1.getText().toString(),
                                                                 res2.getText().toString(),
                                                                 res3.getText().toString(),
                                                                 res4.getText().toString());
                        vm.insertPregunta(pregunta);

                        int i = Integer.parseInt(numPregunta.getText().toString());
                        i++;
                        numPregunta.setText(i+"");
                        res1.setText("");
                        res2.setText("");
                        res3.setText("");
                        res4.setText("");
                        etPregunta.setText("");


                    }




                }



            }
        });





    }







}