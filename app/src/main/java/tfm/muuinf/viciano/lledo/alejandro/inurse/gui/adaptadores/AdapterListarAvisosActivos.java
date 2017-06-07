package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.AvisosDTO;

public class AdapterListarAvisosActivos extends RecyclerView.Adapter<AdapterListarAvisosActivos.AvisosActivosViewHolder> {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private Context context;
    private List<AvisosDTO> listaAvisosDTO = new ArrayList<>();

    public AdapterListarAvisosActivos(List<AvisosDTO> listaAvisosDTO, Context context) {
        this.listaAvisosDTO = listaAvisosDTO;
        this.context = context;
    }

    @Override
    public AvisosActivosViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_listar_avisos_activos, parent, false);
        return new AvisosActivosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterListarAvisosActivos.AvisosActivosViewHolder holder, int position) {
        holder.tvHabitacion.setText(listaAvisosDTO.get(position).getHabitacion());
        holder.tvPaciente.setText(listaAvisosDTO.get(position).getPaciente());
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

    public class AvisosActivosViewHolder extends RecyclerView.ViewHolder {

        TextView tvHabitacion;
        TextView tvPaciente;
        TextView tvFechaIni;
        TextView tvFechaFin;
        TextView tvFrecuencia;
        TextView tvDescripcion;


        AvisosActivosViewHolder(final View itemView) {
            super(itemView);
            tvHabitacion = (TextView) itemView.findViewById(R.id.tv_listar_avisos_habitacion);
            tvPaciente = (TextView) itemView.findViewById(R.id.tv_listar_avisos_paciente);
            tvFechaIni = (TextView) itemView.findViewById(R.id.tv_listar_avisos_fecha_ini);
            tvFechaFin = (TextView) itemView.findViewById(R.id.tv_listar_avisos_fecha_fin);
            tvFrecuencia = (TextView) itemView.findViewById(R.id.tv_listar_avisos_frecuencia);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_listar_avisos_descripcion);
        }
    }
}
