package com.example.proyectomodular.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
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

import com.bumptech.glide.Glide;
import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class EditCardFragment extends Fragment {
    private TextView etNombre;
    private TextView etDescr;
    private ImageView pic;
    private Button btNombre;
    private Button btDescr;
    private Button btPic;
    private Button btRes1;
    private Button btRes2;
    private Button btRes3;
    private Button btRes4;
    private Button atras;
    private List<Pregunta> listaPreguntas;
    private NavController navController;
    private final int PHOTO_SELECTED=1;
    private String url;
    private Animation animationScale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animationScale = AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
        etNombre = view.findViewById(R.id.tvNombre);
        etDescr = view.findViewById(R.id.tvDescr);
        pic = view.findViewById(R.id.ivPic);
        btPic = view.findViewById(R.id.btChangePic);
        btNombre = view.findViewById(R.id.btCambiaNombre);
        btDescr = view.findViewById(R.id.btCambiaDescr);
        btRes1 = view.findViewById(R.id.btRes1);
        btRes2 = view.findViewById(R.id.btRes2);
        btRes3 = view.findViewById(R.id.btRes3);
        btRes4 = view.findViewById(R.id.btRes4);
        atras = view.findViewById(R.id.btAtras);

        etNombre.startAnimation(animationScale);
        etDescr.startAnimation(animationScale);
        pic.startAnimation(animationScale);
        btPic.startAnimation(animationScale);
        btNombre.startAnimation(animationScale);
        btDescr.startAnimation(animationScale);
        btRes1.startAnimation(animationScale);
        btRes2.startAnimation(animationScale);
        btRes3.startAnimation(animationScale);
        btRes4.startAnimation(animationScale);
        atras.startAnimation(animationScale);

        ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(ViewModel.class);

        Carta carta = vm.getEditarCarta();

        etNombre.setText(carta.getNombreAnimal());
        etDescr.setText(carta.getDescripcion());
        Glide.with(getContext())
                .load(carta.getUrlFoto())
                .into(pic);

        vm.postPreguntas(carta.getId());
        vm.getListaPreguntas().observe(getActivity(), new Observer<List<Pregunta>>() {
            @Override
            public void onChanged(List<Pregunta> preguntas) {
                vm.setEditarPreguntas(preguntas);
                textoBotones(preguntas);
            }

            
        });

        btRes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.editPreguntaConcretaFragment);
                vm.setEditarCarta(carta);
                listaPreguntas = vm.getEditarPreguntas();
                vm.setEditarPregunta(listaPreguntas.get(0));

            }
        });

        btRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.editPreguntaConcretaFragment);
                vm.setEditarCarta(carta);
                listaPreguntas = vm.getEditarPreguntas();
                vm.setEditarPregunta(listaPreguntas.get(1));

            }
        });

        btRes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.editPreguntaConcretaFragment);
                vm.setEditarCarta(carta);
                listaPreguntas = vm.getEditarPreguntas();
                vm.setEditarPregunta(listaPreguntas.get(2));

            }
        });

        btRes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.editPreguntaConcretaFragment);
                vm.setEditarCarta(carta);
                listaPreguntas = vm.getEditarPreguntas();
                vm.setEditarPregunta(listaPreguntas.get(3));

            }
        });

        btNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Inserta el nombre");
                final EditText input = new EditText(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(ViewModel.class);
                        carta.setNombreAnimal(input.getText().toString());
                        vm.updateCarta(carta);
                        Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
                        etNombre.setText(carta.getNombreAnimal());
                    }
                });

                builder.setNegativeButton("Cancelar",null);

                AlertDialog alert = builder.create();
                builder.show();


            }
        });

        btDescr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Inserta descripcion");
                final EditText input = new EditText(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(ViewModel.class);
                        carta.setDescripcion(input.getText().toString());
                        vm.updateCarta(carta);
                        Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
                        etDescr.setText(carta.getDescripcion());
                    }
                });

                builder.setNegativeButton("Cancelar",null);

                AlertDialog alert = builder.create();
                builder.show();

            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.adminFragment);
            }
        });

        btPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });






    }



    private void textoBotones(List<Pregunta> preguntas) {
        if(preguntas.size()==1){
            btRes1.setText(preguntas.get(0).getPregunta());
        }else if(preguntas.size()==2){
            btRes1.setText(preguntas.get(0).getPregunta());
            btRes2.setText(preguntas.get(1).getPregunta());
        }else if(preguntas.size()==3){
            btRes1.setText(preguntas.get(0).getPregunta());
            btRes2.setText(preguntas.get(1).getPregunta());
            btRes3.setText(preguntas.get(2).getPregunta());
        }else if(preguntas.size()==4){
            btRes1.setText(preguntas.get(0).getPregunta());
            btRes2.setText(preguntas.get(1).getPregunta());
            btRes3.setText(preguntas.get(2).getPregunta());
            btRes4.setText(preguntas.get(3).getPregunta());

        }else if(preguntas.size()==5){

        }

    }


    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, PHOTO_SELECTED);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SELECTED && data != null && data.getData() != null) {

            Uri uri = data.getData();
            String patron = creaPatron(10);
            url = guardarImagen(patron, uri);

        }
    }

    public String creaPatron (int n){
        String patron = "cHbuYYEkbZaGjgG5GSJn68FukWGyBUyacmRQU6VwfgCjdPmLgMG8Ujirt3JE";
        String patronFinal="";
        Random random = new Random();

        int length = patron.length();
        int index;
        char randomChar;

        for(int i = 0; i < n; i++) {

            index = random.nextInt(patron.length());
            randomChar = patron.charAt(index);
            patronFinal +=randomChar;
        }

        return  patronFinal;
    }

    private String guardarImagen(String nom,Uri uri) {
        String ruta="";
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            FileOutputStream outputStream = getActivity().openFileOutput("img-"+nom, Context.MODE_PRIVATE);
            outputStream.write(byteArray);
            outputStream.close();

            ruta = getActivity().getFilesDir().getPath()+"/img-"+nom;

            Glide.with(getActivity()).load(ruta).into(pic);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ruta;


    }



}