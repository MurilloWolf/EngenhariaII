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
 * @author Aluno
 */
public class ComprarProdutosController implements Initializable {

    @FXML
    private Button btConfirma;
    @FXML
    private Button btLimpaP;
    @FXML
    private Button btLimpaC;
    @FXML
    private Button btSair;
    @FXML
    private Button btRemover;
    @FXML
    private TextField txPesquisa;
    @FXML
    private ComboBox<?> cbTipoPesq;
    @FXML
    private Button btAdicionar;
    @FXML
    private Button btPesquisar;
    @FXML
    private ComboBox<?> cbFornecedor;
    @FXML
    private Button btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtConfirmaCompra(ActionEvent event) {
    }

    @FXML
    private void evtLimpaProdutos(ActionEvent event) {
    }

    @FXML
    private void evtLimpaComprar(ActionEvent event) {
    }

    @FXML
    private void evtSair(ActionEvent event) {
    }

    @FXML
    private void evtRemover(ActionEvent event) {
    }

    @FXML
    private void evtadicionar(ActionEvent event) {
    }

    @FXML
    private void evtPesquisar(ActionEvent event) {
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
    }
    
}
