package Entities;

public class ContasEntity {

    private String idConta;
    private String anoConta;
    private String mesConta;
    private String numeroParcela;
    private String valorConta;
    private String dataVencimento;
    private String dataPagamento;

    public ContasEntity(String idConta, String anoConta, String mesConta, String numeroParcela, String valorConta, String dataVencimento, String dataPagamento) {
        this.idConta = idConta;
        this.anoConta = anoConta;
        this.mesConta = mesConta;
        this.numeroParcela = numeroParcela;
        this.valorConta = valorConta;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
