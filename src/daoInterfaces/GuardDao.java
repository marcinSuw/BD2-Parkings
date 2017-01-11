package daoInterfaces;

import java.sql.ResultSet;

/**
 * Created by szwarc on 05.01.17.
 */
public interface GuardDao {

    public ResultSet getGuards();
    public void updateGuard(int old_pesel, int pesel, String name, String surname);
    public void deleteGuard(int pesel);
    public void addGuard(int pesel, String name, String surname);

}
