package daoInterfacesImpl;

import daoInterfaces.TicketDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by szwarc on 05.01.17.
 */
public class TicketDaoImpl extends DaoUtilities implements TicketDao {
    Connection connection;

    public TicketDaoImpl(Connection connection){ this.connection = connection; }

    @Override
    public ResultSet getTickets() { return this.get_objects(connection, "Tickets"); }

    @Override
    public void updateTicket() {
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
