import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by szwarc on 02.01.17.
 */
public class DbConnector {

    Connection connection;

    public DbConnector(String file_name){

        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection("jdbc:sqlite:"+file_name, config.toProperties());
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
                System.out.println("Connection closed successfully!");
            }
        } catch (Exception e) {
            System.out.println("Could not close the current connection.");
            e.printStackTrace();
        }
    }
}
