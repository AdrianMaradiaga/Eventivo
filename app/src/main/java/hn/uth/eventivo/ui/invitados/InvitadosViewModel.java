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

        // Inicializar el repositorio de invitados
        this.repository = new InvitadosRepository(app);

        // Obtener el conjunto de datos de invitados desde el repositorio
        this.invitadosDataset = repository.getDataset();
    }

    public InvitadosRepository getRepository(){
        return repository;
    }

    public LiveData<List<Invitados>> getInvitadosDataset(){
        return invitadosDataset;
    }

    public LiveData<List<Invitados>> buscarPorInvitados(String nombre){
        // Realizar la búsqueda por nombre en el repositorio y obtener los resultados
        return repository.buscarPorInvitados(nombre);
    }

    public void insert(Invitados nuevo){
        // Insertar un nuevo invitado en el repositorio
        repository.insert(nuevo);
    }

    public void update(Invitados actualizar){
        // Actualizar los datos de un invitado en el repositorio
        repository.update(actualizar);
    }

    public void delete(Invitados eliminar){
        // Eliminar un invitado del repositorio
        repository.delete(eliminar);
    }

    public void deleteAll(){
        // Eliminar todos los invitados del repositorio
        repository.deleteAll();
    }
}
