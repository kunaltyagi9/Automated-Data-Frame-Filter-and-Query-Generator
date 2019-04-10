import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame{
	
	JLabel l1,l2,l3;
	JTextField t1,t2;
	JButton b1,b2;
	JCheckBox c1;
	 
	login(){
		
		super("Login Page");
		l1 = new JLabel("Username");
		t1 = new JTextField();
		
		l2 = new JLabel("Password");
		t2 = new JTextField();
		
		b1 = new JButton("Login");
		b2 = new JButton("Reset");
		
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
		
		t2.setBounds(250,80,200,20);
		add(t2);
		
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
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
	}
	
	public static void main(String[] args){
		new login();
	}

}
