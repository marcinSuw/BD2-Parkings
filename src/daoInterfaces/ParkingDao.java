package daoInterfaces;
import objects.Parking;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by szwarc on 03.01.17.
 */
public interface ParkingDao {

    public ResultSet getParkings();
    public void updateParking(int id_parking, int new_address, int new_cost);
    public void deleteParking(int id_parking);
    public void addParking(int id_address, int cost_per_hour);
    public int get_parking_cost(int id_parking);
}
