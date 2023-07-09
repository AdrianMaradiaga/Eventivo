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
    public EventosViewModel(@NonNull Application app) {
        super(app);
        this.repository = new EventosRepository(app);
        this.eventosDataset = repository.getDataset();
    }


    public EventosRepository getRepository() {
        return repository;
    }

    public LiveData<List<Eventos>> getEventosDataset() {
        return eventosDataset;
    }

    public LiveData<List<Eventos>> buscarPorExpositor(String expositor) {
        return repository.buscarPorExpositor(expositor);
    }

    public void insert(Eventos nuevo){
        repository.insert(nuevo);
    }

    public void update(Eventos actualizar){
        repository.update(actualizar);
    }

    public void delete(Eventos eliminar){
        repository.delete(eliminar);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
