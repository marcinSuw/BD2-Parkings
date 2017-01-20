package daoInterfacesImpl;

import daoInterfaces.TicketDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import objects.Ticket;

/**
 * Created by szwarc on 05.01.17.
 */
public class TicketDaoImpl extends DaoUtilities implements TicketDao {
    Connection connection;

    public TicketDaoImpl(Connection connection){ this.connection = connection; }

    @Override
    public ResultSet getTickets() { return this.get_objects(connection, "Tickets"); }

    @Override
    public Ticket getTicket(int id_ticket) {
        String sql = "SELECT  pesel, Id_Parking, charge, registrationNumber, paid FROM \"Tickets\" WHERE Id_Ticket = ? ;";

        int pesel = 0;
        int id_parking=0;
        int charge = 0;
        String reg_number = "";
        boolean paid = false;


        try{
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_ticket);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                pesel = rs.getInt("pesel");
                id_parking= rs.getInt("Id_Parking");
                charge = rs.getInt("charge");
                reg_number= rs.getString("registrationNumber" );
                paid = rs.getBoolean("paid");
            }
        }
        catch(Exception e){
            throw new RuntimeException("MeterDaoImpl: get_meter");
        }

        return new Ticket(id_ticket, pesel, id_parking, charge, reg_number, paid);
    }

    @Override
    public void updateTicket(int id_ticket, int pesel, int id_parking, int charge, String registrationNumber, boolean paid) {
        String sql = "UPDATE Tickets SET pesel = ?, Id_Parking= ?, charge = ?, registrationNumber = ?, paid = ? WHERE Id_Ticket= ?;";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pesel);
            stmt.setInt(2, id_parking);
            stmt.setInt(3, charge);
            stmt.setString(4, registrationNumber);
            stmt.setBoolean(5, paid);
            stmt.setInt(6, id_ticket);
            stmt.executeUpdate();
        }
        catch(Exception e){ throw new RuntimeException("GuardDaoImpl: updateGuard"); }
    }

    @Override
    public void deleteTicket(int id_ticket) { this.delete_object(connection, "Tickets", "Id_Ticket", id_ticket);}

    @Override
    public void addTicket(int pesel, int id_parking, int charge, String registrationNumber, boolean paid) {
        String sql = "INSERT INTO \"Tickets\" VALUES(NULL, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, pesel);
            prep.setInt(2, id_parking);
            prep.setInt(3, charge);
            prep.setString(4, registrationNumber);
            prep.setBoolean(5, paid);
            prep.executeUpdate();
        }
        catch (Exception e){    handle_exc(e, "addTicket");    }

    }

    private void handle_exc(Exception e, String name_function){
        throw new RuntimeException("TicketDaoImpl: " +name_function + e.getClass().getName() + ": " + e.getMessage());
    }
}
