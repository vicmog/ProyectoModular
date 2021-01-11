package com.example.proyectomodular.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.List;

public class AdaptadorJugadorJuego extends RecyclerView.Adapter<AdaptadorJugadorJuego.ViewHolder> {

    private ViewModel miViewModel;
    private Activity activity;
    private List<Usuario>usuarios;
    private View view;


    public AdaptadorJugadorJuego(Activity activity, List<Usuario> usuarios, View view) {
        this.activity = activity;
        this.usuarios = usuarios;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador_juego,parent,false);
        miViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavController navController = Navigation.findNavController(view);
        holder.tvNombre.setText(usuarios.get(position).getNombre());
        holder.imgImagen.setImageResource(usuarios.get(position).getAvatar());
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            Usuario usuario = usuarios.get(position);
            miViewModel.setUsuarioJuegoJugador(usuario);
            navController.navigate(R.id.juegoJugarFragment);

            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre;
        ImageView imgImagen;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreJugadorJuego);
            imgImagen = itemView.findViewById(R.id.imgJugadorJuego);
            parent_layout = itemView.findViewById(R.id.clItemJugadorJuego);
        }
    }







}
