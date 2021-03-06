
package g2informatica;

import controllers.CtrcadastroFuncionario;
import db.Banco;
import eng2.util.MaskFieldUtil;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import models.Endereco;
import models.Funcionario;
import models.Pessoa;
import models.dao.DaoFuncionario;

public class CadastroFuncionarioController implements Initializable {

    @FXML
    private VBox pnCad;
    @FXML
    private HBox hbBotoes;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSair;
    @FXML
    private AnchorPane apDados;
    @FXML
    private TextField txNome;
    @FXML
    private TextField txCpf;
    @FXML
    private TextField txEndereco;
    @FXML
    private TextField txCep;
    @FXML
    private TextField txUf;
    @FXML
    private TextField txTelefone;
    @FXML
    private TextField txBairro;
    @FXML
    private TextField txLogin;
    @FXML
    private TextField txSenha;
    @FXML
    private TextField txNumero;
    @FXML
    private TextField txRg;
    @FXML
    private TextField txCidede;
    @FXML
    private TextField txEmail;
    @FXML
    private HBox hbPesquisa;
    @FXML
    private Button btnPesquisa;
    @FXML
    private TextField txfPesquisa;
    @FXML
    private ComboBox<String> cbPesquisa;
    @FXML
    private TableView<Funcionario> tbPesquisaFuncionario;
    @FXML
    private TableColumn tcCodigo;
    @FXML
    private TableColumn tcNome;
    @FXML
    private TableColumn tcCpf;
    @FXML
    private TableColumn tcEmail;
    @FXML
    private TextField txTipo;
    @FXML
    private TextField txNivel;
    
    CtrcadastroFuncionario ctrcf = new CtrcadastroFuncionario();
    Endereco e;
    Pessoa p;
    
    ObservableList<Funcionario> lista = FXCollections.observableArrayList();
    
    //ObservableList<Produto> lista = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ctrcf.IniciaEntidade(p, e);
        
        MaskFieldUtil.cpfField(txCpf);
        MaskFieldUtil.foneField(txTelefone);
        MaskFieldUtil.cepField(txCep);
        
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("cod"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        apDados.setDisable(true);
        btnCancelar.setDisable(true);
        btnConfirmar.setDisable(true);
        hbPesquisa.setDisable(true);

        cbPesquisa.getItems().add("Filtro");
        cbPesquisa.getItems().add("Codigo");
        cbPesquisa.getItems().add("Nome");
        cbPesquisa.getItems().add("Cpf");
        cbPesquisa.getItems().add("Login");
        cbPesquisa.getSelectionModel().select(0);
        
    }

    @FXML
    private void evtNovo(ActionEvent event) {
        apDados.setDisable(false);
        btnCancelar.setDisable(false);
        btnConfirmar.setDisable(false);
        btnNovo.setDisable(false);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
        hbPesquisa.setDisable(true);
    }

    @FXML
    private void evtEditar(ActionEvent event) {
        apDados.setDisable(true);
        btnCancelar.setDisable(false);
        btnConfirmar.setDisable(true);
        btnNovo.setDisable(true);
        btnEditar.setDisable(false);
        btnExcluir.setDisable(true);
        hbPesquisa.setDisable(false);
    }

    @FXML
    private void evtExcluir(ActionEvent event) {
        apDados.setDisable(true);
        btnCancelar.setDisable(false);
        btnConfirmar.setDisable(true);
        btnNovo.setDisable(true);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(false);
        hbPesquisa.setDisable(false);
    }

