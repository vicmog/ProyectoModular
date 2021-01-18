package com.example.proyectomodular.model;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectomodular.model.room.GameDataBase;
import com.example.proyectomodular.model.room.dao.CartaDao;
import com.example.proyectomodular.model.room.dao.PreguntaDao;
import com.example.proyectomodular.model.room.dao.UsuarioDao;
import com.example.proyectomodular.model.room.entity.Carta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.util.ApplicationThread;

import java.util.List;

public class Repository {

    private GameDataBase db;

    private CartaDao cartaDao;
    private PreguntaDao preguntaDao;
    private UsuarioDao usuarioDao;


    private LiveData<List<Usuario>> liveListaUsuarios;
    private LiveData<List<Carta>> liveListaCartas;
    private LiveData<List<Pregunta>>liveListaPreguntas ;
    private MutableLiveData<Carta>cartaAleatoria = new MutableLiveData<>();
    private int puntuacionPartidaActual;
    private int numeroRespuestasTotales;

    private Usuario usuarioJuegoJugador;

    public Repository(Context context){
        db = GameDataBase.getDb(context);
        cartaDao = db.getCartaDao();
        preguntaDao = db.getPreguntaDao();
        usuarioDao = db.getUsuarioDao();
    }


    public Usuario getUsuarioJuegoJugador() {
        return usuarioJuegoJugador;
    }

    public void setUsuarioJuegoJugador(Usuario usuarioJuegoJugador) {
        this.usuarioJuegoJugador = usuarioJuegoJugador;
    }

    public int getNumeroRespuestasTotales() {
        return numeroRespuestasTotales;
    }

    public void setNumeroRespuestasTotales(int numeroRespuestasTotales) {
        this.numeroRespuestasTotales = numeroRespuestasTotales;
    }

    public int getPuntuacionPartidaActual() {
        return puntuacionPartidaActual;
    }

    public void setPuntuacionPartidaActual(int puntuacionPartidaActual) {
        this.puntuacionPartidaActual = puntuacionPartidaActual;
    }

    public MutableLiveData<Carta> getCartaAleatoria() {
        return cartaAleatoria;
    }





//USUARIO

    public void insertUsuario(Usuario usuario) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    usuarioDao.insert(usuario);
                } catch (Exception e) {
                    Log.v("insertUsuario", e.toString());
                }
            }
        });
    }

    public void updateUsuario(Usuario usuario) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    usuarioDao.update(usuario);
                } catch (Exception e) {
                    Log.v("updateUsuario", e.toString());
                }
            }
        });
    }

    public void deleteUsuario(Usuario usuario) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    usuarioDao.delete(usuario);
                } catch (Exception e) {
                    Log.v("deleteUsuario", e.toString());
                }
            }
        });
    }

    public LiveData<List<Usuario>> getLiveUsuarioList() {
        liveListaUsuarios = usuarioDao.getAll();
        return liveListaUsuarios;
    }

    //CARTA

    public void insertCarta(Carta carta) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cartaDao.insert(carta);
                } catch (Exception e) {
                    Log.v("insertCarta", e.toString());
                }
            }
        });
    }

    public void getCarta() {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Carta carta = cartaDao.getCartaAleatoria();


                    cartaAleatoria.postValue(carta);

                } catch (Exception e) {

                }
            }
        });
    }

    public void updateCarta(Carta carta) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cartaDao.update(carta);
                } catch (Exception e) {
                    Log.v("updateCarta", e.toString());
                }
            }
        });
    }

    public void deleteCarta(Carta carta) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cartaDao.delete(carta);
                } catch (Exception e) {
                    Log.v("deleteCarta", e.toString());
                }
            }
        });
    }

    public LiveData<List<Carta>> getLiveCartaList() {
        liveListaCartas = cartaDao.getAll();
        return liveListaCartas;
    }

    //PREGUNTA

    public void insertPregunta(Pregunta pregunta) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    preguntaDao.insert(pregunta);
                } catch (Exception e) {
                    Log.v("insertPregunta", e.toString());
                }
            }
        });
    }

    public void updatePregunta(Pregunta pregunta) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    preguntaDao.update(pregunta);
                } catch (Exception e) {
                    Log.v("updatePregunta", e.toString());
                }
            }
        });
    }

    public void deletePregunta(Pregunta pregunta) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    preguntaDao.delete(pregunta);
                } catch (Exception e) {
                    Log.v("deletePregunta", e.toString());
                }
            }
        });
    }

    public LiveData<List<Pregunta>> getAllPreguntas(long id){
        try {
            liveListaPreguntas = preguntaDao.getAll(id);
            Log.v("ZZZ",liveListaPreguntas.getValue().toString());
        }catch (Exception exception){

        }

        return liveListaPreguntas;
    }
}
