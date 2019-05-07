import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.*;



public class ReadJsonFile extends JFrame implements ActionListener{
    
	JTable table;
	JButton button1,button2;
	JScrollPane pane;
	JLabel l1;

    public ReadJsonFile() {
    	super("Showing Json File Data in the form of Table");

    	l1 = new JLabel("Choose file by clicking on \"Read File\" button");
    	
    

        button1 = new JButton("Read File");
        button2 = new JButton("Back");
        
    	setLayout(null);
    	
    	l1.setBounds(30,30,300,20);
		add(l1);
		
    	
    	button1.setBounds(10,420,270,30);
        add(button1);

    	button2.setBounds(300,420,270,30);
        add(button2);



        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);
        
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.addActionListener(this);

        
        getContentPane().setBackground(Color.WHITE);

        setSize(600,500);
        setVisible(true);
        setLocation(600,200);

    }
    
    public void actionPerformed(ActionEvent ae){

    	
    	if(ae.getSource()==button1){
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
    				String everything = "";
    				try {
        		
        		
    					StringBuilder sb = new StringBuilder();
    					String line = br.readLine();

    					while (line != null) {
    						sb.append(line);
    						sb.append(System.lineSeparator());
    						line = br.readLine();
        	       
    					}
    					everything = sb.toString();
    				 
    				}finally {
    					br.close();
    				}

    				String e="",everything2="";
    				JSONObject jsonObject = new JSONObject(everything); 
    			
    				Set<String> keys =jsonObject.keySet();
    				for(String key:keys) {    			   
    					everything2 = jsonObject.get(key).toString().substring(2);
    			  
    					e=everything2.substring(0,everything2.length()-2);
    				}
    	
    				System.out.println("Pass 1");
    			
    				String e1 = e.replace("},{","`");
    		
    				String e2[] = e1.split("`");
    			
    				ArrayList<String> al1=new ArrayList<String>();
    				StringTokenizer st[]=new StringTokenizer[e2.length];
    				int i;
    				for(i=0 ; i < e2.length ; i++){

    					st[i]=new StringTokenizer(e2[i],"`,:,\"");	
    					while(st[i].hasMoreTokens()){		
    						String s11=st[i].nextToken();
    						al1.add(s11);

    					}
        			
    				}
    				
    				System.out.println("pass 2");
    		
    				String col[]=new String[al1.size()/2/i];
    		
    				int k=0;
    				for(int j = 0 ; j < col.length*2 ; j++){
    					if(j%2==0){
    						col[k]=al1.get(j);
    						k++;
    					}
    				
    				}
    				
    				System.out.println("pass 3");
    				int l=0,m=0;
    				int var=(col.length*2);
    			
    				String rows[][]=new String[i][(al1.size()/(i*2))];
    			
    				System.out.println(al1.size());
    			
    				for(int j=0;j<al1.size();++j){	
    				
    					if(j%2!=0 && j%var!=0){
    					
    						rows[m][l]=al1.get(j);
    						l++;
    					}
    				
    					if(j%var==0&&j!=0){
    						l=0;
    						m++;
    						rows[m][l]=al1.get(j);
    					}
    				}
    				
    				for(int j = 0 ; j < rows.length ; j++){
    					for(int k1 = 0 ; k1 < rows[j].length ; k1++){
    						System.out.print(rows[j][k1]);
    					}
    					System.out.println();
    				}

    				table = new JTable(rows, col);
    				pane = new JScrollPane(table);

    				button1.setEnabled(false);
    				l1.setVisible(false);
    				
    				this.repaint();
    			

    				setSize(800,600);
    				button1.setBounds(100,520,270,30);
    				button2.setBounds(400,520,270,30);
    				pane.setBounds(10,10,760,500);
    				add(pane); 
    				setVisible(true);
    			
    			}else{
    				JOptionPane.showMessageDialog(null, "Operation is CANCELLED :(");
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}else if(ae.getSource()==button2){
    		new ReadFile();
    		this.setVisible(false);
    	}
    	
	}		
    


    public static void main(String[] args) {
        new ReadJsonFile();
    }
}
