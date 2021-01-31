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
import com.example.proyectomodular.model.room.entity.CartaConPregunta;
import com.example.proyectomodular.model.room.entity.Pregunta;
import com.example.proyectomodular.model.room.entity.Usuario;
import com.example.proyectomodular.util.ApplicationThread;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private GameDataBase db;

    private CartaDao cartaDao;
    private PreguntaDao preguntaDao;
    private UsuarioDao usuarioDao;
    private Usuario editar;
    private long idCarta;
    private Carta editarCarta;
    private List<Pregunta> editarPreguntas;
    private MutableLiveData<List<CartaConPregunta>> paqueteCartas = new MutableLiveData<>();

    private CartaClient client;

    public Pregunta getEditarPregunta() {
        return editarPregunta;
    }

    public void setEditarPregunta(Pregunta editarPregunta) {
        this.editarPregunta = editarPregunta;
    }

    private Pregunta editarPregunta;

    MutableLiveData<Long> cardId = new MutableLiveData<>();
    MutableLiveData<List<Pregunta>> listaPreguntas = new MutableLiveData<>();


    private LiveData<List<Usuario>> liveListaUsuarios;
    private LiveData<List<Carta>> liveListaCartas;
    private LiveData<List<Pregunta>>liveListaPreguntas ;
    private MutableLiveData<Carta>cartaAleatoria = new MutableLiveData<>();
    private int puntuacionPartidaActual;
    private int numeroRespuestasTotales;

    private  long idCartaAnterior=0;

    public long getIdCartaAnterior() {
        return idCartaAnterior;
    }

    public void setIdCartaAnterior(long idCartaAnterior) {
        this.idCartaAnterior = idCartaAnterior;
    }

    private Usuario usuarioJuegoJugador;

    private Usuario usuarioPuntuacion;

    private List<Contacto> enviarContactos;

    public Repository(Context context){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://informatica.ieszaidinvergeles.org:9033/laraveles/appAnimalesSalvajes/public/api/").addConverterFactory(GsonConverterFactory.create()).build();
        client = retrofit.create(CartaClient.class);
        db = GameDataBase.getDb(context);
        cartaDao = db.getCartaDao();
        preguntaDao = db.getPreguntaDao();
        usuarioDao = db.getUsuarioDao();
    }

    public MutableLiveData<List<CartaConPregunta>> getPaqueteCartas() {
        return paqueteCartas;
    }

    public void descargarPaquete(){
        Call<List<CartaConPregunta>> paquete = client.getCarta();
        paquete.enqueue(new Callback<List<CartaConPregunta>>() {
            @Override
            public void onResponse(Call<List<CartaConPregunta>> call, Response<List<CartaConPregunta>> response) {
                ApplicationThread.threadExecutorPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String descripcion,nombre,url;
                            List<Pregunta>listaPreguntas;
                            Pregunta pregunta;
                            long id;

                            List<CartaConPregunta>list = response.body();

                            for (int i = 0; i < list.size(); i++) {

                                descripcion = list.get(i).getDescripcion();
                                url = list.get(i).getUrl();
                                nombre = list.get(i).getNombre();
                                Carta carta = new Carta(url,nombre,descripcion);

                                id = cartaDao.insert(carta);
                                listaPreguntas = list.get(i).getPreguntas();

                                for (int j = 0; j < listaPreguntas.size(); j++) {
                                    pregunta = listaPreguntas.get(j);
                                    pregunta.setIdCarta(id);
                                    preguntaDao.insert(pregunta);

                                }

                            }

                        }catch(Exception e){

                        }


                    }
                });

            }

            @Override
            public void onFailure(Call<List<CartaConPregunta>> call, Throwable t) {
                Log.v("ZZZ",t.getLocalizedMessage());
            }
        });
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

    public void delAll(long id){
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    preguntaDao.delAll(id);
                } catch (Exception e) {
                    Log.v("getPreguntas", e.toString());
                }
            }
        });
    }


    public List<Pregunta> getEditarPreguntas() {
        return editarPreguntas;
    }

    public void setEditarPreguntas(List<Pregunta> editarPreguntas) {
        this.editarPreguntas = editarPreguntas;
    }

    public void postPreguntas(long id){
         ApplicationThread.threadExecutorPool.execute(new Runnable() {
             @Override
             public void run() {
                 try {
                     List<Pregunta> lista = preguntaDao.getPreguntas(id);
                     listaPreguntas.postValue(lista);
                 } catch (Exception e) {
                     Log.v("getPreguntas", e.toString());
                 }
             }
         });
     }

     public MutableLiveData<List<Pregunta>> getListaPreguntas(){
        return listaPreguntas;
     }

    public long getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(long idCarta) {
        this.idCarta = idCarta;
    }

    public MutableLiveData<Long> getCardId() {
        return cardId;
    }

    public Usuario getEditar() {
        return editar;
    }

    public void setEditar(Usuario editar) {
        this.editar = editar;
    }

    public Carta getEditarCarta() {
        return editarCarta;
    }

    public void setEditarCarta(Carta editar) {
        this.editarCarta = editar;
    }

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

    public LiveData<List<Usuario>> getLiveUsuarioPuntuacionList() {
        liveListaUsuarios = usuarioDao.getAllByPuntuacion();
        return liveListaUsuarios;
    }

    public Usuario getUsuarioPuntuacion(){
        return this.usuarioPuntuacion;
    }

    public void setUsuarioPuntuacion(Usuario usuarioPuntuacion){
        this.usuarioPuntuacion=usuarioPuntuacion;
    }

    //CARTA

    public void insertCarta(Carta carta) {

        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    long id = cartaDao.insert(carta);
                    cardId.postValue(id);


                } catch (Exception e) {
                    Log.v("insertCarta", e.toString());
                }
            }
        });
    }

    public void getCarta(long id) {
        ApplicationThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Carta carta = cartaDao.getCartaAleatoria(id);


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

    //CONTACTO

    public List<Contacto> getEnviarContactos(){
        return this.enviarContactos;
    }

    public void setEnviarContactos(List<Contacto> enviarContactos){
        this.enviarContactos = enviarContactos;
    }

}
