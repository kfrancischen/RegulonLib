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
		final Object[][] playerInfo = { { "����", 91, 100, 191, true }, { "��ѩ��", 91, 100, 191, true } ,{ "��ѩ��", 91, 100, 191, true }};  
        final String names[] = { "����", "����", "��ѧ", "�ܷ�", "����" };  
        final JTable table = new JTable(playerInfo, names);
        JPanel testPanel = new JPanel();
        JFrame testFrame = new JFrame();
        testPanel.add(table);
        testFrame.add(testPanel);
        testFrame.setSize(400, 200);
        final ListSelectionModel selectionModel = table.getSelectionModel();  
        selectionModel.addListSelectionListener(new ListSelectionListener() { // ѡȡ������  
            @Override  
            public void valueChanged(ListSelectionEvent e) {  
                  
                System.out.println("happened"); 
            }
	});
        testFrame.setVisible(true);
	}
}

