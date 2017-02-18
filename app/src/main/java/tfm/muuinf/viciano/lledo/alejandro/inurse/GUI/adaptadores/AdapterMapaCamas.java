package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal.MapaCamasActivity;

public class AdapterMapaCamas extends RecyclerView.Adapter<AdapterMapaCamas.MapaCamasViewHolder> {

    private MapaCamasActivity mapaCamasActivity;

    public AdapterMapaCamas(final MapaCamasActivity activity) {
        mapaCamasActivity = activity;
    }

    @Override

    public MapaCamasViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mapa_camas, parent, false);
        return new MapaCamasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MapaCamasViewHolder holder, final int position) {
        holder.tvHabitacion.setText("210");
        holder.tvPaciente.setText("Paco Perez Martinez");
        holder.tvSexo.setText("Masculino");
        holder.tvMotivo.setText("Neumonía");
        holder.ivImagen.setImageResource(R.mipmap.ic_launcher);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mapaCamasActivity.onClickCard();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MapaCamasViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvHabitacion;
        TextView tvPaciente;
        TextView tvSexo;
        TextView tvMotivo;
        ImageView ivImagen;

        MapaCamasViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_mapa_camas);
            tvHabitacion = (TextView) itemView.findViewById(R.id.tv_mapa_habitacion);
            tvPaciente = (TextView) itemView.findViewById(R.id.tv_mapa_paciente);
            tvSexo = (TextView) itemView.findViewById(R.id.tv_mapa_sexo);
            tvMotivo = (TextView) itemView.findViewById(R.id.tv_mapa_motivo_ingreso);
            ivImagen = (ImageView) itemView.findViewById(R.id.iv_mapa_imagen);
        }
    }
}
