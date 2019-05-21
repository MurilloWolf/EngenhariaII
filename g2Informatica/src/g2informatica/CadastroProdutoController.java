/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author wolf
 */
public class CadastroProdutoController implements Initializable {

    @FXML
    private ComboBox<?> cbFiltro;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPreco;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickBuscar(ActionEvent event) {
    }

    @FXML
    private void clickCadastrar(ActionEvent event) {
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
    }

    @FXML
    private void clickExcluir(ActionEvent event) {
    }

    @FXML
    private void clickCanccelar(ActionEvent event) {
    }
    
}
