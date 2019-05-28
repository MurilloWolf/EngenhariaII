/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author wolf
 */



public class Oferta {
    private int codigo;
    private Timestamp dataInicio;
    private Timestamp dataFinal;
    private String descicao;
    private Funcionario funcionario;
    private ArrayList<OfertaProduto> listaOfertaProduto;
    private ArrayList<OfertaServico> listaOfertaServico;

    
    
    
    
    
    public ArrayList<OfertaProduto> getListaOfertaProduto() {
        return listaOfertaProduto;
    }

    public void setListaOfertaProduto(ArrayList<OfertaProduto> listaOfertaProduto) {
        this.listaOfertaProduto = listaOfertaProduto;
    }

    public ArrayList<OfertaServico> getListaOfertaServico() {
        return listaOfertaServico;
    }

    public void setListaOfertaServico(ArrayList<OfertaServico> listaOfertaServico) {
        this.listaOfertaServico = listaOfertaServico;
    }
    
    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Timestamp getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Timestamp dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    public boolean salvarOfertaProduto(){
        int cont = 0;
        
        for(OfertaProduto i : listaOfertaProduto){
            if(i.salvarOferta(this.codigo))
                cont++;
        }
        
        if(cont == listaOfertaProduto.size())
            return true;
        
        return false ;
    }
    
    public boolean salvarOfertaServico(){
        int cont = 0;
        
        for(OfertaServico i : listaOfertaServico){
            if(i.salvarOferta(this.codigo))
                cont++;
        }
        
        if(cont == listaOfertaProduto.size())
            return true;
        
        return false ;
    }
    
    public boolean salvar() throws SQLException{
        boolean salvarServico =true ;
        boolean salvarProduto =true ;
        boolean resultadoFinal = false;
        try{
            Banco.con.getConnect().setAutoCommit(false);
        
        
         /*FORMATO DATA YYYY-MM-DD hh:mm:ss*/
           

           String sql="insert into Oferta (ofe_cod,ofe_dataInicio,ofe_dataFinal,ofe_desc,Funcionario_fun_cod) values ($1,'$2','$3','$4',$5);";
           sql = sql.replace("$1",this.codigo+"" );
           sql = sql.replace("$2",this.getDataInicio().toLocalDateTime().toString() );
           sql = sql.replace("$3",this.getDataFinal().toLocalDateTime().toString());
           sql = sql.replace("$4",this.descicao );
           sql = sql.replace("$5",this.funcionario.getCod()+"" );

            System.out.println("Sql: "+sql);
            resultadoFinal = Banco.con.manipular(sql);
            
            if(listaOfertaServico!=null)
                salvarServico = salvarOfertaServico();

            if(listaOfertaProduto!=null)
                salvarProduto = salvarOfertaProduto();
            
            
            Banco.con.getConnect().commit();
        }catch(Exception e){
            
            Banco.con.getConnect().rollback();
            
        }finally{
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return salvarProduto && salvarServico && resultadoFinal; 
    }
    
    
    public boolean alterar (){
        boolean resultado = true ;
        
        
        return resultado ;
    }
}
