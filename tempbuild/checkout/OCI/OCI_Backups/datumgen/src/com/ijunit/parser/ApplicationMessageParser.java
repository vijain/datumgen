/**
 * 
 */
package com.ijunit.parser;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ijunit.common.Logger;
import com.ijunit.resource.ResourceManager;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;

/**
 * @author vcjain
 *
 */
public class ApplicationMessageParser extends MessageParser 
{
	static Logger logger = Logger.getLogger(ApplicationMessageParser.class);
	
	private static ApplicationMessageParser parser = null;
	
	static 
	{
		try {
			parser = new ApplicationMessageParser();
			parser.parse(ResourceManager.getFile(IFileConstant.APPLICATION_MSG_FILE).toString());
		} catch (Exception e) 
		{
			logger.error("Problem occured while parsing Application Messges file", e);
		} 
	}
	private ApplicationMessageParser() 
	{
			
	}

	public static String getMessage(int code)
	{
		if(parser != null && parser.msgMap != null){
			return (String)parser.msgMap.get(code);
		}else{
			return "";
		}
	}
	
	public static String getMessage(int code, String[] arguments)
	{
		StringBuffer  message = new StringBuffer("");
		if(parser != null && parser.msgMap != null){
			message.append((String)parser.msgMap.get(code));
		}
		if(message.length() > 0 && message.indexOf("${") != -1){
			int index = -1;
			for (int i = 0,count=1; i < arguments.length; i++,count++) {
				String key = "${"+count+"}";
				index = message.indexOf(key);
				message = message.replace(index,index+(key.length()),arguments[i]);
			}
		}else{
			return message.toString();
		}
		return message.toString();
	}
	
	
	@Override
	public void parse(String fileName) throws IJunitException 
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			saxParser = factory.newSAXParser();
			saxParser.parse(fileName,this);
		}catch (MalformedURLException e) {  
			    InputSource input = new InputSource("file:///" + fileName);  
			    try{
			    	saxParser.parse(input, this);  
			    }catch(Exception e1){
					logger.error("In ObjectDataParser.parse "+ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
							new String[]{"file:///" + fileName}), e1);
					throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
							new String[]{fileName}),e1);
			    }
		} catch (Exception exception) {
			logger.error("In ObjectDataParser.parse "+ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
					new String[]{fileName}), exception);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,
					new String[]{fileName}),exception);
		}
	}

}
