package ibm.com.dao;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import ibm.com.pojo.DocValues;
import ibm.com.pojo.PrintValues;
import ibm.com.util.DBConnection;

/**
 * This class used for retrieval 
 */
public class PrintDeviceDAOImpl implements PrintDeviceDAO {

	int tiffCounter = 0;
	int mixedCounter = 0;
	String deviceType = null;
	String deviceName = null;
	ResultSet result = null;
	JSONArray jsonArray = null;
	JSONObject jsonObj = null;
	Statement statement = null;
	String librarySql = null;
	String deviceTypeQuery = null;
	Statement StatementForLib = null;
	ResultSet resultForLib = null;
	String insertSql = null;
	String libraryName = null;
	
	/**
	 * Default Constructor
	 */
	public PrintDeviceDAOImpl(){

	}


	PreparedStatement preparedStatement = null;

	static final Logger LOGGER = Logger.getLogger(PrintDeviceDAOImpl.class);
	ResourceBundle resource = ResourceBundle.getBundle("ibm.com.properties.DBPropertyFile");

	DBConnection dbConnection = new DBConnection();
	Connection connection =null;

	/* 
	 * This is used to get Device names From Data Base
	 * @
	 */
	

	public JSONArray deviceNamesRetrieval() throws SQLException, NamingException {
		try {
			connection = dbConnection.getConnection();
			if(connection!=null){
				deviceTypeQuery = resource.getString("queryfordevicetype");
				jsonArray = new JSONArray();
				LOGGER.info("query for the device type:::" + deviceTypeQuery);
				statement = connection.createStatement();

				result = statement.executeQuery(deviceTypeQuery);

				while (result.next()) {
					jsonObj = new JSONObject();
					deviceName = result.getString("Device_Name");
					deviceType = result.getString("Device_Type");
					LOGGER.info("devicename" + deviceName);
					jsonObj.put("name", deviceName);
					jsonObj.put("id", deviceType);
					jsonArray.add(jsonObj);

					LOGGER.info("jsonarray of devicenamesretrival" + jsonArray);
				}
			}

		} catch (SQLException e) {
			LOGGER.error("SQLException ocurred" + e);
			throw new SQLException("Connection Failed"+e.getMessage());
		}

		return jsonArray;
	}

	/* 
	 * This is used to get library names From Data Base
	 * 
	 */
	public String libraryNamesRetrieval() throws SQLException, NamingException {
	
		librarySql = resource.getString("queryforlibraryname");
		try {
			connection = dbConnection.getConnection();

			if(connection!=null){
				StatementForLib = connection.createStatement();
				resultForLib = StatementForLib.executeQuery(librarySql);
				while (resultForLib.next()) {
					libraryName = resultForLib.getString("Library_Name");
					LOGGER.info("libraryname" + libraryName);

				}
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException ocurred" + e);
			throw new SQLException("Connection failed"+e.getMessage());
		}
	
		return libraryName;

	}

	/**
	 * Get Current Date
	 * @return
	 */
	public static java.sql.Date getCurrentJavaSqlDate() {
		Date today = new Date();
		return new java.sql.Date(today.getTime());
	}

	/* 
	 * This method is used to insert printFax request in DB
	 * @ StringWriter
	 * @ DocValues
	 */
	public int printRequestInsertion(StringWriter writer, DocValues docValues,PrintValues printValues) throws SQLException, NamingException {

		JSONArray mimetypearraylist = docValues.getMimetypearraylist();
		connection = dbConnection.getConnection();
		int executeUpdate = 0;
		insertSql = resource.getString("queryforinsertion");
		java.sql.Date date = getCurrentJavaSqlDate();

		try {
			if(connection!=null){
				preparedStatement = connection.prepareStatement(insertSql);
				preparedStatement.setString(1, "new");
				preparedStatement.setInt(2, 2);
				preparedStatement.setNString(3, writer.toString());
				preparedStatement.setString(4, "NEW");
				preparedStatement.setString(5, printValues.getUserId());
				preparedStatement.setDate(6, date);
				preparedStatement.setDate(7, null);

				for (int i = 0; i < mimetypearraylist.size(); i++) {
					if (mimetypearraylist.get(i).toString().equalsIgnoreCase("image/tiff")) {
						tiffCounter++;
						LOGGER.info("tiffcounter value" + tiffCounter);
					} else {
						mixedCounter++;
						LOGGER.info("mixedCounter value" + mixedCounter);
					}

				}
				LOGGER.info("tiffcounter value" + tiffCounter);
				LOGGER.info("mixedCounter value" + mixedCounter);
				if (mixedCounter != 0) {
					preparedStatement.setString(8, "mixed");
				} else {
					preparedStatement.setString(8, "image/tiff");
				}
				preparedStatement.setString(9, "");
				executeUpdate = preparedStatement.executeUpdate();
				LOGGER.info("after submission" + executeUpdate);
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException ocurred" + e);
			throw new SQLException("Connection Failed"+e.getMessage());
		}
		finally {
			if(connection!=null){
				connection.close();
			}
		}
		return executeUpdate;
	}
}
