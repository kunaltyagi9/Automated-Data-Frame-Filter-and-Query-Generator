import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Splash {
	public static void main(String[] args){
		fframe f1 = new fframe();
		f1.setVisible(true);
		int i;
		int x = 1;
		for(i = 2 ; i <= 600 ; i+=4, x+=1){
			f1.setLocation(800-((i+x)/2), 500 - (i/2));
			f1.setSize(i+x, i);
			
			try{
				Thread.sleep(10);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
}
class fframe extends JFrame implements Runnable{
		
	Thread t1;
	fframe(){
		super("Oracle Form Workbench");
		setLayout(new FlowLayout());
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Images/splashImage.png"));
		
		Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		add(l1);
		
		t1 = new Thread(this);
		t1.start();
		
	}
	
	public void run(){
		try{
			Thread.sleep(7000);
			this.setVisible(false);
			login l = new login();
			l.setVisible(true);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
