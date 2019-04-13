package Adapter;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.projetofragmento.pc_rafael.mynorthapp.R;

import java.util.List;

import Entities.TipoContaEntity;

public class TipoContaAdapter extends RecyclerView.Adapter<TipoContaAdapter.TipoContaViewHolder> {

    List<TipoContaEntity> listaTipoConta;

    public TipoContaAdapter(List<TipoContaEntity> listTipoContas) {
        this.listaTipoConta = listTipoContas;
    }

    @NonNull
    @Override
    public TipoContaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        TipoContaViewHolder tcvh = new TipoContaViewHolder(v);
        return tcvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final TipoContaViewHolder tipoContaViewHolder, int i) {
        tipoContaViewHolder.tipoConta.setText(listaTipoConta.get(i).getTipoConta());
    }

    @Override
    public int getItemCount() {
        return listaTipoConta.size();
    }

    public static class TipoContaViewHolder extends RecyclerView.ViewHolder{

        private TextView tipoConta;

        public TipoContaViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoConta = itemView.findViewById(R.id.cv_tipoConta);
        }
    }
}
