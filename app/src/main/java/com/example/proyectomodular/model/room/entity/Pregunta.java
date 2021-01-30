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

    @NonNull
    @ColumnInfo(name = "res2")
    private String res2;

    @NonNull
    public String getRes2() {
        return res2;
    }

    public void setRes2(@NonNull String res2) {
        this.res2 = res2;
    }

    @NonNull
    public String getRes3() {
        return res3;
    }

    public void setRes3(@NonNull String res3) {
        this.res3 = res3;
    }

    @NonNull
    public String getRes4() {
        return res4;
    }

    public void setRes4(@NonNull String res4) {
        this.res4 = res4;
    }

    @NonNull
    @ColumnInfo(name = "res3")
    private String res3;

    @NonNull
    @ColumnInfo(name = "res4")
    private String res4;

    public Pregunta(long idCarta, String pregunta, String respuesta,
                    @NonNull String res2, @NonNull String res3, @NonNull String res4 ) {
        this.idCarta = idCarta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.res2 = res2;
        this.res3 = res3;
        this.res4 = res4;
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
