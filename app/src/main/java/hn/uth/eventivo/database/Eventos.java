package hn.uth.eventivo.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Clase que representa un evento.
 * Esta clase está anotada con la etiqueta @Entity, lo que la convierte en una entidad de la base de datos.
 * Cada instancia de Eventos representa una fila en la tabla "eventos_table".
 */
@Entity(tableName = "eventos_table")
public class Eventos implements Parcelable {

    /**
     * Identificador único del evento.
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id_eventos;

    /**
     * Nombre del expositor del evento.
     */
    @NonNull
    @ColumnInfo(name = "expositor")
    private String expositor;

    /**
     * Tema del evento.
     */
    @NonNull
    @ColumnInfo(name = "tema")
    private String tema;

    /**
     * Fecha del evento.
     */
    @NonNull
    @ColumnInfo(name = "fecha")
    private String fecha;

    /**
     * Estado del evento (no finalizado, finalizado).
     */
    @NonNull
    @ColumnInfo(name = "estado")
    private String estado;

    /**
     * Detalle o descripción del evento.
     */
    @NonNull
    @ColumnInfo(name = "detalle")
    private String detalle;

    /**
     * Constructor de la clase Eventos.
     * @param tema Tema del evento.
     * @param expositor Nombre del expositor del evento.
     * @param fecha Fecha del evento.
     * @param estado Estado del evento.
     * @param detalle Detalle o descripción del evento.
     */
    public Eventos(@NonNull String tema, @NonNull String expositor, @NonNull String fecha, @NonNull String estado, @NonNull String detalle) {
        this.tema = tema;
        this.expositor = expositor;
        this.fecha = fecha;
        this.estado = estado;
        this.detalle = detalle;
    }

    /**
     * Constructor de la clase Eventos para el proceso de Parcelable.
     * @param in Objeto Parcel.
     */
    protected Eventos(Parcel in) {
        id_eventos = in.readInt();
        expositor = in.readString();
        tema = in.readString();
        fecha = in.readString();
        estado = in.readString();
        detalle = in.readString();
    }

    /**
     * Creador del Parcelable para la clase Eventos.
     */
    public static final Creator<Eventos> CREATOR = new Creator<Eventos>() {
        @Override
        public Eventos createFromParcel(Parcel in) {
            return new Eventos(in);
        }

        @Override
        public Eventos[] newArray(int size) {
            return new Eventos[size];
        }
    };

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

    /**
     * Método necesario para el proceso de Parcelable.
     * @param dest Objeto Parcel de destino.
     * @param flags Flags adicionales (no utilizados en este caso).
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_eventos);
        dest.writeString(expositor);
        dest.writeString(tema);
        dest.writeString(fecha);
        dest.writeString(estado);
        dest.writeString(detalle);
    }

    /**
     * Método necesario para el proceso de Parcelable.
     * @return Valor 0, no utilizado en este caso.
     */
    @Override
    public int describeContents() {
        return 0;
    }
}

