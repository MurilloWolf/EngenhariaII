package models;

public class Pessoa 
{
    private String nome;
    private String documento;
    private String cep;
    private String endereco;
    private String uf;
    private String fone;
    private String cidade;
    private int cod;

    public Pessoa(int cod, String nome, String documento, String cep, String endereco, String uf, String fone, String cidade) {
        this.cod = cod;
        this.nome = nome;
        this.documento = documento;
        this.cep = cep;
        this.endereco = endereco;
        this.uf = uf;
        this.fone = fone;
        this.cidade = cidade;
    }

    public Pessoa(String nome, String documento, int cod) {
        this.nome = nome;
        this.documento = documento;
        this.cod = cod;
    }

    public Pessoa() {
    }
    

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    
    
    
}
