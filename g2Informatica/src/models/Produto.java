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
    
     public static Produto buscarPorCodigo(int codigo){
        DaoProduto buscaProduto = new DaoProduto();
        
        try {
            return buscaProduto.getProduto(codigo);
            
        } catch (Exception ex) {
            
              System.out.println("Erro : "+ex.toString());
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
        resultado = (ArrayList<Object>) (Object) buscaProduto.getProdutoPorNome(filtro);
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    } 
    
}
