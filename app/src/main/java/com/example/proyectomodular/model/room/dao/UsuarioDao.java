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
public interface UsuarioDao {

    @Insert
    long insert(Usuario usuario);

    @Update
    int update(Usuario usuario);

    @Delete
    int delete(Usuario usuario);

    @Query("select * from usuario order by id")
    LiveData<List<Usuario>> getAll();

}
