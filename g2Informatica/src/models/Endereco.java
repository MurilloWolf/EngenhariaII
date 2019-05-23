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
    private String cep;

    public Endereco(String uf, String cidade, String bairro, String rua, String numero,String cep) {
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep ;
    }

    public Endereco(int codigo) {
       Endereco end = buscarEnderecoPorCodigo(codigo);
       this.uf = end.getUf();
        this.cidade = end.getCidade();
        this.bairro = end.getBairro();
        this.rua = end.getRua();
        this.numero = end.getNumero();
    }

    public Endereco() 
    {
        
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
           sql = "insert into Endereco (end_uf, end_cidade,end_bairro,end_rua,end_numero,end_cep) values ('$1','$2','$3','$4','$5','$6')";
           sql = sql.replace("$1", uf);
           sql = sql.replace("$2", cidade);
           sql = sql.replace("$3", bairro);
           sql = sql.replace("$4", rua);
           sql = sql.replace("$5", numero);
           sql = sql.replace("$6", cep);
           
           System.out.println("Sql Endereco:"+sql);

           return Banco.con.manipular(sql);
           
       }catch(Exception ex){
           System.out.println("erro: "+ ex.toString());
           return false;
       }
       
    }
    
    public boolean verificaEnderecoExistente(){
       String sql = "";
       try{
           sql = "select * from Endereco where end_uf = '$1' and end_cidade = '$2' and end_bairro = '$3' and end_rua = '$4' and end_numero = '$5' and end_cep = '$6';";
           sql = sql.replace("$1", uf);
           sql = sql.replace("$2", cidade);
           sql = sql.replace("$3", bairro);
           sql = sql.replace("$4", rua);
           sql = sql.replace("$5", numero);
           sql = sql.replace("$6", cep);

           
          ResultSet rs = Banco.con.consultar(sql);
          //se tem alguma linha da consulta , esse endereco ja esta cadastrado , nao precisa cadastrar novamente
          if(rs.next())
          {
              this.setCodigo(rs.getInt("end_cod"));
              System.out.println("Sql Endereco:"+sql);
              return true;
          }
       
    
           
       }catch(SQLException ex){
           System.out.println("erro: "+ ex.toString());
       }
       System.out.println("Sql Endereco:"+sql);

       
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
              Endereco end = new Endereco(rs.getString("end_uf"),rs.getString("end_cidade"), rs.getString("end_bairro"), rs.getString("end_rua"),rs.getString("end_numero"),rs.getString("end_cep"));
              
              return end;
          }
       
    
           
       }catch(SQLException ex){
           System.out.println("erro: "+ ex.toString());
       }
       
       return null;
    }
    public  boolean  buscarEndereco(){
       String sql = "";
       try{
           sql = "select * from Endereco where end_uf='"+getUf()+"' and end_cidade = '"+getCidade()+"' and end_bairro = '"+getBairro()+"' and end_rua = '"+getRua()+"' and end_numero = '"+getNumero()+"end_cep= '"+getCep()+"';";
           System.out.println("Sql Buscar Endereco :"+sql);
           
          ResultSet rs = Banco.con.consultar(sql);
          //se tem alguma linha da consulta , esse endereco ja esta cadastrado , nao precisa cadastrar novamente
          if(rs.next())
          {
              this.codigo = rs.getInt("end_cod");
              return true;
          }
       
          
           
       }catch(SQLException ex){
           System.out.println("erro: "+ ex.toString());
       }
       
        return false;
    }
    
    public boolean alterar() {
       String sql = "";
       try{
           sql = "update Endereco set end_uf='$1', end_cidade='$2', end_bairro='$3', end_rua='$4', end_numero='$5', end_cep='$6' where end_cod=" + getCodigo();
           sql = sql.replace("$1", uf);
           sql = sql.replace("$2", cidade);
           sql = sql.replace("$3", bairro);
           sql = sql.replace("$4", rua);
           sql = sql.replace("$5", numero);
           sql = sql.replace("$6", cep);
           
           System.out.println("Sql Endereco:"+sql);

           return Banco.con.manipular(sql);
           
       }catch(Exception ex){
           System.out.println("erro: "+ ex.toString());
           return false;
       }
       
    }
    public boolean apagar() {
       String sql = "";
       try{
           sql = "delete from Endereco where end_cod=" + getCodigo();

           
           System.out.println("Sql Endereco:"+sql);

           return Banco.con.manipular(sql);
           
       }catch(Exception ex){
           System.out.println("erro: "+ ex.toString());
           return false;
       }
       
    }
}
