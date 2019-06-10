/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author wolf
 */



public class Oferta {
    private int codigo;
    private Timestamp dataInicio;
    private Timestamp dataFinal;
    private String descricao;
    private Funcionario funcionario;
    private ArrayList<OfertaProduto> listaOfertaProduto;
    private ArrayList<OfertaServico> listaOfertaServico;

    public Oferta(ObservableList<OfertaProduto> listaOP, ObservableList<OfertaServico> listaOS, Timestamp dtI, Timestamp dtF, String nome) {
        if(listaOP == null || listaOP.size() <=0)
            this.listaOfertaProduto = null;
        else{
            this.listaOfertaProduto = new ArrayList();
            for (int i = 0; i < listaOP.size(); i++) {
                this.listaOfertaProduto.add(listaOP.get(i));
            }
        }
        
        if(listaOS == null || listaOS.size() <=0)
            this.listaOfertaServico = null;
        else{
            this.listaOfertaServico = new ArrayList();
            for (int i = 0; i < listaOS.size(); i++) {
                this.listaOfertaServico.add(listaOS.get(i));
            }
        }
            
        
        this.dataInicio = dtI;
        this.dataFinal = dtF;
        
        this.descricao = nome;
        this.funcionario = null;
        
    }
    
    public Oferta(int codigo){
        this.codigo = codigo;
        
    }
    public Oferta(){
        
    }

    
    
    
    
    
    public ArrayList<OfertaProduto> getListaOfertaProduto() {
        return listaOfertaProduto;
    }

    public void setListaOfertaProduto(ArrayList<OfertaProduto> listaOfertaProduto) {
        this.listaOfertaProduto = listaOfertaProduto;
    }

    public ArrayList<OfertaServico> getListaOfertaServico() {
        return listaOfertaServico;
    }

