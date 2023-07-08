package hn.uth.eventivo.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "eventos_table")
public class Eventos {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id_eventos;
    @NonNull
    @ColumnInfo(name = "expositor")
    private String expositor;
    @NonNull
    @ColumnInfo(name = "tema")
    private String tema;
    @NonNull
    @ColumnInfo(name = "fecha")
    private String fecha;
    @NonNull
    @ColumnInfo(name = "estado")
    private String estado;
    @NonNull
    @ColumnInfo(name = "detalle")
    private String detalle;

    public Eventos(@NonNull String tema, @NonNull String expositor, @NonNull String fecha, @NonNull String estado, @NonNull String detalle) {
        this.tema = tema;
        this.expositor = expositor;
        this.fecha = fecha;
        this.estado = estado;
        this.detalle = detalle;
    }

    public int getId_eventos() {
        return id_eventos;
    }

    public void setId_eventos(int id_eventos) {
        this.id_eventos = id_eventos;
    }

    @NonNull
    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(@NonNull String expositor) {
        this.expositor = expositor;
    }

    @NonNull
    public String getTema() {
        return tema;
    }

    public void setTema(@NonNull String tema) {
        this.tema = tema;
    }

    @NonNull
    public String getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull String fecha) {
        this.fecha = fecha;
    }

    @NonNull
    public String getEstado() {
        return estado;
    }

    public void setEstado(@NonNull String estado) {
        this.estado = estado;
    }

    @NonNull
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(@NonNull String detalle) {
        this.detalle = detalle;
    }
}
