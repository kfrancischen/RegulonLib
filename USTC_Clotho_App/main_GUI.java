
package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Vector;

public class main_GUI{

	public main_GUI(){
		initComponents();
		try {
			loadDataBaseOperon_Operon();
			loadDataBaseGene_Promoter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Unable To Load The Database","Error!",JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String args[]){

		new main_GUI();
		System.out.print("Enter the Matrix size:\n");
		
		commitSizeBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(sizeInput.getText().equals("")){
					System.out.print("Please Input The Matrix Size And Press Commit.\n");
					return;
				}
				matrixsize = Integer.parseInt(sizeInput.getText());
				System.out.print(matrixsize+"\n\n");
			}
		});
		 
		modeOneBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
	
				if(matrixsize == 0){
					System.out.println("Please Input Matrix Size First!\n");
					JOptionPane.showMessageDialog(null,"Please Input Matrix Size First!\n","Error!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				final int[][] TargetMatrix = new int[matrixsize][matrixsize];
				matrixBreakPoint:
				for(int i = 0; i < matrixsize;i++){
					for(int j = 0;j < matrixsize;j++){
						String element = (String)inputMatrixTable.getValueAt(i,j);
						if(element == "+"){
							TargetMatrix[i][j] = 1;
						}
						else if(element == "-"){
							TargetMatrix[i][j] = -1;
						}
						else if(element == "0"){
							TargetMatrix[i][j] = 0;
						}
						else{
							JOptionPane.showMessageDialog(null,"Please Enter Matrix In The Right Place!\n","Error!",JOptionPane.ERROR_MESSAGE);
							continue matrixBreakPoint;
						}
						System.out.print(TargetMatrix[i][j]+"\t");
						
					}
					System.out.print("\n");
				}
				try {
					Operon_Operon aTest = new Operon_Operon(matrixsize,TargetMatrix,operonDataBase,numOfOperons,operonNames);
					JTextPane newTextPanel = new JTextPane();
					JScrollPane newScrollPane = new JScrollPane(newTextPanel);
					System.out.println("There are "+aTest.numberPossibleChoices[0]+" possible choices\n");
					String result = "There are "+aTest.numberPossibleChoices[0]+" possible choices\n\n";
					newTextPanel.replaceSelection(result);
					for(int i = 0;i < aTest.numberPossibleChoices[0]; i++){
						System.out.print(i+"\n");
						newTextPanel.replaceSelection(i+1+"th choice:\n");
						for(int j = 0;j<matrixsize ; j++){
							System.out.print(aTest.result[i][j]+"\t");
							System.out.println(Operon_Operon.operonNames.get(aTest.result[i][j])+"\t");
							newTextPanel.replaceSelection(aTest.result[i][j]+"\t"+Operon_Operon.operonNames.get(aTest.result[i][j])+"\n");
						}
						System.out.print("\n\n");
						newTextPanel.replaceSelection("\n");
					}

					mainTabbedPane.addTab("Result"+index,newScrollPane);
					mainTabbedPane.setSelectedComponent(newScrollPane);
					index++;
				} 
	
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
			
		});
		
		modeTwoBt.addMouseListener(new MouseAdapter(){
			
		}
		);
		
		
		mainTabbedPane.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				int tabIndex = mainTabbedPane.indexAtLocation(e.getX(), e.getY());
				if(e.getClickCount() == 2 && tabIndex != -1){
					mainTabbedPane.remove(tabIndex);
				}
			}
			
		}
		);
		
		/*-----when press X, close the frame -------*/
		mainFrame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			    }
			 }
		);
	}

		
	
	/*-----------method to initiate the mainFrame GUI--------*/
	
	private void initComponents(){
		/*---------Initializing mainFrame and its container---*/
		mainFrame = new JFrame("USTC_Clotho_App");
		Container contentPane = mainFrame.getContentPane();
		
		/*------Initializing main panel---------*/
		JPanel panel_1 = new JPanel();
		matrixSizeLabel = new JLabel("Matrix Size:");
		sizeInput = new JTextField(null,10);
		verticalBox = Box.createVerticalBox();
		
		/*-----Initializing JTable----------*/
		inputMatrixTable = new JTable(10,10);
		JComboBox matrixElement = new JComboBox();
		matrixElement.addItem("");
		matrixElement.addItem("0");
		matrixElement.addItem("+");
		matrixElement.addItem("-");
		
		
		/*------add JComcoBox to  each elements-----*/
		for(int i = 0; i < 10; i++){
			inputMatrixTable.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(matrixElement));
		}
		JScrollPane scrollPane = new JScrollPane(inputMatrixTable); 
		inputMatrixTable.setFillsViewportHeight(true); 
		
		/*------initializing JMenu "File"----*/
		File = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem close = new JMenuItem("Close");
		JMenuItem clear = new JMenuItem("Clear workspace");
		File.add(save);
		File.add(clear);
		File.add(close);
		
		/*------initializing JMenu "OpenDataBase"----*/
		OpenDataBase = new JMenu("Database");
		JMenuItem Operon_Operon = new JMenuItem("Operon_Operon");
		JMenuItem Gene_Promoter = new JMenuItem("Gene_Promoter");
		OpenDataBase.add(Operon_Operon);
		OpenDataBase.add(Gene_Promoter);
		
		/*-------initializing JMenu "Help"----*/
		Help = new JMenu("Help");
		JMenuItem howToUse = new JMenuItem("How To Use");
		JMenuItem aboutDatabase = new JMenu("About Database");
		Help.add(howToUse);
		Help.add(aboutDatabase);
		
		/*------initializing JMenu "About"----*/
		About = new JMenu("About");
		JMenuItem aboutUSTC_2012 = new JMenuItem("About 2012 USTC-Software Team");
		JMenuItem aboutUSTC = new JMenuItem("About USTC");
		About.add(aboutUSTC_2012);
		About.add(aboutUSTC);
		
		/*------initializing JMenuBar mainMenu-------*/
		mainMenu = new JMenuBar();
		mainMenu.add(File);
		mainMenu.add(OpenDataBase);
		mainMenu.add(Help);
		mainMenu.add(About);
		
		
		/*-----initializing  Buttons---------*/
		commitSizeBt = new Button("Commit");
		modeOneBt = new Button("mode 1");
		modeTwoBt = new Button("mode 2");
		
		/*------adding to veticalBox--------*/
		verticalBox.add(matrixSizeLabel);
		verticalBox.add(sizeInput);
		verticalBox.add(commitSizeBt);
		verticalBox.add(modeOneBt);
		verticalBox.add(modeTwoBt);
		panel_1.add(verticalBox);
		panel_1.add(scrollPane);
		
		/*------adding to mainTabbedPane-----*/
		mainTabbedPane = new JTabbedPane();
		mainTabbedPane.setTabPlacement(JTabbedPane.TOP);
		//mainTabbedPane.addChangeListener(this);
		mainTabbedPane.addTab("main panel", panel_1);
	
		//JButton newButton = new JButton("a New Tab");
		//newButton.addActionListener(this);
		//contentPane.add(newButton,BorderLayout.SOUTH);
		mainFrame.setJMenuBar(mainMenu);
		contentPane.add(mainTabbedPane,BorderLayout.CENTER);
		mainFrame.setSize(600,400);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	/*-------------method to load the Operon_Operon database-------*/
	
	public void loadDataBaseOperon_Operon() throws IOException{

		/*-----------------read file-----------------------*/
		String filePath = PATH + "USTC_SOFTWARE_PARTS_DATA.txt";
		FileReader infile = new FileReader(filePath);
		BufferedReader in = new BufferedReader(infile);
		
		
		/*------ initializing numOfOperons-----------*/
		numOfOperons = Integer.parseInt(in.readLine());
		
		
		/*--------------initializing operonNames-----*/
		operonNames = new Vector<String>();
		for(int i = 0;i < numOfOperons; i++){
			operonNames.add(in.readLine());
		}
		
				
		/*---------------------generate database------------------*/
		operonDataBase = new int[numOfOperons][numOfOperons];
		for(int i = 0;i < numOfOperons; i++){
			for(int j = 0;j < numOfOperons;j++){
				operonDataBase[i][j] = Integer.parseInt(in.readLine());
			}
		}	
	}
	
	
	/*------------method to load the Gene_Promoter database---------*/
	public void loadDataBaseGene_Promoter(){
		
	}
	
 /*------    variables in the program --------*/
	private static JLabel matrixSizeLabel;
	private static JTable inputMatrixTable;
	private static JFrame mainFrame;
	private static JTextField sizeInput;
	private static int matrixsize;
	private static Button commitSizeBt;
	private static Button modeOneBt;
	private static Button modeTwoBt;
	private static JMenuBar mainMenu; 
	private static JMenu File;
	private static JMenu OpenDataBase;
	private static JMenu Help;
	private static JMenu About;
	private static JTabbedPane mainTabbedPane;
	private Box verticalBox;
	private static final String PATH = "F:/programs/java/";
	
	
	static int index = 1;    //tab index
	
	/*------variables for Operon_Operon database-------*/
	public static int[][] operonDataBase;
	public static int numOfOperons;
	public static Vector<String> operonNames;
	
	
	/*------variables for Gene_Promoter database------*/
	public static int[][] genepromoterDataBase;
	public static int numOfPromoters;
	public static int numOfGenes;
	public static Vector<String> geneNames;
	public static Vector<String> promoterNames;
	/* 
	@Override

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		 if (index!=mainTabbedPane.getSelectedIndex()){
	         if(index<mainTabbedPane.getTabCount()-1)
	        	 mainTabbedPane.setEnabledAt(index+1,true);
	     }
	     index=mainTabbedPane.getSelectedIndex();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel panel=new JPanel();
	      JLabel label=new JLabel("Result"+newNumber,JLabel.CENTER);
	      label.setOpaque(true);
	      panel.add(label);
	      mainTabbedPane.addTab("new Tab"+newNumber,panel);
	      mainTabbedPane.setEnabledAt(newNumber,true);
	      newNumber++;
	      mainTabbedPane.validate();
	}
	*/
}
