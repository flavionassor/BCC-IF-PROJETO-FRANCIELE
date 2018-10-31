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
            ("SELECT DISTINCT A.IDASSENTO, A.AVIAO_IDAVIAO FROM ASSENTO A, AVIAO B WHERE A.IDASSENTO <= B.QNTASSENTO AND A.AVIAO_IDAVIAO = " + idavi + ";");
            ResultSet rs = sql.executeQuery();
            
            while(rs.next()){
                assento aa= new assento();                
                aa.setCod(rs.getInt("idassento"));
                aa.setCodaviao(rs.getInt("aviao_idaviao"));
                assento.add(aa);
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
