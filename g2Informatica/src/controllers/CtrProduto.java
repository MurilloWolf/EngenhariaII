/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Fornecedor;
import models.Produto;

/**
 *
 * @author wolf
 */
public class CtrProduto {
  
    
    public ArrayList<Produto> getProdutoNome(String filtro){
        return Produto.buscarPorNome(filtro);
    }
            
    public ArrayList<Produto> getProdutoPreco(String filtro){
        return Produto.buscarPorPreco(filtro);
    }
    
    public ArrayList<Produto> getProdutoMarca(String filtro){
        return Produto.buscarPorMarca(filtro);
    }
    
    
    public ArrayList<Produto> getAllProdutos(){
        return Produto.buscarTodosOsProdutos();
    }
    
    public boolean cadastrar(String nome, double preco , String descricao,int quantidade, String marca ){
        Produto p = new Produto(nome,preco,descricao,quantidade, marca);
        
        return p.inserir();
    }
    
    public boolean alterar(int codigo,String nome, double preco , String descricao,int quantidade, String marca ){
        Produto p = new Produto(codigo,nome,preco,descricao,quantidade,marca);
        
        return p.alterar();
    }
    
    public boolean deletar(int codigo ){
        Produto p = new Produto(codigo);
        
        return p.deletar();
    }
    
            
}
