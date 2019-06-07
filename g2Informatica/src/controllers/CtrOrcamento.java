/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Orcamento;

/**
 *
 * @author wolf
 */
public class CtrOrcamento {
    
    public boolean salvar (){
        Orcamento o = new Orcamento();
        return o.salvar();
    }
}
