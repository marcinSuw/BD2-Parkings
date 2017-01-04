import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by szwarc on 03.01.17.
 */
public class TableView extends AbstractTableModel{

    ArrayList columnNames = new ArrayList();
    ArrayList data = new ArrayList();

    @Override

    public int getRowCount(){
        return data.size();
    }
    @Override
    public int getColumnCount(){
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int row, int column){
        Object object = new Object();
        return object;

    }



}
