/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ContasPagar;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class QuitarContasPagarController implements Initializable {

    @FXML
    private Button btConfirmar;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btSair;
    @FXML
    private Button btAdicionar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btRemover;
    @FXML
    private TextField txValorPago;
    @FXML
    private TableView<ContasPagar> tbContas;
    @FXML
    private TableColumn colCodC;
    @FXML
    private TableColumn colValoC;
    @FXML
    private TableColumn colDataC;
    @FXML
    private TableView<ContasPagar> tbContasEscolhidas;
    @FXML
    private TableColumn colCodCE;
    @FXML
    private TableColumn colValorCE;
    @FXML
    private TableColumn colDataCE;

    ctr
    ObservableList<ContasPagar> listaContas = FXCollections.observableArrayList();
    ObservableList<ContasPagar> ListaCEscolidas = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colCodC.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colCodCE.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colDataC.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colDataCE.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colValoC.setCellValueFactory(new PropertyValueFactory<>("valorConta"));
        colValorCE.setCellValueFactory(new PropertyValueFactory<>("valorConta"));
        
        
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) {
    }

    @FXML
    private void evtLImpar(ActionEvent event) {
    }

    @FXML
    private void evtSair(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja sair do Quitar Contas a Pagar? ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            FXMLprincipalController._pndados.getChildren().clear();
        }
    }

    @FXML
    private void evtAdiconar(ActionEvent event) {
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
    }

    @FXML
    private void evtRemovar(ActionEvent event) {
    }
    
}
