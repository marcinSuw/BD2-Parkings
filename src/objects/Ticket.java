package objects;

/**
 * Created by szwarc on 05.01.17.
 */
public class Ticket {
    private int Id_Ticket;
    private int pesel;
    private int Id_Parking;
    private int charge;
    private String registrationNUmber;
    private boolean paid;

    public Ticket(int id_Ticket, int pesel, int id_Parking, int charge, String registrationNUmber, boolean paid) {
        Id_Ticket = id_Ticket;
        this.pesel = pesel;
        Id_Parking = id_Parking;
        this.charge = charge;
        this.registrationNUmber = registrationNUmber;
        this.paid = paid;
    }
}
