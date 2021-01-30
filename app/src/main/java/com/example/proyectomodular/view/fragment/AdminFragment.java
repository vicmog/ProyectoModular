package com.example.proyectomodular.view.fragment;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectomodular.R;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.view.adapters.UsersAdapter;
import com.example.proyectomodular.view.adapters.ViewPagerAdapter;
import com.example.proyectomodular.view.adapters.newPlayerAdapter;
import com.example.proyectomodular.viewmodel.ViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AdminFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        bottomNavigationView = view.findViewById(R.id.bottom_navigation_admin);


        tabLayout = getView().findViewById(R.id.tabLayoutAdmin);
        viewPager = getView().findViewById(R.id.viewPagerAdmin);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),1);
        viewPagerAdapter.addFragments(new ListaUsuarios(),"USUARIOS");
        viewPagerAdapter.addFragments(new CartasFragment(),"CARTAS");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bottomNavigationView.setSelectedItemId(R.id.adminArea);
        navController = Navigation.findNavController(view);
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

    }


}