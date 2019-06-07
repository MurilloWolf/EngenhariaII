/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.util.ArrayList;
import javafx.collections.ObservableList;
import models.Oferta;
import models.OfertaProduto;
import models.OfertaServico;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

    public boolean salvarOferta(ObservableList<OfertaProduto> listaOP, ObservableList<OfertaServico>  listaOS, LocalDate dataInicial, LocalDate dataFinal, String nome ) {
        Timestamp dtI = toTimestamp(dataInicial);
        Timestamp dtF = toTimestamp(dataFinal);
        
        
        Oferta ofe = new Oferta(listaOP,listaOS,dtI,dtF,nome);
        
        boolean resultado;
        try{
            resultado = ofe.salvar();
        }catch(Exception e){
            
            resultado = false;
        }
        return resultado;
    }

    public Timestamp toTimestamp(LocalDate localDate) {
     Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Timestamp timeStamp = new Timestamp(date.getTime());
    return timeStamp;
  }
}
