package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MapaHospitalarioDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal.MapaHospitalarioActivity;

public class AdapterMapaHospitalario extends RecyclerView.Adapter<AdapterMapaHospitalario.MapaHospitalarioViewHolder> {

    private MapaHospitalarioActivity mapaHospitalarioActivity;
    List<MapaHospitalarioDTO> listaMapaHospitalario;

    public AdapterMapaHospitalario(final MapaHospitalarioActivity mapaHospitalarioActivity, List<MapaHospitalarioDTO> listaMapaHospitalario) {
        this.mapaHospitalarioActivity = mapaHospitalarioActivity;
        this.listaMapaHospitalario = listaMapaHospitalario;
    }

    @Override

    public MapaHospitalarioViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mapa_hospitalario, parent, false);
        return new MapaHospitalarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MapaHospitalarioViewHolder holder, final int position) {
        holder.tvHabitacion.setText(listaMapaHospitalario.get(position).getHabitacion());
        holder.tvPaciente.setText(listaMapaHospitalario.get(position).getPaciente());
        holder.tvSexo.setText(listaMapaHospitalario.get(position).getSexo());
        holder.tvMotivo.setText(listaMapaHospitalario.get(position).getMotivo());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mapaHospitalarioActivity.onClickCard(listaMapaHospitalario.get(position).getPacienteKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaMapaHospitalario.size();
    }

    public class MapaHospitalarioViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvHabitacion;
        TextView tvPaciente;
        TextView tvSexo;
        TextView tvMotivo;

        MapaHospitalarioViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_mapa_camas);
            tvHabitacion = (TextView) itemView.findViewById(R.id.tv_mapa_habitacion);
            tvPaciente = (TextView) itemView.findViewById(R.id.tv_mapa_paciente);
            tvSexo = (TextView) itemView.findViewById(R.id.tv_mapa_sexo);
            tvMotivo = (TextView) itemView.findViewById(R.id.tv_mapa_motivo_ingreso);
        }
    }
}
