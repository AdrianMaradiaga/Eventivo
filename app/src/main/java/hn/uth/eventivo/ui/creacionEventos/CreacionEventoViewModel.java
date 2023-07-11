package hn.uth.eventivo.ui.creacionEventos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.database.EventosRepository;


public class CreacionEventoViewModel extends AndroidViewModel {

    private EventosRepository repository;

    /**
     * Constructor de la clase CreacionEventoViewModel.
     * @param app La aplicación en la que se utilizará el ViewModel.
     */
    public CreacionEventoViewModel(@NonNull Application app) {
        super(app);
        this.repository = new EventosRepository(app);
    }

    /**
     * Obtiene el repositorio de eventos asociado al ViewModel.
     * @return El repositorio de eventos.
     */
    public EventosRepository getRepository() {
        return repository;
    }

    /**
     * Inserta un nuevo evento en el repositorio.
     * @param nuevo El evento a insertar.
     */
    public void insert(Eventos nuevo){
        repository.insert(nuevo);
    }

    /**
     * Actualiza un evento existente en el repositorio.
     * @param actualizar El evento a actualizar.
     */
    public void update(Eventos actualizar){
        repository.update(actualizar);
    }

    /**
     * Elimina un evento del repositorio.
     * @param eliminar El evento a eliminar.
     */
    public void delete(Eventos eliminar){
        repository.delete(eliminar);
    }

    /**
     * Elimina todos los eventos del repositorio.
     */
    public void deleteAll(){
        repository.deleteAll();
    }

}
