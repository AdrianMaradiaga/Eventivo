package hn.uth.eventivo.ui.eventos;

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
    private Eventos eventoSeleccionado;


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

        holder.binding.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("evento", eventoMostrar);


                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_edicion_evento, bundle);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        EventoItemBinding binding;
        public ViewHolder(@NonNull EventoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            imgMore = binding.imgMore;
        }

        public void setOnClickListener(Eventos eventoMostrar, OnItemClickListener<Eventos> listener) {
            this.binding.imgMore.setOnClickListener(v -> listener.onItemClick(eventoMostrar));
        }

    }
}
