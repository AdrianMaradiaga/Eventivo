package hn.uth.eventivo.ui.eventos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.database.EventosRepository;

public class EventosViewModel extends AndroidViewModel {
    private EventosRepository repository;
    private final LiveData<List<Eventos>> eventosDataset;
    /**
     * Constructor de la clase.
     * @param app La aplicación Android.
     */
    public EventosViewModel(@NonNull Application app) {
        super(app);
        this.repository = new EventosRepository(app);
        this.eventosDataset = repository.getDataset();
    }

    /**
     * Obtiene el repositorio de eventos.
     * @return El repositorio de eventos.
     */
    public EventosRepository getRepository() {
        return repository;
    }

    /**
     * Obtiene el dataset de eventos como LiveData.
     * @return El dataset de eventos como LiveData.
     */
    public LiveData<List<Eventos>> getEventosDataset() {
        return eventosDataset;
    }

    /**
     * Realiza una búsqueda de eventos por expositor.
     * @param expositor El nombre del expositor a buscar.
     * @return El resultado de la búsqueda como LiveData.
     */
    public LiveData<List<Eventos>> buscarPorExpositor(String expositor) {
        return repository.buscarPorExpositor(expositor);
    }

    /**
     * Inserta un nuevo evento.
     * @param nuevo El evento a insertar.
     */
    public void insert(Eventos nuevo){
        repository.insert(nuevo);
    }

    /**
     * Actualiza un evento existente.
     * @param actualizar El evento a actualizar.
     */
    public void update(Eventos actualizar){
        repository.update(actualizar);
    }

    /**
     * Elimina un evento.
     * @param eliminar El evento a eliminar.
     */
    public void delete(Eventos eliminar){
        repository.delete(eliminar);
    }

    /**
     * Elimina todos los eventos.
     */
    public void deleteAll(){
        repository.deleteAll();
    }
}

