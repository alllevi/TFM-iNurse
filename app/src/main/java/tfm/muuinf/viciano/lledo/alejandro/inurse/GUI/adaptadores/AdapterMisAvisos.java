package tfm.muuinf.viciano.lledo.alejandro.inurse.GUI.adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.DTO.AvisosDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

/**
 * Created by Alex on 21/01/2017.
 */

public class AdapterMisAvisos extends RecyclerView.Adapter<AdapterMisAvisos.MisAvisosViewHolder> {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    Context context;
    List<AvisosDTO> listaAvisosDTO = new ArrayList<>();

    public AdapterMisAvisos(List<AvisosDTO> listaAvisosDTO, Context context) {
        this.listaAvisosDTO = listaAvisosDTO;
        this.context = context;
    }

    @Override

    public MisAvisosViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mis_avisos, parent, false);
        final MisAvisosViewHolder vh = new MisAvisosViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MisAvisosViewHolder holder, final int position) {
        holder.tvFechaIni.setText(sdf.format(listaAvisosDTO.get(position).getFechaInicio()));
        holder.tvFechaFin.setText(sdf.format(listaAvisosDTO.get(position).getFechaFin()));
        holder.tvDescripcion.setText(listaAvisosDTO.get(position).getDescripcion());

        Integer horasRepeticion = listaAvisosDTO.get(position).getHorasRepeticion();
        if (horasRepeticion > 0) {
            String mensajeRepeticion = context.getResources().getString(R.string.repetir_aviso_horas);
            holder.tvFrecuencia.setText(mensajeRepeticion.replace("#H#", horasRepeticion.toString()));
        } else {
            holder.tvFrecuencia.setText(R.string.sin_repeticion);
        }
    }

    @Override
    public int getItemCount() {
        return listaAvisosDTO.size();
    }

    public class MisAvisosViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvFechaIni;
        TextView tvFechaFin;
        TextView tvFrecuencia;
        TextView tvDescripcion;

        MisAvisosViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_mis_avisos);
            tvFechaIni = (TextView) itemView.findViewById(R.id.tv_mis_avisos_fecha_ini);
            tvFechaFin = (TextView) itemView.findViewById(R.id.tv_mis_avisos_fecha_fin);
            tvFrecuencia = (TextView) itemView.findViewById(R.id.tv_mis_avisos_frecuencia);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_mis_avisos_descripcion);
        }
    }
}
