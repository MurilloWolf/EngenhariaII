/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.sql.ResultSet;
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
    private Timestamp dtAtual;
    private String  status; 
    private String  descricao; 
    private ArrayList<OrcamentoProduto> listaP;
    private ArrayList<OrcamentoServico> listaS;

    public Orcamento(int codigo) {
        this.codigo = codigo;
    }

    public Orcamento() {
    }

    public Timestamp getDtAtual() {
        return dtAtual;
    }

    public void setDtAtual(Timestamp dtAtual) {
        this.dtAtual = dtAtual;
    }

    
    
    public Orcamento(double valor, Cliente cliente, Timestamp dtValidade, String status, String descricao) {
        this.valor = valor;
        this.cliente = cliente;
        this.dtValidade = dtValidade;
        this.status = status;
        this.descricao = descricao;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Timestamp getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Timestamp dtValidade) {
        this.dtValidade = dtValidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<OrcamentoProduto> getListaP() {
        return listaP;
    }

    public void setListaP(ArrayList<OrcamentoProduto> listaP) {
        this.listaP = listaP;
    }

    public ArrayList<OrcamentoServico> getListaS() {
        return listaS;
    }

    public void setListaS(ArrayList<OrcamentoServico> listaS) {
        this.listaS = listaS;
    }
    

    
    
    
    // =========================================== METODOS =========================================== 
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
    
    
    public static ArrayList<Orcamento> getTodosOrcamentos(){
        ArrayList<Orcamento> lista = new ArrayList();
        ArrayList<OrcamentoProduto> produtos = new ArrayList();
        ArrayList<OrcamentoServico> servicos = new ArrayList();
        
        Orcamento novoOrcamento = new Orcamento();
        String sqlOferta = "select * from Orcamento ";
        
        String sqlServico ="select * from Orcamento_Servico where Orcamento_orc_cod = ";
        String sqlProduto = "select * from Orcamento_Produto where Orcamento_orc_cod  = ";
        
        String sqlIntermediaria;
        try{
            ResultSet rsO = Banco.con.consultar(sqlOferta);
            ResultSet rsProduto;
            ResultSet rsServico;
            
            while(rsO.next()){
                
                //mudando atributos da oferta
                novoOrcamento.setCodigo( rsO.getInt("orc_cod"));
                novoOrcamento.setDtValidade( rsO.getTimestamp("orc_data"));
                novoOrcamento.setDescricao("orc_desc");
                novoOrcamento.setStatus(rsO.getString("orc_status"));
                novoOrcamento.setValor(rsO.getDouble("orc_valor"));
                novoOrcamento.setDtAtual(rsO.getTimestamp("orc_data"));
                novoOrcamento.setCliente( new Cliente(rsO.getInt("Cliente_cli_cod")));
                
                
                
                //Busca dos Servicos daquela oferta
                sqlIntermediaria = sqlServico+" "+rsO.getInt("orc_cod")+"; ";     
                System.out.println("Sql Servico: "+sqlIntermediaria);

                
                /*BUSCAR SERVICOS (ORCAMENTO_SERVICOS)
                rsServico = Banco.con.consultar(sqlIntermediaria);
                if (rsServico != null) {
                    while(rsServico.next()){
                    
                        OfertaServico s = new OfertaServico( new Servico( rsServico.getInt("Servico_ser_cod") ), rsServico.getDouble("ofe_ser_valor") );
                        servicos.add(s);
                    }
                }
                */
                
                
                //Busca dos Produtos daquela oferta
                /*BUSCAR PRODUTOS (ORCAMENTO_PRODUTOS)
                sqlIntermediaria = sqlProduto+" "+rsO.getInt("orc_cod")+"; ";

                rsProduto = Banco.con.consultar(sqlIntermediaria);
               
              
                if(rsProduto != null){
                    while(rsProduto.next()){
                    
                        OfertaProduto p = new OfertaProduto( new Produto (rsProduto.getInt("Produto_pro_cod")) , rsProduto.getDouble("ofe_pro_valor"));
                        produtos.add(p);
                    
                    }
                }
                */
                
                novoOrcamento.setListaP(produtos);
                novoOrcamento.setListaS(servicos);
                
                
                
                lista.add(novoOrcamento);
                
                novoOrcamento = new Orcamento();
                produtos = new ArrayList();
                servicos = new ArrayList();
            }
            
        }catch(Exception ex){
            System.out.println("erro :"+Banco.con.getMensagemErro());
            lista = null;
        }
        return lista;
    }
}
