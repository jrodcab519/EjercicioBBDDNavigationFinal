package com.example.ejerciciobbddnavigationfinal.Fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ejerciciobbddnavigationfinal.AlumnoAdapter;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.Alumno;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.AlumnoDao;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.EscuelaDatabase;
import com.example.ejerciciobbddnavigationfinal.R;
import com.example.ejerciciobbddnavigationfinal.databinding.FragmentListarBinding;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Fragmento que se encarga de mostrar la lista de alumnos almacenados en la base de datos.
public class ListarFragment extends Fragment {
    private FragmentListarBinding binding;  // Referencia al binding del layout del fragmento
    private static Executor executor = Executors.newSingleThreadExecutor();  // Executor para ejecutar tareas en un hilo secundario

    public ListarFragment() {
    }

    // Método llamado cuando el fragmento crea su vista. Infla el layout para este fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout asociado al fragmento (fragment_listar.xml) y lo devolvemos.
        binding = FragmentListarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Método llamado después de que la vista haya sido creada. Se utiliza para configurar la lógica del fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Creamos una instancia de la base de datos usando Room.
        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        AlumnoDao alumnoDao = db.alumnoDao();  // Obtenemos el DAO de los alumnos para acceder a los datos.

        // Usamos un Executor para ejecutar la consulta de alumnos en un hilo separado para no bloquear la interfaz de usuario.
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Obtenemos la lista de alumnos de la base de datos.
                List<Alumno> alumnoList = alumnoDao.listarAlumnos();

                // Una vez que tenemos los datos, actualizamos la interfaz de usuario en el hilo principal.
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Creamos un adaptador personalizado para mostrar la lista de alumnos en un ListView.
                        AlumnoAdapter alumnoAdapter = new AlumnoAdapter(requireContext(), R.layout.item_list, alumnoList);
                        binding.lista.setAdapter(alumnoAdapter);  // Establecemos el adaptador en el ListView.
                    }
                });
            }
        });
    }
}
