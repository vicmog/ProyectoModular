package com.example.proyectomodular.view.fragment;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.Contacto;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class PuntuacionFragment extends Fragment {

    private ViewModel viewModel;
    private TextView nombre,puntuacion;
    private ImageView avatar;
    private Button enviarCorreo,compartirPuntuacion;
    private Animation animationScale;
    private TextView puntuacion1;
    private TextView puntuacion2;
    private TextView puntuacion3;

    private final static int PERMISOS = 1;
    private boolean permisoCuentas = false;
    private boolean permisoContactos = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puntuacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animationScale = AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        nombre = view.findViewById(R.id.tv_nombrePuntuacion);
        puntuacion = view.findViewById(R.id.tv_puntuacionPuntuacion);
        avatar = view.findViewById(R.id.ivAvatarPuntuacion);
        enviarCorreo = view.findViewById(R.id.btn_enviarMail);
        compartirPuntuacion = view.findViewById(R.id.btn_compartirPuntuacion);
        puntuacion1 = view.findViewById(R.id.tv_puntuacion1);
        puntuacion2 = view.findViewById(R.id.tv_puntuacion2);
        puntuacion3 = view.findViewById(R.id.tv_puntuacion3);
        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.my_toolbar_puntuacion);

        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        nombre.setText(viewModel.getUsuarioPuntuacion().getNombre());
        puntuacion.setText(viewModel.getUsuarioPuntuacion().getNRespuestasCorrectas()+" puntos");
        avatar.setImageResource(viewModel.getUsuarioPuntuacion().getAvatar());

        nombre.startAnimation(animationScale);
        puntuacion.startAnimation(animationScale);
        avatar.startAnimation(animationScale);
        enviarCorreo.startAnimation(animationScale);
        compartirPuntuacion.startAnimation(animationScale);
        puntuacion1.startAnimation(animationScale);
        puntuacion2.startAnimation(animationScale);
        puntuacion3.startAnimation(animationScale);

        enviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compruebaPermisos();
                if(permisoCuentas){
                    enviarMail();
                }
            }
        });

        compartirPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compruebaPermisos();
                if(permisoContactos){
                    viewModel.setEnviarContactos(new ArrayList<Contacto>());
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                    DialogFragment dialog = new ContactosFragment();
                    dialog.show(getFragmentManager(),"tag");
                }
            }
        });

    }

    //METODO PARA OBTENER LA CUENTA GOOGLE PRINCIPAL EN EL DISPOSITIVO
    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

    //METODO CON UN INTENT IMPLICITO PARA ENVIAR UN MAIL A TI MISMO
    public void enviarMail() {
//        String recipientList = mEditTextTo.getText().toString();
//        String[] recipients = recipientList.split(",");
        String  emailAccount = "";
        AccountManager accountManager = AccountManager.get(getActivity().getApplicationContext());
        Account account = getAccount(accountManager);
        if (account != null) {
            emailAccount =  account.name;
        }

        String[] correos = {emailAccount};
        String asuntoEmail = this.getString(R.string.app_name)+" - "+viewModel.getUsuarioPuntuacion().getNombre()+" - Posición en el Ranking!!";
        String mensaje = viewModel.getUsuarioPuntuacion().getNombre()+"! Has conseguido "
                +viewModel.getUsuarioPuntuacion().getNRespuestasCorrectas()+" puntos. \n \n"+
                "Enhorabuena! Sigue así y supera tu marca!";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, correos);
        intent.putExtra(Intent.EXTRA_SUBJECT,asuntoEmail );
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("message/rfc822");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            //startActivity(Intent.createChooser(intent, "Elige una aplicación de mensajería:"));
            startActivity(intent);
        }
    }

    private void compruebaPermisos() {
        int permisoLeeContactos = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS);
        int permisoLeeCuentas = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.GET_ACCOUNTS);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || (permisoLeeContactos == PackageManager.PERMISSION_GRANTED) || (permisoLeeCuentas == PackageManager.PERMISSION_GRANTED) ) {

            permisoContactos = true;
            permisoCuentas = true;

        } else {

            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) || shouldShowRequestPermissionRationale(Manifest.permission.GET_ACCOUNTS)) {
                mostrarInfromacionDetalladaPermisoContactos();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.GET_ACCOUNTS}, PERMISOS);
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case PERMISOS:
                int contador = 0;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        contador++;

                    }
                }
                if (contador == grantResults.length) {
                    permisoContactos = true;
                } else {

                }
                break;

        }
    }

    private void mostrarInfromacionDetalladaPermisoContactos() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Debes dar permisos para acceder a tus contactos");
        builder.setMessage("¿Deseas conceder permisos a esta aplicación para acceder a los contactos?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{ Manifest.permission.READ_CONTACTS,Manifest.permission.GET_ACCOUNTS}, PERMISOS);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();

    }

}