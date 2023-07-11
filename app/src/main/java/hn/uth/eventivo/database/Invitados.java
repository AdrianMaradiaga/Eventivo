package hn.uth.eventivo.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invitados_table") // Indica que esta clase es una entidad de la base de datos y se almacenará en la tabla "invitados_table"
public class Invitados implements Parcelable {

    @PrimaryKey(autoGenerate = true) // Marca el campo como clave primaria y se generará automáticamente un valor único para cada fila
    @NonNull
    @ColumnInfo(name = "id_invitados") // Nombre de la columna en la tabla
    private int id_invitados;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre; // Nombre del invitado

    @NonNull
    @ColumnInfo(name = "correo")
    private String correo; // Correo del invitado

    @NonNull
    @ColumnInfo(name = "fecha_registro")
    private String fecha_registro; // Fecha de registro del invitado

    public Invitados(@NonNull String nombre, @NonNull String correo, @NonNull String fecha_registro) {
        this.nombre = nombre;
        this.correo = correo;
        this.fecha_registro = fecha_registro;
    }

    // Constructor protected utilizado para crear un objeto Invitados a partir de un objeto Parcel
    // Lee los valores del Parcel y los asigna a las variables de la clase
    protected Invitados(Parcel in) {
        this.id_invitados = in.readInt();
        this.nombre = in.readString();
        this.correo = in.readString();
        this.fecha_registro = in.readString();
    }

    // Implementación de Parcelable.Creator para permitir la creación de objetos Invitados a partir de un Parcel
    // También proporciona un arreglo de objetos Invitados con el tamaño especificado
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

    // Método que escribe los valores de las variables en el Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_invitados);
        dest.writeString(nombre);
        dest.writeString(correo);
        dest.writeString(fecha_registro);
    }

    // Método que describe los tipos de objetos especiales contenidos en el Parcelable
    // En este caso, no se utilizan objetos especiales, por lo que devuelve 0
    @Override
    public int describeContents() {
        return 0;
    }

    // Métodos getter y setter para acceder y modificar los campos de la entidad
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
