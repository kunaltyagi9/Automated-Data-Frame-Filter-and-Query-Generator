import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;

public class Index extends JFrame implements ActionListener{
	
	JLabel l1,l2;
	JMenuBar mb1;
	JMenu m1,m3,m4;
	JMenuItem i5,i6,i7,i8,i9,i10;
	
	Index(){
		super("Reporting Tool");
		
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Images/indexImage.jpg"));
		Image image2 = i1.getImage().getScaledInstance(950, 850,Image.SCALE_DEFAULT);
		l1 = new JLabel(new ImageIcon(image2));
		
		ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("Images/file.png"));
		Image image3 = i2.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(image3);
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Images/database.png"));
		Image image4 = i4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
		ImageIcon ii5 = new ImageIcon(image4);
		
		ImageIcon ii6 = new ImageIcon(ClassLoader.getSystemResource("Images/notepadicon.png"));
		Image image5 = ii6.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
		ImageIcon ii7 = new ImageIcon(image5);
		
		ImageIcon ii8 = new ImageIcon(ClassLoader.getSystemResource("Images/calculator.png"));
		Image image6 = ii8.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
		ImageIcon ii9 = new ImageIcon(image6);
		
		ImageIcon ii10 = new ImageIcon(ClassLoader.getSystemResource("Images/web.png"));
		Image image7 = ii10.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
		ImageIcon ii11 = new ImageIcon(image7);
		
		ImageIcon ii12 = new ImageIcon(ClassLoader.getSystemResource("Images/logout.png"));
		Image image8 = ii12.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
		ImageIcon ii13 = new ImageIcon(image8);
		
		
		mb1 = new JMenuBar();
		m1 = new JMenu("File");
		m3 = new JMenu("Utilities");
		m4 = new JMenu("Logout");
		
		
		i9 = new JMenuItem("Read from File");
		i9.setIcon(i3);
		i9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		i9.setMnemonic(KeyEvent.VK_F);
		
		
		i10 = new JMenuItem("Read from Database");
		i10.setIcon(ii5);
		i10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		i10.setMnemonic(KeyEvent.VK_D);
		
		i5 = new JMenuItem("Notepad");
		i5.setIcon(ii7);
		i5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		i5.setMnemonic(KeyEvent.VK_N);
		
		i6 = new JMenuItem("Calculator");
		i6.setIcon(ii9);
		i6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		i6.setMnemonic(KeyEvent.VK_C);
		
		i7 = new JMenuItem("Web Browser");
		i7.setIcon(ii11);
		i7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		i7.setMnemonic(KeyEvent.VK_W);
		
		i8 = new JMenuItem("Logout");
		i8.setIcon(ii13);
		i8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		i8.setMnemonic(KeyEvent.VK_L);
		
		m1.add(i9);
		m1.add(i10);

		
		m3.add(i5);
		m3.add(i6);
		m3.add(i7);
		
		m4.add(i8);
		
		
		
		mb1.add(m1);
		mb1.add(m3);
		mb1.add(m4);
		add(mb1);
		
		
				
		
		setLayout(null);
		
		mb1.setBounds(0,10,1950,30);
		add(mb1);
		
		l1.setBounds(15,10,950,1050);
		add(l1);
		
		
		setSize(1950,1050);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		
		mb1.setBackground(Color.orange);
	
		i5.setBackground(Color.WHITE);
		i6.setBackground(Color.WHITE);
		i7.setBackground(Color.WHITE);
		i8.setBackground(Color.WHITE);
		i9.setBackground(Color.WHITE);
		i10.setBackground(Color.WHITE);
		
		i5.addActionListener(this);
		i6.addActionListener(this);
		i7.addActionListener(this);
		i8.addActionListener(this);
		i9.addActionListener(this);
		i10.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		
		
		String msg = ae.getActionCommand();
		
		if(ae.getSource()==i9){
			new ReadFile().setVisible(true);
		}else if(ae.getSource()==i10){
			new ReadTables().setVisible(true);
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
			try {
				System.out.println("Hello");
				Runtime.getRuntime().exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(ae.getSource()==i8){
			new login().setVisible(true);
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args){
		new Index();
	}

}
