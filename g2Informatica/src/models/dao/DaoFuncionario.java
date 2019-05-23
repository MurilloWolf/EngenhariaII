package models.dao;

import db.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Funcionario;
import models.Pessoa;

public class DaoFuncionario {
    
    public Pessoa login(String login, String senha, Pessoa p)
    {
        String sql = "select * from ";
        
        if(p instanceof Funcionario)
        {
            sql += "Funcionario where fun_id_login = '$1' and fun_senha = '$2'";
            sql = sql.replace("$1", login);
            sql = sql.replace("$2", senha);
            
            
            ResultSet rs = Banco.con.consultar(sql);
        
            try
            {
                if(rs.next())
                {
                    p = new Funcionario(rs.getInt("fun_cod"), rs.getString("fun_nome"), rs.getString("fun_email"), rs.getString("fun_telefone"), rs.getString("fun_cpf"), rs.getString("fun_rg"), rs.getInt("Endereco_end_cod"), rs.getString("fun_id_login"), rs.getString("fun_senha"), rs.getString("fun_tipo"), rs.getString("fun_nivel"));
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        return p;
    }
    
    public boolean salvarF (Pessoa p) throws SQLException
    {
        boolean flag = true;
        try {
            Banco.con.getConnect().setAutoCommit(false);
            
            String sql = "insert into Funcionario (fun_nome, fun_email, fun_telefone, fun_cpf, fun_rg, Endereco_end_cod, fun_if_login, fun_senha, fun_tipo, fun_nivel) values ('$1','$2','$3','$4','$5',$6,'$7','$8','$9','$10')";

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", ((Funcionario) p).getEmail());
            sql = sql.replace("$3", ((Funcionario) p).getTelefone());
            sql = sql.replace("$4", ((Funcionario) p).getCpf());
            sql = sql.replace("$5", ((Funcionario) p).getRg());
            sql = sql.replace("$6", ((Funcionario) p).getEnd_cod() + "");
            sql = sql.replace("$7", ((Funcionario) p).getId_login());
            sql = sql.replace("$8", ((Funcionario) p).getSenha());
            sql = sql.replace("$9", ((Funcionario) p).getTipo());
            sql = sql.replace("$10", ((Funcionario) p).getNivel());

            Banco.con.manipular(sql);
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
    
    
    public boolean AlterarF (Pessoa p) throws SQLException
    {
        boolean flag = true;
        try {
            Banco.con.getConnect().setAutoCommit(false);
            
            String sql = "update Funcionario set fun_nome='$1', fun_email='$2', fun_telefone='$3', fun_cpf='$4', fun_rg='$5', fun_if_login='$6', fun_senha='$7', fun_tipo='$8', fun_nivel='$9' where fun_cod="+p.getCod();

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", ((Funcionario) p).getEmail());
            sql = sql.replace("$3", ((Funcionario) p).getTelefone());
            sql = sql.replace("$4", ((Funcionario) p).getCpf());
            sql = sql.replace("$5", ((Funcionario) p).getRg());
            sql = sql.replace("$6", ((Funcionario) p).getId_login());
            sql = sql.replace("$7", ((Funcionario) p).getSenha());
            sql = sql.replace("$8", ((Funcionario) p).getTipo());
            sql = sql.replace("$9", ((Funcionario) p).getNivel());

            Banco.con.manipular(sql);
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
    
    
    public boolean ExcluirF (Pessoa p) throws SQLException
    {
        boolean flag = true;
        
        try
        {
            Banco.con.getConnect().setAutoCommit(false);
            Banco.con.manipular("delete from Funcionario where fun_cod=" +p.getCod());
            Banco.con.getConnect().commit();
        }
        catch(SQLException se)
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
    
    public ArrayList <Funcionario> getPesoas (String pesq, String tipo) throws SQLException
    {
        ArrayList <Funcionario> lista = new ArrayList();
        String sql="";
        if(!tipo.equals("") && !pesq.equals(""))
        {
            sql = "select * from Funcionario where fun_'"+tipo+"' = '"+pesq+"'";
        }
        else
        {
            sql = "select * from Funcionario";
        }
        
        ResultSet rs = Banco.con.consultar(sql);
        
        try 
        {
            while (rs.next()) 
            {
                lista.add(new Funcionario(rs.getInt("fun_cod"), rs.getString("fun_nome"), rs.getString("fun_email"), rs.getString("fun_telefone"), rs.getString("fun_cpf"), rs.getString("fun_rg"), rs.getInt("Endereco_end_cod"), rs.getString("fun_id_login"), rs.getString("fun_senha"), rs.getString("fun_tipo"), rs.getString("fun_nivel")));
            }
        } catch (Exception e) 
        {
            System.out.println(e);
        }

        
        return lista;
    }
    
}
