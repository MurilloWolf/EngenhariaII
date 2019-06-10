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
import models.Produto;
import models.Servico;

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
        Oferta ofe = new Oferta(codigo);
        return ofe.excluir();
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
    
    public ArrayList<Oferta> getOfertasEncerradas(){
        ArrayList<Oferta> lista = new ArrayList();
        try{
            Oferta of = new Oferta();
            lista = of.buscarOfertasEncerradas();
            
        }catch(Exception ex){
         lista = null;
         
        }
        
        return lista;
        
    }
    
    public ArrayList<Oferta> getOfertasDataFinal(Timestamp dataFinal){
        ArrayList<Oferta> lista = new ArrayList();
        try{
            Oferta of = new Oferta();
            of.setDataFinal(dataFinal);
            lista = of.buscarOfertasDataFinal();
            
        }catch(Exception ex){
         lista = null;
         
        }
        
        return lista;
        
    }
    
    
    // ========================================== CONTROLE DE PRODUTO E SERVICO ========================================== 
    
     public OfertaProduto novaOfertaProduto(Produto p, double preco){
        return new OfertaProduto(p,preco);
    }
     
     
      public OfertaServico novaOfertaServico(Servico s, double preco){
        return new OfertaServico(s,preco);
    }
      
    public ArrayList<Produto> getAllProdutos(){
        return Produto.buscarTodosOsProdutos();
    }
    
    public ArrayList<Servico> getAllServicos(){
        return Servico.buscarTodosServicos();
    }

    public ArrayList<Oferta> getOfertasPorNome(String text) {
        ArrayList<Oferta> lista = new ArrayList();
        if(!text.trim().isEmpty()){
           
            try{
                Oferta of = new Oferta();

                lista = of.buscarOfertasPorNome(text);

            }catch(Exception ex){
             lista = null;

            }
            
        }
        
        
        return lista;        
    }
    
    public boolean alterarOferta(ObservableList<OfertaProduto> listaOP, ObservableList<OfertaServico>  listaOS, LocalDate dataInicial, LocalDate dataFinal, String nome,int codigo ){
        Timestamp dtI = toTimestamp(dataInicial);
        Timestamp dtF = toTimestamp(dataFinal);
        
        
        Oferta ofe = new Oferta(listaOP,listaOS,dtI,dtF,nome);
        ofe.setCodigo(codigo);
        boolean resultado;
        try{
            resultado = ofe.excluir() && ofe.salvar();
        }catch(Exception e){
            
            resultado = false;
        }
        return resultado;        
    }
    
    public boolean alterarOferta(ObservableList<OfertaProduto> listaOP, ObservableList<OfertaServico>  listaOS, Timestamp dataInicial, Timestamp dataFinal, String nome, int codigo ){
       
        
        
        Oferta ofe = new Oferta(listaOP,listaOS,dataInicial,dataFinal,nome);
        ofe.setCodigo(codigo);
        boolean resultado;
        try{
            resultado = ofe.excluir() && ofe.salvar();
        }catch(Exception e){
            
            resultado = false;
        }
        return resultado;        
    }

    public ArrayList<Oferta> getOfertasAbertas() {
        ArrayList<Oferta> lista = new ArrayList();
        try{
            Oferta of = new Oferta();
            lista = of.buscarOfertas("where ofe_dataFinal IS NULL OR ofe_dataFinal >= SYSDATE()");
            
        }catch(Exception ex){
         lista = null;
         
        }
        
        return lista;
    }
}
