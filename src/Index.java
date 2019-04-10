import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Index extends JFrame implements ActionListener{
	
	JLabel l1;
	JMenuBar mb1;
	JMenu m1,m2,m3,m4;
	JMenuItem i2,i3,i4,i5,i6,i7,i8,i9;
	
	Index(){
		super("Oracle Forms");
		
		
		mb1 = new JMenuBar();
		m1 = new JMenu("File");
		m2 = new JMenu("Modify");
		m3 = new JMenu("Utilities");
		m4 = new JMenu("Exit");
		
		
		i9 = new JMenuItem("Read File");

		
		i2 = new JMenuItem("Insert");
		i3 = new JMenuItem("Update");
		i4 = new JMenuItem("Delete");
		
		i5 = new JMenuItem("Notepad");
		i6 = new JMenuItem("Calculator");
		i7 = new JMenuItem("Web Browser");
		
		
		i8 = new JMenuItem("Exit");
		
		m1.add(i9);
		
		m2.add(i2);
		m2.add(i3);
		m2.add(i4);
		
		m3.add(i5);
		m3.add(i6);
		m3.add(i7);
		
		m4.add(i8);
		
		
		
		mb1.add(m1);
		mb1.add(m2);
		mb1.add(m3);
		mb1.add(m4);
		add(mb1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Images/indexImage.jpg"));
		Image image2 = i1.getImage().getScaledInstance(950, 850,Image.SCALE_DEFAULT);
		l1 = new JLabel(new ImageIcon(image2));
		
				
		
		setLayout(null);
		
		mb1.setBounds(0,10,1950,30);
		add(mb1);
		
		l1.setBounds(15,10,950,1050);
		add(l1);
		
		
		setSize(1950,1050);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		
		mb1.setBackground(Color.orange);
		i2.setBackground(Color.WHITE);
		i3.setBackground(Color.WHITE);
		i4.setBackground(Color.WHITE);
		i5.setBackground(Color.WHITE);
		i6.setBackground(Color.WHITE);
		i7.setBackground(Color.WHITE);
		i8.setBackground(Color.WHITE);
		i9.setBackground(Color.WHITE);
		
		i5.addActionListener(this);
		i6.addActionListener(this);
		i9.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		String msg = ae.getActionCommand();
		
		if(ae.getSource()==i9){
			new ReadFile().setVisible(true);
		}else if(ae.getSource()==i5){
			try{
				Runtime.getRuntime().exec("notepad.exe");
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(msg.equals("Calculator")){
			try{
				Runtime.getRuntime().exec("calc.exe");
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(msg.equals("Web Browser")){
					
		}else if(msg.equals("Exit")){
			System.exit(0);
		}
	}
	
	public static void main(String[] args){
		new Index();
	}

}
