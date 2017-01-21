package tfm.muuinf.viciano.lledo.alejandro.inurse.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

/**
 * Created by Alex on 21/01/2017.
 */

public class AdapterListarSolicitudes extends RecyclerView.Adapter<AdapterListarSolicitudes.ListarSolicitudesViewHolder> {

    @Override
    public ListarSolicitudesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_listar_solicitudes, parent, false);
        final ListarSolicitudesViewHolder vh = new ListarSolicitudesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ListarSolicitudesViewHolder holder, final int position) {
        holder.tvPrioridad.setText("Emergencia");
        holder.tvFecha.setText("22/12/1992 20:20");
        holder.tvEstado.setText("En progreso");
        holder.tvSolicitud.setText("Revisión de la cama que se ha Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliquaroto");
        holder.tvDescripcion.setText("Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos");
        //Button listeners
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ListarSolicitudesViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvPrioridad;
        TextView tvFecha;
        TextView tvEstado;
        TextView tvSolicitud;
        TextView tvDescripcion;
        Button btEmpezar;
        Button btRechazar;

        ListarSolicitudesViewHolder(final View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView.findViewById(R.id.cv_listar_solicitudes);
            this.tvPrioridad = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_prioridad);
            this.tvFecha = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_fecha);
            this.tvEstado = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_estado);
            this.tvSolicitud = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_solicitud);
            this.tvDescripcion = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_descripcion);
            this.btEmpezar = (Button) itemView.findViewById(R.id.bt_listar_solicitudes_empezar);
            this.btRechazar = (Button) itemView.findViewById(R.id.bt_listar_solicitudes_rechazar);
        }

    }
}
