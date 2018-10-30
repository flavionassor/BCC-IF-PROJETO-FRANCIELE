package dao;

import bean.aviao;
import com.mysql.jdbc.PreparedStatement;
import conexao.BancoDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class aviaoDAO {
    
    
    public ArrayList consultar(){
        PreparedStatement sql; 
        ArrayList aviao = new ArrayList();
        try{
            sql=(PreparedStatement) BancoDados.getInstance().prepareStatement
            ("SELECT * FROM AVIAO");
            ResultSet rs = sql.executeQuery();
            
            while(rs.next()){
                aviao aa= new aviao();
                aa.setCod(rs.getInt("idaviao"));
                aa.setMarca(rs.getString("marca"));
                aa.setModelo(rs.getString("modelo"));
                aa.setQntassento(rs.getInt("qntassento"));
                aviao.add(aa);
            }// fim do while
            
        }// fim do try
        catch(SQLException ex) {
          System.out.println(ex);
        }
        System.out.println(aviao);
        return aviao;
    }
   
}
