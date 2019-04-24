package Adapter;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projetofragmento.pc_rafael.mynorthapp.R;

import java.util.ArrayList;
import java.util.List;

import Controller.TipoContaController;
import Entities.TipoContaEntity;

public class TipoContaAdapter extends RecyclerView.Adapter<TipoContaAdapter.TipoContaViewHolder> {

    public static List<TipoContaEntity> listaTipoConta;

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

        final TipoContaEntity tipoConta = listaTipoConta.get(i);
        ImageButton deleteButton = tipoContaViewHolder.itemView.findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

                alertDialog.setTitle("Confirmação");
                alertDialog.setMessage("Deseja realmente excluir o item?");
                alertDialog.setCancelable(false);
                alertDialog.setIcon(R.mipmap.ic_mynorthapp);

                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "Exclusão Cancelada", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TipoContaController tcController = new TipoContaController(view.getContext());
                        boolean sucesso = tcController.delete(Integer.parseInt(tipoConta.getId()));
                        if (sucesso){
                            removerTipoConta(tipoConta);
                            Toast.makeText(view.getContext(), "Item excluído com sucesso!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(view.getContext(), "Erro ao excluir o item!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                alertDialog.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaTipoConta != null ? listaTipoConta.size():0;
    }

    public static class TipoContaViewHolder extends RecyclerView.ViewHolder{

        private TextView tipoConta;

        public TipoContaViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoConta = itemView.findViewById(R.id.cv_tipoConta);
        }
    }

    public void notificaInsertTipoConta(TipoContaEntity tipoConta){
        if (listaTipoConta == null){
            listaTipoConta = new ArrayList<>();
        }
        listaTipoConta.add(tipoConta);
        notifyItemInserted(getItemCount());
    }

    public void removerTipoConta(TipoContaEntity tipoConta){
        int position = listaTipoConta.indexOf(tipoConta);
        listaTipoConta.remove(position);
        notifyItemRemoved(position);
    }
}
