/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Orcamento;
import models.Produto;
import models.Servico;

/**
 *
 * @author wolf
 */
public class CtrOrcamento {
    
    public boolean salvar (){
        Orcamento o = new Orcamento();
        return o.salvar();
    }

    
    // PRODUTO E SERVICO
    public ArrayList<Produto> getAllProdutos() {
        return Produto.buscarTodosOsProdutos();
    }

    public ArrayList<Servico> getAllServicos() {
        return Servico.buscarTodosServicos();
    }
    
    public ArrayList<Orcamento> getAllOrcamentos(){
        return Orcamento.getTodosOrcamentos();
    }
}
