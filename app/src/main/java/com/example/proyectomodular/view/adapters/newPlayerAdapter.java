package com.example.proyectomodular.view.adapters;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class newPlayerAdapter extends RecyclerView.Adapter<newPlayerAdapter.MyViewHolder> {


    private ArrayList<Integer> pics;
    private Context context;
    private Application application;
    private View view;

    public newPlayerAdapter(Context context, Application application, View vew){
        this.context=context;
        this.application = application;
        this.view = view;
    }

    public void setMainList(ArrayList<Integer> lista){
        this.pics = lista;
        notifyDataSetChanged();
    }

    public List getList(){
        return pics;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pictures_row, parent, false);
        return new newPlayerAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull newPlayerAdapter.MyViewHolder holder, int position) {

        holder.pic.setImageResource(pics.get(position));
        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿Como te llamas?");
                final EditText input = new EditText(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Usuario user = new Usuario(input.getText().toString(),pics.get(position),0,0);
                        ViewModel vm = new ViewModel(application);
                        vm.insertUsuario(user);

                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.adminFragment);
                        Toast.makeText(context, ""+user.getNombre()+" ha sido creado!", Toast.LENGTH_SHORT).show();
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
        return this.pics.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView pic;
        ConstraintLayout lout;
        public MyViewHolder(View view){
            super(view);
            pic = view.findViewById(R.id.idPic);
            lout = view.findViewById(R.id.rowId);
        }
    }

}
