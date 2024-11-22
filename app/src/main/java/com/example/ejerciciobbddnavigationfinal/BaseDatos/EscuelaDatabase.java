package com.example.ejerciciobbddnavigationfinal.BaseDatos;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// La anotación @Database define una base de datos de Room.
// La entidad que se incluye es Alumno.class y se establece la versión de la base de datos como 1.
@Database(entities = {Alumno.class}, version = 1)
public abstract class EscuelaDatabase extends RoomDatabase {

    // Método abstracto que devuelve el DAO (Data Access Object) de alumnos.
    // El DAO es utilizado para acceder a la base de datos y realizar operaciones CRUD.
    public abstract AlumnoDao alumnoDao();  // Obtener el DAO (Data Access Object) de alumnos

}
