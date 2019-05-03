import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class ReadTables extends JFrame implements ActionListener{

	JButton button1,button2, button3, button4, button5;
	ArrayList<String> list = new ArrayList<String>(), list2 = new ArrayList<String>(), list3 = new ArrayList<String>();
	Panel panel,panel2[],panel3[],panel4[];
	JCheckBox[] c,c2;
	JTable table,newJointTable;
	String row[][];
	JScrollPane pane[],pane1,newPane;
	ArrayList<Integer> totalColumns = new ArrayList<Integer>();
	ArrayList<String> getSelectedColumns = new ArrayList<String>(), insertColumns = new ArrayList<String>(), getTableName = new ArrayList<String>(), getTable = new ArrayList<String>(), getColumn = new ArrayList<String>();
	String gcolumn[][],newTable[][];
	JLabel tableName[], tname[];
	int var;
	int countColumns[];
	String newColumn[];

	
	ReadTables(){
		
		button1 = new JButton("Show Tables");
		button1.setBackground(Color.BLACK);
		button1.setForeground(Color.WHITE);
		
		button2 = new JButton("Show Data");
		button2.setBackground(Color.BLACK);
		button2.setForeground(Color.WHITE);
		
		button3 = new JButton("Select Columns");
		button3.setBackground(Color.BLACK);
		button3.setForeground(Color.WHITE);
		

		button4 = new JButton("Form Table");
		button4.setBackground(Color.BLACK);
		button4.setForeground(Color.WHITE);
		
		button5 = new JButton("Download");
		button5.setBackground(Color.BLACK);
		button5.setForeground(Color.WHITE);


		setLayout(null);
		
		button1.setBounds(40,20,200,30);
		add(button1);
		
		button2.setBounds(270,20,200,30);
		add(button2);
		
		button3.setBounds(500,20,200,30);
		add(button3);

		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);

		
		setSize(770,600);
		setVisible(true);
		setLocation(600,70);
		getContentPane().setBackground(Color.WHITE);
	}
	
	public void actionPerformed(ActionEvent ae){

		if(ae.getSource()==button1){
			
			try{
				conn c1 = new conn();
			
				//selecting the tables created after the given date
				String q = "SELECT object_name FROM dba_objects WHERE object_type = 'TABLE' AND owner = 'SYSTEM' AND owner = 'SYSTEM' AND created >= to_date('01-MAY-2019','DD-MON-YYYY')";
				
				ResultSet rs = c1.s.executeQuery(q);
				 
				//Adding all the tables in the list
				while(rs.next()){		
					list.add(rs.getString("OBJECT_NAME"));
				}
				
				//creating check box to select tables
				panel = new Panel();
				c = new JCheckBox[list.size()];
				
				setLayout(null);
				
				for(int i = 0 ; i < list.size() ; i++){
					c[i] = new JCheckBox(list.get(i));
					panel.add(c[i]);
				}
				
				panel.setBackground(Color.WHITE);
				panel.setLayout(new GridLayout(list.size(),1));
				panel.setBounds(50,80,400,400);
				add(panel);
				
				button1.setEnabled(false);
			
				this.repaint();
				setVisible(true);
		
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(ae.getSource()==button2){
			int count = 0;
			
			try{

				conn c1 = new conn();
				conn c2 = new conn();
				
				//calculating the count, number of tables selected
				for(int i = 0 ; i < list.size() ; i++){
					if(c[i].isSelected()){
						count++;
					}
				}

				//adding the selected tables in an array list "list2"
				for(int i = 0 ; i < list.size() ; i++){
					if(c[i].isSelected()){
						list2.add(list.get(i));
					}
				}
				
				int columnCount;
				String str[] = new String[list2.size()], column[];
				
				//to display no. of columns in a table
				gcolumn = new String[list.size()][];
				
				pane = new JScrollPane[list2.size()];
				tableName = new JLabel[list2.size()];
				
				for(int i = 0 ; i < list2.size() ; i++){
					
					
					tableName[i] = new JLabel(list2.get(i));
					
					//fetching the data of the table selected from the database
					str[i] = "select * from "+list2.get(i)+"";
					

					ResultSet rs = c1.s.executeQuery(str[i]);
					ResultSet rs1 = c2.s.executeQuery(str[i]);
					
					ResultSetMetaData rsmd = rs.getMetaData();
					
					//calculating the no. of columns in a table
					columnCount = rsmd.getColumnCount();
					
					//creating a array of the size of columns
					column = new String[columnCount];
					
					//adding the columns in an array list
					totalColumns.add(columnCount);
	
				
					gcolumn[i] = new String[columnCount];

					for (int j = 0 ; j < columnCount ; j++) {
						column[j] = rsmd.getColumnName(j+1);	
					}
					
					//adding the no. of columns in a particular table
					for(int j = 0 ; j < columnCount ; j++){
						gcolumn[i][j] = column[j];
					}

					
					int rowCount=0,cCount = 0, rCount = 0;	
					while(rs1.next()) {
						rowCount++;		
					}	

					//getting the data from the database into a 2-d array
					row = new String[rowCount][columnCount];
					while(rs.next()) {
						
						for (int counter = 1 ; counter <= columnCount ; counter++){
							row[rCount][cCount] = rs.getString(counter);
							cCount++;
						}
						cCount = 0;
						rCount++;
						
					}
					
					//putting the data into the table
					table = new JTable(row,column);
					table.setBackground(Color.WHITE);
				
					

					pane[i] = new JScrollPane(table);
		    		pane[i].setBackground(Color.WHITE);
					
					setLayout(null);
					
					repaint();
					
					panel.setVisible(false);
					
					if(i<2){
						tableName[i].setBounds(40+i*350,70,100,20);
						pane[i].setBounds(40+i*350,100,310,200);
					}else{
						tableName[i].setBounds(40+(i-2)*350,300,100,30);
						pane[i].setBounds(40+(i-2)*350,330,310,200);
					}
					
					
					button2.setEnabled(false);;
					pane[i].setBackground(Color.WHITE);
					add(tableName[i]);
					add(pane[i]); 
					setVisible(true);
					
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(ae.getSource()==button3){
			int count = 0;
			
			//calculating the total number of columns
			for(int i = 0 ; i < list2.size() ; i++){
				for(int j = 0 ; j < totalColumns.get(i) ; j++){
					count++;
				}
			}
			

			var = count;
			int counter = 0;
			
			//creating the check box for total no of columns
			c2 = new JCheckBox[count];
			
			panel4 = new Panel[list2.size()];
			panel3 = new Panel[list2.size()];
			tname = new JLabel[list2.size()];
			for(int i = 0 ; i < list2.size() ; i++){
				
				panel2 = new Panel[totalColumns.get(i)];
				panel3[i] = new Panel();
				panel4[i] = new Panel();
				
//				list4.add(list2.get(i));
				
				tname[i] = new JLabel(list2.get(i));
				panel4[i].add(tname[i]);
				
				//adding the total columns of a table in a panel
				for(int j = 0 ; j < totalColumns.get(i) ; j++){
					c2[counter] = new JCheckBox(gcolumn[i][j]);
					insertColumns.add(gcolumn[i][j]);
					
					
					panel2[j] = new Panel();
					panel2[j].setLayout(new FlowLayout());
					
					panel2[j].setBounds(10,20*(i+50),130,150);
					panel2[j].setBackground(Color.WHITE);
					
					panel2[j].add(c2[counter]);
					
					panel3[i].add(panel2[j]);
					counter++;
				}
				
			
				//button4.setEnabled(false);
				button3.setVisible(false);
				
				//To display table name
				panel4[i].setLayout(new GridLayout(1,1));
				panel4[i].setBackground(Color.WHITE);
				panel4[i].setBounds(50+i*150,600,130,40);
				
				add(panel4[i]);
				
				//to display columns
				panel3[i].setLayout(new GridLayout(totalColumns.get(i)+1,1));
				panel3[i].setBackground(Color.WHITE);
				setSize(800,900);
				panel3[i].setBounds(50+i*150,650,130,150);
				
				add(panel3[i]);
				
			
			}
			setLayout(null);
			
			//button5 -- started
			class handler2 implements ActionListener{
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, "Data Downloaded");
					setVisible(false);
					
					Date date = new Date();
					
					StringBuilder builder2 = new StringBuilder();
					for(int i = 0; i < newColumn.length; i++){
						   
					      builder2.append(newColumn[i]+"");
					      if(i < newColumn.length - 1)
					         builder2.append("|");
					  
					}
					
					 builder2.append("\n");
					
					StringBuilder builder = new StringBuilder();
					for(int i = 0; i < newTable.length; i++){
					   for(int j = 0; j < newTable.length; j++){
						  
						   if(newTable[i][j]!=null){
						  
							  builder.append(newTable[i][j]+"");
							  if(j < newTable.length - 1)
								  builder.append("|");
					   }
					   }
					   builder.append("\n");
					}
					
					BufferedWriter writer = null;
					try {
						writer = new BufferedWriter(new FileWriter("C:/Users/768970/Desktop/Databases/download.txt"));
						writer.write(builder2.toString());
						writer.write(builder.toString());
						writer.close();
					}catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
				
			
			//button 4 -- started
			class handler1 implements ActionListener{
				public void actionPerformed(ActionEvent e){
					
					conn c1 = new conn();
					
					
					//Set Visible false to repaint the frame
					table.setVisible(false);
					panel.setVisible(false);
					button1.setVisible(false);
					button2.setVisible(false);
					button3.setVisible(false);
					button4.setVisible(false);
				
					
					
					for(int i = 0 ; i < list2.size() ; i++){
						panel3[i].setVisible(false);
						pane[i].setVisible(false);
						tableName[i].setVisible(false);
					}
				
			
					//Getting the columns selected by the user
					int k = 0;
					for(int i = 0 ; i < var ; i++){
						if(c2[i].isSelected()){
							getSelectedColumns.add(insertColumns.get(i));								
							k++;
						}
					}
					
					//Made an array to make a count the no. of columns selected by the user from each table
					countColumns  = new int[10];
				
					int k1 = 0, k2=0,count=0, counter2 = 0;
					boolean flag = false;
					for(int i = 0 ; i < list2.size() ; i++){
						for(int j = 0 ; j < totalColumns.get(i) ; j++){
							if(getSelectedColumns.get(k1).equals(insertColumns.get(k2))){
							
								getTable.add(list2.get(i));
								getColumn.add(getSelectedColumns.get(k1));									
								count++;
								++k1;
							}
							if(getSelectedColumns.size()==k1){
								flag = true;
								break;
							}
							
							k2++;
							
						}
						if(count!=0){

							countColumns[counter2] = count;
							counter2++;
							count = 0;	
						}
						if(flag){
							break;
						}
					
					}
	
					int counter = 0,counter1 = 0;
					newTable = new String[10][10];
					newColumn = new String[getColumn.size()];
					int cCount = 0, rCount = 0, countCol = 0;
					try{
						
					
						for(int i = 0 ; i < getTable.size() ; i++){

							for(int j = 0 ; j < countColumns[i] ; j++){
							
								String q = "select "+getColumn.get(counter)+"  from "+getTable.get(counter1)+""; //sjksajdskadkjsdsdsadjsjkdasdjksas
								System.out.println(q);
								
								ResultSet rs = c1.s.executeQuery(q);
								
								while(rs.next()){
										newTable[cCount][rCount] = rs.getString(1);
										cCount++;
								}
								cCount = 0;
								rCount++;
								
								newColumn[countCol] = getColumn.get(counter);
								countCol++;
								counter++;
								counter1++;
								}
	
						}
								
						newJointTable = new JTable(newTable ,newColumn);
						newJointTable.setBackground(Color.WHITE);
					
						newPane = new JScrollPane(newJointTable);
			    		newPane.setBackground(Color.WHITE);
						
			    		repaint();
						setLayout(null);
						
						newPane.setBounds(10,10,550,400);
						add(newPane);
						
						button5.setBounds(10,420,100,30);
						add(button5);
						
						handler2 thehandler2 =new handler2();
						button5.addActionListener(thehandler2);
						
					}catch(Exception ex){
						ex.printStackTrace();
					}

					setSize(600,500);
					repaint();
					setVisible(true);
				}
			}

			button4.setBounds(550,820,150,30);
			add(button4);
			handler1 thehandler1 =new handler1();
			
			button4.addActionListener(thehandler1);

			
			this.repaint();
			setVisible(true);

		}
	}
	
	public static void main(String[] args){
		new ReadTables();
	}
}
