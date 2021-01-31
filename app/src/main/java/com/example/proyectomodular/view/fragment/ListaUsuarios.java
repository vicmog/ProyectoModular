package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.view.adapters.UsersAdapter;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarios extends Fragment {
    private List<Usuario> userList = new ArrayList<>();         //PORK COÑO NO PUEDE SER UN ARRAYLIST?????
    public static ViewModel v;
    public UsersAdapter adapter;
    private NavController navController;
    private Animation animationTrans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lista_usuarios, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animationTrans = AnimationUtils.loadAnimation(getActivity(),R.anim.trans);

        v = new ViewModelProvider(this).get(ViewModel.class);
        v.getLiveUsuarioList().observe(getActivity(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                userList = new ArrayList<>();
                userList.addAll(usuarios);

                initRecyclerView(view);
            }
        });



        Button btNewPlayer = view.findViewById(R.id.btNewPlayer);

        btNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.nuevoJugadorFragment);
            }
        });

    }

    private void initRecyclerView(View v) {

        RecyclerView recyclerView = v.findViewById(R.id.userRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new UsersAdapter(getActivity(), getActivity().getApplication());
        adapter.setMainList(userList);
        recyclerView.setAdapter(adapter);
        recyclerView.startAnimation(animationTrans);
    }










}