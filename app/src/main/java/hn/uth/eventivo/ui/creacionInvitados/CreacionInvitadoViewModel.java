package hn.uth.eventivo.ui.creacionInvitados;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import hn.uth.eventivo.database.Invitados;
import hn.uth.eventivo.database.InvitadosRepository;

public class CreacionInvitadoViewModel extends AndroidViewModel {

    private InvitadosRepository repository;
    public CreacionInvitadoViewModel(@NonNull Application app) {
        super(app);
        this.repository = new InvitadosRepository(app);
    }

    public InvitadosRepository getRepository() {
        return repository;
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
