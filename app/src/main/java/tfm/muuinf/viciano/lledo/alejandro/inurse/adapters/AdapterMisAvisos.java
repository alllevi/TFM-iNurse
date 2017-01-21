package tfm.muuinf.viciano.lledo.alejandro.inurse.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

/**
 * Created by Alex on 21/01/2017.
 */

public class AdapterMisAvisos extends RecyclerView.Adapter<AdapterMisAvisos.MisAvisosViewHolder> {


    @Override
    public MisAvisosViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mis_avisos, parent, false);
        final MisAvisosViewHolder vh = new MisAvisosViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MisAvisosViewHolder holder, final int position) {
        holder.tvFecha.setText("12/01/2016 20:30");
        holder.tvFrecuencia.setText("Cada 12 horas");
        holder.tvDescripcion.setText("Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MisAvisosViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvFecha;
        TextView tvFrecuencia;
        TextView tvDescripcion;

        MisAvisosViewHolder(final View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView.findViewById(R.id.cv_mis_avisos);
            this.tvFecha = (TextView) itemView.findViewById(R.id.tv_mis_avisos_fecha);
            this.tvFrecuencia = (TextView) itemView.findViewById(R.id.tv_mis_avisos_frecuencia);
            this.tvDescripcion = (TextView) itemView.findViewById(R.id.tv_mis_avisos_descripcion);
        }
    }
}
