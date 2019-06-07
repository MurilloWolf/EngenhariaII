/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrOferta;
import controllers.CtrOfertaProduto;
import controllers.CtrOfertaServico;
import controllers.CtrProduto;
import controllers.CtrServico;
import eng2.util.MaskFieldUtil;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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
    CtrOfertaProduto ctrOP = new CtrOfertaProduto();
    CtrOfertaServico ctrOS = new CtrOfertaServico();
    CtrOferta ctrOferta = new CtrOferta();
    
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
    
    ArrayList<Servico> listaBuscaS;
    ArrayList<Produto> listaBuscaP;
    @FXML
    private TableColumn clProduto;
    @FXML
    private TableColumn clValorProdutos;
    @FXML
    private TableColumn clServico;
    @FXML
    private TableColumn clValorServico;
    @FXML
    private TextField txtNome;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        aplicarMascaras();
        linkarTabela();
        limparTela();
        carregarProdutos();
        carregarServicos();
    }    

    @FXML
    private void addProdutos(ActionEvent event) {
        
        if(txtValorProduto.getText().trim().isEmpty())
            preencherCampoValor(txtPorcentagemProduto.getText().trim(),  txtValorProduto , cbProdutos);
        
        if(!verificarCampoValor(txtPorcentagemProduto,txtValorProduto,cbProdutos)){
            //adicionar na tabela 
            Produto p;
            for (int i = 0; i < listaBuscaP.size() ; i ++) {
                p  = listaBuscaP.get(i);
                if(p.getNome().equals(cbProdutos.getSelectionModel().getSelectedItem().toString())){
                    
                    String valor = txtValorProduto.getText().trim();
                    if(valor.contains(",")){
                        valor = valor.replace(".", "");
                        valor = valor.replace(",", ".");
                    }
                   
                    tbProduto.getItems().add(ctrOP.novaOfertaProduto(p,Double.parseDouble(valor)));
                }
                    
            }
            
            limparTela();
            
        }
    }

    @FXML
    private void addServico(ActionEvent event) {
          
        if(txtValorServico.getText().trim().isEmpty())
            preencherCampoValor(txtPorcentagemServico.getText().trim(),  txtValorServico , cbServicos);
        
        if(!verificarCampoValor(txtPorcentagemServico,txtValorServico,cbServicos)){
            //adicionar na tabela 
            Servico s;
            for (int i = 0; i < listaBuscaS.size() ; i ++) {
                s  = listaBuscaS.get(i);
                if(s.getDescricao().equals(cbServicos.getSelectionModel().getSelectedItem().toString())){
                    
                    String valor = txtValorServico.getText().trim();
                    if(valor.contains(",")){
                        valor = valor.replace(".", "");
                        valor = valor.replace(",", ".");
                    }
                    tbServico.getItems().add(ctrOS.novaOfertaServico(s,Double.parseDouble(valor)));
                }
                    
            }
            
            limparTela();
            
        }
    }

    @FXML
    private void clickConfirmar(ActionEvent event) {
        
        //verificar se datas estao preenchidas 
        //verificar se data final menor que data inicial
        if(!verificaDatasPreenchidas()){
        
            //verificar se tem itens em oferta 
            if(!listaS.isEmpty() || !listaP.isEmpty()){
                 //verificar se a oferta tem uma descricao
                if(!verificarNome()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Deseja Cadastrar a Oferta  ?");
                    if(alert.showAndWait().get() == ButtonType.OK){
                        
                        if(ctrOferta.salvarOferta(tbProduto.getItems(),tbServico.getItems(), dtInicial.getValue() , dtFinal.getValue(), txtNome.getText()) ){
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Oferta Cadastrada com SUCESSO");
                            alert.showAndWait();
                            Stage s = (Stage)txtNome.getScene().getWindow();
                            s.close();
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("ERRO ao cadastrar Oferta");
                            alert.showAndWait();
                            
                        }
                    }
                }
                
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nao há itens em oferta");
                alert.showAndWait();
            }
                    
        }
     
        
       
        //perguntar se deseja realizar a operação
        //fechar tela
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
    
    private boolean verificarNome(){
     
        int index;
        
        boolean erro = false;
      
        
        if(txtNome.getText().trim().isEmpty()){
            
            index = txtNome.getStyleClass().size() - 1;

            if (!txtNome.getStyleClass().get(index).toString().equals("text-field")) {
                txtNome.getStyleClass().remove(index);
            }

            txtNome.getStyleClass().add("text-field-erro");
            erro = true;

        } else {

            index = txtNome.getStyleClass().size() - 1;
           
            if (txtNome.getStyleClass().get(index).toString().equals("text-field-erro")) {
                txtNome.getStyleClass().remove(index);
            }

            txtNome.getStyleClass().add("text-field-success");

        }

        return erro;
    }
    
    private boolean verificaDatasPreenchidas(){
        boolean erro = false;
        int index ;
        if (dtInicial.getValue() == null || dtInicial.getValue().toString().trim().isEmpty()) {
            
            index = dtInicial.getStyleClass().size() - 1;

            if (!dtInicial.getStyleClass().get(index).toString().equals("date-picker")) {
                dtInicial.getStyleClass().remove(index);
            }

            dtInicial.getStyleClass().add("text-field-erro");
            erro = true;

        } else {

            index = dtInicial.getStyleClass().size() - 1;
           
            if (dtInicial.getStyleClass().get(index).toString().equals("text-field-erro")) {
                dtInicial.getStyleClass().remove(index);
            }

            dtInicial.getStyleClass().add("text-field-success");

        }

        if (dtFinal.getValue() ==null ||dtFinal.getValue().toString().isEmpty() || (dtFinal.getValue().isBefore(dtInicial.getValue()))) {

            index = dtFinal.getStyleClass().size() - 1;

            if (!dtFinal.getStyleClass().get(index).toString().equals("date-picker")) {
                dtFinal.getStyleClass().remove(index);
            }

            dtFinal.getStyleClass().add("text-field-erro");
            erro = true;
        } else {

            index = dtFinal.getStyleClass().size() - 1;

            if (dtFinal.getStyleClass().get(index).toString().equals("text-field-erro")) {
                dtFinal.getStyleClass().remove(index);
            }

            dtFinal.getStyleClass().add("text-field-success");
        }

        return erro;
    }
    
    private void aplicarMascaras(){
        
        MaskFieldUtil.monetaryField(txtPorcentagemProduto);
        MaskFieldUtil.monetaryField(txtPorcentagemServico);
    }
    private void limparTela(){
        txtPorcentagemProduto.clear();
        txtPorcentagemServico.clear();
        
        txtNome.clear();
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
        listaBuscaP = ctrP.getAllProdutos();
        for(Produto p : listaP ){
            cbProdutos.getItems().add(p.getNome());
            
        }
    }
    
    private void carregarServicos(){
        cbServicos.getItems().clear();
        listaS = ctrS.getAllServicos();
        listaBuscaS = ctrS.getAllServicos();

        for(Servico s : listaS ){
            cbServicos.getItems().add(s.getDescricao());
            
        }
    }
    
    private void linkarTabela(){
        clProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clValorProdutos.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        
        clServico.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        clValorServico.setCellValueFactory(new PropertyValueFactory<>("preco"));

        
        
    }
    
    
    // =============================================== Verificar Campos ===============================================
    private boolean verificarCampoValor(TextField porcentagem, TextField valor, ComboBox cb){
        boolean erro = false;
        
        int index ;
        
        //SE OS DOIS CAMPOS ESTAO VAZIOS

         if(valor.getText().trim().isEmpty() && porcentagem.getText().isEmpty() )
        {
            index = valor.getStyleClass().size() -1;
          
            if(!valor.getStyleClass().get(index).toString().equals("text-field"))
               valor.getStyleClass().remove(index);
                
            valor.getStyleClass().add("text-field-erro");
                    
            
            erro = true;
            
        }else{
             
            //SE SO O CAMPO DE VALOR ESTA VAZIO , PREENCHER CAMPO DE PORCENTAGEM 
            if(valor.getText().trim().isEmpty() && !porcentagem.getText().isEmpty()){
                preencherCampoValor(porcentagem.getText().trim(),  valor , cb);
            }else{
                
                index = porcentagem.getStyleClass().size() -1;

                if(valor.getStyleClass().get(index).toString().equals("text-field-erro"))
                    valor.getStyleClass().remove(index);

                valor.getStyleClass().add("text-field-success");     

                
                
            }
            
            
            

        }
         return erro;
    }
    
    private void preencherCampoValor(String porcentagem, TextField valor, ComboBox cb){
        
        //
        porcentagem = porcentagem.replace(".", "");
        porcentagem = porcentagem.replace(",", ".");
        
       
        double valorAntigo = -1;
        
        //buscar valor Servico
        for (Servico s : listaBuscaS) {
            if(s.getDescricao().equals(cb.getSelectionModel().getSelectedItem().toString()))
                valorAntigo = s.getValor();
        }
        
        //se nao achou um valorAntigo na busca do Servico é porque é um produto
        if(valorAntigo == -1){
            //buscar valor Produto
            for (int i = 0; i < listaBuscaP.size() ; i ++) {
                Produto p  = listaBuscaP.get(i);
                if(p.getNome().equals(cb.getSelectionModel().getSelectedItem().toString())){
                     valorAntigo = p.getPreco();
                     i = listaBuscaP.size();
                }
                   
            }
          
        }
        
        
        
       try{
           double porcent =  Double.parseDouble(porcentagem);
           double novoValor =0 ;
          
           if(porcent <= 100){
               if(porcent!=0)
                novoValor = Math.ceil(valorAntigo  - (valorAntigo * (porcent/100)));
               else
                   novoValor = valorAntigo;
               
               valor.setText(novoValor+"");
           }else{
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("A porcentagem nao pode ser maior que 100% ");
                alert.showAndWait();
           }
        
       }catch(Exception e){
           System.out.println("Erro:"+e.getMessage());
       }
        
    }

 
  

    @FXML
    private void textChangeProduto(ActionEvent event) {
        txtValorProduto.clear();
        preencherCampoValor(txtPorcentagemProduto.getText().trim() ,txtValorProduto,cbProdutos);
       
    }

    @FXML
    private void textChangeServico(ActionEvent event) {
        txtValorServico.clear();
        preencherCampoValor(txtPorcentagemServico.getText().trim() ,txtValorServico,cbServicos);
    }

   
    
}
