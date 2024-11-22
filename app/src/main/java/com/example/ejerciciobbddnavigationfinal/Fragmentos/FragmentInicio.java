package com.example.ejerciciobbddnavigationfinal.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ejerciciobbddnavigationfinal.R;

// Fragmento FragmentInicio es el fragmento principal que se muestra al iniciar la aplicación.
public class FragmentInicio extends Fragment {

    // Constructor vacío, típico de los fragmentos en Android.
    public FragmentInicio() {

    }

    // Método llamado cuando el fragmento debe crear su vista. Inflamos el layout para este fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout asociado al fragmento (fragment_inicio.xml) y lo devolvemos.
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }
}
