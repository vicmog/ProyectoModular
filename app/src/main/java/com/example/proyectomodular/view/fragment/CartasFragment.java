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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.view.adapters.CardAdapter;
import com.example.proyectomodular.view.adapters.UsersAdapter;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartasFragment extends Fragment {
    private List<Carta> cardList = new ArrayList<>();         //PORK COÑO NO PUEDE SER UN ARRAYLIST?????
    public static ViewModel v;
    public CardAdapter adapter;
    private NavController navController;
    private Button newCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cartas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newCard = view.findViewById(R.id.btNewCard);

        newCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.newCardFragment);
            }
        });




        v = new ViewModelProvider(this).get(ViewModel.class);
        v.getLiveCartaList().observe(getActivity(), new Observer<List<Carta>>() {
            @Override
            public void onChanged(List<Carta> cartas) {
                cardList = new ArrayList<>();
                cardList.addAll(cartas);

                initRecyclerView(view);
            }
        });




    }


    private void initRecyclerView(View v) {

        RecyclerView recyclerView = v.findViewById(R.id.recyclerCartas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new CardAdapter(getActivity(), getActivity().getApplication());
        adapter.setMainList(cardList);
        recyclerView.setAdapter(adapter);
    }




}