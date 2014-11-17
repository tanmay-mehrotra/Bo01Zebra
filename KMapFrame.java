/*******************************************************
This class generates graphical view for Kmaps

 * Author: Team BoO1Zebra
 *               (ds3161,sc3545,nr2445,rp2614,tm2635)
 *               Columbia University
 *               NY-10027
 * ******************************************************/

//package javaFunctions;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class KMapFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
    private JTable table;
    static  String [] columnNames;
    static Object[][] data;
    static String kmaptype;
    
    public KMapFrame() {
        initComponents();
    }

 
    private void initComponents() {
        
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(kmaptype);
        
        table = new javax.swing.JTable() {
   
			private static final long serialVersionUID = 1L;

			@Override
            public Component prepareRenderer(
                TableCellRenderer renderer, int row, int col) {
                if (col == 0) {
                    return this.getTableHeader().getDefaultRenderer()
                        .getTableCellRendererComponent(this, this.getValueAt(
                            row, col), false, false, row, col);
                } else {
                	JComponent component = (JComponent) super.prepareRenderer(renderer, row, col);
                	            	
                	return component;
                }
            }
        };
        
        table.setAutoCreateRowSorter(false);
        table.setRowHeight(65);
        final JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table));
        
        table.setModel(new javax.swing.table.DefaultTableModel(
            data,columnNames));
        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        pack();
    }

    
    public static void funct(String columnNames1[],Object data1[][], String kmaptype1) {
    	
       columnNames=columnNames1;
	   data = data1;
	   kmaptype=kmaptype1;
    	
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KMapFrame().setVisible(true);
            }
        });
    }

    
    
    private static class HeaderRenderer implements TableCellRenderer {

        TableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
            
        	Component c =renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
        	
            return c;
        
        }
    }
}


