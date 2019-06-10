/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.util.ArrayList;

/**
 *
 * @author wolf
 */
public class OfertaServico {
   
   private double preco;
   private Servico servico;
   private String descricao;

    public OfertaServico(Servico servico, double preco) {
        this.servico = servico;
        this.preco = preco;
        this.descricao = servico.getDescricao();
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

    /* ========================================== SQL ========================================== */
    public boolean salvarOferta(int codigo){
     boolean resultado = false;
     String sql="";
     
     try{
         sql = "insert into Oferta_Servico (Servico_ser_cod, Oferta_ofe_cod, ofe_ser_valor) values ($1,$2,$3);";
         sql = sql.replace("$1",servico.getCodigo()+"" );
         sql = sql.replace("$2",codigo+"" );
         sql = sql.replace("$3",preco+"");
         
         
         resultado = Banco.con.manipular(sql);
         
     }catch(Exception e ){
         System.out.println("Sql:"+e.getMessage());
     }
     
     return resultado ;
    }

    public boolean alterarOferta(int codigo) {
        boolean resultado = false;
        String sql="";
     
        try{
            sql = "update Oferta_Servico set Servico_ser_cod = $1, ofe_ser_valor = $2  where Oferta_ofe_cod =  "+codigo+";";
            sql = sql.replace("$1",servico.getCodigo()+"" );
            sql = sql.replace("$2",preco+"");

            System.out.println("sql Servico:"+sql);

            resultado = Banco.con.manipular(sql);

        }catch(Exception e ){
            System.out.println("Sql:"+e.getMessage());
        }

        return resultado ;

    }
    
    
    public static boolean deletarOferta(int codigo){
        boolean resultado = false;
        String sql="";

        try{
            sql = "delete from Oferta_Servico where Oferta_ofe_cod ="+codigo+";";
     
            System.out.println("sql Produto:"+sql);
            resultado = Banco.con.manipular(sql);

        }catch(Exception e ){
            System.out.println("Sql:"+e.getMessage());
        }

        return resultado ;
    }
    
   
   
}
