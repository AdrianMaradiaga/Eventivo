package hn.uth.eventivo.ui.invitados;

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
import hn.uth.eventivo.database.Invitados;
import hn.uth.eventivo.databinding.FragmentInvitadosBinding;

public class InvitadosFragment extends Fragment implements OnItemClickListener<Invitados> {

    private FragmentInvitadosBinding binding;
    private InvitadosAdapter adaptador;
    private InvitadosViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        binding = FragmentInvitadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtener la instancia del ViewModel
        viewModel = new ViewModelProvider(this).get(InvitadosViewModel.class);

        // Crear el adaptador y configurar el manejador de eventos de clic
        adaptador = new InvitadosAdapter(getContext(), new ArrayList<>(), this, viewModel);

        // Observar los cambios en el conjunto de datos de invitados y actualizar el adaptador
        viewModel.getInvitadosDataset().observe(getViewLifecycleOwner(), invitados -> adaptador.setItems(invitados));

        // Configurar el RecyclerView
        setupRecyclerView();

        // Configurar el listener de búsqueda en el SearchView
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                buscarPorInvitados(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                buscarPorInvitados(newText);
                return false;
            }
        });

        return root;
    }

    private void setupRecyclerView() {
        // Configurar el RecyclerView con un LinearLayoutManager y el adaptador
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvInvitados.setLayoutManager(linearLayoutManager);
        binding.rvInvitados.setAdapter(adaptador);
    }

    private void buscarPorInvitados(String nombre) {
        // Realizar la búsqueda por nombre y actualizar el adaptador con los resultados
        viewModel.buscarPorInvitados(nombre).observe(getViewLifecycleOwner(), invitados -> adaptador.setItems(invitados));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Invitados data) {
        // Acción al hacer clic en un invitado
    }

}
