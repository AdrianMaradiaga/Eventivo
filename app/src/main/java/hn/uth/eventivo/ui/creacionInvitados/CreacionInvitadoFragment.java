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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Calendar;

import hn.uth.eventivo.database.Invitados;
import hn.uth.eventivo.databinding.FragmentCreacionInvitadoBinding;

public class CreacionInvitadoFragment extends Fragment {
    private CreacionInvitadoViewModel viewModel;
    private FragmentCreacionInvitadoBinding binding;

    private Invitados InvitadosExistente;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreacionInvitadoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle SavedInstanceState) {
        super.onViewCreated(view, SavedInstanceState);

        viewModel = new ViewModelProvider(this).get(CreacionInvitadoViewModel.class);

        binding.btnGuardado.setOnClickListener(v -> {
            String invitado = binding.tilInvitado.getText().toString().trim();
            String correo = binding.tillCorreo.getText().toString().trim();
            String fecha = binding.dtFecha.getText().toString().trim();



            if (invitado.isEmpty()) {
                Toast.makeText(getContext(), "Nombre del Invitado", Toast.LENGTH_SHORT).show();
                return;
            }

            if (correo.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese el correo", Toast.LENGTH_SHORT).show();
                return;
            }

            if (fecha.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese la fecha", Toast.LENGTH_SHORT).show();
                return;
            }

            // Realiza la acción de guardar el evento
            Invitados nuevo = new Invitados (invitado, correo, fecha);
            viewModel.insert(nuevo);

            binding.tilInvitado.getText().clear();
            binding.tillCorreo.getText().clear();
            binding.dtFecha.setText("");


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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
