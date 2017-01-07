package objects;

/**
 * Created by szwarc on 07.01.17.
 */
public class Meter {
    private int id_meter;
    private int id_parking;
    private int moneyAmount;
    private int moneyCapcity;
    private int paperAmount;
    private int paperCapcity;

    public Meter(int id_meter, int id_parking, int moneyAmount, int moneyCapcity, int paperAmount, int paperCapcity) {
        this.id_meter = id_meter;
        this.id_parking = id_parking;
        this.moneyAmount = moneyAmount;
        this.moneyCapcity = moneyCapcity;
        this.paperAmount = paperAmount;
        this.paperCapcity = paperCapcity;
    }
}
