package g2informatica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import telaControladoras.DAOTelaLogin;

public class TelaLoguinController implements Initializable {

    @FXML
    private TextField txfUnsuario;
    @FXML
    private TextField txfSenha;
    @FXML
    private Button btnLogar;
    @FXML
    private Button btnSair;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtLogar(ActionEvent event)
    {
        DAOTelaLogin tl = new DAOTelaLogin();
        int nivel;
        String usu = txfUnsuario.toString(), sen = txfSenha.toString();
        nivel = tl.logarctr(usu , sen);
    }

    @FXML
    private void evtSair(ActionEvent event)
    {
        System.exit(0);
    }
    
}
