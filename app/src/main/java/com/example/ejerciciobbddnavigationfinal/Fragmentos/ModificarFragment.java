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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ejerciciobbddnavigationfinal.BaseDatos.Alumno;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.AlumnoDao;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.EscuelaDatabase;
import com.example.ejerciciobbddnavigationfinal.R;
import com.example.ejerciciobbddnavigationfinal.databinding.FragmentModificarBinding;

// Fragmento que permite modificar los datos de un alumno en la base de datos.
public class ModificarFragment extends Fragment {

    private FragmentModificarBinding binding;

    // Constructor del fragmento.
    public ModificarFragment() {

    }

    // Método que se llama cuando se crea la vista del fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout del fragmento.
        binding = FragmentModificarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Este método se llama después de que la vista del fragmento se haya creado.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Creamos la instancia de la base de datos usando Room y obtenemos el DAO de alumnos.
        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        AlumnoDao alumnoDao = db.alumnoDao();

        // Configuramos el Spinner con las opciones de sexo (Hombre, Mujer).
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.sexo_array,  // Array de opciones de sexo (Hombre/Mujer) en el archivo de recursos
                android.R.layout.simple_spinner_item
        );
        // Establecemos el estilo para el desplegable del Spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner2.setAdapter(adapter);

        // Configuramos el botón para modificar los datos del alumno.
        binding.btnModificar.setOnClickListener(v -> {
            // Obtenemos los valores ingresados por el usuario en los campos de texto y el spinner.
            String nombre = binding.editTextText5.getText().toString().trim();
            String apellido = binding.editTextText6.getText().toString().trim();
            String sexo = binding.spinner2.getSelectedItem().toString();  // Obtenemos el valor seleccionado en el spinner
            String dni = binding.editTextText4.getText().toString().trim();

            // Verificamos que todos los campos estén completos antes de proceder.
            if (!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty()) {
                // En un hilo secundario, buscamos el alumno por su DNI.
                new Thread(() -> {
                    Alumno alumnoExistente = alumnoDao.buscarAlumnoPorDNI(dni);

                    // Si el alumno existe, se actualizan sus datos.
                    if (alumnoExistente != null) {
                        alumnoDao.modificarAlumno(nombre, apellido, sexo, dni);

                        // Una vez modificados los datos, mostramos un mensaje en la UI en el hilo principal.
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "Alumno modificado con éxito", Toast.LENGTH_SHORT).show());
                    } else {
                        // Si el alumno no existe, mostramos un mensaje de error.
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "El alumno no existe", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                // Si algún campo está vacío, mostramos un mensaje de error.
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Configuramos el botón para navegar de regreso al fragmento de inicio.
        binding.button3.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_borrarFragment_to_fragmentInicio));
    }
}
