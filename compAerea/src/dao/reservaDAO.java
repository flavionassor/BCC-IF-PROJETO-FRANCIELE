
package dao;

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
            ("select *from assento where aviao = " + codAviao + ";");
            ResultSet rs = sql.executeQuery();
            
            while(rs.next()){
                assento++;
            }// fim do while
            
        }// fim do try
        catch(SQLException ex) {
          System.out.println(ex);
        }
        System.out.println(assento);
        return assento;
    }
}
