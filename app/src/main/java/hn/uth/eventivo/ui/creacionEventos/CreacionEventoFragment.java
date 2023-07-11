package hn.uth.eventivo.ui.creacionEventos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Calendar;

import hn.uth.eventivo.R;
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.FragmentCreacionEventoBinding;

public class CreacionEventoFragment extends Fragment {

    private FragmentCreacionEventoBinding binding;
    private CreacionEventoViewModel viewModel;
    private Eventos eventoExistente;
    private boolean estadoGuardado = false;


    /**
     * Método llamado al crear la vista del fragmento.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreacionEventoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método llamado después de que la vista haya sido creada.
     * Aquí se inicializan los elementos de la interfaz de usuario y se configuran los listeners.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CreacionEventoViewModel.class);

        // Restaurar el estado guardado, si existe
        if (savedInstanceState != null) {
            estadoGuardado = savedInstanceState.getBoolean("estadoGuardado");
            binding.bxEstado.setChecked(estadoGuardado);
        }

        // Configurar el botón de guardar
        binding.btnGuardar.setOnClickListener(v -> {
            String tema = binding.tilTema.getText().toString().trim();
            String expositor = binding.tilExpositor.getText().toString().trim();
            String fecha = binding.dtFecha.getText().toString().trim();
            String detalle = binding.tilDetalle.getText().toString().trim();
            String estado = binding.bxEstado.isChecked() ? "finalizado" : "no finalizado";

            // Validar campos obligatorios
            if (tema.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese el tema", Toast.LENGTH_SHORT).show();
                return;
            }

            if (expositor.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese el expositor", Toast.LENGTH_SHORT).show();
                return;
            }

            if (fecha.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese la fecha", Toast.LENGTH_SHORT).show();
                return;
            }

            if (detalle.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese el detalle", Toast.LENGTH_SHORT).show();
                return;
            }

            // Realizar la acción de guardar el evento
            Eventos nuevo = new Eventos(tema, expositor, fecha, estado, detalle);
            viewModel.insert(nuevo);

            // Limpiar los campos de entrada
            binding.tilTema.getText().clear();
            binding.tilExpositor.getText().clear();
            binding.dtFecha.setText("");
            binding.bxEstado.setChecked(false);
            binding.tilDetalle.getText().clear();

            // Navegar hacia arriba en la navegación
            Navigation.findNavController(v).navigate(R.id.nav_eventos);

            // Mostrar un mensaje de éxito
            Toast.makeText(v.getContext(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

            // Llamar al método finish personalizado
            finish();
        });

        // Configurar el botón de selección de fecha
        binding.btnFecha.setOnClickListener(v -> {
            // Obtener fecha actual
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            // Crear DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                    TextView dtFechas = binding.dtFecha;
                    dtFechas.setText(fechaSeleccionada);
                }
            }, year, month, dayOfMonth);

            // Mostrar el DatePickerDialog
            datePickerDialog.show();
        });

    }

    /**
     * Método personalizado para finalizar la acción.
     * Este método puede ser reemplazado según sea necesario.
     */
    private void finish() {
        // Lógica para finalizar la acción (opcional)
    }

    /**
     * Método llamado antes de destruir la vista del fragmento.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

