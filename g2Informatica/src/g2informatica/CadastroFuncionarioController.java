
package g2informatica;

import eng2.util.MaskFieldUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.dao.DaoFuncionario;

public class CadastroFuncionarioController implements Initializable {

    @FXML
    private VBox pnCad;
    @FXML
    private HBox hbBotoes;
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
    private AnchorPane apDados;
    @FXML
    private TextField txNome;
    @FXML
    private TextField txCpf;
    @FXML
    private TextField txEndereco;
    @FXML
    private TextField txCep;
    @FXML
    private TextField txUf;
    @FXML
    private TextField txTelefone;
    @FXML
    private TextField txBairro;
    @FXML
    private TextField txLogin;
    @FXML
    private TextField txSenha;
    @FXML
    private TextField txApt;
    @FXML
    private TextField txNumero;
    @FXML
    private TextField txRg;
    @FXML
    private TextField txCidede;
    @FXML
    private TextField txEmail;
    @FXML
    private HBox hbPesquisa;
    @FXML
    private Button btnPesquisa;
    @FXML
    private TextField txfPesquisa;
    @FXML
    private ComboBox<String> cbPesquisa;
    @FXML
    private TableView<?> tbPesquisaFuncionario;
    @FXML
    private TableColumn tcCodigo;
    @FXML
    private TableColumn tcNome;
    @FXML
    private TableColumn tcCep;
    @FXML
    private TableColumn tcCpf;
    @FXML
    private TableColumn tcEmail;

    //ObservableList<Produto> lista = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.cpfField(txCpf);
        MaskFieldUtil.foneField(txTelefone);
        MaskFieldUtil.cepField(txCep);
        
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("cod"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        apDados.setDisable(true);
        btnCancelar.setDisable(true);
        btnConfirmar.setDisable(true);
        hbPesquisa.setDisable(true);

        cbPesquisa.getItems().add("Nome");
        cbPesquisa.getItems().add("Cpf");
        cbPesquisa.getItems().add("Codigo");
        cbPesquisa.getItems().add("login");
        cbPesquisa.getSelectionModel().select(0);
        
    }
    
    void printaTabela()
    {
        tbPesquisaFuncionario.getItems().clear();
        DaoFuncionario daoAux = new DaoFuncionario();
        
        //lista.clear();
        //lista.addAll(daoAux.getFucionario(""));
        
        //tbPesquisaFuncionario.getItems().addAll(lista);
    }

    @FXML
    private void evtNovo(ActionEvent event) {
        
    }

    @FXML
    private void evtEditar(ActionEvent event) {
    }

    @FXML
    private void evtExcluir(ActionEvent event) {
    }

    @FXML
    private void evtConfirmar(ActionEvent event) {
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
    }

    @FXML
    private void evtSair(ActionEvent event) {
    }

    @FXML
    private void evtPesquisa(ActionEvent event) {
    }
    
}