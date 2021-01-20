package com.example.proyectomodular.view.fragment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.viewmodel.ViewModel;

public class PuntuacionFragment extends Fragment {

    private ViewModel viewModel;
    private TextView nombre,puntuacion;
    private ImageView avatar;
    private Button enviarCorreo;

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

        nombre.setText(viewModel.getUsuarioPuntuacion().getNombre());
        puntuacion.setText(viewModel.getUsuarioPuntuacion().getNRespuestasCorrectas()+" puntos");
        avatar.setImageResource(viewModel.getUsuarioPuntuacion().getAvatar());
        enviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMail();
            }
        });

    }

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

}