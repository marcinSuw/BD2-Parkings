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
        DataBaseView data_view = new DataBaseView();
        data_model.insert_parking(1,8);
        /*data_model.insert_address(1,"12-123", "Polna", 12);
        data_model.insert_guard(12345,"Marcin", "Suwala");
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

        data_model.insert_ticket(12345, 1, 50, "123wr", false);
        data_model.insert_transaction(1, start_dates, end_dates, 30);
        System.out.println("Finished");*/
    }

}
