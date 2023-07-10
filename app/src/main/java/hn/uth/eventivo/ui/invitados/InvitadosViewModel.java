package hn.uth.eventivo.ui.invitados;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import hn.uth.eventivo.database.Invitados;
import hn.uth.eventivo.database.InvitadosRepository;

public class InvitadosViewModel extends AndroidViewModel {

    private InvitadosRepository repository;
    private final LiveData<List<Invitados>> invitadosDataset;


    public InvitadosViewModel(@NonNull Application app) {
        super(app);
        this.repository = new InvitadosRepository(app);
        this.invitadosDataset = repository.getDataset();
    }

    public InvitadosRepository getRepository(){
        return repository;
    }

    public LiveData<List<Invitados>> getInvitadosDataset(){
        return invitadosDataset;
    }

    public LiveData<List<Invitados>> buscarPorInvitados(String nombre){
        return repository.buscarPorInvitados(nombre);
    }

    public void insert(Invitados nuevo){
        repository.insert(nuevo);
    }

    public void update(Invitados actualizar){
        repository.update(actualizar);
    }

    public void delete(Invitados eliminar){
        repository.delete(eliminar);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}