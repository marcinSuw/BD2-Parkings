package objects;

/**
 * Created by szwarc on 03.01.17.
 */
public class Parking {

    private int id_parking;
    private int id_address;
    private int cost_per_hour;

    public Parking(int id_parking, int id_address, int cost_per_hour) {
        this.id_parking = id_parking;
        this.id_address = id_address;
        this. cost_per_hour = cost_per_hour;
    }

    public void setCost_per_hour(int cost_per_hour) {
        this.cost_per_hour = cost_per_hour;
    }
    public int getCost_per_hour() {
        return this.cost_per_hour;
    }

    public void setId_address(int id_address) {
        this.id_address = id_address;
    }
    public int getId_address(){ return this.id_address; }

    public int getId_parking() {
        return this.id_parking;
    }
}
