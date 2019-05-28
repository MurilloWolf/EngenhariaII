/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.sql.ResultSet;

/**
 *
 * @author wolf
 */
public class OfertaProduto {
    private Produto produto;
    private double preco;

    public OfertaProduto(Produto produto, double preco) {
        this.produto = produto;
        this.preco = preco;
    }

    
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
    /* ========================================== SQL ========================================== */
    public boolean salvarOferta(int codigo){
     boolean resultado = false;
     String sql="";
     
     try{
         sql = "insert into Oferta_Produto (Produto_pro_cod, Oferta_ofe_cod, ofe_ser_valor) values ($1,$2,$3);";
         sql = sql.replace("$1",produto.getCod()+"" );
         sql = sql.replace("$2",codigo+"" );
         sql = sql.replace("$3",preco+"");
         
         
         resultado = Banco.con.manipular(sql);
         
     }catch(Exception e ){
         System.out.println("Sql:"+e.getMessage());
     }
     
     return resultado ;
    }
    
}
