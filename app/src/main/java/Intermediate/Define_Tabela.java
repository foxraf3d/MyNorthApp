package Intermediate;

public class Define_Tabela {

    public static String NOME_TABELA;
    public static String QUERY_SQL;

    public static String getQuerySql() {
        return QUERY_SQL;
    }

    public static String setQuerySql(String querySql) {
       return QUERY_SQL = querySql;
    }

    public static String getNOME_TABELA() {
        return NOME_TABELA;
    }

    public static String setNOME_TABELA(String nomeTabela) {
        return NOME_TABELA = nomeTabela;
    }


}
