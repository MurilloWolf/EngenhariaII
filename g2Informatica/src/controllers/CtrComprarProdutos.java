package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import models.Fornecedor;
import models.Produto;
import models.dao.DaoComprarProdutos;
import models.dao.DaoProduto;

public class CtrComprarProdutos {
    
    public ArrayList <Produto> Pesquisa()
    {
        DaoProduto daoD = new DaoProduto();
        ArrayList <Produto> lista = new ArrayList();
        lista = daoD.getTodosProdutos();
        return lista;
    }
    
    
    public boolean ConfirmarCompra(ObservableList lista, Fornecedor fo) throws SQLException
    {
        boolean flag = false;
        DaoComprarProdutos daoCP = new DaoComprarProdutos();
        if(daoCP.RegistrarCompra((ArrayList)lista, fo))
        {
            flag = true;
        }
        return flag;
    }
}
