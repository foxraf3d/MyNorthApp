package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import DAL.CriaBanco;
import Entities.ContasEntity;

import static helper.Define_Tabela.TABELA_CONTAS;
import static helper.Define_Tabela.anoConta;
import static helper.Define_Tabela.dataPagamentoConta;
import static helper.Define_Tabela.dataVencimentoConta;
import static helper.Define_Tabela.idContaContas;
import static helper.Define_Tabela.mesConta;
import static helper.Define_Tabela.nomeTabelaConta;
import static helper.Define_Tabela.numeroParcela;
import static helper.Define_Tabela.qtdParcela;
import static helper.Define_Tabela.tipoContaContas;
import static helper.Define_Tabela.valorConta;

public class ContasController {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private Cursor cursor;

    public ContasController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserirConta(String _tipoContaContas,
                               String _anoConta,
                               String _mesConta,
                               String _numeroParcela,
                               String _qtdParcela ,
                               String _valorConta,
                               String _dataVencimento,
                               String _dataPagamento){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getReadableDatabase();
        valores.put(TABELA_CONTAS[tipoContaContas], _tipoContaContas);
        valores.put(TABELA_CONTAS[anoConta], _anoConta);
        valores.put(TABELA_CONTAS[mesConta], _mesConta);
        valores.put(TABELA_CONTAS[numeroParcela], _numeroParcela);
        valores.put(TABELA_CONTAS[qtdParcela], _qtdParcela);
        valores.put(TABELA_CONTAS[valorConta], _valorConta);
        valores.put(TABELA_CONTAS[dataVencimentoConta], _dataVencimento);
        valores.put(TABELA_CONTAS[dataPagamentoConta], _dataPagamento);

        resultado = db.insert(TABELA_CONTAS[nomeTabelaConta], null, valores);
        db.close();

        if (resultado == -1){
            return "Erro ao inserir a Conta";
        }else{
            return "Conta inserida com sucesso!";
        }
    }

    public Cursor carregaDados(){
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM "+TABELA_CONTAS[nomeTabelaConta]+" ORDER BY id",null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public ContasEntity retornaUltimoRegistro(){
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM "+TABELA_CONTAS[nomeTabelaConta]+" ORDER BY id DESC", null);
        if (cursor!=null){
            cursor.moveToFirst();
            String id = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[idContaContas]));
            String tipoContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[tipoContaContas]));
            String anoContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[anoConta]));
            String mesContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[mesConta]));
            String numParcContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[numeroParcela]));
            String qtdParcelaContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[qtdParcela]));
            String valorContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[valorConta]));
            String dataVencimentoContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[dataVencimentoConta]));
            String dataPagamentoContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_CONTAS[dataPagamentoConta]));
            return new ContasEntity(id, tipoContaColuna,anoContaColuna, mesContaColuna, numParcContaColuna, qtdParcelaContaColuna,
                    valorContaColuna, dataVencimentoContaColuna, dataPagamentoContaColuna );
        }
        db.close();
        return null;
    }


    public boolean delete (int id){
        db = banco.getWritableDatabase();
        return db.delete(TABELA_CONTAS[nomeTabelaConta],"id=?", new String[]{id + ""})>0;
    }
}
