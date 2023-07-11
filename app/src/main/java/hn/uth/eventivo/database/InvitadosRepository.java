package hn.uth.eventivo.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class InvitadosRepository {

    private InvitadosDao daoInvitados;
    private LiveData<List<Invitados>> dataset;

    public InvitadosRepository(Application app) {
        EventivoDatabase db = EventivoDatabase.getDatabase(app);
        this.daoInvitados = db.invitadosDao();
        this.dataset = daoInvitados.getInvitados();
    }

    // Método para obtener el conjunto de datos de invitados
    public LiveData<List<Invitados>> getDataset(){
        return dataset;
    }

    // Método para buscar invitados por nombre
    public LiveData<List<Invitados>> buscarPorInvitados(String nombre) {
        return daoInvitados.buscarPorInvitados(nombre);
    }

    // Método para insertar un nuevo invitado en la base de datos
    public void insert(Invitados nuevo){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.insert(nuevo);
        });
    }

    // Método para actualizar los datos de un invitado existente en la base de datos
    public void update(Invitados actualizar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.update(actualizar);
        });
    }

    // Método para eliminar un invitado de la base de datos
    public void delete(Invitados borrar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.delete(borrar);
        });
    }

    // Método para eliminar todos los invitados de la base de datos
    public void deleteAll(){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.deleteAll();
        });
    }
}

