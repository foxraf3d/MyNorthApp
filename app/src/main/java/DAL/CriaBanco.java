package DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import Entities.UsuarioEntity;
import Intermediate.Define_Tabela;

import static android.content.ContentValues.TAG;

public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "MyNorth.db";
    public static String TABELA = "usuario";
    public static String ID = "id";
    public static String LOGIN = "login";
    public static String SENHA = "senha";
    public static final int VERSAO = 1;

    //campos tabela usuário
    public int idUsuario = 0;
    public int login = 1;
    public int senha = 2;
    public int nomeTabelaUsuario = 3;

    //Campos tabela conta
    public int idConta = 0;
    public int nomeConta = 1;
    public int nomeTabelaConta = 2;


    public CriaBanco(Context context) {
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlUsuario = queryTabelaUsuario();
        db.execSQL(sqlUsuario);

        String sqlContas = queryTabelaContas();
        db.execSQL(sqlContas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL("DROP TABLE IF EXISTS " + TABELA);

        String sqlDropTableUsuario = dropTable(Define_Tabela.TABELA_USUARIO[nomeTabelaUsuario]);
        db.execSQL(sqlDropTableUsuario);

        String sqlDropTableContas = dropTable(Define_Tabela.TABELA_CONTAS[nomeTabelaConta]);
        db.execSQL(sqlDropTableContas);

        onCreate(db);
    }

    private String queryTabelaUsuario(){
        String sql = "CREATE TABLE IF NOT EXISTS "+Define_Tabela.TABELA_USUARIO[nomeTabelaUsuario]+"(" +
                ""+Define_Tabela.TABELA_USUARIO[idUsuario]+" integer primary key autoincrement," +
                ""+Define_Tabela.TABELA_USUARIO[login]+" text," +
                ""+Define_Tabela.TABELA_USUARIO[senha]+" text)";
        return sql;
    }

    private String queryTabelaContas(){
        String sql = "CREATE TABLE IF NOT EXISTS "+Define_Tabela.TABELA_CONTAS[nomeTabelaConta]+"(" +
                ""+Define_Tabela.TABELA_CONTAS[idConta]+" integer primary key autoincrement," +
                ""+Define_Tabela.TABELA_CONTAS[nomeConta]+" text)";
        return sql;
    }

    private String dropTable(String nomeTabela){
        String sql = "DROP TABLE IF EXISTS " + nomeTabela;
        return sql;
    }


}
