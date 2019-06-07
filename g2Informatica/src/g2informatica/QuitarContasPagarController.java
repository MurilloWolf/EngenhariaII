
package g2informatica;

import controllers.CtrQuitarContasApagar;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import models.ContasPagar;


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


    CtrQuitarContasApagar ctrQ = new CtrQuitarContasApagar();
    ObservableList<ContasPagar> listaContas = FXCollections.observableArrayList();
    ObservableList<ContasPagar> ListaCEscolidas = FXCollections.observableArrayList();
    ContasPagar cp , cpE;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaContas.addAll(ctrQ.addTabelaContas());
        tbContas.getItems().addAll(listaContas);
        ctrQ.IniciaEntidades(cp,cpE);
        colCodC.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colCodCE.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colDataC.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colDataCE.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colValoC.setCellValueFactory(new PropertyValueFactory<>("valorConta"));
        colValorCE.setCellValueFactory(new PropertyValueFactory<>("valorConta"));
        btAdicionar.setDisable(true);
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btRemover.setDisable(true);
        
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja Confirmar ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            if (ctrQ.pagarAScontas(ListaCEscolidas)) {
                ListaCEscolidas.clear();
                tbContasEscolhidas.getItems().clear();
                btConfirmar.setDisable(true);
                listaContas.addAll(ctrQ.addTabelaContas());
            }
        }
      
    }

    @FXML
    private void evtLImpar(ActionEvent event) {

        ListaCEscolidas.clear();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja Limpar ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            ListaCEscolidas.clear();
            tbContasEscolhidas.getItems().clear();
            cp = null;
            btRemover.setDisable(true);
            btCancelar.setDisable(true);
            btAdicionar.setDisable(true);
            btConfirmar.setDisable(true);
        }
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
        cp=tbContas.getSelectionModel().getSelectedItem();
        ListaCEscolidas.add(cp);
        
        if(!tbContasEscolhidas.getItems().equals(cp))
        {
            ListaCEscolidas.add(cp);
            tbContasEscolhidas.getItems().clear();
            tbContasEscolhidas.getItems().addAll(ListaCEscolidas);
            btAdicionar.setDisable(true);
            btCancelar.setDisable(true);
            btConfirmar.setDisable(false);
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        if(!btAdicionar.isDisable())
        {
            cp = null;
            btAdicionar.setDisable(true);
            btCancelar.setDisable(true);
        }
        else
            if(!btRemover.isDisable())
            {
                cpE = null;
                btRemover.setDisable(true);
                btCancelar.setDisable(true);
            }
    }

    @FXML
    private void evtRemovar(ActionEvent event) {
        ListaCEscolidas.remove(tbContasEscolhidas.getSelectionModel().getSelectedItem().hashCode());
        tbContasEscolhidas.getItems().addAll(ListaCEscolidas);
        ListaCEscolidas.remove(cpE);
        tbContasEscolhidas.getItems().clear();
        tbContasEscolhidas.getItems().addAll(ListaCEscolidas);
        btRemover.setDisable(true);
        btCancelar.setDisable(true);
        if(ListaCEscolidas.isEmpty())
        {
            btConfirmar.setDisable(true);
        }
    }

    @FXML
    private void evtTabelasContas(MouseEvent event) {
        cp = tbContas.getSelectionModel().getSelectedItem();
        btAdicionar.setDisable(false);
        btCancelar.setDisable(false);
    }

    @FXML
    private void evtTabelaContasEscolhidas(MouseEvent event) {
        cpE = tbContasEscolhidas.getSelectionModel().getSelectedItem();
        btRemover.setDisable(false);
        btCancelar.setDisable(false);
    }
    
}
