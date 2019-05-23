package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.Banco;
import models.Fornecedor;
import models.Produto;

public class DaoProduto 
{
    public boolean salvar(Produto p ) throws SQLException
    { 
        String sql ="";
        boolean teste = false;
        try
        {
            Banco.con.getConnect().setAutoCommit(false);
            
           sql = "insert into Produto (pro_nome, pro_preco,pro_desc,pro_quantidade, Fornecedor_for_cod) values ('$1',$2,'$3',$4,$5);";

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", p.getPreco()+"");     
            sql = sql.replace("$3", p.getDescricao());
            sql = sql.replace("$4", p.getQuantidade()+"");
            sql = sql.replace("$5", p.getFornecedor().getCodigo()+"");

            teste = Banco.con.manipular(sql);
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
        System.out.println("sql Salvar Produto:"+sql);
        return teste;
    }
    
    public boolean alterar(Produto p) throws SQLException
    {
        boolean teste = true;
        
        try
        {
            Banco.con.getConnect().setAutoCommit(false);
        
            String sql = "update produto set pro_nome='$1', pro_preco=$2 , pro_desc = '$3' pro_quantidade = $4 Fornecedor_for_cod = $5 where pro_cod="+ p.getCod()+" ;";
            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", p.getPreco()+"");
            sql = sql.replace("$3", p.getDescricao());
            sql = sql.replace("$4", p.getQuantidade()+"");
            sql = sql.replace("$5", p.getFornecedor().getCodigo()+"");
            

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
    
    public ArrayList <Produto> getTodosProdutos()
    {
        ArrayList <Produto> lista = new ArrayList();
        
        String sql = "select * from Produto";
        
        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            while(rs.next())
            { 
                Produto resultadoBusca = new Produto(rs.getInt("pro_cod"), rs.getString("pro_nome"), rs.getDouble("pro_preco"),rs.getString("pro_desc"),
                    rs.getInt("pro_quantidade"), new Fornecedor(rs.getInt("Endereco_end_cod")));
              
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
    
    public ArrayList <Produto> getProdutoPorNome(String filtro)
    {
        ArrayList <Produto> lista = new ArrayList();
        
        String sql = "select * from Produto";
        if(!filtro.isEmpty())
        {
            sql += " where upper(pro_nome) like '%"+filtro.toUpperCase()+"%'";
        }
        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            while(rs.next())
            { 
                Produto resultadoBusca = new Produto(rs.getInt("pro_cod"), rs.getString("pro_nome"), rs.getDouble("pro_preco"),rs.getString("pro_desc"),
                    rs.getInt("pro_quantidade"), new Fornecedor(rs.getInt("Fornecedor_for_cod")));
              
                lista.add(resultadoBusca);    
            }
            System.out.println("sql Nome:"+sql);
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
        
        String sql = "select * from Produto";
        if(!filtro.isEmpty())
        {
            sql += " where pro_preco = "+filtro+";";
        }
        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            while(rs.next())
            {
                Produto resultadoBusca = new Produto(rs.getInt("pro_cod"), rs.getString("pro_nome"), rs.getDouble("pro_preco"),rs.getString("pro_desc"),
                    rs.getInt("pro_quantidade"), new Fornecedor(rs.getInt("Endereco_end_cod")));
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
    
    
    public ArrayList <Produto> getProdutoPorFornecedor(String filtro)
    {
        ArrayList <Produto> lista = new ArrayList();
        
       
       
        
        try
        {
            String sql = "select * from Produto";
            if(!filtro.isEmpty())
            {
                String sqlFor = "select * from Fornecedor where upper(for_nome) lik '%"+filtro.toUpperCase()+"%' ;";
                ResultSet rsFor = Banco.con.consultar(sql);
                if(rsFor.next()){
                    sql += " where Fornecedor_for_cod = "+rsFor.getInt("for_cod")+";";
                }
                 ResultSet rs = Banco.con.consultar(sql);
               while(rs.next())
               { 
                   Produto resultadoBusca = new Produto(rs.getInt("pro_cod"), rs.getString("pro_nome"), rs.getDouble("pro_preco"),rs.getString("pro_desc"),
                       rs.getInt("pro_quantidade"), new Fornecedor(rs.getInt("Endereco_end_cod")));

                   lista.add(resultadoBusca);    
               }

            }
            
           
        }
        catch(Exception e)
        {
            lista = null;
            System.out.println(e);
        }
        
        return lista;
    }

    public boolean deletar(Produto produto) throws SQLException {
        String sql ="";
        boolean teste = true;
        try
        {
            Banco.con.getConnect().setAutoCommit(false);
            
            sql = "delete from Produto where pro_nome like '%$1%' and pro_preco = $2 and pro_desc like = '%$3%' and pro_quantidade = $4;";
            sql = sql.replace("$1", produto.getNome());
            sql = sql.replace("$2", produto.getPreco()+"");
            sql = sql.replace("$3", produto.getDescricao());
            sql = sql.replace("$4", produto.getQuantidade()+"");
            
            System.out.println("sql Excluir:"+sql);
            
            teste = Banco.con.manipular(sql);
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
}
