package com.projetofragmento.pc_rafael.mynorthapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Controller.UsuarioController;
import DAL.UsuarioDAL;
import Entities.UsuarioEntity;

public class PrincipalActivity extends AppCompatActivity {

    private TextView user;
    private UsuarioController crudUser;
    private ArrayList<String> itens;
    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<Integer>ids;
    private ListView listUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listUser = findViewById(R.id.lstViewUserID);
        try {
            crudUser = new UsuarioController(getBaseContext());
            Cursor cursor = ConsultarDados();

            int indiceColunaID = cursor.getColumnIndex("_id");
            int indiceColunaLogin = cursor.getColumnIndex("login");

            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();
            itensAdaptador = new ArrayAdapter<String>(getBaseContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    itens);
            listUser.setAdapter(itensAdaptador);
            cursor.moveToFirst();
            while (cursor != null) {
                itens.add(cursor.getString(indiceColunaLogin));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaID)));
                Log.i("Resultado - ", "ID: " + cursor.getString(indiceColunaID) + " LOGIN: " + cursor.getString(indiceColunaLogin));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Cursor ConsultarDados() {
        Cursor cursor = crudUser.carregaDados();
        return cursor;
    }
}
