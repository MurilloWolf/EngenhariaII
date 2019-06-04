/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrProduto;
import controllers.CtrServico;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.OfertaProduto;
import models.OfertaServico;
import models.Produto;
import models.Servico;

/**
 * FXML Controller class
 *
 * @author wolf
 */
public class CrudOfertasController implements Initializable {

    CtrProduto ctrP = new CtrProduto();
    CtrServico ctrS = new CtrServico();
    @FXML
    private VBox vboxCentral;
    @FXML
    private VBox vboxDireita;
    @FXML
    private ComboBox<String> cbProdutos;
    @FXML
    private Button btnAdicionarProdutos;
    @FXML
    private TextField txtPorcentagemProduto;
    @FXML
    private TextField txtValorProduto;
    @FXML
    private ComboBox<String> cbServicos;
    @FXML
    private Button btnAdicionarServicos;
    @FXML
    private TextField txtPorcentagemServico;
    @FXML
    private TextField txtValorServico;
    @FXML
    private DatePicker dtInicial;
    @FXML
    private DatePicker dtFinal;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<OfertaProduto> tbProduto;
    @FXML
    private TableView<OfertaServico> tbServico;
    
    ArrayList<Servico> listaS;
    ArrayList<Produto> listaP;
    @FXML
    private TableColumn clProduto;
    @FXML
    private TableColumn clValorProdutos;
    @FXML
    private TableColumn clServico;
    @FXML
    private TableColumn clValorServico;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        linkarTabela();
        limparTela();
        carregarProdutos();
        carregarServicos();
    }    

    @FXML
    private void addProdutos(ActionEvent event) {
    }

    @FXML
    private void addServico(ActionEvent event) {
    }

    @FXML
    private void clickConfirmar(ActionEvent event) {
        
        
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
    }

    @FXML
    private void clickRemover(ActionEvent event) {
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja cancelara operação ? ");
        
        if(alert.showAndWait().get() ==ButtonType.OK){
           
            Stage stage = (Stage) btnAlterar.getScene().getWindow(); //Obtendo a janela atual
            stage.close(); 
        } 
        
       
    }
    
    // ============================================== TELA ==============================================
    
    private void limparTela(){
        txtPorcentagemProduto.clear();
        txtPorcentagemServico.clear();
        
        txtValorProduto.clear();
        txtValorServico.clear();
        
        cbProdutos.getSelectionModel().clearSelection();
        cbServicos.getSelectionModel().clearSelection();
    }
    
    private void botoesEstadoInicial(){
        
    }
    
    private void carregarProdutos(){
        cbProdutos.getItems().clear();
        listaP = ctrP.getAllProdutos();
        for(Produto p : listaP ){
            cbProdutos.getItems().add(p.getNome());
            
        }
    }
    
    private void carregarServicos(){
        cbServicos.getItems().clear();
        listaS = ctrS.getAllServicos();
        for(Servico s : listaS ){
            cbServicos.getItems().add(s.getDescricao());
            
        }
    }
    
    private void linkarTabela(){
        clProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clValorProdutos.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        
        clServico.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clValorServico.setCellValueFactory(new PropertyValueFactory<>("preco"));

        
        
    }
    
}
