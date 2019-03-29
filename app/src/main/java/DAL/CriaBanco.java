package DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Intermediate.Define_Tabela;

import static Intermediate.Define_Tabela.*;

public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "MyNorth.db";
    public static final int VERSAO = 1;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlUsuario = queryTabelaUsuario();
        db.execSQL(sqlUsuario);

        String sqlTipoContas = queryTabelaContas();
        db.execSQL(sqlTipoContas);

        String sqlContasPendentes = queryTabelaContasPendentes();
        db.execSQL(sqlContasPendentes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropTableUsuario = dropTable(Define_Tabela.TABELA_USUARIO[nomeTabelaUsuario]);
        db.execSQL(sqlDropTableUsuario);

        String sqlDropTableContas = dropTable(Define_Tabela.TABELA_TIPOCONTAS[nomeTabelaConta]);
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
        String sql = "CREATE TABLE IF NOT EXISTS "+Define_Tabela.TABELA_TIPOCONTAS[nomeTabelaConta]+"(" +
                ""+Define_Tabela.TABELA_TIPOCONTAS[idConta]+" integer primary key autoincrement," +
                ""+Define_Tabela.TABELA_TIPOCONTAS[tipoConta]+" text)";
        return sql;
    }

    private String queryTabelaContasPendentes() {
        String sql = "CREATE TABLE IF NOT EXISTS "+Define_Tabela.TABELA_CONTASPENDENTES[nomeTabelaContaPendente]+"(" +
                ""+Define_Tabela.TABELA_CONTASPENDENTES[idContaPendente]+" integer primary key autoincrement," +
                ""+Define_Tabela.TABELA_CONTASPENDENTES[tipoContaPendente]+" text," +
                ""+Define_Tabela.TABELA_CONTASPENDENTES[dataVencimento]+" text" +
                ""+Define_Tabela.TABELA_CONTASPENDENTES[dataPagamento]+" text" +
                ""+Define_Tabela.TABELA_CONTASPENDENTES[valorContaPendente]+" text)";
        return sql;
    }

    private String dropTable(String nomeTabela){
        String sql = "DROP TABLE IF EXISTS " + nomeTabela;
        return sql;
    }





}
