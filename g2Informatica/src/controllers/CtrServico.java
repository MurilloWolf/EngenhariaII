/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Servico;

/**
 *
 * @author wolf
 */
public class CtrServico {
    
    public CtrServico(){
        
    }
    
    public ArrayList<Servico> getAllServicos(){
        return Servico.buscarTodosServicos();
    }
}
