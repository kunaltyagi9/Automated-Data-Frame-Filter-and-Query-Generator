import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadFile extends JFrame implements ActionListener{

	JFileChooser fc;
	JFrame frame;
	JTextArea tarea;
	JButton readButton;
	
    ReadFile(){
        fc = new JFileChooser();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tarea = new JTextArea(10, 10);
        readButton = new JButton("OPEN FILE");
        
        
        JScrollPane scrollableTextArea = new JScrollPane(tarea); 
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
         
        
        readButton.setBackground(Color.BLACK);
        readButton.setForeground(Color.WHITE);

        frame.getContentPane().add(scrollableTextArea);  
        frame.getContentPane().add(tarea, BorderLayout.CENTER);
        frame.getContentPane().add(readButton, BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);
        
     
        frame.setSize(700,600);
        frame.setLocation(600,200);
        frame.getContentPane().setBackground(Color.WHITE);
        
        readButton.addActionListener(this);
       
    }
    
    public void actionPerformed(ActionEvent ae){
    	int returnVal = fc.showOpenDialog(frame);
    	if (returnVal == JFileChooser.APPROVE_OPTION) {
    		File file = fc.getSelectedFile();
    		try {
    			BufferedReader input = new BufferedReader(new InputStreamReader(
    					new FileInputStream(file)));
    			tarea.read(input, "READING FILE :-)");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else {
    		System.out.println("Operation is CANCELLED :(");
    	}
    }
    
    public static void main(String[] args){
        new ReadFile();
    }
}

