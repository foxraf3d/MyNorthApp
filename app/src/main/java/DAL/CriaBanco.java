package DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Entities.UsuarioEntity;
import Intermediate.Define_Tabela;

public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "MyNorth.db";
    public static String TABELA = Define_Tabela.getNOME_TABELA();
    //public static String QUERY = Define_Tabela.getQuerySql();
    public static String ID = "_id";
    public static String LOGIN = "login";
    public static String SENHA = "senha";
//    public UsuarioEntity usuarioEntity = new UsuarioEntity();
    public static final int VERSAO = 1;


    public CriaBanco(Context context) {
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABELA+"(" +
                ""+ID+" integer primary key autoincrement," +
                ""+LOGIN+" text," +
                ""+SENHA+" text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
