package com.ijunit.common.constant;

public interface IConfigurationConstant {
	
	public static final String DATABASE = "DATABASE"; 
	public static final String DRIVER = "DRIVER";
	public static final String DATABASEURL = "DATABASEURL";
	public static final String HOST = "HOST";
	public static final String PORT = "PORT";
	public static final String USER = "USER";
	public static final String PASSWORD = "PASSWORD";
	public static final String CONFIGURATION = "CONFIGURATION";
	public static final String CONFIGURATION_NAME = "name";
	public static String TESTAPP_CONF = "app";
	public static String APP_CONF = "own";
	
	public static String DATABASE_TMP = "${database}";
	public static String DRIVER_TMP = "${driver}";
	public static String DBURL_TMP = "${databaseurl}";
	public static String HOST_TMP = "${host}";
	public static String PORT_TMP = "${port}";
	public static String USER_TMP = "${user}";
	public static String PASSWORD_TMP = "${password}";
	public static String CONFIGURATION_NAME_TMP = "${name}";
	public static String CONFIGURATION_TMP = "${configuration}";
	
	public static String ROOT_PRP = "root";
	public static String CLASSPATH_DIR = "classPathDir";
	public static String TESTCLASSES_DIR = "testclassPathDir";
	public static String RUNNINGMODE_PRP = "runningmode.standalone";
	public static String LOG_DIR = "logDir";
	public static String VERSION = "version";
}
