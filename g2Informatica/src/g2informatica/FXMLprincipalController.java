package g2informatica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLprincipalController implements Initializable {
    
    public static HBox _pndados;
    @FXML
    private Button btnLoguar;
    @FXML
    private Label lbLoguin;
    @FXML
    private Button btnCadFunc;
    @FXML
    private HBox pnDados;
    @FXML
    private Button btnParametrizacao;
    @FXML
    private Button btnProduto;
    @FXML
    private Button btnOfertas;
    @FXML
    private Button btnQuitarContas;
    @FXML
    private Button btComprarProdutos;
    @FXML
    private Button btnOrcamento;
    
    
    void disabilita()
    {
        btnCadFunc.setDisable(true);
        btnParametrizacao.setDisable(true);
        btnProduto.setDisable(true);
        _pndados.getChildren().clear();
    }
    
    void abilita()
    {
        btnCadFunc.setDisable(false);
        btnParametrizacao.setDisable(false);
        btnProduto.setDisable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _pndados = pnDados;
        disabilita();
        //evtLogar(null);
    }    

    @FXML
    private void evtLogar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (TelaLoginController.login.equals(""))
            JOptionPane.showMessageDialog(null, "Usuario Invalido");
        else
        {
            if (btnLoguar.getText().equals("Logar")) {
                lbLoguin.setText(TelaLoginController.login);
                if (TelaLoginController.ni == 2) {
                    abilita();
                    btnParametrizacao.setDisable(true);
                    //desativar o botao de parametrizaçao aqui quando estiver pronto
                } else if (TelaLoginController.ni == 1) {
                    abilita();
                    //ativar todos os botoes
                } else if (TelaLoginController.ni == 0) {
                    disabilita();
                }
                btnLoguar.setText("Deslogar");
            }
            else
            {
                disabilita();
                btnLoguar.setText("Logar");
                lbLoguin.setText("");
            }
        }
    }

    @FXML
    private void evtCadFunc(ActionEvent event) {
        try
        {    
            Parent root = FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"));
            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } 
        catch (Exception e) {System.out.println(e);}
    }

    @FXML
    private void clickAbrirParametrizacao(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Empresa.fxml"));
            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        }catch(Exception e ){
            System.out.println("Erro :"+e);
        }
            
    }

    @FXML
    private void clickAbrirProdutos(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("CadastroProduto.fxml"));
            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        }catch(Exception e ){
            System.out.println("Erro :"+e);
        }
        
    }

    @FXML
    private void clickGerenciarOfertas(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("GerenciarOferta.fxml"));
            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        }catch(Exception e ){
            System.out.println("Erro :"+e);
        }
    }

    @FXML
    private void evtQuitarContas(ActionEvent event) {
        try
        {    
            Parent root = FXMLLoader.load(getClass().getResource("QuitarContasPagar.fxml"));
            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } 
        catch (Exception e) {System.out.println(e);}
    }

    @FXML
    private void evtComprarProdutos(ActionEvent event) {
        try
        {    
            Parent root = FXMLLoader.load(getClass().getResource("ComprarProdutos.fxml"));
            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } 
        catch (Exception e) {System.out.println(e);}
    }

    @FXML
    private void clickGerenciarOrcamento(ActionEvent event) {
         try
        {    
            Parent root = FXMLLoader.load(getClass().getResource("GerenciarOrcamento.fxml"));
            pnDados.getChildren().clear();
            pnDados.getChildren().add(root);
        } 
        catch (Exception e) {System.out.println(e);}
    }
    
}
