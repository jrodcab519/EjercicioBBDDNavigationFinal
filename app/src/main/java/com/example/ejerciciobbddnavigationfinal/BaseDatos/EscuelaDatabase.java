package com.example.ejerciciobbddnavigationfinal.BaseDatos;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Alumno.class}, version = 1)
public abstract class EscuelaDatabase extends RoomDatabase {



    public abstract AlumnoDao alumnoDao();  // Obtener el DAO (Data Access Object) de alumnos


}

