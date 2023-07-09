package hn.uth.eventivo.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EventosRepository {
    private EventosDao daoEventos;
    private LiveData<List<Eventos>> dataset;

    public EventosRepository(Application app) {
        EventivoDatabase db = EventivoDatabase.getDatabase(app);
        this.daoEventos = db.eventosDao();
        this.dataset = daoEventos.getEventos();
    }

    public LiveData<List<Eventos>> getDataset(){
        return dataset;
    }

    public LiveData<List<Eventos>> buscarPorExpositor(String expositor) {
        return daoEventos.buscarPorExpositor(expositor);
    }


    public void insert(Eventos nuevo){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.insert(nuevo);
        });
    }

    public void update(Eventos actualizar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.update(actualizar);
        });
    }

    public void delete(Eventos borrar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.delete(borrar);
        });
    }

    public void deleteAll(){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.deleteAll();
        });
    }
}
