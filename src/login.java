import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame implements ActionListener{
	
	JLabel l1,l2,l3;
	JTextField t1;
	JPasswordField p1; 
	JButton b1,b2;
	JCheckBox c1;
	 
	login(){
		
		super("Login Page");
		l1 = new JLabel("Username");
		t1 = new JTextField();
		
		l2 = new JLabel("Password");
		p1 = new JPasswordField();
		
		b1 = new JButton("Login");
		b2 = new JButton("Clear");
		
		c1 = new JCheckBox("Show Password");
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Images/avatar.png"));
		Image i2 = i1.getImage().getScaledInstance(128, 128,Image.SCALE_DEFAULT);
		l3 = new JLabel(new ImageIcon(i2));
		
		setLayout(null);
		
		l1.setBounds(150,40,100,20);
		add(l1);
		
		t1.setBounds(250,40,200,20);
		add(t1);
		
		l2.setBounds(150,80,100,20);
		add(l2);
		
		p1.setBounds(250,80,200,20);
		add(p1);
		
		c1.setBounds(250,110,200,15);
		add(c1);
		
		b1.setBounds(250,150,80,20);
		add(b1);
		
		b2.setBounds(370,150,80,20);
		add(b2);
		
		l3.setBounds(10,20,90,120);
		add(l3);
		
		setSize(500,250);
		setVisible(true);
		setLocation(700,300);
		
		getContentPane().setBackground(Color.WHITE);
		
		b1.setBackground(Color.black);
		b1.setForeground(Color.WHITE);
		
		b2.setBackground(Color.black);
		b2.setForeground(Color.WHITE);
		
		c1.setBackground(Color.white);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		try{
			conn c1 = new conn();
			System.out.println("Connected to : "+c1);
			String a = t1.getText();
			String b = p1.getText();
			
			String q = "select * from login where username = '" + a + "' and password = '" + b + "' ";
			
			ResultSet rs = c1.s.executeQuery(q);
			
			if(ae.getSource()==b1){
				if(rs.next()){		
					new Index().setVisible(true);
					this.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
					setVisible(false);
				}
			
			}else if(ae.getSource()==b2){
				t1.setText("");
				p1.setText("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new login();
	}

}
