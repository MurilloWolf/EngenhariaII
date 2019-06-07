
package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import models.ContasPagar;
import models.dao.DaoContasApagar;

public class CtrQuitarContasApagar {
    public void IniciaEntidades(ContasPagar cp, ContasPagar cpE)
    {
        cp = new ContasPagar();
        cpE = new ContasPagar();
    }
    
    public boolean pagarAScontas(ObservableList listas) throws SQLException
    {
        DaoContasApagar dao = new DaoContasApagar();
        boolean flag = false;
        if(dao.pagar(listas))
            flag = true;
        
        return flag;
    }
    
    public ArrayList <ContasPagar> addTabelaContas()
    {
        ArrayList <ContasPagar> lista = new ArrayList();
        DaoContasApagar dao = new DaoContasApagar();
        
        lista = dao.todasContas();
        return lista;
    }
}
