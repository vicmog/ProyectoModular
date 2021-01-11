package com.example.proyectomodular.model.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carta")
public class Carta {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "urlFoto")
    private int urlFoto;

    @NonNull
    @ColumnInfo(name = "nombreAnimal")
    private String nombreAnimal;

    @NonNull
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    public Carta(@NonNull int urlFoto, @NonNull String nombreAnimal, @NonNull String descripcion) {
        this.urlFoto = urlFoto;
        this.nombreAnimal = nombreAnimal;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public int getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(@NonNull int urlFoto) {
        this.urlFoto = urlFoto;
    }

    @NonNull
    public String getNombreAnimal() {
        return nombreAnimal;
    }

    public void setNombreAnimal(@NonNull String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "id=" + id +
                ", urlFoto='" + urlFoto + '\'' +
                ", nombreAnimal='" + nombreAnimal + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
