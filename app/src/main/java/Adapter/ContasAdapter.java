package Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Entities.ContasEntity;

public class ContasAdapter extends RecyclerView.Adapter<ContasAdapter.ContaPendenteViewHolder> {

    public static List<ContasEntity> listaContas;

    @NonNull
    @Override
    public ContaPendenteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContaPendenteViewHolder contaPendenteViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listaContas != null ? listaContas.size():0;
    }

    public class ContaPendenteViewHolder extends RecyclerView.ViewHolder{
        public ContaPendenteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
