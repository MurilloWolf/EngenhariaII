/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import models.Fornecedor;

/**
 *
 * @author wolf
 */
public class CtrFornecedor {
    
    public ArrayList<String> getListaDeFornecedores(){
        return Fornecedor.buscarTodosFornecedores();
    }
    public ObservableList<Fornecedor> getListaDeFornecedores2(){
        return Fornecedor.buscarTodosFornecedores2();
    }
}
