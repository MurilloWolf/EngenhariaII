package controllers;

import java.sql.SQLException;
import models.Endereco;
import models.Funcionario;
import models.Pessoa;
import models.dao.DaoFuncionario;

public class CtrcadastroFuncionario {
    
    public boolean CadastrarFuncionario(String nome, String email, String telefone, String cpf, String rg, String id_login, String senha, String tipo, String nivel, String uf, String cidade, String bairro, String rua, String numero, String cep) throws SQLException
    {
        boolean flag = false;
        DaoFuncionario daof = new DaoFuncionario();
        Endereco e =new Endereco(uf, cidade ,bairro ,rua ,numero ,cep);
        Pessoa p = new Funcionario(nome, email, telefone, cpf, rg, 0, id_login, senha, tipo, nivel);
        if(!e.verificaEnderecoExistente())
        {
            if(e.salvar())
            {
                e.buscarEndereco();
                ((Funcionario)p).setEnd_cod(e.getCodigo());
                if(daof.salvarF(p))
                {
                    flag = true;
                }
            }    
        }
        else
        {
            e.buscarEndereco();
            ((Funcionario)p).setEnd_cod(e.getCodigo());
            if(daof.salvarF(p))
            {
                flag = true;
            }
        }
        
        
        return flag;
    }
    
    public boolean AlterarFuncionario(Endereco e, Pessoa p) throws SQLException
    {
        boolean flag = false;
        DaoFuncionario daof = new DaoFuncionario();
        
        //if(e.alterar())
           //if(dao)
        
        
        return flag;
    }
    
}
