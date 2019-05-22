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

    public Fornecedor(int codigo) {
        this.codigo = codigo;
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
    
}
