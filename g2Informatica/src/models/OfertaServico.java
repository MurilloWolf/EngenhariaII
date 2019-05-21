/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author wolf
 */
public class OfertaServico {
   
   private double preco;
   private Servico servico;
   

    public OfertaServico(Servico servico, double preco) {
        this.servico = servico;
        this.preco = preco;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    
    
   
   
}
