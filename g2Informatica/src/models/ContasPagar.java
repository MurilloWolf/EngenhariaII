package models;

import java.util.Date;


public class ContasPagar {
    
    private double valorPago;
    private double valorConta;
    private Date dataConta;
    private Date dataVencimento;
    private Date dataPagamento;

    public ContasPagar(double valorPago, double valorConta, Date dataConta, Date dataVencimento, Date dataPagamento) {
        this.valorPago = valorPago;
        this.valorConta = valorConta;
        this.dataConta = dataConta;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public ContasPagar() {
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

    public Date getDataConta() {
        return dataConta;
    }

    public void setDataConta(Date dataConta) {
        this.dataConta = dataConta;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    
    
    
    
}
