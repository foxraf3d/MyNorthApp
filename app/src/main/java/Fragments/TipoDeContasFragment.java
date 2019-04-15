package Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.projetofragmento.pc_rafael.mynorthapp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Adapter.TipoContaAdapter;
import Controller.TipoContaController;
import Controller.UsuarioController;
import DAL.CriaBanco;
import Entities.TipoContaEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipoDeContasFragment extends Fragment {

    private RecyclerView rv;
    private TipoContaController tcController;
    private CriaBanco db;
    private TipoContaAdapter tipoContaAdapter;
    private List<TipoContaEntity> listaTipoContaEntity;

    public TipoDeContasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_tipo_de_contas, container, false);

        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            tcController = new TipoContaController(getContext());
            Cursor cursor = ConsultarDados();

            int indiceColunaID = cursor.getColumnIndex("id");
            int indiceColunaLogin = cursor.getColumnIndex("tipoConta");

            ArrayList<String> itens = new ArrayList<String>();
            ArrayList<String> ids = new ArrayList<String>();

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++){
                itens.add(cursor.getString(indiceColunaLogin));
                ids.add(cursor.getString(indiceColunaID));
                Log.i("Resultado ", "ID: " + cursor.getString(indiceColunaID) + " TIPO_CONTA: " + cursor.getString(indiceColunaLogin));
                if (i <= cursor.getCount())
                    cursor.moveToNext();
            }

            listaTipoContaEntity = converteArrayParaEntity(ids, itens);

            tipoContaAdapter = new TipoContaAdapter(listaTipoContaEntity);
            rv.setAdapter(tipoContaAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    private List<TipoContaEntity> converteArrayParaEntity(ArrayList<String> ids,ArrayList<String> itens) {
        TipoContaEntity tCEntity;
        List<TipoContaEntity> listTCEntit = new ArrayList<>();
        for (int i = 0; i < itens.size(); i++){
            tCEntity = new TipoContaEntity(ids.get(i),itens.get(i));
            listTCEntit.add(new TipoContaEntity(tCEntity.getId(),tCEntity.getTipoConta()));
        }
        return listTCEntit;
    }

    private Cursor ConsultarDados() {
        Cursor cursor = tcController.carregaDados();
        return cursor;
    }



}
