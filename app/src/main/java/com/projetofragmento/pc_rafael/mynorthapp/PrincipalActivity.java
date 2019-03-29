package com.projetofragmento.pc_rafael.mynorthapp;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import Controller.UsuarioController;
import DAL.CriaBanco;

public class PrincipalActivity extends AppCompatActivity {

    private String nomeUsuario;
    private UsuarioController crudUser;
    private Toolbar toolbar;
    private CriaBanco db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        toolbar = findViewById(R.id.toolbarID);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            nomeUsuario = extras.getString("USUARIO");
        }

        toolbar.setTitle("MyNorthApp");
        toolbar.setSubtitle("...olá "+ nomeUsuario);
        setSupportActionBar(toolbar);


       /* try {
            crudUser = new UsuarioController(getBaseContext());
            Cursor cursor = ConsultarDados();

            int indiceColunaID = cursor.getColumnIndex("id");
            int indiceColunaLogin = cursor.getColumnIndex("login");

            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();
            itensAdaptador = new ArrayAdapter<String>(getBaseContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    itens);
            listUser.setAdapter(itensAdaptador);
            cursor.moveToFirst();

            for (int i = 1; i <= cursor.getCount(); i++){
                itens.add(cursor.getString(indiceColunaLogin));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaID)));
                Log.i("Resultado ", "ID: " + cursor.getString(indiceColunaID) + " LOGIN: " + cursor.getString(indiceColunaLogin));
                if (i <= cursor.getCount())
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_sair:
                deslogarUsuario();
                return true;
            case R.id.item_backupBanco:
                try{
                    new UsuarioController(getBaseContext()).copiaBanco();
                    Toast.makeText(getApplicationContext(), "Banco de dados copiado com sucesso!", Toast.LENGTH_LONG).show();
                    return true;
                }catch (Exception e){
                    e.getMessage();
                }
            case R.id.item_addTipoConta:
                adicionarTipoConta();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void adicionarTipoConta() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PrincipalActivity.this);

        //Configurando a Dialog
        alertDialog.setTitle("Adicionar Tipo de Conta");
        alertDialog.setMessage("Tipo de Conta");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(PrincipalActivity.this);
        alertDialog.setView(editText);

        alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tipoConta = editText.getText().toString();
                //Valida se o campo foi preenchido
                if (tipoConta.isEmpty()){
                    Toast.makeText(PrincipalActivity.this, "Informe um tipo de conta", Toast.LENGTH_SHORT).show();
                }else{
                    
                }
            }
        });
    }

    private void deslogarUsuario() {
        SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_MYNORTH, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        finish();
    }


    private Cursor ConsultarDados() {
        Cursor cursor = crudUser.carregaDados();
        return cursor;
    }
}
