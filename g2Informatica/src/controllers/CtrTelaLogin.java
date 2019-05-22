package controllers;

import javax.swing.JOptionPane;
import models.Funcionario;
import models.Pessoa;
import models.dao.DaoFuncionario;

public class CtrTelaLogin {
    
    public String logarctr (String usuario , String senha)
    {
        String aux = "";
        Pessoa p = new Funcionario();
        DaoFuncionario daoP = new DaoFuncionario();
        
        
        if (usuario == "" || senha == "") 
        {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos para logar");
        } 
        else
        {
            p = daoP.login(usuario, senha, p);
            if (((Funcionario)p).getId_login().equals(usuario) && ((Funcionario)p).getSenha().equals(senha))
            {
                aux=((Funcionario)p).getNivel();
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos");
            }
        }
        
        return aux;
    }
    
}
