/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrFornecedor;
import eng2.util.MaskFieldUtil;
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
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarFornecedores();
        colocarMascaras();
        botoesEstadoInicial();
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
    }

    public void botoesEstadoInicial(){
        //nao se pode alterar se nao vuscar , nem excluir
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
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
    }
    @FXML
    private void clickBuscar(ActionEvent event) {
        String filtro = cbFiltro.getSelectionModel().toString();
        
        
    }

    @FXML
    private void clickCadastrar(ActionEvent event) {
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
    }

    @FXML
    private void clickExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja excluir o produto ? ");
        alert.showAndWait();
    }

    @FXML
    private void clickCanccelar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja cancelara operação ? ");
        
        if(alert.showAndWait().get() ==ButtonType.OK){
            limparTela();
            botoesEstadoInicial();
        }
    }
    
}
