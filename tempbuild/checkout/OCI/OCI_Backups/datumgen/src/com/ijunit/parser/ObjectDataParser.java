/**
 * 
 */
package com.ijunit.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.DataTypes;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.model.AttributeData;

/**
 * @author vcjain
 *
 */
public class ObjectDataParser extends DefaultHandler
{
	
	static Logger logger = Logger.getLogger(ObjectDataParser.class);
	
	//Constant
	static final String OBJECT = "OBJECT";
	static final String OBJECT_CLASS = "class";
	static final String ATTRIBUTE = "ATTRIBUTE";
	static final String NAME = "NAME";
	static final String TYPE = "TYPE";
	static final String COLUMN = "COLUMN";
	static final String VALUE = "VALUE";
	static final String SETMETHOD = "SETMETHOD";
	static final String GETMETHOD = "GETMETHOD";
	static final String PRIMARYKEY = "PRIMARYKEY";
	static final String COLUMNDISPLAYSIZE = "COLUMNDISPLAYSIZE";
	static final String COLUMNTYPE = "COLUMNTYPE";
	static final String COLUMNTYPENAME = "COLUMNTYPENAME";
	static final String PRECISION = "PRECISION";
	static final String SCALE = "SCALE";
	static final String AUTOINCREMENT = "AUTOINCREMENT";
	static final String NULLABLE = "NULLABLE";
	
	private AttributeData data = null;
	private StringBuffer buffer = new StringBuffer();
	private Vector attributeDataList = null;
	private String dataClassName = null;
	
	public ObjectDataParser() 
	{
	}
	
	@Override
	public void startDocument() throws SAXException 
	{
		logger.debug("Entered in ObjectDataParser.startDocument()");
		attributeDataList = new Vector();
		logger.debug("Exited from ObjectDataParser.startDocument()");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		//logger.debug("Entered in ObjectDataParser.startElement()");
		if(qName.equalsIgnoreCase(OBJECT))
		{
			dataClassName = attributes.getValue(OBJECT_CLASS);
		}else if(qName.equalsIgnoreCase(ATTRIBUTE))
		{
			data = new AttributeData();
		}
		//logger.debug("Exited from ObjectDataParser.startElement()");
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException 
	{
		for (int i=0; i<length; i++) 
		{
			buffer.append(ch[start+i]);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException 
	{
		if(qName.equalsIgnoreCase(ATTRIBUTE))
		{
			attributeDataList.add(data);
		}else if(qName.equalsIgnoreCase(NAME))
		{
			data.setName(buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(TYPE))
		{
			data.setType(Integer.parseInt(buffer.toString().trim()));
		}else if(qName.equalsIgnoreCase(SETMETHOD))
		{
			data.setSetMethod(buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(COLUMN))
		{
			data.setColumn(buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(VALUE))
		{
			data.setValue(buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(GETMETHOD))
		{
			data.setGetMethod(buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(PRIMARYKEY))
		{
			data.setPrimaryKey(Boolean.parseBoolean(buffer.toString().trim()));
		}else if(qName.equalsIgnoreCase(COLUMNDISPLAYSIZE))
		{
			data.setColumnDisplaySize(Integer.parseInt(buffer.toString().trim()));
		}else if(qName.equalsIgnoreCase(COLUMNTYPE))
		{
			data.setColumnType(Integer.parseInt(buffer.toString().trim()));
		}else if(qName.equalsIgnoreCase(COLUMNTYPENAME))
		{
			data.setColumnTypeName(buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(PRECISION))
		{
			data.setPrecision(Integer.parseInt(buffer.toString().trim()));
		}else if(qName.equalsIgnoreCase(SCALE))
		{
			data.setScale(Integer.parseInt(buffer.toString().trim()));
		}else if(qName.equalsIgnoreCase(AUTOINCREMENT))
		{
			data.setAutoIncrement(Boolean.parseBoolean(buffer.toString().trim()));
		}else if(qName.equalsIgnoreCase(NULLABLE))
		{
			data.setIsNullable(Integer.parseInt(buffer.toString().trim()));
		}
		buffer = new StringBuffer();
	}
	
	@Override
	public void endDocument() throws SAXException 
	{
			//logger.debug("Entered in ObjectDataParser.endDocument()");
			
			//logger.debug("Exited from ObjectDataParser.parse()");
	}
	

	public void parse(String fileName) throws IJunitException
	{
		SAXParser parser = null;
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			parser.parse(fileName,this);
		}catch (MalformedURLException e) {  
		    InputSource input = new InputSource("file:///" + fileName);  
		    try{
		    	parser.parse(input, this);  
		    }catch(Exception e1){
				logger.error("In ObjectDataParser.parse "+ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
						new String[]{"file:///" + fileName}), e1);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
						new String[]{fileName}),e1);
		    }
		}catch(Exception e){
			logger.error("In ObjectDataParser.parse "+ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP),e);
		}
	}
	
	public static void main(String[] args) {
		ObjectDataParser parser = new ObjectDataParser();
		try {
			parser.parse("PatientData.oxd.xml");
			//System.out.println("\n\n\n @@@@@@@@ "+parser.attributeDataList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * @return the attributeDataList
	 */
	public Vector getAttributeDataList() {
		return attributeDataList;
	}

	/**
	 * @param attributeDataList the attributeDataList to set
	 */
	public void setAttributeDataList(Vector attributeDataList) {
		this.attributeDataList = attributeDataList;
	}

	/**
	 * @return the dataClassName
	 */
	public String getDataClassName() {
		return dataClassName;
	}

	/**
	 * @param dataClassName the dataClassName to set
	 */
	public void setDataClassName(String dataClassName) {
		this.dataClassName = dataClassName;
	}
}
