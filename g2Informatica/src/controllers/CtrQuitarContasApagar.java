
package controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.ObservableList;
import models.ContasPagar;
import models.dao.DaoContasApagar;

public class CtrQuitarContasApagar {
    public void IniciaEntidades(ContasPagar cp)
    {
        cp = new ContasPagar();
    }
    
    public ArrayList <ContasPagar> addTabelaContas(String flag)
    {
        ArrayList <ContasPagar> lista = new ArrayList();
        DaoContasApagar dao = new DaoContasApagar();
        
        lista = dao.todasContas(flag);
        return lista;
    }
    
    public ArrayList <ContasPagar> addTabelaContasFiltro(String filtro, String pesquisa, String flag)
    {
        ArrayList <ContasPagar> lista = new ArrayList();
        DaoContasApagar dao = new DaoContasApagar();
        lista = dao.todasContasFiltro(filtro, pesquisa, flag);
        return lista;
    }
     
     
    public boolean pagarAScontas(ContasPagar cp, LocalDate datav) throws SQLException
    {
        Timestamp dtv = toTimestamp(datav);
        cp.setDataPagamento(dtv);
        boolean flag = false;
        DaoContasApagar dao = new DaoContasApagar();
        if(dao.pagar(cp))
            flag=true;
        return flag;
    }
    public boolean estronarConta(ContasPagar cp) throws SQLException
    {
        boolean flag = false;
        DaoContasApagar dao = new DaoContasApagar();
        if(dao.Estronar(cp))
            flag=true;
        return flag;
    }
    
    public Timestamp toTimestamp(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Timestamp timeStamp = new Timestamp(date.getTime());
        return timeStamp;
    }
}
