package com.example.ejerciciobbddnavigationfinal.Fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ejerciciobbddnavigationfinal.BaseDatos.Alumno;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.AlumnoDao;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.EscuelaDatabase;
import com.example.ejerciciobbddnavigationfinal.R;
import com.example.ejerciciobbddnavigationfinal.databinding.FragmentBorrarBinding;

// Fragmento BorrarFragment se encarga de permitir borrar un alumno de la base de datos.
public class BorrarFragment extends Fragment {
    private FragmentBorrarBinding binding;  // Referencia al binding del layout del fragmento

    public BorrarFragment() {
    }

    // Método llamado cuando el fragmento se crea. Infla el layout del fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se infla el layout usando View Binding y se devuelve la vista raíz del fragmento.
        binding = FragmentBorrarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Método llamado cuando la vista del fragmento ya está creada.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Se crea la instancia de la base de datos utilizando Room.
        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        AlumnoDao alumnoDao = db.alumnoDao();  // Obtenemos el DAO de los alumnos para interactuar con la base de datos.

        // Se configura el evento de clic para el botón de borrar.
        binding.button4.setOnClickListener(v -> {
            // Obtenemos el DNI introducido por el usuario.
            String dni = binding.editTextText4.getText().toString().trim();

            // Verificamos que el DNI no esté vacío.
            if (!dni.isEmpty()) {
                // Realizamos la operación en un hilo separado para evitar bloquear la UI.
                new Thread(() -> {
                    // Buscamos el alumno por el DNI.
                    Alumno alumnoExistente = alumnoDao.buscarAlumnoPorDNI(dni);
                    // Si el alumno existe, lo borramos de la base de datos.
                    if (alumnoExistente != null) {
                        alumnoDao.borrarAlumno(alumnoExistente);
                        // Mostramos un mensaje de éxito en la UI utilizando runOnUiThread.
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "Alumno borrado con éxito", Toast.LENGTH_SHORT).show());
                    } else {
                        // Si el alumno no existe, mostramos un mensaje de error.
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "El alumno no existe", Toast.LENGTH_SHORT).show());
                    }
                }).start();  // Iniciamos el hilo.
            } else {
                // Si el campo de DNI está vacío, mostramos un mensaje pidiendo al usuario que ingrese el DNI.
                Toast.makeText(getContext(), "Por favor, introduce el dni", Toast.LENGTH_SHORT).show();
            }
        });

        // Configuramos el evento de clic para el botón de cancelar, que navega al fragmento de inicio.
        binding.button3.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_borrarFragment_to_fragmentInicio));
    }
}
