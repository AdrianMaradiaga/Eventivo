package hn.uth.eventivo.database;

import android.app.Application;
import android.media.metrics.Event;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EventosRepository {
    private EventosDao dao;
    private LiveData<List<Eventos>> dataset;

    public EventosRepository(Application app) {
        EventivoDatabase db = EventivoDatabase.getDatabase(app);
        this.dao = db.eventosDao();
        this.dataset = dao.getEventos();
    }

    public LiveData<List<Eventos>> getDataset(){
        return dataset;
    }

    public void insert(Eventos nuevo){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            dao.insert(nuevo);
        });
    }

    public void update(Eventos actualizar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            dao.update(actualizar);
        });
    }

    public void delete(Eventos borrar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            dao.delete(borrar);
        });
    }

    public void deleteAll(){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            dao.deleteAll();
        });
    }

}
