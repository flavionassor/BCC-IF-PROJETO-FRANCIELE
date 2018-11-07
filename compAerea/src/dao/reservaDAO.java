
package dao;

import bean.reserva;
import com.mysql.jdbc.PreparedStatement;
import conexao.BancoDados;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reservaDAO {
    
    public int qntAssento(int codAviao){
        PreparedStatement sql; 
        int assento=0;
        try{
            sql=(PreparedStatement) BancoDados.getInstance().prepareStatement
            ("select A.cod from assento A where A.aviao_cod = " + codAviao + " and A.cod not in(select R.assento_cod from reserva R);");
            ResultSet rs = sql.executeQuery();
            
            while(rs.next()){
                assento++;
            }// fim do while
            
        }// fim do try
        catch(SQLException ex) {
          System.out.println(ex);
        }
        System.out.println("quantidade de assentos vagos que o voo possui: " + assento);
        return assento;
    }
    
    public void gravar(reserva R){
        PreparedStatement sql;
        try{
        sql=(PreparedStatement) BancoDados.getInstance().prepareStatement
        ("insert into reserva(cod, cpf, assento_cod) values (null,?,?)");
        sql.setString(1,R.getCpf());
        sql.setString(2,R.getAssento_cod());
        sql.execute();
        }
        catch(SQLException ex) {
          System.out.println(ex);
        }
    }
}
