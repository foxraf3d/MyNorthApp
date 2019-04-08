package Entities;

public class TipoContaEntity {

    private String id;
    private String tipoConta;

    public TipoContaEntity(String id, String tipoConta) {
        this.setId(id);
        this.setTipoConta(tipoConta);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
}
