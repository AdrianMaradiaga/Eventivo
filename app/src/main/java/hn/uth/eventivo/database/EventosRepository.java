package hn.uth.eventivo.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
/**
 * Repositorio para manejar las operaciones relacionadas con los eventos en la base de datos.
 */
public class EventosRepository {
    private EventosDao daoEventos;
    private LiveData<List<Eventos>> dataset;
    /**
     * Constructor de la clase EventosRepository.
     *
     * @param app la aplicación de Android
     */
    public EventosRepository(Application app) {
        EventivoDatabase db = EventivoDatabase.getDatabase(app);
        this.daoEventos = db.eventosDao();
        this.dataset = daoEventos.getEventos();
    }
    /**
     * Obtiene los datos de eventos como LiveData.
     *
     * @return los datos de eventos como LiveData
     */
    public LiveData<List<Eventos>> getDataset(){
        return dataset;
    }
    /**
     * Busca eventos por expositor.
     *
     * @param expositor el nombre del expositor a buscar
     * @return los eventos que coinciden con el expositor especificado como LiveData
     */
    public LiveData<List<Eventos>> buscarPorExpositor(String expositor) {
        return daoEventos.buscarPorExpositor(expositor);
    }
    /**
     * Inserta un nuevo evento en la base de datos.
     *
     * @param nuevo el evento a insertar
     */
    public void insert(Eventos nuevo){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.insert(nuevo);
        });
    }
    /**
     * Actualiza un evento existente en la base de datos.
     *
     * @param actualizar el evento a actualizar
     */
    public void update(Eventos actualizar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.update(actualizar);
        });
    }
    /**
     * Elimina un evento de la base de datos.
     *
     * @param borrar el evento a eliminar
     */
    public void delete(Eventos borrar){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.delete(borrar);
        });
    }
    /**
     * Elimina todos los eventos de la base de datos.
     */
    public void deleteAll(){
        EventivoDatabase.databaseWriteExectutor.execute(()->{
            daoEventos.deleteAll();
        });
    }
}
