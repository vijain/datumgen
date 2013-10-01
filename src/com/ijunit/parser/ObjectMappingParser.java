package com.ijunit.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.resource.ResourceManager;

public class ObjectMappingParser extends DefaultHandler 
{

	static Logger logger = Logger.getLogger(ObjectMappingParser.class);
	
	public static String OBJECT = "OBJECT";
	public static String OBJECT_CLASS = "class";
	public static String OBJECT_TABLE = "table";
	public static String DATAFILE = "FILE";
	public static String DATAHELPER = "DATAHELPER";
	public static String OBJECTS = "OBJECTS";
	public static String SQLFILE = "SQLFILE";
	
	private static Map map = new HashMap();
	private Map innerMap = null;
	private StringBuffer buffer = new StringBuffer();
	
	private static ObjectMappingParser parser;
	
	private ObjectMappingParser() throws IJunitException 
	{
		parse(IFileConstant.OBJECTXMLDATA_MAP_FILE);
	}
	
	@Override
	public void startDocument() throws SAXException 
	{
		logger.debug("Entered in ObjectMappingParser.startDocument()");
		
		logger.debug("Exited from ObjectMappingParser.enclosing_method()");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		logger.debug("Entered in ObjectMappingParser.startElement()");
		if(qName.equalsIgnoreCase(OBJECT))
		{
			innerMap = new HashMap();
			map.put(attributes.getValue(OBJECT_CLASS),innerMap);
			innerMap.put(OBJECT_TABLE, attributes.getValue(OBJECT_TABLE));
		}
		logger.debug("Exited from ObjectMappingParser.startElement()");
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException 
	{
		logger.debug("Entered in ObjectMappingParser.characters()");
		for (int i=0; i<length; i++) 
		{
			buffer.append(ch[start+i]);
		}
		logger.debug("Exited from ObjectMappingParser.characters()");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException 
	{
		logger.debug("Entered in ObjectMappingParser.endElement()");
		if(qName.equalsIgnoreCase(DATAFILE))
		{
			innerMap.put(DATAFILE,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(DATAHELPER))
		{
			innerMap.put(DATAHELPER,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(SQLFILE))
		{
			innerMap.put(SQLFILE,buffer.toString().trim());
		}
		buffer = new StringBuffer();
		logger.debug("Exited from ObjectMappingParser.endElement()");
	}
	
	@Override
	public void endDocument() throws SAXException 
	{
		logger.debug("Entered in ObjectMappingParser.endDocument()");
		logger.debug("Exited from ObjectMappingParser.getFile()");
	}
	
	public static String getFile(String className) throws  IJunitException
	{
		if(parser == null)
		{
			parser = new ObjectMappingParser();
		}
		return (String)((ArrayList)map.get(className)).get(1);
	}
	
	public static String getDataHelper(String className) throws  IJunitException
	{
		if(parser == null)
		{
			parser = new ObjectMappingParser();
		}
		return getProperty(className, DATAHELPER);
	}
	
	public static String getProperty(String className, String key) throws  IJunitException{
		if(parser == null)
		{
			parser = new ObjectMappingParser();
		}
		Map tempMap = (Map)map.get(className);
		return (String)tempMap.get(key);
	}
	
	public static void main(String[] args) throws IJunitException  
	{
		ObjectMappingParser handler = new ObjectMappingParser();
		handler.parse(IFileConstant.OBJECTXMLDATA_MAP_FILE);
		
	}
	
	public void parse(String fileName) throws IJunitException
	{
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(ResourceManager.getFile(fileName),this);
		}catch (Exception e) {
			logger.error("In ObjectMappingParser.parse "+ErrorMessageParser.getMessage(IExceptionCodes.PARSER_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.PARSER_EXP),e);
		}
	}
	
	public static void resetParser(){
		parser = null;
	}
}
