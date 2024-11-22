// Definición de la clase Alumno, que representa la entidad de la tabla "alumnos" en la base de datos
package com.example.ejerciciobbddnavigationfinal.BaseDatos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// La anotación @Entity indica que esta clase es una entidad de base de datos
// y que se almacenará en una tabla llamada "alumnos"
@Entity(tableName = "alumnos")
public class Alumno {

    // La anotación @PrimaryKey marca este campo como la clave primaria de la tabla
    // La anotación @NonNull asegura que el valor de "dni" no puede ser nulo
    // La anotación @ColumnInfo define el nombre del campo en la base de datos, en este caso "dni"
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "dni")
    private String dni;

    // Anotaciones similares para los demás campos de la clase, que corresponden a las columnas de la tabla
    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "sexo")
    private String sexo;

    // Constructor de la clase, que recibe los valores para los campos de la entidad
    public Alumno(String dni, String nombre, String apellidos, String sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    // Métodos getter para acceder a los valores de los campos
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getSexo() {
        return sexo;
    }
}
