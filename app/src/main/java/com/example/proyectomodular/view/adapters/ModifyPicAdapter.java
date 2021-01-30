package com.example.proyectomodular.view.adapters;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class ModifyPicAdapter extends RecyclerView.Adapter<ModifyPicAdapter.MyViewHolder>{

    private ArrayList<Integer> pics;
    private Context context;
    private Application application;

    public ModifyPicAdapter(Context context, Application application){
        this.context=context;
        this.application = application;
    }

    public void setMainList(ArrayList<Integer> lista){
        this.pics = lista;
    }

    public List getList(){
        return pics;
    }


    @NonNull
    @Override
    public ModifyPicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pictures_row, parent, false);
        return new ModifyPicAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ModifyPicAdapter.MyViewHolder holder, int position) {

        holder.pic.setImageResource(pics.get(position));
        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿Confirmar imagen?");

                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ViewModel vm = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
                        vm.getEditar().setAvatar(pics.get(position));
                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.editJugadorFragment);
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
