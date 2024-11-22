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

// La clase AlumnoAdapter extiende ArrayAdapter<Alumno> y es responsable de adaptar
// los datos de la lista de alumnos para ser mostrados en una vista (como un ListView o GridView).
public class AlumnoAdapter extends ArrayAdapter<Alumno> {

    private Context context;  // Contexto de la aplicación, usado para inflar vistas
    private int layout;       // Layout que se utilizará para cada elemento de la lista
    private List<Alumno> alumnoList; // Lista de objetos Alumno que se mostrarán

    // Constructor del adaptador, recibe el contexto, el recurso del layout y la lista de objetos Alumno
    public AlumnoAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.alumnoList = objects;
    }

    // Clase interna ViewHolder, que mantiene referencias a las vistas para mejorar el rendimiento
    // al evitar llamadas repetidas a findViewById().
    private static class ViewHolder {
        ImageView image;      // Imagen del género del alumno
        TextView dni;         // DNI del alumno
        TextView nombre;      // Nombre del alumno
        TextView apellido;    // Apellidos del alumno
    }

    // El método getView es llamado para obtener una vista que representará cada elemento de la lista.
    // Este método se llama cada vez que un elemento debe ser visualizado en la interfaz.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Declaración del ViewHolder para almacenar las vistas de cada ítem
        ViewHolder viewHolder;

        // Si convertView es nulo, significa que esta vista no se ha reutilizado,
        // por lo que inflamos el layout y creamos un nuevo ViewHolder.
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            viewHolder = new ViewHolder();

            // Asociamos cada vista del layout con el ViewHolder
            viewHolder.image = convertView.findViewById(R.id.imageView3);
            viewHolder.dni = convertView.findViewById(R.id.textView7);
            viewHolder.nombre = convertView.findViewById(R.id.textView8);
            viewHolder.apellido = convertView.findViewById(R.id.textView9);

            // Guardamos el ViewHolder en la vista para reutilizarlo
            convertView.setTag(viewHolder);

        } else {
            // Si convertView no es nulo, reutilizamos el ViewHolder almacenado en el tag de la vista
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtenemos el objeto Alumno correspondiente a la posición en la lista
        Alumno alumno = alumnoList.get(position);

        // Asignamos los datos del alumno a las vistas correspondientes
        viewHolder.dni.setText(alumno.getDni());
        viewHolder.nombre.setText(alumno.getNombre());
        viewHolder.apellido.setText(alumno.getApellidos());

        // Según el sexo del alumno, se asigna una imagen diferente
        switch (alumno.getSexo()) {
            case "Hombre":
                viewHolder.image.setImageResource(R.drawable.ic_hombre);  // Imagen para hombres
                break;

            case "Mujer":
                viewHolder.image.setImageResource(R.drawable.ic_mujer);   // Imagen para mujeres
                break;
        }

        // Devolvemos la vista para ser mostrada en el ListView o GridView
        return convertView;
    }
}
