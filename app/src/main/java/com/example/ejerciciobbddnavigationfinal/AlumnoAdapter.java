package com.example.ejerciciobbddnavigationfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejerciciobbddnavigationfinal.BaseDatos.Alumno;

import java.util.List;

public class AlumnoAdapter extends ArrayAdapter<Alumno> {

    private Context context;
    private int layout;
    private List<Alumno> alumnoList;

    public AlumnoAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.alumnoList = objects;
    }

    private static class ViewHolder {
        ImageView image;
        TextView dni;
        TextView nombre;
        TextView apellido;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder ;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout , parent, false);
            viewHolder = new ViewHolder();

            viewHolder.image = convertView.findViewById(R.id.imageView3);
            viewHolder.dni = convertView.findViewById(R.id.textView7);
            viewHolder.nombre = convertView.findViewById(R.id.textView8);
            viewHolder.apellido = convertView.findViewById(R.id.textView9);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Alumno alumno = alumnoList.get(position);

        viewHolder.dni.setText(alumno.getDni());
        viewHolder.nombre.setText(alumno.getNombre());
        viewHolder.apellido.setText(alumno.getApellidos());

        switch (alumno.getSexo()){
            case "Hombre":
            viewHolder.image.setImageResource(R.drawable.ic_hombre);
                break;

            case "Mujer":
                viewHolder.image.setImageResource(R.drawable.ic_mujer);
                break;

        }

        return convertView;
    }
}
