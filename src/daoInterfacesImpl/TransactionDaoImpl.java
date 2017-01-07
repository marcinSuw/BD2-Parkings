package daoInterfacesImpl;

import daoInterfaces.TransactionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by szwarc on 07.01.17.
 */
public class TransactionDaoImpl extends DaoUtilities implements TransactionDao {
    private Connection connection;

    public TransactionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet getTransactions() { return this.get_objects(connection, "Transactions");
    }
    @Override
    public void updateTransaction() {
    }

    @Override
    public void deleteTransaction(int id_transaction) {
        this.delete_object(connection, "Transactions", "Id_Transaction", id_transaction);
    }

    @Override
    public void addTransaction(int id_meter, String startData, String endData, int cost) {
        String sql = "INSERT INTO \"Transactions\" VALUES(NULL, ?, ?, ?, ?);";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_meter);
            prep.setString(2, startData);
            prep.setString(3, endData);
            prep.setInt(4, cost);
            prep.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException("TransactionDaoImpl: addTransaction");
        }
    }
}

