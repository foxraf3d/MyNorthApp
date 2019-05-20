package com.projetofragmento.pc_rafael.mynorthapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import Adapter.ContasAdapter;
import Adapter.TabAdapter;
import Adapter.TipoContaAdapter;
import Controller.ContasController;
import Controller.TipoContaController;
import Controller.UsuarioController;
import DAL.CriaBanco;
import DAL.SlidingTabLayout;
import Entities.ContasEntity;
import Entities.TipoContaEntity;
import faranjit.currency.edittext.CurrencyEditText;
import helper.Util;

public class PrincipalActivity extends AppCompatActivity {

    private String nomeUsuario;
    private UsuarioController crudUser;
    private Toolbar toolbar;
    private CriaBanco db;
    private TipoContaController crudTipoConta;
    private ContasController crudConta;
    private TipoContaEntity tipoContaEntity;
    private ContasEntity contaEntity;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private static EditText dataVencimentoEdit;
    private static EditText dataPagamentoEdit;
    private static Spinner dropTipoConta;
    private static EditText anoEdit;
    private static Spinner dropMes;
    private static EditText numeroParcelaEdit;
    private static EditText qtdParcelaEdit;
    private static CurrencyEditText valorEdit;

    private String resultado;


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
                dialogTipoConta();
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

        dropTipoConta = new Spinner(PrincipalActivity.this);
        ArrayList<String>listTipoConta = carregarTipoConta();
        ArrayAdapter<String> adapterTipoConta = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listTipoConta);
        dropTipoConta.setAdapter(adapterTipoConta);
        layout.addView(dropTipoConta);

        anoEdit = new EditText(PrincipalActivity.this);
        anoEdit.setHint("Ano");
        anoEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        anoEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        layout.addView(anoEdit);

        dropMes = new Spinner(PrincipalActivity.this);
        String[] listMes = Util.listaMeses();
        ArrayAdapter<String> adapterMes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listMes);
        dropMes.setAdapter(adapterMes);
        layout.addView(dropMes);

        numeroParcelaEdit = new EditText(PrincipalActivity.this);
        numeroParcelaEdit.setHint("Parcela");
        numeroParcelaEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(numeroParcelaEdit);

        qtdParcelaEdit = new EditText(PrincipalActivity.this);
        qtdParcelaEdit.setHint("Quantidade de Parcelas");
        qtdParcelaEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(qtdParcelaEdit);

        valorEdit = new CurrencyEditText(PrincipalActivity.this,null);
        valorEdit.setLocale(new Locale("pt", "BR"));
        valorEdit.setGroupDivider('.');
        valorEdit.setMonetaryDivider(',');
        valorEdit.showSymbol(true);
        valorEdit.setHint("Valor R$");
        valorEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(valorEdit);

        dataVencimentoEdit = new EditText(PrincipalActivity.this);
        dataVencimentoEdit.setHint("Data de vencimento");
        dataVencimentoEdit.setInputType(InputType.TYPE_NULL);
        dataVencimentoEdit.setTextIsSelectable(false);
        dataVencimentoEdit.setMaxEms(10);

        dataVencimentoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        layout.addView(dataVencimentoEdit);

        dataPagamentoEdit = new EditText(PrincipalActivity.this);
        dataPagamentoEdit.setHint("Data de pagamento");
        dataPagamentoEdit.setInputType(InputType.TYPE_NULL);
        dataPagamentoEdit.setTextIsSelectable(false);
        dataPagamentoEdit.setMaxEms(10);

        dataPagamentoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        layout.addView(dataPagamentoEdit);


        alertDialog.setView(layout);

        //Configurando Botões
        alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    boolean camposPreenchidos = Util.validaConta(dropTipoConta.getSelectedItem().toString(),
                            anoEdit.getText().toString(), dropMes.getSelectedItem().toString(), numeroParcelaEdit.getText().toString(),
                            qtdParcelaEdit.getText().toString(), valorEdit.getCurrencyText(),dataVencimentoEdit.getText().toString(),
                            dataPagamentoEdit.getText().toString());

                    if (camposPreenchidos){
                        String feedBack = inserirConta(dropTipoConta.getSelectedItem().toString(),
                                anoEdit.getText().toString(), dropMes.getSelectedItem().toString(), numeroParcelaEdit.getText().toString(),
                                qtdParcelaEdit.getText().toString(), valorEdit.getCurrencyText(),dataVencimentoEdit.getText().toString(),
                                dataPagamentoEdit.getText().toString());
                        Toast.makeText(PrincipalActivity.this, feedBack, Toast.LENGTH_SHORT).show();
                        contaEntity = retornaUltimoRegistroConta();
                        ContasAdapter cAdapter = new ContasAdapter(ContasAdapter.listaContas);
                        cAdapter.notificaInsertConta(contaEntity);
                    }else{
                        Toast.makeText(getApplicationContext(), "Verifique os dados da Conta.", Toast.LENGTH_LONG).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
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

    private ArrayList<String> carregarTipoConta() {
        TipoContaController tpController = new TipoContaController(getBaseContext());
        Cursor cursor = tpController.carregaDados();
        ArrayList<String> itens = new ArrayList<String>();
        itens.add("Selecione um tipo de conta...");
        int indiceColunaTipoConta = cursor.getColumnIndex("tipoConta");

        if (cursor != null){
            cursor.moveToFirst();
        }
        for(int i = 0;i < cursor.getCount(); i++){
            itens.add(cursor.getString(indiceColunaTipoConta));
            cursor.moveToNext();
        }
        return itens;
    }

    private void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            if (dataVencimentoEdit.isFocused()) {
                dataVencimentoEdit.setText(day + "/" + (month + 1) + "/" + year);
            }else{
                dataPagamentoEdit.setText(day + "/" + (month + 1) + "/" + year);
            }
        }

    }

    private void dialogTipoConta() {
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
        resultado = crudTipoConta.inserirTipoConta(tipoConta);
        return resultado;
    }

    private String inserirConta(String tipoConta, String anoConta, String mesConta, String numParc, String qtdParc, String valorConta, String dataVencimento, String dataPagamento){
        crudConta = new ContasController(getBaseContext());
        resultado = crudConta.inserirConta(tipoConta, anoConta, mesConta, numParc, qtdParc, valorConta, dataVencimento,dataPagamento);
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

    private ContasEntity retornaUltimoRegistroConta(){
        crudConta = new ContasController(getBaseContext());
        contaEntity = crudConta.retornaUltimoRegistro();
        return contaEntity;
    }


}
