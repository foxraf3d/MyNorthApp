package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import DAL.CriaBanco;
import Entities.UsuarioEntity;
import Intermediate.Define_Tabela;

public class UsuarioController{

    private SQLiteDatabase db;
    private CriaBanco banco;
    private Cursor cursor;
    private UsuarioEntity usuarioEntity = new UsuarioEntity();
    private Define_Tabela dt = new Define_Tabela();

    public UsuarioController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserirUsuario(String login, String senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(banco.LOGIN, login);
        valores.put(banco.SENHA, senha);

        resultado = db.insert(banco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir usuário!";
        else
            return "Usuário inserido com sucesso!";
    }

    public Cursor carregaDados(){
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM usuario ORDER BY id", null);
        if (cursor!=null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor consultaUsuarioPorID(String id){
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM usuario WHERE id  = "+id+"", null);
        db.close();
        return cursor;
    }
}

