package controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
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
    
    
    public boolean ConfirmarCompra(ObservableList lista, int codFor) throws SQLException
    {
        boolean flag = false;
        DaoComprarProdutos daoCP = new DaoComprarProdutos();
        
        if(daoCP.RegistrarCompra(lista, codFor))
        {
            flag = true;
        }
        return flag;
    }
    
    public void inicializaCP(Produto p, Produto pe, Fornecedor f)
    {
        p = new Produto();
        pe = new Produto();
        f = new Fornecedor();
    }
}
