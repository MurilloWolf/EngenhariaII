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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private TextField txtQuantidade;
    @FXML
    private TextArea txaDescricao;
  
    ObservableList<Produto> lista = FXCollections.observableArrayList();
    Produto produto;
    
    private CtrProduto ctr = new CtrProduto();
    @FXML
    private TableView<Produto> tbPesquisaProduto;
    @FXML
    private TableColumn tbCodigo;
    @FXML
    private TableColumn tbNome;
    @FXML
    private TableColumn  tbPreco;
    @FXML
    private ComboBox<String> cbMarca;
    @FXML
    private TableColumn tbMarca;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarMarcas();
        colocarMascaras();
        botoesEstadoInicial();
        
        
        
        cbFiltro.getItems().add("Nome");
        cbFiltro.getItems().add("Preco");
        cbFiltro.getItems().add("Marca");
        
        
        //linkando tabela
        tbCodigo.setCellValueFactory(new PropertyValueFactory<>("cod"));
        tbNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
    }    
    
    
    
   
    @FXML
    private void clickBuscar(ActionEvent event) {
        CtrProduto ctrP = new CtrProduto();
        boolean buscou = false;
         tbPesquisaProduto.getItems().clear();        
        lista.clear();
                                
        if(cbFiltro.getSelectionModel().getSelectedItem() != null){
            String filtro = cbFiltro.getSelectionModel().getSelectedItem().toString();
             
            try{
                
                
                
                if(filtro.toUpperCase().equals("NOME")){
                  
                    lista.addAll(ctrP.getProdutoNome(txtBuscar.getText()));
                    buscou = true;
                }

                if(filtro.toUpperCase().equals("PRECO")){
                    lista.addAll(ctrP.getProdutoPreco(txtBuscar.getText()));
                    buscou = true;
                }

                if(filtro.toUpperCase().equals("MARCA")){
                    lista.addAll(ctr.getProdutoMarca(txtBuscar.getText()));
                    buscou = true;

                }
                
            }catch(Exception ex ){
                
            
              
            }finally{
                
                
                
                
                
                System.out.println("lista: "+lista.toString());
         //       tbPesquisaProduto.getItems().addAll(lista);
            }
           
            
        }else{
            if(txtBuscar.getText().trim().equals("")){
                    lista.addAll(ctrP.getAllProdutos());
                    buscou = true;
            }
            
            
           
        }
        
         if(buscou){
                
             desabilitarCampos();
             tbPesquisaProduto.getItems().addAll(lista);
            }
            
        
        
    }

    @FXML
    private void clickCadastrar(ActionEvent event) {
        if(!verificaCampos()){
           
            String preco =  txtPreco.getText();
            preco = preco.replace(".","");
            preco = preco.replace(",", ".");
            
            //se inserir deu certo
            if(ctr.cadastrar(txtNome.getText(),Double.parseDouble(preco),txaDescricao.getText(),Integer.parseInt(txtQuantidade.getText()),cbMarca.getSelectionModel().getSelectedItem())){
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
        
        
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
        
        if(!verificaCampos()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tem certeza que deseja Alterar o produto ? ");
            if(alert.showAndWait().get() ==ButtonType.OK){


                //pensar melhor sobre essa funcao 
                habilitarCampos();
                
               
                String preco =  txtPreco.getText();
                preco = preco.replace(".","");
                preco = preco.replace(",", ".");
                    //alterar no banco
                if(ctr.alterar(produto.getCod(),txtNome.getText(), Double.parseDouble(preco), txaDescricao.getText(),Integer.parseInt(txtQuantidade.getText()) , cbMarca.getSelectionModel().getSelectedItem())){
                  limparTela();
                  botoesEstadoInicial();
                  
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setContentText("Alteração realizada com SUCESSO");
                    alert1.showAndWait();
                }


                
            }
        }
       
    }

    @FXML
    private void clickExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja excluir o produto ? ");
        if(alert.showAndWait().get() ==ButtonType.OK){
            
            if(!verificaCampos()){
               

               if(ctr.deletar(produto.getCod())){
                    limparTela();
                    botoesEstadoInicial();
               }

               
            }
            
           
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
    
  
    @FXML
    private void clickTabela(MouseEvent event) {
        
        limparTela();
        
        produto = tbPesquisaProduto.getSelectionModel().getSelectedItem();
        txtNome.setText(produto.getNome());
        txtPreco.setText(produto.getPreco()+"");
        txtQuantidade.setText(produto.getQuantidade()+"");
        txaDescricao.setText(produto.getDescricao());
        System.out.println("prouto codigo:"+produto.getCod());
        
        cbMarca.getSelectionModel().select(produto.getMarca());
        botoesEstadoAlterar();
        
    }


    
    
    
    
    // ================================================= PROPRIEDADES DA TELA =================================================

    public void carregarMarcas(){
         
        
        cbMarca.getItems().add("");
        cbMarca.getItems().add("Dell");
        cbMarca.getItems().add("Lenovo");
        cbMarca.getItems().add("Accer");
        cbMarca.getItems().add("Asus");
        cbMarca.getItems().add("Nvidea");
        cbMarca.getItems().add("AMD");
        cbMarca.getItems().add("Intel");
       
       
       
    }
    
    
    public void habilitarCampos(){
        txtNome.setDisable(true);
        txtPreco.setDisable(true);
        txtQuantidade.setDisable(true);
        txaDescricao.setDisable(true);
        cbMarca.setDisable(true);
    }
    
    public void desabilitarCampos(){
        txtNome.setDisable(false);
        txtPreco.setDisable(false);
        txtQuantidade.setDisable(false);
        txaDescricao.setDisable(false);
        cbMarca.setDisable(false);
    }
    
    
     public void colocarMascaras(){
     MaskFieldUtil.monetaryField(txtPreco);
     MaskFieldUtil.numericField(txtQuantidade);
    }

    public void botoesEstadoInicial(){
        //nao se pode alterar se nao vuscar , nem excluir
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
        cbMarca.getSelectionModel().clearSelection();
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
        cbMarca.getSelectionModel().select(0);
        txtQuantidade.clear();
        txaDescricao.clear();
    }
    
    
    public boolean verificaCampos(){
      
        
       boolean erro = false;
        int index ;
        
        /* ==================================== NOME ====================================*/

         if(txtNome.getText().trim().isEmpty())
        {
            index = txtNome.getStyleClass().size() -1;
          
            if(!txtNome.getStyleClass().get(index).toString().equals("text-field"))
               txtNome.getStyleClass().remove(index);
                
            txtNome.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtNome.getStyleClass().size() -1;
            
            if(txtNome.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtNome.getStyleClass().remove(index);
            
            txtNome.getStyleClass().add("text-field-success");            
            

        }
        /* ==================================== PRECO ====================================*/

         if(txtPreco.getText().trim().isEmpty())
        {
            index = txtPreco.getStyleClass().size() -1;
          
            if(!txtPreco.getStyleClass().get(index).toString().equals("text-field"))
               txtPreco.getStyleClass().remove(index);
                
            txtPreco.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtPreco.getStyleClass().size() -1;
            
            if(txtPreco.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtPreco.getStyleClass().remove(index);
            
            txtPreco.getStyleClass().add("text-field-success");            
            

        }
         /* ==================================== QUANTIDADE ====================================*/

         if(txtQuantidade.getText().trim().isEmpty())
        {
            index = txtQuantidade.getStyleClass().size() -1;
          
            if(!txtQuantidade.getStyleClass().get(index).toString().equals("text-field"))
               txtQuantidade.getStyleClass().remove(index);
                
            txtQuantidade.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtQuantidade.getStyleClass().size() -1;
            
            if(txtQuantidade.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtQuantidade.getStyleClass().remove(index);
            
            txtQuantidade.getStyleClass().add("text-field-success");            
            

        }
            
        
        
        
         
         return erro;
    }

}


// ================================================= PROPRIEDADES DA TELA =================================================

