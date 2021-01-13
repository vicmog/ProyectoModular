package com.example.proyectomodular.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Usuario;

import java.util.List;

@Dao
public interface CartaDao {

    @Insert
    long insert(Carta carta);

    @Update
    int update(Carta carta);

    @Delete
    int delete(Carta carta);

    @Query("select * from carta order by id")
    LiveData<List<Carta>> getAll();

    @Query("select count(carta.id) from carta")
    long getNumeroCartas();

    @Query("select * from carta where carta.id = :id")
    Carta get(long id);

}
