package ibm.com.dao;

import java.io.StringWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;

import com.ibm.json.java.JSONArray;

import ibm.com.pojo.DocValues;
import ibm.com.pojo.PrintValues;

public interface PrintDeviceDAO {
	
	public JSONArray deviceNamesRetrieval() throws SQLException, NamingException;
	public String libraryNamesRetrieval() throws SQLException, NamingException;

	public int printRequestInsertion(StringWriter writer, DocValues docValues, PrintValues printValues) throws SQLException, NamingException ;
	
	
}
