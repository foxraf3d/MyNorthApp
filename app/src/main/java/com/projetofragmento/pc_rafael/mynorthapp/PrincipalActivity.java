package com.projetofragmento.pc_rafael.mynorthapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adapter.TabAdapter;
import Adapter.TipoContaAdapter;
import Controller.TipoContaController;
import Controller.UsuarioController;
import DAL.CriaBanco;
import DAL.SlidingTabLayout;
import Entities.TipoContaEntity;
import helper.Util;

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
                return true;
            case R.id.item_cadastrarConta:
                adicionaConta();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void adicionaConta(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PrincipalActivity.this);

        //Configurando a Dialog
        alertDialog.setTitle("Cadastramento de Contas");
        alertDialog.setMessage("Cadastre uma conta");
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.mipmap.ic_mynorthapp);

        //Configurando elementes de interação com usuário.
        LinearLayout layout = new LinearLayout(PrincipalActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText anoEdit = new EditText(PrincipalActivity.this);
        anoEdit.setHint("Ano");
        anoEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        anoEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        layout.addView(anoEdit);

        final Spinner dropMes = new Spinner(PrincipalActivity.this);
        String[] listMes = Util.listaMeses();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listMes);
        dropMes.setAdapter(adapter);
        layout.addView(dropMes);

        final EditText numeroParcelaEdit = new EditText(PrincipalActivity.this);
        numeroParcelaEdit.setHint("Parcela");
        layout.addView(numeroParcelaEdit);

        final EditText valorEdit = new EditText(PrincipalActivity.this);
        valorEdit.setHint("Valor R$");
        valorEdit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        layout.addView(valorEdit);

        

        alertDialog.setView(layout);

        /*valores.put(TABELA_CONTAS[anoConta], _anoConta);
        valores.put(TABELA_CONTAS[mesConta], _mesConta);
        valores.put(TABELA_CONTAS[numeroParcela], _numeroParcela);
        valores.put(TABELA_CONTAS[valorConta], _valorConta);
        valores.put(TABELA_CONTAS[dataVencimentoConta], _dataVencimento);
        valores.put(TABELA_CONTAS[dataPagamentoConta], _dataPagamento);*/

        //Configurando Botões


        alertDialog.create();
        alertDialog.show();

    }


    private void adicionarTipoConta() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PrincipalActivity.this);

        //Configurando a Dialog
        alertDialog.setTitle("Adicionar Tipo de Conta");
        alertDialog.setMessage("Tipo de Conta");
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.mipmap.ic_mynorthapp);

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
                    tipoContaEntity = retornaUltimoRegistro();
                    TipoContaAdapter tcAdapter = new TipoContaAdapter(TipoContaAdapter.listaTipoConta);
                    tcAdapter.notificaInsertTipoConta(tipoContaEntity);
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
        String resultado;

        resultado = crudTipoConta.inserirTipoConta(tipoConta);
        return resultado;
    }

    private void deslogarUsuario() {
        SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_MYNORTH, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        finish();
    }

    private TipoContaEntity retornaUltimoRegistro(){
        crudTipoConta = new TipoContaController(getBaseContext());
        tipoContaEntity = crudTipoConta.retornaUltimoRegistro();
        return tipoContaEntity;
    }


}
