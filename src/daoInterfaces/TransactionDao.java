package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 07.01.17.
 */
public interface TransactionDao {
    public ResultSet getTransactions();
    public void updateTransaction();
    public void deleteTransaction(int id_transaction);
    public void addTransaction( int id_meter, String startData, String endData, int cost);
}
