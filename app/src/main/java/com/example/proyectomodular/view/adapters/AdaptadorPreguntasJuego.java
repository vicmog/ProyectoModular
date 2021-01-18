package com.example.proyectomodular.view.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.List;

public class AdaptadorPreguntasJuego extends RecyclerView.Adapter<AdaptadorPreguntasJuego.ViewHolder> {

private List<Pregunta>preguntas;
private View vista;
private String texto;
private Activity activity;
private ViewModel miViewModel;

    public AdaptadorPreguntasJuego(List<Pregunta> preguntas, View vista, Activity act) {
        this.preguntas = preguntas;
        this.vista = vista;
        this.activity = act;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preguntas_juegos,parent,false);
        miViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    holder.tvNombrePregunta.setText(preguntas.get(position).getPregunta());
    holder.bt1.setText(preguntas.get(position).getOpcion1());
    holder.bt2.setText(preguntas.get(position).getOpcion2());
    holder.bt3.setText(preguntas.get(position).getOpcion3());
    holder.bt4.setText(preguntas.get(position).getOpcion4());
    miViewModel.setNumeroRespuestasTotales(preguntas.size());



    holder.bt1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.bt1.setBackgroundColor(R.color.white);
            holder.bt1.setEnabled(false);
            holder.bt2.setEnabled(false);
            holder.bt3.setEnabled(false);
            holder.bt4.setEnabled(false);

            texto = holder.bt1.getText().toString();
            if(texto.compareToIgnoreCase(preguntas.get(position).getRespuesta())==0){
                miViewModel.setPuntuacionPartidaActual(miViewModel.getPuntuacionPartidaActual()+1);
            }
        }
    });
        holder.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bt1.setEnabled(false);
                holder.bt2.setBackgroundColor(R.color.white);
                holder.bt2.setEnabled(false);
                holder.bt3.setEnabled(false);
                holder.bt4.setEnabled(false);

                texto = holder.bt2.getText().toString();
                if(texto.compareToIgnoreCase(preguntas.get(position).getRespuesta())==0){
                    miViewModel.setPuntuacionPartidaActual(miViewModel.getPuntuacionPartidaActual()+1);
                }
            }
        });
        holder.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bt1.setEnabled(false);
                holder.bt2.setEnabled(false);
                holder.bt3.setBackgroundColor(R.color.white);
                holder.bt3.setEnabled(false);
                holder.bt4.setEnabled(false);

                texto = holder.bt3.getText().toString();
                if(texto.compareToIgnoreCase(preguntas.get(position).getRespuesta())==0){
                    miViewModel.setPuntuacionPartidaActual(miViewModel.getPuntuacionPartidaActual()+1);
                }
            }
        });
        holder.bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bt1.setEnabled(false);
                holder.bt2.setEnabled(false);
                holder.bt3.setEnabled(false);
                holder.bt4.setBackgroundColor(R.color.white);
                holder.bt4.setEnabled(false);

                texto = holder.bt4.getText().toString();
                if(texto.compareToIgnoreCase(preguntas.get(position).getRespuesta())==0){
                    miViewModel.setPuntuacionPartidaActual(miViewModel.getPuntuacionPartidaActual()+1);

                }
            }
        });




    }

    @Override
    public int getItemCount() {

        return preguntas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombrePregunta;
        Button bt1,bt2,bt3,bt4;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombrePregunta = itemView.findViewById(R.id.tvPreguntaJuego);
            bt1 = itemView.findViewById(R.id.btPregunta1);
            bt2 = itemView.findViewById(R.id.btPregunta2);
            bt3 = itemView.findViewById(R.id.btPregunta3);
            bt4 = itemView.findViewById(R.id.btPregunta4);
            parent_layout = itemView.findViewById(R.id.clPreguntas);
        }
    }





}
