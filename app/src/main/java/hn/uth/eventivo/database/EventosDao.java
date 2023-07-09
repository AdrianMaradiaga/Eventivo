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

    @Insert
    void insert(Eventos nuevo);
    @Update
    void update(Eventos actualizar);

    @Query("DELETE FROM eventos_table")
    void deleteAll();

    @Delete
    void delete(Eventos eliminar);

    @Query("SELECT * FROM eventos_table order by expositor")
    LiveData<List<Eventos>> getEventos();

    //Buscar por expositor
    @Query("SELECT * FROM eventos_table WHERE expositor LIKE '%' || :expositor || '%'")
    LiveData<List<Eventos>> buscarPorExpositor(String expositor);

}
