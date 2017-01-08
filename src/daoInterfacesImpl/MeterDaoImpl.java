package daoInterfacesImpl;

import daoInterfaces.MeterDao;
import objects.Meter;

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
    public void updateMeters(int id_meter, int id_parking, int monetAmount, int monetCapcity, int paperAmount, int paperCapcity) {
        String sql = "UPDATE Meters SET  Id_Parking = ?, monetAmount = ?, monetCapcity = ?, paperAmount = ?, paperCapcity = ?  WHERE Id_Meter = ?;";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_parking);
            prep.setInt(2, monetAmount);
            prep.setInt(3, monetCapcity);
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
    public Meter get_meter(int id_meter){

        int id_parking =0;
        int moneyAmount = 0;
        int moneyCapcity = 0;
        int paperAmount = 0;
        int paperCapcity = 0;


        String sql = "SELECT Id_Parking, monetAmount, monetCapcity, paperAmount, paperCapcity  FROM \"Meters\" WHERE Id_Meter = ? ;";
        try{
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_meter);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                id_parking = rs.getInt("Id_Parking");
                moneyAmount = rs.getInt("monetAmount");
                moneyCapcity = rs.getInt("monetCapcity");
                paperAmount = rs.getInt("paperAmount");
                paperCapcity = rs.getInt("paperCapcity");
            }
        }
        catch(Exception e){
            throw new RuntimeException("MeterDaoImpl: get_meter");
        }

        Meter meter = new Meter(id_meter, id_parking, moneyAmount, moneyCapcity, paperAmount, paperCapcity);

        return meter;

    }
}
