package ibm.com.mits;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
	
	
	public static Connection getConnection() {
		
		
		
		 Connection connection = null;
	
		try {
			
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://172.16.12.96;user=sa;password=mits123$;database=PrintFaxDB");
			
			
			System.out.println("Connection ::: "+connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		 
		   
		   
		
	 
		
		return connection;
		
		


}
	
	
	public static void main(String [] args)
	{
		
		
		DBConnection DBConnection=new DBConnection();
		
		
		DBConnection.getConnection();
		
		
	}

}
