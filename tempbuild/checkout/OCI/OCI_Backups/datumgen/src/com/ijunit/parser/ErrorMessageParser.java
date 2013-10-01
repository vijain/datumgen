/**
 * 
 */
package com.ijunit.parser;

import java.net.MalformedURLException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.resource.ResourceManager;

/**
 * @author vcjain
 *
 */
public class ErrorMessageParser extends MessageParser 
{
	static Logger logger = Logger.getLogger(ErrorMessageParser.class);
	private static ErrorMessageParser parser = null;

	static
	{
		try
		{
			parser = new ErrorMessageParser();
			parser.parse(ResourceManager.getFile(IFileConstant.ERROR_MSG_FILE).toString());
		}catch (Exception e) 
		{
			logger.error("Problem occured while parsing Error messges file", e);
		} 
	}
	
	public static String getMessage(int code)
	{
		
		if(parser !=null && parser.msgMap != null){
			return (String)parser.msgMap.get(new Integer(code));	
		}else{
			return "";
		}
		
	}
	
	public static String getMessage(int code, String[] params)
	{
		String message;
		if(parser != null && parser.msgMap != null){
			message = (String)parser.msgMap.get(new Integer(code));
			logger.debug("Message Obtain fom parser "+message);
			for (int i = 0; i < params.length; i++) 
			{
				String toReplace = "${"+(i+1)+"}";
				logger.debug("toReplace String "+toReplace);
				int index = message.indexOf(toReplace);
				StringBuffer buffer = new StringBuffer(message);
				message = buffer.replace(index, index+toReplace.length(), params[i]).toString();
			}
			logger.debug("Message after placing paramatere value "+message);
		}else{
			message = "";
		}
		return message;
	}
	
	@Override
	public void parse(String fileName) throws IJunitException 
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			saxParser = factory.newSAXParser();
			saxParser.parse(fileName,this);
		} catch (MalformedURLException e) {  
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
