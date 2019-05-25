/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrTelaLogin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author wolf
 */
public class TelaLoginController implements Initializable {

    @FXML
    private TextField txfUnsuario;
    @FXML
    private TextField txfSenha;
    @FXML
    private Button btnLogar;
    @FXML
    private Button btnSair;

    /**
     * Initializes the controller class.
     */
    
    protected static int ni;
    protected static String login = "";
    public static int cod;
    @FXML
    private AnchorPane apLog;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtLogar(ActionEvent event)
    {
        CtrTelaLogin tl = new CtrTelaLogin();
        String nivel = "";
        if (!verificaTodosCamposDaTela()) {
            String usu = txfUnsuario.getText().toString(), sen = txfSenha.getText().toString();
            nivel = tl.logarctr(usu, sen);

            if (nivel.equals("1")) {
                ni = 1;
                login = usu;
            } else if (nivel.equals("2")) {
                ni = 2;
                login = usu;
            } else if (nivel.equals("0")) {
                ni = 0;
                login = usu;
            } else {
                ni = 0;
                login = "";
            }
            btnLogar.getScene().getWindow().hide();
        }
    }

    @FXML
    private void evtSair(ActionEvent event)
    {
        btnSair.getScene().getWindow().hide();
    }
    
    
     private boolean verificaTodosCamposDaTela(){
        boolean erro = false;
        try{
            
            ObservableList<Node> obser =  apLog.getChildren();
            for (int i = 0; i < obser.size(); i++) {
                if(obser.get(i) instanceof TextField ){
                  erro =  verificaCampo((TextField) obser.get(i));
                }
            }
        }catch(Exception e){
            
        }
        
        return erro;
    }
    
    
    private boolean verificaCampo(TextField txt){
        boolean erro = false;
        int index;
        if(txt.getText().trim().isEmpty())
        {
            index = txt.getStyleClass().size() -1;
          
            if(!txt.getStyleClass().get(index).toString().equals("text-field"))
               txt.getStyleClass().remove(index);
                
            txt.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txt.getStyleClass().size() -1;
            
            if(txt.getStyleClass().get(index).toString().equals("text-field-erro"))
                txt.getStyleClass().remove(index);
            
            txt.getStyleClass().add("text-field-success");            
            

        }
        return erro;
    }
    
    
}
