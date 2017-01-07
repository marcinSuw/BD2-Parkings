/**
 * Created by szwarc on 01.01.17.
 */
import objects.Parking;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main( String args[] ){
        DataBaseModel data_model = new DataBaseModel();
        DataBaseView data_view = new DataBaseView(data_model);

        //data_view.setMainTable(data_model.getParkingDao().getParkings());

        // Parking test
        /*data_model.getParkingDao().addParking(1,10);
        data_model.getParkingDao().updateParking(2,20, 1);
        data_view.setMainTable(data_model.getParkingDao().getParkings());
        data_model.getParkingDao().deleteParking(2);*/

        //Address test
        /*data_model.getAddressDao().addAddress(2,"12-123", "Polna", 12);
        data_model.getAddressDao().updateAddress(2,3,"12-134", "Zlota", 12);
        data_view.setMainTable(data_model.getAddressDao().getAddresses());
        data_model.getAddressDao().deleteAddress(2);*/

        //Guard test
        /*data_model.getGuardDao().addGuard(12345, "Marcin", "Suwala");
        data_model.getGuardDao().updateGuard(12345, "Marcin", "Zakala");
        data_view.setMainTable(data_model.getGuardDao().getGuards());
        data_model.getGuardDao().deleteGuard(12345);*/

        // Parking_Guard test
        /*data_model.getParkingGuardDao().addParkingGuard(1, 12345);
        data_model.getParkingGuardDao().updateParkingGuard(1, 12344);
        data_view.setMainTable(data_model.getParkingGuardDao().getParkingsGuards());
        data_model.getParkingGuardDao().deleteParkingGuard(1);*/

        // Ticket test
        /*data_model.getTicketDao().addTicket(12345, 1, 50 , "Wr123", false );
        data_view.setMainTable(data_model.getTicketDao().getTickets());
        data_model.getTicketDao().deleteTicket(1);*/


        /*data_model.insert_guard(12345,"Marcin", "Suwala");
        data_model.insert_parking_guards(12345, 1);
        String start_dates = "2017-01-03 14:35";
        String end_dates = null;
        try {


            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date date = df.parse(start_dates);

            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(date); // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
            Date end_date = cal.getTime();
            end_dates = df.format(end_date);
        }
        catch(Exception e){

        }
        data_model.insert_transaction(1, start_dates, end_dates, 30);
        System.out.println("Finished");*/
        //data_model.close_connection();
    }

}
