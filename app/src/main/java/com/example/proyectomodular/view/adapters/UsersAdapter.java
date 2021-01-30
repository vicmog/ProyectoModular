package com.example.proyectomodular.view.adapters;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {


    private List<Usuario> userlist;
    private Context context;
    private Application application;



    public UsersAdapter(Context context, Application application){
        this.context=context;
        this.application = application;

    }

    public void setMainList(List<Usuario> lista){
        this.userlist = lista;
    }

    public List getList(){
        return userlist;
    }


    @NonNull
    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new UsersAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.MyViewHolder holder, int position) {

        if(userlist.get(position).getNRespuestas()!=0){
            int stats1 = (int)(userlist.get(position).getNRespuestasCorrectas())/(userlist.get(position).getNRespuestas());
            holder.stats.setText(stats1+"%");

        }else{
            holder.stats.setText(0+"");
        }


        holder.name.setText(userlist.get(position).getNombre());
        holder.img.setImageResource(userlist.get(position).getAvatar());
        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿Qué deseas hacer?");
                ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);

                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Usuario user = userlist.get(position);
                        vm.setEditar(user);
                        NavController navController;
                        navController = Navigation.findNavController(v);
                        navController.navigate(R.id.editJugadorFragment);

                    }
                });
                builder.setNeutralButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vm.deleteUsuario(userlist.get(position));
                    }
                });

                builder.setNegativeButton("Cancelar",null);

                AlertDialog alert = builder.create();
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView stats;
        TextView name;
        ImageView img;
        ConstraintLayout lout;
        public MyViewHolder(View view){
            super(view);
            img = view.findViewById(R.id.userPic);
            name = view.findViewById(R.id.rowUsername);
            stats = view.findViewById(R.id.rowUserStats);
            lout = view.findViewById(R.id.rowIduser);
        }
    }



}
