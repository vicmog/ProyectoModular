package com.example.proyectomodular.view.adapters;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {


    private List<Carta> cardlist;
    private Context context;
    private Application application;


    public CardAdapter(Context context, Application application) {
        this.context = context;
        this.application = application;

    }

    public void setMainList(List<Carta> lista) {
        this.cardlist = lista;
    }

    public List getList() {
        return cardlist;
    }


    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new CardAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder holder, int position) {

        String descr = cardlist.get(position).getDescripcion();
        String descr2 = descr.substring(0,90)+" [...]";
        Log.v("asd",descr2);
        holder.name.setText(cardlist.get(position).getNombreAnimal());
        holder.decr.setText(descr2);
        Glide.with(context)
                .load(cardlist.get(position).getUrlFoto())
                .into(holder.img);


        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿Qué deseas hacer?");
                ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);

                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Carta card = cardlist.get(position);
                        vm.setEditarCarta(card);
                        NavController navController;
                        navController = Navigation.findNavController(v);
                        navController.navigate(R.id.editCardFragment);

                    }
                });
                builder.setNeutralButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.v("asd",vm.getEditarPreguntas().toString());

                        vm.delAll(cardlist.get(position).getId());
                        vm.deleteCarta(cardlist.get(position));
                    }
                });

                builder.setNegativeButton("Cancelar", null);

                AlertDialog alert = builder.create();
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return cardlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView decr;
        TextView name;
        ImageView img;
        ConstraintLayout lout;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.userPic);
            name = view.findViewById(R.id.rowUsername);
            decr = view.findViewById(R.id.rowUserStats);
            lout = view.findViewById(R.id.rowIduser);
        }

    }
}
