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
    private String marca;
    
    public Produto(int codigo){
        Produto pro = Produto.buscarPorCodigo(codigo);
        
        if(pro!=null){
          this.cod = codigo;
          this.nome = pro.getNome();
          this.preco = pro.getPreco();
          this.descricao = pro.getDescricao();
          this.marca = pro.getMarca();
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

    public Produto(int cod, String nome, double preco, String descricao, int quantidade, String marca) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.marca = marca;
    }
    
    public Produto( String nome, double preco, String descricao, int quantidade,String marca) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.marca = marca;
    }

    public String getMarca() {
        if(marca == null)
            return "";
        
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }



    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        if(descricao == null || descricao.equals(""))
            return "";
        
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
            return deletarProduto.apagar(this);
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
     
    public static ArrayList<Produto> buscarTodosOsProdutos(){
        
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Produto> resultado;
        resultado = buscaProduto.getTodosProdutos();
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    }  
     
    public static ArrayList<Produto> buscarPorNome(String filtro){
        
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Produto> resultado;
        resultado =  buscaProduto.getProdutoPorNome(filtro);
        
        if(resultado != null && resultado.size() > 0)
        {
            for (int i = 0; i < resultado.size(); i++) {
                System.out.println("R"+i+":"+resultado.get(i).toString());
            }
            return resultado;
        }
        
        return null;
        
        
    } 
    public static ArrayList<Produto> buscarPorPreco(String filtro){
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Produto> resultado;
        resultado = buscaProduto.getProdutoPorPreco(filtro);
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    } 

    public static ArrayList<Produto> buscarPorMarca(String filtro){
        DaoProduto buscaProduto = new DaoProduto();
        
        ArrayList<Produto> resultado;
        resultado =  buscaProduto.getProdutoPorMarca(filtro);
        
        if(resultado != null && resultado.size() > 0)
        {
            return resultado;
        }
        
        return null;
        
        
    } 
    
}
