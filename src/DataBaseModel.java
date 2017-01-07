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
    private MeterDao meterDao;
    private TransactionDao transactionDao;

    public DataBaseModel(){
        connector = new DbConnector("test.sqlite");
        parkingDao = new ParkingDaoImpl(connector.getConnection());
        addressDao = new AddressDaoImpl(connector.getConnection());
        guardDao = new GuardDaoImpl(connector.getConnection());
        parkingGuardDao = new ParkingGuardDaoImpl(connector.getConnection());
        ticketDao = new TicketDaoImpl(connector.getConnection());
        meterDao = new MeterDaoImpl(connector.getConnection());
        transactionDao = new TransactionDaoImpl(connector.getConnection());
    }

    public ParkingDao getParkingDao() { return this.parkingDao; }

    public AddressDao getAddressDao() { return this.addressDao; }

    public GuardDao getGuardDao() { return this.guardDao; }

    public ParkingGuardDao getParkingGuardDao() { return this.parkingGuardDao; }

    public TicketDao getTicketDao() { return this.ticketDao; }
    
    public DbConnector getConnector() { return this.connector; }

    public MeterDao getMeterDao() { return  this.meterDao; }

    public TransactionDao getTransactionDao() { return  this.transactionDao; }
    
    public String[] getAllTableNames() {
        //propably should be done smarter
        return new String[]{"Parkings", "Addresses", "Guards", "Parkings_Guards", "Tickets", "Meters", "Transactions"};
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

    private void register_transaction(int id_meter, int duration){




    }


    public String addFromUserInput(String table, ArrayList<String> input) {
        if (!Arrays.asList(getAllTableNames()).contains(table)) 
            throw new RuntimeException("No table of name " + table + " exist");
        try {
            int key = input.get(0) == null
                ? 0
                : Integer.parseInt(input.get(0));
            switch(table) {
                case "Parkings":
                    getParkingDao().addParking(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    break;
                case "Guards":
                    getGuardDao().addGuard(Integer.parseInt(input.get(1)), input.get(2), input.get(3));
                    break;
                case "Tickets":
                    getTicketDao().addTicket(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), input.get(3), false);
                    break;
                case "Meters":
                    getMeterDao().addMeter(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)), Integer.parseInt(input.get(5))  );
                    break;
                case "Transactions":
                    register_transaction(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)) );
                    //getTransactionDao().addTransaction(Integer.parseInt(input.get(1)), input.get(2), input.get(3), Integer.parseInt(input.get(4)));
                    break;
                case "Parkings_Guards":
                    getParkingGuardDao().addParkingGuard(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    break;
    	        default:
    	        	throw new RuntimeException("Option not present for adding");
            }
        } catch (Exception e) {
            return e.getClass() + " " + e.getMessage();
        }
        return null;
    }

    public String deleteFromUserInput(String table, String key) {
        if (!Arrays.asList(getAllTableNames()).contains(table)) 
            throw new RuntimeException("No table of name " + table + " exist");
        try {
            switch(table) {
                case "Parkings":
                    //TODO
                    //int deleteress_key = getdeleteressDao().deletedeleteress(
                    getParkingDao().deleteParking(Integer.parseInt(key));
                    break;
                case "Guards":
                    getGuardDao().deleteGuard(Integer.parseInt(key));
                    break;
                case "Tickets":
                    getTicketDao().deleteTicket(Integer.parseInt(key));
                    break;
                case "Meters":
                    getMeterDao().deleteMeter(Integer.parseInt(key));
                    break;
                case "Transactions":
                    //TODO:
                    getTransactionDao().deleteTransaction(Integer.parseInt(key));
                    break;
                case "Parkings_Guards":
                    getParkingGuardDao().deleteParkingGuard(Integer.parseInt(key));
                    break;
    	        default:
    	        	throw new RuntimeException("Option not present for deleteing");
            }
        } catch (Exception e) {
            return e.getClass() + " " + e.getMessage();
        }
        return null;
    }

    private void handle_exception(Exception e){
        System.err.println("DataBaseModel: "+ e.getClass().getName() + ": " + e.getMessage());
    }

    public void close_connection(){
        connector.closeDB();
    }

}


