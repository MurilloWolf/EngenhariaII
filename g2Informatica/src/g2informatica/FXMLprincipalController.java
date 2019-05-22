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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _pndados = pnDados;
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
            System.exit(0);
        else
        {
            lbLoguin.setText("Login: " + TelaLoginController.login);
            if(TelaLoginController.ni == 2)
            {
                
                //desativar o botao de parametrizaçao aqui quando estiver pronto
            }
            else if(TelaLoginController.ni == 1)
            {
                //ativar todos os botoes
            }
            else if(TelaLoginController.ni == 0)
            {
                //destiva tudo
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
    
}
