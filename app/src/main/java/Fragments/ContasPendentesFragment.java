package Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projetofragmento.pc_rafael.mynorthapp.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.ContasAdapter;
import Controller.ContasController;
import Entities.ContasEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContasPendentesFragment extends Fragment {


    public ContasPendentesFragment() {
        // Required empty public constructor
    }


    private RecyclerView rv;
    private ContasController contasController;
    private List<ContasEntity> listaContasEntity;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contas_pendentes, container, false);

        rv = view.findViewById(R.id.rvContasPendentes);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            contasController = new ContasController(getContext());
            cursor  = consultaDados();

            int indiceColunaID = cursor.getColumnIndex("id");
            int indiceColunaTipoConta = cursor.getColumnIndex("tipoContaContas");
            int indiceColunaAnoConta =  cursor.getColumnIndex("anoConta");
            int indiceColunaMesConta =  cursor.getColumnIndex("mesConta");
            int indiceColunaNumeroParcelaConta =  cursor.getColumnIndex("numeroParcela");
            int indiceColunaQtdParcelaConta =  cursor.getColumnIndex("qtdParcela");
            int indiceColunaValorContaConta =  cursor.getColumnIndex("valorConta");
            int indiceColunaDataVencimentoContaConta =  cursor.getColumnIndex("dataVencimentoConta");
            int indiceColunaDataPagamentoContaConta =  cursor.getColumnIndex("dataPagamentoConta");

            String[][] itens = new String[cursor.getCount()][9];
            ArrayList<String> ids = new ArrayList<String>();

            cursor.moveToFirst();

            for (int i = 0;i<cursor.getCount(); i++){
                itens[i][0]=cursor.getString(indiceColunaTipoConta);
                itens[i][1]=cursor.getString(indiceColunaAnoConta);
                itens[i][2]=cursor.getString(indiceColunaMesConta);
                itens[i][3]=cursor.getString(indiceColunaNumeroParcelaConta);
                itens[i][4]=cursor.getString(indiceColunaQtdParcelaConta);
                itens[i][5]=cursor.getString(indiceColunaValorContaConta);
                itens[i][6]=cursor.getString(indiceColunaDataVencimentoContaConta);
                itens[i][7]=cursor.getString(indiceColunaDataPagamentoContaConta);
                ids.add(cursor.getString(indiceColunaID));
                Log.i("Resultado ", "ID: " + cursor.getString(indiceColunaID) + " Conta: " + cursor.getString(indiceColunaTipoConta)+" Mês: "+ cursor.getString(indiceColunaMesConta));
                if (i < cursor.getCount())
                    cursor.moveToNext();
            }

            listaContasEntity = converteArrayParaEntity(ids, itens);

            ContasAdapter contasAdapter = new ContasAdapter(listaContasEntity);
            rv.setAdapter(contasAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }

    private List<ContasEntity> converteArrayParaEntity(ArrayList<String> ids, String[][] itens) {
        ContasEntity contasEntity;
        List<ContasEntity> listContasEntity = new ArrayList<>();
        for (int i = 0; i < itens.length; i++){
            contasEntity = new ContasEntity(
                    null,
                    itens[i][0],
                    itens[i][1],
                    itens[i][2],
                    itens[i][3],
                    itens[i][4],
                    itens[i][5],
                    itens[i][6],
                    itens[i][7]);

            listContasEntity.add(new ContasEntity(
                    contasEntity.getIdConta(),
                    contasEntity.getTipoContasConta(),
                    contasEntity.getAnoConta(),
                    contasEntity.getMesConta(),
                    contasEntity.getNumeroParcela(),
                    contasEntity.getQtdParcela(),
                    contasEntity.getValorConta(),
                    contasEntity.getDataVencimento(),
                    contasEntity.getDataPagamento()));
        }
        return listContasEntity;
    }

    private Cursor consultaDados() {
        cursor = contasController.carregaDados();
        return cursor;
    }

}
