package ibm.com.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import ibm.com.mits.DBConnection;

public class PrintDeviceDAOImpl implements PrintDeviceDAO {

	@Override
	public JSONArray devicemanesretrival() {
		
		Statement statement = null;
		ResultSet result = null;
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj =null;
		JSONObject jsonObj1= new JSONObject();
		
		 String devicetype=null;
		 String devicename=null;
		
	Connection 	Connection =DBConnection.getConnection();
		 
		
		String sql="select Device_Name,Device_Type from [PrintFaxDB].[dbo].[DW_PrintDevice]";
		
		System.out.println("the srting is"+sql);
		

		try {
			statement = Connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			while (result.next()){
				
				jsonObj= new JSONObject();
			  
			   devicename    = result.getString("Device_Name");
			 
			    devicetype = result.getString("Device_Type");
				 
			    System.out.println("devicename"+devicename);
			    
			    
				 jsonObj.put("name",devicename);
				 jsonObj.put("id",devicetype);
				 
				 
					jsonArray.add(jsonObj); 
					//jsonArray.add(jsonObj1); 
					
					
					System.out.println("jsonarray"+jsonArray);
			   
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		//jsonArray.add(jsonObj1);
		
	
	
	return jsonArray;
	}
	
	


}
