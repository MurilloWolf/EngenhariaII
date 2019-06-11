package g2informatica;

import controllers.CtrComprarProdutos;
import controllers.CtrFornecedor;
import controllers.CtrProduto;
import db.Banco;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import javax.swing.JOptionPane;
import models.Fornecedor;
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
    private ComboBox<String> cbTipoPesq;
    @FXML
    private Button btAdicionar;
    @FXML
    private Button btPesquisar;
    @FXML
    private ComboBox<Fornecedor> cbFornecedor;
    @FXML
    private Button btCancelar;
    @FXML
    private TableView<Produto> tbProdutosEscolhidos;
    @FXML
    private TableColumn colCodPE;
    @FXML
    private TableColumn colValorPE;
    @FXML
    private TableView<Produto> tbProdutos;
    @FXML
    private TableColumn colCodP;
    @FXML
    private TextField txValorTotal;
    @FXML
    private ComboBox<String> cbPagamento;
    @FXML
    private DatePicker dpDataVencimento;
    @FXML
    private TableColumn colQuantidadePE;
    @FXML
    private TableColumn colQuantidadeP;
    @FXML
    private TextField txValorCompra;
    @FXML
    private TableColumn colNomePE;
    @FXML
    private TableColumn colNomeP;
    @FXML
    private TextField txQtde;
    
    CtrComprarProdutos ctrCP = new CtrComprarProdutos();
    ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    ObservableList<Produto> listaProdutosEscolhidos = FXCollections.observableArrayList();
    Produto p, pe;
    Fornecedor f;
    int tipopagamento;
    double valorTotal = 0;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CtrFornecedor ctrF = new CtrFornecedor();
        colCodP.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colCodPE.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colNomeP.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNomePE.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colValorPE.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colQuantidadePE.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colQuantidadeP.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        cbFornecedor.getItems().addAll(ctrF.getListaDeFornecedores2());
        
        cbPagamento.getItems().add("Forma de Pagamento");
        cbPagamento.getItems().add("A Prazo");
        cbPagamento.getItems().add("A Vista");
        cbPagamento.getSelectionModel().select(0);
        
        cbTipoPesq.getItems().add("Filtro");
        cbTipoPesq.getItems().add("Cod");
        cbTipoPesq.getItems().add("Nome");
        cbTipoPesq.getSelectionModel().select(0);
        
        dpDataVencimento.setValue(LocalDate.now());
        ctrCP.inicializaCP(p, pe, f);
        listaProdutosEscolhidos.clear();
        tbProdutosEscolhidos.getItems().clear();
        btRemover.setDisable(true);
        btCancelar.setDisable(true);
        btAdicionar.setDisable(true);
        btConfirma.setDisable(true);
        cbPagamento.setDisable(true);
        txQtde.setDisable(true);
        txValorCompra.setDisable(true);
    }    

    @FXML
    private void evtConfirmaCompra(ActionEvent event) throws SQLException {
        if(cbFornecedor.getSelectionModel().getSelectedIndex()>=0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tem certeza que deseja Confirmar ?");
            f = cbFornecedor.getSelectionModel().getSelectedItem();//ver se tudo esta selecionado
            if (alert.showAndWait().get() == ButtonType.OK) {
                if (ctrCP.ConfirmarCompra(listaProdutosEscolhidos, f.getCodigo())) {
                    listaProdutosEscolhidos.clear();
                    tbProdutosEscolhidos.getItems().clear();
                    btConfirma.setDisable(true);
                    JOptionPane.showMessageDialog(null, "Compra realisada com sucesso");
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Escolha um fonecedor");
        }
    }

    @FXML
    private void evtLimpaProdutos(ActionEvent event) {
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
            txQtde.setDisable(true);
            txValorCompra.setDisable(true);
            txValorCompra.setText("");
            txQtde.setText("");
        }
    }

    @FXML
    private void evtLimpaComprar(ActionEvent event) {
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
            cbPagamento.setDisable(true);
            txQtde.setDisable(true);
            txValorCompra.setDisable(true);
            txValorCompra.setText("");
            txQtde.setText("");
            valorTotal=0;
            txValorTotal.setText("");
            
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
        listaProdutosEscolhidos.remove(pe);
        tbProdutosEscolhidos.getItems().clear();
        tbProdutosEscolhidos.getItems().addAll(listaProdutosEscolhidos);
        btRemover.setDisable(true);
        btCancelar.setDisable(true);
        valorTotal = valorTotal - pe.getPreco();
        if(listaProdutosEscolhidos.isEmpty())
        {
            btConfirma.setDisable(true);
        }
        pe=null;
        txValorTotal.setText(valorTotal+"");
    }
 
    @FXML
    private void evtadicionar(ActionEvent event) {
        p.setQuantidade(Integer.parseInt(txQtde.getText()));
        p.setPreco(Integer.parseInt(txValorCompra.getText()));
        if (!txQtde.getText().isEmpty() && !txValorCompra.getText().isEmpty()) {
            if (!listaProdutosEscolhidos.equals(p)) {
                if (!listaProdutosEscolhidos.equals(p)) {
                    valorTotal = valorTotal + p.getPreco();
                    listaProdutosEscolhidos.add(p);
                    tbProdutosEscolhidos.getItems().clear();
                    tbProdutosEscolhidos.getItems().addAll(listaProdutosEscolhidos);
                    btConfirma.setDisable(false);
                    btCancelar.setDisable(true);
                    btAdicionar.setDisable(true);
                    txQtde.setDisable(true);
                    txQtde.setText("");
                    txValorCompra.setDisable(true);
                    txValorCompra.setText("");
                    txValorTotal.setText(valorTotal + "");
                    p = null;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Produto ja foi selecionado");
            }
        }
        
    }

    @FXML
    private void evtPesquisar(ActionEvent event) {
        tbProdutos.getItems().clear();
        
        listaProdutos.clear();
        
        listaProdutos.addAll(ctrCP.Pesquisa());
        
        tbProdutos.getItems().addAll(listaProdutos);
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        if(!btAdicionar.isDisable())
        {
            p = null;
            btAdicionar.setDisable(true);
            btCancelar.setDisable(true);
            txQtde.setText("");
            txValorCompra.setText("");
        }
        else
            if(!btRemover.isDisable())
            {
                pe = null;
                btRemover.setDisable(true);
                btCancelar.setDisable(true);
                txQtde.setText("");
                txValorCompra.setText("");
            }
    }

    @FXML
    private void evtCbFormaPagamento(ActionEvent event) {
        tipopagamento = cbPagamento.getSelectionModel().getSelectedIndex();
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
        txQtde.setDisable(false);
        txValorCompra.setDisable(false);
        btAdicionar.setDisable(false);
        btCancelar.setDisable(false);
    }

    @FXML
    private void evtCbFornecedor(ActionEvent event) {
        
        f = cbFornecedor.getSelectionModel().getSelectedItem();
        
    }
    
}
