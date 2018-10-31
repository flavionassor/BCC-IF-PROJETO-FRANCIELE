package dao;

import bean.assento;
import com.mysql.jdbc.PreparedStatement;
import conexao.BancoDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class vooDAO {
    
    public int codVoo(String origem, String dataSaida, String destino, String dataChegada){
        PreparedStatement sql; 
        int cod=0;
        try{
            sql=(PreparedStatement) BancoDados.getInstance().prepareStatement
            ("select V.cod from voo V where v.origem = '" + origem + "' and v.datasaida = '" + dataSaida + "' and v.destino = '" + destino + "' and v.datachegada = '" +
                    dataChegada + "';");
            ResultSet rs = sql.executeQuery();
            rs.next();
            cod = rs.getInt("cod");
            
        }// fim do try
        catch(SQLException ex) {
          System.out.println(ex);
        }
        System.out.println("Cod do avião: " + cod);
        return cod;
    }
    
}
