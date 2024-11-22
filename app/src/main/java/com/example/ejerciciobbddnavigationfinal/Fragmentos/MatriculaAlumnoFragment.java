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


public class MatriculaAlumnoFragment extends Fragment {


    private EditText etNombre, etApellido, etDNI;
    private Button btnInsertar, btnCancelar;
    private AlumnoDao alumnoDao;
    private Spinner spinner;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matricular_alumnos2, container, false);


        etNombre = view.findViewById(R.id.editTextText2);
        etApellido = view.findViewById(R.id.editTextText3);
        etDNI = view.findViewById(R.id.editTextText);
        spinner = view.findViewById(R.id.spinner);
        btnInsertar = view.findViewById(R.id.button2);
        btnCancelar = view.findViewById(R.id.button);


        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        alumnoDao = db.alumnoDao();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.sexo_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        btnInsertar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();
            String dni = etDNI.getText().toString().trim();
            String sexo = spinner.getSelectedItem().toString();

            if (!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty()) {
                Alumno alumno = new Alumno(dni, nombre, apellido, sexo);


                new Thread(() -> {
                    alumnoDao.insertarAlumno(alumno);
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Alumno matriculado con éxito", Toast.LENGTH_SHORT).show());
                }).start();
            } else {
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });


        btnCancelar.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragmentInicio));

        return view;
    }
}

