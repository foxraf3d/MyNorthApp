package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import DAL.CriaBanco;
import Entities.TipoContaEntity;
import helper.Define_Tabela;

import static helper.Define_Tabela.TABELA_TIPOCONTAS;
import static helper.Define_Tabela.idConta;
import static helper.Define_Tabela.nomeTabelaConta;
import static helper.Define_Tabela.tipoConta;

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

    public Cursor carregaDados(){
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM "+TABELA_TIPOCONTAS[nomeTabelaConta]+" ORDER BY id", null);
        if (cursor!=null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public TipoContaEntity retornaUltimoRegistro(){
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM "+TABELA_TIPOCONTAS[nomeTabelaConta]+" ORDER BY id DESC", null);
        if (cursor!=null){
            cursor.moveToFirst();
            String id = cursor.getString(cursor.getColumnIndex(TABELA_TIPOCONTAS[idConta]));
            String tipoContaColuna = cursor.getString(cursor.getColumnIndex(TABELA_TIPOCONTAS[tipoConta]));
            return new TipoContaEntity(id, tipoContaColuna);
        }
        db.close();
        return null;
    }

    public boolean delete(int id){
        db = banco.getWritableDatabase();
        return db.delete(TABELA_TIPOCONTAS[nomeTabelaConta], "id=?", new String[]{id + ""}) > 0;
    }

}
