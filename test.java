import java.awt.*;
import java.io.*;
import javax.swing.*;

import javax.swing.event.*;
public class test {
	public static void main(String[] args) {  
        // TODO Auto-generated method stub  
		//JTable testTable = new JTable(10,10);
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
	
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {  
				System.out.print("It happend");
			}
		}
		);
	};
}

