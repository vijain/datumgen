package com.ijunit.common.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author vcjain
 *
 */
public class Configuration
{
	
	private static String driverName= null;
	private static String database = null;
	private static String db_user = null;
	private static String db_password = null;
	private static String db_Url = null;
	private static String db_Server = null;
	private static String db_Port = null;
	private static String rootPath = null;
	
	private static final String DATABASE = "database-name";
	private static final String DRIVER = "driver-name";
	private static final String DBURL = "database-url";
	private static final String DBSERVER = "server";
	private static final String DBPORT = "port";
	private static final String DBUSER = "user";
	private static final String DBPASSWORD = "password";
	private static final String OBJECTMAP = "object-mappings";
	private static final String MAP = "map";
	private static final String OBJECTMAP_ROOTPATH  = "rootPath";
	private static final String MAP_FILE  = "file";
	private static final String MAP_CLASS  = "class";
	private static final String MAP_DATAHELPER  = "datahelper";
	private static final String KEY_SEPRATOR  = "_";
	
	private static Map objectFileMap = null;
	
	static
	{
		try{
			objectFileMap = new HashMap();
			loadConfiguration();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}catch(SAXException saxException){
			saxException.printStackTrace();
		}catch(ParserConfigurationException pcException){
			pcException.printStackTrace();
		}
	}
	
	/**
	 * Returns rootpath specified in Configuration.xml file
	 * @return the rootPath
	 */
	public static String getRootPath() {
		return rootPath;
	}

	/**
	 * Set configure rootPath. 
	 * @param rootPath the rootPath to set
	 */
	public static void setRootPath(String rootPath) {
		Configuration.rootPath = rootPath;
	}

	/**
	 * Method will load all configuration setting like database, Object to XMLData file mapping etc. specify in
	 * Configuration.xml file. 
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static void loadConfiguration() throws IOException,SAXException,ParserConfigurationException
	{
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		parser.parse("E:\\eclipse-workspaces\\weblogic103\\i-emphsys42\\unittests\\resources\\Configuration.xml", 
				new DefaultHandler() 
				{
					StringBuffer buffer = new StringBuffer();
					String objClassName = null;
					String objFile = null;
					public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException 
					{
						super.startElement(uri, qName, qName, attributes);
						if(qName.equalsIgnoreCase(OBJECTMAP))
						{
							rootPath = attributes.getValue(OBJECTMAP_ROOTPATH);
						}else if(qName.equalsIgnoreCase(MAP))
						{
							objClassName = attributes.getValue(MAP_CLASS);
						}
					}
					
					/**
					 * Method will collect the text define for an XML Node. 
					 */
					public void characters(char[] chars, int start, int length)	throws SAXException 
					{
						buffer.append(chars, start, length);
					}
					
					public void endElement(String uri, String localName, String qName) throws SAXException
					{
						super.endElement(uri, localName, qName);
						if(qName.equalsIgnoreCase(DATABASE))
						{
							database = buffer.toString().trim();
						}else if(qName.equalsIgnoreCase(DRIVER))
						{
							driverName = buffer.toString().trim();
						}else if(qName.equalsIgnoreCase(DBURL))
						{
							db_Url = buffer.toString().trim();
						}else if(qName.equalsIgnoreCase(DBSERVER))
						{
							db_Server = buffer.toString().trim();
						}else if(qName.equalsIgnoreCase(DBPORT))
						{
							db_Port = buffer.toString().trim();
						}else if(qName.equalsIgnoreCase(DBUSER))
						{
							db_user = buffer.toString().trim();
						}else if(qName.equalsIgnoreCase(DBPASSWORD))
						{
							db_password = buffer.toString().trim();
						}else if(qName.equalsIgnoreCase(MAP_FILE))
						{
							objFile = buffer.toString().trim();
							objectFileMap.put(objClassName+KEY_SEPRATOR+MAP_FILE, objFile);
						}else if(qName.equalsIgnoreCase(MAP_DATAHELPER))
						{
							objectFileMap.put(objClassName+KEY_SEPRATOR+MAP_DATAHELPER, buffer.toString().trim());
						}
						buffer = new StringBuffer();
					}
						
					public void warning(SAXParseException exception) 
					{
						exception.printStackTrace();
						return;
					}
		
					public void error(SAXParseException exception) 
					{
						exception.printStackTrace();
						return;
					}
		
					public void fatalError(SAXParseException exception)	throws SAXException 
					{
						exception.printStackTrace();
						throw exception;
					}
				});

	}
	
	/**
	 * Returns database connection object. Database parameter can be found at Configuration.xml file.
	 * @return database connection to database with properties defined in configuration.xml file
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException, 
				InstantiationException,IllegalAccessException
	{
		Driver driver = (Driver)Class.forName(driverName).newInstance();
		Properties dbProperties = new Properties();
		dbProperties.setProperty("user",db_user );
		dbProperties.setProperty("password",db_password );
		String url = db_Url+":"+"@"+db_Server+":"+db_Port+":"+database;
		System.out.println("url "+url);
		Connection con = driver.connect(url, dbProperties);
		con.setAutoCommit(false);
		return con;
	}
	
	/**
	 * Returns Object's XMLData file name corressponding to Object's class name passed as argument. 
	 * Argument className will be a full package classname like "com.mbi.common.data.user.UserInfo". 
	 * If no entry corresponding to classname argument is specified in Configuration.xml file then NULL will
	 * be return. 
	 * @param className
	 * @return name of file specified in configuration.xml file corressponding to className
	 */
	public static String getObjectFile(String className)
	{
		String fileName = (String)objectFileMap.get(className+KEY_SEPRATOR+MAP_FILE);
		return fileName;
	}
	
	/**
	 * Returns full package DataHelper Class name corressponding to Object's Class name passed as argument. 
	 * Argument className will be a full package classname like "com.mbi.common.data.user.UserInfo". If no 
	 * entry corresponding to classname argument is specified in Configuration.xml file then NULL will be return.   
	 * @param className
	 * @return name of file specified in configuration.xml file corressponding to className
	 */
	public static String getObjectDataHelper(String className)
	{
		return (String)objectFileMap.get(className+KEY_SEPRATOR+MAP_DATAHELPER);
	}
	
	public static void main(String[] args) throws Exception 
	{
		Connection con = Configuration.getConnection();
		Statement stmt = con.createStatement();
		System.out.println(rootPath+"\\"+Configuration.getObjectDataHelper("com.mbi.common.data.user.UserInfo"));
		System.out.println(rootPath+"\\"+Configuration.getObjectFile("com.mbi.common.data.user.UserInfo"));
	}
}
