/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author wolf
 */
public class Fornecedor {
    private int codigo;
    private String nome;
    private String telefone;
    private String email;
    private Endereco endereco;

    public Fornecedor() {
        
    }

    public Fornecedor(String nome){
       Fornecedor f =  buscarFornecedorPorNome(nome);
       this.codigo = f.getCodigo();
       this.email = f.getEmail();
       this.endereco = f.getEndereco();
       this.nome = f.getNome();
       this.telefone = f.getTelefone();
    }
    
    public Fornecedor(int codigo) {
       Fornecedor f = buscarFornecedor(codigo);
       this.codigo = codigo;
       this.email = f.getEmail();
       this.endereco = f.getEndereco();
       this.nome = f.getNome();
       this.telefone = f.getTelefone();
    }

    
    
    public Fornecedor(String nome, String telefone, String email, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

   

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    // ================================================== SQL ================================================== 
    
    public static ArrayList<String> buscarTodosFornecedores(){
        ArrayList<String> retorno = new ArrayList();
        //ArrayList<Fornecedor> fornecedores = new ArrayList();
        String sql ="";
        try{
            sql = "select * from Fornecedor";
            
            ResultSet rs = Banco.con.consultar(sql);
            
            while(rs.next()){
                /*
                    Fornecedor f = new Fornecedor();
                    f.setNome(rs.getString("for_nome"));
                    f.setEmail(rs.getString("for_email"));
                    f.setCodigo(rs.getString("for_codigo"));
                    f.setEndereco( new Endereco(rs.getInt("for_cod")));
                    fornecedores.add(f);
                */
                retorno.add(rs.getString("for_nome"));
            }
            
            return retorno ; 
        }catch(Exception ex){
            
        }
        return null;
    }
    
    public Fornecedor buscarFornecedor(int codigo){
      
        String sql ="";
        try{
            sql = "select * from Fornecedor where for_cod ="+codigo+";";
            
            ResultSet rs = Banco.con.consultar(sql);
            Fornecedor f = new Fornecedor();
            
            if(rs.next()){
               
                    
                    f.setNome(rs.getString("for_nome"));
                    f.setEmail(rs.getString("for_email"));
                    f.setCodigo(rs.getInt("for_codigo"));
                    f.setEndereco( new Endereco(rs.getInt("Endereco_end_cod")));
                
            }
            System.out.println("sql Forn: "+sql);
            return f ; 
        }catch(Exception ex){
            
        }
        return null;
    }
    
     public Fornecedor buscarFornecedorPorNome(String nome){
      
        String sql ="";
        try{
            sql = "select * from Fornecedor where upper(for_nome) like '%"+nome.toUpperCase()+"%';";
            
            ResultSet rs = Banco.con.consultar(sql);
            Fornecedor f = new Fornecedor();
            
            if(rs.next()){
               
                   
                    f.setNome(rs.getString("for_nome"));
                    f.setEmail(rs.getString("for_email"));
                    f.setCodigo(rs.getInt("for_cod"));
                    f.setEndereco( new Endereco(rs.getInt("Endereco_end_cod")));
                
            }
            System.out.println("sql Fornecedor:"+sql);
            
            return f ; 
        }catch(Exception ex){
            
        }
        return null;
    }
    
}
