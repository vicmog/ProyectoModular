package com.example.proyectomodular.model.receiver;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.proyectomodular.R;

public class BateryLowReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
    String state = intent.getAction();
    if(state.equals(Intent.ACTION_BATTERY_LOW)){

        Toast.makeText(context, R.string.app_name+"Bateria baja.Por favor,conecte el cargador",Toast.LENGTH_LONG).show();

    }
    }
}