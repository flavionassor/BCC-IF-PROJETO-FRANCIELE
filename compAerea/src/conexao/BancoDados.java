package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDados {
	// atributos
    private static Connection BancoDados = null;
    //esta eh a variavel fonte recebe o mesmo nome da base de dados criada no post gre sql
    private String fonte = "compaerea";   
	
	
    //Conexao Para a Base de Dados do PostgresSQL utilizando JDBC
    public BancoDados() {
        try {
            //Driver para fazer conexao com um Banco MySQL
            Class.forName("com.mysql.jdbc.Driver");

            //comando para fazer conexao via JDBC com um banco mysql
            //sendo informado o servidor 
            // + o nome da base de dados, o usuario e a senha.
            BancoDados = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + fonte, "root", "");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro de class nao encontrada!!!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro na conexao com o banco de dados!!!");
        }
    }

    public static Connection getInstance() {
        if (BancoDados == null) {
            new BancoDados();
        }
        return BancoDados;
    }

}
