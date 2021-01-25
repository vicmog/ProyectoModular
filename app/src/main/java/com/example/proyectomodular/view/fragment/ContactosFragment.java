package com.example.proyectomodular.view.fragment;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.ContactsContract;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.Contacto;
import com.example.proyectomodular.view.adapters.EmailContactosAdapter;
import com.example.proyectomodular.view.adapters.PuntuacionRecyclerAdapter;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ContactosFragment extends DialogFragment implements AdapterView.OnItemClickListener{

    private ViewModel viewModel;
    private TextView tvprueba;
    private Button prueba;
    private RecyclerView recyclerView;
    private List<Contacto> contactos;
    private EmailContactosAdapter adapter;

    public ContactosFragment() {
        // Required empty public constructor
    }

//    public static ContactosFragment newInstance(){
//        return new ContactosFragment();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contactos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        tvprueba = view.findViewById(R.id.textView8);
        prueba = view.findViewById(R.id.btn_prueba);
        recyclerView = view.findViewById(R.id.recyclerContactos);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new EmailContactosAdapter(getContactos());
        recyclerView.setAdapter(adapter);

        prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMail();
                dismiss();
            }
        });

    }

    public List<Contacto> getContactos(){

        contactos = new ArrayList<>();

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME+" ASC" );

        cursor.moveToFirst();

        int i = 0;
        while (cursor.moveToNext()){
            i++;
            contactos.add(new Contacto(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    , cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    "contacto"+i+"@email.com", false));
        }

        return contactos;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //METODO CON UN INTENT IMPLICITO PARA ENVIAR UN MAIL A TI MISMO
    public void enviarMail() {
        String emailList= "";
        for(Contacto c: contactos){
            if(c.isEnviar()){
                emailList+= c.getEmail()+",";
            }
        }
        String[] emails = emailList.split(",");

        String[] correos = emails;
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