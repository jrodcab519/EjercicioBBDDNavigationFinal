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



public class BorrarFragment extends Fragment {

    private FragmentBorrarBinding binding;

    public BorrarFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBorrarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        AlumnoDao alumnoDao = db.alumnoDao();

        binding.button4.setOnClickListener(v -> {
            String dni = binding.editTextText4.getText().toString().trim();
            if (!dni.isEmpty()) {
                new Thread(() -> {
                    Alumno alumnoExistente = alumnoDao.buscarAlumnoPorDNI(dni);

                    if (alumnoExistente != null) {
                        alumnoDao.borrarAlumno(alumnoExistente);

                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "Alumno borrado con éxito", Toast.LENGTH_SHORT).show());
                    } else {
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "El alumno no existe", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                Toast.makeText(getContext(), "Por favor, introduce el dni", Toast.LENGTH_SHORT).show();
            }
        });

        binding.button3.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_borrarFragment_to_fragmentInicio));
    }
}
