/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrFornecedor;
import controllers.CtrProduto;
import eng2.util.MaskFieldUtil;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Funcionario;
import models.Produto;

/**
 * FXML Controller class
 *
 * @author wolf
 */
public class CadastroProdutoController implements Initializable {

    @FXML
    private ComboBox<String> cbFiltro;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPreco;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<String> cbFornecedor;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextArea txaDescricao;
    ObservableList<Produto> lista = FXCollections.observableArrayList();
    
    
    private CtrProduto ctr = new CtrProduto();
    @FXML
    private TableView<?> tbPesquisaProduto;
    @FXML
    private TableColumn tbCodigo;
    @FXML
    private TableColumn tbNome;
    @FXML
    private TableColumn  tbPreco;
    @FXML
    private TableColumn tbFornecedor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarFornecedores();
        colocarMascaras();
        botoesEstadoInicial();
        
        cbFiltro.getItems().add("Nome");
        cbFiltro.getItems().add("Preco");
        cbFiltro.getItems().add("Fornecedor");
        
        
        //linkando tabela
         tbCodigo.setCellValueFactory(new PropertyValueFactory<>("cod"));
        tbNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tbFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        
    }    
    
    public void carregarFornecedores(){
        ArrayList<String> listaDeFornecedores; 
        CtrFornecedor ctrF = new CtrFornecedor();
        listaDeFornecedores = ctrF.getListaDeFornecedores();
        if(listaDeFornecedores!=null){
            cbFornecedor.getItems().clear();
            for(int i=0; i < listaDeFornecedores.size(); i ++)
              cbFornecedor.getItems().add(listaDeFornecedores.get(i));
        }
    }
    
    public void colocarMascaras(){
     MaskFieldUtil.monetaryField(txtPreco);
     MaskFieldUtil.numericField(txtQuantidade);
    }

    public void botoesEstadoInicial(){
        //nao se pode alterar se nao vuscar , nem excluir
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
        cbFornecedor.getSelectionModel().clearSelection();
    }
    
    //Vai chamar essa função quando ele clicar no item que foi buscado e as informações forem carregadas nos
    //texts box
    public void botoesEstadoAlterar(){
        btnAlterar.setDisable(false);
        btnExcluir.setDisable(false);
        
        btnCadastrar.setDisable(true);
    }
    public void limparTela(){
        txtNome.clear();
        txtPreco.clear();
        cbFornecedor.getSelectionModel().select(0);
        txtQuantidade.clear();
        txaDescricao.clear();
    }
    @FXML
    private void clickBuscar(ActionEvent event) {
        if(cbFiltro.getSelectionModel().getSelectedItem() != null){
            String filtro = cbFiltro.getSelectionModel().getSelectedItem().toString();
            boolean buscou = false; 
            try{
                tbPesquisaProduto.getItems().clear();
                lista.clear();
                
                CtrProduto ctrP = new CtrProduto();
                if(filtro.toUpperCase().equals("NOME")){
                    lista.addAll( (ObservableList) ctrP.getProdutoNome(txtBuscar.getText()));
                }

                if(filtro.toUpperCase().equals("PRECO")){
                    lista.addAll((ObservableList)ctrP.getProdutoPreco(txtBuscar.getText()));
                }

                if(filtro.toUpperCase().equals("FORNECEDOR")){
                    lista.addAll((ObservableList)ctr.getProdutoFornecedor(txtBuscar.getText()));
                }
                
            }catch(Exception ex ){
                
            
              
            }finally{
                
                
                

                
                System.out.println("lista: "+lista.toString());
         //       tbPesquisaProduto.getItems().addAll(lista);
            }
            if(buscou)
             desabilitarCampos();
        }
        
        
    }

    @FXML
    private void clickCadastrar(ActionEvent event) {
        verificaCampos();
        
        String preco =  txtPreco.getText();
        preco = preco.replace(".","");
        preco = preco.replace(",", ".");
        System.out.println("preco :"+preco);
        //se inserir deu certo
        if(ctr.cadastrar(txtNome.getText(),Double.parseDouble(preco),txaDescricao.getText(),Integer.parseInt(txtQuantidade.getText()),cbFornecedor.getSelectionModel().getSelectedItem())){
            limparTela();
            botoesEstadoInicial();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cadastro realizado com sucesso");
            alert.showAndWait();
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nao foi possivel realizar o cadastro");
            alert.showAndWait();
        }
        
        
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja Alterar o produto ? ");
        if(alert.showAndWait().get() ==ButtonType.OK){
            
            
            //pensar melhor sobre essa funcao 
            habilitarCampos();
            verificaCampos();
            //alterar no banco
            
            
            
            limparTela();
            botoesEstadoInicial();
        }
    }

    @FXML
    private void clickExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja excluir o produto ? ");
        if(alert.showAndWait().get() ==ButtonType.OK){
            
            verificaCampos();
            
            String preco = txtPreco.getText().replace(",", ".");
            if(ctr.deletar(txtNome.getText(),Double.parseDouble(preco),txaDescricao.getText(),Integer.parseInt(txtQuantidade.getText()),cbFornecedor.getSelectionModel().getSelectedItem()));
            
            limparTela();
            botoesEstadoInicial();
        }
    }

    @FXML
    private void clickCanccelar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja cancelara operação ? ");
        
        if(alert.showAndWait().get() ==ButtonType.OK){
            limparTela();
            botoesEstadoInicial();
            habilitarCampos();
        }
    }
    
  
    public void habilitarCampos(){
        txtNome.setDisable(true);
        txtPreco.setDisable(true);
        txtQuantidade.setDisable(true);
        txaDescricao.setDisable(true);
        cbFornecedor.setDisable(true);
    }
    
    public void desabilitarCampos(){
        txtNome.setDisable(false);
        txtPreco.setDisable(false);
        txtQuantidade.setDisable(false);
        txaDescricao.setDisable(false);
        cbFornecedor.setDisable(false);
    }
    
    public void verificaCampos(){
      if(txtNome.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("O Produto precisa ter Nome");
            alert.showAndWait();
                
            txtNome.requestFocus();
            return;
        }
        if(txtPreco.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("O produto precisa ter um preço");
            alert.showAndWait();
                
            txtPreco.requestFocus();
            return;
        }
         if(txtQuantidade.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso ter uma quantidade");
            alert.showAndWait();
                
            txtQuantidade.requestFocus();
            return;
        }
         
        
        
        System.out.println("Combo box :"+cbFornecedor.getSelectionModel().getSelectedItem());
         if(cbFornecedor.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso ter um Fornecedor");
            alert.showAndWait();
                
            cbFornecedor.requestFocus();
            return;
        }   
    }
}
