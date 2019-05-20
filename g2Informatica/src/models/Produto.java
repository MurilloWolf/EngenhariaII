package models;

public class Produto 
{
    
    private int cod;
    private String nome;
    private double preco;

    public Produto(int cod, String nome, double preco) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
    }
    
  

    public Produto(String nome, double preco ) {
        this.nome = nome;
        this.preco = preco;
    }
   

    public Produto() {
        this.nome = "";
        this.preco = -1;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
 
   
    @Override
    public String toString()
    {
        return nome;
    }
}
