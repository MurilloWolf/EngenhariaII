
package controllers;

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
    
    public boolean pagarAScontas(ObservableList listas)
    {
        DaoContasApagar dao = new DaoContasApagar();
        boolean flag = false;
        //dao.pagar((ArrayList) listas);
        //fazer
        return flag;
    }
    
    public ArrayList <ContasPagar> addTabelaContas()
    {
        ArrayList <ContasPagar> lista = new ArrayList();
        
        //lista = dao.todasContas();
        //fazer
        return lista;
    }
}
