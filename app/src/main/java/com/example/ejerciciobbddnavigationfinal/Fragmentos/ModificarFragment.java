package com.example.ejerciciobbddnavigationfinal.Fragmentos;

import android.annotation.SuppressLint;
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


public class ModificarFragment extends Fragment {

    private FragmentModificarBinding binding;


    public ModificarFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModificarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        AlumnoDao alumnoDao= db.alumnoDao();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.sexo_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner2.setAdapter(adapter);

        binding.btnModificar.setOnClickListener(v -> {
            String nombre = binding.editTextText5.getText().toString().trim();
            String apellido = binding.editTextText6.getText().toString().trim();
            String sexo = binding.spinner2.getSelectedItem().toString();
            String dni = binding.editTextText4.getText().toString().trim();

            if (!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty()) {
                new Thread(() -> {
                    Alumno alumnoExistente = alumnoDao.buscarAlumnoPorDNI(dni);

                    if (alumnoExistente != null) {
                        alumnoDao.modificarAlumno(nombre, apellido,sexo, dni);

                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "Alumno modificado con éxito", Toast.LENGTH_SHORT).show());
                    } else {
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "El alumno no existe", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });


       binding.button3.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_borrarFragment_to_fragmentInicio));
    }
}


