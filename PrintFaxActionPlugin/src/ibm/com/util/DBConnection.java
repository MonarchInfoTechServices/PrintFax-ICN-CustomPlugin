package ibm.com.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DBConnection {
	static final Logger LOGGER = Logger.getLogger(DBConnection.class);

	/**
	 * This method is used to get Data Base Connection 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public Connection getConnection() throws SQLException, NamingException {

		ResourceBundle resource = ResourceBundle.getBundle("ibm.com.properties.DBPropertyFile");

	PropertyConfigurator
		.configure(this.getClass().getClassLoader().getResource("ibm/com/properties/log4j.properties"));

		Connection connection = null;
		try {
			String dataSourcename = resource.getString("datasource");
			try {
				Context initialContext = null;
				// Creating Object for InitialContext
				initialContext = new InitialContext();

				// Creating DataSource Object using InitialContext
				// Object(Calling Lookup())
				DataSource dataSource = (DataSource) initialContext.lookup(dataSourcename);
				if (dataSource != null) {
					connection = dataSource.getConnection();
				}
				LOGGER.info("connection to the database" + connection);

			} catch (NamingException e) {
				LOGGER.error("NamingException ocurred" + e);
				throw new NamingException("Data Source Connection Failed"+e.getMessage());
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException ocurred" + e);
			throw new SQLException("Connection Failed"+e.getMessage());
		}
		return connection;
	}







}
