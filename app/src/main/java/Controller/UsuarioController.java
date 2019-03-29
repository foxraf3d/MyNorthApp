package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import DAL.CriaBanco;
import Entities.UsuarioEntity;
import Intermediate.Define_Tabela;

import static Intermediate.Define_Tabela.TABELA_USUARIO;
import static Intermediate.Define_Tabela.login;
import static Intermediate.Define_Tabela.nomeTabelaUsuario;
import static Intermediate.Define_Tabela.senha;
import static android.content.ContentValues.TAG;

public class UsuarioController{

    private SQLiteDatabase db;
    private CriaBanco banco;
    private Cursor cursor;
    private Define_Tabela dt = new Define_Tabela();

    private boolean bkupOk = false;

    public UsuarioController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserirUsuario(String _login, String _senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(TABELA_USUARIO[login], _login);
        valores.put(TABELA_USUARIO[senha], _senha);

        resultado = db.insert(TABELA_USUARIO[nomeTabelaUsuario], null, valores);
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

    public void copiaBanco() {
        try{
            SQLiteDatabase writableDatabase = banco.getWritableDatabase();
            String path = writableDatabase.getPath();
            Log.i(TAG, "caminho pro banco "+path);
            File dbFile = new File(path);
            Log.i(TAG, "File criado "+dbFile);
            File bkup = new File(Environment.getExternalStorageDirectory(), dbFile.getName());
            Log.i(TAG, "File dest criado "+bkup);
            copyFile(dbFile, bkup);
            Log.i(TAG, "Copiado com sucesso ");
            bkupOk = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean copyFile(File from, File to) {

        try {

            FileChannel source = new FileInputStream(from).getChannel();
            FileChannel destination = new FileOutputStream(to).getChannel();

            System.out.println("from "+from.getAbsolutePath());
            System.out.println("to   "+to.getAbsolutePath());

            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

