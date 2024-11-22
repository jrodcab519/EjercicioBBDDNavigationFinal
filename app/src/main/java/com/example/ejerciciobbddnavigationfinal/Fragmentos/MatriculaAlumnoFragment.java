package com.example.ejerciciobbddnavigationfinal.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.ejerciciobbddnavigationfinal.BaseDatos.Alumno;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.AlumnoDao;
import com.example.ejerciciobbddnavigationfinal.BaseDatos.EscuelaDatabase;
import com.example.ejerciciobbddnavigationfinal.R;

// Fragmento que permite matricular a un nuevo alumno en la base de datos.
public class MatriculaAlumnoFragment extends Fragment {

    // Declaramos los componentes de la interfaz de usuario (EditText, Button, Spinner).
    private EditText etNombre, etApellido, etDNI;
    private Button btnInsertar, btnCancelar;
    private AlumnoDao alumnoDao;  // DAO para interactuar con la base de datos de alumnos
    private Spinner spinner;  // Spinner para seleccionar el sexo del alumno

    // Método que se llama cuando se crea la vista del fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflamos el layout del fragmento.
        View view = inflater.inflate(R.layout.fragment_matricular_alumnos2, container, false);

        // Inicializamos los componentes de la interfaz de usuario.
        etNombre = view.findViewById(R.id.editTextText2);
        etApellido = view.findViewById(R.id.editTextText3);
        etDNI = view.findViewById(R.id.editTextText);
        spinner = view.findViewById(R.id.spinner);
        btnInsertar = view.findViewById(R.id.button2);
        btnCancelar = view.findViewById(R.id.button);

        // Creamos la instancia de la base de datos usando Room y obtenemos el DAO de alumnos.
        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        alumnoDao = db.alumnoDao();

        // Configuramos el Spinner con las opciones de sexo (Hombre, Mujer).
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.sexo_array,  // Array de opciones de sexo (Hombre/Mujer) en el archivo de recursos
                android.R.layout.simple_spinner_item
        );
        // Establecemos el estilo para el desplegable del Spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Configuramos el botón para insertar un nuevo alumno.
        btnInsertar.setOnClickListener(v -> {
            // Obtenemos los valores ingresados por el usuario en los campos de texto y el spinner.
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();
            String dni = etDNI.getText().toString().trim();
            String sexo = spinner.getSelectedItem().toString();  // Obtenemos el valor seleccionado en el spinner

            // Verificamos que todos los campos estén completos antes de proceder.
            if (!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty()) {
                // Creamos un objeto Alumno con los datos proporcionados por el usuario.
                Alumno alumno = new Alumno(dni, nombre, apellido, sexo);

                // Insertamos el alumno en la base de datos en un hilo secundario para evitar bloquear la interfaz de usuario.
                new Thread(() -> {
                    alumnoDao.insertarAlumno(alumno);  // Llamamos al DAO para insertar el nuevo alumno
                    // Una vez insertado el alumno, mostramos un mensaje en la UI en el hilo principal.
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Alumno matriculado con éxito", Toast.LENGTH_SHORT).show());
                }).start();
            } else {
                // Si alguno de los campos está vacío, mostramos un mensaje de error.
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Configuramos el botón cancelar para que navegue de vuelta al fragmento de inicio.
        btnCancelar.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragmentInicio));

        return view;
    }
}

