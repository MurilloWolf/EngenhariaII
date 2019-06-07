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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HafPc
 */
public class BuscaOrcamentosController implements Initializable {

    @FXML
    private ComboBox<?> cbFiltros;
    @FXML
    private TextField txtBusca;
    @FXML
    private TableView<?> tbOrcamento;
    @FXML
    private TableColumn<?, ?> clCodigo;
    @FXML
    private TableColumn<?, ?> clCliente;
    @FXML
    private TableColumn<?, ?> clPreco;
    @FXML
    private TableColumn<?, ?> clStatus;

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
    
}
