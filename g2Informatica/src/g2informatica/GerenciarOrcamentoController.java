/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrOrcamento;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Cliente;
import models.Produto;
import models.Servico;

/**
 * FXML Controller class
 *
 * @author HafPc
 */
public class GerenciarOrcamentoController implements Initializable {

    
   
   
    
    CtrOrcamento ctrO = new CtrOrcamento();
    
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
    private TableView<Produto> tbProdutos;
    @FXML
    private TableView<Servico> tbServicos;
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

    ArrayList<Produto> listaP;
    ArrayList<Produto> listaBuscaP;
    ArrayList<Servico> listaS;
    ArrayList<Servico> listaBuscaS;
    
    Produto produtoSelecionado;
    Servico servicoSelecionado;
    String tabela="";
    boolean resultadoDaBusca = false; 
    double valorTotal = 0;
    
    @FXML
    private TableColumn clServico;
    @FXML
    private TableColumn clPrecoServico;
    @FXML
    private Button btnRemover;
    @FXML
    private TextField txtValorTotal;
    @FXML
    private TableColumn clProdutos;
    @FXML
    private TableColumn clPrecoProdutos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarComboBoxProdutos();
        carregarComboBoxServicos();
        carregarComboBoxClientes();
        carregarStatus();
        linkarTabelas();
        botoesEstadoInicial();
        
        txtValorTotal.setText(valorTotal+"");
        
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
        if (!resultadoDaBusca) {
            botoesEstadoInicial();

            if (!verificarDados()) {

                //verificar se tem itens em oferta 
                if (!listaS.isEmpty() || !listaP.isEmpty()) {
                    //verificar se a oferta tem uma descricao

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Deseja Cadastrar o Orçamento  ?");
                    if (alert.showAndWait().get() == ButtonType.OK) {

                        if (ctrO.salvar()) {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Orçamento Cadastrado com SUCESSO");
                            alert.showAndWait();

                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("ERRO ao cadastrar Orcamento");
                            alert.showAndWait();

                        }
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nao há itens no Orcamento");
                alert.showAndWait();
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
        clProdutos.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clPrecoProdutos.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        clServico.setCellValueFactory(new PropertyValueFactory<>("descricao"));
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
        listaP = ctrO.getAllProdutos();
        listaBuscaP = ctrO.getAllProdutos();
        for(Produto p : listaP ){
            cbProdutos.getItems().add(p.getNome());
            
        }
        listaP.clear();
    }
    
    private void carregarComboBoxClientes(){
     cbClientes.getItems().clear();
     ArrayList<Cliente> clientes = Cliente.getAllClientes();
        for (int i = 0; i < clientes.size(); i++) {
            cbClientes.getItems().add(clientes.get(i).getNome());
        }
     
    }
    
    private void carregarComboBoxServicos(){
        cbServicos.getItems().clear();
        listaS = ctrO.getAllServicos();
        listaBuscaS = ctrO.getAllServicos();

        for(Servico s : listaS ){
            cbServicos.getItems().add(s.getDescricao());
            
        }
    }
    private void carregarStatus(){
        cbStatus.getItems().clear();
        cbStatus.getItems().add("aberto");
        cbStatus.getItems().add("OS aberta");
        cbStatus.getItems().add("finalizado");
        
       
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
        
        //verificar se produto ta em promocao
        
            //adicionar na tabela 
            Produto p;
            for (int i = 0; i < listaBuscaP.size() ; i ++) {
                p  = listaBuscaP.get(i);
                
                if(p.getNome().equals(cbProdutos.getSelectionModel().getSelectedItem().toString())){
                    
                   if(!verificarDuplicataDeProduto(p)){
                   
                       listaP.add(p);
                       tbProdutos.getItems().add(p);
                       
                       limparSelecao();
                       calcularValorTotal();

                       
                   }
                    
                }
                    
            }
            
            
       
    }

    @FXML
    private void btnAddServico(ActionEvent event) {
          Servico s;
            for (int i = 0; i < listaBuscaP.size() ; i ++) {
                s  = listaBuscaS.get(i);
                if(s.getDescricao().equals(cbServicos.getSelectionModel().getSelectedItem().toString())){
                    
                    if(!verificarDuplicataDeServico(s)){
                        listaS.add(s);
                        tbServicos.getItems().add(s);
                        limparSelecao();
                        calcularValorTotal();
                    }
                }
                    
            }
            
    }

    @FXML
    private void clickRemover(ActionEvent event) {
        boolean removeu = false;
        if(tabela.equals("servicos")){
           
            for (int i = 0; i < tbServicos.getItems().size() && !removeu; i++) {
                if(servicoSelecionado.equals(tbServicos.getItems().get(i))){
                    
                    removeu = true;
                    listaS.remove(tbServicos.getItems().get(i));
                    tbServicos.getItems().remove(i);
                }
                    
            }
            calcularValorTotal();

            
        }else{
         
            if(tabela.equals("produtos")){
                for (int i = 0; i < tbProdutos.getItems().size() && !removeu; i++) {
                    if(produtoSelecionado.equals(tbProdutos.getItems().get(i))){
                        
                        removeu = true;
                        listaP.remove(tbProdutos.getItems().get(i));
                        tbProdutos.getItems().remove(i);
                        
                    }

                }
                calcularValorTotal();

            }
        }
        
        btnRemover.setDisable(true);
    }

    @FXML
    private void clickTabelaproduto(MouseEvent event) {
        tabela = "produtos";
        produtoSelecionado = tbProdutos.getSelectionModel().getSelectedItem();
        
        
        btnRemover.setDisable(false);
    }

    @FXML
    private void clickTabelaServico(MouseEvent event) {
        tabela ="servicos";
        servicoSelecionado = tbServicos.getSelectionModel().getSelectedItem();

        btnRemover.setDisable(false);

    }
    
    private void limparSelecao(){
        cbProdutos.getSelectionModel().clearSelection();
        cbServicos.getSelectionModel().clearSelection();
    }
         
    
    //=========================== FUNCOES COPIADAS ============================
     private boolean verificarDuplicataDeProduto(Produto novaOferta){
        boolean erro = false;
        for (Produto p : tbProdutos.getItems()) {
            if(novaOferta.getCod() == p.getCod())
                erro = true;
        }
        
        
        return erro ;
    }
    
     private boolean verificarDuplicataDeServico(Servico novaOferta){
        boolean erro = false;
        for (Servico s : tbServicos.getItems()) {
            if(novaOferta.getCodigo() == s.getCodigo())
                erro = true;
        }
        
        
        return erro ;
    }
     
     
     public void calcularValorTotal(){
         double novoValor = 0;
         for (Produto p : tbProdutos.getItems()) {
             novoValor +=p.getPreco();
         }
         for (Servico s : tbServicos.getItems()) {
             novoValor += s.getValor();
         }
         
         valorTotal = novoValor;
         txtValorTotal.setText(""+valorTotal);
     }
    
}
