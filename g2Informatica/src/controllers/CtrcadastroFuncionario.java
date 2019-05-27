package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
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
        //if(daof.verificaloguin(id_login))
        //{
            if (!e.verificaEnderecoExistente()) 
            {
                if (e.salvar()) 
                {
                    e.buscarEndereco();
                    ((Funcionario) p).setEnd_cod(e.getCodigo());
                    if (daof.salvarF(p)) 
                    {
                        flag = true;
                    }
                }
            } else {
                e.buscarEndereco();
                ((Funcionario) p).setEnd_cod(e.getCodigo());
                if (daof.salvarF(p)) 
                {
                    flag = true;
                }
            }
        //}
        
        return flag;
    }
    
    public boolean AlterarFuncionario(int cod, String nome, String email, String telefone, String cpf, String rg, String id_login, String senha, String tipo, String nivel, String uf, String cidade, String bairro, String rua, String numero, String cep, int end_cod) throws SQLException
    {
        boolean flag = false;
        DaoFuncionario daof = new DaoFuncionario();
        Pessoa p = new Funcionario(cod, nome, email, telefone, cpf, rg, end_cod, id_login, senha, tipo, nivel);
        Endereco e = new Endereco(end_cod, uf, cidade ,bairro ,rua ,numero ,cep);
        
        if(daof.AlterarF(p))
        {
            if(e.alterar())
            {
                flag = true;
            }
        }
        
        return flag;
    }
    
    public boolean ExcluirFuncionario(Endereco e, Pessoa p) throws SQLException
    {
        boolean flag = false;
        DaoFuncionario daof = new DaoFuncionario();
        if(daof.ExcluirF(p.getCod()))
        {
            flag = true;
        }
        return flag;
    }
    
    public ArrayList <Funcionario> Pesquisa(String aux, int auxN) throws SQLException
    {
        DaoFuncionario daof = new DaoFuncionario();
        ArrayList <Funcionario> lista = new ArrayList();
        
        switch (auxN)
        {
            case 0:
            {
                lista=daof.getPesoas(aux, "");
                break;
            }
            case 1:
            {
                lista=daof.getPesoas(aux, "cod");
                break;
            }
            case 2:
            {
                lista=daof.getPesoas(aux, "nome");
                break;
            }
            case 3:
            {
                lista=daof.getPesoas(aux, "cpf");
                break;
            }
            case 4:
            {
                lista=daof.getPesoas(aux, "id_login");
                break;
            }
        }
        return lista;
    }
    
    public void LimpaEntidade(Pessoa f, Endereco e)
    {
        f = null;
        e = null;
    }
    public void IniciaEntidade(Pessoa f, Endereco e)
    {
        f = new Funcionario();
        e = new Endereco();
    }
}
