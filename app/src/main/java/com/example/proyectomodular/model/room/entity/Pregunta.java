package com.example.proyectomodular.model.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
    @ColumnInfo(name = "respuesta")
    private String respuesta;

    public Pregunta(long idCarta, @NonNull String pregunta, @NonNull String respuesta) {
        this.idCarta = idCarta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
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
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(@NonNull String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", idCarta=" + idCarta +
                ", pregunta='" + pregunta + '\'' +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }

}
