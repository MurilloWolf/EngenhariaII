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
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    @FXML
    private BorderPane borderPane;
    
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
       
        //verificarCamposObrigatorios();
        //verificaTodosCamposDaTela(borderPane);
        
        if(verificarCamposObrigatorios()){
          
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
                alert.setContentText("SUCESSO, é preciso reiniciaro o programa");
                alert.showAndWait();
                System.exit(0);


            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setContentText("ERRO ao salvar parametrização");
                alert.setContentText("Nao foi possivel salvar a parametrizacao");
                alert.showAndWait();

            }
        }
        
       
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        FXMLprincipalController._pndados.getChildren().clear();
    }
    
    private void estadoInicial(){
        
    }
    
    private boolean verificarCamposObrigatorios(){
        
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
        /* ==================================== CNPJ ====================================*/

         if(txtCNPJ.getText().trim().isEmpty())
        {
            index = txtCNPJ.getStyleClass().size() -1;
          
            if(!txtCNPJ.getStyleClass().get(index).toString().equals("text-field"))
               txtCNPJ.getStyleClass().remove(index);
                
            txtCNPJ.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtCNPJ.getStyleClass().size() -1;
            
            if(txtCNPJ.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtCNPJ.getStyleClass().remove(index);
            
            txtCNPJ.getStyleClass().add("text-field-success");            
            

        }
         
         /* ==================================== MISSAO ====================================*/
        if(txtMissao.getText().trim().isEmpty())
        {
            index = txtMissao.getStyleClass().size() -1;
          
            if(!txtMissao.getStyleClass().get(index).toString().equals("text-field"))
               txtMissao.getStyleClass().remove(index);
                
            txtMissao.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtMissao.getStyleClass().size() -1;
            
            if(txtMissao.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtMissao.getStyleClass().remove(index);
            
            txtMissao.getStyleClass().add("text-field-success");            
            

        }
         
         /* ==================================== EMAIL ====================================*/
        if(txtEmail.getText().trim().isEmpty())
        {
            index = txtEmail.getStyleClass().size() -1;
          
            if(!txtEmail.getStyleClass().get(index).toString().equals("text-field"))
               txtEmail.getStyleClass().remove(index);
                
            txtEmail.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtEmail.getStyleClass().size() -1;
            
            if(txtEmail.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtEmail.getStyleClass().remove(index);
            
            txtEmail.getStyleClass().add("text-field-success");            
            

        }
         
        
        /* ==================================== TELEFONE ====================================*/

        if(txtTelefone.getText().trim().isEmpty())
        {
            index = txtTelefone.getStyleClass().size() -1;
          
            if(!txtTelefone.getStyleClass().get(index).toString().equals("text-field"))
               txtTelefone.getStyleClass().remove(index);
                
            txtTelefone.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtTelefone.getStyleClass().size() -1;
            
            if(txtTelefone.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtTelefone.getStyleClass().remove(index);
            
            txtTelefone.getStyleClass().add("text-field-success");            
            

        }
        
        
        /* ==================================== CEP ====================================*/

        if(txtCep.getText().trim().isEmpty())
        {
            index = txtCep.getStyleClass().size() -1;
          
            if(!txtCep.getStyleClass().get(index).toString().equals("text-field"))
               txtCep.getStyleClass().remove(index);
                
            txtCep.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtCep.getStyleClass().size() -1;
            
            if(txtCep.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtCep.getStyleClass().remove(index);
            
            txtCep.getStyleClass().add("text-field-success");            
            

        }
        
        /* ==================================== UF ====================================*/

        if(txtUf.getText().trim().isEmpty())
        {
            index = txtUf.getStyleClass().size() -1;
          
            if(!txtUf.getStyleClass().get(index).toString().equals("text-field"))
               txtUf.getStyleClass().remove(index);
                
            txtUf.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtUf.getStyleClass().size() -1;
            
            if(txtUf.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtUf.getStyleClass().remove(index);
            
            txtUf.getStyleClass().add("text-field-success");            
            

        }
        
        /* ==================================== CIDADE ====================================*/

        if(txtCidade.getText().trim().isEmpty())
        {
            index = txtCidade.getStyleClass().size() -1;
          
            if(!txtCidade.getStyleClass().get(index).toString().equals("text-field"))
               txtCidade.getStyleClass().remove(index);
                
            txtCidade.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtCidade.getStyleClass().size() -1;
            
            if(txtCidade.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtCidade.getStyleClass().remove(index);
            
            txtCidade.getStyleClass().add("text-field-success");            
            

        }
        
        /* ==================================== BAIRRO ====================================*/

        if(txtBairro.getText().trim().isEmpty())
        {
            index = txtBairro.getStyleClass().size() -1;
          
            if(!txtBairro.getStyleClass().get(index).toString().equals("text-field"))
               txtBairro.getStyleClass().remove(index);
                
            txtBairro.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtBairro.getStyleClass().size() -1;
            
            if(txtBairro.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtBairro.getStyleClass().remove(index);
            
            txtBairro.getStyleClass().add("text-field-success");            
            

        }
        
        
        /* ==================================== RUA ====================================*/

        if(txtRua.getText().trim().isEmpty())
        {
            index = txtRua.getStyleClass().size() -1;
          
            if(!txtRua.getStyleClass().get(index).toString().equals("text-field"))
               txtRua.getStyleClass().remove(index);
                
            txtRua.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtRua.getStyleClass().size() -1;
            
            if(txtRua.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtRua.getStyleClass().remove(index);
            
            txtRua.getStyleClass().add("text-field-success");            
            

        }
        
        /* ==================================== NUMERO ====================================*/

        if(txtNumero.getText().trim().isEmpty())
        {
            index = txtNumero.getStyleClass().size() -1;
          
            if(!txtNumero.getStyleClass().get(index).toString().equals("text-field"))
               txtNumero.getStyleClass().remove(index);
                
            txtNumero.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txtNumero.getStyleClass().size() -1;
            
            if(txtNumero.getStyleClass().get(index).toString().equals("text-field-erro"))
                txtNumero.getStyleClass().remove(index);
            
            txtNumero.getStyleClass().add("text-field-success");            
            

        }
        
        if(erro){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setContentText("ERRO ao salvar parametrização");
            alert.setContentText("É preciso preencher todos os campos obrigatórios");
            alert.showAndWait();   
        }
        
        return erro;
    }
    
    
    
    /* ============================ MODULARIZAÇÂO ============================ */
    /*PROBLEMA : NO CASO ESSAS FUNÇÕES SÓ VAO FUNCIONAR CASO OS FILHOS DO BORDERPANE
    FOREM OS TEXTFIELDS, SE OS TEXTFIELDS TIVEREM DENTRO DE OUTRO ELEMENTO, EXEMPLO:
    --BORDERPANE
        --VHBOX
            --TEXTFIELD
    
    NAO IRA FUNCIONAR POIS ELE VAI PEGAR O VHBOX E NAO ENTRARA NO INSTANCEOF
    */
    private void verificaTodosCamposDaTela(BorderPane bp){
        try{
            
            ObservableList<Node> obser =  bp.getChildren();
            for (int i = 0; i < obser.size(); i++) {
                
                if(obser.get(i) instanceof TextField ){
                    verificaCampo((TextField) obser.get(i));
                }
            }
        }catch(Exception e){
            
        }
    }
    
    
    private boolean verificaCampo(TextField txt){
        boolean erro = false;
        int index;
        if(txt.getText().trim().isEmpty())
        {
            index = txt.getStyleClass().size() -1;
          
            if(!txt.getStyleClass().get(index).toString().equals("text-field"))
               txt.getStyleClass().remove(index);
                
            txt.getStyleClass().add("text-field-erro");
            
                
            
            erro = true;
            
        }else{
            index = txt.getStyleClass().size() -1;
            
            if(txt.getStyleClass().get(index).toString().equals("text-field-erro"))
                txt.getStyleClass().remove(index);
            
            txt.getStyleClass().add("text-field-success");            
            

        }
        return erro;
    }
}
