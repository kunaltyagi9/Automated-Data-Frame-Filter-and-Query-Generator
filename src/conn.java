import java.sql.*;  

public class conn{ 	 
	public static void main(String args[]){  
		try{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");   	 
				Statement stmt=con.createStatement();  
				con.close();  
		}catch(Exception e){ 
				System.out.println(e);
		}  
	}  
}  