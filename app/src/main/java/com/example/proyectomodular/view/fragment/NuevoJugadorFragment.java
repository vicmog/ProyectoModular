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

        pics.add(R.drawable.av68);
        pics.add(R.drawable.av69);
        pics.add(R.drawable.av70);
        pics.add(R.drawable.av71);
        pics.add(R.drawable.av72);
        pics.add(R.drawable.av73);
        pics.add(R.drawable.av74);
        pics.add(R.drawable.av75);
        pics.add(R.drawable.av76);
        pics.add(R.drawable.av77);
        pics.add(R.drawable.av78);
        pics.add(R.drawable.av79);
        pics.add(R.drawable.av80);
        pics.add(R.drawable.av81);
        pics.add(R.drawable.av82);
        pics.add(R.drawable.av83);
        pics.add(R.drawable.av84);
        pics.add(R.drawable.av85);
        pics.add(R.drawable.av86);
        pics.add(R.drawable.av87);
        pics.add(R.drawable.av88);
        pics.add(R.drawable.av89);
        pics.add(R.drawable.av90);
        pics.add(R.drawable.av91);
        pics.add(R.drawable.av92);
        pics.add(R.drawable.av93);
        pics.add(R.drawable.av94);
        pics.add(R.drawable.av95);
        pics.add(R.drawable.av96);
        pics.add(R.drawable.av97);
        pics.add(R.drawable.av98);
        pics.add(R.drawable.av99);


        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.pictureChooseRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        newPlayerAdapter adapter = new newPlayerAdapter(getActivity(), getActivity().getApplication(), getView());
        adapter.setMainList(pics);
        recyclerView.setAdapter(adapter);

    }



}