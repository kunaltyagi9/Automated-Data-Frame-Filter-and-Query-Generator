import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Insert extends JFrame{
	
	JButton b1;
	JTextArea a1;
	
	Insert(){
		super("Insert Data in Table");
		
		b1 = new JButton("Show Tables");
		a1 = new JTextArea("Tables in Database");
		
		
		setLayout(null);
		
		b1.setBounds(50,20,200,30);
		add(b1);
		
		a1.setBounds(50,60,300,100);
		add(a1);
		
		getContentPane().setBackground(Color.WHITE);
		
		setSize(700,800);
		setVisible(true);
		setLocation(700,100);
		
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
	}
	
	
	public static void main(String[] args){
		new Insert();
	}

}
