package hn.uth.eventivo.ui.invitados;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import hn.uth.eventivo.OnItemClickListener;
import hn.uth.eventivo.R;
import hn.uth.eventivo.database.Invitados;
import hn.uth.eventivo.databinding.InvitadoItemBinding;

public class InvitadosAdapter extends RecyclerView.Adapter<InvitadosAdapter.ViewHolder> {

    public ImageView imgMore;

    private List<Invitados> dataset;
    private OnItemClickListener<Invitados> manejadorEventoClick;
    private Context context;
    private InvitadosViewModel viewModel;
    private Invitados invitadoSeleccionado;

    public InvitadosAdapter(Context context, List<Invitados> dataset, OnItemClickListener<Invitados> manejadorEventoClick, InvitadosViewModel viewModel) {
        this.context = context;
        this.dataset = dataset;
        this.manejadorEventoClick = manejadorEventoClick;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public InvitadosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño del elemento de invitado
        InvitadoItemBinding binding = InvitadoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InvitadosAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InvitadosAdapter.ViewHolder holder, int position) {
        // Obtener el objeto invitado correspondiente a la posición actual
        Invitados invitadoItem = dataset.get(position);

        // Establecer el nombre y la fecha de registro en las vistas correspondientes
        holder.binding.txtInvitado.setText(invitadoItem.getNombre());
        holder.binding.txtFechaRegistro.setText(invitadoItem.getFecha_registro());

        // Configurar el evento de clic en el elemento de invitado
        holder.setOnClickListener(invitadoItem, manejadorEventoClick);

        // Configurar el evento de clic largo en el elemento de invitado para mostrar el diálogo de eliminación
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(invitadoItem);
                return true;
            }
        });

        // Configurar el evento de clic en el botón "Más" para navegar a la pantalla de edición de invitado
        holder.binding.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un paquete Bundle para pasar el objeto invitado a la pantalla de edición
                Bundle bundle = new Bundle();
                bundle.putParcelable("invitado", invitadoItem);

                // Obtener el NavController y navegar a la pantalla de edición de invitado
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_edicion_invitado, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Invitados> invitados){
        this.dataset = invitados;
        notifyDataSetChanged();
    }

    private void showDialog(Invitados invitado) {
        // Crear el diálogo de confirmación de eliminación
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar registro");
        builder.setMessage("¿Estás seguro de que deseas eliminar este registro?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteInvitado(invitado);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void deleteInvitado(Invitados invitado) {
        // Llamar al método de eliminación en el ViewModel
        viewModel.delete(invitado);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        InvitadoItemBinding binding;

        public ViewHolder(@NonNull InvitadoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            imgMore = binding.imgMore;
        }

        public void setOnClickListener(Invitados invitadoMostrar, OnItemClickListener<Invitados> listener) {
            // Configurar el evento de clic en el botón "Más" para invocar el manejador de eventos externo
            binding.imgMore.setOnClickListener(v -> listener.onItemClick(invitadoMostrar));
        }
    }
}
