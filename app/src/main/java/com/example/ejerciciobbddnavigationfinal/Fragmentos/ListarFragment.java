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


public class ListarFragment extends Fragment {

  private FragmentListarBinding binding;

  private static Executor executor = Executors.newSingleThreadExecutor();

    public ListarFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EscuelaDatabase db = Room.databaseBuilder(requireActivity(), EscuelaDatabase.class, "escuela").build();
        AlumnoDao alumnoDao = db.alumnoDao();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Alumno> alumnoList = alumnoDao.listarAlumnos();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlumnoAdapter alumnoAdapter = new AlumnoAdapter(requireContext() , R.layout.item_list, alumnoList);
                        binding.lista.setAdapter(alumnoAdapter);
                    }
                });
            }
        });
    }
}