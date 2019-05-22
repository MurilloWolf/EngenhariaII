package models;

import db.Banco;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Empresa {
    private String nome;
    private String missao;
    private String logo; 

    private String email;
    private String paginaFb;
    private String instagram;
    private String cnpj;
    private String site;
    
    private String telefone;
    private int codigo;
    private Endereco end;

    public Empresa() {
        codigo = 1;
    }

    public Empresa(String nome, String missao, String logo,  String email, String telefone) {
        this.nome = nome;
        this.missao = missao;
        this.logo = logo;
        this.codigo = 1;
        this.email = email;
        this.telefone = telefone;
        this.end = new Endereco();
    }

    public Empresa(String nome, String missao,  String email, String telefone, Endereco end) {
        this.nome = nome;
        this.missao = missao;
       
        this.email = email;
        this.telefone = telefone;
        this.end = end;
    }

    public Empresa(String nome, String missao,String email, String telefone, Endereco end, String pagina, String instagram) {
        this.nome = nome;
        this.missao = missao;
        this.email = email;
        this.telefone = telefone;
        this.end = end;
        this.paginaFb = pagina;
        this.instagram = instagram;
    }

    public Empresa(String nome, String missao, String email, String telefone,String cnpj,String paginaFb,
            String site,String instagram, Endereco endereco){
        
        this.nome = nome;
        this.missao = missao;
    
        this.email = email;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.end = end;
        this.site = site;
        this.paginaFb = paginaFb;
        this.instagram = instagram;
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

    public String getMissao() {
        return missao;
    }

    public void setMissao(String Missao) {
        this.missao = missao;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPaginaFb() {
        return paginaFb;
    }

    public void setPaginaFb(String paginaFb) {
        this.paginaFb = paginaFb;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }
 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    
    
    @Override
    public String toString()
    {
        return nome;
    }
    
    
 // ======================================================== SQL ========================================================  
    
    public Empresa getParametrizacao()
    {
        Empresa p = new Empresa();
        
        String sql = "select * from Empresa";

        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            if(rs.next())
            {
                p = new Empresa(rs.getString("emp_nome"), rs.getString("emp_missao"),
                        rs.getString("emp_email"), rs.getString("emp_telefone"),rs.getString("emp_cnpj"),
                        rs.getString("emp_paginaFb"),rs.getString("emp_site"),rs.getString("emp_instagram"), new Endereco (rs.getInt("Endereco_end_cod")));
            }
        }
        catch(Exception e)
        {
            System.out.println("Erro: " + Banco.con.getMensagemErro());
        }
        
       
        
        return p;
    }
    
    public boolean salvarParametrizacao() {
        String sql= "";
        boolean resultado = false;
        
        
            
            if(Banco.con.getMaxPK("Empresa","emp_cod") <1){
            
              
                //pode cadastrar, porque o endereco ja foi cadastrado
                
                sql = "insert into empresa (emp_cod,emp_nome,emp_email,emp_telefone,emp_missao,emp_cnpj,emp_site,emp_paginaFb,emp_instagram) "
                        + "values ('$1','$2','$3','$4','$5','$6','$7','$8','$9')";

                sql = sql.replace("$1",getCodigo()+"");
                sql = sql.replace("$2",getNome());
                sql = sql.replace("$3",getEmail());
                sql = sql.replace("$4",getTelefone());
                sql = sql.replace("$5",getMissao());
                sql = sql.replace("$6",getCnpj());
                sql = sql.replace("$7",getSite());
                sql = sql.replace("$8",getPaginaFb());
                sql = sql.replace("#9",getInstagram());

                System.out.println("Sql: "+sql);
                //  if(getLogo()!=null)
                //      salvarImagem(getNome(), SwingFXUtils.fromFXImage(getLogo(), null));

                    resultado = Banco.con.manipular(sql);
                
            }
     
       
        
        return resultado;

    }
    
    public boolean alterarParametrizacao() {
        String sql="update Empresa set emp_email='$2',emp_telefone='$3', emp_missao='$4' , emp_cnpj = '$5', emp_site ='$6', emp_paginaFb='$7', emp_instagram = '$8',"
                + " emp_nome = '$9' where emp_cod = 1";
        
     
        sql = sql.replace("$2",getEmail());
        sql = sql.replace("$3",getTelefone());
        sql = sql.replace("$4",getMissao());
        sql = sql.replace("$5",getCnpj());
        sql = sql.replace("$6",getSite());
        sql = sql.replace("$7",getPaginaFb());
        sql = sql.replace("$8",getInstagram());
        sql = sql.replace("$9",getNome());
       
        System.out.println("sql: "+sql);     
        
        
        
     //   if(getLogo()!=null)
     //       salvarImagem(getNome(), SwingFXUtils.fromFXImage(getLogo(), null));
        
        return Banco.con.manipular(sql);
    }
    
    public static boolean getStatus(){
        if(Banco.con.getMaxPK("Empresa","emp_cod") == 1)
            return true;
        
        return false;
    }
  

    
}
