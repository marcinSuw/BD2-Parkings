import daoInterfaces.ParkingDao;
import daoInterfacesImpl.ParkingDaoImpl;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by szwarc on 02.01.17.
 */

public class DataBaseModel {
    private DbConecction conect;
    private ParkingDao parkingDao;

    public DataBaseModel(){
        conect = new DbConecction("test.sqlite");
        parkingDao = new ParkingDaoImpl(conect.getConnection());

    }

    void insert_parking(int id_address, int cost_per_hour) {
        parkingDao.addParking(id_address,cost_per_hour);
    }

    void insert_address(int id_parking, String postal_code , String street_name, int number){
        String sql = "INSERT INTO \"Addresses\" VALUES(NULL,?, ?, ?, ?);";
        PreparedStatement prep = conect.getPrepStetm(sql);
        try {
            prep.setInt(1, id_parking);
            prep.setString(2, postal_code);
            prep.setString(3, street_name);
            prep.setInt(4, number);
            prep.executeUpdate();
        }
        catch(Exception e){ handle_exception(e);}
    }
    void insert_guard(int pesel, String name, String surname){
        String sql = " INSERT INTO \"Guards\" VALUES(?, ?, ?);";
        PreparedStatement prep = conect.getPrepStetm(sql);

        try {

            prep.setInt(1, pesel);
            prep.setString(2, name );
            prep.setString(3, surname);
            prep.executeUpdate();
        }
        catch(Exception e){ handle_exception(e);}
    }

    void insert_parking_guards(int pesel, int id_parking){
        String sql = "INSERT INTO \"Parkings_Guards\" VALUES(?, ?);";
        PreparedStatement prep = conect.getPrepStetm(sql);

        try {
            prep.setInt(1, pesel);
            prep.setInt(2, id_parking);
            prep.executeUpdate();
        }
        catch(Exception e){ handle_exception(e);}
    }

    void insert_ticket(int pesel, int id_parking, int charge, String regNumber, boolean paid){

        String sql = "INSERT INTO \"Tickets\" VALUES(NULL, ?, ?, ?, ?, ?);";
        PreparedStatement prep = conect.getPrepStetm(sql);
        try {
            prep.setInt(1, pesel);
            prep.setInt(2, id_parking);
            prep.setInt(3, charge);
            prep.setString(4, regNumber);
            prep.setBoolean(5, paid);
            prep.executeUpdate();
        }
        catch (Exception e){    handle_exception(e);    }

    }

    void insert_transaction(int id_meter, String start_date, String end_date, int cost){
        String sql = "INSERT INTO \"Transactions\" VALUES(NULL, ?, ?, ?, ?);";
        PreparedStatement prep = conect.getPrepStetm(sql);
        try{
            prep.setInt(1, id_meter);
            prep.setString(2, start_date);
            prep.setInt(4, cost);
            prep.setString(3, end_date);
            prep.executeUpdate();

        }
        catch(Exception e){ handle_exception(e);}
    }

    //TODO
    void insert_meter(int id_parking, int m_amount, int m_capcity, int p_amount, int p_capcity ){
        String sql = "INSERT INTO \"Meters\" VALUES(NULL, ?, ?, ?, ?, ?);";
        PreparedStatement prep = conect.getPrepStetm(sql);
        try{
            prep.setInt(1, id_parking);
            prep.setInt(2, m_amount);
            prep.setInt(3, m_capcity);
            prep.setInt(4, p_amount);
            prep.setInt(4, p_capcity);
            prep.executeUpdate();
        }
        catch(Exception e){ handle_exception(e);}
    }

    private void handle_exception(Exception e){
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }


}


