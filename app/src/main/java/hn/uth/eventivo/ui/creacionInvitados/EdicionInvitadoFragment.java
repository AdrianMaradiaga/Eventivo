package hn.uth.eventivo.ui.creacionInvitados;

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

import hn.uth.eventivo.database.Invitados;
import hn.uth.eventivo.databinding.FragmentCreacionInvitadoBinding;

public class EdicionInvitadoFragment extends Fragment {

    private FragmentCreacionInvitadoBinding binding;
    private CreacionInvitadoViewModel viewModel;
    private Invitados invitadosExistente;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreacionInvitadoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtener el argumento pasado al fragmento
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                invitadosExistente = bundle.getParcelable("invitado");

                // Si hay un invitado existente, establecer los valores en los campos de edición
                if (invitadosExistente != null) {
                    binding.tilInvitado.setText(invitadosExistente.getNombre());
                    binding.tillCorreo.setText(invitadosExistente.getCorreo());
                    binding.dtFecha.setText(invitadosExistente.getFecha_registro());
                }
            }
        }

        viewModel = new ViewModelProvider(requireActivity()).get(CreacionInvitadoViewModel.class);

        // Configurar el botón de guardado
        binding.btnGuardado.setOnClickListener(v -> {
            String nombre = binding.tilInvitado.getText().toString();
            String correo = binding.tillCorreo.getText().toString();
            String fecha = binding.dtFecha.getText().toString();

            if (invitadosExistente != null) {
                // Actualizar los datos del invitado existente con los valores ingresados
                invitadosExistente.setNombre(nombre);
                invitadosExistente.setCorreo(correo);
                invitadosExistente.setFecha_registro(fecha);

                // Llamar al método de actualización en el ViewModel
                viewModel.update(invitadosExistente);
            }

            // Navegar hacia atrás en la navegación
            Navigation.findNavController(v).navigateUp();

            // Mostrar mensaje de éxito
            Toast.makeText(v.getContext(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

            finish();
        });

        // Configurar el botón para seleccionar fecha
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


