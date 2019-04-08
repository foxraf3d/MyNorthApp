package com.projetofragmento.pc_rafael.mynorthapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import Adapter.TabAdapter;
import Controller.TipoContaController;
import Controller.UsuarioController;
import DAL.CriaBanco;
import DAL.SlidingTabLayout;
import Entities.TipoContaEntity;

public class PrincipalActivity extends AppCompatActivity {

    private String nomeUsuario;
    private UsuarioController crudUser;
    private Toolbar toolbar;
    private CriaBanco db;
    private TipoContaController crudTipoConta;
    private TipoContaEntity tipoContaEntity;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;



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

        //Sliding Tabs
        slidingTabLayout = findViewById(R.id.stl_tabs);
        viewPager = findViewById(R.id.vp_pagina);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.textColor));

        //Adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);

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
                    String feedback = inserirTipoConta(tipoConta);
                    Toast.makeText(PrincipalActivity.this, feedback, Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    private String inserirTipoConta(String tipoConta) {
        crudTipoConta = new TipoContaController(getBaseContext());
        tipoContaEntity = new TipoContaEntity();
        tipoContaEntity.setTipoConta(tipoConta);
        String resultado;

        resultado = crudTipoConta.inserirTipoConta(tipoContaEntity.getTipoConta());
        return resultado;
    }

    private void deslogarUsuario() {
        SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_MYNORTH, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        finish();
    }


}
