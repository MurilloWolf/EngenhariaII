
package g2informatica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroFuncionarioController implements Initializable {

    @FXML
    private Button btnNovo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnPesquisa;
    @FXML
    private TextField txfPesquisa;
    @FXML
    private ComboBox<?> cbPesquisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtNovo(ActionEvent event)
    {
    }

    @FXML
    private void evtEditar(ActionEvent event)
    {
    }

    @FXML
    private void evtExcluir(ActionEvent event)
    {
    }

    @FXML
    private void evtConfirmar(ActionEvent event)
    {
    }

    @FXML
    private void evtCancelar(ActionEvent event)
    {
    }

    @FXML
    private void evtSair(ActionEvent event)
    {
        FXMLprincipalController._pndados.getChildren().clear();
    }

    @FXML
    private void evtPesquisa(ActionEvent event)
    {
    }
    
}