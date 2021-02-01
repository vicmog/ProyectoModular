package com.example.proyectomodular.view.fragment;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.view.adapters.PuntuacionRecyclerAdapter;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HallFameFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private ViewModel viewModel;
    private RecyclerView recyclerView;
    private PuntuacionRecyclerAdapter adapter;
    private List<Usuario> jugadores;
    private Animation animationTrans;

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

        animationTrans = AnimationUtils.loadAnimation(getActivity(),R.anim.trans);
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


        recyclerView = view.findViewById(R.id.recyclerPuntuacion);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new PuntuacionRecyclerAdapter(getActivity(),jugadores,getView());
        recyclerView.setAdapter(adapter);
        recyclerView.startAnimation(animationTrans);

        viewModel.getLiveUsuarioPuntuacionList().observe(getActivity(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                jugadores.clear();
                jugadores.addAll(usuarios);
                adapter.notifyDataSetChanged();
            }
        });

    }

}