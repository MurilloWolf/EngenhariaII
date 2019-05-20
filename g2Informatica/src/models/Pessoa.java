package models;

abstract public class Pessoa 
{
    private int cod;
    private String nome;

    public Pessoa(int cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa() {
        this.nome="";
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
    
    @Override
    public String toString() {
        return nome;
    }
}