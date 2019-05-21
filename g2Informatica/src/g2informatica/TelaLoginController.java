/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrTelaLogin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtLogar(ActionEvent event)
    {
        CtrTelaLogin tl = new CtrTelaLogin();
        String nivel = "";
        String usu = txfUnsuario.toString(), sen = txfSenha.toString();
        nivel = tl.logarctr(usu , sen);
        
        if(nivel == "adm")
        {
            ni = 1;
            login = usu;
        }
        else if(nivel == "funcionario")
        {
            ni = 2;
            login = usu;
        }
        else if(nivel == "bloqueado")
        {
            ni = 0;
            login = usu;
        }
        else if(nivel == "")
        {
            ni=0;
            login = "";
        }
    }

    @FXML
    private void evtSair(ActionEvent event)
    {
        System.exit(0);
    }
    
    
}
