package com.example.ejerciciobbddnavigationfinal;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ejerciciobbddnavigationfinal.databinding.ActivityMainBinding;

// MainActivity es la actividad principal de la aplicación que maneja la interfaz de usuario
// y la configuración de la navegación mediante el Drawer Layout y el Navigation Component.
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;  // Configuración de la barra de herramientas y la navegación
    private ActivityMainBinding binding;  // Enlace a la vista generada automáticamente (View Binding)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflamos el layout de la actividad mediante View Binding, que es una forma más segura y eficiente
        // de acceder a las vistas del layout.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Establecemos la toolbar como la barra de acción principal de la actividad.
        setSupportActionBar(binding.appBarMain.toolbar);

        // Obtenemos el DrawerLayout y el NavigationView de la interfaz de usuario.
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Configuramos la AppBarConfiguration para manejar los destinos de navegación.
        // Estos son los fragmentos que pueden ser accedidos mediante el menú lateral (Drawer).
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fragmentInicio, R.id.listarFragment, R.id.borrarFragment, R.id.matriculaAlumnoFragment, R.id.modificarFragment)
                .setOpenableLayout(drawer)  // Indicamos el DrawerLayout que se utilizará para abrir el menú lateral.
                .build();

        // Configuramos el NavController para manejar la navegación entre los fragmentos.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Configuramos la ActionBar para que sea compatible con la navegación y muestre el ícono del Drawer.
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // Vinculamos el NavigationView con el NavController para que la navegación se controle desde el menú lateral.
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    // Este método se llama cuando el usuario interactúa con la barra de navegación en la parte superior (ActionBar).
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Navega hacia atrás en la pila de navegación o llama al método base si no es posible.
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
