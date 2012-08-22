import java.awt.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("serial")
public class test extends JFrame{
	public static JTable regulatorTable =new JTable();
	public static JScrollPane regulatorScrollPane = new JScrollPane();
	public static JTable regulateeTable = new JTable();
	public static JScrollPane regulateeScrollPane = new JScrollPane();
	
	public static JTable regulateeCandidates = new JTable();
	public static JScrollPane regulateeCanScrollPane = new JScrollPane();
	public static JTable regulatorCandidates = new JTable();
	public static JScrollPane regulatorCanScrollPane = new JScrollPane();
	
	public static JTextField regulatorName = new JTextField();
	public static JTextField regulateeName = new JTextField();
	
	//public static JButton searchBt;
	
	
	private static JButton searchBt = new JButton();
	//private Box boxOfRegulator;
	//private Box boxOfRegulatee;
	
	private static JLabel regulatorNameLabel = new JLabel();
	private static JLabel regulateeNameLabel = new JLabel();
	private static JLabel pictureLabel = new JLabel();
	public test(){
		initComponents();
	}
	public static void main(String[] args) {  
		test aNew = new test();
		aNew.setTitle("SearchPage");
		aNew.setVisible(true);
	}
	public void initComponents(){
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));

		regulatorScrollPane.setBackground(new java.awt.Color(255, 255, 255));
		regulatorScrollPane.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		regulatorScrollPane.setForeground(new java.awt.Color(204, 204, 204));
		regulatorScrollPane.setViewportBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

		regulatorTable.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		regulatorTable.setFont(new java.awt.Font("Consolas", 0, 12));
		regulatorTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		regulatorTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		regulatorTable.setGridColor(new java.awt.Color(204, 204, 204));
		regulatorTable
				.setSelectionBackground(new java.awt.Color(204, 204, 204));
		regulatorScrollPane.setViewportView(regulatorTable);

		regulatorNameLabel.setFont(new java.awt.Font("Consolas", 1, 14));
		regulatorNameLabel.setText("jLabel1");
		regulatorNameLabel.setOpaque(true);

		regulatorName.setFont(new java.awt.Font("Courier New", 0, 12));

		regulateeCanScrollPane.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(204, 204, 204), 1, true));
		regulateeCanScrollPane.setForeground(new java.awt.Color(204, 204, 204));
		regulateeCanScrollPane.setViewportBorder(javax.swing.BorderFactory
				.createEtchedBorder());

		regulateeCandidates.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		regulateeCandidates.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		regulateeCandidates
				.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		regulateeCandidates.setGridColor(new java.awt.Color(204, 204, 204));
		regulateeCandidates.setSelectionBackground(new java.awt.Color(204, 204,
				204));
		regulateeCanScrollPane.setViewportView(regulateeCandidates);

		pictureLabel.setText("picture");

		regulateeNameLabel.setFont(new java.awt.Font("Consolas", 1, 14));
		regulateeNameLabel.setLabelFor(regulatorName);
		regulateeNameLabel.setText("jLabel1");
		regulateeNameLabel.setOpaque(true);

		regulateeName.setFont(new java.awt.Font("Courier New", 0, 12));

		regulatorCanScrollPane.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(204, 204, 204), 1, true));
		regulatorCanScrollPane.setForeground(new java.awt.Color(204, 204, 204));
		regulatorCanScrollPane.setViewportBorder(javax.swing.BorderFactory
				.createEtchedBorder());

		regulatorCandidates.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		regulatorCandidates.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		regulatorCandidates
				.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		regulatorCandidates.setGridColor(new java.awt.Color(204, 204, 204));
		regulatorCandidates.setSelectionBackground(new java.awt.Color(204, 204,
				204));
		regulatorCanScrollPane.setViewportView(regulatorCandidates);

		regulateeScrollPane.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		regulateeScrollPane.setForeground(new java.awt.Color(204, 204, 204));

		regulateeTable.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		regulateeTable.setFont(new java.awt.Font("Consolas", 0, 12));
		regulateeTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		regulateeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		regulateeTable.setGridColor(new java.awt.Color(204, 204, 204));
		regulateeTable
				.setSelectionBackground(new java.awt.Color(102, 102, 102));
		regulateeScrollPane.setViewportView(regulateeTable);

		searchBt.setBackground(new java.awt.Color(153, 153, 153));
		searchBt.setFont(new java.awt.Font("΢���ź�", 1, 14));
		searchBt.setText("Search!");
		searchBt.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		searchBt.setOpaque(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(regulatorScrollPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		regulatorNameLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		78,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(37, 37,
																		37))
												.addComponent(
														regulatorName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														115,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														regulateeCanScrollPane,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														173,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(7, 7, 7)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		searchBt,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		100,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED))
												.addComponent(
														pictureLabel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														100,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(63, 63,
																		63)
																.addComponent(
																		regulateeNameLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		70,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(5, 5, 5)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						regulateeName,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						115,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						regulatorCanScrollPane,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						173,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(regulateeScrollPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										114,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(5,
																										5,
																										5)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		regulatorNameLabel,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		27,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGap(2,
																																		2,
																																		2))
																												.addGroup(
																														javax.swing.GroupLayout.Alignment.LEADING,
																														layout.createSequentialGroup()
																																.addComponent(
																																		regulateeNameLabel,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		27,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGap(2,
																																		2,
																																		2)))
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGap(2,
																																		2,
																																		2)
																																.addComponent(
																																		regulatorName,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		29,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																.addComponent(
																																		regulateeCanScrollPane,
																																		0,
																																		0,
																																		Short.MAX_VALUE))
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGap(1,
																																		1,
																																		1)
																																.addComponent(
																																		regulateeName,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		28,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGap(18,
																																		18,
																																		18)
																																.addComponent(
																																		regulatorCanScrollPane,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		308,
																																		Short.MAX_VALUE))))
																				.addComponent(
																						regulateeScrollPane,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						389,
																						Short.MAX_VALUE)
																				.addComponent(
																						regulatorScrollPane,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						385,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(39, 39,
																		39)
																.addComponent(
																		pictureLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		50,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		searchBt)))
								.addContainerGap()));

		pack();
	}// </editor-fold>
}

