/*******************************************************
This class generates graphical view for TruthTables

 * Author: Team BoO1Zebra
 *               (ds3161,sc3545,nr2445,rp2614,tm2635)
 *               Columbia University
 *               NY-10027
 * ******************************************************/
	
//package javaFunctions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TruthTableFrame extends JFrame{
	
  	private static final long serialVersionUID = 1L;
	
  	JTable table;

	public TruthTableFrame(String columnNames1[],Object data1[][]){
		setLayout (new FlowLayout());
		
		String [] columnNames=columnNames1;
		
		Object[][] data = data1;
		
		int noOfCol=columnNames.length;
		int noOfRows=data.length;
		int w=noOfCol*200;
		int h=noOfRows*40;
		
		table =new JTable(data,columnNames);
		table.setFillsViewportHeight(true);
		this.setSize(w,h);
		this.setPreferredSize(new Dimension(w/2,h/2));
		table.setRowHeight(20);
		JScrollPane scrollpane= new JScrollPane(table);
		add(scrollpane);
	   }
	
	}
	
