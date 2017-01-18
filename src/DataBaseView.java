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
import java.util.ArrayList;
import java.util.Arrays;
import daoInterfacesImpl.*;
import javax.sql.*;
import java.sql.Statement;

/**
 * Created by szwarc on 02.01.17.
 */

public class DataBaseView {

	public DataBaseView(DataBaseModel model){
		this.model = model;
		prepareGUI();
	}
	//private Statement stmt;
	private DataBaseModel model;

	private JFrame mainFrame;
	private JTable mainTable;
	private JScrollPane mainPane;
	private JList<String> tableList;
	private JButton addElementButton;
	private JButton removeElementButton;
	private JButton updateElementButton;

	private void prepareGUI(){
		prepareMainFrame();
		prepareTableList();
		prepareButtons();
		mainFrame.setVisible(true);
	}

	private void prepareMainFrame() {
		mainFrame = new JFrame();
		mainTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		mainTable.setRowSelectionAllowed(false);
		mainPane = new JScrollPane(mainTable);

		mainFrame.add(mainPane, BorderLayout.CENTER);

		mainFrame.setSize(400,400);
		mainFrame.setTitle("DataBase");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		mainPane.setVisible(true);
	}


	private ResultSet createSQLQuery(int row) {
		String SQLQuery=null;
		Statement stmt;
		switch(row) {
			case 7:
				// transakcje na parkomat
				SQLQuery="SELECT ID_METER, SUM(cost), substr(startData, 0, 8 ) as date FROM TRANSACTIONS GROUP BY ID_METER, date;";
				break;
			case 8:
				// transakcje na parking
				SQLQuery="SELECT ID_PARKING, SUM(cost) as cost FROM ((SELECT ID_METER, SUM(cost) as cost FROM TRANSACTIONS GROUP BY ID_METER) NATURAL JOIN  (SELECT ID_METER, ID_PARKING FROM METERS)) GROUP BY ID_PARKING ;";
				break;
			case 9:
				// mandaty na parking
				SQLQuery="SELECT ID_PARKING, PAID, COUNT(*) as amount, SUM(charge) as cost FROM TICKETS GROUP BY ID_PARKING, PAID ;";			
				break;
		}
		ResultSet rs=null;
		try{
			stmt = model.getConnector().getConnection().createStatement();
			rs = stmt.executeQuery(SQLQuery); 
		}
		catch(Exception e){

		}
		
		return rs;
	}



