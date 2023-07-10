package hn.uth.eventivo.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invitados_table")
public class Invitados implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_invitados")
    private int id_invitados;
    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;
    @NonNull
    @ColumnInfo(name = "correo")
    private String correo;
    @NonNull
    @ColumnInfo(name = "fecha_registro")
    private String fecha_registro;

    public Invitados(@NonNull String nombre, @NonNull String correo, @NonNull String fecha_registro) {
        this.nombre = nombre;
        this.correo = correo;
        this.fecha_registro = fecha_registro;
    }

    protected Invitados(Parcel in){
        this.id_invitados = in.readInt();
        this.nombre = in.readString();
        this.correo = in.readString();
        this.fecha_registro = in.readString();
    }

    public static final Parcelable.Creator<Invitados> CREATOR = new Parcelable.Creator<Invitados>() {
        @Override
        public Invitados createFromParcel(Parcel in) {
            return new Invitados(in);
        }

        @Override
        public Invitados[] newArray(int size) {
            return new Invitados[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_invitados);
        dest.writeString(nombre);
        dest.writeString(correo);
        dest.writeString(fecha_registro);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId_invitados() {
        return id_invitados;
    }

    public void setId_invitados(int id_invitados) {
        this.id_invitados = id_invitados;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(@NonNull String correo) {
        this.correo = correo;
    }

    @NonNull
    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(@NonNull String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
