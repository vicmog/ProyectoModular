package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.view.adapters.ModifyPicAdapter;
import com.example.proyectomodular.view.adapters.UsersAdapter;
import com.example.proyectomodular.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class ModificarPicFragment extends Fragment {
    public static ViewModel v;
    public ModifyPicAdapter adapter;
    ArrayList<Integer> pics = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modificar_pic, container, false);
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

        RecyclerView recyclerView = getView().findViewById(R.id.modificarPicRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new ModifyPicAdapter(getActivity(), getActivity().getApplication());
        adapter.setMainList(pics);
        recyclerView.setAdapter(adapter);

    }




}