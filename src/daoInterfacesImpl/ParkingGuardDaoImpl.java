package daoInterfacesImpl;

import daoInterfaces.ParkingGuardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by szwarc on 05.01.17.
 */
public class ParkingGuardDaoImpl extends DaoUtilities implements ParkingGuardDao {
    Connection connection;

    public ParkingGuardDaoImpl(Connection connection){ this.connection = connection; }

    @Override
    public ResultSet getParkingsGuards() {
        return this.get_objects(connection, "Parkings_Guards");
    }

    @Override
    public void updateParkingGuard(int old_pesel, int new_pesel, int old_parking, int new_parking) {
        String sql = "UPDATE Parkings_Guards SET pesel = ?, Id_Parking = ? WHERE pesel = ? AND Id_Parking = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, new_pesel);
            stmt.setInt(2, new_parking);
            stmt.setInt(3, old_pesel);
            stmt.setInt(4, old_parking);
            stmt.executeUpdate();
        }
        catch (Exception e){
            handle_exc(e, "updateParkingGuard");
        }
    }

    @Override
    public void deleteParkingGuard(int id_parking) {
        delete_object(connection, "Parkings_Guards", "Id_parking", id_parking);
    }

    @Override
    public void addParkingGuard(int id_parking, int pesel) {

        String sql = "INSERT INTO \"Parkings_Guards\" VALUES(?, ?);";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id_parking);
            stmt.setInt(2, pesel);
            stmt.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("GuardDaoImpl: addParkingGuard ");
        }
    }

    private void handle_exc(Exception e, String name_function){
        throw new RuntimeException("GuardDaoImpl: " +name_function + e.getClass().getName() + ": " + e.getMessage());
    }


}
