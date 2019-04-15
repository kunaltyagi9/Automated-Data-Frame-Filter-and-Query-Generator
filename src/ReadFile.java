import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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
        	
        	//Restrict the extension of the file
        	FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xml files", chooseFile); 
        	j.addChoosableFileFilter(restrict);
        	
        	
        	int r = j.showOpenDialog(null); 
            
            if (r == JFileChooser.APPROVE_OPTION) { 
            	//Opening the Raw XML File
            	File file = j.getSelectedFile();
            	FileInputStream ins = null;
                FileOutputStream outs = null;
            	try{
            		//Parsing of XML File          
                	FileWriter file2=new FileWriter("C:/Users/768970/Desktop/Databases/AllDatabase1.txt"); 
                	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    try {
                    	DocumentBuilder builder = factory.newDocumentBuilder();
                    	Document doc = builder.parse(file);

                    	NodeList nodes = doc.getElementsByTagName("*");
                    	
                    	//----------- Getting the child nodes to make columns -----------------
                    	Set s = new LinkedHashSet();
                    	
                    	for (int i = 0 ; i < nodes.getLength() ; i++){
                    			String str = null;
                   				if(nodes.item(i) instanceof Element)
                   				{
                   					Element ele = (Element) nodes.item(i);
                   					str = ele.getNodeName();
                   					s.add(str);
                   				}
                   				
                   				// ------------- Copying the content after Parsing ----------------
                   				
                    			String str1 = nodes.item(i).getTextContent();
                    			String result = str1.trim();
                    			file2.write(result);
             
                    	}
                    	//System.out.println(s);
                    	file2.close();
                    	
                    
                    	
                    	//Fetching the data from AllDatabase.txt File and pasting it on TextArea (GUI) 
                    	
                    	BufferedReader br = new BufferedReader(new FileReader("C:/Users/768970/Desktop/Databases/AllDatabase1.txt"));
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
  
                    	
                    	
                    	
                        
                        
                    
                    	//-------------------- Making heading of JTable by putting the data in an array -------------------------------------
                    	
                    	String[] columnheading = new String[s.size()];
                    	int i = 0;
                    	for ( Object o : s )
                    	{
                    		columnheading[i] = (String)o;
                    		//System.out.print(columnheading[i]);
                    		i++;
                    	}
                    	
                    	
                    	//Object[] rowdata = everything.split("\n");
                    	//table1 = new JTable((Object[][]) rowdata, columnheading);
                    	
                    	System.out.println(everything);
                    	
                    	
                  
                    	
                    	
                    }catch (Exception e) {
                    	e.printStackTrace();
                    }
                    finally{
                    	file2.close();
                    }
                    
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            	
            }else{
            	JOptionPane.showMessageDialog(null, "Operation is CANCELLED :(");   	
            }
        	
        	
        	
        	
        }
        
        
                
         

        
    }
    
    public static void main(String[] args){
    	new ReadFile();
    }

  
}

