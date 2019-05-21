/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao;

import db.Banco;
import java.sql.SQLException;
import models.Oferta;
import models.OfertaServico;

/**
 *
 * @author wolf
 */
public class DaoOfertaServico {
    
    public boolean salvar(OfertaServico os,Oferta of) throws SQLException{
        String sql = "";
        boolean teste = true;
        try{
            Banco.con.getConnect().setAutoCommit(false);
            
            sql = "insert into Oferta_Servico (Oferta_ofe_cod,Servico_ser_cod,ofe_ser_valor) values ($1,$2,$3) ";
            
            sql = sql.replace("$1", of.getCodigo()+"");
            sql = sql.replace("$2", os.getServico().getCodigo()+"");
            sql = sql.replace("$3", os.getPreco()+"");
            
            
            System.out.println("sql:"+sql);
        }catch(SQLException se){
       
            Banco.con.getConnect().rollback();
            teste = false;
            System.out.println("Erro:"+se.toString());
     
        }finally{
            Banco.con.getConnect().setAutoCommit(true);
        }
 
        return teste;
    }
}
