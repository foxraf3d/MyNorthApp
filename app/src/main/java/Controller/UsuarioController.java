package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;

import DAL.CriaBanco;
import Entities.UsuarioEntity;
import Intermediate.Define_Tabela;

public class UsuarioController{

    private SQLiteDatabase db;
    private CriaBanco banco;
    private UsuarioEntity usuarioEntity = new UsuarioEntity();
    private Define_Tabela dt = new Define_Tabela();

    public UsuarioController(Context context) {
        banco = new CriaBanco(context);
        banco.TABELA = dt.setNOME_TABELA("usuario");
    }

    public String inserirUsuario(String login, String senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(usuarioEntity.login, login);
        valores.put(usuarioEntity.senha, senha);

        resultado = db.insert(banco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir usuário!";
        else
            return "Usuário inserido com sucesso!";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {
                usuarioEntity.id,
                usuarioEntity.login,
                usuarioEntity.senha};

        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null);
        if (cursor!=null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}

