package controllers;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import models.Produto;
import models.dao.DaoProduto;

public class CtrComprarProdutos {
    
    public ArrayList <Produto> Pesquisa()
    {
        DaoProduto daoD = new DaoProduto();
        ArrayList <Produto> lista = new ArrayList();
        lista = daoD.getTodosProdutos();
        return lista;
    }
    
    
    public boolean ConfirmarCompra(ObservableList lista)
    {
        boolean flag =true;
        
        return flag;
    }
}
