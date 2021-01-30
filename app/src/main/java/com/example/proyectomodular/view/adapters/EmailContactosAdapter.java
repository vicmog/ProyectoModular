package com.example.proyectomodular.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.Contacto;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.List;

public class EmailContactosAdapter extends RecyclerView.Adapter<EmailContactosAdapter.ViewHolder>{

    private List<Contacto> contactos;
    private ViewModel viewModel;
    private Activity activity;

    public EmailContactosAdapter(Activity activity, List<Contacto> contactos){
        this.activity = activity;
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto,parent,false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        ViewHolder holder = new ViewHolder(vista);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contacto contacto = contactos.get(position);
        holder.contacto_nombre.setText(contactos.get(position).getNombre());
        holder.contacto_email.setText(contactos.get(position).getEmail());
        holder.contacto_enviar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewModel.getEnviarContactos().add(contacto);
                } else {
                    viewModel.getEnviarContactos().remove(contacto);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView contacto_nombre,contacto_numero,contacto_email;
        public CheckBox contacto_enviar;
        public ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            contacto_nombre = itemView.findViewById(R.id.tv_nombreContacto);
            contacto_email = itemView.findViewById(R.id.tv_emailContacto);
            contacto_enviar = itemView.findViewById(R.id.cb_enviarContacto);
            parent_layout = itemView.findViewById(R.id.item_contacto);
        }
    }

}
