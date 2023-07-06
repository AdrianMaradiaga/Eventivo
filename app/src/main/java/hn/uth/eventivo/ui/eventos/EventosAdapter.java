package hn.uth.eventivo.ui.eventos;

import android.view.LayoutInflater;
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

    public EventosAdapter(List<Eventos> dataset, OnItemClickListener<Eventos> manejadorEventoClick) {
        this.dataset = dataset;
        this.manejadorEventoClick = manejadorEventoClick;
    }

    @NonNull
    @Override
    public EventosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventoItemBinding binding = EventoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosAdapter.ViewHolder holder, int position) {
        Eventos eventoMostrar = dataset.get(position);
        holder.binding.txtExpositor.setText(eventoMostrar.getExpositor());
        holder.binding.txtTema.setText(eventoMostrar.getTema());
        holder.binding.txtFecha.setText(eventoMostrar.getFecha());
        holder.setOnClickListener(eventoMostrar, manejadorEventoClick);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Eventos> eventos){
        this.dataset = eventos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EventoItemBinding binding;
        public ViewHolder(@NonNull EventoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void setOnClickListener(Eventos eventoMostrar, OnItemClickListener<Eventos> listener) {
            this.binding.imgMore.setOnClickListener(v -> listener.onItemClick(eventoMostrar));
        }
    }
}
