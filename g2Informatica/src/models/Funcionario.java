package models;

public class Funcionario extends Pessoa
{
    
    private String loguin;
    private String senha;
    private String dt_admi;
    private String dt_demi;
    private int nivel;

    public Funcionario() {
    }

    public Funcionario(int cod, String nome, String documento) {
        super(nome, documento, cod);
    }

    public Funcionario(String loguin, String senha, String dt_admi, String dt_demi, int cod, String nome, String documento, String cep, String endereco, String uf, String fone, String cidade, int nivel) {
        super(cod, nome, documento, cep, endereco, uf, fone, cidade);
        this.loguin = loguin;
        this.senha = senha;
        this.dt_admi = dt_admi;
        this.dt_demi = dt_demi;
        this.nivel = nivel;
    }

    public Funcionario(String dt_admi, String dt_demi, int cod, String nome, String documento, String cep, String endereco, String uf, String fone, String cidade, int nivel) {
        super(cod, nome, documento, cep, endereco, uf, fone, cidade);
        this.dt_admi = dt_admi;
        this.dt_demi = dt_demi;
        this.nivel = nivel;
    }

    public String getLoguin() {
        return loguin;
    }

    public void setLoguin(String loguin) {
        this.loguin = loguin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDt_admi() {
        return dt_admi;
    }

    public void setDt_admi(String dt_admi) {
        this.dt_admi = dt_admi;
    }

    public String getDt_demi() {
        return dt_demi;
    }

    public void setDt_demi(String dt_demi) {
        this.dt_demi = dt_demi;
    }

    
}
