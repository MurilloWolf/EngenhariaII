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
    private String razao;
    private String logo; 
    private String CEP;
    private String email;
    private String telefone;
    private int codigo;

    public Empresa() {
        codigo = 1;
    }

    public Empresa(String nome, String razao, String logo, String CEP, String email, String telefone) {
        this.nome = nome;
        this.razao = razao;
        this.logo = logo;
        this.CEP = CEP;
        this.codigo = 1;
        this.email = email;
        this.telefone = telefone;
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

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
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
    
    
    
    @Override
    public String toString()
    {
        return nome;
    }
    
    public Empresa getParametrizacao()
    {
        Empresa p = new Empresa();
        
        String sql = "select * from Empresa";

        ResultSet rs = Banco.con.consultar(sql);
        
        try
        {
            if(rs.next())
            {
                p = new Empresa(rs.getString("emp_nome"), rs.getString("emp_razao"), null, rs.getString("emp_cep"),rs.getString("emp_email"), rs.getString("emp_telefone"));
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
        if(Banco.con.getMaxPK("Empresa","emp_cod") <1){
            
            sql = "insert into empresa (emp_cod,emp_nome,emp_cep,emp_email,emp_telefone,emp_missao) "
                    + "values ('$1','$2','$3','$4','$5','$6')";

            sql = sql.replace("$1",getCodigo()+"");
            sql = sql.replace("$2",getNome());
            sql = sql.replace("$3",getCEP());
            sql = sql.replace("$4",getEmail());
            sql = sql.replace("$5",getTelefone());
            sql = sql.replace("$6",getRazao());

            System.out.println("Sql: "+sql);
      //  if(getLogo()!=null)
      //      salvarImagem(getNome(), SwingFXUtils.fromFXImage(getLogo(), null));
        
        }
        
        return Banco.con.manipular(sql);

    }
    
    public boolean alterarParametrizacao() {
        String sql="update Empresa set emp_cep='$2',"+ "emp_email='$9',emp_telefone='$a', emp_missao='$b' where emp_cod = 1";
        
        sql = sql.replace("$2",getCEP());
       
        sql = sql.replace("$9",getEmail());
        sql = sql.replace("$a",getTelefone());
        sql = sql.replace("$b",getRazao());
        
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
