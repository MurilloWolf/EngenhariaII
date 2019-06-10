/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrOferta;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Oferta;
import models.OfertaProduto;
import models.OfertaServico;
import models.Produto;
import models.Servico;


/**
 * FXML Controller class
 *
 * @author wolf
 */
public class GerenciarOfertaController implements Initializable {

    
    
    ObservableList<Oferta> lista = FXCollections.observableArrayList();
    Oferta ofe;
    
    public static String operacao="";
    public static Oferta ofertaAlterar;
    public static VBox _painel;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCancelar;
    @FXML
    private VBox hboxCentral;
    @FXML
    private ComboBox<String> cbFiltros;
    @FXML
    private TextField txtBusca;
    @FXML
    private Button btnBuscar;
    
    CtrOferta ctr = new CtrOferta();
    
    @FXML
    private TableView<Oferta> tbOferta;
    @FXML
    private TableView<OfertaProduto> tbProduto;
    @FXML
    private TableView<OfertaServico> tbServico;
    @FXML
    private TableColumn tbDescricao;
    @FXML
    private TableColumn tbDataInicial;
    @FXML
    private TableColumn tbDataFinal;
    @FXML
    private TableColumn clProduto;
    @FXML
    private TableColumn clValorPro;
    @FXML
    private TableColumn clServico;
    @FXML
    private TableColumn clValorSer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregaComboBox();
        linkarTabelas();
        botoesEstadoInicial();
        limparTela();
        
        
        
    }   


    private void clickOfertasAbertas(ActionEvent event) {
         try{
            Parent root = FXMLLoader.load(getClass().getResource("OfertasEmAberto.fxml"));
            FXMLprincipalController._pndados.getChildren().clear();
            FXMLprincipalController._pndados.getChildren().add(root);
            
        }catch(Exception e ){
            System.out.println("Erro :"+e);
        }
        
    }

    @FXML
    private void clickNovo(ActionEvent event) {
           try {
               
            operacao = "novo";
               
            Parent root = FXMLLoader.load(getClass().getResource("CrudOfertas.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
        }
         
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
        
        if(ofe!=null){
            try {
           
                if(ofe.getDataFinal().after( new Timestamp(System.currentTimeMillis()))){
                    
                    operacao = "alterar";

                    Parent root = FXMLLoader.load(getClass().getResource("CrudOfertas.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Não é possivel alterar uma oferta que ja se encerrou");
                    alert.showAndWait();
                }
            
            
            
            
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        
    }

    @FXML
    private void clickExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja excluir essa Promoção ");
        if(alert.showAndWait().get() ==ButtonType.OK){
            
            if(ctr.deletarOferta(ofe.getCodigo())){
                limparTela();
                botoesEstadoInicial();
                limparTabelas();
                
            }else{
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setContentText("Não foi possivel excluir a Oferta");
                erro.showAndWait();
            }

           
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        botoesEstadoInicial();
        limparTela();
    }

    @FXML
    private void clickBuscar(ActionEvent event) {
        
        boolean buscou = false;
        lista.clear();
        if(txtBusca.getText().trim().isEmpty() && (cbFiltros.getSelectionModel().getSelectedItem() == null ||cbFiltros.getSelectionModel().getSelectedItem().trim().isEmpty() ) ){
            lista.addAll(ctr.getOfertasAbertas());
            
            buscou = true;
        }else{
            if(cbFiltros.getSelectionModel().getSelectedItem().toUpperCase().equals("ENCERRADAS")){
                
                lista.addAll(ctr.getOfertasEncerradas());
                buscou = true;
            }else
            {
                if(cbFiltros.getSelectionModel().getSelectedItem().toUpperCase().equals("NOME")){
                    lista.addAll(ctr.getOfertasPorNome(txtBusca.getText()));
                    buscou = true;
                }
            }
        }
        
        
        if(buscou){
            limparTabelas();     
            tbOferta.getItems().addAll(lista);
            
        }
        
    }
    
    
   // ====================================== CONTROLE TELA ======================================
    
    private void botoesEstadoInicial(){
        btnNovo.setDisable(false);
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
        btnCancelar.setDisable(false);
    }
    
     private void botoesEstadoAlterar(){
        btnNovo.setDisable(false);
        btnAlterar.setDisable(false);
        btnExcluir.setDisable(false);
        btnCancelar.setDisable(false);
    }
    
     private void limparTela(){
         lista.clear();
         txtBusca.clear();
         cbFiltros.getSelectionModel().clearSelection();
         limparTabelas();
     }
     
     private void limparTabelas(){
          tbOferta.getItems().clear();
          tbProduto.getItems().clear();
          tbServico.getItems().clear();
     }
     
     private void linkarTabelas(){
      
         //tabela de Oferta
        tbDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tbDataInicial.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        tbDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        
        //tabela de Produto
        clProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clValorPro.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        //tabela de Servvico
        clServico.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        clValorSer.setCellValueFactory(new PropertyValueFactory<>("preco"));


     }
     
    private void carregaComboBox(){
        cbFiltros.getItems().add("");
        cbFiltros.getItems().add("Nome");
        cbFiltros.getItems().add("Encerradas");
    }

    // ====================================== EVENTOS ======================================

    @FXML
    private void clickTabelaOfertas(MouseEvent event) {
        ofe = tbOferta.getSelectionModel().getSelectedItem();
        
        if ( ofe != null){
            botoesEstadoAlterar();
            
            tbProduto.getItems().clear();
            tbServico.getItems().clear();
            
            
            
            
            
            tbProduto.getItems().addAll(ofe.getListaOfertaProduto());
            tbServico.getItems().addAll(ofe.getListaOfertaServico());
            
            ofertaAlterar = ofe;
            //carregar outras duas tabelas
        }
           
    }
}
