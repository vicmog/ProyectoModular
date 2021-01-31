package com.example.proyectomodular.view.fragment;

import android.app.Application;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;

public class EditJugadorFragment extends Fragment {

    private Button btatras;
    private Button bteditPic;
    private Button btguardar;
    private Button bteditName;
    private TextView editTextName;
    private ImageView editPic;
    private NavController navController;
    private TextView stats;
    private Animation animationScale;
    private TextView textview12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_jugador, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animationScale = AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
        btatras = view.findViewById(R.id.btAtrasEdit);
        bteditName = view.findViewById(R.id.btEditNick);
        bteditPic = view.findViewById(R.id.btEditPic);
        editPic = view.findViewById(R.id.editPic);
        editTextName = view.findViewById(R.id.editTextName);
        btguardar = view.findViewById(R.id.btguardarEdit);
        stats = view.findViewById(R.id.stats);
        textview12 = view.findViewById(R.id.textView12);

        btatras.startAnimation(animationScale);
        bteditName.startAnimation(animationScale);
        bteditPic.startAnimation(animationScale);
        editPic.startAnimation(animationScale);
        editTextName.startAnimation(animationScale);
        btguardar.startAnimation(animationScale);
        stats.startAnimation(animationScale);
        textview12.startAnimation(animationScale);


        navController = Navigation.findNavController(view);

        ViewModel vm = new ViewModelProvider(getActivity()).get(ViewModel.class);
        Usuario user = vm.getEditar();


        if(user.getNRespuestas()>0){
            double stats1 = user.getNRespuestasCorrectas();
            stats1/=user.getNRespuestas();
            stats1*=100;
            int stats2 = (int)stats1;
            stats.setText(stats2+"%");
        }else{
            stats.setText("Aún no ha jugado");
        }



        editTextName.setText(vm.getEditar().getNombre());
        editPic.setImageResource(vm.getEditar().getAvatar());

        bteditPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.modificarPicFragment);
            }
        });

        bteditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Inserta el nuevo nick");
                final EditText input = new EditText(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextName.setText(input.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancelar",null);

                AlertDialog alert = builder.create();
                builder.show();
            }
        });

        btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setNombre(editTextName.getText().toString());
                vm.updateUsuario(user);
                Toast.makeText(getContext(), user.getNombre()+" actualizado!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.adminFragment);
            }
        });

        btatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.adminFragment);
            }
        });









    }
}