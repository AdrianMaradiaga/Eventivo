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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import java.util.Calendar;
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.FragmentCreacionEventoBinding;

public class EdicionEventoFragment  extends Fragment {

    private FragmentCreacionEventoBinding binding;
    private CreacionEventoViewModel viewModel;
    private Eventos eventoExistente;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreacionEventoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                eventoExistente = bundle.getParcelable("evento");
                if (eventoExistente != null) {
                    binding.tilTema.setText(eventoExistente.getTema());
                    binding.tilExpositor.setText(eventoExistente.getExpositor());
                    binding.dtFecha.setText(eventoExistente.getFecha());
                    binding.bxEstado.setChecked(Boolean.parseBoolean(eventoExistente.getEstado()));
                    binding.tilDetalle.setText(eventoExistente.getDetalle());
                }
            }
        }

        viewModel = new ViewModelProvider(requireActivity()).get(CreacionEventoViewModel.class);


        binding.btnGuardar.setOnClickListener(v -> {
            String tema = binding.tilTema.getText().toString();
            String expositor = binding.tilExpositor.getText().toString();
            String fecha = binding.dtFecha.getText().toString();
            boolean estado = binding.bxEstado.isChecked();
            String detalle = binding.tilDetalle.getText().toString();

            if (eventoExistente != null) {
                // Actualizar los datos del evento existente con los valores ingresados
                eventoExistente.setTema(tema);
                eventoExistente.setExpositor(expositor);
                eventoExistente.setFecha(fecha);
                eventoExistente.setEstado(String.valueOf(estado));
                eventoExistente.setDetalle(detalle);

                // Llamar al método de actualización en el ViewModel
                viewModel.update(eventoExistente);
            }

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
        return root;
    }

    private void finish() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}