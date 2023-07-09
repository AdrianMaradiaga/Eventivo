package hn.uth.eventivo.ui.eventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentEventosBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    viewModel = new ViewModelProvider(this).get(EventosViewModel.class);

    adaptador = new EventosAdapter(new ArrayList<>(), this);

    viewModel.getEventosDataset().observe(getViewLifecycleOwner(), eventos -> adaptador.setItems(eventos));

        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        binding.rvEventos.setLayoutManager(linearLayoutManager);
        binding.rvEventos.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Eventos data) {
    }
}