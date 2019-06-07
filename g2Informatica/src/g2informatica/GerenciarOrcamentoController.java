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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Produto;
import models.Servico;

/**
 * FXML Controller class
 *
 * @author HafPc
 */
public class GerenciarOrcamentoController implements Initializable {

    
    ArrayList<Produto> listaP;
    ArrayList<Produto> listaBuscaP;
    CtrProduto ctrP = new CtrProduto();
    
    
    ArrayList<Servico> listaS;
    ArrayList<Servico> listaBuscaS;
    CtrServico ctrS = new CtrServico();
    
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<String> cbProdutos;
    @FXML
    private ComboBox<String> cbServicos;
    @FXML
    private ComboBox<String> cbClientes;
    @FXML
    private DatePicker dtDataDeVencimento;
    @FXML
    private ComboBox<String> cbStatus;
    @FXML
    private TableView tbProdutos;
    @FXML
    private TableView tbServicos;
    @FXML
    private TextArea txaDescricao;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCancelar;

    
    boolean resultadoDaBusca = false; 
    @FXML
    private TableColumn clProduto;
    @FXML
    private TableColumn clPrecoProduto;
    @FXML
    private TableColumn clServico;
    @FXML
    private TableColumn clPrecoServico;
    @FXML
    private Button btnRemover;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarComboBoxProdutos();
        carregarComboBoxServicos();
    }    

    @FXML
    private void clickBuscar(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("BuscaOrcamentos.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Busca");
            stage.setScene(scene);
            stage.showAndWait();
        }catch(Exception e){
            System.out.println("Erro:"+e.getLocalizedMessage());
        }
    }

    @FXML
    private void clickConfirmar(ActionEvent event) {
        //se o nao tem resultado de busca entao é uma insercao e nao um update
        if(!resultadoDaBusca){
            botoesEstadoInicial();
            
            if(!verificarDados()){
                
            }
            
        }
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
    }

    @FXML
    private void clickExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja cancelara operação ? ");
        
        if(alert.showAndWait().get() ==ButtonType.OK){
           
            Stage stage = (Stage) btnAlterar.getScene().getWindow(); //Obtendo a janela atual
            stage.close(); 
        } 
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("operação cancelada");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
    }
    
    /* ================================= TELA =================================*/
    
    private void linkarTabelas(){
        clProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clPrecoProduto.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        clServico.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clPrecoServico.setCellValueFactory(new PropertyValueFactory<>("preco"));
    }
    
    private void botoesEstadoInicial(){
        btnConfirmar.setDisable(false);
        btnAlterar.setDisable(true);
        btnCancelar.setDisable(false);
        btnExcluir.setDisable(true);
        btnRemover.setDisable(true);
    }
    
    private void botoesEstadoAlterar(){
        btnConfirmar.setDisable(true);
        btnAlterar.setDisable(false);
        btnCancelar.setDisable(false);
        btnExcluir.setDisable(true);
        
    }
    
    
    private void limparTela(){
        txaDescricao.clear();
        //dtDataDeVencimento.disarm();
        cbProdutos.getSelectionModel().clearSelection();
        cbServicos.getSelectionModel().clearSelection();
        cbStatus.getSelectionModel().clearSelection();
        cbClientes.getSelectionModel().clearSelection();

    }
    
    private void carregarComboBoxProdutos(){
        cbProdutos.getItems().clear();
        listaP = ctrP.getAllProdutos();
        listaBuscaP = ctrP.getAllProdutos();
        for(Produto p : listaP ){
            cbProdutos.getItems().add(p.getNome());
            
        }
    }
    
    private void carregarComboBoxServicos(){
        cbServicos.getItems().clear();
        listaS = ctrS.getAllServicos();
        listaBuscaS = ctrS.getAllServicos();

        for(Servico s : listaS ){
            cbServicos.getItems().add(s.getDescricao());
            
        }
    }
    
    private boolean verificarDados(){
        boolean erro = false;
        
        int index ;
        
        //VERIFICAR SE FOI SELECIONADO UM CLIENTE ======================================

         if(cbClientes.getSelectionModel().getSelectedItem().isEmpty() )
        {
            index = cbClientes.getStyleClass().size() -1;
            for (int i = 0; i < cbClientes.getItems().size(); i++) {
                System.out.println("Clientes: "+cbClientes.getStyleClass().get(index).toString());
            }
            
            if(!cbClientes.getStyleClass().get(index).toString().equals("text-field"))
               cbClientes.getStyleClass().remove(index);
                
            cbClientes.getStyleClass().add("text-field-erro");
                    
            
            erro = true;
            
        }else{
             
            
                
                index = cbClientes.getStyleClass().size() -1;

                if(cbClientes.getStyleClass().get(index).toString().equals("text-field-erro"))
                    cbClientes.getStyleClass().remove(index);

                cbClientes.getStyleClass().add("text-field-success");     

                
                
            }
         
         
         //VERIFICAR SE FOI INSERIDA UMA DATA DE VENCIMENTO ======================================

         if(dtDataDeVencimento == null || dtDataDeVencimento.getValue().toString().trim().isEmpty() )
        {
            index = dtDataDeVencimento.getStyleClass().size() -1;
            
            
            if(!dtDataDeVencimento.getStyleClass().get(index).toString().equals("date-picker"))
               dtDataDeVencimento.getStyleClass().remove(index);
                
            dtDataDeVencimento.getStyleClass().add("text-field-erro");
                    
            
            erro = true;
            
        }else{
                    
                index = dtDataDeVencimento.getStyleClass().size() -1;

                if(dtDataDeVencimento.getStyleClass().get(index).toString().equals("text-field-erro"))
                    dtDataDeVencimento.getStyleClass().remove(index);

                dtDataDeVencimento.getStyleClass().add("text-field-success");     

                
                
            }
        
            
         if(listaP.isEmpty() && listaS.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("É necessário ter um produto out Servico no orcamento ");
            alert.show();
            erro = true;
         }
         
         
       
         return erro;
    }

    @FXML
    private void btnAddProduto(ActionEvent event) {
    }

    @FXML
    private void btnAddServico(ActionEvent event) {
    }

    @FXML
    private void clickRemover(ActionEvent event) {
        
        
        btnRemover.setDisable(true);
    }

    @FXML
    private void clickTabelaproduto(MouseEvent event) {
        btnRemover.setDisable(false);
    }

    @FXML
    private void clickTabelaServico(MouseEvent event) {
        btnRemover.setDisable(false);

    }
         
    
}
