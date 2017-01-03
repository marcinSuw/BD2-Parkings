package daoInterfacesImpl;

import daoInterfaces.ParkingDao;
import objects.Parking;

import java.sql.Connection;
import java.sql.PreparedStatement;



/**
 * Created by szwarc on 03.01.17.
 */

public class ParkingDaoImpl implements ParkingDao {

    private Connection connection;

    public ParkingDaoImpl(Connection con){
        this.connection = con;
    }

    @Override
    public void deleteParking(int id_parking){
        int x = 0;
    }

    @Override
    public Parking getParkings(){
        int x = 0;
        Parking par = new Parking(1,1);
        return par;
    }
    @Override
    public void updateParking(int id_parking, int new_cost){
        String sql = "UPDATE Parkings SET costPerHour = ? WHERE Id_Parking = ?;";
        excecuteQueru(new_cost, id_parking, sql);
    }

    @Override
    public void addParking(int id_address, int cost_per_hour) {
        String sql = "INSERT INTO \"Parkings\" VALUES (NULL, ?, ?);";
        excecuteQueru(id_address, cost_per_hour, sql);
    }


    private void excecuteQueru(int first, int second, String sql){
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, first);
            prep.setInt(2, second);
            prep.executeUpdate();
        }
        catch(Exception e){
            handle_exc(e);
        }

    }

    private void handle_exc(Exception e){
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }

}
