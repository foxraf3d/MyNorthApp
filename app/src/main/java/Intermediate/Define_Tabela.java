package Intermediate;

public class Define_Tabela {

    public static String[] TABELA_USUARIO;
    public static String[] TABELA_CONTAS;

    public Define_Tabela() {
        //Tabela Usuário
        TABELA_USUARIO = new String[4];
        TABELA_USUARIO[0] = "id";
        TABELA_USUARIO[1] = "login";
        TABELA_USUARIO[2] = "senha";
        TABELA_USUARIO[3] = "usuario";

        //Tabela Contas
        TABELA_CONTAS = new String [3];
        TABELA_CONTAS[0] = "id";
        TABELA_CONTAS[1] = "nomeConta";
        TABELA_CONTAS[2] = "contas";

    }
}
