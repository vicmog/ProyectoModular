package com.example.proyectomodular.model.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "pregunta",foreignKeys = @ForeignKey(
        entity = Carta.class,
        parentColumns = "id",
        childColumns = "idCarta",
        onDelete = ForeignKey.RESTRICT))
public class Pregunta {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "idCarta")
    private long idCarta;

    @NonNull
    @ColumnInfo(name = "pregunta")
    private String pregunta;

    @NonNull
    @ColumnInfo(name = "opcion1")
    private String opcion1;

    @NonNull
    @ColumnInfo(name = "opcion2")
    private String opcion2;

    @NonNull
    @ColumnInfo(name = "opcion3")
    private String opcion3;

    @NonNull
    @ColumnInfo(name = "opcion4")
    private String opcion4;

    public Pregunta(long idCarta, @NonNull String pregunta,@NonNull String opcion1, @NonNull String opcion2, @NonNull String opcion3, @NonNull String opcion4) {

        this.idCarta = idCarta;
        this.pregunta = pregunta;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(long idCarta) {
        this.idCarta = idCarta;
    }

    @NonNull
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(@NonNull String pregunta) {
        this.pregunta = pregunta;
    }


    @NonNull
    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(@NonNull String opcion1) {
        this.opcion1 = opcion1;
    }

    @NonNull
    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(@NonNull String opcion2) {
        this.opcion2 = opcion2;
    }

    @NonNull
    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(@NonNull String opcion3) {
        this.opcion3 = opcion3;
    }

    @NonNull
    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(@NonNull String opcion4) {
        this.opcion4 = opcion4;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", idCarta=" + idCarta +
                ", pregunta='" + pregunta + '\'' +
                ", opcion1='" + opcion1 + '\'' +
                ", opcion2='" + opcion2 + '\'' +
                ", opcion3='" + opcion3 + '\'' +
                ", opcion4='" + opcion4 + '\'' +
                '}';
    }
}
