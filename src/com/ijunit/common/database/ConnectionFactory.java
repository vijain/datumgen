/**
 * 
 */
package com.ijunit.common.database;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ijunit.common.Logger;



public class ConnectionFactory
{
	
	static Logger logger = Logger.getLogger(ConnectionFactory.class);
	static int counter = 0;
	
	static Class junitConnectionClass = null;
	public static Map<String,JUnitConnection> connectionMap = new HashMap<String,JUnitConnection>();
	static
	{
		try {
			junitConnectionClass = Class.forName("com.ijunit.common.database.JUnitConnection");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection()
	{
		try {
			logger.debug("Entered in ConnectionFactory.getConnection()");
			JUnitConnection connectionObj = null;
			connectionObj = (JUnitConnection)junitConnectionClass.newInstance();
			connectionMap.put(Thread.currentThread().getName()+counter, connectionObj);
			counter++;
			logger.debug("In ConnectionFactory.getConnection message=A Connection is added to map="+connectionMap);
			return (Connection)connectionObj;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static JUnitConnection getConnection(String key){
		return connectionMap.get(key);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public  static void clear() throws Exception{
		logger.debug("Entered in ConnectionFactory.clear()");
		Set keySet = connectionMap.keySet();
		for (Iterator iter = keySet.iterator(); iter.hasNext();) {
			JUnitConnection connectionObj = (JUnitConnection) connectionMap.get(iter.next());
			if(connectionObj !=null){
				logger.debug("In ConnectionFactory.clear message= Clearing Connection object "+connectionObj);
				connectionObj.closeWraper();
				connectionObj = null;
			}
		}
		connectionMap.clear();
		resetCounter();
		logger.debug("Exited from ConnectionFactory.clear()");
	}
	
	public static void resetCounter(){
		logger.debug("Entered in ConnectionFactory.resetCounter()");
		counter = 0;
		logger.debug("Exited from ConnectionFactory.resetCounter() Reset Value of Counter "+counter);
	}
	
	public static Connection getConnectionObject(int index){
		return connectionMap.get(Thread.currentThread().getName()+index);
	}
	
}