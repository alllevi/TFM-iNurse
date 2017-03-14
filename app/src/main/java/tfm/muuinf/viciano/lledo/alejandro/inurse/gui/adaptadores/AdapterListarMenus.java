package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tfm.muuinf.viciano.lledo.alejandro.inurse.R;
import tfm.muuinf.viciano.lledo.alejandro.inurse.dto.MenuDTO;

public class AdapterListarMenus extends RecyclerView.Adapter<AdapterListarMenus.ListarMenusViewHolder> {

    private List<MenuDTO> listaMenuDTO;

    public AdapterListarMenus(List<MenuDTO> listaMenuDTO) {
        this.listaMenuDTO = listaMenuDTO;
    }

    @Override
    public ListarMenusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_listar_menus, parent, false);
        return new ListarMenusViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListarMenusViewHolder holder, int position) {
        holder.tvHabitacion.setText(listaMenuDTO.get(position).getHabitacion());
        holder.tvPaciente.setText(listaMenuDTO.get(position).getNombrePaciente());
        holder.tvPrimerPlato.setText(listaMenuDTO.get(position).getPrimero());
        holder.tvSegundoPlato.setText(listaMenuDTO.get(position).getSegundo());
        holder.tvPostre.setText(listaMenuDTO.get(position).getPostre());
    }

    @Override
    public int getItemCount() {
        return listaMenuDTO.size();
    }

    public class ListarMenusViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvHabitacion;
        TextView tvPaciente;
        TextView tvPrimerPlato;
        TextView tvSegundoPlato;
        TextView tvPostre;

        ListarMenusViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_listar_menus);
            tvPaciente = (TextView) itemView.findViewById(R.id.tv_listar_menus_paciente);
            tvHabitacion = (TextView) itemView.findViewById(R.id.tv_listar_menus_habitacion);
            tvPrimerPlato = (TextView) itemView.findViewById(R.id.tv_listar_menus_primero);
            tvSegundoPlato = (TextView) itemView.findViewById(R.id.tv_listar_menus_segundo);
            tvPostre = (TextView) itemView.findViewById(R.id.tv_listar_menus_postre);
        }
    }
}
