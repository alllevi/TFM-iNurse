package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

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
        holder.tvFecha.setText(sdf.format(listaSolicitudesDTO.get(position).getFecha()));
        holder.tvEstado.setText(listaSolicitudesDTO.get(position).getTipoDescripcion());
        holder.tvSolicitud.setText(listaSolicitudesDTO.get(position).getDescripcion());
        holder.tvDescripcion.setText(listaSolicitudesDTO.get(position).getDescripcionLarga());
        String motivoRechazo = listaSolicitudesDTO.get(position).getMotivoRechazo();
        if (StringUtils.isNotBlank(motivoRechazo)) {
            holder.tvMotivoRechazo.setText(motivoRechazo);
        } else {
            holder.llMotivoRechazo.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return listaSolicitudesDTO.size();
    }

    public class MisSolicitudesViewHolder extends RecyclerView.ViewHolder {

        TextView tvPrioridad;
        TextView tvFecha;
        TextView tvEstado;
        TextView tvSolicitud;
        TextView tvDescripcion;
        TextView tvMotivoRechazo;
        LinearLayout llMotivoRechazo;

        MisSolicitudesViewHolder(final View itemView) {
            super(itemView);
            tvPrioridad = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_prioridad);
            tvFecha = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_fecha);
            tvEstado = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_estado);
            tvSolicitud = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_solicitud);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_descripcion);
            tvMotivoRechazo = (TextView) itemView.findViewById(R.id.tv_mis_solicitudes_rechazado);
            llMotivoRechazo = (LinearLayout) itemView.findViewById(R.id.tv_mis_solicitudes_vl_rechazo);
        }
    }
}