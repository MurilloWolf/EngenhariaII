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
public class Servico {
    private int codigo;
    private String descricao;
    private double valor;

    public Servico(int codigo) {
        Servico s = buscarServicoPorCodigo(codigo);
        if(s!=null){
            this.setCodigo(s.getCodigo());
            this.setDescricao(s.getDescricao());
            this.setValor(s.getValor());
        }
        
        
        
    }

    
    
    public Servico(int codigo , String descricao, double valor) {
        this.descricao = descricao;
        this.codigo = codigo;
        this.valor = valor;
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public static ArrayList<Servico> buscarTodosServicos(){
        ArrayList<Servico> lista = new ArrayList();
        String sql = "select * from Servico;";
        
         ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            while(rs.next())
            { 
                lista.add(new Servico (rs.getInt("ser_cod"),rs.getString("ser_desc"),rs.getDouble("ser_valor")) );
                   
            }
        }
        catch(Exception e)
        {
            lista = null;
            System.out.println(e);
        }        
        
        return lista;
    }
    
    public Servico buscarServicoPorCodigo(int codigo){
        Servico retorno = null;
        String sql = "select * from Servico where ser_cod = "+codigo+";";
        try{
            
            ResultSet rs = Banco.con.consultar(sql);

            if(rs.next()){
                retorno = new Servico(rs.getInt("ser_cod"),rs.getString("ser_desc"),rs.getDouble("ser_valor"));
            }
        }catch(Exception e){
            retorno = null;
        }
        return retorno; 
    }
    
}
