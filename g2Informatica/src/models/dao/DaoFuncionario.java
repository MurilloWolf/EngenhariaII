package models.dao;

import db.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Funcionario;
import models.Pessoa;

public class DaoFuncionario {
    public Pessoa login(String login, String senha, Pessoa p)
    {
        String sql = "select * from ";
        
        if(p instanceof Funcionario)
        {
            sql += "Funcionario where fun_id = '$1' and fun_senha = '$2'";
            sql = sql.replace("$1", login);
            sql = sql.replace("$2", senha);
            
            ResultSet rs = Banco.con.consultar(sql);
        
            try
            {
                if(rs.next())
                {
                    //p = new Funcionario(rs.getString("usr_id"), rs.getString("usr_senha"), rs.getInt("usr_nivel"), rs.getInt("usr_habilitado"), rs.getInt("usr_cod"), rs.getString("usr_nome"));
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
}
