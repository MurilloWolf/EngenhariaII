package models;

import java.sql.Timestamp;

public class Compra {
    
    private int cod;
    private Timestamp data;
    private double valor;
    private int nf;
    private int cod_fornecedor;

    public Compra(int cod, Timestamp data, double valor, int nf, int cod_fornecedor) {
        this.cod = cod;
        this.data = data;
        this.valor = valor;
        this.nf = nf;
        this.cod_fornecedor = cod_fornecedor;
    }

    public Compra(Timestamp data, double valor, int nf, int cod_fornecedor) {
        this.data = data;
        this.valor = valor;
        this.nf = nf;
        this.cod_fornecedor = cod_fornecedor;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNf() {
        return nf;
    }

    public void setNf(int nf) {
        this.nf = nf;
    }

    public int getCod_fornecedor() {
        return cod_fornecedor;
    }

    public void setCod_fornecedor(int cod_fornecedor) {
        this.cod_fornecedor = cod_fornecedor;
    }
    
    
    
}
