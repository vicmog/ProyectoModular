package com.example.proyectomodular.model.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;

@Dao
public interface PreguntaDao {

    @Insert
    long insert(Pregunta pregunta);

    @Update
    int update(Pregunta pregunta);

    @Delete
    int delete(Pregunta pregunta);
}
