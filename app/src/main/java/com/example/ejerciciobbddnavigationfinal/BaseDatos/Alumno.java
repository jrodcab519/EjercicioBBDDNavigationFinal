package com.example.ejerciciobbddnavigationfinal.BaseDatos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alumnos")
public class Alumno {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "dni")
    private String dni;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "sexo")
    private String sexo;


    public Alumno(String dni, String nombre, String apellidos, String sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }


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