    public void setListaOfertaServico(ArrayList<OfertaServico> listaOfertaServico) {
        this.listaOfertaServico = listaOfertaServico;
    }
    
    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Timestamp getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Timestamp dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descicao) {
        this.descricao = descicao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    // ======================================= SALVAR PRODUTO/SERVICO =======================================
    public boolean salvarOfertaProduto(){
        int cont = 0;
        
        for(OfertaProduto i : listaOfertaProduto){
            if(i.salvarOferta(this.codigo))
                cont++;
        }
        
        if(cont == listaOfertaProduto.size())
            return true;
        
        return false ;
    }
    
    public boolean salvarOfertaServico(){
        int cont = 0;
        
        for(OfertaServico i : listaOfertaServico){
            if(i.salvarOferta(this.codigo))
                cont++;
        }
        
        if(cont == listaOfertaServico.size())
            return true;
        
        return false ;
    }
    
    // ======================================= ALTERAR PRODUTO/SERVICO =======================================

    public boolean alterarOfertaProduto(){
         int cont = 0;
        
        for(OfertaProduto i : listaOfertaProduto){
            if(i.alterarOferta(this.codigo))
                cont++;
        }
        
        if(cont == listaOfertaProduto.size())
            return true;
        
        return false ;
        
    }
    
    public boolean alterarOfertaServico(){
        int cont = 0;
        
        for(OfertaServico i : listaOfertaServico){
            if(i.alterarOferta(this.codigo))
                cont++;
        }
        
        if(cont == listaOfertaServico.size())
            return true;
        
        return false ;
    }
    
    public boolean salvar() throws SQLException{
        boolean salvarServico =true ;
        boolean salvarProduto =true ;
        boolean resultadoFinal = false;
        try{
            Banco.con.getConnect().setAutoCommit(false);
        
        
         /*FORMATO DATA YYYY-MM-DD hh:mm:ss*/
           

           String sql="insert into Oferta (ofe_cod,ofe_dataInicio,ofe_dataFinal,ofe_desc,Funcionario_fun_cod) values ($1,'$2','$3','$4',$5);";
           sql = sql.replace("$1",this.codigo+"" );
           sql = sql.replace("$2",this.getDataInicio().toLocalDateTime().toString() );
           sql = sql.replace("$3",this.getDataFinal().toLocalDateTime().toString());
           sql = sql.replace("$4",this.descricao );
           
           if(this.funcionario==null)
           sql = sql.replace("$5","null" );

            System.out.println("Sql: "+sql);
            resultadoFinal = Banco.con.manipular(sql);
            
            this.codigo = Banco.con.getMaxPK("Oferta","ofe_cod");
            
            if(this.listaOfertaServico!=null)
                salvarServico = salvarOfertaServico();

            if(this.listaOfertaProduto!=null)
                salvarProduto = salvarOfertaProduto();
            
            
            Banco.con.getConnect().commit();
        }catch(Exception e){
            
            Banco.con.getConnect().rollback();
            
        }finally{
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return salvarProduto && salvarServico && resultadoFinal; 
    }
    
    
    public boolean alterar () throws SQLException{
        boolean resultado = true ;
        boolean salvarServico =true ;
        boolean salvarProduto =true ;
        boolean resultadoFinal = false;
        try{
            Banco.con.getConnect().setAutoCommit(false);
        
        
         /*FORMATO DATA YYYY-MM-DD hh:mm:ss*/
           

           String sql="update Oferta set ofe_dataInicio = '$1', ofe_dataFinal = '$2', ofe_desc = '$3' where ofe_cod = "+this.getCodigo()+";";
           
           sql = sql.replace("$1",this.getDataInicio().toLocalDateTime().toString() );
           sql = sql.replace("$2",this.getDataFinal().toLocalDateTime().toString());
           sql = sql.replace("$3",this.descricao );
           
           

            System.out.println("Sql: "+sql);
            resultadoFinal = Banco.con.manipular(sql);
            
            
            if(this.listaOfertaServico!=null)
                salvarServico = alterarOfertaServico();

            if(this.listaOfertaProduto!=null)
                salvarProduto = alterarOfertaProduto();
            
            
            Banco.con.getConnect().commit();
        }catch(Exception e){
            
            Banco.con.getConnect().rollback();
            
        }finally{
            Banco.con.getConnect().setAutoCommit(true);
        }
        
        return salvarProduto && salvarServico && resultadoFinal; 
        
        
    }
    
    public ArrayList<Oferta> buscarOfertas(String where){
        ArrayList<Oferta> lista = new ArrayList();
        ArrayList<OfertaServico> servicos = new ArrayList();
        ArrayList<OfertaProduto> produtos = new ArrayList();
        
        Oferta novaOferta = new Oferta();
        String sqlOferta = "select * from Oferta "+where;
        System.out.println("Busca generica: "+sqlOferta);
        
        String sqlServico ="select * from Oferta_Servico where Oferta_ofe_cod = ";
        String sqlProduto = "select * from Oferta_Produto where Oferta_ofe_cod = ";
        
        String sqlIntermediaria;
        try{
            ResultSet rsOferta = Banco.con.consultar(sqlOferta);
            ResultSet rsProduto;
            ResultSet rsServico;
            
            while(rsOferta.next()){
                
                //mudando atributos da oferta
                novaOferta.setCodigo( rsOferta.getInt("ofe_cod"));
                novaOferta.setDataFinal(rsOferta.getTimestamp("ofe_dataFinal"));
                novaOferta.setDataInicio(rsOferta.getTimestamp("ofe_dataInicio"));
                novaOferta.setDescricao(rsOferta.getString("ofe_desc"));
                novaOferta.setFuncionario( new Funcionario (rsOferta.getInt("Funcionario_fun_cod")) );
                
                
                
                //Busca dos Servicos daquela oferta
                sqlIntermediaria = sqlServico+" "+rsOferta.getInt("ofe_cod")+"; ";     
                System.out.println("Sql Servico: "+sqlIntermediaria);

                rsServico = Banco.con.consultar(sqlIntermediaria);
                if (rsServico != null) {
                    while(rsServico.next()){
                    
                        OfertaServico s = new OfertaServico( new Servico( rsServico.getInt("Servico_ser_cod") ), rsServico.getDouble("ofe_ser_valor") );
                        servicos.add(s);
                    }
                }
                
                
                
                //Busca dos Produtos daquela oferta
                sqlIntermediaria = sqlProduto+" "+rsOferta.getInt("ofe_cod")+"; ";
                System.out.println("Sql Produto: "+sqlIntermediaria);
                rsProduto = Banco.con.consultar(sqlIntermediaria);
               
              
                if(rsProduto != null){
                    while(rsProduto.next()){
                    
                        OfertaProduto p = new OfertaProduto( new Produto (rsProduto.getInt("Produto_pro_cod")) , rsProduto.getDouble("ofe_pro_valor"));
                        produtos.add(p);
                    
                    }
                }
                
                
                novaOferta.setListaOfertaProduto(produtos);
                novaOferta.setListaOfertaServico(servicos);
                
                lista.add(novaOferta);
                
                novaOferta = new Oferta();
                produtos = new ArrayList();
                servicos = new ArrayList();
            }
            
        }catch(Exception ex){
            System.out.println("erro :"+Banco.con.getMensagemErro());
            lista = null;
        }
        return lista; 
    }

    public ArrayList<Oferta> buscarTodasOfertas() {
        ArrayList<Oferta> lista = new ArrayList();
        ArrayList<OfertaServico> servicos = new ArrayList();
        ArrayList<OfertaProduto> produtos = new ArrayList();
        
        Oferta novaOferta = new Oferta();
        String sqlOferta = "select * from Oferta where ofe_dataFinal >= SYSDATE() or ofe_dataFinal IS NOT NULL;";
        String sqlServico ="select * from Oferta_Servico where Oferta_ofe_cod = ";
        String sqlProduto = "select * from Oferta_Produto where Oferta_ofe_cod = ";
        
        String sqlIntermediaria;
        try{
            ResultSet rsOferta = Banco.con.consultar(sqlOferta);
            ResultSet rsProduto;
            ResultSet rsServico;
            
            while(rsOferta.next()){
                
                //mudando atributos da oferta
                novaOferta.setCodigo( rsOferta.getInt("ofe_cod"));
                novaOferta.setDataFinal(rsOferta.getTimestamp("ofe_dataFinal"));
                novaOferta.setDataInicio(rsOferta.getTimestamp("ofe_dataInicio"));
                novaOferta.setDescricao(rsOferta.getString("ofe_desc"));
                novaOferta.setFuncionario( new Funcionario (rsOferta.getInt("Funcionario_fun_cod")) );
                
                
                
                //Busca dos Servicos daquela oferta
                sqlIntermediaria = sqlServico+" "+rsOferta.getInt("ofe_cod")+"; ";     
                System.out.println("Sql Servico: "+sqlIntermediaria);

                rsServico = Banco.con.consultar(sqlIntermediaria);
                if (rsServico != null) {
                    while(rsServico.next()){
                    
                        OfertaServico s = new OfertaServico( new Servico( rsServico.getInt("Servico_ser_cod") ), rsServico.getDouble("ofe_ser_valor") );
                        servicos.add(s);
                    }
                }
                
                
                
                //Busca dos Produtos daquela oferta
                sqlIntermediaria = sqlProduto+" "+rsOferta.getInt("ofe_cod")+"; ";
                System.out.println("Sql Produto: "+sqlIntermediaria);
                rsProduto = Banco.con.consultar(sqlIntermediaria);
               
              
                if(rsProduto != null){
                    while(rsProduto.next()){
                    
                        OfertaProduto p = new OfertaProduto( new Produto (rsProduto.getInt("Produto_pro_cod")) , rsProduto.getDouble("ofe_pro_valor"));
                        produtos.add(p);
                    
                    }
                }
                
                
                novaOferta.setListaOfertaProduto(produtos);
                novaOferta.setListaOfertaServico(servicos);
                
                lista.add(novaOferta);
                
                novaOferta = new Oferta();
                produtos = new ArrayList();
                servicos = new ArrayList();
            }
            
        }catch(Exception ex){
            System.out.println("erro :"+Banco.con.getMensagemErro());
            lista = null;
        }
        return lista; 
        
    }
    
    // ================================== EXCLUIR ===================================
    public boolean excluir(){
        boolean resultadoProduto =true ; 
        boolean resultadoServico = true;
        boolean resultadoOferta = true;
        
        String sql = "delete from Oferta where ofe_cod = "+this.getCodigo()+";";
        
        
        try{
            
            resultadoProduto = OfertaProduto.deletarOferta(this.getCodigo());
            resultadoServico = OfertaServico.deletarOferta(this.getCodigo());
            
            resultadoOferta = Banco.con.manipular(sql);
            
            
        }catch(Exception e){
            
        }
        
        return true;
        //return resultadoProduto && resultadoServico && resultadoOferta;
    }

    public ArrayList<Oferta> buscarOfertasEncerradas() {
        String sql = "where ofe_dataFinal IS NULL or ofe_dataFinal < SYSDATE();";
        return buscarOfertas(sql);

    }
    
    public ArrayList<Oferta> buscarOfertasDataFinal() {
        String sql = "where ofe_dataFinal like '"+this.getDataFinal()+"';";
        return buscarOfertas(sql);

    }
    
    public ArrayList<Oferta> buscarOfertasPorNome(String nome) {
        String sql = "where ofe_desc like '%"+nome+"%';";
        return buscarOfertas(sql);

    }
    
    public ArrayList<Oferta> buscarOfertasFuturas() {
        String sql = "where ofe_dataFinal IS NULL or ofe_dataFinal < SYSDATE();";
        return buscarOfertas(sql);

    }
}
