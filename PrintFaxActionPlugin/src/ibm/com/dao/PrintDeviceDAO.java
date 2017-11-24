package ibm.com.dao;

import java.io.StringWriter;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import com.ibm.json.java.JSONArray;

import ibm.com.pojo.DocValues;

public interface PrintDeviceDAO {
	
	public JSONArray devicenamesretrival();
	public String librarynameretrival();

	public void Printrequestinsertion(StringWriter writer, DocValues docValues);
	
	
}
