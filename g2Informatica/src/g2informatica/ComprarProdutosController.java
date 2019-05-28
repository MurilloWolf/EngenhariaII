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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Produto;

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
    @FXML
    private TableView<Produto> tbProdutosEscolhidos;
    @FXML
    private TableColumn colCodPE;
    @FXML
    private TableColumn colDescPE;
    @FXML
    private TableColumn colValorPE;
    @FXML
    private TableView<Produto> tbProdutos;
    @FXML
    private TableColumn colCodP;
    @FXML
    private TableColumn colDescP;
    @FXML
    private TableColumn colValorP;
    @FXML
    private TextField txValorTotal;
    @FXML
    private Spinner<?> spQtde;
    @FXML
    private ComboBox<?> cbPagamento;
    @FXML
    private DatePicker dpDataVencimento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colCodP.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colCodPE.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colDescP.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colDescPE.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValorP.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colValorPE.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja sair do Comprar Produtos ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            FXMLprincipalController._pndados.getChildren().clear();
        }
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

    @FXML
    private void evtCbFormaPagamento(ActionEvent event) {
    }
    
}
