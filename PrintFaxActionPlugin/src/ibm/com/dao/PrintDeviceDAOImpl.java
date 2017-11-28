package ibm.com.dao;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import ibm.com.pojo.DocValues;
import ibm.com.util.DBConnection;

public class PrintDeviceDAOImpl implements PrintDeviceDAO {

	int tiffCounter = 0;
	int mixedCounter = 0;
	String devicetype = null;
	String devicename = null;
	ResultSet result = null;
	JSONArray jsonArray = null;
	JSONObject jsonObj = null;
	Statement statement = null;
	String librarysql = null;
	String devicetypequery = null;
	Statement Statementforlib = null;
	ResultSet resultforlib = null;
	String insertsql = null;
	String libraryname = null;
	public PrintDeviceDAOImpl(){

	}


	PreparedStatement preparedstatement = null;

	static final Logger LOGGER = Logger.getLogger(PrintDeviceDAOImpl.class);
	ResourceBundle resource = ResourceBundle.getBundle("ibm.com.properties.DBPropertyFile");

	DBConnection dbConnection = new DBConnection();
	Connection connection =null;

	@Override
	public JSONArray devicenamesretrival() throws SQLException, NamingException {
		try {
			connection = dbConnection.getConnection();
			if(connection!=null){
				devicetypequery = resource.getString("queryfordevicetype");
				jsonArray = new JSONArray();
				LOGGER.info("query for the device type:::" + devicetypequery);
				statement = connection.createStatement();

				result = statement.executeQuery(devicetypequery);

				while (result.next()) {
					jsonObj = new JSONObject();
					devicename = result.getString("Device_Name");
					devicetype = result.getString("Device_Type");
					LOGGER.info("devicename" + devicename);
					jsonObj.put("name", devicename);
					jsonObj.put("id", devicetype);
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

	public String librarynameretrival() throws SQLException, NamingException {
		LOGGER.info("Entered in librarynameretrival method"+connection);
		librarysql = resource.getString("queryforlibraryname");
		try {
			connection = dbConnection.getConnection();

			if(connection!=null){
				Statementforlib = connection.createStatement();
				resultforlib = Statementforlib.executeQuery(librarysql);
				while (resultforlib.next()) {
					libraryname = resultforlib.getString("Library_Name");
					LOGGER.info("libraryname" + libraryname);

				}
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException ocurred" + e);
			throw new SQLException("Connection failed"+e.getMessage());
			// TODO Auto-generated catch block
		}
		LOGGER.info("left from librarynameretrival method");
		return libraryname;

	}

	public static java.sql.Date getCurrentJavaSqlDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public int Printrequestinsertion(StringWriter writer, DocValues docValues) throws SQLException, NamingException {

		JSONArray mimetypearraylist = docValues.getMimetypearraylist();
		connection = dbConnection.getConnection();
		int executeUpdate = 0;
		insertsql = resource.getString("queryforinsertion");
		java.sql.Date date = getCurrentJavaSqlDate();

		try {
			if(connection!=null){
				preparedstatement = connection.prepareStatement(insertsql);
				preparedstatement.setString(1, "new");
				preparedstatement.setInt(2, 2);
				preparedstatement.setNString(3, writer.toString());
				preparedstatement.setString(4, "NEW");
				preparedstatement.setString(5, "P8admin");
				preparedstatement.setDate(6, date);
				preparedstatement.setDate(7, null);

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
					preparedstatement.setString(8, "mixed");
				} else {
					preparedstatement.setString(8, "image/tiff");
				}
				preparedstatement.setString(9, "");
				executeUpdate = preparedstatement.executeUpdate();
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
