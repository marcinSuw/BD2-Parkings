package daoInterfacesImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by szwarc on 04.01.17.
 */
public class DaoUtilities {

    public void delete_object(Connection con, String table_name, String col_name, Object val){

        String sql = "DELETE FROM "+table_name+" WHERE "+ col_name+" = ?;";
        try {
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setObject(1, val);
            prep.executeUpdate();
        }
        catch(Exception e){
            System.err.println("Delete_object: "+ e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public ResultSet get_objects(Connection con, String table_name){
        String sql = "SELECT * FROM "+table_name+";";
        ResultSet rs = null;
        try{
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        }
        catch(Exception e){
            System.err.println("Get_Objects: " + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
    }

}
