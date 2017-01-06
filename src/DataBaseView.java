import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import daoInterfacesImpl.*;

/**
 * Created by szwarc on 02.01.17.
 */
public class DataBaseView {

    public DataBaseView(DataBaseModel model){
        this.model = model;
        prepareGUI();
    }

    private DataBaseModel model;

    private JFrame mainFrame;
    private JTable mainTable;
    private JScrollPane mainPane;
    private JList<String> tableList;
	private JScrollPane listScroller;

    private void prepareGUI(){
        mainFrame = new JFrame();
        mainTable = new JTable();
        mainPane = new JScrollPane(mainTable);

        mainFrame.add(mainPane, BorderLayout.CENTER);

        mainFrame.setSize(400,400);
        mainFrame.setTitle("DataBase");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);
        mainPane.setVisible(true);

        tableList = new JList<String>(model.getAllTableNames());
        tableList.setVisibleRowCount(model.getAllTableNames().length);
        tableList.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	if (!event.getValueIsAdjusting()) {
            		current_row = current_row > event.getFirstIndex()
            				? event.getFirstIndex()
            				: event.getLastIndex();
            		setMainTable(new DaoUtilities().get_objects(model.getConnector().getConnection(), model.getAllTableNames()[current_row]));
            	}
            }
          	private int current_row;
        });

        listScroller = new JScrollPane(tableList);
        listScroller.setVisible(true);
        mainFrame.add(tableList, BorderLayout.WEST);
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
