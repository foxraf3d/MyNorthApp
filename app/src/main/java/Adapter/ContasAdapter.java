package Adapter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.projetofragmento.pc_rafael.mynorthapp.R;

import java.util.ArrayList;
import java.util.List;

import Controller.ContasController;
import Entities.ContasEntity;

public class ContasAdapter extends RecyclerView.Adapter<ContasAdapter.ContaPendenteViewHolder> {

    public static List<ContasEntity> listaContas;

    public ContasAdapter(List<ContasEntity> _listaContas) {
        this.listaContas = _listaContas;
    }

    @NonNull
    @Override
    public ContaPendenteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowlayot_contas_pendentes, viewGroup, false);
        ContaPendenteViewHolder contaPendenteViewHolder = new ContaPendenteViewHolder(v);
        return contaPendenteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ContaPendenteViewHolder contaPendenteViewHolder, int i) {
        final ContasEntity contasEntity = listaContas.get(i);
        contaPendenteViewHolder.tipoContaContas.setText(listaContas.get(i).getTipoContasConta());
        contaPendenteViewHolder.mesConta.setText(listaContas.get(i).getMesConta());
        contaPendenteViewHolder.anoConta.setText(listaContas.get(i).getAnoConta());
        contaPendenteViewHolder.valorConta.setText("R$ " + listaContas.get(i).getValorConta());

        if(listaContas.get(i).getDataPagamento().equalsIgnoreCase("")){
            contaPendenteViewHolder.statusConta.setText("| Pendente");
            contaPendenteViewHolder.statusConta.setTextColor(Color.RED);
            //contaPendenteViewHolder.colorBackground.setBackgroundColor(Color.RED);
        }else{
            contaPendenteViewHolder.statusConta.setText("| Pago: " + listaContas.get(i).getDataPagamento());
            contaPendenteViewHolder.statusConta.setTextColor(Color.GREEN);
            //contaPendenteViewHolder.colorBackground.setBackgroundColor(Color.GRAY);
        }


        ImageButton deleteButton = contaPendenteViewHolder.itemView.findViewById(R.id.btnDeleteConta);
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
                        ContasController contaController = new ContasController(view.getContext());
                        boolean sucesso = contaController.delete(Integer.parseInt(contasEntity.getIdConta()));
                        if (sucesso){
                            removerConta(contasEntity);
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
        return listaContas != null ? listaContas.size():0;
    }

    public class ContaPendenteViewHolder extends RecyclerView.ViewHolder{

        private TextView tipoContaContas;
        private TextView mesConta;
        private TextView anoConta;
        private TextView valorConta;
        private TextView statusConta;
        private ConstraintLayout colorBackground;


        public ContaPendenteViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoContaContas = itemView.findViewById(R.id.txtTipoContaID);
            mesConta = itemView.findViewById(R.id.txtMESID);
            anoConta = itemView.findViewById(R.id.txtAnoID);
            valorConta = itemView.findViewById(R.id.txtValorID);
            statusConta = itemView.findViewById(R.id.txtStatusID);
            colorBackground = itemView.findViewById(R.id.backgroundID);
        }

    }

    public void notificaInsertConta(ContasEntity conta){
        if (listaContas == null){
            listaContas = new ArrayList<>();
        }
        listaContas.add(conta);
        notifyItemInserted(getItemCount());
    }

    public void removerConta(ContasEntity conta){
        int position = listaContas.indexOf(conta);
        listaContas.remove(position);
        notifyItemRemoved(position);
    }
}
