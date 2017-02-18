package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.SolicitudDTO;

public class AdapterMisSolicitudes extends RecyclerView.Adapter<AdapterMisSolicitudes.MisSolicitudesViewHolder> {

    private List<SolicitudDTO> listaSolicitudesDTO;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public AdapterMisSolicitudes(final List<SolicitudDTO> listaSolicitudesDTO) {
        this.listaSolicitudesDTO = listaSolicitudesDTO;
    }

    @Override
    public MisSolicitudesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mis_solicitudes, parent, false);
        return new MisSolicitudesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MisSolicitudesViewHolder holder, final int position) {
        holder.tvPrioridad.setText(listaSolicitudesDTO.get(position).getPrioridadDescripcion());
        holder.tvFecha.setText(listaSolicitudesDTO.get(position).getFecha().toString());
        holder.tvFecha.setText(sdf.format(listaSolicitudesDTO.get(position).getFecha()));
        holder.tvEstado.setText(listaSolicitudesDTO.get(position).getTipoDescripcion());
        holder.tvSolicitud.setText(listaSolicitudesDTO.get(position).getDescripcion());
        holder.tvDescripcion.setText(listaSolicitudesDTO.get(position).getDescripcionLarga());
    }

    @Override
    public int getItemCount() {
        return listaSolicitudesDTO.size();
    }

    public class MisSolicitudesViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvPrioridad;
        TextView tvFecha;
        TextView tvEstado;
        TextView tvSolicitud;
        TextView tvDescripcion;

        MisSolicitudesViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_mis_solicitudes);
            tvPrioridad = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_prioridad);
            tvFecha = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_fecha);
            tvEstado = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_estado);
            tvSolicitud = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_solicitud);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_descripcion);
        }
    }
}