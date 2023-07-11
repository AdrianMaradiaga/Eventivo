package hn.uth.eventivo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import hn.uth.eventivo.database.EventosRepository;
import hn.uth.eventivo.database.InvitadosRepository;
import hn.uth.eventivo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private View creacionEventoView;
    private EventosRepository eventosRepository;
    private InvitadosRepository invitadosRepository;

    private AppBarConfiguration mAppBarConfiguration;
    @NonNull

    ActivityMainBinding binding;
    private ImageView imgMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

        eventosRepository = new EventosRepository(this.getApplication());
         invitadosRepository = new InvitadosRepository(this.getApplication());

         binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_eventos, R.id.nav_invitados, R.id.nav_creacion_evento, R.id.nav_creacion_invitado)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            eventosRepository.deleteAll();
            Toast.makeText(getApplicationContext(), "Los eventos han sido borrados", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_delete_invitados) {
            invitadosRepository.deleteAll();
            Toast.makeText(getApplicationContext(), "Los invitados han sido borrados", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}