package hn.uth.eventivo.ui.creacionEventos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.database.EventosRepository;


public class CreacionEventoViewModel extends AndroidViewModel {



    private EventosRepository repository;
    public CreacionEventoViewModel(@NonNull Application app) {
        super(app);
        this.repository = new EventosRepository(app);
    }


    public EventosRepository getRepository() {
        return repository;
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