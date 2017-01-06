import daoInterfaces.*;
import daoInterfacesImpl.*;
import objects.Guard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by szwarc on 02.01.17.
 */

public class DataBaseModel {
    private DbConnector connector;
    private ParkingDao parkingDao;
    private AddressDao addressDao;
    private GuardDao guardDao;
    private ParkingGuardDao parkingGuardDao;
    private TicketDao ticketDao;

    public DataBaseModel(){
        connector = new DbConnector("test.sqlite");
        parkingDao = new ParkingDaoImpl(connector.getConnection());
        addressDao = new AddressDaoImpl(connector.getConnection());
        guardDao = new GuardDaoImpl(connector.getConnection());
        parkingGuardDao = new ParkingGuardDaoImpl(connector.getConnection());
        ticketDao = new TicketDaoImpl(connector.getConnection());
    }

    public ParkingDao getParkingDao() { return this.parkingDao; }

    public AddressDao getAddressDao() { return this.addressDao; }

    public GuardDao getGuardDao() { return this.guardDao; }

    public ParkingGuardDao getParkingGuardDao() { return this.parkingGuardDao; }

    public TicketDao getTicketDao() { return this.ticketDao; }
    
    public DbConnector getConnector() { return this.connector; }
    
    public String[] getAllTableNames() {
        //propably should be done smarter
        return new String[]{"Parkings", "Addresses", "Guards", "Parkings_Guards", "Tickets"};
    }

    void insert_ticket(int pesel, int id_parking, int charge, String regNumber, boolean paid){

        String sql = "INSERT INTO \"Tickets\" VALUES(NULL, ?, ?, ?, ?, ?);";
        PreparedStatement prep = connector.getPrepStetm(sql);
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
        PreparedStatement prep = connector.getPrepStetm(sql);
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
        PreparedStatement prep = connector.getPrepStetm(sql);
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

    public String addFromUserInput(String table, ArrayList<String> input) {
        if (!Arrays.asList(getAllTableNames()).contains(table)) 
            throw new RuntimeException("No table of name " + table + " exist");
        try {
            switch(table) {
                case "Parkings":
                    //TODO:
                    //int address_key = getAddressDao().addAddress(
                    getParkingDao().addParking(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)));
            }
        } catch (Exception e) {
            return e.getClass() + " " + e.getMessage();
        }
        return null;
    }

    private void handle_exception(Exception e){
        System.err.println("DataBaseModel: "+ e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }

    public void close_connection(){
        connector.closeDB();
    }

}


