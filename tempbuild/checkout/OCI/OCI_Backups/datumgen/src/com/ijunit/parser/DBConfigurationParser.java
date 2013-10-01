/**
 * 
 */
package com.ijunit.parser;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.resource.ResourceManager;

/**
 * @author vcjain
 *
 */
public class DBConfigurationParser extends DefaultHandler 
{

	static Logger logger = Logger.getLogger(DBConfigurationParser.class);
	
	Map<String,Map> map = null;
	Map<String,String> innerMap = null;
	StringBuffer buffer = new StringBuffer();
	
	static DBConfigurationParser dbParser = null;
	static{
		try {
			parse(ResourceManager.getFile(IFileConstant.DATABASE_CONF_FILE).toString());
		} catch (IJunitException e) {
			logger.error("In DBConfigurationParser.enclosing_method "+ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), e);
			JOptionPane.showMessageDialog(null,ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static synchronized  DBConfigurationParser getInstance(){
		if(dbParser == null){
			dbParser = new DBConfigurationParser();
		}
		return dbParser;
	}
	
	public static void parse(String fileName) throws IJunitException
	{
		logger.debug("Entered in DBConfigurationParser.parse()");
		SAXParser parser = null;
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			parser.parse(fileName,getInstance());
			logger.debug("Exited from DBConfigurationParser.parse()");
		}catch (MalformedURLException e) {  
			    InputSource input = new InputSource("file:///" + fileName);  
			    try{
			    	parser.parse(input, dbParser);  
			    }catch(Exception e1){
					logger.error("In ObjectDataParser.parse "+ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
							new String[]{"file:///" + fileName}), e1);
					throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
							new String[]{fileName}),e1);
				}
		}catch(Exception e){
			logger.error("In ObjectDataParser.parse "+ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
					new String[]{fileName}), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
					new String[]{fileName}),e);
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		map = new HashMap<String,Map>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equalsIgnoreCase(IConfigurationConstant.CONFIGURATION)){
			innerMap = new HashMap<String,String>();
			map.put(attributes.getValue(IConfigurationConstant.CONFIGURATION_NAME),innerMap);
		}
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
		if(qName.equalsIgnoreCase(IConfigurationConstant.DATABASE))
		{
			innerMap.put(IConfigurationConstant.DATABASE,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(IConfigurationConstant.DRIVER))
		{
			innerMap.put(IConfigurationConstant.DRIVER,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(IConfigurationConstant.DATABASEURL))
		{
			innerMap.put(IConfigurationConstant.DATABASEURL,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(IConfigurationConstant.HOST))
		{
			innerMap.put(IConfigurationConstant.HOST,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(IConfigurationConstant.PORT))
		{
			innerMap.put(IConfigurationConstant.PORT,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(IConfigurationConstant.USER))
		{
			innerMap.put(IConfigurationConstant.USER,buffer.toString().trim());
		}else if(qName.equalsIgnoreCase(IConfigurationConstant.PASSWORD))
		{
			innerMap.put(IConfigurationConstant.PASSWORD,buffer.toString().trim());
		}
		buffer = new StringBuffer();
	}

	public String getProperty(String configurationName, String propertyName){
		
		return (String) getInstance().map.get(configurationName).get(propertyName);
	}

	/**
	 * @return the map
	 */
	public Map<String, Map> getMap() {
		return map;
	}
	
	
}
