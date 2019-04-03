package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import DAL.CriaBanco;
import Helper.Define_Tabela;

import static Helper.Define_Tabela.TABELA_TIPOCONTAS;
import static Helper.Define_Tabela.nomeTabelaConta;
import static Helper.Define_Tabela.tipoConta;

public class TipoContaController {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private Cursor cursor;
    private Define_Tabela dt = new Define_Tabela();


    public TipoContaController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserirTipoConta(String _tipoConta){
        ContentValues valores;
        long resultado;

        db = banco.getReadableDatabase();
        valores = new ContentValues();
        valores.put(TABELA_TIPOCONTAS[tipoConta], _tipoConta);

        resultado = db.insert(TABELA_TIPOCONTAS[nomeTabelaConta], null, valores);
        db.close();

        if (resultado == -1){
            return "Erro ao inserir tipo de conta";
        }else{
            return "Tipo de Conta inserido com sucesso!";
        }
    }
}
