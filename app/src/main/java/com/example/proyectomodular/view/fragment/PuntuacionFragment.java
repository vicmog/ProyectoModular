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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomodular.R;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.regex.Pattern;

public class PuntuacionFragment extends Fragment {

    private ViewModel viewModel;
    private TextView nombre,puntuacion;
    private ImageView avatar;
    private Button enviarCorreo,compartirPuntuacion;

    private static int PERMISOACCEDERCORREOS = 1;
    private boolean permisoCuentas;

//    private final static int PERMISSION_REQUEST_CODE = 1;
//    private String wantPermission = Manifest.permission.GET_ACCOUNTS;

    public PuntuacionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puntuacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        nombre = view.findViewById(R.id.tv_nombrePuntuacion);
        puntuacion = view.findViewById(R.id.tv_puntuacionPuntuacion);
        avatar = view.findViewById(R.id.ivAvatarPuntuacion);
        enviarCorreo = view.findViewById(R.id.btn_enviarMail);
        compartirPuntuacion = view.findViewById(R.id.btn_compartirPuntuacion);

        nombre.setText(viewModel.getUsuarioPuntuacion().getNombre());
        puntuacion.setText(viewModel.getUsuarioPuntuacion().getNRespuestasCorrectas()+" puntos");
        avatar.setImageResource(viewModel.getUsuarioPuntuacion().getAvatar());
        enviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compruebaPermisoContactos();
                if(permisoCuentas){
                    enviarMail();
                }
                enviarMail();
//                if (!checkPermission(wantPermission)) {
//                    requestPermission(wantPermission);
//                } else {
//                    getEmails();
//                }
            }
        });

        compartirPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                DialogFragment dialog = new ContactosFragment();
                dialog.show(getFragmentManager(),"tag");
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
    private void enviarMail() {
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
            startActivity(Intent.createChooser(intent, "Elige una aplicación de mensajería:"));
        }
    }

    private void compruebaPermisoContactos() {
        int permisoLeeCuentas = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.GET_ACCOUNTS);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || (permisoLeeCuentas == PackageManager.PERMISSION_GRANTED)) {

            permisoCuentas = true;

        } else {

            if (shouldShowRequestPermissionRationale(Manifest.permission.GET_ACCOUNTS)) {
                mostrarInfromacionDetalladaPermisoContactos();
            } else {
                requestPermissions(new String[]{Manifest.permission.GET_ACCOUNTS}, PERMISOACCEDERCORREOS);
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISOACCEDERCORREOS){
            int contador = 0;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    contador++;

                }
            }
            if (contador == grantResults.length) {
                permisoCuentas = true;
            } else {

            }
        } else {
            Log.v("Permisos cuentas","zxc");
        }
    }

    private void mostrarInfromacionDetalladaPermisoContactos() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Permisos");
        builder.setMessage("Permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{ Manifest.permission.READ_CONTACTS}, PERMISOACCEDERCORREOS);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();

    }

//    private void getEmails() {
//        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
//
//        // Getting all registered Google Accounts;
//        // Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");
//
//        // Getting all registered Accounts;
//        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
//
//        for (Account account : accounts) {
//            if (emailPattern.matcher(account.name).matches()) {
//                Log.d("TAG", String.format("%s - %s", account.name, account.type));
//            }
//        }
//    }
//
//    private boolean checkPermission(String permission){
//        if (Build.VERSION.SDK_INT >= 23) {
//            int result = ContextCompat.checkSelfPermission(getActivity(), permission);
//            if (result == PackageManager.PERMISSION_GRANTED){
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return true;
//        }
//    }
//
//    private void requestPermission(String permission){
//        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)){
//            Toast.makeText(getActivity(), "Get account permission allows us to get your email",
//                    Toast.LENGTH_LONG).show();
//        }
//        ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, PERMISSION_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    getEmails();
//                } else {
//                    Toast.makeText(getActivity(),"Permission Denied.",
//                            Toast.LENGTH_LONG).show();
//                }
//                break;
//        }
//    }

}