	private void prepareTableList() {
		tableList = new JList<String>(model.getAllTableNames());
		tableList.setVisibleRowCount(model.getAllTableNames().length);
		tableList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					current_row = current_row > event.getFirstIndex()
						? event.getFirstIndex()
						: event.getLastIndex();
					String table_name = model.getAllTableNames()[current_row];
                    ArrayList<String> modifiable = new ArrayList<String>(Arrays.asList(new String[] {"Addresses", "Guards", "Parkings", "Meters", "Parkings_Guards"}));
                    if(modifiable.contains(table_name)) {
                        addElementButton.setEnabled(true);
                        removeElementButton.setEnabled(true);
                        updateElementButton.setEnabled(true);
                    } else {
                        addElementButton.setEnabled(false);
                        removeElementButton.setEnabled(true);
                        updateElementButton.setEnabled(false);
                    }
					//setMainTable(new DaoUtilities().get_objects(model.getConnector().getConnection(), model.getAllTableNames()[current_row]));
                    if(current_row>6){
                    	setEnabledCRUButtons(false);
                    	setMainTable(createSQLQuery(current_row));
                    }
                    else{
                    	setMainTable(new DaoUtilities().get_objects(model.getConnector().getConnection(), model.getAllTableNames()[current_row]));
                    }
				}
			}
			private int current_row;
		});

		mainFrame.add(tableList, BorderLayout.WEST);
		mainFrame.setVisible(true);
	}

    private void setEnabledCRUButtons(boolean enabled) {
        addElementButton.setEnabled(enabled);
        removeElementButton.setEnabled(enabled);
        updateElementButton.setEnabled(enabled);
    }

	private void prepareButtons() {
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));

		prepareAddElementButton(toolbar);
		prepareRemoveElementButton(toolbar);
		prepareUpdateElementButton(toolbar);

		mainFrame.add(toolbar, BorderLayout.EAST);
	}

	private void prepareAddElementButton(JToolBar toolbar) {
		addElementButton = new JButton("Dodaj rekord");
		addElementButton.setEnabled(false);
		addElementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addElementAction(new ArrayList<String>());
			}
		});
		toolbar.add(addElementButton);
	}

	private void addElementAction(ArrayList<String> key) {
		if(!tableList.isSelectionEmpty()) {
			JComponent[] components = getComponentsFor(tableList.getSelectedValue());
			int result = JOptionPane.showConfirmDialog(null, components, "Dodawanie rekordu:", JOptionPane.OK_CANCEL_OPTION);

			if(result == 0) {
				ArrayList<String> user_input = new ArrayList<String>(key);

				user_input.add(key.isEmpty()?null:key.get(0));
				for(int i = 1; i < components.length; i += 2)
					user_input.add(((JTextField)components[i]).getText());

				String answer = model.addFromUserInput(tableList.getSelectedValue(), user_input);
				if(answer != null)
					JOptionPane.showMessageDialog(null, new JLabel(answer), "Nie udalo sie dodac", JOptionPane.OK_OPTION);
				else
					setMainTable(new DaoUtilities().get_objects(model.getConnector().getConnection(), tableList.getSelectedValue()));
			}
		}
	}

	private void prepareRemoveElementButton(JToolBar toolbar) {
		removeElementButton = new JButton("Usun rekord");
		removeElementButton.setEnabled(false);
		removeElementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				removeElementAction();
			}
		});
		toolbar.add(removeElementButton);
	}

	private String removeElementAction() {
		if(!tableList.isSelectionEmpty()) {
			JComponent[] components = new JComponent[] {
				new JLabel("Klucz glowny"),
				new JTextField(),
			};
			int result = JOptionPane.showConfirmDialog(null, components, "Usuwanie tablicy:", JOptionPane.OK_CANCEL_OPTION);

			if(result == 0) {
				String user_input = ((JTextField)components[1]).getText();

				String answer = model.deleteFromUserInput(tableList.getSelectedValue(), user_input);
				if(answer != null)
					JOptionPane.showMessageDialog(null, new JLabel(answer), "Nie udalo sie usunac", JOptionPane.OK_OPTION);
				else
					setMainTable(new DaoUtilities().get_objects(model.getConnector().getConnection(), tableList.getSelectedValue()));

				return user_input;
			}
		}
		return null;
	}

	private void prepareUpdateElementButton(JToolBar toolbar) {
		updateElementButton = new JButton("Edytuj rekord");
		updateElementButton.setEnabled(false);
		updateElementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                JComponent[] update = null;
                if(tableList.getSelectedValue() != "Parkings_Guards")
                    update = new JComponent[] { new JLabel("klucz glowny"), new JTextField() };
                else
                    update = getComponentsFor("Parkings_Guards");
				int result = JOptionPane.showConfirmDialog(null, update, "Edycja elementu", JOptionPane.OK_CANCEL_OPTION);
                ArrayList<String> update_key = new ArrayList<String>();
                if (result == 0) 
                    for(int i = 1; i < update.length; i+=2)
                         update_key.add(((JTextField)update[i]).getText());
				if(update_key != null)
					addElementAction(update_key);
			}
		});
		toolbar.add(updateElementButton);
	}

	private JComponent[] getComponentsFor(String chosen_table) {
		switch(chosen_table) {
			case "Parkings":
				return new JComponent[] {
                    new JLabel("Id adresu"),
                    new JTextField(),
                    new JLabel("Koszt za godzine"),
                    new JTextField(),
				};
			case "Addresses":
				return new JComponent[] {
					new JLabel("Kod pocztowy"),
					new JTextField(),
					new JLabel("Nazwa ulicy"),
					new JTextField(),
					new JLabel("Numer ulicy"),
					new JTextField(),
				};
			case "Guards":
				return new JComponent[] {
					new JLabel("Pesel"),
					new JTextField(),
					new JLabel("Imie"),
					new JTextField(),
					new JLabel("Nazwisko"),
					new JTextField(),
				};
			case "Tickets":
				return new JComponent[] {
					new JLabel("Pesel"),
					new JTextField(),
					new JLabel("ID parkingu"),
					new JTextField(),
					new JLabel("Oplata"),
					new JTextField(),
					new JLabel("Numer Rejestracyjny"),
					new JTextField(),
				};
			case "Meters":
				return new JComponent[] {
                    new JLabel("Id parkingu"),
                    new JTextField(),
                    new JLabel("Ilość pieniedzy"),
                    new JTextField(),
                    new JLabel("Pojemnosc pieniedzy"),
                    new JTextField(),
                    new JLabel("Ilosc papieru"),
                    new JTextField(),
                    new JLabel("Pojemnosc papieru"),
                    new JTextField(),
				};
			case "Transactions":
				return new JComponent[] {
                    new JLabel("Id parkomatu"),
                    new JTextField(),
                    new JLabel("Liczba godzin"),
                    new JTextField(),
				};
			case "Parkings_Guards":
				return new JComponent[] {
					new JLabel("Id parkingu"),
					new JTextField(),
					new JLabel("Pesel"),
					new JTextField(),
				};
			default:
				throw new RuntimeException("Option not present for adding");
		}
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