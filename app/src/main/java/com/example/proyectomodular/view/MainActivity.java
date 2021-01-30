package com.example.proyectomodular.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.proyectomodular.R;
import com.example.proyectomodular.broadcast.BatteryReceiver;

public class MainActivity extends AppCompatActivity {

    private IntentFilter bateriaIntentFilter;
    private BatteryReceiver Breceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {


        bateriaIntentFilter = new IntentFilter();
        bateriaIntentFilter.addAction (Intent.ACTION_BATTERY_LOW);
        bateriaIntentFilter.addAction (Intent.ACTION_BATTERY_OKAY);
        Breceiver = new BatteryReceiver();
    }

    @Override
    protected void onResume(){
        super.onResume();
        registerReceiver(Breceiver, bateriaIntentFilter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(Breceiver);
    }




}