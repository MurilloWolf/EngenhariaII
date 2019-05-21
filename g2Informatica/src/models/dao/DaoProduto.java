package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.Banco;
import models.Produto;

public class DaoProduto 
{
    public boolean salvar(Produto p ) throws SQLException
    {
        boolean teste = true;
        try
        {
            Banco.con.getConnect().setAutoCommit(false);
            
            String sql = "insert into Produto (pro_nome, pro_preco) values ('$1',$2)";

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", p.getPreco()+"");

            Banco.con.manipular(sql);
            Banco.con.getConnect().commit();
        }
        catch(SQLException se)
        {
            Banco.con.getConnect().rollback();
            teste = false;
        }
        finally
        {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return teste;
    }
    
    public boolean alterar(Produto p) throws SQLException
    {
        boolean teste = true;
        
        try
        {
            Banco.con.getConnect().setAutoCommit(false);
        
            String sql = "update produto set pro_nome='$1', pro_preco=$2 where pro_cod="+ p.getCod()+" ;";
            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", p.getPreco()+"");

            Banco.con.manipular(sql);

            Banco.con.getConnect().commit();
        }
        catch(SQLException se)
        {
            Banco.con.getConnect().rollback();
            System.out.println("Erro: "+se.toString());
            teste = false;
        }
        finally
        {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return teste;
    }
    
    public boolean apagar(Produto p) throws SQLException
    {
        boolean teste = true;
        
        try
        {
            Banco.con.getConnect().setAutoCommit(false);
          
            Banco.con.manipular("delete from produto where pro_cod=" +p.getCod()+" ;");
            Banco.con.getConnect().commit();
        }
        catch(SQLException se)
        {
            Banco.con.getConnect().rollback();
            System.out.println("Erro: "+se.toString());
 
            teste = false;
        }
        finally
        {
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return teste;
    }
    
    public Produto getProduto(int cod)
    {
        Produto p = null;
        String sql = "select * from produto where pro_cod="+ cod;
        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            if(rs.next())
            {
                p = new Produto(rs.getInt("pro_cod"), rs.getString("pro_nome"), rs.getDouble("pro_preco"));
               
            } 
        }
        catch(Exception e)
        {
            System.out.println(e);
            p = null;
        }
        
        return p;
    }
    
    public ArrayList <Produto> getProdutoPorNome(String filtro)
    {
        ArrayList <Produto> lista = new ArrayList();
        
        String sql = "select * from produto";
        if(!filtro.isEmpty())
        {
            sql += " where upper(pro_nome) like '%"+filtro.toUpperCase()+"%'";
        }
        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            while(rs.next())
            { 
                Produto resultadoBusca = new Produto(rs.getInt("pro_cod"), rs.getString("pro_nome"), rs.getDouble("pro_preco"));
                lista.add(resultadoBusca);    
            }
        }
        catch(Exception e)
        {
            lista = null;
            System.out.println(e);
        }
        
        return lista;
    }
    
    public ArrayList <Produto> getProdutoPorPreco(String filtro)
    {
        ArrayList <Produto> lista = new ArrayList();
        
        String sql = "select * from produto";
        if(!filtro.isEmpty())
        {
            sql += " where pro_preco = "+filtro;
        }
        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            while(rs.next())
            {
                Produto resultadoBusca = new Produto(rs.getInt("pro_cod"), rs.getString("pro_nome"), rs.getDouble("pro_preco"));
                lista.add(resultadoBusca);          
              
            }
        }
        catch(Exception e)
        {
            lista = null;
            System.out.println(e);
        }
        
        return lista;
    }
}
