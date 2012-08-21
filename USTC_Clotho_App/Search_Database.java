package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.util.Vector;

@SuppressWarnings("serial")
public class Search_Database extends JFrame{
	
	public static Vector<String> regulatorNames;
	public static Vector<String> regulateeNames;
	public static int numOfRegulator;
	public static int numOfRegulatee;
	public static int[][] database;
	
	public static JTable regulatorTable;
	public static JScrollPane regulatorScrollPane;
	public static JTable regulateeTable;
	public static JScrollPane regulateeScrollPane;
	
	public static JTable regulateeCandidates;
	public static JScrollPane regulateeCanScrollPane;
	public static JTable regulatorCandidates;
	public static JScrollPane regulatorCanScrollPane;
	
	public static JTextField regulatorName;
	public static JTextField regulateeName;
	
	//public static JButton searchBt;
	private static JFrame searchFrame;
	private static JPanel searchPanel;
	
	private static JButton searchBt;
	private Box boxOfRegulator;
	private Box boxOfRegulatee;
	
	
	/*------------constructor---------*/
	public Search_Database(int NumOfRegulator,
						int NumOfRegulatee,
						Vector<String>RegulatorNames,
						Vector<String>RegulateeNames,
						int[][] Database)

	{	
		numOfRegulator = NumOfRegulator;
		numOfRegulatee = NumOfRegulatee;
		regulatorNames = new Vector<String>();
		regulateeNames = new Vector<String>();
		database = new int[numOfRegulatee][numOfRegulator];
		for(int i = 0;i < numOfRegulator;i++){
			regulatorNames.add(RegulatorNames.get(i));
		}
		
		for(int i = 0; i < numOfRegulatee;i++){	
			regulateeNames.add(RegulateeNames.get(i));
		}
		
		for(int i = 0; i < numOfRegulatee;i++){
			for(int j = 0;j < numOfRegulator;j++){
				database[i][j] = Database[i][j];
			}
		}
		//initiate all the components
		initComponents();
		//initiate all the regulator and regulatee names
		initNames();
		//initiate all the table events
		initTableEvents();
	}
	
	
	/*---------------method to initiate all the components--*/

