package ibm.com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ibm.com.dao.PrintDeviceDAOImpl;

import sun.misc.BASE64Decoder;

public class DBConnection {
	static final Logger LOGGER = Logger.getLogger(DBConnection.class);
	
    private static final String ALGORITHM = "AES";
    
    private static final String KEY = "3hJo5Nlq6tdfUPv4";
    
String Password=null;
String decryptpassword =null;
	public Connection getConnection() {

		
		
	java.util.Properties properties=new java.util.Properties();
			
			InputStream inputStreamprop=null;
			try {
				inputStreamprop = new FileInputStream("C://printfax/DBPropertyFile.properties");
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				LOGGER.error("printStackTrace ocurred" + e2);
			}
			
			try {
				properties.load(inputStreamprop);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
			
				
				LOGGER.error("printStackTrace ocurred" + e2);
			}
			
		
		PropertyConfigurator
				.configure(this.getClass().getClassLoader().getResource("ibm/com/properties/log4j.properties"));

		

		Connection connection = null;
		
		Password=properties.getProperty("password");
		
	
		DBConnection conn=new DBConnection();
		try {
			 decryptpassword = conn.decrypt(Password);
			 
			 LOGGER.info("decryptpassword"+decryptpassword);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		 LOGGER.info("url+++++++++"+properties.getProperty("connectionurl")+";"+"user="+properties.getProperty("username")+";"+"password="+decryptpassword+";"+"database="+properties.getProperty("database"));

		try {

			Class.forName(properties.getProperty("jdbcurl"));
			connection = DriverManager.getConnection(properties.getProperty("connectionurl")+";"+"user="+properties.getProperty("username")+";"+"password="+decryptpassword+";"+"database="+properties.getProperty("database"));

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("SQLException ocurred" + e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.error("ClassNotFoundException ocurred" + e);
		}

		return connection;

	}
	
	
	 public String decrypt(String Password) throws Exception
     {
         Key key = generateKey();
         Cipher cipher = Cipher.getInstance(DBConnection.ALGORITHM);
         cipher.init(Cipher.DECRYPT_MODE, key);
         byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(Password);
         byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
         String decryptedValue = new String(decryptedByteValue,"utf-8");
         return decryptedValue;
                 
     }
     
     private static Key generateKey() throws Exception 
     {
         Key key = new SecretKeySpec(DBConnection.KEY.getBytes(),DBConnection.ALGORITHM);
         return key;
     }


    
     
     
}
