/**
 * 
 */
package com.ijunit.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ijunit.common.exception.IJunitException;

/**
 * @author vcjain
 *
 */
public abstract class MessageParser extends DefaultHandler 
{
	
	private static String MESSAGE = "MESSAGE";
	private static String MESSAGE_CODE = "code";
	
	
	Map msgMap = new HashMap();
	StringBuffer buffer = new StringBuffer();
	Integer code;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		if(qName.equalsIgnoreCase(MESSAGE))
		{
			code = new Integer(attributes.getValue(MESSAGE_CODE).trim());
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
		if(qName.equalsIgnoreCase(MESSAGE))
		{
			msgMap.put(code,buffer.toString().trim());
		}
		buffer = new StringBuffer();
	}
	
	
	public abstract void parse(String fileName) throws ParserConfigurationException, SAXException, IOException,IJunitException;
	
}
