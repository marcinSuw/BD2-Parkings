package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 07.01.17.
 */
public interface MeterDao {
    public ResultSet getMeters();
    public void updateMeters(int id_meter, int id_parking, int moneyAmount, int moneyCapcity, int paperAmount, int paperCapcity);
    public void deleteMeter(int id_meter);
    public void addMeter(int id_parking, int moneyAmount, int moneyCapcity, int paperAmount, int paperCapcity);
    public int get_meter_id_parking(int id_meter);
}
