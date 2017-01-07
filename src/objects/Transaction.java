package objects;

import java.util.Date;

/**
 * Created by szwarc on 07.01.17.
 */
public class Transaction {
    private int id_transaction;
    private int id_parking;
    private Date startData;
    private Date endData;
    private int cost;

    public Transaction(int id_transaction, int id_parking, Date startData, Date endData, int cost) {
        this.id_transaction = id_transaction;
        this.id_parking = id_parking;
        this.startData = startData;
        this.endData = endData;
        this.cost = cost;
    }
}
