package com.example.proyectomodular.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;

import java.util.List;

@Dao
public interface PreguntaDao {

    @Insert
    long insert(Pregunta pregunta);

    @Update
    int update(Pregunta pregunta);

    @Delete
    int delete(Pregunta pregunta);

    @Query("select * from pregunta where pregunta.idCarta = :id")
    LiveData<List<Pregunta>> getAll(long id);
}
