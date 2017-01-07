package daoInterfacesImpl;

import daoInterfaces.ParkingDao;
import objects.Parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by szwarc on 03.01.17.
 */

public class ParkingDaoImpl extends DaoUtilities implements ParkingDao {

    private Connection connection;

    public ParkingDaoImpl(Connection con){
        this.connection = con;
    }

    @Override
    public void deleteParking(int id_parking){
        this.delete_object(connection, "Parkings", "Id_Parking", id_parking);
    }

    @Override
    public ResultSet getParkings(){
        return this.get_objects(connection, "Parkings");
    }

    @Override
    public void updateParking(int id_parking, int new_cost, int id_address){
        String sql = "UPDATE Parkings SET costPerHour = ?, Id_Address = ? WHERE Id_Parking = ?;";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, new_cost);
            prep.setInt(2, id_address);
            prep.setInt(3, id_parking);
            prep.executeUpdate();
        }
        catch(Exception e){
            handle_exc(e);
        }
    }

    @Override
    public void addParking( int id_address, int cost_per_hour) {
        String sql = "INSERT INTO \"Parkings\" VALUES (NULL, ?, ?);";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_address);
            prep.setInt(2, cost_per_hour);
            prep.executeUpdate();
        }
        catch(Exception e){
            handle_exc(e);
        }
    }



    private void handle_exc(Exception e){
        throw new RuntimeException("ParkingDaoImpl: "+ e.getClass().getName() + ": " + e.getMessage());
    }

}
