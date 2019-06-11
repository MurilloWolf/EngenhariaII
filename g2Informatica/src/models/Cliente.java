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
public class Cliente {
    private String nome;
    private int codigo;
    
    public Cliente (String nome, int codigo){
        this.nome = nome;
        this.codigo = codigo;
    }

    public Cliente(int codigo) {
        Cliente c = Cliente.getCliente(codigo);
        if(c!=null){
            
            this.codigo = c.getCodigo();
            this.nome = c.getNome();
        }
    }
    public Cliente(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    public static ArrayList<Cliente> getAllClientes(){
     String sql= "select * from Cliente ;";
     ArrayList<Cliente> lista = new ArrayList();
     
     try{
         ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            while(rs.next())
            { 
               Cliente c = new Cliente(rs.getString("cli_nome"), rs.getInt("cli_cod"));
              
                lista.add(c);    
            }
        }
        catch(Exception e)
        {
            lista = null;
            System.out.println(e);
        }
         
     }catch(Exception ex){
         lista = null;   
     }
     
     
     return lista;
    }
    
     public static Cliente getCliente(int codigo){
     String sql= "select * from Cliente where cli_cod = "+codigo+";";
     Cliente c = new Cliente();
     
     try{
         ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            if(rs.next())
            { 
               c.setCodigo(rs.getInt("cli_cod"));
               c.setNome(rs.getString("cli_nome"));
            }
        }
        catch(Exception e)
        {
            c = null;
            System.out.println(e);
        }
         
     }catch(Exception ex){
         c = null;   
     }
     
     
     return c;
    }
    
}
