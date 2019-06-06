package g2informatica;

import controllers.CtrProduto;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Produto;

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
    private Spinner<Integer> spQtde;
    @FXML
    private ComboBox<String> cbPagamento;
    @FXML
    private DatePicker dpDataVencimento;

    CtrProduto ctrP = new CtrProduto();
    ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    ObservableList<Produto> listaProdutosEscolhidos = FXCollections.observableArrayList();
    Produto p, pe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja Limpar ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            listaProdutosEscolhidos.clear();
            tbProdutosEscolhidos.getItems().clear();
            pe = null;
            btRemover.setDisable(true);
            btCancelar.setDisable(true);
            btAdicionar.setDisable(true);
            btConfirma.setDisable(true);
            spQtde.setDisable(true);
        }
    }

    @FXML
    private void evtLimpaComprar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja Limpar ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            listaProdutos.clear();
            tbProdutos.getItems().clear();
            p = null;
            btRemover.setDisable(true);
            btCancelar.setDisable(true);
            btAdicionar.setDisable(true);
            btConfirma.setDisable(true);
            cbPagamento.setDisable(true);
        }
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
        ListaCEscolidas.remove(cpE);
        tbContasEscolhidas.getItems().clear();
        tbContasEscolhidas.getItems().addAll(ListaCEscolidas);
        btRemover.setDisable(true);
        btCancelar.setDisable(true);
        if(ListaCEscolidas.isEmpty())
        {
            btConfirma.setDisable(true);
        }
    }

    @FXML
    private void evtadicionar(ActionEvent event) {
        listaProdutosEscolhidos.add(p);
        tbProdutosEscolhidos.getItems().clear();
        tbProdutosEscolhidos.getItems().addAll(listaProdutosEscolhidos);
        btConfirma.setDisable(false);
        btRemover.setDisable(true);
        btCancelar.setDisable(true);
        btAdicionar.setDisable(true);
    }

    @FXML
    private void evtPesquisar(ActionEvent event) {
        
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        btRemover.setDisable(true);
        btCancelar.setDisable(true);
        btAdicionar.setDisable(true);
        p = null;
        pe = null;
    }

    @FXML
    private void evtCbFormaPagamento(ActionEvent event) {
    }

    @FXML
    private void evtTabelaCompras(MouseEvent event) {
        pe = tbProdutosEscolhidos.getSelectionModel().getSelectedItem();
        btRemover.setDisable(false);
        btCancelar.setDisable(false);
    }

    @FXML
    private void evtTabelaProdutos(MouseEvent event) {
        p = tbProdutos.getSelectionModel().getSelectedItem();
        btAdicionar.setDisable(false);
        btCancelar.setDisable(false);
    }
    
}
