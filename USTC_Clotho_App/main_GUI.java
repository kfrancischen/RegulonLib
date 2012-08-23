/*---------------------------------------------------*/
// This java class is for USTC Clotho App            //
// Author: Francis Chen                              //
// Usage: This class is mainly for main GUI          //
// Copyrights Reserved                               //
/*---------------------------------------------------*/
package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
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
		initTextEvents();
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
		panel_1 = new JPanel();
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
		save.setEnabled(false);
		clear = new JMenuItem("Clear",'C');
		exit = new JMenuItem("Exit",'E');
		File.add(New);
		File.add(save);
		File.add(clear);
		File.add(exit);
		
		/*------initializing JMenu "Search"----*/
		Search = new JMenu("Search");
		Operon_Operon = new JMenuItem("Search in Operon-Operon");
		Gene_Promoter = new JMenuItem("Search in Gene-Promoter");
		Search.add(Operon_Operon);
		Search.add(Gene_Promoter);
		
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
		mainMenu.add(Search);
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
		mainTabbedPane.addTab("Main Page", panel_1);
	
		//JButton newButton = new JButton("a New Tab");
		//newButton.addActionListener(this);
		//contentPane.add(newButton,BorderLayout.SOUTH);
		mainFrame.setJMenuBar(mainMenu);
		contentPane.add(mainTabbedPane,BorderLayout.CENTER);
		mainFrame.setSize(600,400);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		outPutBuffers = new Vector<StringBuffer>();
		for(int i = 0; i < 1000; i++){
			outPutBuffers.add(null);
		}
	}
	
	
	/*-------------method to load the Operon_Operon database-------*/
	
	public void loadDataBase() throws IOException{

		/*---------First Part: Loading Operon_Operon Database-------*/
		
		//-----------------read file-----------------------//
		String filePath1 = PATH + "USTC_SOFTWARE_PARTS_DATA.txt";
		FileReader infile1 = new FileReader(filePath1);
		BufferedReader in1 = new BufferedReader(infile1);
		
		
		//----- initializing numOfOperons-----------//
		numOfOperons = Integer.parseInt(in1.readLine());
		
		
		//--------------initializing operonNames-----//
		operonNames = new Vector<String>();
		for(int i = 0;i < numOfOperons; i++){
			operonNames.add(in1.readLine());
		}
		
				
		//---------------------generate database------------------//
		operonDataBase = new int[numOfOperons][numOfOperons];
		for(int i = 0;i < numOfOperons; i++){
			for(int j = 0;j < numOfOperons;j++){
				operonDataBase[i][j] = Integer.parseInt(in1.readLine());
			}
		}
		in1.close();
		
		
		/*--------Second part: Loading Gene_Promoter Database------*/
		
		//-----------------read file-----------------------//
		String filePath2 = PATH + "USTC_SOFTWARE_BIOBRICKS_DATA.txt";
		FileReader infile2 = new FileReader(filePath2);
		BufferedReader in2 = new BufferedReader(infile2);
		
		//------------- initializing numOfGenes and numOfPromoters-------//
		numOfGenes = Integer.parseInt(in2.readLine());
		numOfPromoters = Integer.parseInt(in2.readLine());
		
		
		//------------------initializing geneNames--------------//
		geneNames = new Vector<String>();
		for(int i = 0;i < numOfGenes; i++){
			geneNames.add(in2.readLine());
		}
		
		
		//------------------initializing promoterNames-------------//
		promoterNames = new Vector<String>();
		for(int i = 0;i < numOfPromoters;i++){
			promoterNames.add(in2.readLine());
		}
		
		//--------------------generate database------------------//
		genepromoterDataBase = new int[numOfPromoters][numOfGenes];
		for(int i = 0;i < numOfPromoters; i++){
			for(int j = 0;j < numOfGenes;j++){
				genepromoterDataBase[i][j] = Integer.parseInt(in2.readLine());
			}
		}
		in2.close();
	}
	
	
	/*------------method to initiate button events---------*/
	public void initButtonEvents(){
		 
		//initiate commit size button, press it to enter the matrix size//
		commitSizeBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(sizeInput.getText().equals("")){
					System.out.print("Please Input The Matrix Size!\n");
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
							return;
						}
						System.out.print(TargetMatrix[i][j]+"\t");	
					}
					System.out.print("\n");
				}
			
				try {
					Operon_Operon aTest = new Operon_Operon(matrixsize,
														TargetMatrix,
														operonDataBase,
														numOfOperons,
														operonNames);
					StringBuffer aBuffer = new StringBuffer();
					JTextPane newTextPanel = new JTextPane();
					JScrollPane newScrollPane = new JScrollPane(newTextPanel);
					System.out.println("There are "+aTest.numberPossibleChoices[0]+" possible choices\n");
					newTextPanel.replaceSelection("Mode 1 is used:\n All these data are from Operon-Operon database.\n\n");
					aBuffer.append("Mode 1 is used:\n All these data are from Operon_Operon database.\n\n");
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
					aBuffer.append(result);
					newTextPanel.replaceSelection(result);
					for(int i = 0;i < aTest.numberPossibleChoices[0]; i++){
						System.out.print(i+"\n");
						newTextPanel.replaceSelection("choice "+(i+1)+":\n");
						aBuffer.append("Choice"+(i+1)+"\n");
						for(int j = 0;j<matrixsize ; j++){
							System.out.print(aTest.result[i][j]+"\t");
							System.out.println(operonNames.get(aTest.result[i][j])+"\t");
							newTextPanel.replaceSelection(aTest.result[i][j]+"\t"+operonNames.get(aTest.result[i][j])+"\n");
							aBuffer.append(aTest.result[i][j]+"\t"+operonNames.get(aTest.result[i][j])+"\n");
						}
						System.out.print("\n\n");
						newTextPanel.replaceSelection("\n");
						aBuffer.append("\n");
					}
					newTextPanel.setEditable(false);
					mainTabbedPane.addTab("Result"+index,newScrollPane);
					mainTabbedPane.setSelectedComponent(newScrollPane);
					save.setEnabled(true);
					outPutBuffers.setElementAt(aBuffer, index);  //buffer index is the same with the tab index
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
			public void mousePressed(MouseEvent e){
				
				if(matrixsize == 0){
					System.out.println("Please Input Matrix Size First!\n");
					JOptionPane.showMessageDialog(null,"Please Input Matrix Size First!\n","Error!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				final int[][] TargetMatrix = new int[matrixsize][matrixsize];
				
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
							return;
						}
						System.out.print(TargetMatrix[i][j]+"\t");	
					}
					System.out.print("\n");
				}
				try{
					Gene_Promoter aTest = new Gene_Promoter(matrixsize,
															TargetMatrix,
															genepromoterDataBase,
															numOfGenes,
															numOfPromoters,
															geneNames,
															promoterNames);
					StringBuffer aBuffer = new StringBuffer();
					JTextPane newTextPanel = new JTextPane();
					JScrollPane newScrollPane = new JScrollPane(newTextPanel);
					System.out.println("There are "+aTest.numberPossibleChoices[0]+" possible choices\n");
					newTextPanel.replaceSelection("Mode 2 is used:\n All these data are from Gene_Promoter database.\n\n");
					aBuffer.append("Mode 2 is used:\n All these data are from Gene_Promoter database.\n\n");
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
					aBuffer.append(result);
					newTextPanel.replaceSelection(result);
					for(int i = 0; i < aTest.numberPossibleChoices[0];i++){
						System.out.print(i+"\n");
						newTextPanel.replaceSelection("choice "+(i+1)+":\n");
						aBuffer.append("Choice"+(i+1)+"\n");
						for(int j = 0; j < matrixsize; j++){
							System.out.println(aTest.result[i][0][j]+"\t"+promoterNames.get(aTest.result[i][0][j])+"\n");
							System.out.println(aTest.result[i][1][j]+"\t"+geneNames.get(aTest.result[i][1][j])+"\n");
							newTextPanel.replaceSelection(aTest.result[i][0][j]+"\t"+promoterNames.get(aTest.result[i][0][j])+"\n");
							aBuffer.append(aTest.result[i][0][j]+"\t"+promoterNames.get(aTest.result[i][0][j])+"\n");
							newTextPanel.replaceSelection(aTest.result[i][1][j]+"\t"+geneNames.get(aTest.result[i][1][j])+"\n");
							aBuffer.append(aTest.result[i][1][j]+"\t"+geneNames.get(aTest.result[i][1][j])+"\n");
						}
						System.out.print("\n\n");
						newTextPanel.replaceSelection("\n");
						aBuffer.append("\n");
					}
					newTextPanel.setEditable(false);
					mainTabbedPane.addTab("Result"+index,newScrollPane);
					mainTabbedPane.setSelectedComponent(newScrollPane);
					save.setEnabled(true);
					outPutBuffers.setElementAt(aBuffer, index);  //buffer index is the same with the tab index
					index++;
				}
				catch(IOException e2){
					JOptionPane.showMessageDialog(null,"Cannot Open Gene_Promoter Database!\n","Error!",JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
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
				if(e.getClickCount() == 2 && tabIndex != -1 && tabIndex != 0){
					mainTabbedPane.remove(tabIndex);
					outPutBuffers.setElementAt(null, tabIndex);   //when close the tab, remove its buffer
				}
				int numOfTabs = mainTabbedPane.getTabCount();
				if(numOfTabs == 1){
					save.setEnabled(false);
				}
			}
			
		}
		);
	}
	
	/*--------method to initiate text events---------*/
	public void initTextEvents(){
		sizeInput.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() == KeyEvent.VK_ENTER){
					if(sizeInput.getText().equals("")){
						System.out.print("Please Input The Matrix Size!\n");
						return;
					}
					matrixsize = Integer.parseInt(sizeInput.getText());
					System.out.print(matrixsize+"\n\n");
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
				int numOfTabs = mainTabbedPane.getTabCount();
				if(numOfTabs == 1){
					save.setEnabled(false);
				}
				mainTabbedPane.setSelectedComponent(panel_1);
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
				int numOfTabs = mainTabbedPane.getTabCount();
				int currentTab = 0;
				for(int i = 0; i < numOfTabs; i++){
					if(mainTabbedPane.getComponent(i) == mainTabbedPane.getSelectedComponent()){
						currentTab = i;
						break;
					}
				}
				if(numOfTabs == 1){
					JOptionPane.showMessageDialog(null,"No Result To Be Saved!\n","Warning!",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(currentTab == 0){
					JOptionPane.showMessageDialog(null,"Please Turn To The Result Tab!\n","Warning!",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				//mainTabbedPane.getComponent(tabIndex).getComponentAt(0, 0).get;
			

				String currentTitle = mainTabbedPane.getTitleAt(currentTab);
				String temp = String.valueOf(currentTitle.charAt(currentTitle.length()-1));
				int currentIndex = Integer.parseInt(temp);
				
				JFileChooser saveFile = new JFileChooser();
				saveFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				saveFile.showSaveDialog(null);
				File targetFile=saveFile.getSelectedFile(); 
				String path=targetFile.getPath(); 
				String fileName = currentTitle+".txt";
				File newFile = new File(path + "/" + fileName);
				try {
					FileWriter out = new FileWriter(newFile);
					out.write(outPutBuffers.get(currentIndex).toString());
					out.close();
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Failed to save the file!","Error!",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		//initiate JMenuItem "clear"
		clear.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				sizeInput.setText(null);
				save.setEnabled(false);
				index = 1;
				outPutBuffers.removeAllElements(); //remove all the string buffers
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
	
	/*---------method to initiate Search menu events----*/
	public void initDatabaseMenuEvents(){
		
		//initializing JMenuItem "Operon_Operon"
		Operon_Operon.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Search_Database searchInO_O = new Search_Database(numOfOperons,
																numOfOperons,
																operonNames,
																operonNames,
																operonDataBase);
				searchInO_O.setTitle("Search Page");
				searchInO_O.setVisible(true);
			}
		}
		);
		
		//initializing JMenuItem "Gene_Promoter"
		Gene_Promoter.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Search_Database searchInG_P = new Search_Database(numOfGenes,
																numOfPromoters,
																geneNames,
																promoterNames,
																genepromoterDataBase);
				searchInG_P.setTitle("Search Page");
				searchInG_P.setVisible(true);
			}
		}
		);
	}
	
	
	/*-------method to initiate help menu events----*/
	public void initHelpMenuEvents(){
		
		//initiate JMenuItem "how to use" 
		howToUse.addMouseListener(new MouseAdapter(){
			public void	mousePressed(MouseEvent e){ 
				JOptionPane.showMessageDialog(null,"\n","How To Use",JOptionPane.PLAIN_MESSAGE);
			}
		}
		);
		
		//initiate JMenuItem "About DataBase"
		aboutDatabase.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				LinkLabel AboutDatabase= new LinkLabel("RegulonDB", "http://regulondb.ccg.unam.mx/");  
				Object[] message = { "DataBases are from RegulonDB",AboutDatabase};
				JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE);
				JDialog dialog = pane.createDialog(pane, "About Database");  
                dialog.setVisible(true); 
			}
		}
		);
		
	}
	
	/*------method to initiate about menu events-----*/
	public void initAboutMenuEvents(){
		//initiate JMenuItem "aboutUSTC_2012"
		aboutUSTC_2012.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				LinkLabel AboutUSTC_2012 = new LinkLabel("2012 USTC_SoftWare Wiki","http://2012.igem.org/Team:USTC-Software");
				Object[] message = {"2012 USTC_Software Team consists of XX members,visit our wiki",AboutUSTC_2012};
				JOptionPane pane = new JOptionPane(message,JOptionPane.PLAIN_MESSAGE);
				JDialog dialog = pane.createDialog(pane, "About 2012 USTC-Software Team");
				dialog.setVisible(true);
			}
		});
		
		//initiate JMenuItem "aboutUSTC"
		aboutUSTC.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				LinkLabel AboutUSTC = new LinkLabel("USTC Website","http://www.ustc.edu.cn/");
				Object[] message = {"USTC, namely Univeristy of Science Technology.\n Visit the website",AboutUSTC};
				JOptionPane pane = new JOptionPane(message,JOptionPane.PLAIN_MESSAGE);
				JDialog dialog = pane.createDialog(pane,"About USTC");
				dialog.setVisible(true);
			}
		}
		);
		
	}
 /*------    variables in the program --------*/
	private static JPanel panel_1;
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
	private static JMenu Search;
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
	
	public static Vector<StringBuffer> outPutBuffers;
}
