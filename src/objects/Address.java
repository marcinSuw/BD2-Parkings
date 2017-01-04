package objects;

/**
 * Created by szwarc on 04.01.17.
 */
public class Address {
    private int id_address;
    private  int id_parking;
    private String postal_code;
    private String street_name;
    private int street_number;

    public Address(int id_address, int id_parking, String postal_code, String street_name, int street_number) {
        this.id_address = id_address;
        this.id_parking = id_parking;
        this.postal_code = postal_code;
        this.street_name = street_name;
        this.street_number = street_number;
    }

}
