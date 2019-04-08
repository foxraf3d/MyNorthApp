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
            ArrayList<Integer> ids = new ArrayList<Integer>();

//            ArrayAdapter<String> itensAdaptador = new ArrayAdapter<String>(getBaseContext(),
//                    android.R.layout.simple_list_item_1,
//                    android.R.id.text1,
//                    itens);
//            listUser.setAdapter(itensAdaptador);

            cursor.moveToFirst();

            for (int i = 1; i <= cursor.getCount(); i++){
                itens.add(cursor.getString(indiceColunaLogin));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaID)));
                Log.i("Resultado ", "ID: " + cursor.getString(indiceColunaID) + " LOGIN: " + cursor.getString(indiceColunaLogin));
                if (i <= cursor.getCount())
                    cursor.moveToNext();
            }

            List<TipoContaEntity> listaTipoContaEntity = converteArrayParaEntity(itens);

            TipoContaAdapter tipoContaAdapter = new TipoContaAdapter(listaTipoContaEntity);
            rv.setAdapter(tipoContaAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    private List<TipoContaEntity> converteArrayParaEntity(ArrayList<String> itens) {
        TipoContaEntity tCEntity = new TipoContaEntity();
        List<TipoContaEntity> listTCEntit = null;
        for (int i = 0; i <= itens.size(); i++){
            tCEntity.setTipoConta(itens.get(i));
            listTCEntit.add(i, tCEntity);
        }
        return listTCEntit;
    }

    private Cursor ConsultarDados() {
        Cursor cursor = tcController.carregaDados();
        return cursor;
    }

}