    @FXML
    private void evtConfirmar(ActionEvent event) throws SQLException {
        boolean flag = false;
        
        if(!btnNovo.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tem certeza que deseja salvar ? ");
            if(!verificaTodosCamposDaTela()){
                if (alert.showAndWait().get() == ButtonType.OK) {

                if (ctrcf.CadastrarFuncionario(txNome.getText(), txEmail.getText(), txTelefone.getText(), txCpf.getText(), txRg.getText(), txLogin.getText(), txSenha.getText(), txTipo.getText(), txNivel.getText(), txUf.getText(), txCidede.getText(), txBairro.getText(), txEndereco.getText(), txNumero.getText(), txCep.getText())) {
                    JOptionPane.showMessageDialog(null, "Cadastro realisado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "nao foi possivel salvar: "+Banco.con.getMensagemErro());
                }

                flag = true;
                }
            }
            
        }
        
        if(!btnEditar.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tem certeza que deseja alterar ? ");

            if (alert.showAndWait().get() == ButtonType.OK) {

                
                if (ctrcf.AlterarFuncionario(p.getCod(),txNome.getText(), txEmail.getText(), txTelefone.getText(), txCpf.getText(), txRg.getText(), txLogin.getText(), txSenha.getText(), txTipo.getText(), txNivel.getText(), txUf.getText(), txCidede.getText(), txBairro.getText(), txEndereco.getText(), txNumero.getText(), txCep.getText(), e.getCodigo()))//txNome.getText(), txEmail.getText(), txTelefone.getText(), txCpf.getText(), txRg.getText(), txLogin.getText(), txSenha.getText(), txTipo.getText(), txNivel.getText(), txUf.getText(), txCidede.getText(), txBairro.getText(), txEndereco.getText(), txNumero.getText(), txCep.getText())) 
                {
                    JOptionPane.showMessageDialog(null, "Alteração realisada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "nao foi possivel alterar: "+ Banco.con.getMensagemErro());
                }

                flag = true;
            }
        }
        
        if(!btnExcluir.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Tem certeza que deseja excluir ? ");

            if (alert.showAndWait().get() == ButtonType.OK) {

                if (ctrcf.ExcluirFuncionario(e, p))//txNome.getText(), txEmail.getText(), txTelefone.getText(), txCpf.getText(), txRg.getText(), txLogin.getText(), txSenha.getText(), txTipo.getText(), txNivel.getText(), txUf.getText(), txCidede.getText(), txBairro.getText(), txEndereco.getText(), txNumero.getText(), txCep.getText())) 
                {
                    JOptionPane.showMessageDialog(null, "Exclusao realisada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "nao foi possivel excluir: "+Banco.con.getMensagemErro());
                }

                flag = true;
            }
        }
        if(flag)
        {
            ctrcf.LimpaEntidade(p, e);
        
            hbPesquisa.setDisable(true);
            apDados.setDisable(true);
            btnCancelar.setDisable(true);
            btnConfirmar.setDisable(true);
            btnNovo.setDisable(false);
            btnEditar.setDisable(false);
            btnExcluir.setDisable(false);
            lista.clear();
            tbPesquisaFuncionario.getItems().clear();
            //apDados.getChildren().clear();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event) 
    {
        ctrcf.LimpaEntidade(p, e);
        hbPesquisa.setDisable(true);
        apDados.setDisable(true);
        btnCancelar.setDisable(true);
        btnConfirmar.setDisable(true);
        btnNovo.setDisable(false);
        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
        lista.clear();
        tbPesquisaFuncionario.getItems().clear();
    }

    @FXML
    private void evtSair(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tem certeza que deseja sair do cadastro de Funcionario? ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            FXMLprincipalController._pndados.getChildren().clear();
        }
    }

    @FXML
    private void evtPesquisa(ActionEvent event) throws SQLException 
    {
        tbPesquisaFuncionario.getItems().clear();
        
        lista.clear();
        
        lista.addAll(ctrcf.Pesquisa(txfPesquisa.getText(), cbPesquisa.getSelectionModel().getSelectedIndex()));
        
        tbPesquisaFuncionario.getItems().addAll(lista);
    }

    @FXML
    private void evtTabela(MouseEvent event) {
        p = (Funcionario) tbPesquisaFuncionario.getSelectionModel().getSelectedItem();
        e = e.buscarEnderecoPorCodigo(((Funcionario)p).getEnd_cod());
        
        txBairro.setText(e.getBairro());
        txCep.setText(e.getCep());
        txCidede.setText(e.getCidade());
        txCpf.setText(((Funcionario)p).getCpf());
        txEmail.setText(((Funcionario)p).getEmail());
        txEndereco.setText(e.getRua());
        txLogin.setText(((Funcionario)p).getId_login());
        txNivel.setText(((Funcionario)p).getNivel());
        txNome.setText(((Funcionario)p).getNome());
        txNumero.setText(e.getNumero());
        txRg.setText(((Funcionario)p).getRg());
        txSenha.setText(((Funcionario)p).getSenha());
        txTelefone.setText(((Funcionario)p).getTelefone());
        txTipo.setText(((Funcionario)p).getTipo());
        txUf.setText(e.getUf());
        
        
        if(!btnEditar.isDisable())
            apDados.setDisable(false);
        
        btnConfirmar.setDisable(false);
    }
    
    private boolean verificaTodosCamposDaTela(){
        boolean erro = false;
        try{
            
            ObservableList<Node> obser =  apDados.getChildren();
            for (int i = 0; i < obser.size(); i++) {
                if(obser.get(i) instanceof TextField ){
                  erro =  verificaCampo((TextField) obser.get(i));
                }
            }
        }catch(Exception e){
            
        }
        
        return erro;
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