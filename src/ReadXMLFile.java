import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class ReadXMLFile extends JFrame implements ActionListener{


	JButton button1,button2;
	JScrollPane pane;
	JTable table;
	JLabel l1;
	
	public ReadXMLFile() {
		
		button1 = new JButton("Get File");
		button2 = new JButton("Back");

		l1 = new JLabel("Choose file by clicking on \"Get File\" button");

		setLayout(null);
		
		l1.setBounds(30,30,300,20);
		add(l1);
		
		button1.setBounds(10,420,270,30);
		add(button1);
		
		button2.setBounds(300,420,270,30);
		add(button2);

		
		setSize(600,500);
		setLocation(600,200);
		setVisible(true);

		getContentPane().setBackground(Color.WHITE);
				
		button1.setBackground(Color.BLACK);
		button1.setForeground(Color.WHITE);
		button1.addActionListener(this);
		
		button2.setBackground(Color.BLACK);
		button2.setForeground(Color.WHITE);
		button2.addActionListener(this);
	
	}
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource()==button1){
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
					
					for(i = 0 ; i < e2.length ; i++){

						st[i]=new StringTokenizer(e2[i],"`,:,\"");	
						while(st[i].hasMoreTokens()){	
							String s11=st[i].nextToken();
							al1.add(s11);

						}
					}
					
					String col[]=new String[al1.size()/2/i];
					int k=0;
					for(int j = 0 ; j < col.length*2 ; j++){
						if(j%2==0){
							col[k]=al1.get(j);
							k++;
						}
    				
					}
    			
					int l=0,m=0;
					int var=(col.length*2);
					
					String rows[][]=new String[i][(al1.size()/(i*2))];
    		
					for(int j = 0 ; j < al1.size() ; j++){	
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
    			
					table = new JTable(rows, col);
					pane = new JScrollPane(table);
    		
					l1.setVisible(false);
					this.repaint();
					setLayout(null);
					pane.setBounds(10,10,560,400);
					add(pane); 
					setVisible(true);
    
					button1.setEnabled(false);
    			
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
	
	/*
	public static void demo()
	{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document doc = builder.parse("D:\\agil\\Workspace Project\\src\\emp.xml");
			
			NodeList list = doc.getElementsByTagName("*");
			int length = list.getLength();
			for(int i=0; i<length; i++)
			{
				Node ele = (Element)list.item(i);
				if(ele.getNodeName().equals("Emp"))
					System.out.println("\nEmp ID : "+((Element) ele).getAttribute("id"));
				if(ele.getNodeName().equals("Name"))
				System.out.println("Emp Name : "+ele.getTextContent());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public static void main(String[] args){
		
		new ReadXMLFile();
	}
}
