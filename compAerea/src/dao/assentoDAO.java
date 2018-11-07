package dao;

import bean.assento;
import com.mysql.jdbc.PreparedStatement;
import conexao.BancoDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class assentoDAO {
    
    
     public ArrayList consultar(int idavi){
        PreparedStatement sql; 
        ArrayList assento = new ArrayList();
        try{
            sql=(PreparedStatement) BancoDados.getInstance().prepareStatement
            ("SELECT DISTINCT A.COD FROM ASSENTO A, RESERVA R WHERE "+ idavi + " = A.AVIAO_COD AND A.COD NOT IN (SELECT RESERVA.ASSENTO_COD FROM RESERVA);");
            ResultSet rs = sql.executeQuery();
            
            while(rs.next()){               
                //aa.setCod();
                assento.add(rs.getString("cod"));
            }// fim do while
            
        }// fim do try
        catch(SQLException ex) {
          System.out.println(ex);
        }
        System.out.println(assento);
        return assento;
    }
     
     public int teste(){
        PreparedStatement sql; 
        int assento=0;
        try{
            sql=(PreparedStatement) BancoDados.getInstance().prepareStatement
            ("select *from assento");
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
