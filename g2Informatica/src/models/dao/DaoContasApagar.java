
package models.dao;

import db.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import models.ContasPagar;

public class DaoContasApagar {
    
    public boolean pagar(ContasPagar p, int aux) throws SQLException
    {
        boolean flag = true;
        String sql ="";
        Banco.conectar();
        try {
            Banco.con.getConnect().setAutoCommit(false);
            if (aux == 1) {
                sql = "update Contas_Pagar set conp_valorPago=$1, conp_dataPagamento='" + p.getDataPagamento() + "', conp_status='$3' where conp_cod=" + p.getCod();
                sql = sql.replace("$1", p.getValorPago() + "");
                sql = sql.replace("$3", "pago");
                System.out.println("contas ALTERAR: " + sql);
            } else if (aux == 2) {
                sql = "update Contas_Pagar set conp_valorPago=$1, conp_dataPagamento='" + p.getDataPagamento() + "', conp_status='$3' where conp_cod=" + p.getCod();
                sql = sql.replace("$1", p.getValorPago() + "");
                sql = sql.replace("$3", "pendente");
                System.out.println("contas ALTERAR: " + sql);
            }
            flag = Banco.con.manipular(sql);
            Banco.con.getConnect().commit();
        } catch (SQLException ex) {
            Banco.con.getConnect().rollback();
            flag = false;
            System.out.println(ex+"");
        } finally {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return flag;
    }
    
    public boolean Estronar(ContasPagar p) throws SQLException
    {
        boolean flag = true;
        String sql ="";
        Banco.conectar();
        try {
            Banco.con.getConnect().setAutoCommit(false);

            sql = "update Contas_Pagar set conp_valorPago=0.0, conp_dataPagamento=null, conp_status='pendente' where conp_cod=" + p.getCod();

            System.out.println("contas ALTERAR: " + sql);
            flag = Banco.con.manipular(sql);

            Banco.con.getConnect().commit();
        } catch (SQLException ex) {
            Banco.con.getConnect().rollback();
            flag = false;
            System.out.println(ex+"");
        } finally {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return flag;
    }
    
    public ArrayList <ContasPagar> todasContas(String flag)
    {
        ArrayList <ContasPagar> lista = new ArrayList();
        String sql = "select * from Contas_Pagar where conp_status = '"+flag+"';";
        
        System.out.println("conta_todas: "+sql);
        Banco.conectar();
        ResultSet rs = Banco.con.consultar(sql);
        
        try 
        {
            while (rs.next()) 
            {
                lista.add(new ContasPagar(rs.getInt("conp_cod"), rs.getDouble("conp_valorPago"), rs.getDouble("conp_valorConta"), rs.getTimestamp("conp_dataConta"), rs.getTimestamp("conp_dataVencimento"), rs.getTimestamp("conp_dataPagamento")));
            }
        } catch (Exception e) 
        {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public ArrayList <ContasPagar> todasContasFiltro(String filtro, String pesquisa, String flag)
    {
        ArrayList <ContasPagar> lista = new ArrayList();
        String sql = "select * from Contas_Pagar where conp_status = '"+flag+"' and "+filtro+"="+pesquisa+";";//verificar
        
        System.out.println("conta_todas: "+sql);
        Banco.conectar();
        ResultSet rs = Banco.con.consultar(sql);
        
        try 
        {
            while (rs.next()) 
            {
                lista.add(new ContasPagar(rs.getInt("conp_cod"), rs.getDouble("conp_valorPago"), rs.getDouble("conp_valorConta"), rs.getTimestamp("conp_dataConta"), rs.getTimestamp("conp_dataVencimento"), rs.getTimestamp("conp_dataPagamento")));
            }
        } catch (Exception e) 
        {
            System.out.println(e);
        }
        
        return lista;
    }
    
    
}
