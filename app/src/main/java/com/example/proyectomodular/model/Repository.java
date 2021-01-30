package com.example.proyectomodular.model;

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
    private Usuario editar;
    private long idCarta;
    private Carta editarCarta;
    private List<Pregunta> editarPreguntas;

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

    public Repository(Context context){
        db = GameDataBase.getDb(context);
        cartaDao = db.getCartaDao();
        preguntaDao = db.getPreguntaDao();
        usuarioDao = db.getUsuarioDao();
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


}
