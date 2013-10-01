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
import java.util.Vector;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.swing.model.AttributeData;
import com.ijunit.swing.model.IAttributeDataModel;

/**
 * @author vcjain
 *
 */
public class ObjectXMLDataWriter extends FileWriter
{

	static Logger logger = Logger.getLogger(ObjectXMLDataWriter.class);

	//Template of XML File
	private String rootTag = "<OBJECT class=\"${classname}\">\n"+
								"\t<ATTRIBUTES>\n"+
								"${attribute}"+
								"\t</ATTRIBUTES>\n"+
							"</OBJECT>";
	private String attributeTag = "\t\t<ATTRIBUTE>\n"+
										"\t\t\t\t<NAME>${name}</NAME>\n"+
										"\t\t\t\t<TYPE>${type}</TYPE>\n"+
										"\t\t\t\t<SETMETHOD>${setmethod}</SETMETHOD>\n"+
										"\t\t\t\t<COLUMN>${column}</COLUMN>\n"+
										"\t\t\t\t<VALUE>${value}</VALUE>\n"+
										"\t\t\t\t<PRECISION>${precision}</PRECISION>\n"+
										"\t\t\t\t<SCALE>${scale}</SCALE>\n"+
										"\t\t\t\t<COLUMNDISPLAYSIZE>${columndisplaysize}</COLUMNDISPLAYSIZE>\n"+
										"\t\t\t\t<COLUMNTYPE>${columntype}</COLUMNTYPE>\n"+
										"\t\t\t\t<COLUMNTYPENAME>${columntypename}</COLUMNTYPENAME>\n"+
										"\t\t\t\t<PRIMARYKEY>${primarykey}</PRIMARYKEY>\n"+
										"\t\t\t\t<AUTOINCREMENT>${autoincrement}</AUTOINCREMENT>\n"+
										"\t\t\t\t<NULLABLE>${nullable}</NULLABLE>\n"+
										"\t\t\t\t<GETMETHOD>${getmethod}</GETMETHOD>\n"+
									"\t\t</ATTRIBUTE>";
	
	private final String NAME_VAR = "${name}";
	private final String TYPE_VAR = "${type}";
	private final String SETMETHOD_VAR = "${setmethod}";
	private final String COLUMN_VAR = "${column}";
	private final String VALUE_VAR = "${value}";
	private final String CLASSNAME_VAR = "${classname}";
	private final String ATTRIBUTE_VAR = "${attribute}";
 	private final String  GETMETHOD_VAR = "${getmethod}";
 	private final String  PRIMARYKEY_VAR = "${primarykey}";
 	private final String  COLUMNDISPLAYSIZE_VAR = "${columndisplaysize}";
 	private final String  COLUMNTYPE_VAR = "${columntype}";
 	private final String  COLUMNTYPENAME_VAR = "${columntypename}";
 	private final String  PRECISION_VAR = "${precision}";
 	private final String  SCALE_VAR = "${scale}";
 	private final String  AUTOINCREMENT_VAR = "${autoincrement}";
 	private final String  NULLABLE_VAR = "${nullable}";
 	
	
	public void write(Object obj, File file) throws IJunitException {
		logger.debug("Entered in ObjectXMLDataWriter.write(Object, File)");

		Map<String,Object> map = new HashMap<String,Object> ();
		IAttributeDataModel dataModel = (IAttributeDataModel)obj;
		Vector vec = (Vector)dataModel.getAllAttributeData();
		
		StringBuffer buffer = new StringBuffer();
		for (Iterator iter = vec.iterator(); iter.hasNext();) {
			AttributeData data = (AttributeData) iter.next();
			map.put(NAME_VAR, data.getName());
			map.put(TYPE_VAR, data.getType());
			map.put(SETMETHOD_VAR, data.getSetMethod());
			map.put(COLUMN_VAR, data.getColumn());
			map.put(VALUE_VAR, data.getValue());
			map.put(GETMETHOD_VAR, data.getGetMethod());
			map.put(PRIMARYKEY_VAR, data.isPrimaryKey());
			map.put(COLUMNDISPLAYSIZE_VAR, data.getColumnDisplaySize());
			map.put(COLUMNTYPE_VAR, data.getColumnType());
			map.put(COLUMNTYPENAME_VAR, data.getColumnTypeName());
			map.put(PRECISION_VAR, data.getPrecision());
			map.put(SCALE_VAR, data.getScale());
			map.put(AUTOINCREMENT_VAR, data.isAutoIncrement());
			map.put(NULLABLE_VAR, data.getIsNullable());
			write(buffer,map,attributeTag);
			map.clear();
		}
		map.put(CLASSNAME_VAR, dataModel.getClassName());
		map.put(ATTRIBUTE_VAR, buffer.toString());
		logger.debug("In ObjectXMLDataWriter.write message= Attribute tag for all attributes "+buffer);
		buffer = new StringBuffer(xmlHeader);
		write(buffer, map, rootTag);
		appendToFile(buffer.toString(), file);
		logger.debug("In ObjectXMLDataWriter.write message= Full Data XML "+buffer);
		logger.debug("Exited from ObjectXMLDataWriter.write(Object, File)");
	}

//	public void write(Map map, File file) throws IOException {
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(fileHeader).append(rootTag);
//		
//	}
	
	
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
