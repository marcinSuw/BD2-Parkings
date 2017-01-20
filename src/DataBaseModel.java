import daoInterfaces.*;
import daoInterfacesImpl.*;
import objects.Guard;
import objects.Meter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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
        return new String[]{"Parkings", "Addresses", "Guards", "Parkings_Guards", "Tickets", "Meters", "Transactions","Raport1","Raport2","Raport3"};
    }

    private void register_transaction(int id_meter, int duration){
        ArrayList<String> dates = compute_time(duration);
        Meter meter = getMeterDao().get_meter(id_meter);
        int cost = duration* getParkingDao().get_parking_cost(meter.getId_parking());
        getMeterDao().updateMeters(id_meter, meter.getId_parking(), meter.getMoneyAmount() + cost,
                meter.getMoneyCapcity(), meter.getPaperAmount() - 1, meter.getPaperCapcity() );
        getTransactionDao().addTransaction(id_meter, dates.get(0), dates.get(1), cost);
    }


    public String addFromUserInput(String table, ArrayList<String> input) {
        if (!Arrays.asList(getAllTableNames()).contains(table)) 
            throw new RuntimeException("No table of name " + table + " exist");
        try {
            if(input.get(0) != null) 
                return updateFromUserInput(table, input);
            
            switch(table) {
                case "Parkings":
                    getParkingDao().addParking(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    break;
                case "Addresses":
                    getAddressDao().addAddress(input.get(1), input.get(2), Integer.parseInt(input.get(3)));
                    break;
                case "Guards":
                    getGuardDao().addGuard(Integer.parseInt(input.get(1)), input.get(2), input.get(3));
                    break;
                case "Meters":
                    getMeterDao().addMeter(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)), Integer.parseInt(input.get(5))  );
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

    public String updateFromUserInput(String table, ArrayList<String> input) {
        try {
            switch(table) {
                case "Parkings":
                    getParkingDao().updateParking(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)));
                    break;
                case "Addresses":
                    getAddressDao().updateAddress(Integer.parseInt(input.get(1)), input.get(2), input.get(3), Integer.parseInt(input.get(4)));
                    break;
                case "Guards":
                    getGuardDao().updateGuard(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)), input.get(3), input.get(4));
                    break;
                case "Meters":
                    getMeterDao().updateMeters(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)), Integer.parseInt(input.get(5)), Integer.parseInt(input.get(6)));
                    break;
                case "Parkings_Guards":
                    getParkingGuardDao().updateParkingGuard(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(4)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)));
                    break;
    	        default:
    	        	throw new RuntimeException("Option not present for updating");
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
                case "Addresses":
                    getAddressDao().deleteAddress(Integer.parseInt(key));
                case "Guards":
                    getGuardDao().deleteGuard(Integer.parseInt(key));
                    break;
                case "Tickets": //TO BE REMOVED
                    getTicketDao().deleteTicket(Integer.parseInt(key));
                    break;
                case "Meters":
                    getMeterDao().deleteMeter(Integer.parseInt(key));
                    break;
                case "Transactions": //TO BE REMOVED
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

    public ArrayList<String> compute_time(int duration){
        ArrayList<String> dates = new ArrayList<String>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start_data = new Date();
        dates.add(df.format(start_data));
        String end_dates = null;
        try {
            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(start_data); // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, duration); // adds one hour
            Date end_date = cal.getTime();
            dates.add(df.format(end_date));
        }
        catch(Exception e){
            throw new RuntimeException("DataBaseModel: compute_time");
        }
        return dates;
    }

    public void close_connection(){
        connector.closeDB();
    }

    public int calculateMoneyFor(Meter meter, int duration) {
        return duration* getParkingDao().get_parking_cost(meter.getId_parking());
    }
}


