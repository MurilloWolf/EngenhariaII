
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
import javafx.scene.control.Button;
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
            if (ctrcf.CadastrarFuncionario(txNome.getText(), txEmail.getText(), txTelefone.getText(), txCpf.getText(), txRg.getText(), txLogin.getText(), txSenha.getText(), txTipo.getText(), txNivel.getText(), txUf.getText(), txCidede.getText(), txBairro.getText(), txEndereco.getText(), txNumero.getText(), txCep.getText())) 
            {
                JOptionPane.showMessageDialog(null, "Cadastro realisado com sucesso");
            }
            else
            {
                JOptionPane.showMessageDialog(null, Banco.con.getMensagemErro());
            }

            flag = true;
        }
        
        if(!btnEditar.isDisable())
        {
            if (ctrcf.AlterarFuncionario(e,p))//txNome.getText(), txEmail.getText(), txTelefone.getText(), txCpf.getText(), txRg.getText(), txLogin.getText(), txSenha.getText(), txTipo.getText(), txNivel.getText(), txUf.getText(), txCidede.getText(), txBairro.getText(), txEndereco.getText(), txNumero.getText(), txCep.getText())) 
            {
                JOptionPane.showMessageDialog(null, "Alteração realisado com sucesso");
            }
            else
            {
                JOptionPane.showMessageDialog(null, Banco.con.getMensagemErro());
            }

            flag = true;
        }
        
        if(!btnExcluir.isDisable())
        {
            if (ctrcf.ExcluirFuncionario(e,p))//txNome.getText(), txEmail.getText(), txTelefone.getText(), txCpf.getText(), txRg.getText(), txLogin.getText(), txSenha.getText(), txTipo.getText(), txNivel.getText(), txUf.getText(), txCidede.getText(), txBairro.getText(), txEndereco.getText(), txNumero.getText(), txCep.getText())) 
            {
                JOptionPane.showMessageDialog(null, "Exclusao realisado com sucesso");
            }
            else
            {
                JOptionPane.showMessageDialog(null, Banco.con.getMensagemErro());
            }

            flag = true;
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
        FXMLprincipalController._pndados.getChildren().clear();
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
    
    
    
}