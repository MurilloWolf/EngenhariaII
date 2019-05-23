package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.dao.DaoProduto;

public class Produto 
{
    
    private int cod;
    private String nome;
    private double preco;
    private int quantidade;
    private String descricao;
    private Fornecedor fornecedor;
    
    
    public Produto(int codigo){
        Produto pro = Produto.buscarPorCodigo(codigo);
        
        if(pro!=null){
          nome = pro.getNome();
          preco = pro.getPreco();
        }
        else{
            nome = "";
            preco = 0;
            cod = 0;
        }
    }

    public Produto(int cod, String nome, double preco) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
    }
    
  

    public Produto(String nome, double preco ) {
        this.nome = nome;
        this.preco = preco;
    }
   

    public Produto() {
        this.nome = "";
        this.preco = -1;
    }

    public Produto(int cod, String nome, double preco, String descricao, int quantidade, Fornecedor fornecedor) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
    }
    
    public Produto( String nome, double preco, String descricao, int quantidade, Fornecedor fornecedor) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
    }



    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
 
   
    @Override
    public String toString()
    {
        return nome;
    }
    
    
    
    
 //======================================================= Persistencia =======================================================    
    public boolean inserir(){
        DaoProduto novoProduto = new DaoProduto();
        try {
            return novoProduto.salvar(this);
            
        } catch (SQLException ex) {
            
            System.out.println("Erro : "+ex.toString());
        }
        return false;
    }
    
    public boolean alterar(){
        DaoProduto alterarProduto = new DaoProduto();
        try {
            return alterarProduto.alterar(this);
        } catch (SQLException ex) {
            
              System.out.println("Erro : "+ex.toString());
        }
        return false;
    }
    
    public boolean deletar(){
        DaoProduto deletarProduto = new DaoProduto();
        try {
            return deletarProduto.deletar(this);
        } catch (Exception ex) {
            
              System.out.println("Erro : "+ex.toString());
        }
        return false;
    }
    
    
    
     public static Produto buscarPorCodigo(int codigo){
        DaoProduto buscaProduto = new DaoProduto();
        
        try {
            return buscaProduto.getProduto(codigo);
            
        } catch (Exception ex) {
            
              System.out.println("Erro : "+ex.toString());
        }
        return null;
    }
     
    public static ArrayList<Object> buscarTodosOsProdutos(){
        
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Object> resultado;
        resultado = (ArrayList<Object>) (Object) buscaProduto.getTodosProdutos();
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    }  
     
    public static ArrayList<Object> buscarPorNome(String filtro){
        
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Object> resultado;
        resultado = (ArrayList<Object>) (Object) buscaProduto.getProdutoPorNome(filtro);
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    } 
    public static ArrayList<Object> buscarPorPreco(String filtro){
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Object> resultado;
        resultado = (ArrayList<Object>) (Object) buscaProduto.getProdutoPorPreco(filtro);
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    } 
    
    public static ArrayList<Object> buscarPorFornecedor(String filtro){
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Object> resultado;
        resultado = (ArrayList<Object>) (Object) buscaProduto.getProdutoPorFornecedor(filtro);
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    } 
    
}
