package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 04.01.17.
 */
public interface AddressDao  {
    public ResultSet getAddresses();
    public void updateAddress(int id_address, String postal_code, String street_name, int street_number);
    public void deleteAddress(int id_address);
    public void addAddress( String postal_code, String street_name, int street_number);
}
