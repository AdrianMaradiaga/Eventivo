package hn.uth.eventivo.ui.eventos;

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
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.EventoItemBinding;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {
    public ImageView imgMore;
    private List<Eventos> dataset;
    private OnItemClickListener<Eventos> manejadorEventoClick;
    private Context context;
    private EventosViewModel viewModel;
    private Eventos eventoSeleccionado;

    /**
     * Constructor de la clase EventosAdapter.
     * @param context El contexto de la aplicación.
     * @param dataset La lista de eventos a mostrar.
     * @param manejadorEventoClick El manejador de eventos de clic.
     * @param viewModel El ViewModel asociado a los eventos.
     */
    public EventosAdapter(Context context, List<Eventos> dataset, OnItemClickListener<Eventos> manejadorEventoClick, EventosViewModel viewModel) {
        this.context = context;
        this.dataset = dataset;
        this.manejadorEventoClick = manejadorEventoClick;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public EventosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño del elemento de lista
        EventoItemBinding binding = EventoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosAdapter.ViewHolder holder, int position) {
        // Obtener el evento en la posición actual
        Eventos eventoItem = dataset.get(position);

        // Establecer los valores del evento en los componentes de vista correspondientes
        holder.binding.txtExpositor.setText(eventoItem.getExpositor());
        holder.binding.txtTema.setText(eventoItem.getTema());
        holder.binding.txtFecha.setText(eventoItem.getFecha());

        // Establecer el evento de clic en el elemento de lista
        holder.setOnClickListener(eventoItem, manejadorEventoClick);

        // Establecer el evento de clic largo en el elemento de lista
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(eventoItem);
                return true;
            }
        });

        // Establecer el evento de clic en el botón de opciones adicionales
        holder.binding.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pasar el evento seleccionado al fragmento de edición
                Bundle bundle = new Bundle();
                bundle.putParcelable("evento", eventoItem);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_edicion_evento, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /**
     * Establece la lista de eventos a mostrar en el adaptador.
     * @param eventos La lista de eventos.
     */
    public void setItems(List<Eventos> eventos) {
        this.dataset = eventos;
        notifyDataSetChanged();
    }

    /**
     * Muestra un diálogo de confirmación antes de eliminar un evento.
     * @param evento El evento a eliminar.
     */
    private void showDialog(Eventos evento) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar registro");
        builder.setMessage("¿Estás seguro de que deseas eliminar este registro?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteEvento(evento);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    /**
     * Elimina un evento utilizando el ViewModel.
     * @param evento El evento a eliminar.
     */
    private void deleteEvento(Eventos evento) {
        viewModel.delete(evento);
    }

    /**
     * Clase ViewHolder que contiene los componentes de vista del elemento de lista.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        EventoItemBinding binding;

        /**
         * Constructor de la clase ViewHolder.
         * @param itemView La vista del elemento de lista.
         */
        public ViewHolder(@NonNull EventoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            imgMore = binding.imgMore;
        }

        /**
         * Establece el evento de clic en el botón de opciones adicionales.
         * @param eventoMostrar El evento a mostrar en el clic.
         * @param listener El manejador de eventos de clic.
         */
        public void setOnClickListener(Eventos eventoMostrar, OnItemClickListener<Eventos> listener) {
            binding.imgMore.setOnClickListener(v -> listener.onItemClick(eventoMostrar));
        }
    }
}


