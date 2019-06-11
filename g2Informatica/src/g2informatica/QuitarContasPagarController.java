
package g2informatica;

import controllers.CtrQuitarContasApagar;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import models.ContasPagar;


public class QuitarContasPagarController implements Initializable {

    @FXML
    private Button btConfirmar;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btSair;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField txValorPago;
    @FXML
    private TableView<ContasPagar> tbContas;
    @FXML
    private Button btEstornar;
    @FXML
    private TableColumn colCod;
    @FXML
    private TableColumn colValoConta;
    @FXML
    private TableColumn colDataVenci;
    @FXML
    private TableColumn colValorPago;
    @FXML
    private TableColumn colDataPagamento;
    @FXML
    private TableColumn colDataReg;
    @FXML
    private TextField txValorContas;
    @FXML
    private ComboBox<String> cbFiltro;
    @FXML
    private TextField txPesquisa;
    @FXML
    private Button btPesquisa;
    @FXML
    private DatePicker dpDataPagamento;
    
    CtrQuitarContasApagar ctrQ = new CtrQuitarContasApagar();
    ObservableList<ContasPagar> listaContas = FXCollections.observableArrayList();
    ContasPagar cp;
    String flag = "pendente";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaContas.addAll(ctrQ.addTabelaContas(flag));
        tbContas.getItems().addAll(listaContas);
        ctrQ.IniciaEntidades(cp);
        colCod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colValoConta.setCellValueFactory(new PropertyValueFactory<>("valorConta"));
        colDataVenci.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colValorPago.setCellValueFactory(new PropertyValueFactory<>("valorPago"));
        colDataPagamento.setCellValueFactory(new PropertyValueFactory<>("dataPagamento"));
        colDataReg.setCellValueFactory(new PropertyValueFactory<>("dataConta"));
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        dpDataPagamento.setValue(LocalDate.now());
        btEstornar.setDisable(false);
        btLimpar.setDisable(false);
        
    }    

    @FXML
    private void evtConfirmar(ActionEvent event) throws SQLException {
        
        if (Double.parseDouble(txValorPago.getText()) <= Double.parseDouble(txValorContas.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tem certeza que deseja Confirmar ?");
            cp.setValorPago(Double.parseDouble(txValorPago.getText()));
            if (alert.showAndWait().get() == ButtonType.OK) {
                if (flag.equals("pendente")) {
                    if (ctrQ.pagarAScontas(cp, dpDataPagamento.getValue())) {
                        JOptionPane.showMessageDialog(null, "Quitar Conta realisada com sucesso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Quitar Conta falho");
                    }
                } else {
                    if (ctrQ.estronarConta(cp)) {
                        JOptionPane.showMessageDialog(null, "Estornar Conta realisada com sucesso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Estornar Conta falho");
                    }
                }
            }
            cp = null;
            dpDataPagamento.setValue(LocalDate.now());
            btCancelar.setDisable(true);
            btConfirmar.setDisable(true);
            btEstornar.setDisable(false);
            txValorContas.setText("");
            txValorPago.setText("");
            txPesquisa.setText("");
            listaContas.clear();
            tbContas.getItems().clear();
            flag = "pendente";
            listaContas.addAll(ctrQ.addTabelaContas(flag));
            tbContas.getItems().addAll(listaContas);
        }
        else
           JOptionPane.showMessageDialog(null, "valor a ser pago e maior que valor da conta");
        
    }

    @FXML
    private void evtLImpar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja Limpar ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            cp = null;
            dpDataPagamento.setValue(LocalDate.now());
            btCancelar.setDisable(true);
            btConfirmar.setDisable(true);
            btEstornar.setDisable(false);
            txValorContas.setText("");
            txValorPago.setText("");
            txPesquisa.setText("");
            listaContas.clear();
            tbContas.getItems().clear();
            flag = "pendente";
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
    private void evtCancelar(ActionEvent event) {
        
        cp=null;
        txValorContas.setText("");
        txValorPago.setText("");
        txPesquisa.setText("");
        dpDataPagamento.setValue(LocalDate.now());
        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        flag = "pendente";
    }

    @FXML
    private void evtTabelasContas(MouseEvent event) {
        cp = tbContas.getSelectionModel().getSelectedItem();
        btCancelar.setDisable(false);
        txValorContas.setText(cp.getValorConta()+"");
        txValorPago.setText(cp.getValorPago()+"");
        
        btConfirmar.setDisable(false);
    }


    @FXML
    private void evtEstornar(ActionEvent event) {
        cp = null;
        dpDataPagamento.setValue(LocalDate.now());
        btCancelar.setDisable(false);
        btConfirmar.setDisable(true);
        btEstornar.setDisable(true);
        txValorContas.setText("");
        txValorPago.setText("");
        txPesquisa.setText("");
        listaContas.clear();
        tbContas.getItems().clear();
        flag = "paga";
        listaContas.addAll(ctrQ.addTabelaContas(flag));
    }

    @FXML
    private void evtPesquisa(ActionEvent event) {
        
        if (cbFiltro.getSelectionModel().getSelectedIndex()!=0 && !txPesquisa.getText().isEmpty()) {
            switch (cbFiltro.getSelectionModel().getSelectedItem()) {
                case "codigo":
                    listaContas.addAll(ctrQ.addTabelaContasFiltro("Compra_com_cod", txPesquisa.getText(), flag));
                    break;
            }
        } 
        else{
            listaContas.addAll(ctrQ.addTabelaContas(flag));
        }
        
        tbContas.getItems().addAll(listaContas);
    }
    
}
