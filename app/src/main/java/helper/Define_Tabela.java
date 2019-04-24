package helper;

public class Define_Tabela {

    public static String[] TABELA_USUARIO;
    public static final int idUsuario = 0;
    public static final int login = 1;
    public static final int senha = 2;
    public static final int nomeTabelaUsuario = 3;

    public static String[] TABELA_TIPOCONTAS;
    public static final int idConta = 0;
    public static final int tipoConta = 1;
    public static final int nomeTabelaTipoConta = 2;

    public static String[] TABELA_CONTAS;
    public static final int idContaContas = 0;
    public static final int tipoContaContas = 1;
    public static final int anoConta = 2;
    public static final int mesConta = 3;
    public static final int numeroParcela = 4;
    public static final int qtdParcela = 5;
    public static final int valorConta = 6;
    public static final int dataVencimentoConta = 7;
    public static final int dataPagamentoConta = 8;
    public static final int nomeTabelaConta = 9;

    public Define_Tabela() {
        //Tabela Usuário
        TABELA_USUARIO = new String[4];
        TABELA_USUARIO[idUsuario] = "id";
        TABELA_USUARIO[login] = "login";
        TABELA_USUARIO[senha] = "senha";
        TABELA_USUARIO[nomeTabelaUsuario] = "usuario";

        //Tabela Tipo de Contas
        TABELA_TIPOCONTAS = new String [3];
        TABELA_TIPOCONTAS[idConta] = "id";
        TABELA_TIPOCONTAS[tipoConta] = "tipoConta";
        TABELA_TIPOCONTAS[nomeTabelaTipoConta] = "tipoContas";

        //Tabela Contas
        TABELA_CONTAS = new String [10];
        TABELA_CONTAS[idContaContas] = "id";
        TABELA_CONTAS[tipoContaContas] = "tipoContaContas";
        TABELA_CONTAS[anoConta] = "anoConta";
        TABELA_CONTAS[mesConta] = "mesConta";
        TABELA_CONTAS[numeroParcela] = "numeroParcela";
        TABELA_CONTAS[qtdParcela] = "qtdParcela";
        TABELA_CONTAS[valorConta] = "valorConta";
        TABELA_CONTAS[dataVencimentoConta] = "dataVencimentoConta";
        TABELA_CONTAS[dataPagamentoConta] = "dataPagamentoConta";
        TABELA_CONTAS[nomeTabelaConta] = "contas";

    }
}
