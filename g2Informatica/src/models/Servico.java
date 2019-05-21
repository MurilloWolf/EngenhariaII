/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author wolf
 */
public class Servico {
    private int codigo;
    private String descricao;
    private double valor;

    public Servico(int codigo) {
        this.codigo = codigo;
        
    }

    
    
    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        codigo = 0;
        this.valor = valor;
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
