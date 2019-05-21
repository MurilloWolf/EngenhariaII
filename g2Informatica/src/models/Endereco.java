/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author wolf
 */
public class Endereco {
    private int codigo;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

    public Endereco(String uf, String cidade, String bairro, String rua, String numero) {
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    Endereco(int codigo) {
       Endereco end = buscarEnderecoPorCodigo(codigo);
       this.uf = end.getUf();
        this.cidade = end.getCidade();
        this.bairro = end.getBairro();
        this.rua = end.getRua();
        this.numero = end.getNumero();
    }

    Endereco() {
    }
    
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    
    
    
    
 // ======================================================== SQL ========================================================     
    public boolean salvar() {
       String sql = "";
       try{
           sql = "insert into Endereco (end_uf, end_cidade,end_bairro,end_rua,end_numero) values ('$1','$2','$3','$4','$5')";
           sql = sql.replace("$1", uf);
           sql = sql.replace("$2", cidade);
           sql = sql.replace("$3", bairro);
           sql = sql.replace("$4", rua);
           sql = sql.replace("$5", numero);
           
           return Banco.con.manipular(sql);
           
       }catch(Exception ex){
           System.out.println("erro: "+ ex.toString());
           return false;
       }
       
    }
    
    public boolean verificaEnderecoExistente(){
        String sql = "";
       try{
           sql = "select * from Endereco where end_uf = '$1' and end_cidade = '$2' and end_bairro = '$3' and end_rua = '$4' and end_numero = '$5' ;";
           sql = sql.replace("$1", uf);
           sql = sql.replace("$2", cidade);
           sql = sql.replace("$3", bairro);
           sql = sql.replace("$4", rua);
           sql = sql.replace("$5", numero);
           
          ResultSet rs = Banco.con.consultar(sql);
          //se tem alguma linha da consulta , esse endereco ja esta cadastrado , nao precisa cadastrar novamente
          if(rs.next())
          {
              return true;
          }
       
    
           
       }catch(SQLException ex){
           System.out.println("erro: "+ ex.toString());
       }
       
       return false;
    }
    
    public static Endereco buscarEnderecoPorCodigo(int codigo){
       String sql = "";
       try{
           sql = "select * from Endereco where end_cod = "+codigo+";";
          
           
          ResultSet rs = Banco.con.consultar(sql);
          //se tem alguma linha da consulta , esse endereco ja esta cadastrado , nao precisa cadastrar novamente
          if(rs.next())
          {
              Endereco end = new Endereco(rs.getString("end_ud"),rs.getString("end_cidade"), rs.getString("end_bairro"), rs.getString("end_rua"),rs.getString("end_numero"));
              
              return end;
          }
       
    
           
       }catch(SQLException ex){
           System.out.println("erro: "+ ex.toString());
       }
       
       return null;
    }
    
}
