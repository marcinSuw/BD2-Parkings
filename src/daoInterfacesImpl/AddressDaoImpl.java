package daoInterfacesImpl;

import daoInterfaces.AddressDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by szwarc on 04.01.17.
 */
public class AddressDaoImpl extends DaoUtilities implements AddressDao {

    private Connection connection;

    public AddressDaoImpl(Connection connection) { this.connection = connection; }

    @Override
    public ResultSet getAddresses() {
        return this.get_objects(connection, "Addresses");
    }

    @Override
    public void updateAddress(int id_address, int id_parking, String postal_code, String street_name, int street_number) {

        String sql = "UPDATE Addresses SET Id_Parking = ?, postalCode = ?, streetName = ?, streetNumber = ? WHERE Id_Address = ?;";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_parking);
            prep.setString(2, postal_code);
            prep.setString(3, street_name);
            prep.setInt(4, street_number);
            prep.setInt(5, id_address);
            prep.executeUpdate();
        }
        catch(Exception e){
            handle_exc(e, "updateAddress: ");
        }

    }

    @Override
    public void deleteAddress(int id_address) {
        this.delete_object(connection, "Addresses", "Id_Address", id_address);
    }

    @Override
    public void addAddress(int id_parking, String postal_code, String street_name, int street_number) {
        String sql = "INSERT INTO \"Addresses\" VALUES(NULL,?, ?, ?, ?);";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id_parking);
            prep.setString(2, postal_code);
            prep.setString(3, street_name);
            prep.setInt(4, street_number);
            prep.executeUpdate();
        }
        catch(Exception e){ handle_exc(e, "addAddress: ");}
    }

    private void handle_exc(Exception e, String name_function){
        System.err.println("AddressDaoImpl: " +name_function + e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
}
