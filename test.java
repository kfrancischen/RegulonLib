import java.awt.*;
import java.io.*;
import javax.swing.*;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
public class test {
	public static void main(String[] args) {  
        // TODO Auto-generated method stub  
		//JTable testTable = new JTable(10,10);
		/*
		DefaultListModel listModel = new DefaultListModel();
		JPanel testPanel = new JPanel();
		JFrame testFrame = new JFrame();
				//Container contenpanel = testFrame.getContentPane();
		//contenpanel.add(testPanel);
	//	testFrame.setVisible(true);
		testFrame.setSize(400, 200);
		listModel.addElement("it");
		listModel.addElement("is");
		JList list = new JList(listModel);
		testPanel.add(list);
		testFrame.add(testPanel);
		testFrame.setVisible(true);
	
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {  
				System.out.print("It happend");
			}
		}
		);
		*/
		JTable aTest = new JTable(20,1);
		int numRow = aTest.getRowCount();
		//System.out.print(numRow+"\n");
		for(int i = 0; i < numRow; i++){
			aTest.setValueAt(i, i, 0);
			System.out.print(aTest.getValueAt(i, 0)+"\n");
		}
		
		for(int i = 0;i < numRow; i++){
			aTest.setValueAt(null,i,0);
		}
		DefaultTableModel aNewModel = new DefaultTableModel(30,1);
		aTest.setModel(aNewModel);
		
		int newRow = aTest.getRowCount();
		for(int i = 0;i < newRow;i++){
			aTest.setValueAt(i, i, 0);
			System.out.print(aTest.getValueAt(i, 0)+"\n");
		}
		//System.out.print(newRow);
	}
		
}

