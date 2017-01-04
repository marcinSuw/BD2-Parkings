import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * Created by szwarc on 02.01.17.
 */
public class DataBaseView {

    public DataBaseView(){
        prepareGUI();
    }

    private JFrame mainFrame;
    private JTable mainTable;
    private JScrollPane mainPane;

    private void prepareGUI(){
        mainFrame = new JFrame();
        mainTable = new JTable();
        mainPane = new JScrollPane(mainTable);

        mainFrame.add(mainPane);


        mainFrame.setSize(400,400);
        mainFrame.setTitle("DataBase");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);
        mainPane.setVisible(true);

        //mainFrame.setLayout(new GridLayout(3, 1));

    }


    public void setMainTable(ResultSet data){
        mainTable.setModel(buildTableModel(data));
        //mainTable.setVisible(true);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) {

        ResultSetMetaData metaData = null;
        Vector<Vector<Object>> data = null;
        Vector<String> columnNames = null;

        try {
            metaData = rs.getMetaData();

            // names of columns
            if (metaData != null) {
                columnNames = new Vector<String>();
                int columnCount = metaData.getColumnCount();
                for (int column = 1; column <= columnCount; column++) {
                    columnNames.add(metaData.getColumnName(column));
                }
                // data of the table
                data = new Vector<Vector<Object>>();
                while (rs.next()) {
                    Vector<Object> vector = new Vector<Object>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        vector.add(rs.getObject(columnIndex));
                    }
                    data.add(vector);
                }
            }
        }
        catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        return new DefaultTableModel(data, columnNames);
    }
}
