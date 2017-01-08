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

    public int getId_meter() {
        return id_meter;
    }

    public void setId_meter(int id_meter) {
        this.id_meter = id_meter;
    }

    public int getId_parking() {
        return id_parking;
    }

    public void setId_parking(int id_parking) {
        this.id_parking = id_parking;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public int getMoneyCapcity() {
        return moneyCapcity;
    }

    public void setMoneyCapcity(int moneyCapcity) {
        this.moneyCapcity = moneyCapcity;
    }

    public int getPaperAmount() {
        return paperAmount;
    }

    public void setPaperAmount(int paperAmount) {
        this.paperAmount = paperAmount;
    }

    public int getPaperCapcity() {
        return paperCapcity;
    }

    public void setPaperCapcity(int paperCapcity) {
        this.paperCapcity = paperCapcity;
    }
}
