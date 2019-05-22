/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2informatica;

import db.Banco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Empresa;
import models.Endereco;

/**
 *
 * @author Aluno
 */
public class G2Informatica extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLprincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);

        //se conectar com o banco de dados
        if(  Banco.conectar() ){
            
            System.out.println("Status:"+Empresa.getStatus());
            //se ja há uma parametrização , login da tela inicial
            if(Empresa.getStatus()){
                stage.show();
            }
            else
            {
                 /*
                Endereco end = new Endereco(1);
                System.out.println(end.getCidade());
                */
                //login da tela de parametrizacao
                System.out.println("Iniciar Parametrizacao");
                root = FXMLLoader.load(getClass().getResource("Empresa.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
               
            }
        
        }
      
       
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
