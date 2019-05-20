package db;


public class Banco {
    
    static public Conexao con = null;
    
    private Banco() {
    
    }
    
    static public boolean conectar() {
        if(con == null) {
            con = new Conexao();

            return con.conectar("jdbc:mysql://localhost:3306/","bancoG2","root","mwolf1234");
            //return con.conectar("jdbc:postgresql://localhost/", "sismanhe", "postgres", "mwolf1234");
            //return con.conectar("jdbc:postgresql://localhost:5432/","engenharia2.0","postgres", "postgres123");
        }
        return true;
    }
}
