package com.example.ejerciciobbddnavigationfinal.BaseDatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// La anotación @Dao indica que esta es una interfaz de acceso a datos (Data Access Object)
// y define las operaciones que se pueden realizar sobre la base de datos.
@Dao
public interface AlumnoDao {

    // La anotación @Insert define un método para insertar un objeto "Alumno" en la base de datos.
    // onConflict = OnConflictStrategy.IGNORE: Si se intenta insertar un registro con una clave primaria
    // que ya existe, se ignora la operación (no se sobreescribe el registro existente).
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertarAlumno(Alumno alumno);

    // La anotación @Query define una consulta SQL personalizada para modificar un registro en la tabla "alumnos".
    // Este método actualiza el nombre, apellidos y sexo del alumno cuyo "dni" coincida con el proporcionado.
    @Query("UPDATE alumnos SET nombre = :nombre, apellidos = :apellido, sexo = :sexo WHERE dni = :dni")
    int modificarAlumno(String nombre, String apellido, String sexo, String dni);

    // La anotación @Delete define un método para eliminar un alumno de la base de datos.
    // El método acepta un objeto Alumno y elimina el registro correspondiente.
    @Delete
    int borrarAlumno(Alumno alumno);

    // La anotación @Query define una consulta SQL personalizada para obtener todos los alumnos de la tabla.
    // Retorna una lista de objetos Alumno.
    @Query("SELECT * FROM alumnos")
    List<Alumno> listarAlumnos();

    // La anotación @Query define una consulta SQL personalizada para buscar un alumno por su "dni".
    // Si se encuentra un alumno con ese dni, se devuelve el objeto Alumno correspondiente.
    @Query("SELECT * FROM alumnos WHERE dni = :dni")
    Alumno buscarAlumnoPorDNI(String dni);
}

