package controllers;

import javax.swing.JOptionPane;
import models.Funcionario;
import models.Pessoa;
import models.dao.CtrFuncionario;

public class CtrTelaLogin {
    
    public String logarctr (String usuario , String senha)
    {
        String aux = "";
        Pessoa p = new Funcionario();
        CtrFuncionario ctrP = new CtrFuncionario();
        
        
        if (usuario == "" || senha == "") 
        {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos para logar");
        } 
        else
        {
            p = ctrP.login(usuario, senha, p);
            if (((Funcionario)p).getId_loguin().equals(usuario) && ((Funcionario)p).getId_loguin().equals(senha) && !((Funcionario)p).getNivel().equals("0"))
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
