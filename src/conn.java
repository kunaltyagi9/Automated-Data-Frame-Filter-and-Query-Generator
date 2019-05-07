import java.sql.*;  

public class conn{ 	 
	Connection c;
	Statement s;
	public conn(){		
		try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","password-1");  
				s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		}catch(Exception e){ 
				System.out.println(e);
		}  
		
	}  
	public static void main(String[] args){
		new conn();
	}
}  