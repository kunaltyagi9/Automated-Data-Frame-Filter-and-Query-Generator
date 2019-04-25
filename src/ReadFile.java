import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;

import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class ReadFile extends JFrame implements ActionListener{

	JLabel l1;
	
	JFrame frame;
	JTextArea tarea;
	JButton readButton;
	JComboBox cb;
	JScrollPane scrollableTextArea;
	
    ReadFile(){
    	
    	l1 = new JLabel("Choose File : ");

        tarea = new JTextArea(410, 410);
        readButton = new JButton("OPEN FILE");
        
        String files[]={"txt","csv","json","xml"};        
        cb= new JComboBox(files);    
        cb.setBackground(Color.WHITE);
        
        scrollableTextArea = new JScrollPane(tarea); 
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setBackground(Color.WHITE); 
        
        readButton.setBackground(Color.BLACK);
        readButton.setForeground(Color.WHITE);

        setLayout(null);
        
        l1.setBounds(40,10,100,30);
        add(l1);
        
        cb.setBounds(160,10,200,30);
        add(cb);
        
        readButton.setBounds(20,500,100,30);
        add(readButton);
        
        getContentPane().setBackground(Color.WHITE);

        scrollableTextArea.setBounds(20,50,650,430);
        getContentPane().add(scrollableTextArea);  
        
        setVisible(true);
        
     
        setSize(700,600);
        setLocation(600,200);

        readButton.addActionListener(this);
       
    }
    
    public void actionPerformed(ActionEvent ae){
    	
    	String chooseFile = (String)cb.getSelectedItem();
    	JTable table1;
    	
    	JFileChooser j = new JFileChooser("C:/Users/768970/Desktop/Databases"); 
        j.setAcceptAllFileFilterUsed(false); 

        if(chooseFile.equals("txt")){
        	new ReadTextFile();
        	this.setVisible(false);
        	
        }else if(chooseFile.equals("csv")){
        	new ReadCSVFile();
        	this.setVisible(false);
        	
        }else if(chooseFile.equals("json")){
        	new ReadJsonFile();
        	this.setVisible(false);
        
        
        }else if(chooseFile.equals("xml")){
        	new ReadXMLFile();
        	this.setVisible(false);
        	
        }

    }
    
    public static void main(String[] args){
    	new ReadFile();
    }

  
}

