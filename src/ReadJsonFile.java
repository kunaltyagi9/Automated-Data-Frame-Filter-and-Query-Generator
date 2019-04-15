import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;




public class ReadJsonFile extends JFrame implements ActionListener{
    
	JTable table;
	JButton b1;
	JTextArea tarea;
	JScrollPane scrollableTextArea;

    public ReadJsonFile() {
    	super("Json File Data in the form of Table");

        b1 = new JButton("Read File");
        add(b1, BorderLayout.SOUTH);
        
        //table.setPreferredScrollableViewportSize(new Dimension(700, 70));
        //table.setFillsViewportHeight(true);
        
        //add(table, BorderLayout.CENTER);
        
        tarea = new JTextArea(410, 410);
        
        scrollableTextArea = new JScrollPane(tarea); 
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setBackground(Color.WHITE); 

        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        scrollableTextArea.setBounds(20,50,650,430);
        getContentPane().add(scrollableTextArea);  
        
        b1.addActionListener(this);

        setSize(700,600);
        setVisible(true);
        setLocation(600,150);
        
    }
    
    public void actionPerformed(ActionEvent ae){

    	try{
    		File file = null;
    	
    		JFileChooser chooser = new JFileChooser("C:/Users/768970/Desktop/Databases");
			
    		chooser.setAcceptAllFileFilterUsed(false); 
    		FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .json files", "json"); 
    		chooser.addChoosableFileFilter(restrict);

    		int result = chooser.showOpenDialog(table);
    		if(result == JFileChooser.APPROVE_OPTION) {
    			file = chooser.getSelectedFile();
		
    			BufferedReader br = new BufferedReader(new FileReader(file));
    			String everything ;
    			try {
        		
        		
    				StringBuilder sb = new StringBuilder();
    				String line = br.readLine();

    				while (line != null) {
    					sb.append(line);
    					sb.append(System.lineSeparator());
    					line = br.readLine();
        	       
    				}
    				everything = sb.toString();
    			} finally {
    				br.close();
    			}
   
    			tarea.setText(everything);
			
			
			
			
    		}else{
    			JOptionPane.showMessageDialog(null, "Operation is CANCELLED :(");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
	}			


    public static void main(String[] args) {
        new ReadJsonFile();
    }
}
