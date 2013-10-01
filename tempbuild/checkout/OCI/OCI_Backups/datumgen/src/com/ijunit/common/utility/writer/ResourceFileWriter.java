/**
 * 
 */
package com.ijunit.common.utility.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;

/**
 * @author vcjain
 *
 */
public class ResourceFileWriter extends FileWriter 
{

	static Logger logger = Logger.getLogger(ResourceFileWriter.class);
	
	private final String propertyDesc = "####################################\n"+
		"Decsription of properties define below\n"+
		"###################################\n"+
		"#This is running mode of tool. keep the defualt value.\n"+
		"#runningmode.standalone=true       \n"+
		"###################################\n"+
		"#This propetiy defines the log directory where log related to DatumGen will be generated \n"+
		"#logDir=E:/datumgen/log\n"+
		"###################################\n"+
		"###################################\n"+
		"#This propetiy defines the resource directory. Resource directory is the directory where all oxd, db related file will be kept.\n"+
		"#root=E:/eclipse-workspaces/Test/emphsys-junit/TestSrc/resource\n"+
		"###################################\n"+
		"###################################\n"+
		"#This propetiy directory where the i-emphsys classes are placed.\n"+
		"#classPathDir=E:/eclipse-workspaces/Test/emphsys-junit/build/classes\n"+
		"###################################\n"+
		"###################################\n"+
		"#This propetiy represent version.\n"+
		"#version=5.2\n"+
		"###################################\n";
		  
	public void write(Object obj, File file) throws IJunitException
	{
		logger.debug("Entered in ResourceFileWriter.write()");
		StringBuffer buffer = new StringBuffer(propertyDesc).append("\n\n");
		Map map = (Map)obj;
		Iterator it = map.keySet().iterator();
		String key = "";
		logger.debug("In ResourceFileWriter.write() message= template key map "+map);
		while(it.hasNext())
		{
			key = (String)it.next();
			buffer.append(key).append("=").append(map.get(key)).append("\n");
			logger.debug("In ResourceFileWriter.write() message= Properties "+buffer);
		}
		logger.debug("In ResourceFileWriter.write() message= Properties Set "+buffer);
		appendToFile(buffer.toString(), file);
		logger.debug("Exited from ResourceFileWriter.write()");
	}

	
}
