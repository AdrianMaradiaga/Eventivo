package hn.uth.eventivo.ui.creacionEventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.FragmentCreacionEventoBinding;

public class CreacionEventoFragment extends Fragment {

    private FragmentCreacionEventoBinding binding;
    private CreacionEventoViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreacionEventoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(CreacionEventoViewModel.class);

        binding.btnGuardar.setOnClickListener(v -> {
            String tema = binding.tilTema.getText().toString().trim();
            String expositor = binding.tilExpositor.getText().toString().trim();
            String fecha = binding.dtFecha.getText().toString().trim();
            String detalle = binding.tilDetalle.getText().toString().trim();

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
            Eventos nuevo = new Eventos(tema, expositor, fecha, "", detalle);
            viewModel.insert(nuevo);

            binding.tilTema.getText().clear();
            binding.tilExpositor.getText().clear();
            binding.dtFecha.getText().clear();
            binding.bxEstado.setChecked(false);
            binding.tilDetalle.getText().clear();

            Navigation.findNavController(v).navigateUp();
        });

        return root;
    }


@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}