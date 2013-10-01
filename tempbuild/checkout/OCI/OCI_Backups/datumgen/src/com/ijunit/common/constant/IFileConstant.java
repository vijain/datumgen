package com.ijunit.common.constant;


/**
 * This interface defines file name for variuos file used in the API.
 * @author vcjain
 */
public interface IFileConstant 
{
	/**
	 * Name of the Test condition mapping file.  
	 */
	String TESTCONDITION_MAP_FILE = "testconditions.tcm.xml"; 
	/**
	 * Name of the Object Xml Data Mapping file. 
	 */
	String OBJECTXMLDATA_MAP_FILE = "objectconfiguration.oxd.xml";
	/**
	 * Name of the  database configuration file.
	 */
	String DATABASE_CONF_FILE = "database.cfg.xml";
	/**
	 * Name of the log 4j configuration file. 
	 */
	String LOG4J_FILE = "ijunit-log4j.xml";
	/**
	 * Name of the Error message file. 
	 */
	String ERROR_MSG_FILE = "error.msg.xml";
	/**
	 * Name of the application message file. 
	 */
	String APPLICATION_MSG_FILE = "app.msg.xml";
	
	String RESOURCE_FILE="ijunit.properties";
	
	String TESTCLASS_HISTORY_FILE="tc.his";
	
	String TESTDATA_HISTORY_FILE="tdc.his";
	
	public static String APP_PROPERTY_FILE="app.properties";
	
	public static String DATABASE_CONF_WRITER = "DATABASE_CONF";
	
	public static String RESOURCE_CONF_WRITER = "RESOURCE_CONF";
	
	public static String OBJECTXML_DATA_WRITER = "OBJECTXML_DATA";
	
	public static String OBJECTXML_MAP_WRITER = "OBJECTXML_MAP";
	
	public static String SQLFILE_WRITER = "SQLFILE";
	
	public static String SQLFILENAME = "SQLFILENAME";
	
	public static String QUERYTOINSERT = "QUERYTOINSERT";
	
	public static String CONDITIONNAME = "CONDITIONNAME";
	
	public static String GLOBALNAME = "GLOBAL";
	
	public static String GLOBAL_CONDITIONNAME = "GLOBALCONDITION";
	
	public static String BASICFILE_WRITER = "BASICFILE_WRITER";
}
