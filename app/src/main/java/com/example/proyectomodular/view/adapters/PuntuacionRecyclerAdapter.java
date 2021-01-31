package com.example.proyectomodular.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class PuntuacionRecyclerAdapter extends RecyclerView.Adapter<PuntuacionRecyclerAdapter.RecyclerViewHolder> {

    private ViewModel viewModel;
    private Activity activity;
    private List<Usuario> jugadores;
    private View view;

    public PuntuacionRecyclerAdapter(Activity activity, List<Usuario> jugadores, View view) {
        this.activity = activity;
        this.jugadores = jugadores;
        this.view = view;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puntuacion, parent, false);

        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);

        RecyclerViewHolder holder = new RecyclerViewHolder(vista);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        NavController navController = Navigation.findNavController(view);
        Usuario jugador = jugadores.get(position);
        holder.posiciones.setText(position+1+".");
        holder.puntuaciones.setText(jugadores.get(position).getNRespuestasCorrectas()+"");
        holder.nombres.setText(jugadores.get(position).getNombre());
        holder.inner_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setUsuarioPuntuacion(jugador);
                navController.navigate(R.id.puntuacionFragment);
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
