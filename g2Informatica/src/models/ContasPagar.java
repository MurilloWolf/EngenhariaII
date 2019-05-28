package models;

import java.sql.Timestamp;


public class ContasPagar {
    
    private int cod;
    private double valorPago;
    private double valorConta;
    private Timestamp dataConta;
    private Timestamp dataVencimento;
    private Timestamp dataPagamento;

    public ContasPagar(int cod, double valorPago, double valorConta, Timestamp dataConta, Timestamp dataVencimento, Timestamp dataPagamento) {
        this.cod = cod;
        this.valorPago = valorPago;
        this.valorConta = valorConta;
        this.dataConta = dataConta;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public ContasPagar() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorConta() {
        return valorConta;
    }

    public void setValorConta(double valorConta) {
        this.valorConta = valorConta;
    }

    public Timestamp getDataConta() {
        return dataConta;
    }

    public void setDataConta(Timestamp dataConta) {
        this.dataConta = dataConta;
    }

    public Timestamp getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Timestamp dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    
    
    
    
}
