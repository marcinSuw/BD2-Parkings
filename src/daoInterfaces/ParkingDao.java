package daoInterfaces;
import objects.Parking;

import java.sql.Connection;

/**
 * Created by szwarc on 03.01.17.
 */
public interface ParkingDao {

    public Parking getParkings();
    public void updateParking(int id_parking, int new_cost);
    public void deleteParking(int id_parking);
    public void addParking(int id_address, int cost_per_hour);
}
