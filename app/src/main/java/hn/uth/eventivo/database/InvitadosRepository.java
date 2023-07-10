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

    public LiveData<List<Invitados>> getDataset(){
        return dataset;
    }

    public LiveData<List<Invitados>> buscarPorInvitados(String nombre) {
        return daoInvitados.buscarPorInvitados(nombre);
    }

    public void insert(Invitados nuevo){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.insert(nuevo);
        });
    }

    public void update(Invitados actualizar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.update(actualizar);
        });
    }

    public void delete(Invitados borrar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.delete(borrar);
        });
    }

    public void deleteAll(){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoInvitados.deleteAll();
        });
    }
}
