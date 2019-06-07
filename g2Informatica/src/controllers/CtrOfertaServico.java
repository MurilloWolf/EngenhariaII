/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.OfertaServico;
import models.Produto;
import models.Servico;

/**
 *
 * @author wolf
 */
public class CtrOfertaServico {
     public OfertaServico novaOfertaServico(Servico s, double preco){
        return new OfertaServico(s,preco);
    }
}
