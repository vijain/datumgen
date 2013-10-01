/**
 * 
 */
package com.ijunit.common.utility;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.writer.BasicFileWriter;
import com.ijunit.common.utility.writer.DBConfigurationXMLWriter;
import com.ijunit.common.utility.writer.FileWriter;
import com.ijunit.common.utility.writer.ObjectXMLDataWriter;
import com.ijunit.common.utility.writer.ObjectXMLMappingWriter;
import com.ijunit.common.utility.writer.ResourceFileWriter;
import com.ijunit.common.utility.writer.SQLFileWriter;
import com.ijunit.parser.ErrorMessageParser;



/**
 * @author vcjain
 *
 */
public class FileWriterFactory 
{
	static FileWriterFactory factory = new FileWriterFactory();
	
	static Logger logger = Logger.getLogger(FileWriterFactory.class);
	
	public static FileWriter getFileWriter(String type) throws IJunitException
	{
		try{
			if(type.equalsIgnoreCase(IFileConstant.DATABASE_CONF_WRITER)){
				return new DBConfigurationXMLWriter();
			}else if(type.equalsIgnoreCase(IFileConstant.RESOURCE_CONF_WRITER)){
				return new ResourceFileWriter();
			}else if(type.equalsIgnoreCase(IFileConstant.OBJECTXML_DATA_WRITER)){
				return new ObjectXMLDataWriter();
			}else if(type.equalsIgnoreCase(IFileConstant.OBJECTXML_MAP_WRITER)){
				return new ObjectXMLMappingWriter();
			}else if(type.equalsIgnoreCase(IFileConstant.SQLFILE_WRITER)){
				return new SQLFileWriter();
			}else if(type.equalsIgnoreCase(IFileConstant.BASICFILE_WRITER)){
				return new BasicFileWriter();
			}else{
				throw new ClassNotFoundException();
			}
		}catch (ClassNotFoundException e) {
			logger.error(ErrorMessageParser.getMessage(IExceptionCodes.FILEWRITER_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FILEWRITER_EXP),e);
		} 
	}
}
