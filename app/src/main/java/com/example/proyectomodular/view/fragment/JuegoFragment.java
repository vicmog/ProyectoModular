package com.example.proyectomodular.view.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.view.adapters.AdaptadorJugadorJuego;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class JuegoFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private ViewModel miViewModel;
    private List<Usuario> usuarios;
    private ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_juego, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        bottomNavigationView = view.findViewById(R.id.bottom_navigation_juego);
        miViewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        usuarios = new ArrayList<>();



       // miViewModel.insertUsuario(new Usuario("Pepe",R.drawable.profile1,1,1));

        bottomNavigationView.setSelectedItemId(R.id.jugarPartida);
        navController = Navigation.findNavController(view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.players:navController.navigate(R.id.hallFameFragment);
                        break;
                    case R.id.jugarPartida:navController.navigate(R.id.juegoFragment);
                        break;
                    case R.id.adminArea:navController.navigate(R.id.adminFragment);
                        break;
                }
                return true;
            }
        });

        RecyclerView recyclerViewJuego = getView().findViewById(R.id.recyclerViewJugadorElegido);
        AdaptadorJugadorJuego adapter = new AdaptadorJugadorJuego(getActivity(),usuarios,getView());
        recyclerViewJuego.setAdapter(adapter);
        recyclerViewJuego.setLayoutManager(new LinearLayoutManager(getActivity()));

        miViewModel.getLiveUsuarioList().observe(getActivity(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> users) {
            usuarios.clear();
            usuarios.addAll(users);
            adapter.notifyDataSetChanged();
            }
        });




    }
}