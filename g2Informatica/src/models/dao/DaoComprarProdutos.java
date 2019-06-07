/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao;

import db.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Fornecedor;
import models.Produto;

/**
 *
 * @author HafPc
 */
public class DaoComprarProdutos {
    
    public boolean RegistrarCompra(ArrayList <Produto>lista, Fornecedor fo) throws SQLException
    {
        boolean flag =true;
        double aux =0;
        for(int i=0; i < lista.size(); i++)
        {
            aux +=lista.get(i).getPreco();
        }
        try {
            Banco.con.getConnect().setAutoCommit(false);
            
            String sql = "insert into Compra (com_data, com_valorTotal, com_for_cod) values (SYSDATE(), %1, %2";
            sql = sql.replace("$1", aux+"");
            sql = sql.replace("$2", fo.getCodigo()+"");
            System.out.println("Compra insere:"+sql);
            Banco.con.manipular(sql);
            
            for(int i=0; i < lista.size(); i++)
            {
                String sql2 = "insert into Compra_Produto (Compra_com_cod, Produto_pro_cod, com_pro_quatidade, com_pro_valor) values (%1, %2, %3, %4)";
                sql = sql2.replace("$1", Banco.con.getMaxPK("Compra", "com_cod")+"");
                sql = sql2.replace("$2", lista.get(i).getCod()+"");
                sql = sql2.replace("$3", lista.get(i).getQuantidade()+"");
                sql = sql2.replace("$4", lista.get(i).getPreco()+"");
                    
                System.out.println("Compra_Produto insere:"+sql);
                Banco.con.manipular(sql2);
            }

            Banco.con.getConnect().commit();
        } catch (SQLException ex) 
        {
            Banco.con.getConnect().rollback();
            flag= false;
        }
        finally
        {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
            
        return flag;
    }
    
}
