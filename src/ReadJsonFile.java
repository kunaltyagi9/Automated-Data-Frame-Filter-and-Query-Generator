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
	JButton button;
	JTextArea tarea;
	JScrollPane scrollableTextArea,pane;

    public ReadJsonFile() {
    	super("Showing Json File Data in the form of Table");


        button = new JButton("Read File");
        add(button, BorderLayout.SOUTH);

        tarea = new JTextArea(410, 410);
        
        scrollableTextArea = new JScrollPane(tarea); 
        scrollableTextArea.setBackground(Color.WHITE); 

        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        button.addActionListener(this);

        setSize(800,600);
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
    	
    			
    			String e1 = e.replace("},{","`");
    		
    			String e2[] = e1.split("`");
    			
    			ArrayList<String> al1=new ArrayList<String>();
    			StringTokenizer st[]=new StringTokenizer[e2.length];
    			int i;
    			for(i=0;i<e2.length;++i)
    			{

        			st[i]=new StringTokenizer(e2[i],"`,:,\"");	
        			while(st[i].hasMoreTokens())
        			{	
        				String s11=st[i].nextToken();
        				al1.add(s11);

        			}
        			
    			}
    		
    			String col[]=new String[al1.size()/2/i];
    		
    			int k=0;
    			for(int j=0;j<col.length*2;++j)
    			{
    				if(j%2==0)
    				{
    					col[k]=al1.get(j);
    					k++;
    				}
    				
    			}
    			System.out.println("Columns");
    			for(int j=0;j<col.length;++j)
    			{
    				System.out.println(col[j]);
    			}
    			int l=0,m=0;
    			int var=(col.length*2);
    			
    			String rows[][]=new String[i][(al1.size()/(i*2))];
    			
    			
    			for(int j=0;j<al1.size();++j)
    			{	
    				
    				if(j%2!=0 && j%var!=0)
    				{
    					
    					rows[m][l]=al1.get(j);
    					l++;
    				}
    				
    				if(j%var==0&&j!=0)
    				{
    					l=0;
    					m++;
    					rows[m][l]=al1.get(j);
    				}
    			}

    			for(k=0;k<rows.length;++k)
    			{
    				for(l=0;l<rows[0].length;++l)
    				{
    					System.out.print(" " + rows[k][l]);
    				}
    				System.out.println();
    			}

    			table = new JTable(rows, col);

    			pane = new JScrollPane(table);
    		
    			
    			this.repaint();
    			this.add(pane, BorderLayout.CENTER); 
    			setVisible(true);
    			
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
