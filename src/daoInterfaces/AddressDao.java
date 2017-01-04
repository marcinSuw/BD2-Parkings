package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 04.01.17.
 */
public interface AddressDao  {
    public ResultSet getAddresses();
    public void updateAddress(int id_address, int id_parking, String postal_code, String street_name, int street_number);
    public void deleteAddress(int id_address);
    public void addAddress(int id_parking, String postal_code, String street_name, int street_number);
}
