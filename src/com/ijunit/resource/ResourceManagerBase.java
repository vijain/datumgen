/**
 * 
 */
package com.ijunit.resource;

/**
 * @author vcjain
 *
 */
public class ResourceManagerBase {
	
	public static String resourceFileName = null;
	public static String TESTCONDITION_MAP_FILE_EXT = "tcm.xml";
	public static String TESTCONDITION_FILE_EXT = "tc.xml";
	public static String OBJECTXMLDATA_MAP_FILE_EXT = "oxd.xml";
	public static String DATABASE_CONF_FILE_EXT = "cfg.xml";
	public static String MESSAGE_FILE_EXT = "msg.xml";
	public static String OBJECTXMLDATA_FILE_EXT = "oxd.xml";
	public static String HISTORY_FILE_EXT = "his";
	public static String SQL_FILE_EXT = "sql";
	public static String IMAGE_FILE_EXT = "PNG";
	public static String PROPERTY_FILE_EXT = "properties";
	public static String FILE_SEPRATOR = System.getProperty("file.separator");
	
	public static String TESTCONDITION_DIR = "tc";
	public static String OBJECTXMLDATA_DIR = "oxd";
	public static String DATABASE_DIR = "db";
	public static String SQLFILES_DIR = "sql";
	public static String LOG4J_DIR = "logging";
	public static String MESSAGE_DIR = "msg";
	public static String IMAGE_DIR = "image";
	public static boolean runningMode = true;
	
	public static String resourceDir="iJunit";
	
	public static String resourcePath = System.getProperty("user.home")+FILE_SEPRATOR+resourceDir;
	public static String datumgenResourcePath = System.getenv("DATUMGEN_HOME")+FILE_SEPRATOR+"resource";
	
	public static String DatumgenImagePath = datumgenResourcePath+FILE_SEPRATOR+"image";
	public static String datumgenHistoryPath = datumgenResourcePath+FILE_SEPRATOR+"history";
}
