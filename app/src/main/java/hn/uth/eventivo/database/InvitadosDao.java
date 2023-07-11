package hn.uth.eventivo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface InvitadosDao {
    // Método de inserción para agregar un nuevo invitado a la base de datos
    @Insert
    void insert(Invitados nuevo);

    // Método de actualización para actualizar los datos de un invitado existente en la base de datos
    @Update
    void update(Invitados actualizar);

    // Método para eliminar todos los invitados de la tabla "invitados_table"
    @Query("DELETE FROM invitados_table")
    void deleteAll();

    // Método para eliminar un invitado específico de la base de datos
    @Delete
    void delete(Invitados eliminar);

    // Método de consulta para obtener todos los invitados de la tabla "invitados_table"
    // La lista de invitados se ordena por el nombre
    @Query("SELECT * FROM invitados_table order by nombre")
    LiveData<List<Invitados>> getInvitados();

    // Método de consulta para buscar invitados que coincidan con un nombre específico
    // Utiliza la cláusula LIKE en la consulta para realizar una búsqueda parcial
    @Query("SELECT * FROM invitados_table WHERE nombre LIKE '%' || :nombre || '%'")
    LiveData<List<Invitados>> buscarPorInvitados(String nombre);
}

