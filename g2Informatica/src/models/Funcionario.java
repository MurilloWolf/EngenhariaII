package models;

public class Funcionario extends Pessoa
{
    //int cod fun_cod
    //String nome fun_nome
    private String email;// fun_email
    private String telefone;// fun_telefone
    private String cpf;// fun_cpf
    private String rg;// fun_rg
    private int end_cod;// Endereco_end_cod
    private String id_login;// fun_id_login
    private String senha;// fun_senha
    private String tipo;// fun_tipo
    private String nivel;// fun_nivel

    public Funcionario(int cod, String nome, String email, String telefone, String cpf, String rg, int end_cod, String id_login, String senha, String tipo, String nivel) {
        super(cod, nome);
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.rg = rg;
        this.end_cod = end_cod;
        this.id_login = id_login;
        this.senha = senha;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public Funcionario() {
        super(-1, "");
        this.email = "";
        this.telefone = "";
        this.cpf = "";
        this.rg = "";
        this.end_cod = -1;
        this.id_login = "";
        this.senha = "";
        this.tipo = "";
        this.nivel = "";
    }

    public Funcionario(String nome, String email, String telefone, String cpf, String rg, int end_cod, String id_login, String senha, String tipo, String nivel) {
        super(nome);
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.rg = rg;
        this.end_cod = end_cod;
        this.id_login = id_login;
        this.senha = senha;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    Funcionario(int cod) {
        
    }

    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public int getEnd_cod() {
        return end_cod;
    }

    public void setEnd_cod(int end_cod) {
        this.end_cod = end_cod;
    }

    public String getId_login() {
        return id_login;
    }

    public void setId_login(String id_loguin) {
        this.id_login = id_loguin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    

    
}
