package com.example.proyectomodular.model.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "avatar")
    private String avatar;

    @NonNull
    @ColumnInfo(name = "nRespuestas")
    private int nRespuestas;

    @NonNull
    @ColumnInfo(name = "nRespuestasCorrectas")
    private  int nRespuestasCorrectas;

    public Usuario(@NonNull String nombre, @NonNull String avatar, int nRespuestas, int nRespuestasCorrectas) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.nRespuestas = nRespuestas;
        this.nRespuestasCorrectas = nRespuestasCorrectas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(@NonNull String avatar) {
        this.avatar = avatar;
    }

    public int getNRespuestas() {
        return nRespuestas;
    }

    public void setNRespuestas(int nRespuestas) {
        this.nRespuestas = nRespuestas;
    }

    public int getNRespuestasCorrectas() {
        return nRespuestasCorrectas;
    }

    public void setNRespuestasCorrectas(int nRespuestasCorrectas) {
        this.nRespuestasCorrectas = nRespuestasCorrectas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nRespuestas=" + nRespuestas +
                ", nRespuestasCorrectas=" + nRespuestasCorrectas +
                '}';
    }

}
