package hn.uth.eventivo.ui.eventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import hn.uth.eventivo.OnItemClickListener;
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.FragmentEventosBinding;

public class EventosFragment extends Fragment implements OnItemClickListener<Eventos> {

    private FragmentEventosBinding binding;
    private EventosAdapter adaptador;
    private EventosViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEventosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(EventosViewModel.class);

        adaptador = new EventosAdapter(getContext(), new ArrayList<>(), this, viewModel);

        viewModel.getEventosDataset().observe(getViewLifecycleOwner(), eventos -> adaptador.setItems(eventos));

        setupRecyclerView();

        // Configurar el SearchView
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                buscarEventosPorExpositor(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                buscarEventosPorExpositor(newText);
                return false;
            }
        });

        return root;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvEventos.setLayoutManager(linearLayoutManager);
        binding.rvEventos.setAdapter(adaptador);
    }

    private void buscarEventosPorExpositor(String expositor) {
        viewModel.buscarPorExpositor(expositor).observe(getViewLifecycleOwner(), eventos -> adaptador.setItems(eventos));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Eventos data) {
        // Acción al hacer clic en un evento
    }
}
