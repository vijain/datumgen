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
public class ObjectXMLMappingWriter extends FileWriter
{

	static Logger logger = Logger.getLogger(ObjectXMLMappingWriter.class);
	
//	Template of XML File
	private String fileHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	private String rootTag = "<OBJECTS>\n"+
												"${object}"+
											"</OBJECTS>";
	private String objectTag = "\t<OBJECT class=\"${objectclass}\" table=\"${objecttable}\">\n"+
													"\t\t<FILE>${datafile}</FILE>\n"+
													"\t\t<SQLFILE>${sqlfile}</SQLFILE>\n"+
													"\t\t<DATAHELPER>${datahelper}</DATAHELPER>\n"+
												"\t</OBJECT>";
	
	public void write(Object obj, File file) throws IJunitException {
		logger.debug("Entered in ObjectXMLMappingWriter.write()");
		Map map = (Map)obj;
		StringBuffer buffer = new StringBuffer();
		
		write(buffer, map, objectTag);
		map.put(FileWriter.OCM_OBJECT_VAR, buffer.toString());
		buffer = new StringBuffer(fileHeader);
		write(buffer, map, rootTag);
		appendToFile(buffer.toString(), file);
		logger.debug("Exited from ObjectXMLMappingWriter.write()");
	}

	private void write(StringBuffer buffer, Map map, String template)
	{
		logger.debug("Entered in ObjectXMLMappingWriter.write(Buffer, Map, String)");
		buffer.append(template).append("\n");
		Iterator it = map.keySet().iterator();
		String key = "";
		logger.debug("In ObjectXMLMappingWriter.write message= template key map "+map);
		while(it.hasNext()){
			key = it.next().toString();
			int index = buffer.indexOf(key);
			if(index != -1){
				String value = map.get(key)!=null && !map.get(key).equals("")?map.get(key).toString():"";
				buffer = buffer.replace(index,index+(key.length()),value);
			}
		}
		logger.debug("Template after placing actual value "+buffer.toString());
		logger.debug("Exited from ObjectXMLMappingWriter.write(Buffer, Map, String)");
	}
	
}
