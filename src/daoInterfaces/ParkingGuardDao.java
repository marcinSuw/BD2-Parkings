package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 05.01.17.
 */
public interface ParkingGuardDao {
    public ResultSet getParkingsGuards();
    public void updateParkingGuard(int old_pesel, int new_pesel, int old_parking, int new_parking);
    public void deleteParkingGuard(int id_parking);
    public void addParkingGuard(int id_parking, int pesel);

}
