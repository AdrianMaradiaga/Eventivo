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
    @Insert
    void insert(Invitados nuevo);
    @Update
    void update(Invitados actualizar);

    @Query("DELETE FROM invitados_table")
    void deleteAll();

    @Delete
    void delete(Invitados eliminar);

    @Query("SELECT * FROM invitados_table order by nombre")
    LiveData<List<Invitados>> getInvitados();

    //Buscar por expositor
    @Query("SELECT * FROM invitados_table WHERE nombre LIKE '%' || :nombre || '%'")
    LiveData<List<Invitados>> buscarPorInvitados(String nombre);
}
