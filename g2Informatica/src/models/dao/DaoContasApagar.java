
package models.dao;

import db.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.ContasPagar;

public class DaoContasApagar {
    
    public boolean pagar(ArrayList <ContasPagar> listas) throws SQLException
    {
        boolean flag = true;
        int aux= listas.size();
        int codigo = listas.get(aux).getCod();
        String sql ;
        try {
            Banco.con.getConnect().setAutoCommit(false);
            while (aux < listas.size()) {
                sql = "update Contas_Pagar set conp_valorPago=$1, conp_dataPagamento=SYSDATE(), conp_status='$3' where fun_cod="+codigo;
                sql = sql.replace("$1", listas.get(aux).getValorConta()+"");
                sql = sql.replace("$3", "pago");
                System.out.println("contas ALTERAR: " + sql);
                flag = Banco.con.manipular(sql);
                Banco.con.getConnect().commit();
            }
        } catch (SQLException ex) {
            Banco.con.getConnect().rollback();
            flag = false;
        } finally {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return flag;
    }
    
    public ArrayList <ContasPagar> todasContas()
    {
        ArrayList <ContasPagar> lista = new ArrayList();
        String sql = "select * from Contas_Pagar where conp_status = 'pendente';";
        
        System.out.println("conta_todas: "+sql);
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
