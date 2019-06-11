package models.dao;

import db.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import models.Fornecedor;
import models.Produto;

public class DaoComprarProdutos {
    
    public boolean RegistrarCompra(ObservableList <Produto> lista, int codFor) throws SQLException
    {
        boolean flag1, flag2, flag3;
        flag1=flag2=flag3=true;
        double aux =0 ;
        int aux2 = 0;
        for(int i=0; i < lista.size(); i++)
        {
            aux +=lista.get(i).getPreco();
        }
        try {
            Banco.conectar();
            Banco.con.getConnect().setAutoCommit(false);
            
            String sql = "insert into Compra (com_data, com_valorTotal, com_for_cod) values (SYSDATE(), %1, %2);";
            sql = sql.replace("%1", aux+"");
            sql = sql.replace("%2", codFor+"");
            System.out.println("Compra insere:"+sql);
            flag1=Banco.con.manipular(sql);
            
            for(int i=0; i < lista.size(); i++)
            {
                String sql2 = "insert into Compra_Produto (Compra_com_cod, Produto_pro_cod, com_pro_quatidade, com_pro_valor) values (%1, %2, %3, %4)";
                sql2 = sql2.replace("%1", Banco.con.getMaxPK("Compra", "com_cod")+"");
                sql2 = sql2.replace("%2", lista.get(i).getCod()+"");
                sql2 = sql2.replace("%3", lista.get(i).getQuantidade()+"");
                sql2 = sql2.replace("%4", lista.get(i).getPreco()+"");
                    
                System.out.println("Compra_Produto insere:"+sql2);
                flag2=Banco.con.manipular(sql2);
            }
            for(int i=0; i < lista.size(); i++)
            {
                Produto p =new Produto();
                p = p.buscarPorCodigo(lista.get(i).getCod());
                aux2 = p.getQuantidade() + lista.get(i).getQuantidade();
                String sql3 = "update Produto set pro_quantidade="+aux2+" where pro_cod="+lista.get(i).getCod();
                System.out.println("Compra_Produto insere:"+sql3);
                flag3=Banco.con.manipular(sql3);
            }
            Banco.con.getConnect().commit();
        } catch (SQLException ex) 
        {
            Banco.con.getConnect().rollback();
            return false;
        }
        finally
        {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
            
        return flag1 && flag2 && flag3;
    }
    
}
