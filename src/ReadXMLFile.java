import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import org.json.JSONObject;
import org.json.XML;

public class ReadXMLFile extends JFrame implements ActionListener{


	JButton button;
	JTextArea tarea;
	JScrollPane scrollableTextArea,pane;
	JTable table;
	
	public ReadXMLFile() {
		
		button = new JButton("Get File");
		tarea = new JTextArea(410, 410);
		
		
		scrollableTextArea = new JScrollPane(tarea); 
	    scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollableTextArea.setBackground(Color.WHITE); 
        
		//add(scrollableTextArea, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		
		setSize(600,500);
		setLocation(600,200);
		setVisible(true);

		setBackground(Color.WHITE);
				
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		button.addActionListener(this);
	
	}
	public void actionPerformed(ActionEvent ae){
		
		int PRETTY_FACTOR=4;
		String jsonFileName = "C:/Users/768970/Desktop/Databases/temp.json";
        try {        
        	
        	File file = null;
    		JFileChooser chooser = new JFileChooser("C:/Users/768970/Desktop/Databases");
			
    		chooser.setAcceptAllFileFilterUsed(false); 
    		FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xml files", "xml"); 
    		chooser.addChoosableFileFilter(restrict);

    		int result = chooser.showOpenDialog(this);
    		if(result == JFileChooser.APPROVE_OPTION) {
    			file = chooser.getSelectedFile();
    			
    	        try {
    	            
    	            InputStream inputStream = new FileInputStream(file);
    	            StringBuilder builder = new StringBuilder();
    	            int ptr;
    	            while ((ptr = inputStream.read()) != -1) {
    	                builder.append((char) ptr);
    	            }

    	            String xml = builder.toString();
    	            JSONObject jsonObj = XML.toJSONObject(xml);
    	            //System.out.print(jsonObj);
    	            FileWriter fileWriter = new FileWriter(jsonFileName);

    	            // Always wrap FileWriter in BufferedWriter.
    	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    	            bufferedWriter.write(jsonObj.toString(PRETTY_FACTOR));
    	            bufferedWriter.close();
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    			
    			
    			// ---------------- Change it to string --------------------------
    			
    			String fileName1 = "C:/Users/768970/Desktop/Databases/temp.json";

    			BufferedReader br = new BufferedReader(new FileReader(fileName1));
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
    			    e=everything2.substring(8,everything2.length()-3);
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
    			System.out.println(al1);
    		
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
    			
    			int l=0,m=0;
    			int var=(col.length*2);
    			System.out.println(var);
    			
    			String rows[][]=new String[i][(al1.size()/(i*2))];
    		
    			for(int j=0;j<al1.size();++j)
    			{	
    				System.out.println(j);
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
    			add(pane); 
    			setVisible(true);
    
    			
    		}else{
    			JOptionPane.showMessageDialog(null, "Operation is CANCELLED :(");
    		}
    			
        }catch(Exception e){
        	e.printStackTrace();
        }

    		
    	
	}
	
	public static void main(String[] args){
		new ReadXMLFile();
	}
}
