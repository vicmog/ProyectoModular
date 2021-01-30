package com.example.proyectomodular.view.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectomodular.R;
import com.example.proyectomodular.view.adapters.newPlayerAdapter;

import java.util.ArrayList;

public class NuevoJugadorFragment extends Fragment {
    ArrayList<Integer> pics = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nuevo_jugador, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pics.add(R.drawable.av1);
        pics.add(R.drawable.av2);
        pics.add(R.drawable.av3);
        pics.add(R.drawable.av4);
        pics.add(R.drawable.av5);
        pics.add(R.drawable.av6);
        pics.add(R.drawable.av7);
        pics.add(R.drawable.av8);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.pictureChooseRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        newPlayerAdapter adapter = new newPlayerAdapter(getActivity(), getActivity().getApplication());
        adapter.setMainList(pics);
        recyclerView.setAdapter(adapter);

    }



}