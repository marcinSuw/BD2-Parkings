package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 05.01.17.
 */
public interface ParkingGuardDao {
    public ResultSet getParkingsGuards();
    public void updateParkingGuard(int id_parking, int pesel);
    public void deleteParkingGuard(int id_parking);
    public void addParkingGuard(int id_parking, int pesel);

}
