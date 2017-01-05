package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 05.01.17.
 */
public interface TicketDao {

    public ResultSet getTickets();
    public void updateTicket();
    public void deleteTicket(int id_ticket);
    public void addTicket(int pesel, int id_parking, int charge, String registrationNumber, boolean paid);
}
