package com.example.proyectomodular.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomodular.R;
import com.example.proyectomodular.view.MainActivity;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String accion= intent.getAction();
        if (accion.equals(Intent.ACTION_BATTERY_LOW)){
            Toast.makeText(context,context.getString(R.string.app_name)+". Batería baja. Por favor, conecte el cargador",Toast.LENGTH_LONG).show();
        }
//        else if(accion.equals(Intent.ACTION_BATTERY_OKAY)) {
//            Toast.makeText(context,"Cargador conectado",Toast.LENGTH_SHORT).show();
//
//        }

    }

}
