import java.awt.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("serial")
public class test extends JFrame{
	public static JTable regulatorTable =new JTable();
	public static JScrollPane regulatorScrollPane = new JScrollPane();
	public static JTable regulateeTable = new JTable();
	public static JScrollPane regulateeScrollPane = new JScrollPane();
	
	public static JTable regulateeCandidates = new JTable();
	public static JScrollPane regulateeCanScrollPane = new JScrollPane();
	public static JTable regulatorCandidates = new JTable();
	public static JScrollPane regulatorCanScrollPane = new JScrollPane();
	
	public static JTextField regulatorName = new JTextField();
	public static JTextField regulateeName = new JTextField();
	
	//public static JButton searchBt;
	private static JFrame searchFrame = new JFrame();
	//private static JPanel searchPanel = new JPanel();
	
	private static JButton searchBt = new JButton();
	//private Box boxOfRegulator;
	//private Box boxOfRegulatee;
	
	private static JLabel regulatorNameLabel = new JLabel();
	private static JLabel regulateeNameLabel = new JLabel();
	private static JLabel pictureLabel = new JLabel();
	public test(){
		initComponents();
	}
	public static void main(String[] args) {  
		new test().setVisible(true);
		
	}
	public void initComponents(){
		Object[][] object_1 = new Object[][]{
				{null},{null}
		};
		String[] String_1 = new String[]{
				"regulator"
		};
		regulatorTable.setModel(new DefaultTableModel(object_1,String_1));
		regulatorScrollPane.setViewportView(regulatorTable);
		regulatorTable.getColumnModel().getColumn(0).setWidth(30);
		Object[][] object_2 = new Object[][]{
				{null},{null}
		};
		String[] String_2 = new String[]{
				"regulatee"
		};
		regulateeTable.setModel(new DefaultTableModel(object_2,String_2));
		regulateeScrollPane.setViewportView(regulateeTable);
		regulateeTable.getColumnModel().getColumn(0).setWidth(30);
		regulateeTable.getColumnModel().getColumn(0).setResizable(false);
		
		Object[][] object_3 = new Object[][]{
				{null,null},
				{null,null}
		};
		String[] String_3 = new String[]{
				"Regulatee\nCandidates", "Regulation"
		};
		regulateeCandidates.setModel(new DefaultTableModel(object_3,String_3));
		regulateeCandidates.getColumnModel().getColumn(0).setWidth(50);
		regulateeCandidates.getColumnModel().getColumn(1).setWidth(40);
		regulateeCanScrollPane.setViewportView(regulateeCandidates);
		
		Object[][] object_4 = new Object[][]{
				{null,null},
				{null,null}
		};
		String[] String_4 = new String[]{
				"Regulatoe\nCandidates", "Regulation"
		};
		regulatorCandidates.setModel(new DefaultTableModel(object_4,String_4));
		regulatorCandidates.getColumnModel().getColumn(0).setWidth(50);
		regulatorCandidates.getColumnModel().getColumn(1).setWidth(40);
		regulatorCanScrollPane.setViewportView(regulatorCandidates);
		
		pictureLabel.setAlignmentY(0.0F);
		
	}
}

