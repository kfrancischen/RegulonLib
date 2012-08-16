
package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.util.Vector;
public class main_GUI  {

	public main_GUI(){
		initComponents();
	}

	public static void main(String args[]){

		new main_GUI();
		System.out.print("Enter the Matrix size:\n");
		
		commitSize.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(sizeInput.getText() == ""){
					System.out.print("Please input the matrix size and press commit.\n");
					return;
				}
				matrixsize = Integer.parseInt(sizeInput.getText());
				System.out.print(matrixsize+"\n\n");
			}
		});
		 
		modeOne.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(matrixsize == 0){
					System.out.println("Please Input Matrix Size First!\n");
					return;
				}
				final int[][] TargetMatrix = new int[matrixsize][matrixsize];
				for(int i = 0; i < matrixsize;i++){
					for(int j = 0;j < matrixsize;j++){
						TargetMatrix[i][j] = Integer.parseInt((String)inputMatrix.getValueAt(i,j));
						System.out.print(TargetMatrix[i][j]+"\t");
					}
					System.out.print("\n");
				}
				try {
					Operon_Operon aTest = new Operon_Operon(matrixsize,TargetMatrix);
					System.out.println("There are "+aTest.numberPossibleChoices[0]+" possible choices\n");
					for(int i = 0;i < aTest.numberPossibleChoices[0]; i++){
						System.out.print(i+"\n");
						for(int j = 0;j<matrixsize ; j++){
							System.out.print(aTest.result[i][j]+"\t");
							System.out.println(Operon_Operon.operonNames.get(aTest.result[i][j])+"\t");
						}
						System.out.print("\n\n");
					}
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
			
		});
		
		modeTwo.addMouseListener(new MouseAdapter(){
			
		}
		);
		mainWindow.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			    }
			 }
		);
	}

		
	private void initComponents(){
		
		mainFrame1 = new JFrame();
		mainFrame2 = new JFrame();
		mainWindow = new JWindow();
		
		matrixSize = new JLabel("Matrix Size");
		sizeInput = new JTextField("",10);
		
		inputMatrix = new JTable(100,100);
		JPanel panelOfInputMatrix = new JPanel(new BorderLayout());
		panelOfInputMatrix.add(inputMatrix,BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(panelOfInputMatrix);   
		inputMatrix.setFillsViewportHeight(true); 
		
		
		commitSize= new Button("Confirm");
		modeOne = new Button("Mode 1");
		modeTwo = new Button("Mode 2");
			
		mainFrame1.setLayout(new GridLayout(3,2));
		mainFrame1.add(matrixSize);
		mainFrame1.add(sizeInput);
		mainFrame1.add(commitSize);
		mainFrame1.add(modeOne);
		mainFrame1.add(modeTwo);
		mainFrame1.setBounds(100, 100, 100, 200);
		
		mainFrame2.setLayout(new GridLayout(1,1));
		mainFrame2.add(scrollPane);
		mainFrame2.setBounds(100, 100, 100, 200);

		mainWindow.setLayout(new GridLayout(2,1));
		mainWindow.add(mainFrame1);
		mainWindow.add(mainFrame2);
		
		mainWindow.setBackground(Color.gray);
		mainWindow.setBounds(100, 100, 200, 200);
		mainWindow.setVisible(true);
	}

	private static JLabel matrixSize;
	private static JTable inputMatrix;
	private static JWindow mainWindow;
	private static JFrame mainFrame1;
	private static JFrame mainFrame2;
	private static JTextField sizeInput;
	private static int matrixsize;
	private static Button commitSize;
	private static Button modeOne;
	private static Button modeTwo;
}
