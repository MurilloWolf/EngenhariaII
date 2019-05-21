package controllers;

import db.Banco;
import models.Empresa;
import java.util.ArrayList;
import javafx.scene.image.Image;
import models.Endereco;

public class CtrEmpresa {

    private Empresa empresa;
    
    public CtrEmpresa() {
        //se tem uma empresa cadastrada
        if(Empresa.getStatus()){
            empresa = new Empresa().getParametrizacao();
        }else{
            empresa = new Empresa();
        }
        
    }
    
    public Object getEmpresa(){
        return empresa;
    }
    
    public boolean salvarEmpresa(String nome, String missao, String email, String telefone, String uf, String cidade,
                                 String bairro, String rua, String numero, String pagina, String instagram, String logo){
       
        Endereco end = new Endereco(uf,cidade,bairro,rua,numero);
        
        boolean existe  = end.verificaEnderecoExistente();
        boolean resultado = true ; 
      
        if(!existe){
           resultado = end.salvar(); 
        }
        
        if(resultado){
            Empresa emp = new Empresa(nome, missao, email, telefone, end, pagina , instagram  );
            if(Empresa.getStatus()){

                
                return emp.salvarParametrizacao();
            }else{
                return emp.alterarParametrizacao();
            }
        }
        else{
            return false;
        }
        
       
    }
  
}