	public void initComponents(){
		regulatorTable = new JTable(numOfRegulator,1){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		Object[][] tableContents_1 = new Object[numOfRegulator][1]; 
		regulatorTable.setModel(new DefaultTableModel(
				tableContents_1,new String[]{"Regulator"}
				));
		regulatorScrollPane = new JScrollPane(regulatorTable);

		
		Object[][] tableContents_2 = new Object[numOfRegulatee][1];
		regulateeTable = new JTable(numOfRegulatee,1){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulateeTable.setModel(new DefaultTableModel(
				tableContents_2,new String[]{"Regulatee"}
				));
		regulateeScrollPane = new JScrollPane(regulateeTable);
		
		
		//Object[][] tableContents_3 = new Object[20][2];
		regulateeCandidates = new JTable(20,2){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		//regulateeCandidates.setModel(new DefaultTableModel(
				//tableContents_3,new String[]{"Regulatee Candidates","Regulation"}
				//));
		regulateeCanScrollPane = new JScrollPane(regulateeCandidates);
		
		//Object[][] tableContents_4 = new Object[20][2];
		regulatorCandidates = new JTable(20,2){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		//regulatorCandidates.setModel(new DefaultTableModel(
			//	tableContents_4,new String[]{"Regulator Candidates","Regulation"}
				//));
		regulatorCanScrollPane = new JScrollPane(regulatorCandidates);
		
		regulatorName = new JTextField(null,10);
		regulateeName = new JTextField(null,10);
		
		searchBt = new JButton("Search!");

		searchPanel = new JPanel();
		searchFrame = new JFrame("Search Page");
		
		boxOfRegulator = Box.createVerticalBox();
		boxOfRegulatee = Box.createVerticalBox();
		
		boxOfRegulator.add(regulatorName);
		boxOfRegulator.add(regulateeCanScrollPane);
		
		boxOfRegulatee.add(regulateeName);
		boxOfRegulatee.add(regulatorCanScrollPane);
		
		searchPanel.setLayout(new GridLayout(1,5));
		searchPanel.add(regulatorScrollPane);
		searchPanel.add(boxOfRegulator);
		searchPanel.add(searchBt);
		searchPanel.add(boxOfRegulatee);
		searchPanel.add(regulateeScrollPane);

		Container contentPane = searchFrame.getContentPane();
		contentPane.add(searchPanel);
		searchFrame.setSize(600,400);
		searchFrame.setVisible(true);
	}
	
	
	
	/*------method to initiate regulator names and regulatee names--*/
	public void initNames(){
		for(int i = 0;i < numOfRegulator; i++){
			regulatorTable.setValueAt(regulatorNames.get(i), i, 0);
		}
		for(int i = 0;i < numOfRegulatee; i++){
			regulateeTable.setValueAt(regulateeNames.get(i), i, 0);
		}
	}
	
	
	/*------method to initiate all the table events----*/
	public void initTableEvents(){
		
		//initiate regulator table events
		//ListSelectionModel modeForRegulatorTable = regulatorTable.getSelectionModel();
		//modeForRegulatorTable.addListSelectionListener(new ListSelectionListener(){
		regulatorTable.addMouseListener(new MouseAdapter(){
			//public void valueChanged(ListSelectionEvent e){
			public void mousePressed(MouseEvent e){
				int numRow = regulateeCandidates.getRowCount();
				regulatorName.setText(null);
				
				for(int i = 0;i < numRow;i++){
					regulateeCandidates.setValueAt(null, i, 0);
					regulateeCandidates.setValueAt(null, i, 1);
				}
				DefaultTableModel refreshModel = new DefaultTableModel(20,2);
				regulateeCandidates.setModel(refreshModel);
				
				int rowPoint = regulatorTable.getSelectedRow();
				regulatorName.setText((String) regulatorTable.getValueAt(rowPoint,0));
				int allRegulateeCanRow = 0;
				for(int i = 0;i < numOfRegulatee; i++){
					int temp = database[i][rowPoint];
					if(temp == 1 || temp == -1 || temp == 2)
						allRegulateeCanRow++;
				}
				if(allRegulateeCanRow > 20){
					DefaultTableModel aNewModel = new DefaultTableModel(allRegulateeCanRow,2);
					regulateeCandidates.setModel(aNewModel);
				}
				int newRegulateeCanRow = 0;
				for(int i = 0;i < numOfRegulatee; i++){
					int temp = database[i][rowPoint];
					switch(temp){
					case 1: 
						regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
						regulateeCandidates.setValueAt("+", newRegulateeCanRow, 1);
						newRegulateeCanRow ++;
						break;
					case -1:
						regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
						regulateeCandidates.setValueAt("-", newRegulateeCanRow, 1);
						newRegulateeCanRow ++;
						break;
					case 2:
						regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
						regulateeCandidates.setValueAt("+-", newRegulateeCanRow, 1);
						newRegulateeCanRow ++;
						break;
					default:
						break;
					}
				}
			}
		});
		
		
		//initiate regulatee table events
		//ListSelectionModel modeForRegulateeTable = regulateeTable.getSelectionModel();
		//modeForRegulateeTable.addListSelectionListener(new ListSelectionListener(){
		regulateeTable.addMouseListener(new MouseAdapter(){
			//public void valueChanged(ListSelectionEvent e){
			public void mousePressed(MouseEvent e){
				int numRow = regulatorCandidates.getRowCount();
				for(int i = 0;i < numRow;i++){
					regulatorCandidates.setValueAt(null, i, 0);
					regulatorCandidates.setValueAt(null, i, 1);
				}
				DefaultTableModel refreshModel = new DefaultTableModel(20,2);
				regulatorCandidates.setModel(refreshModel);
				regulateeName.setText(null);
				
				int rowPoint = regulateeTable.getSelectedRow();
				regulateeName.setText((String) regulateeTable.getValueAt(rowPoint,0));
				int allRegulatorCanRow = 0;
				for(int i = 0;i < numOfRegulator; i++){
					int temp = database[rowPoint][i];
					if(temp == 1 || temp == -1 || temp == 2)
						allRegulatorCanRow++;
				}
				if(allRegulatorCanRow > 20){
					DefaultTableModel aNewModel = new DefaultTableModel(allRegulatorCanRow,2);
					regulatorCandidates.setModel(aNewModel);
				};
				int newRegulatorCanRow = 0;
				for(int i = 0;i < numOfRegulator; i++){
					int temp = database[rowPoint][i];
					switch(temp){
					case 1: 
						regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
						regulatorCandidates.setValueAt("+", newRegulatorCanRow, 1);
						newRegulatorCanRow ++;
						break;
					case -1:
						regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
						regulatorCandidates.setValueAt("-", newRegulatorCanRow, 1);
						newRegulatorCanRow ++;
						break;
					case 2:
						regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
						regulatorCandidates.setValueAt("+-", newRegulatorCanRow, 1);
						newRegulatorCanRow ++;
						break;
					default:
						break;
					}
				}
			}
		}
		);
		
		//initiate regulator candidates table events
		//ListSelectionModel modeForRegulatorCanTable = regulatorCandidates.getSelectionModel();
		//regulatorCandidates.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//modeForRegulatorCanTable.addListSelectionListener(new ListSelectionListener(){
		regulatorCandidates.addMouseListener(new MouseAdapter(){
			//public void valueChanged(ListSelectionEvent e){
			public void mousePressed(MouseEvent e){
				int rowPoint = regulatorCandidates.getSelectedRow();
				if(regulatorCandidates.getValueAt(rowPoint, 0)==null)
					return;
				
				int numRow = regulateeCandidates.getRowCount();
				for(int i = 0;i < numRow;i++){
					regulateeCandidates.setValueAt(null, i, 0);
					regulateeCandidates.setValueAt(null, i ,1);
				}
				regulatorName.setText(null);
				DefaultTableModel refreshModel = new DefaultTableModel(20,2);
				regulateeCandidates.setModel(refreshModel);
				
				String selectedRegulator = (String) regulatorCandidates.getValueAt(rowPoint, 0);
				regulatorName.setText(selectedRegulator);
				int itsPosition = 0;
				for(int i = 0;i < numOfRegulator;i++){
					if(selectedRegulator == regulatorNames.get(i)){
						itsPosition = i;
						break;
					}
				}
				int allRegulateeCanRow = 0;
				for(int i = 0;i < numOfRegulatee; i++){
					int temp = database[i][itsPosition];
					if(temp == 1 || temp == -1 || temp == 2)
						allRegulateeCanRow++;
				}
				if(allRegulateeCanRow > 20){
					DefaultTableModel aNewModel = new DefaultTableModel(allRegulateeCanRow,2);
					regulateeCandidates.setModel(aNewModel);
				}
				int newRegulateeCanRow = 0;
				for(int i = 0;i < numOfRegulatee;i++){
					int temp = database[i][itsPosition];
					switch(temp){
					case 1: 
						regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
						regulateeCandidates.setValueAt("+", newRegulateeCanRow, 1);
						newRegulateeCanRow ++;
						break;
					case -1:
						regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
						regulateeCandidates.setValueAt("-", newRegulateeCanRow, 1);
						newRegulateeCanRow ++;
						break;
					case 2:
						regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
						regulateeCandidates.setValueAt("+-", newRegulateeCanRow, 1);
						newRegulateeCanRow ++;
						break;
					default:
						break;
					}
				}
			}
		}
		);
		
		//initiate regulatee candidates table events
		//ListSelectionModel modeForRegulateeCanTable = regulateeCandidates.getSelectionModel();
		//regulateeCandidates.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//modeForRegulateeCanTable.addListSelectionListener(new ListSelectionListener(){
		regulateeCandidates.addMouseListener(new MouseAdapter(){
		
			//public void valueChanged(ListSelectionEvent e){
			public void mousePressed(MouseEvent e){
				int rowPoint = regulateeCandidates.getSelectedRow();
				if(regulateeCandidates.getValueAt(rowPoint, 0)==null)
					return;
				
				int numRow = regulatorCandidates.getRowCount();
				regulateeName.setText(null);
				for(int i = 0;i < numRow; i++){
					regulatorCandidates.setValueAt(null, i, 0);
					regulatorCandidates.setValueAt(null, i, 1);
				}
				DefaultTableModel refreshModel = new DefaultTableModel(20,2);
				regulatorCandidates.setModel(refreshModel);
				
				String selectedRegulatee = (String) regulateeCandidates.getValueAt(rowPoint, 0);
				regulateeName.setText(selectedRegulatee);
				int itsPosition = 0;
				for(int i = 0;i < numOfRegulatee;i++){
					if(selectedRegulatee == regulateeNames.get(i)){
						itsPosition = i;
						break;
					}
				}
				int allRegulatorCanRow = 0;
				for(int i = 0;i < numOfRegulator; i++){
					int temp = database[itsPosition][i];
					if(temp == 1 || temp == -1 || temp == 2)
						allRegulatorCanRow++;
				}
				if(allRegulatorCanRow > 20){
					DefaultTableModel aNewModel = new DefaultTableModel(allRegulatorCanRow,2);
					regulatorCandidates.setModel(aNewModel);
				}
				int newRegulatorCanRow = 0;
				for(int i = 0;i < numOfRegulator;i++){
					int temp = database[itsPosition][i];
					switch(temp){
					case 1: 
						regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
						regulatorCandidates.setValueAt("+", newRegulatorCanRow, 1);
						newRegulatorCanRow ++;
						break;
					case -1:
						regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
						regulatorCandidates.setValueAt("-", newRegulatorCanRow, 1);
						newRegulatorCanRow ++;
						break;
					case 2:
						regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
						regulatorCandidates.setValueAt("+-", newRegulatorCanRow, 1);
						newRegulatorCanRow ++;
						break;
					default:
						break;
					}
				}
			}
		}
		);
		//end of the function
		
		
	}

}