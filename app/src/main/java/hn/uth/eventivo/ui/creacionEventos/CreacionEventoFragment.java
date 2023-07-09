package hn.uth.eventivo.ui.creacionEventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.FragmentCreacionEventoBinding;

public class CreacionEventoFragment extends Fragment {

    private FragmentCreacionEventoBinding binding;
    private CreacionEventoViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentCreacionEventoBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(CreacionEventoViewModel.class);

        binding.btnGuardar.setOnClickListener(v -> {
        Eventos nuevo = new Eventos(binding.tilTema.getText().toString(), binding.tilExpositor.getText().toString(),
                binding.dtFecha.getText().toString(),
                binding.bxEstado.getText().toString(),
                binding.tilDetalle.getText().toString());;

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