package com.example.proyectomodular.model;

import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.CartaConPregunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartaClient {
    @DELETE("cartas/{id}")
    Call<Integer> deleteCarta(@Path("id") long id);

    @GET("cartas/{id}")
    Call<Carta> getCarta(@Path("id") long id);

    @GET("cartas")
    Call<List<CartaConPregunta>> getCarta();

    @POST("cartas")
    Call<Long> postCarta(@Body Carta carta);

    @PUT("cartas/{id}")
    Call<Integer> putCarta(@Path("id") long id, @Body Carta carta);

}
