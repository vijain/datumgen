/**
 * 
 */
package com.ijunit.common.utility.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.resource.ResourceManager;

/**
 * Class will act as Super class
 * @author vcjain
 *
 */
public abstract class FileWriter 
{
	static Logger logger = Logger.getLogger(FileWriter.class);
	//Constants for Object Confgiuration Mapping.
	public static String OCM_OBJECT_VAR = "${object}";
	public static String OCM_OBJECTCLASS_VAR = "${objectclass}";
	public static String OCM_OBJECTTABLE_VAR = "${objecttable}";
	public static String OCM_DATAFILE_VAR = "${datafile}";
	public static String OCM_SQLFILE_VAR = "${sqlfile}";
	public static String OCM_DATAHELPER_VAR = "${datahelper}";
	
	public String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	
	public abstract void write(Object obj,File file) throws IJunitException;
	
	public void appendToFile(String toAppend, File file) throws IJunitException 
	{
		logger.debug("Entered in ResourceFileWriter.appendToFile()");
		BufferedWriter writer = null;
		try{
			if(!file.exists())
			{
				file.createNewFile();
			}
			writer = new BufferedWriter(new java.io.FileWriter(file));
			writer.write(toAppend);
			writer.flush();
			logger.debug("Exited from ResourceFileWriter.enclosing_method()");
		}catch (IOException e) {
			logger.error(ErrorMessageParser.getMessage(IExceptionCodes.FILEWRITER_EXP
					,new String[]{file.toString()}), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FILEWRITER_EXP
					,new String[]{file.toString()}),e);
		}finally{
			try {
				if(writer != null){
					writer.close();
				}
			} catch (IOException e) {
				logger.error("In ResourceFileWriter.appendToFile "+ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP), e);
			}
		}
	}
	
	
}
