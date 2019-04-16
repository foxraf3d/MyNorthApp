package DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import helper.Define_Tabela;

import static helper.Define_Tabela.*;

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

        String sqlTipoContas = queryTabelaTipoContas();
        db.execSQL(sqlTipoContas);

        String sqlContas = queryTabelaContas();
        db.execSQL(sqlContas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropTableUsuario = dropTable(Define_Tabela.TABELA_USUARIO[nomeTabelaUsuario]);
        db.execSQL(sqlDropTableUsuario);

        String sqlDropTableContas = dropTable(Define_Tabela.TABELA_TIPOCONTAS[nomeTabelaTipoConta]);
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

    private String queryTabelaTipoContas(){
        String sql = "CREATE TABLE IF NOT EXISTS "+Define_Tabela.TABELA_TIPOCONTAS[nomeTabelaTipoConta]+"(" +
                ""+Define_Tabela.TABELA_TIPOCONTAS[idConta]+" integer primary key autoincrement," +
                ""+Define_Tabela.TABELA_TIPOCONTAS[tipoConta]+" text)";
        return sql;
    }

    private String queryTabelaContas() {
        String sql = "CREATE TABLE IF NOT EXISTS "+Define_Tabela.TABELA_CONTAS[nomeTabelaConta]+"(" +
                ""+Define_Tabela.TABELA_CONTAS[idContaContas]+" integer primary key autoincrement," +
                ""+Define_Tabela.TABELA_CONTAS[anoConta]+" text," +
                ""+Define_Tabela.TABELA_CONTAS[mesConta]+" text," +
                ""+Define_Tabela.TABELA_CONTAS[numeroParcela]+" text," +
                ""+Define_Tabela.TABELA_CONTAS[valorConta]+" text," +
                ""+Define_Tabela.TABELA_CONTAS[dataVencimentoConta]+" text,"+
                ""+Define_Tabela.TABELA_CONTAS[dataPagamentoConta]+" text)";
        return sql;
    }

/*    private String idConta;
    private String anoConta;
    private String mesConta;
    private String numeroParcela;
    private String valorConta;
    private String dataVencimento;
    private String dataPagamento;*/


    private String dropTable(String nomeTabela){
        String sql = "DROP TABLE IF EXISTS " + nomeTabela;
        return sql;
    }





}
