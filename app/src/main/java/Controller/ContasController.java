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
import static helper.Define_Tabela.mesConta;
import static helper.Define_Tabela.nomeTabelaConta;
import static helper.Define_Tabela.numeroParcela;
import static helper.Define_Tabela.valorConta;

public class ContasController {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private Cursor cursor;

    public ContasController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserirConta(String _anoConta, String _mesConta, String _numeroParcela, String _valorConta, String _dataVencimento, String _dataPagamento){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getReadableDatabase();
        valores.put(TABELA_CONTAS[anoConta], _anoConta);
        valores.put(TABELA_CONTAS[mesConta], _mesConta);
        valores.put(TABELA_CONTAS[numeroParcela], _numeroParcela);
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
}
