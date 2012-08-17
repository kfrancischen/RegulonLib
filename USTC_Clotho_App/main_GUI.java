
package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import java.util.List;
import java.util.Vector;

public class main_GUI{

	public main_GUI(){
		initComponents();
	}

	public static void main(String args[]){

		new main_GUI();
		System.out.print("Enter the Matrix size:\n");
		
		commitSizeBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(sizeInput.getText() == ""){
					System.out.print("Please input the matrix size and press commit.\n");
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
					return;
				}
				final int[][] TargetMatrix = new int[matrixsize][matrixsize];
				for(int i = 0; i < matrixsize;i++){
					for(int j = 0;j < matrixsize;j++){
						TargetMatrix[i][j] = Integer.parseInt((String)inputMatrixTable.getValueAt(i,j));
						System.out.print(TargetMatrix[i][j]+"\t");
						
					}
					System.out.print("\n");
				}
				try {
					Operon_Operon aTest = new Operon_Operon(matrixsize,TargetMatrix);
					JTextPane newTextPanel = new JTextPane();
					JScrollPane newScrollPane = new JScrollPane(newTextPanel);
					System.out.println("There are "+aTest.numberPossibleChoices[0]+" possible choices\n");
					String result = "There are "+aTest.numberPossibleChoices[0]+" possible choices\n\n";
					newTextPanel.replaceSelection(result);
					for(int i = 0;i < aTest.numberPossibleChoices[0]; i++){
						System.out.print(i+"\n");
						newTextPanel.replaceSelection(i+"\n");
						for(int j = 0;j<matrixsize ; j++){
							System.out.print(aTest.result[i][j]+"\t");
							System.out.println(Operon_Operon.operonNames.get(aTest.result[i][j])+"\t");
							newTextPanel.replaceSelection(aTest.result[i][j]+"\t"+Operon_Operon.operonNames.get(aTest.result[i][j])+"\n");
						}
						System.out.print("\n\n");
						newTextPanel.replaceSelection("\n");
					}
					mainTabbedPane.add("Result"+index,newScrollPane);
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
		mainFrame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			    }
			 }
		);
	}

		
	private void initComponents(){
		mainFrame = new JFrame("USTC_Clotho_App");
		Container contentPane = mainFrame.getContentPane();
		
		
		JPanel panel_1 = new JPanel();
		matrixSizeLabel = new JLabel("Matrix Size:");
		sizeInput = new JTextField("",10);
		verticalBox = Box.createVerticalBox();
		
		inputMatrixTable = new JTable(10,10);
		JScrollPane scrollPane = new JScrollPane(inputMatrixTable); 
		inputMatrixTable.setFillsViewportHeight(true); 

		commitSizeBt = new Button("Commit");
		modeOneBt = new Button("mode 1");
		modeTwoBt = new Button("mode 2");
		verticalBox.add(matrixSizeLabel);
		verticalBox.add(sizeInput);
		verticalBox.add(commitSizeBt);
		verticalBox.add(modeOneBt);
		verticalBox.add(modeTwoBt);
		panel_1.add(verticalBox);
		panel_1.add(scrollPane);
		
		
		mainTabbedPane = new JTabbedPane();
		mainTabbedPane.setTabPlacement(JTabbedPane.TOP);
		//mainTabbedPane.addChangeListener(this);
		mainTabbedPane.addTab("main panel", panel_1);
	
		//JButton newButton = new JButton("a New Tab");
		//newButton.addActionListener(this);
		//contentPane.add(newButton,BorderLayout.SOUTH);
		contentPane.add(mainTabbedPane,BorderLayout.CENTER);
		mainFrame.setSize(600,400);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	

	private static JLabel matrixSizeLabel;
	private static JTable inputMatrixTable;
	private static JFrame mainFrame;
	private static JTextField sizeInput;
	private static int matrixsize;
	private static Button commitSizeBt;
	private static Button modeOneBt;
	private static Button modeTwoBt;
	
	private static JTabbedPane mainTabbedPane;
	private Box verticalBox;
	static int index = 1;
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
