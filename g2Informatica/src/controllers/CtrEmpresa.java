package controllers;

import db.Banco;
import models.Empresa;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class CtrEmpresa {

    static private CtrEmpresa ctr;
    
    private CtrEmpresa() {}
    
    static public CtrEmpresa iniciar()
    {
        if(ctr == null)
            ctr = new CtrEmpresa();
        
        return ctr;
    }/*
    
    public String getCidNome(Object o)
    {
        return ((Cidade)o).getNome();
    }
    public int getCidEstCod(Object o)
    {
        return ((Cidade)o).getEstado().getCod();
    }
    
    public int getEstCod(Object o)
    {
        return ((Estado)o).getCod();
    }
    
    public String getParaNome(Object o){
        return ((Empresa)o).getNome();
    }
    public String getParaRazao(Object o){
        return ((Empresa)o).getRazao();
    }
    public String getParaCEP(Object o){
        return ((Empresa)o).getCEP();
    }
    public String getParaLogradouro(Object o){
        return ((Empresa)o).getLogradouro();
    }
    public String getParaBairro(Object o){
        return ((Empresa)o).getBairro();
    }
    public String getParaCor(Object o){
        return ((Empresa)o).getCor();
    }
    public String getParaFonte(Object o){
        return ((Empresa)o).getFonte();
    }
    public String getParaSite(Object o){
        return ((Empresa)o).getSite();
    }
    public String getParaTelefone(Object o){
        return ((Empresa)o).getTelefone();
    }
    public String getParaEmail(Object o){
        return ((Empresa)o).getEmail();
    }
    public Image getParaLogo(Object o){
        return ((Empresa)o).getLogo();
    }
    public int getParaCidEstCod(Object o){
        return ((Empresa)o).getCidade().getEstado().getCod();
    }
    public String getParaCidNome(Object o)
    {
        return ((Empresa)o).getCidade().getNome();
    }
    
    public int CountParametrizacao() {
        return Banco.getCon().getCount("parametrizacao", "par_nome");
    }
    
    public Object getParametrizacao()
    {
        return new Empresa().getParametrizacao();
    }
    
    public Object getCidade(String nome)
    {
        Object o = new Cidade().getCidade(nome);
        return o;
    }

    public Object getEstado(int cod)
    {
        Object o = new Estado().getEstado(cod);
        return o;
    }
    
    public ArrayList<Object> getEstados(String filtro)
    {
        ArrayList<Object> Lestado = new Estado().getEstados(filtro);
        
        return Lestado;
    }
    
    public ArrayList getCidades(String filtro)
    {
        ArrayList<Object> L = new Cidade().getCidades(filtro);
        return L;
    }

    public boolean salvarParametrizacao(String nome, String razao, Image img, String cep, String logradouro, String bairro, Object cidade, String cor, String fonte, String site, String email, String telefone) {
        
        Empresa par = new Empresa(nome, razao, img, cep, logradouro, bairro, (Cidade)cidade, cor, fonte, site, email, telefone);
        return par.salvarParametrizacao();
    }
    
    public boolean alterarParametrizacao(String nome, String razao, Image img, String cep, String logradouro, String bairro, Object cidade, String cor, String fonte, String site, String email, String telefone) {
        Empresa par = new Empresa(nome, razao, img, cep, logradouro, bairro, (Cidade)cidade, cor, fonte, site, email, telefone);
        
        return par.alterarParametrizacao();
    }

    public boolean apagarParametrizacao(String text) {
        return Banco.getCon().manipular("delete from parametrizacao where par_nome='"+text+"'");
    }*/
}
