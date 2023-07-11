package hn.uth.eventivo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventosDao {

    // Insertar un nuevo evento en la tabla de eventos
    @Insert
    void insert(Eventos nuevo);

    // Actualizar los datos de un evento en la tabla de eventos
    @Update
    void update(Eventos actualizar);

    // Eliminar todos los eventos de la tabla de eventos
    @Query("DELETE FROM eventos_table")
    void deleteAll();

    // Eliminar un evento de la tabla de eventos
    @Delete
    void delete(Eventos eliminar);

    // Obtener todos los eventos de la tabla de eventos ordenados por expositor
    @Query("SELECT * FROM eventos_table ORDER BY expositor")
    LiveData<List<Eventos>> getEventos();

    // Buscar eventos por expositor en la tabla de eventos
    @Query("SELECT * FROM eventos_table WHERE expositor LIKE '%' || :expositor || '%'")
    LiveData<List<Eventos>> buscarPorExpositor(String expositor);

}
