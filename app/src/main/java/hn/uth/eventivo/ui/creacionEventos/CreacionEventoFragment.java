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

import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.FragmentCreacionEventoBinding;

public class CreacionEventoFragment extends Fragment {

    private FragmentCreacionEventoBinding binding;
    private CreacionEventoViewModel viewModel;
    private Eventos eventoExistente;
    private boolean estadoGuardado = false;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreacionEventoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CreacionEventoViewModel.class);

        if (savedInstanceState != null) {
            estadoGuardado = savedInstanceState.getBoolean("estadoGuardado");
            binding.bxEstado.setChecked(estadoGuardado);
        }

        binding.btnGuardar.setOnClickListener(v -> {
            String tema = binding.tilTema.getText().toString().trim();
            String expositor = binding.tilExpositor.getText().toString().trim();
            String fecha = binding.dtFecha.getText().toString().trim();
            String detalle = binding.tilDetalle.getText().toString().trim();
            String estado = binding.bxEstado.isChecked() ? "finalizado" : "no finalizado";

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

            // Realiza la acción de guardar el evento
            Eventos nuevo = new Eventos(tema, expositor, fecha, estado, detalle);
            viewModel.insert(nuevo);

            binding.tilTema.getText().clear();
            binding.tilExpositor.getText().clear();
            binding.dtFecha.setText("");
            binding.bxEstado.setChecked(false);
            binding.tilDetalle.getText().clear();

            Navigation.findNavController(v).navigateUp();
            Toast.makeText(v.getContext(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            finish();
        });

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

    private void finish() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("estadoGuardado", binding.bxEstado.isChecked());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
