package com.ijunit.resource;

import static com.ijunit.resource.ResourceManagerBase.IMAGE_DIR;
import static com.ijunit.resource.ResourceManagerBase.LOG4J_DIR;
import static com.ijunit.resource.ResourceManagerBase.MESSAGE_DIR;
import static com.ijunit.resource.ResourceManagerBase.OBJECTXMLDATA_DIR;
import static com.ijunit.resource.ResourceManagerBase.SQLFILES_DIR;
import static com.ijunit.resource.ResourceManagerBase.TESTCONDITION_DIR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;

public class ResourceManager extends ResourceManagerBase 
{
	static Logger logger = null;
	public static String root;
	public static Properties props = null;
	
	
	static
	{
//		System.out.println("R1");
		if(resourceFileName != null && new File(resourceFileName).exists()){
			loadProperties(resourceFileName);
		}
	}
	
	public static void loadProperties(String fileName)
	{
		root = System.getProperty(IConfigurationConstant.ROOT_PRP);
//		System.out.println("R2");
		if(root == null)
		{
//			System.out.println("R3");
			props = new Properties();
			try {
				props.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				logger.error("Property file "+fileName+" not found at path ", e);
			} catch (IOException e) {
				logger.error("Exception occur while reading property file "+fileName, e);
			}
			root = props.getProperty(IConfigurationConstant.ROOT_PRP);
			runningMode = new Boolean(props.getProperty(IConfigurationConstant.RUNNINGMODE_PRP)).booleanValue();
//			logPath=ResourceManager.class.getResource(".").getFile()+"log";
			String logPath=props.getProperty(IConfigurationConstant.LOG_DIR);
//			System.out.println("R4 Root "+root);
			System.setProperty("ijunit.log.path",logPath);
			File logFile = new File(logPath);
			if(!logFile.exists()){
				logFile.mkdir();
			}
//			System.out.println("R5");
			logger = Logger.getLogger(ResourceManager.class);
			logger.debug("Successfully Loaded Properties");
			System.out.println("Successfully Loaded Resource Properties");
		}
	}
	
	public static File getFile(String fileName) throws IJunitException 
	{
		File file = null;
		try{
			
			String ext = fileName.substring(fileName.indexOf('.')+1);
			String resourceDir = System.getenv("DATUMGEN_HOME")+FILE_SEPRATOR+"resource";
			if(ext.equalsIgnoreCase(TESTCONDITION_MAP_FILE_EXT) || ext.equalsIgnoreCase(TESTCONDITION_FILE_EXT))
			{
				file = new File(root+FILE_SEPRATOR+TESTCONDITION_DIR+FILE_SEPRATOR+fileName);
			}else if (ext.equalsIgnoreCase(OBJECTXMLDATA_MAP_FILE_EXT))
			{
				file = new File(root+FILE_SEPRATOR+OBJECTXMLDATA_DIR+FILE_SEPRATOR+fileName);
			}else if (ext.equalsIgnoreCase(OBJECTXMLDATA_FILE_EXT))
			{
				file = new File(root+FILE_SEPRATOR+OBJECTXMLDATA_DIR+FILE_SEPRATOR+fileName);
			}else if(ext.equalsIgnoreCase(DATABASE_CONF_FILE_EXT))
			{
				file = new File(root+FILE_SEPRATOR+DATABASE_DIR+FILE_SEPRATOR+fileName);
			}else if( ext.equalsIgnoreCase(MESSAGE_FILE_EXT))
			{
				file = new File(resourceDir+FILE_SEPRATOR+MESSAGE_DIR+FILE_SEPRATOR+fileName);
			}else if( fileName.equalsIgnoreCase(IFileConstant.LOG4J_FILE))
			{
				file = new File(resourceDir+FILE_SEPRATOR+LOG4J_DIR+FILE_SEPRATOR+fileName);
			}else if( ext.equalsIgnoreCase(IMAGE_FILE_EXT))
			{
				file = new File(resourceDir+FILE_SEPRATOR+IMAGE_DIR+FILE_SEPRATOR+fileName);
			}else if( ext.equalsIgnoreCase(PROPERTY_FILE_EXT))
			{
				File dir = new File(System.getProperty("user.home")+FILE_SEPRATOR+"iJunit");
				if(!dir.exists()){
					dir.mkdirs();
				}
				file = new File(System.getProperty("user.home")+FILE_SEPRATOR
						+"iJunit"+FILE_SEPRATOR+fileName);
			
			}else if(ext.equalsIgnoreCase(SQL_FILE_EXT))
			{
				file = new File(root+FILE_SEPRATOR+SQLFILES_DIR+FILE_SEPRATOR+getProperty(IConfigurationConstant.VERSION)
						+FILE_SEPRATOR+fileName);
			}else 
			{
				throw new FileNotFoundException();
			}
			}catch (FileNotFoundException e) {
				logger.error("In ResourceManager.getFile "+ErrorMessageParser.getMessage(IExceptionCodes.FILENOT_FOUND_EXP, 
						new String[]{fileName}), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FILENOT_FOUND_EXP,new String[]{fileName}),e);
			}
		//logger.debug("File Return from ResourceManager.getFile method "+file.getAbsolutePath());
		return file; 
	}
	
	public static String getProperty(String propertyName){
		String propValue = (String)props.get(propertyName);
		if(propValue == null){
			logger.warn("Property not found in property file "+resourceFileName);
		}
		return propValue;
	}
	
}
