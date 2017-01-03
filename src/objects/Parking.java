package objects;

/**
 * Created by szwarc on 03.01.17.
 */
public class Parking {

    private int id_parking;
    private int cost_per_hour;

    public Parking(int id_parking, int cost_per_hour) {
        this.id_parking = id_parking;
        this. cost_per_hour = cost_per_hour;
    }

    public void setCost_per_hour(int cost_per_hour) {
        this.cost_per_hour = cost_per_hour;
    }

    public void setId_parking(int id_parking) {
        this.id_parking = id_parking;
    }

    public int getCost_per_hour() {
        return cost_per_hour;
    }

    public int getId_parking() {
        return id_parking;
    }
}
