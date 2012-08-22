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
	
	private static JLabel regulatorNameLable;
	private static JLabel regulateeNameLabel;
	private static JLabel pictureLabel;
	
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
		//initiate button events
		initButtonEvents();
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
	
////////////////////////////////////////////////////////////////////////////////	
	/*-----triggering functions: for regulator table------*/
	public static void triggerRegulatorEvents(int RowPoint){
		int numRow = regulateeCandidates.getRowCount();
		regulatorName.setText(null);
		
		for(int i = 0;i < numRow;i++){
			regulateeCandidates.setValueAt(null, i, 0);
			regulateeCandidates.setValueAt(null, i, 1);
		}
		DefaultTableModel refreshModel = new DefaultTableModel(20,2);
		regulateeCandidates.setModel(refreshModel);
		
		int rowPoint = RowPoint;
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
	
	/*------triggering functions: for regulatee table ----*/
	public static void triggerRegulateeEvents(int RowPoint){
		int numRow = regulatorCandidates.getRowCount();
		for(int i = 0;i < numRow;i++){
			regulatorCandidates.setValueAt(null, i, 0);
			regulatorCandidates.setValueAt(null, i, 1);
		}
		DefaultTableModel refreshModel = new DefaultTableModel(20,2);
		regulatorCandidates.setModel(refreshModel);
		regulateeName.setText(null);
		
		int rowPoint = RowPoint;
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
	
	
	/*-----triggering function: regulator candidates table-------*/
	
	public static void triggerRegulatorCanEvents(int RowPoint){
		int rowPoint = RowPoint;
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
	
	/*------triggering function: regulatee candidates table------*/
	public static void triggerRegulateeCanEvents(int RowPoint){
		int rowPoint = RowPoint;
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
	
	/*------triggering functions: search Button------------*/
	public static void triggerSearchBt(){
		String regulator = regulatorName.getText();
		String regulatee = regulateeName.getText();
		if(regulator == null){
			JOptionPane.showMessageDialog(null,"Please Enter Regulator Name!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(regulatee == null){
			JOptionPane.showMessageDialog(null,"Please Enter Regulatee Name!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		int regulatorPosition = 0;
		Boolean haveThisRegulator = false;
		int regulateePosition = 0;
		Boolean haveThisRegulatee = false;
		for(int i = 0;i < numOfRegulator;i++){
			if(regulator.equals(regulatorNames.get(i))){
				regulatorPosition = i;
				haveThisRegulator = true;
				break;
			}
		}
		for(int i = 0;i < numOfRegulatee;i++){
			if(regulatee.equals(regulateeNames.get(i))){
				regulateePosition = i;
				haveThisRegulatee = true;
				break;
			}
		}
		
		if(haveThisRegulator == false || haveThisRegulatee == false){
			JOptionPane.showMessageDialog(null,"No Search Result In This Database!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int temp = database[regulatorPosition][regulateePosition];
		switch(temp){
		case 1:
			JOptionPane.showMessageDialog(null,"Positive Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			break;
		case -1:
			JOptionPane.showMessageDialog(null,"Negative Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(null,"Positive&Negative Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			break;
		case -2:
			JOptionPane.showMessageDialog(null,"Unknown Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(null,"No Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			break;
		}
		
	}
////////////////////////////////////////////////////////////////////////////////////////////	
	/*------method to initiate all the table events----*/
	public void initTableEvents(){
		
	/*--------initializing regulator table events---------------*/	
		//1、initiate regulator table mouse events
		regulatorTable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerRegulatorEvents(regulatorTable.getSelectedRow());
			}
		});
		
		//2、initiate regulator table key events
		regulatorTable.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN)
					return;
				int rowPoint = regulatorTable.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulatorEvents(regulatorTable.getSelectedRow() - 1);
				}
				else if(I.getKeyCode() == KeyEvent.VK_DOWN && rowPoint < numOfRegulator - 1){
					triggerRegulatorEvents(regulatorTable.getSelectedRow() + 1);
				}
			}
		});
		
	/*--------initializing regulatee table events---------------*/	
		//1、initiate regulatee mouse table events
		regulateeTable.addMouseListener(new MouseAdapter(){
			//public void valueChanged(ListSelectionEvent e){
			public void mousePressed(MouseEvent e){
				triggerRegulateeEvents(regulateeTable.getSelectedRow());
			}
		}
		);
		
		
		//2、initiate regulatee table key events
		regulateeTable.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN)
					return;
				int rowPoint = regulateeTable.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulateeEvents(rowPoint - 1);
				}
				else if(I.getKeyCode() == KeyEvent.VK_DOWN && rowPoint < numOfRegulatee - 1){
					triggerRegulateeEvents(rowPoint + 1);
				}
			}
		});
		
		
	/*-----------initiating regulator candidates table events---------*/
		//1、initiate regulator candidates table mouse events
		regulatorCandidates.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerRegulatorCanEvents(regulatorCandidates.getSelectedRow());
			}
		}
		);
		//2、initiate regulator candidates table key events
		regulatorCandidates.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN)
					return;
				int rowPoint = regulatorCandidates.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulatorCanEvents(rowPoint - 1);
				}
				else if(I.getKeyCode() == KeyEvent.VK_DOWN && rowPoint < regulatorCandidates.getRowCount() - 1){
					if(regulatorCandidates.getValueAt(rowPoint+1, 0)==null){
						regulatorName.setText(null);
						DefaultTableModel refreshModel = new DefaultTableModel(20,2);
						regulateeCandidates.setModel(refreshModel);
						return;
					}		
					triggerRegulatorCanEvents(rowPoint + 1);
				}
			}
		}
		);
		
	/*-------------initiating regulatee candidates table events----------*/	
		//1、initiate regulatee candidates table mouse events
		regulateeCandidates.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerRegulateeCanEvents(regulateeCandidates.getSelectedRow());
			}
		}
		);
		//2、initiate regulatee candidates table key events
		regulateeCandidates.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN)
					return;
				int rowPoint = regulateeCandidates.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulateeCanEvents(rowPoint - 1);
				}
				else if(I.getKeyCode() == KeyEvent.VK_DOWN && rowPoint < regulateeCandidates.getRowCount() - 1){
					if(regulateeCandidates.getValueAt(rowPoint+1, 0)==null){
						regulateeName.setText(null);
						DefaultTableModel refreshModel = new DefaultTableModel(20,2);
						regulatorCandidates.setModel(refreshModel);
						return;
					}
					triggerRegulateeCanEvents(rowPoint + 1);
				}
			}
		}
		);
		//end of the function
		
		
	}
	
	public void initButtonEvents(){
		searchBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerSearchBt();
			}
		}
		);
		
		searchBt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_ENTER)
					return;
				triggerSearchBt();
			}
		}
		);
	}

}