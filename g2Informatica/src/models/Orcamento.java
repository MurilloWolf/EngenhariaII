/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author wolf
 */
public class Orcamento {
    private int codigo; 
    private double valor; 
    private Cliente  cliente; 
    private Timestamp dtValidade; 
    private String  status; 
    private String  descricao; 
    private ArrayList<OrcamentoProduto> listaP;
    private ArrayList<OrcamentoServico> listaS;
    

    
    public boolean salvar(){
        String sql = "insert into Orcamento (Cliente_cli_cod,orc_valor, orc_status,orc_desc,orc_dataValidade,orc_data) values($1,$2,$3,$4,$5,SYSDATE());";
        boolean retorno = false;
        try{
            sql = sql.replace("$1",""+cliente.getCodigo());
            sql = sql.replace("$2",""+valor);
            sql = sql.replace("$3",""+status);
            sql = sql.replace("$4",""+descricao);
            sql = sql.replace("$5",""+dtValidade.toString());
            
            
            
            
            retorno = Banco.con.manipular(sql);
        }catch(Exception ex){
            
        }
        return retorno ; 
    }
}
