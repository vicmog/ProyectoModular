package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
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

        RecyclerView recyclerView = getView().findViewById(R.id.modificarPicRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new ModifyPicAdapter(getActivity(), getActivity().getApplication());
        adapter.setMainList(pics);
        recyclerView.setAdapter(adapter);

    }




}