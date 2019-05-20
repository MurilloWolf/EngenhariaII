package models;

public class Funcionario extends Pessoa
{
    private String tipo;
    private String carteiraMotorista;
    private String cpf;
    private String fone;
    private int usr_cod;

    public Funcionario(String tipo, String carteiraMotorista, String cpf, String fone, int usr_cod, int cod, String nome) {
        super(cod, nome);
        this.tipo = tipo;
        this.carteiraMotorista = carteiraMotorista;
        this.cpf = cpf;
        this.fone = fone;
        this.usr_cod = usr_cod;
    }

    public Funcionario(String tipo, String carteiraMotorista, String cpf, String fone, int usr_cod, String nome) {
        super(nome);
        this.tipo = tipo;
        this.carteiraMotorista = carteiraMotorista;
        this.cpf = cpf;
        this.fone = fone;
        this.usr_cod = usr_cod;
    }
    
    public Funcionario() {
        super(-1, "");
        this.tipo = "";
        this.carteiraMotorista = "";
        this.cpf = "";
        this.fone = "";
        this.usr_cod = -1;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCarteiraMotorista() {
        return carteiraMotorista;
    }

    public void setCarteiraMotorista(String carteiraMotorista) {
        this.carteiraMotorista = carteiraMotorista;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public int getUsr_cod() {
        return usr_cod;
    }

    public void setUsr_cod(int usr_cod) {
        this.usr_cod = usr_cod;
    }
}
