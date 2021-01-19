package com.example.proyectomodular.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectomodular.R;
import com.example.proyectomodular.view.adapters.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


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
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),1);
        viewPagerAdapter.addFragments(new NuevoJugadorFragment(),"USUARIOS");
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