/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrEmpresa;
import db.Banco;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author wolf
 */
public class EmpresaController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCNPJ;
    @FXML
    private TextField txtMissao;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtUf;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtRua;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtSite;
    @FXML
    private TextField txtPaginaFb;
    @FXML
    private TextField txtInstagram;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;

    
    private CtrEmpresa ctr = new CtrEmpresa();
    @FXML
    private TextField txtNumero;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @FXML
    private void clickBuscar(ActionEvent event) {
    }

    @FXML
    private void clickSalvar(ActionEvent event) {
        if(txtNome.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso ter Nome");
            alert.showAndWait();
                
            txtNome.requestFocus();
            return;
        }
        
         if(txtCNPJ.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir o CNPJ ");
            alert.showAndWait();
                
            txtCNPJ.requestFocus();
            return;
        }
         if(txtMissao.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir a Missao ");
            alert.showAndWait();
                
            txtMissao.requestFocus();
            return;
        }
         
          if(txtEmail.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir o Email ");
            alert.showAndWait();
                
            txtEmail.requestFocus();
            return;
        }
        
           if(txtTelefone.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir o Telefone ");
            alert.showAndWait();
                
            txtTelefone.requestFocus();
            return;
        }
        
        
        
        if(txtCep.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir o  CEP");
            alert.showAndWait();
                
            txtCep.requestFocus();
            return;
        }
        
        if(txtUf.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso selecionar um Estado");
            alert.showAndWait();
            
            txtUf.requestFocus();
            return;
        } 
        
        if(txtCidade.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir a Cidade");
            alert.showAndWait();
                
            txtCidade.requestFocus();
            return;
        }
        
        if(txtBairro.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir o Bairro");
            alert.showAndWait();
                
            txtBairro.requestFocus();
            return;
        }
        
        
        
        if(txtRua.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir uma Rua");
            alert.showAndWait();
            
            txtRua.requestFocus();
            return;
        }
        
        
        if(txtNumero.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É preciso inserir o numero");
            alert.showAndWait();
            
            txtNumero.requestFocus();
            return;
        }
        

      
        /*Salvar no Banco*/
        /*if(){
            
        }
        if(ctr.alterarParametrizacao(txtNome.getText(), txtRazao.getText(), imgLogo.getImage(), txtCEP.getText(), txtLogradouro.getText(), txtBairro.getText(),
                cbCidade.getSelectionModel().getSelectedItem(), cbCor.getSelectionModel().getSelectedItem(), cbFonte.getSelectionModel().getSelectedItem(),
                txtSite.getText(), txtEmail.getText(), txtTelefone.getText()))
        {
           TelaPrincipal.apagarTela();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro: "+Banco.con.getMensagemErro());
            alert.showAndWait();
        
        }
        */
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
    }
    
    private void estadoInicial(){
        
    }
    
    
}
