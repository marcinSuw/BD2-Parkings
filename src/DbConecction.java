import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by szwarc on 02.01.17.
 */
public class DbConecction {

    Connection connection;

    public DbConecction(String file_name){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+file_name);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");

    }


    public PreparedStatement getPrepStetm(String sql){
        PreparedStatement  stmt = null;

        try {
            stmt = connection.prepareStatement(sql);
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return stmt;
    }

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection con){
        connection = con;
    }

    public void closeDB() {
        try {
            if (connection!= null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Could not close the current connection.");
            e.printStackTrace();
        }
    }
}
