import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ReadTextFile extends JFrame implements ActionListener{
	
	JTable table1, table2;
	JButton button1,button2,button3;
	JScrollPane pane, pane2;
	String header="";
	JCheckBox c[];
	ArrayList<Integer> arr=new ArrayList<Integer>();
	Panel panel;
	
	ArrayList<String> list = new ArrayList<String>();
	
	public ReadTextFile() {

		
	//	DefaultTableModel model = new DefaultTableModel(defaultCols, 0);
		
		
		button1 = new JButton("Get File");
		button2 = new JButton("Print Table");
		button3 = new JButton("Back");
	
		pane = new JScrollPane(table1);
        
		setLayout(null);
		
		pane.setBounds(10,10,560,400);
		add(pane);
	
		button1.setBounds(10,420,250,30);
		add(button1);
		
		button3.setBounds(320,420,250,30);
		add(button3);
		
		setSize(600,500);
		setLocation(600,200);
		setVisible(true);
		
		getContentPane().setBackground(Color.WHITE);
				
		button1.addActionListener(this);
		button1.setBackground(Color.BLACK);
		button1.setForeground(Color.WHITE);
		
		button2.addActionListener(this);
		button2.setBackground(Color.BLACK);
		button2.setForeground(Color.WHITE);
		
		button3.addActionListener(this);
		button3.setBackground(Color.BLACK);
		button3.setForeground(Color.WHITE);
	}
	
	
	public void actionPerformed(ActionEvent ae){

		if(ae.getSource()==button1){
			
			DefaultTableModel model = null;
		
			//---------------Choose a file and restrict the extension to .txt ---------------------------
			JFileChooser chooser = new JFileChooser("C:/Users/768970/Desktop/Databases");
			chooser.setAcceptAllFileFilterUsed(false); 
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt"); 
			chooser.addChoosableFileFilter(restrict);
    	
    	
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				
				try {
					
					BufferedReader txtReader = new BufferedReader(new FileReader(file));
					this.header = txtReader.readLine();
				
					askUser(this.header);
				
					System.out.println(this.header);
					model = new DefaultTableModel(this.header.split("\\|"),0);
	
					String line;
					while ((line = txtReader.readLine()) != null) {
						model.addRow(line.split("\\|"));
					
					}
			
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				table1 = new JTable(model);
				table1.setModel(model);
	
			}

		}
		if(ae.getSource()==button2){
			
			
			int count = 0;
			//splitting the heading 
			String header1[] = this.header.split("\\|");
			
			//calculating the count, number of columns selected
			for(int i = 0 ; i < header1.length ; i++){
				if(c[i].isSelected()){
					count++;
				}
			}
			
			//adding the selected columns in an array list
			for(int i = 0 ; i < header1.length ; i++){
				if(c[i].isSelected()){
					arr.add(i);
				}
			}
			

			for(int i = 0 ;i < arr.size() ; i++){
				System.out.println(arr.get(i));
			}

			
			//adding the selected columns in the array list
			for(int i = 0 ; i < table1.getModel().getRowCount() ; i++){
				for(int k = 0 ; k < table1.getModel().getColumnCount() ; k++){
					if(arr.contains(k)){
						list.add((String) table1.getModel().getValueAt(i,k)); 
					}
				}
			}

			int r=table1.getModel().getRowCount();
			int col=list.size()/(r);
			
			String rows[][]=new String[r][col];

			int r1=0,c1=0;
			
			
			//adding the data of the selected columns in the 2-D array
			for(int k1=0 ; k1 < list.size() ; k1++){
				for(int j1 = 0 ; j1 < col ; j1++){
					rows[r1][c1]=list.get(k1);
					c1++;
					k1++;
				}
				k1--;
				r1++;
				c1=0;
			}

			String heading[] = new String[col];
			int k2 = 0;

			//adding the selected columns in the array
			for(int j = 0 ; j < arr.size() ; j++){
				heading[j] = header1[arr.get(j)];
			}				

			//adding the rows and columns in the table
			DefaultTableModel model1 = new DefaultTableModel(rows, heading);
			
			table2 = new JTable(model1);

			panel.setVisible(false);
			pane2 = new JScrollPane(table2);
    		
			setLayout(null);
			
			repaint();
			
			pane2.setBounds(10,10,560,400);
			add(pane2); 
			setVisible(true);
			
		}else if(ae.getSource()==button3){
			new ReadFile();
		}
	}
	
	public void askUser(String header){
		
		panel = new Panel();
		
		String header1[] = header.split("\\|");
		c = new JCheckBox[header1.length];
		
		setLayout(null);
		
		for(int i = 0 ; i < header1.length ; i++){
			c[i] = new JCheckBox(header1[i]);
			panel.add(c[i]);
		}

		button1.setBounds(40,420,150,30);
		add(button1);
		
		button2.setBounds(210,420,150,30);
		add(button2);
		
		button3.setBounds(380,420,150,30);
		add(button3);
		
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(header1.length,1));
		panel.setBounds(50,10,400,400);
		add(panel);
		
		pane.setVisible(false);
		this.repaint();
		setVisible(true);
		
		
	}
	

	public static void main(String[] args) {
		new ReadTextFile();
	}

}