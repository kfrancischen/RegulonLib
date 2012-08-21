import java.awt.*;
import java.io.*;
import javax.swing.*;

import javax.swing.event.*;
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
		final Object[][] playerInfo = { { "王鹏", 91, 100, 191, true }, { "朱雪莲", 91, 100, 191, true } ,{ "朱雪莲", 91, 100, 191, true }};  
        final String names[] = { "姓名", "语文", "数学", "总分", "及格" };  
        final JTable table = new JTable(playerInfo, names);
        JPanel testPanel = new JPanel();
        JFrame testFrame = new JFrame();
        testPanel.add(table);
        testFrame.add(testPanel);
        testFrame.setSize(400, 200);
        final ListSelectionModel selectionModel = table.getSelectionModel();  
        selectionModel.addListSelectionListener(new ListSelectionListener() { // 选取侦听器  
            @Override  
            public void valueChanged(ListSelectionEvent e) {  
                  
                System.out.println("happened"); 
            }
	});
        testFrame.setVisible(true);
	}
}

