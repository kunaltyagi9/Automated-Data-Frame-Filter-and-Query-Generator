import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ReadTextFile extends JFrame implements ActionListener{
	
	JTable table;
	JButton button;
	JScrollPane pane;
	public ReadTextFile() {
		String[] defaultCols = {"COL1", "COL2", "COL3", "COL4", "COL5", "COL6"};
		DefaultTableModel model = new DefaultTableModel(defaultCols, 0);
		table = new JTable(model);
		button = new JButton("Get File");
	
		pane = new JScrollPane(table);
        
		add(pane);
		add(button, BorderLayout.SOUTH);
		
		setSize(600,500);
		setLocation(600,200);
		setVisible(true);
		
		pane.setBackground(Color.WHITE);
		table.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		
		button.addActionListener(this);
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
	}
	
	private DefaultTableModel createModel(File file) {
		DefaultTableModel model = null;

		try {
		
			BufferedReader txtReader = new BufferedReader(new FileReader(file));
			String header = txtReader.readLine();
			System.out.println(header);
			model = new DefaultTableModel(header.split("\\|"),0);
			String line;
			while ((line = txtReader.readLine()) != null) {
				model.addRow(line.split("\\|"));
				
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return model;
	}
	
	public void actionPerformed(ActionEvent ae){
		
		//---------------Choose a file and restrict the extension to .txt ---------------------------
		JFileChooser chooser = new JFileChooser("C:/Users/768970/Desktop/Databases");
		chooser.setAcceptAllFileFilterUsed(false); 
		FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt"); 
    	chooser.addChoosableFileFilter(restrict);
    	
    	
		int result = chooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			DefaultTableModel model = createModel(file);
			table.setModel(model);
		}
		
		
		
		
	}

	public static void main(String[] args) {
		new ReadTextFile();
	}

}