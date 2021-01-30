package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.util.OnItemClickListener;
import com.example.proyectomodular.view.adapters.PuntuacionRecyclerAdapter;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HallFameFragment extends Fragment implements OnItemClickListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private ViewModel viewModel;
    private RecyclerView recyclerView;
    private PuntuacionRecyclerAdapter adapter;
    private List<Usuario> jugadores;

    public HallFameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hall_fame, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_hall);
        navController = Navigation.findNavController(view);

        jugadores = new ArrayList<>();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.players:navController.navigate(R.id.hallFameFragment);
                        break;
                    case R.id.jugarPartida:navController.navigate(R.id.juegoFragment);
                        break;
                    case R.id.adminArea:navController.navigate(R.id.adminPassFragment);
                        break;
                }
                return true;
            }
        });

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        viewModel.insertUsuario(new Usuario("Tete",R.drawable.avatar,1,30));

        recyclerView = view.findViewById(R.id.recyclerPuntuacion);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new PuntuacionRecyclerAdapter(jugadores,this);
        recyclerView.setAdapter(adapter);

        viewModel.getLiveUsuarioPuntuacionList().observe(getActivity(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                jugadores.clear();
                jugadores.addAll(usuarios);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onClick(Object usuario) {
        viewModel.setUsuarioPuntuacion((Usuario)usuario);
        NavHostFragment.findNavController(HallFameFragment.this).navigate(R.id.action_hallFameFragment_to_puntuacionFragment);

    }
}