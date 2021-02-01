package com.example.proyectomodular.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class NewCardFragment extends Fragment {
    private Button subirFoto;
    private Button siguiente;
    private EditText etNombre;
    private EditText etDesc;
    private ImageView pic;
    private final int PHOTO_SELECTED=1;
    private String url="";
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subirFoto = view.findViewById(R.id.ncbtSubirfoto);
        siguiente = view.findViewById(R.id.ncbtSiguiente);
        etNombre = view.findViewById(R.id.ncetNombre);
        etDesc = view.findViewById(R.id.ncedDescr);
        pic = view.findViewById(R.id.ncPic);
        pic.setImageResource(R.drawable.upload);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etNombre.getText().toString().equalsIgnoreCase("") || etDesc.getText().toString().equalsIgnoreCase("") || url.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Rellena los campos!", Toast.LENGTH_SHORT).show();
                }else{
                    ViewModel vm = new ViewModelProvider(getActivity()).get(ViewModel.class);
                    Carta carta = new Carta(url, etNombre.getText().toString(), etDesc.getText().toString());
                    vm.insertCarta(carta);

                    vm.getCardId().observe(getActivity(), new Observer<Long>() {
                        @Override
                        public void onChanged(Long aLong) {
                            vm.setIdCarta(aLong);
                        }
                    });
                    navController = Navigation.findNavController(view);
                    navController.navigate(R.id.preguntaFragment);
                }




            }
        });

        subirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });






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