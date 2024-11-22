package com.example.ejerciciobbddnavigationfinal.BaseDatos;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlumnoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertarAlumno(Alumno alumno);

    @Query("UPDATE alumnos SET nombre = :nombre, apellidos = :apellido, sexo = :sexo WHERE dni = :dni" )
    int modificarAlumno(String nombre, String apellido, String sexo, String dni);


    @Delete
    int borrarAlumno(Alumno alumno);

    @Query("SELECT * FROM alumnos")
    List<Alumno> listarAlumnos();

    @Query("SELECT * FROM alumnos WHERE dni = :dni")
    Alumno buscarAlumnoPorDNI(String dni);
}
