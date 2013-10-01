/**
 * 
 */
package com.ijunit.common.utility.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;

/**
 * @author vcjain
 *
 */
public class DBConfigurationXMLWriter extends FileWriter 
{
	
	static Logger logger = Logger.getLogger(DBConfigurationXMLWriter.class);
	
	
	//template for database.cfg.xml file.
	private String rootTag = "<CONFIGURATIONS>\n"+
												"${configuration}"+
											"</CONFIGURATIONS>";
	private String template = "\t<CONFIGURATION name=\"${name}\">\n"+
													"\t\t<DATABASE>${database}</DATABASE>\n"+
													"\t\t<DRIVER>${driver}</DRIVER>\n"+
													"\t\t<DATABASEURL>${databaseurl}</DATABASEURL>\n"+
													"\t\t<HOST>${host}</HOST>\n"+
													"\t\t<PORT>${port}</PORT>\n"+
													"\t\t<USER>${user}</USER>\n"+
													"\t\t<PASSWORD>${password}</PASSWORD>\n"+
												"\t</CONFIGURATION>";
	
	public void write(Object obj,File file) throws IJunitException
	{
		logger.debug("Entered in DBConfigurationXMLWriter.write()");
		StringBuffer buffer = new StringBuffer();
		Map<String, Map> map = (Map)obj;
		Iterator it = map.keySet().iterator();
		String key = "";
		logger.debug("In DBConfigurationXMLWriter.write message= template key map "+map);
		while(it.hasNext()){
			key = it.next().toString();
			write(buffer, map.get(key),template);
		}
		Map<String, String> outerMap = new HashMap<String,String>();
	
		outerMap.put(IConfigurationConstant.CONFIGURATION_TMP, buffer.toString());
		
		buffer = new StringBuffer(xmlHeader);
		write(buffer,outerMap, rootTag);
		appendToFile(buffer.toString(), file);
		logger.debug("Template after placing actual value "+buffer.toString());
		appendToFile(buffer.toString(), file);
		logger.debug("Exited from DBConfigurationXMLWriter.write()");
	}
	
	private void write(StringBuffer buffer, Map map, String template)
	{
		logger.debug("Entered in ObjectXMLDataWriter.write(Buffer, Map, String)");
		buffer.append(template).append("\n");
		Iterator it = map.keySet().iterator();
		String key = "";
		logger.debug("In ObjectXMLDataXMLWriter.write message= template key map "+map);
		while(it.hasNext()){
			key = it.next().toString();
			int index = buffer.indexOf(key);
			String value = map.get(key)!=null?map.get(key).toString():"";
			buffer = buffer.replace(index,index+(key.length()),value);
		}
		logger.debug("Template after placing actual value "+buffer.toString());
		logger.debug("Exited from ObjectXMLDataWriter.write(Buffer, Map, String)");
		
	} 
	
}
