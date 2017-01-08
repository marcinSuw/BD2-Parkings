package daoInterfacesImpl;

import daoInterfaces.MeterDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by szwarc on 07.01.17.
 */
public class MeterDaoImpl extends DaoUtilities implements MeterDao {

    private Connection connection;

    public MeterDaoImpl(Connection connection) { this.connection = connection; }

    @Override
    public ResultSet getMeters() {

        return this.get_objects(connection, "Meters");
    }

    @Override
    public void updateMeters(int id_meter, int id_parking, int moneyAmount, int moneyCapcity, int paperAmount, int paperCapcity) {
        String sql = "UPDATE Meters SET  Id_Parking = ?, moneyAmount = ?, moneyCapcity = ?, paperAmount = ?, paperCapcity = ?  WHERE Id_Meter = ?;";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_parking);
            prep.setInt(2, moneyAmount);
            prep.setInt(3, moneyCapcity);
            prep.setInt(4, paperAmount);
            prep.setInt(5, paperCapcity);
            prep.setInt(6, id_meter);
            prep.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException("MeterDaoImpl: updateMeter");
        }

    }

    @Override
    public void deleteMeter(int id_meter) { this.delete_object(connection, "Meters", "Id_Meter", id_meter); }

    @Override
    public void addMeter(int id_parking, int moneyAmount, int moneyCapcity, int paperAmount, int paperCapcity) {
        String sql = "INSERT INTO \"Meters\" VALUES(NULL, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_parking);
            prep.setInt(2, moneyAmount);
            prep.setInt(3, moneyCapcity);
            prep.setInt(4, paperAmount);
            prep.setInt(5, paperCapcity);
            prep.executeUpdate();
        }
        catch (Exception e ){
            throw new RuntimeException("MeterDaoImpl: addMeter");
        }
    }
    @Override
    public int get_meter_id_parking(int id_meter){
        int id_parking =0;

        String sql = "SELECT Id_Parking FROM \"Meters\" WHERE Id_Meter = ? ;";
        try{
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_meter);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                id_parking = rs.getInt("Id_Parking");
            }
        }
        catch(Exception e){
            throw new RuntimeException("MeterDaoImpl: get_id_parking");

        }


        return id_parking;

    }
}
