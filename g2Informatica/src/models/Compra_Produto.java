package models;

public class Compra_Produto {
    
    private int cod_compra;
    private int cod_produto;
    private int qtde;
    private int valor;

    public Compra_Produto(int cod_compra, int cod_produto, int qtde, int valor) {
        this.cod_compra = cod_compra;
        this.cod_produto = cod_produto;
        this.qtde = qtde;
        this.valor = valor;
    }

    public int getCod_compra() {
        return cod_compra;
    }

    public void setCod_compra(int cod_compra) {
        this.cod_compra = cod_compra;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    
}
