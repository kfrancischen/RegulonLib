
package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;

import java.util.Vector;

public class main_GUI{

	public main_GUI(){
		try {
			loadDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Unable To Load The Database","Error!",JOptionPane.ERROR_MESSAGE);
		}
		initComponents();
		initFrameEvents();
		initTabEvents();
		initButtonEvents();
		initFileMenuEvents();
		initDatabaseMenuEvents();
		initHelpMenuEvents();
		initAboutMenuEvents();
	}

	
	/*-----main function of the class------*/
	public static void main(String args[]){

		new main_GUI();
		System.out.print("Enter the Matrix size:\n");	
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
		New = new JMenuItem("New",'N');
		save = new JMenuItem("Save",'S');
		clear = new JMenuItem("Clear",'C');
		exit = new JMenuItem("Exit",'E');
		File.add(New);
		File.add(save);
		File.add(clear);
		File.add(exit);
		
		/*------initializing JMenu "OpenDataBase"----*/
		OpenDataBase = new JMenu("Database");
		Operon_Operon = new JMenuItem("Operon_Operon");
		Gene_Promoter = new JMenuItem("Gene_Promoter");
		OpenDataBase.add(Operon_Operon);
		OpenDataBase.add(Gene_Promoter);
		
		/*-------initializing JMenu "Help"----*/
		Help = new JMenu("Help");
		howToUse = new JMenuItem("How To Use");
		aboutDatabase = new JMenu("About Database");
		Help.add(howToUse);
		Help.add(aboutDatabase);
		
		/*------initializing JMenu "About"----*/
		About = new JMenu("About");
		aboutUSTC_2012 = new JMenuItem("About 2012 USTC-Software Team");
		aboutUSTC = new JMenuItem("About USTC");
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
	
	public void loadDataBase() throws IOException{

		/*---------First Part: Loading Operon_Operon Database-------*/
		
		//-----------------read file-----------------------//
		String filePath = PATH + "USTC_SOFTWARE_PARTS_DATA.txt";
		FileReader infile = new FileReader(filePath);
		BufferedReader in = new BufferedReader(infile);
		
		
		//----- initializing numOfOperons-----------//
		numOfOperons = Integer.parseInt(in.readLine());
		
		
		//--------------initializing operonNames-----//
		operonNames = new Vector<String>();
		for(int i = 0;i < numOfOperons; i++){
			operonNames.add(in.readLine());
		}
		
				
		//---------------------generate database------------------//
		operonDataBase = new int[numOfOperons][numOfOperons];
		for(int i = 0;i < numOfOperons; i++){
			for(int j = 0;j < numOfOperons;j++){
				operonDataBase[i][j] = Integer.parseInt(in.readLine());
			}
		}
		in.close();
		
		/*---------Second part: Loading Gene_Promoter Database------*/
		//to be continued
	}
	
	
	/*------------method to initiate button events---------*/
	public void initButtonEvents(){
		 
		//initiate commit size button, press it to enter the matrix size//
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
		
		
		//initiate mode One button, press it to use mode one//
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
					newTextPanel.replaceSelection("Mode 1 is used:\n All these data are from Operon-Operon database.\n\n");
					String result = "";
					switch(aTest.numberPossibleChoices[0]){
					case 0:
						result = "There is no possible choice\n\n";
						break;
					case 1:
						result = "There is only one possible choice\n\n";
						break;
					default:
						result = "There are "+aTest.numberPossibleChoices[0]+" possible choices\n\n";
					}
					
					newTextPanel.replaceSelection(result);
					for(int i = 0;i < aTest.numberPossibleChoices[0]; i++){
						System.out.print(i+"\n");
						newTextPanel.replaceSelection("choice "+(i+1)+":\n");
						for(int j = 0;j<matrixsize ; j++){
							System.out.print(aTest.result[i][j]+"\t");
							System.out.println(operonNames.get(aTest.result[i][j])+"\t");
							newTextPanel.replaceSelection(aTest.result[i][j]+"\t"+operonNames.get(aTest.result[i][j])+"\n");
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
					JOptionPane.showMessageDialog(null,"Cannot Open Operon_Operon Database!\n","Error!",JOptionPane.ERROR_MESSAGE);
				}	
			}
			
		});
		
		
		//initiate mode two button, press it to use mode two------*/
		modeTwoBt.addMouseListener(new MouseAdapter(){
			
		}
		);
		
	}
	
	
	/*--------method to initiate frame close events-----*/
	public void initFrameEvents(){
		/*-----when press X, close the frame -------*/
		mainFrame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			    }
			 }
		);
	}
	
	/*---------method to initiate tab events----------*/
	public void initTabEvents(){
		
		//if double click the tab, it will close-----*/
		mainTabbedPane.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				int tabIndex = mainTabbedPane.indexAtLocation(e.getX(), e.getY());
				if(e.getClickCount() == 2 && tabIndex != -1){
					mainTabbedPane.remove(tabIndex);
				}
			}
			
		}
		);
	}
	
	/*--------method to initiate file menu events-------*/
	public void initFileMenuEvents(){
		
		//initiate JMenuItem "New"
		New.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				sizeInput.setText(null);
				int numRow = inputMatrixTable.getRowCount();
				int numColumn = inputMatrixTable.getColumnCount();
				for(int i = 0; i < numRow; i++){
					for(int j = 0; j < numColumn; j++){
						inputMatrixTable.setValueAt(null,i, j);
					}
				}
			}
			
		});
		
		//initiate JMenuItem "Save"
		
		save.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				
				
				
			}
		});
		
		//initiate JMenuItem "clear"
		clear.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				sizeInput.setText(null);
				index = 1;
				int numRow = inputMatrixTable.getRowCount();
				int numColumn = inputMatrixTable.getColumnCount();
				for(int i = 0; i < numRow; i++){
					for(int j = 0; j < numColumn; j++){
						inputMatrixTable.setValueAt(null,i, j);
					}
				}
				int numOfTabs = mainTabbedPane.getTabCount();
				if(numOfTabs <= 1)
					return;
				else{
					for(int i = 1; i < numOfTabs; i++){
						mainTabbedPane.remove(i);
					}
				}
				
			}
			
		});
		
		//initiate JMenuItem "Exit"
		exit.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				System.exit(0);
			}
		});
	}
	
	/*---------method to initiate database menu events----*/
	public void initDatabaseMenuEvents(){
		
	}
	
	
	/*-------method to initiate help menu events----*/
	public void initHelpMenuEvents(){
		
	}
	
	/*------method to initiate about menu events-----*/
	public void initAboutMenuEvents(){
		
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
	
	//JMenuItems for Menu "File"
	private static JMenuItem New;
	private static JMenuItem save;
	private static JMenuItem clear;
	private static JMenuItem exit;
	
	
	//JMenuItems for Menu "Database"
	private static JMenuItem Operon_Operon;
	private static JMenuItem Gene_Promoter;
	
	//JMenuItems for Menu "Help"
	private static JMenuItem howToUse;
	private static JMenuItem aboutDatabase;
	
	//JMenuItems for Menu "About"
	private static JMenuItem aboutUSTC_2012;
	private static JMenuItem aboutUSTC;
	
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
}
