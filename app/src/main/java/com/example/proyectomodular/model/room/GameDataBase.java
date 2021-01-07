package com.example.proyectomodular.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectomodular.model.room.dao.CartaDao;
import com.example.proyectomodular.model.room.dao.PreguntaDao;
import com.example.proyectomodular.model.room.dao.UsuarioDao;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;

@Database(entities = {Carta.class, Usuario.class, Pregunta.class},version = 1,exportSchema = false)
public abstract class GameDataBase extends RoomDatabase {

    public abstract CartaDao getCartaDao();
    public abstract PreguntaDao getPreguntaDao();
    public abstract UsuarioDao getUsuarioDao();

    private volatile static GameDataBase INSTANCE;

    public static synchronized GameDataBase getDb(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    GameDataBase.class, "dbgame.sqlite")
                    .build();
        }
        return INSTANCE;
    }
}
