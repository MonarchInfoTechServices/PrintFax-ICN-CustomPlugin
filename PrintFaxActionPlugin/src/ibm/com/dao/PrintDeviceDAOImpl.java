package ibm.com.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import ibm.com.pojo.DocValues;
import ibm.com.util.DBConnection;
import ibm.com.xml.PFS;
import ibm.com.xml.PrintFaxRequestType;
import ibm.com.xml.Request;

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
	PreparedStatement preparedstatement = null;

	static final Logger LOGGER = Logger.getLogger(PrintDeviceDAOImpl.class);
	ResourceBundle resource = ResourceBundle.getBundle("ibm.com.properties.DBPropertyFile");

	DBConnection dbConnection = new DBConnection();

	Connection Connection = dbConnection.getConnection();

	@Override
	public JSONArray devicenamesretrival() {

		devicetypequery = resource.getString("queryfordevicetype");

		jsonArray = new JSONArray();

		LOGGER.info("query for the device type:::" + devicetypequery);

		try {
			statement = Connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("SQLException ocurred" + e);
		}

		try {
			result = statement.executeQuery(devicetypequery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("SQLException ocurred" + e);
		}

		try {
			while (result.next()) {

				jsonObj = new JSONObject();

				devicename = result.getString("Device_Name");

				devicetype = result.getString("Device_Type");

				LOGGER.info("devicename" + devicename);

				jsonObj.put("name", devicename);
				jsonObj.put("id", devicetype);

				jsonArray.add(jsonObj);

				LOGGER.info("jsonarray" + jsonArray);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("SQLException ocurred" + e);
		}

		return jsonArray;
	}

	public String librarynameretrival() {

		librarysql = resource.getString("queryforlibraryname");

		try {
			Statementforlib = Connection.createStatement();

			resultforlib = Statementforlib.executeQuery(librarysql);

			while (resultforlib.next()) {

				libraryname = resultforlib.getString("Library_Name");

				LOGGER.info("libraryname" + libraryname);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("SQLException ocurred" + e);
		}
		return libraryname;

	}
	
	 public static java.sql.Date getCurrentJavaSqlDate() {
		    java.util.Date today = new java.util.Date();
		    return new java.sql.Date(today.getTime());
		  }

	public void Printrequestinsertion(StringWriter writer, DocValues docValues) {

		JSONArray mimetypearraylist = docValues.getMimetypearraylist();


		insertsql = resource.getString("queryforinsertion");
		java.sql.Date date = getCurrentJavaSqlDate();

		try {
			preparedstatement = Connection.prepareStatement(insertsql);
			preparedstatement.setString(1, "new");
			preparedstatement.setInt(2, 2);
			preparedstatement.setNString(3, writer.toString());
			preparedstatement.setString(4, "NEW");
			preparedstatement.setString(5, "P8admin");
			preparedstatement.setDate(6,  date);
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
			int executeUpdate = preparedstatement.executeUpdate();
			LOGGER.info("after submission" + executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("SQLException ocurred" + e);
		}

		finally {
			try {
				Connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("SQLException ocurred" + e);
			}
		}

	}

}
