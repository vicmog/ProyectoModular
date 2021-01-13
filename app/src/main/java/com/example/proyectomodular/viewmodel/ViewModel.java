package com.example.proyectomodular.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectomodular.model.Repository;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;


    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Usuario getUsuarioJuegoJugador() {
        return repository.getUsuarioJuegoJugador();
    }

    public void setUsuarioJuegoJugador(Usuario usuarioJuegoJugador) {
        repository.setUsuarioJuegoJugador(usuarioJuegoJugador);
    }

    public MutableLiveData<Long> getNumeroCartasDisponibles() {
        return repository.getNumeroCartasDisponibles();
    }

    public void getNumeroCartas() {
        repository.getNumeroCartas();
    }

    public int getIdCartaAnteriorJugada() {
        return repository.getIdCartaAnteriorJugada();
    }

    public void setIdCartaAnteriorJugada(int idCartaAnteriorJugada) {
        repository.setIdCartaAnteriorJugada(idCartaAnteriorJugada);
    }

    public MutableLiveData<Carta> getCartaAleatoria() {
        return repository.getCartaAleatoria();
    }

    public void getCarta(long id) {
        repository.getCarta(id);
    }

    //USUARIO

    public void insertUsuario(Usuario usuario){
        repository.insertUsuario(usuario);
    }

    public void updateUsuario(Usuario usuario){
        repository.updateUsuario(usuario);
    }

    public void deleteUsuario(Usuario usuario){
        repository.deleteUsuario(usuario);
    }

    public LiveData<List<Usuario>> getLiveUsuarioList() {
        return repository.getLiveUsuarioList();
    }

    //CARTA

    public void insertCarta(Carta carta){
        repository.insertCarta(carta);
    }

    public void updateCarta(Carta carta){
        repository.updateCarta(carta);
    }

    public void deleteCarta(Carta carta){
        repository.deleteCarta(carta);
    }

    public LiveData<List<Carta>> getLiveCartaList() {
        return repository.getLiveCartaList();
    }

    //PREGUNTA

    public void insertPregunta(Pregunta pregunta){
        repository.insertPregunta(pregunta);
    }

    public void updatePregunta(Pregunta pregunta){
        repository.updatePregunta(pregunta);
    }

    public void deletePregunta(Pregunta pregunta){
        repository.deletePregunta(pregunta);
    }
}
