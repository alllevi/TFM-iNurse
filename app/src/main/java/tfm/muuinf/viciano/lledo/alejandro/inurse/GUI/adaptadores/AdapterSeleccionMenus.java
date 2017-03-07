package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MenuDTO;
import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.pacientes.SeleccionMenuActivity;

public class AdapterSeleccionMenus extends RecyclerView.Adapter<AdapterSeleccionMenus.SeleccionMenusViewHolder> {

    private List<MenuDTO> listaMenus;
    private SeleccionMenuActivity activity;

    public AdapterSeleccionMenus(SeleccionMenuActivity activity, List<MenuDTO> listaMenus) {
        this.listaMenus = listaMenus;
        this.activity = activity;
    }

    @Override
    public SeleccionMenusViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_seleccion_menu, parent, false);
        return new SeleccionMenusViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SeleccionMenusViewHolder holder, final int position) {
        holder.tvMenuId.setText("Menu " + (position + 1));
        holder.tvPrimerPlato.setText(listaMenus.get(position).getPrimero());
        holder.tvSegundoPlato.setText(listaMenus.get(position).getSegundo());
        holder.tvTercerPlato.setText(listaMenus.get(position).getPostre());
        holder.ckSeleccionado.setSelected(true);
        if (listaMenus.get(position).isPrecarga()) {
            holder.ckSeleccionado.setChecked(true);
            activity.controlCardViewItems(1);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (holder.ckSeleccionado.isChecked()) {
                    activity.controlCardViewItems(-1);
                    holder.ckSeleccionado.setChecked(false);
                    activity.deseleccionarMenu(listaMenus.get(position));
                } else {
                    Boolean control = activity.controlCardViewItems(1);
                    if (control) {
                        holder.ckSeleccionado.setChecked(true);
                        activity.seleccionarMenu(listaMenus.get(position));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class SeleccionMenusViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvMenuId;
        TextView tvPrimerPlato;
        TextView tvSegundoPlato;
        TextView tvTercerPlato;
        CheckBox ckSeleccionado;

        public SeleccionMenusViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_seleccion_menu);
            tvMenuId = (TextView) itemView.findViewById(R.id.tv_menu_id);
            tvPrimerPlato = (TextView) itemView.findViewById(R.id.tv_primer_plato);
            tvSegundoPlato = (TextView) itemView.findViewById(R.id.tv_segundo_plato);
            tvTercerPlato = (TextView) itemView.findViewById(R.id.tv_postre);
            ckSeleccionado = (CheckBox) itemView.findViewById(R.id.ch_menu_solicitado);
        }
    }
}
