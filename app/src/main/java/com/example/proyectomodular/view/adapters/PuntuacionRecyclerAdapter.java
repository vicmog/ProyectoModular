package com.example.proyectomodular.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.util.OnItemClickListener;

import java.util.List;

public class PuntuacionRecyclerAdapter extends RecyclerView.Adapter<PuntuacionRecyclerAdapter.RecyclerViewHolder> implements View.OnClickListener {

    private List<Usuario> jugadores;
    private OnItemClickListener listener;
    private View.OnClickListener listeneritem;

    public PuntuacionRecyclerAdapter(List<Usuario> jugadores, OnItemClickListener listener) {
        this.jugadores = jugadores;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puntuacion, parent, false);
        vista.setOnClickListener(this);

        RecyclerViewHolder holder = new RecyclerViewHolder(vista);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Usuario jugador = jugadores.get(position);
        holder.posiciones.setText(position+1+".");
        holder.puntuaciones.setText(jugadores.get(position).getNRespuestasCorrectas()+"");
        holder.nombres.setText(jugadores.get(position).getNombre());
        holder.inner_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(jugador);
            }
        });
//        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //view.findNavController().navigate(R.id.action_searchFragment_to_artistFragment)
//                //Navigation.createNavigateOnClickListener(R.id.action_searchFragment_to_artistFragment)
//                //Navigation.findNavController(view).navigate(R.id.juegoJugarFragment);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listeneritem=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView posiciones;
        public TextView puntuaciones;
        public TextView nombres;
        public ConstraintLayout inner_layout;
        public ConstraintLayout parent_layout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            posiciones = itemView.findViewById(R.id.tvPosicionPuntuacion);
            puntuaciones = itemView.findViewById(R.id.tvPuntuacion);
            nombres = itemView.findViewById(R.id.tv_puntuacionNombre);
            inner_layout = itemView.findViewById(R.id.boton_puntuacion);
            parent_layout = itemView.findViewById(R.id.item_puntuacion);
        }
    }
}
