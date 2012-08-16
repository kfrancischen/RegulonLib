
package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import java.util.List;
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
		mainFrame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			    }
			 }
		);
	}

		
	private void initComponents(){
		
		mainFrame = new JFrame();

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
			
		GridLayout Layout = new GridLayout(3,2);
		mainFrame.setLayout(Layout);
		mainFrame.add(matrixSize);
		mainFrame.add(sizeInput);
		mainFrame.add(commitSize);

		
		mainFrame.add(scrollPane);
		mainFrame.add(modeOne);
		mainFrame.add(modeTwo);
		mainFrame.setBounds(100, 100, 400, 800);
		mainFrame.setVisible(true);

	}

	private static JLabel matrixSize;
	private static JTable inputMatrix;
	private static JFrame mainFrame;
	private static JTextField sizeInput;
	private static int matrixsize;
	private static Button commitSize;
	private static Button modeOne;
	private static Button modeTwo;
}
