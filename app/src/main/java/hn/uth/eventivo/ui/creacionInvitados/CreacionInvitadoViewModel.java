package hn.uth.eventivo.ui.creacionInvitados;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import hn.uth.eventivo.database.Invitados;
import hn.uth.eventivo.database.InvitadosRepository;

public class CreacionInvitadoViewModel extends AndroidViewModel {

    private InvitadosRepository repository;

    // Constructor de la clase
    public CreacionInvitadoViewModel(@NonNull Application app) {
        super(app);
        this.repository = new InvitadosRepository(app);
    }

    // Método para obtener el repositorio de invitados
    public InvitadosRepository getRepository() {
        return repository;
    }

    // Método para insertar un nuevo invitado
    public void insert(Invitados nuevo){
        repository.insert(nuevo);
    }

    // Método para actualizar los datos de un invitado existente
    public void update(Invitados actualizar){
        repository.update(actualizar);
    }

    // Método para eliminar un invitado
    public void delete(Invitados eliminar){
        repository.delete(eliminar);
    }

    // Método para eliminar todos los invitados
    public void deleteAll(){
        repository.deleteAll();
    }
}
