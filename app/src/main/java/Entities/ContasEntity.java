package Entities;

public class ContasEntity {

    private String idConta;
    private String tipoContasConta;
    private String anoConta;
    private String mesConta;
    private String numeroParcela;
    private String qtdParcela;
    private String valorConta;
    private String dataVencimento;
    private String dataPagamento;

    public ContasEntity(String idConta, String tipoContasConta, String anoConta, String mesConta, String numeroParcela, String qtdParcela, String valorConta, String dataVencimento) {
        this.idConta = idConta;
        this.tipoContasConta = tipoContasConta;
        this.anoConta = anoConta;
        this.mesConta = mesConta;
        this.numeroParcela = numeroParcela;
        this.qtdParcela = qtdParcela;
        this.valorConta = valorConta;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public String getTipoContasConta() {
        return tipoContasConta;
    }

    public void setTipoContasConta(String tipoContasConta) {
        this.tipoContasConta = tipoContasConta;
    }

    public String getAnoConta() {
        return anoConta;
    }

    public void setAnoConta(String anoConta) {
        this.anoConta = anoConta;
    }

    public String getMesConta() {
        return mesConta;
    }

    public void setMesConta(String mesConta) {
        this.mesConta = mesConta;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(String qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public String getValorConta() {
        return valorConta;
    }

    public void setValorConta(String valorConta) {
        this.valorConta = valorConta;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
