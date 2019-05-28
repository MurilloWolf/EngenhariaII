/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author wolf
 */
public class GerenciarOfertaController implements Initializable {

    public static VBox _painel;
    @FXML
    private DatePicker dateInicial;
    @FXML
    private DatePicker dateFinal;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnOfertas;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<?> tbProduto;
    @FXML
    private TableView<?> tbServico;
    @FXML
    private VBox hboxCentral;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }   

    @FXML
    private void clickConfirmar(ActionEvent event) {
    }

    @FXML
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
    }

    @FXML
    private void clickAlterar(ActionEvent event) {
    }

    @FXML
    private void clickExcluir(ActionEvent event) {
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
    }
    
    
   
    
    
}
