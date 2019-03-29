package Intermediate;

public class Define_Tabela {

    public static String[] TABELA_USUARIO;
    public static final int idUsuario = 0;
    public static final int login = 1;
    public static final int senha = 2;
    public static final int nomeTabelaUsuario = 3;

    public static String[] TABELA_TIPOCONTAS;
    public static final int idConta = 0;
    public static final int tipoConta = 1;
    public static final int nomeTabelaConta = 2;

    public static String[] TABELA_CONTASPENDENTES;
    public static final int idContaPendente = 0;
    public static final int tipoContaPendente = 1;
    public static final int dataVencimento = 2;
    public static final int dataPagamento = 3;
    public static final int valorContaPendente = 4;
    public static final int nomeTabelaContaPendente = 5;

    public Define_Tabela() {
        //Tabela Usuário
        TABELA_USUARIO = new String[4];
        TABELA_USUARIO[idUsuario] = "id";
        TABELA_USUARIO[login] = "login";
        TABELA_USUARIO[senha] = "senha";
        TABELA_USUARIO[nomeTabelaUsuario] = "usuario";

        //Tabela Contas
        TABELA_TIPOCONTAS = new String [3];
        TABELA_TIPOCONTAS[idConta] = "id";
        TABELA_TIPOCONTAS[tipoConta] = "tipoConta";
        TABELA_TIPOCONTAS[nomeTabelaConta] = "tipoContas";

        //Tabela Contas Pendentes
        TABELA_CONTASPENDENTES = new String [6];
        TABELA_CONTASPENDENTES[idContaPendente] = "id";
        TABELA_CONTASPENDENTES[tipoContaPendente] = "tipoContaPendente";
        TABELA_CONTASPENDENTES[dataVencimento] = "dataVencimento";
        TABELA_CONTASPENDENTES[dataPagamento] = "dataPagamento";
        TABELA_CONTASPENDENTES[valorContaPendente] = "valorContaPendente";
        TABELA_CONTASPENDENTES[nomeTabelaContaPendente] = "contasPendentes";

    }
}
