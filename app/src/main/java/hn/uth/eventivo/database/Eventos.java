package hn.uth.eventivo.database;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "eventos_table")
public class Eventos implements Parcelable {
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

    protected Eventos(Parcel in) {
        id_eventos = in.readInt();
        expositor = in.readString();
        tema = in.readString();
        fecha = in.readString();
        estado = in.readString();
        detalle = in.readString();
    }

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_eventos);
        dest.writeString(expositor);
        dest.writeString(tema);
        dest.writeString(fecha);
        dest.writeString(estado);
        dest.writeString(detalle);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
