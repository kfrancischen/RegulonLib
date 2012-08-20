package USTC_Clotho_App;
import java.awt.*;
import javax.swing.*;
import java.util.Vector;

public class Search_Database extends JFrame{
	public static JTable regulator;
	public static JScrollPane regulatorScrollPane;
	public static JTable regulatee;
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
	
	/*public Search_Database(int numOfRegulator,
						int numOfRegulatee,
						Vector<String>regulatorNames,
						Vector<String>regulateeNames,
						int[][] database)*/
	public Search_Database(int numOfRegulator,int numOfRegulatee)
	{
		regulator = new JTable(numOfRegulator,1);
		regulatorScrollPane = new JScrollPane(regulator);
		regulatorScrollPane.setBounds(20, 20, 40, 40);
		regulatee = new JTable(numOfRegulatee,1);
		regulateeScrollPane = new JScrollPane(regulatee);
		
		regulateeCandidates = new JTable(20,2);
		regulateeCanScrollPane = new JScrollPane(regulateeCandidates);
		regulatorCandidates = new JTable(20,2);
		regulatorCanScrollPane = new JScrollPane(regulatorCandidates);
		
		regulatorName = new JTextField(null,10);
		regulateeName = new JTextField(null,10);
		
		searchBt = new JButton("Search!");
		searchBt.setBounds(new Rectangle(15, 120, 80, 36));
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
	public static void main(String args[]){
		Search_Database aTest = new Search_Database(100,100);
	}

}