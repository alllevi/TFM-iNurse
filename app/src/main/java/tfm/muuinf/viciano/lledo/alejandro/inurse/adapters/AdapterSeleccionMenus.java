package tfm.muuinf.viciano.lledo.alejandro.inurse.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;

/**
 * Created by Alex on 19/01/2017.
 */

public class AdapterSeleccionMenus extends RecyclerView.Adapter<AdapterSeleccionMenus.SeleccionMenusViewHolder> {

    public AdapterSeleccionMenus() {
    }

    @Override
    public SeleccionMenusViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_seleccion_menu, parent, false);
        final SeleccionMenusViewHolder vh = new SeleccionMenusViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final SeleccionMenusViewHolder holder, final int position) {
        holder.tvMenuId.setText("Menu 1");
        holder.tvPrimerPlato.setText("Sopa de estrellas");
        holder.tvSegundoPlato.setText("Pollo asado con patatas fritas");
        holder.tvTercerPlato.setText("Yogurt natural");
        holder.ckSeleccionado.setSelected(true);
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
            this.cardView = (CardView) itemView.findViewById(R.id.cv_seleccion_menu);
            this.tvMenuId = (TextView) itemView.findViewById(R.id.tv_menu_id);
            this.tvPrimerPlato = (TextView) itemView.findViewById(R.id.tv_primer_plato);
            this.tvSegundoPlato = (TextView) itemView.findViewById(R.id.tv_segundo_plato);
            this.tvTercerPlato = (TextView) itemView.findViewById(R.id.tv_postre);
            this.ckSeleccionado = (CheckBox) itemView.findViewById(R.id.ch_menu_solicitado);
        }
    }
}
