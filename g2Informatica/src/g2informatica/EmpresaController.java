/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import controllers.CtrEmpresa;
import db.Banco;
import eng2.util.MaskFieldUtil;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import models.Empresa;

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
    @FXML
    private TextField txtLogo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        aplicarMascara();
        if(!Empresa.getStatus()){
            btnCancelar.setDisable(true);
            
        }
        else{
            carregarDadosParaTela();
        }
        
    }    
    public void carregarDadosParaTela(){
        Empresa emp = new Empresa();
        emp = emp.getParametrizacao();
        //DADOS DA EMPRESA
        txtNome.setText(emp.getNome());
        txtMissao.setText(emp.getMissao());
        txtCNPJ.setText(emp.getCnpj());
        txtTelefone.setText(emp.getTelefone());
        txtEmail.setText(emp.getEmail());
        
        //IDENTIDADE VISUAL 
        txtSite.setText(emp.getSite());
        txtPaginaFb.setText(emp.getPaginaFb());
        txtInstagram.setText(emp.getInstagram());
        //FALTOU A LOGO
        
        //LOCAL
        txtCidade.setText(emp.getEndereco().getCidade());
        txtRua.setText(emp.getEndereco().getRua());
        txtUf.setText(emp.getEndereco().getUf());
        txtCep.setText(emp.getEndereco().getCep());
        txtNumero.setText(emp.getEndereco().getNumero());

        
    } 
    public void aplicarMascara(){
        MaskFieldUtil.cepField(txtCep);
        MaskFieldUtil.cnpjField(txtCNPJ);
        MaskFieldUtil.foneField(txtTelefone);
        MaskFieldUtil.maxField(txtUf, 2);
        
        
        //MaskFieldUtil.numericField(txtNumero);
        
    }
    @FXML
    private void clickBuscar(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String diretorio = "";
        File selected = fc.showOpenDialog(null);
         if(selected != null)
        {
            diretorio = selected.getAbsolutePath();
            diretorio = diretorio.replace("\\","/");
            txtLogo.setText(diretorio);
            
            try {
                imgLogo.setVisible(true);
                imgLogo.setImage(SwingFXUtils.toFXImage(ImageIO.read(selected),null));
            } catch (Exception e) {
            }
        }
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
        
        CtrEmpresa ctr = new CtrEmpresa();
        boolean resultadoInsercao = ctr.salvarEmpresa(
                                                    txtNome.getText(),txtMissao.getText(), txtCep.getText(), txtEmail.getText(),
                                                    txtTelefone.getText(), txtCNPJ.getText(), txtUf.getText(), txtCidade.getText(), 
                                                    txtBairro.getText(), txtRua.getText(), txtNumero.getText(), txtSite.getText(),
                                                    txtPaginaFb.getText(), txtInstagram.getText(), txtNome.getText()
                                                    );
      
        //se inseriu
        if(resultadoInsercao){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Salvar Parametrização realizada com SUCESSO");
            alert.showAndWait();
            System.exit(0);
        }else{
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setContentText("ERRO ao salvar parametrização");
            alert.setContentText("Erro:"+Banco.con.getMensagemErro());
            alert.showAndWait();
        
        }
       
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        System.exit(0);
    }
    
    private void estadoInicial(){
        
    }
    
    
}
