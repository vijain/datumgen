/**
 * 
 */
package com.ijunit.common.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IApplicationMsgCodes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.DBConfigurationParser;
import com.ijunit.parser.ErrorMessageParser;



/**
 * @author vcjain
 *
 */
public class DBConnection 
{
	private static Logger logger = Logger.getLogger(DBConnection.class);
	
	private static String database;
	private static String dbURL;
	private static String driver;
	private static String host;
	private static String userName;
	private static String password;
	private static String port;
	
	private static String app_database;
	private static String app_dbURL;
	private static String app_driver;
	private static String app_host;
	private static String app_userName;
	private static String app_password;
	private static String app_port;
	
	static
	{
		DBConfigurationParser dbParser = DBConfigurationParser.getInstance();
		
		//Connection properties for Tool.
		database = dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.DATABASE);
		driver = dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.DRIVER);
		dbURL = dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.DATABASEURL);
		host = dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.HOST);
		userName = dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.USER);
		password = dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.PASSWORD);
		port = dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.PORT);
		
		//Connection properties for Test Application. 
		app_database = dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.DATABASE);
		app_driver = dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.DRIVER);
		app_dbURL = dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.DATABASEURL);
		app_host = dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.HOST);
		app_userName = dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.USER);
		app_password = dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.PASSWORD);
		app_port = dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.PORT);
		System.out.println(app_database);
	}
	
	/**
	 * Returns database connection object. Database parameter can be found at Configuration.xml file.
	 * @return database connection to database with properties defined in configuration.xml file
	 * @throws IJunitException 
	 * @throws Exception 
	 */
	public static Connection getAppConnection() throws  IJunitException
	{
		logger.debug("Entered in DBConnection.getAppConnection()");
		
		Connection con = null;
		if(app_driver  == null || app_database  == null || app_host  == null || 	app_dbURL  == null || app_port == null || app_userName  == null || app_password  == null )
		{
			JOptionPane.showMessageDialog(null,ApplicationMessageParser.getMessage(IApplicationMsgCodes.DB_CONFIGURATION_BLANK,
					new String[]{IFileConstant.DATABASE_CONF_FILE})	,"Message",JOptionPane.INFORMATION_MESSAGE);
		}else
		{
			Driver driver = null;
			try {
				driver = (Driver)Class.forName(app_driver).newInstance();
				Properties dbProperties = new Properties();
				dbProperties.setProperty("user",app_userName );
				dbProperties.setProperty("password",app_password);
				String url = app_dbURL +":" +"@" + app_host +":" + app_port +":" + app_database;
				con = driver.connect(url, dbProperties);
				con.setAutoCommit(false);
				logger.debug("Exited from DBConnection.getAppConnection()");
			} catch (Exception e) {
				logger.error("In DBConnection.getConnection "+ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP),e);
			} 
		}
		return con;
	}
	
	public static Connection getConnection() throws IJunitException  
	{
		logger.debug("Entered in DBConnection.getConnection()");
		
		Connection con = null;
		if(driver  == null || database  == null || host  == null || dbURL  == null || port == null || userName  == null || password  == null )
		{
			JOptionPane.showMessageDialog(null,ApplicationMessageParser.getMessage(IApplicationMsgCodes.DB_CONFIGURATION_BLANK)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
		}else
		{
			Driver dbDriver = null;
			try {
				dbDriver = (Driver)Class.forName(driver).newInstance();
							Properties dbProperties = new Properties();
				dbProperties.setProperty("user",userName );
				dbProperties.setProperty("password",password );
				String url = dbURL +":" +"@" + host +":" + port +":" + database;
				
				con = dbDriver.connect(url, dbProperties);
				con.setAutoCommit(false);
				logger.debug("Exited from DBConnection.getConnection()");
			} catch (Exception e) {
				logger.error("In DBConnection.getConnection "+ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP),e);
			} 
		}
		return con;
	}
	
	/**
	 * This method will return the Next ID for DATACOLUMN table
	 * @return
	 * @throws Exception
	 */
	public static int getDCSequenceNo() throws IJunitException, SQLException{
		Connection con = getConnection();
		ResultSet rs = con.createStatement().executeQuery("SELECT DataColumn_SEQ.NextVal FROM DUAL");
		if(rs.next()){
			return  rs.getInt(1);
		}else 
			return -1;
	}
	
	/**
	 * This method will return the Next ID for TESTCONDITION table
	 * @return
	 * @throws IJunitException 
	 * @throws SQLException 
	 * @throws Exception
	 */
	public static int getTCSequenceNo() throws IJunitException, SQLException {
		Connection con = getConnection();
		ResultSet rs = con.createStatement().executeQuery("SELECT TESTCONDITION_SEQ.NextVal FROM DUAL");
		if(rs.next()){
			return  rs.getInt(1);
		}else 
			return -1;
	}
	
	public static void releaseConnection( Connection con )
	{
		try
		{
			con.close( ) ;
		}
		catch(Exception e)
		{}
	}
	
	public static void releaseConnection( Thread currentThread )
	{
		JUnitConnection con = (JUnitConnection)ConnectionFactory.getConnection(currentThread.getName());
		try {
			con.closeWraper();
			con = null;
		} catch (SQLException e) {
			logger.error("In DBConnection.releaseConnection "+ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP), e);
		}
	}
	
	
	public static void rollback(Connection con){
		logger.debug("Entered in DBConnection.rollback()");
		  try {
			con.rollback();
		} catch (SQLException e) {
			logger.error("In DBConnectionManager.rollback "+ErrorMessageParser.getMessage(IExceptionCodes.CON_ROLLBACK_EXP), e);
		}
	  }
	  
//	public static void closeWrapper(Connection con){
//		 try {
//			((JUnitConnection)con).closeWraper();
//		} catch (SQLException e) {
//			logger.error("In DBConnectionManager.rollback "+ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP), e);
//		}
//	}
	
	
	
	public static void main(String[] args) {
		try {
			Connection con = DBConnection.getAppConnection();
			
		} catch (IJunitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
