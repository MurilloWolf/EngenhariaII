/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Oferta;

/**
 *
 * @author wolf
 */
public class CtrOferta {
    
    
    public ArrayList<Oferta> getAllOfertas(){
        ArrayList<Oferta> lista = new ArrayList();
        try{
            Oferta of = new Oferta();
            lista = of.buscarTodasOfertas();
            
        }catch(Exception ex){
         lista = null;
         
        }
        
        return lista;
    }

    public boolean deletarOferta(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
