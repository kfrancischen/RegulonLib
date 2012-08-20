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
		regulatorScrollPane = new JScrollPane(regulatorTable);

		regulateeTable = new JTable(numOfRegulatee,1){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulateeScrollPane = new JScrollPane(regulateeTable);
		
		regulateeCandidates = new JTable(20,2){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulateeCanScrollPane = new JScrollPane(regulateeCandidates);
		regulatorCandidates = new JTable(20,2){
			public boolean isCellEditable(int row, int column) { return false; }
		};
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
			regulatorTable.setValueAt(regulatorNames.get(i), i,0);
		}
		for(int i = 0;i < numOfRegulatee; i++){
			regulateeTable.setValueAt(regulateeNames.get(i),i,0);
		}
	}
	
	
	/*------method to initiate all the table events----*/
	public void initTableEvents(){
		
		//initiate regulator table events
		regulatorTable.addMouseListener(new MouseAdapter(){
	
			public void mousePressed(MouseEvent e){
				int numRow = regulateeCandidates.getRowCount();
				for(int i = 0;i < numRow;i++){
					regulateeCandidates.setValueAt(null, i, 0);
					regulateeCandidates.setValueAt(null, i, 1);
				}
				int rowPoint = regulatorTable.rowAtPoint(e.getPoint());
				regulatorName.setText((String) regulatorTable.getValueAt(rowPoint,0));
				int allRegulateeCanRow = 0;
				for(int i = 0;i < numOfRegulatee; i++){
					int temp = database[i][rowPoint];
					if(temp == 1 || temp == -1 || temp == 2)
						allRegulateeCanRow++;
				}
				if(allRegulateeCanRow > 15){
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
		regulateeTable.addMouseListener(new MouseAdapter(){
		
			public void mousePressed(MouseEvent e){
				int numRow = regulatorCandidates.getRowCount();
				for(int i = 0;i < numRow;i++){
					regulatorCandidates.setValueAt(null, i, 0);
					regulatorCandidates.setValueAt(null, i, 1);
				}
				int rowPoint = regulateeTable.rowAtPoint(e.getPoint());
				regulateeName.setText((String) regulateeTable.getValueAt(rowPoint,0));
				int allRegulatorCanRow = 0;
				for(int i = 0;i < numOfRegulator; i++){
					int temp = database[rowPoint][i];
					if(temp == 1 || temp == -1 || temp == 2)
						allRegulatorCanRow++;
				}
				if(allRegulatorCanRow > 15){
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
		regulatorCandidates.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int numRow = regulateeCandidates.getRowCount();
				int numColumn = regulateeCandidates.getColumnCount();
				for(int i = 0;i < numRow;i++){
					for(int j = 0;j < numColumn;j++){
						regulateeCandidates.setValueAt(null, i, j);
					}
				}
				int rowPoint = regulatorCandidates.rowAtPoint(e.getPoint());
				if(regulatorCandidates.getValueAt(rowPoint, 0)==null)
					return;
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
				if(allRegulateeCanRow > 15){
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
		regulateeCandidates.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int numRow = regulatorCandidates.getRowCount();
				int numColumn = regulatorCandidates.getColumnCount();
				for(int i = 0;i < numRow; i++){
					for(int j =0 ;j < numColumn; j++){
						regulatorCandidates.setValueAt(null, i, j);
					}
				}
				int rowPoint = regulateeCandidates.rowAtPoint(e.getPoint());
				if(regulateeCandidates.getValueAt(rowPoint, 0)==null)
					return;
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
				if(allRegulatorCanRow > 15){
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