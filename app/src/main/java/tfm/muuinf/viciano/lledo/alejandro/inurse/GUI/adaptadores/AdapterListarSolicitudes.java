package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.SolicitudDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal.ListarSolicitudesActivity;

public class AdapterListarSolicitudes extends RecyclerView.Adapter<AdapterListarSolicitudes.ListarSolicitudesViewHolder> {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    private ListarSolicitudesActivity listarSolicitudesActivity;
    private List<SolicitudDTO> listaSolicitudesDTO;
    private String codigo;

    public AdapterListarSolicitudes(ListarSolicitudesActivity activity, List<SolicitudDTO> listaSolicitudesDTO, String codigo) {
        listarSolicitudesActivity = activity;
        this.listaSolicitudesDTO = listaSolicitudesDTO;
        this.codigo = codigo;
    }

    @Override
    public ListarSolicitudesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_listar_solicitudes, parent, false);
        return new ListarSolicitudesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListarSolicitudesViewHolder holder, final int position) {
        holder.tvPrioridad.setText(listaSolicitudesDTO.get(position).getPrioridadDescripcion());
        holder.tvFecha.setText(sdf.format(listaSolicitudesDTO.get(position).getFecha()));
        holder.tvEstado.setText(listaSolicitudesDTO.get(position).getTipoDescripcion());
        //Paciente
        //Habitacion
        holder.tvSolicitud.setText(listaSolicitudesDTO.get(position).getDescripcion());
        holder.tvDescripcion.setText(listaSolicitudesDTO.get(position).getDescripcionLarga());

        setListeners(holder, listaSolicitudesDTO.get(position).getKey());
        ocultarBotones(holder, listaSolicitudesDTO.get(position).getTipoCodigo());
    }

    private void setListeners(final ListarSolicitudesViewHolder holder, final Integer key) {
        //Button listeners
        holder.btRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                listarSolicitudesActivity.onClickRechazar(key);
            }
        });

        holder.btEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                listarSolicitudesActivity.onClickEnProgreso(key);
            }
        });
    }

    private void ocultarBotones(ListarSolicitudesViewHolder holder, String codigo) {
        if (codigo.equals("TIPEST3")) {
            holder.btEmpezar.setVisibility(View.GONE);
        } else if ("TIPEST4".equals(codigo) || "TIPEST5".equals(codigo)) {
            holder.btEmpezar.setVisibility(View.GONE);
            holder.btRechazar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaSolicitudesDTO.size();
    }

    public class ListarSolicitudesViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvPrioridad;
        TextView tvFecha;
        TextView tvPaciente;
        TextView tvHabitacion;
        TextView tvEstado;
        TextView tvSolicitud;
        TextView tvDescripcion;
        Button btEmpezar;
        Button btRechazar;

        ListarSolicitudesViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_listar_solicitudes);
            tvPrioridad = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_prioridad);
            tvPaciente = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_paciente);
            tvHabitacion = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_habitacion);
            tvFecha = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_fecha);
            tvEstado = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_estado);
            tvSolicitud = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_solicitud);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_listar_solicitudes_descripcion);
            btEmpezar = (Button) itemView.findViewById(R.id.bt_listar_solicitudes_empezar);
            btRechazar = (Button) itemView.findViewById(R.id.bt_listar_solicitudes_rechazar);
        }
    }
}
