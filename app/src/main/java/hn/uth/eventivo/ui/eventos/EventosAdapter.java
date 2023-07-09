package hn.uth.eventivo.ui.eventos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hn.uth.eventivo.OnItemClickListener;
import hn.uth.eventivo.database.Eventos;
import hn.uth.eventivo.databinding.EventoItemBinding;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {

    private List<Eventos> dataset;
    private OnItemClickListener<Eventos> manejadorEventoClick;
    private Context context;
    private EventosViewModel viewModel;


    public EventosAdapter(Context context, List<Eventos> dataset, OnItemClickListener<Eventos> manejadorEventoClick, EventosViewModel viewModel) {
        this.context = context;
        this.dataset = dataset;
        this.manejadorEventoClick = manejadorEventoClick;
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public EventosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventoItemBinding binding = EventoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosAdapter.ViewHolder holder, int position) {
        Eventos eventoItem = dataset.get(position);
        holder.binding.txtExpositor.setText(eventoItem.getExpositor());
        holder.binding.txtTema.setText(eventoItem.getTema());
        holder.binding.txtFecha.setText(eventoItem.getFecha());
        holder.bind(eventoItem, manejadorEventoClick);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(eventoItem);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Eventos> eventos){
        this.dataset = eventos;
        notifyDataSetChanged();
    }

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

    private void deleteEvento(Eventos evento) {
        // Implementa la lógica para eliminar el registro
        viewModel.delete(evento);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EventoItemBinding binding;

        public ViewHolder(@NonNull EventoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Eventos eventoMostrar, OnItemClickListener<Eventos> listener) {
            binding.imgMore.setOnClickListener(v -> listener.onItemClick(eventoMostrar));
        }
    }
}

