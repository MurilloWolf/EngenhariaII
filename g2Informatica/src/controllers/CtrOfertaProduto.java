/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.OfertaProduto;
import models.Produto;

/**
 *
 * @author wolf
 */
public class CtrOfertaProduto {
    public OfertaProduto novaOfertaProduto(Produto p, double preco){
        return new OfertaProduto(p,preco);
    }
}
