package daoInterfacesImpl;

import daoInterfaces.GuardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by szwarc on 05.01.17.
 */
public class GuardDaoImpl extends DaoUtilities implements GuardDao {
    private Connection connection;

    public GuardDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public ResultSet getGuards() {
        return this.get_objects(connection, "Guards");
    }

    @Override
    public void updateGuard(int pesel, String name, String surname) {
        String sql = "UPDATE Guards SET name = ?, surname = ? WHERE pesel = ?;";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setInt(3, pesel);
            stmt.executeUpdate();
        }
        catch(Exception e){  throw new RuntimeException("GuardDaoImpl: updateGuard"); }
    }

    @Override
    public void deleteGuard(int pesel) {
        this.delete_object(connection, "Guards", "pesel", pesel);
    }

    @Override
    public void addGuard(int pesel, String name, String surname) {
        String sql = "INSERT INTO \"Guards\" VALUES(?, ?, ?);";
        try{
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, pesel);
            prep.setString(2, name);
            prep.setString(3, surname);
            prep.executeUpdate();
        }
        catch (Exception e){ handle_exc(e, "addGuard"); }
    }

    private void handle_exc(Exception e, String name_function){
        throw new RuntimeException("GuardDaoImpl: " +name_function + e.getClass().getName() + ": " + e.getMessage());
    }
}
