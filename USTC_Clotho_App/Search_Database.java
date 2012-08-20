package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.Vector;

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
	@SuppressWarnings("serial")
	public void initComponents(){
		regulatorTable = new JTable(numOfRegulator,1){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulatorScrollPane = new JScrollPane(regulatorTable);

		regulateeTable = new JTable(numOfRegulatee,1){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulateeScrollPane = new JScrollPane(regulateeTable);
		
		regulateeCandidates = new JTable(15,2){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulateeCanScrollPane = new JScrollPane(regulateeCandidates);
		regulatorCandidates = new JTable(15,2){
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
			@SuppressWarnings("serial")
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
				if(allRegulateeCanRow > 15)
					regulateeCandidates = new JTable(allRegulateeCanRow,2){
					public boolean isCellEditable(int row, int column) { return false; }
				};
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
			
		}
		);
		
		//initiate regulator candidates table events
		regulatorCandidates.addMouseListener(new MouseAdapter(){
			
		}
		);
		
		//initiate regulatee candidates table events
		regulateeCandidates.addMouseListener(new MouseAdapter(){
			
		}
		);
	}

}