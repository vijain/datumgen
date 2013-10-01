package com.ijunit.common;

import java.net.URL;
import java.util.Hashtable;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.ijunit.common.constant.IFileConstant;
import com.ijunit.resource.ResourceManager;
import com.ijunit.resource.ResourceManagerBase;




/**
 * Logger
 * <br>
 * This Class encapsulates access to Apache's Log4j logger.
 * To obtain an instance, utilize the getLogger() method.
 * <br>
 * @author Pete Byer
 *  
 */
public class Logger {
	private static final String _defaultLoggerName = "com.ijunit";

	private org.apache.log4j.Logger log4jLogger = null;
	private static Hashtable loggers = new Hashtable();
	private static int nLevel = Level.WARN_INT;
	static
	{
		try
		{
//			System.out.println("Looger: 1");
			if(ResourceManagerBase.runningMode)
			{
//				System.out.println("Looger: 2");
//				System.out.println("Looger: 4");
				nLevel = Boolean.valueOf(System.getProperty("logGTest.debug")) ? Level.DEBUG_INT : nLevel;
				DOMConfigurator.configure(ResourceManager.getFile(IFileConstant.LOG4J_FILE).getAbsolutePath());
				 
//				System.out.println("Looger: 5");
			}
//			System.out.println("Looger: 6");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	//Singleton, so disable instantiation.
	private Logger() {
	};

	/**
	 * This method obtains the Logger and corresponding
	 * log4jLogger instances.
	 * 
	 * @return Logger
	 * 
	 */
	public static Logger getLogger() {
		return getLogger(_defaultLoggerName);		
	}

	/**
	 * This method obtains the Logger and corresponding
	 * log4jLogger instances.
	 * 
	 * @param java.lang.String loggerName
	 * 
	 * @return Logger
	 * 
	 */
	public static Logger getLogger(String loggerName) {

	

		Logger logger = (Logger)loggers.get(loggerName);
		
		if (logger == null) {
			try {
				logger = new Logger();
				logger.log4jLogger = org.apache.log4j.Logger.getLogger(loggerName);
				loggers.put(loggerName,logger);
			} catch (Exception e) {
				System.err.println("Could not get logger: "+loggerName);
				e.printStackTrace();
			}

		}
		logger.setLoggerLevel(nLevel);
		return logger;		
	}

	/**
	 * This method obtains the Logger and corresponding
	 * log4jLogger instances.
	 * 
	 * @param java.lang.String loggerName
	 * 
	 * @return Logger
	 * 
	 */
	public static Logger getLogger(Class classObj) 
	{
		return getLogger(classObj.getName());
	}

	
	/**
	 * This method resets the logger list.  This will allow changes
	 * to log4j.xml to take effect without a server restart.
	 * 
	 * @param java.lang.String loggerName
	 * 
	 * @return Logger
	 * 
	 */

	/* 
	 * To programmatically reload the log4j.xml file, something similar to the following will be required
	 */
	public void reset() {
		try {
			loggers.clear();
			System.err.println("RESET LOGGER.1");
			System.err.println(this.getClass().getResource("log4j.xml"));
			System.err.println("RESET LOGGER.2");
			System.err.println(log4jLogger.getClass().getResource("log4j.xml"));
			System.err.println("RESET LOGGER.3");

			URL url = this.getClass().getResource("log4j.xml");
			//PropertyConfigurator.configureAndWatch(url.getPath(), 2000);
			System.err.println("RESET LOGGER.4");
			//PropertyConfigurator.configure( url );
			PropertyConfigurator.configure("log4j.xml");
			System.err.println("RESET LOGGER.5"); 
		} catch (Exception e) {
			System.err.println("Could not reset logger.");
			e.printStackTrace();
		}
		return;		
	}


	/**
	 * This method provides access to log4j's debug method,
	 * first checking to see if debug is enabled (performance improvement).
	 * 
	 * @param java.lang.Object message - The message to be printed via toString()
	 * 
	 * @return void
	 * 
	 */
	public void debug(Object message) {
		log4jLogger.debug(message);		
	}

	public void debug(Object message, Throwable t) {
		log4jLogger.debug(message,t);
	}

	/**
	 * This method provides access to log4j's info method,
	 * first checking to see if info is enabled (performance improvement).
	 * 
	 * @param java.lang.Object message - The message to be printed via toString()
	 * 
	 * @return void
	 * 
	 */
	public void info(Object message) {
		log4jLogger.info(message);	
	}


	public void info(Object message, Throwable t) {
		log4jLogger.info(message,t);
	}


	/**
	 * This method provides access to log4j's warn method,
	 * first checking to see if warn is enabled (performance improvement).
	 * 
	 * @param java.lang.Object message - The message to be printed via toString()
	 * 
	 * @return void
	 * 
	 */
	public void warn(Object message) {
		log4jLogger.warn(message);	
	}

	public void warn(Object message, Throwable t) {
		log4jLogger.warn(message,t);	
	}

	/**
	 * This method provides access to log4j's error method,
	 * 
	 * @param java.lang.Object message - The message to be printed via toString()
	 * 
	 * @return void
	 * 
	 */
	public void error(Object message) {
		log4jLogger.error(message);			
	}

	public void error(Object message, Throwable t) {
		log4jLogger.error(message,t);	
	}

	/**
	 * This method provides access to log4j's fatal method,
	 * 
	 * @param java.lang.Object message - The message to be printed via toString()
	 * 
	 * @return void
	 * 
	 */
	public void fatal(Object message) {
		log4jLogger.fatal(message);		
	}

	public void fatal(Object message, Throwable t) {
		log4jLogger.fatal(message,t);
	}	

	/**
	 * This method returns true if this logger is set to log DEBUG messages.
	 * 
	 * @return boolean
	 * 
	 */
	public boolean isDebugEnabled() {
		return log4jLogger.isDebugEnabled();
	}

	/**
	 * This method returns true if this logger is set to log INFO messages.
	 * 
	 * @return boolean
	 * 
	 */	
	public boolean isInfoEnabled() {
		return log4jLogger.isInfoEnabled();	
	}

	/**
	 * This method returns true if this logger is set to log WARN messages.
	 * 
	 * @return boolean
	 * 
	 */
	public boolean isWarnEnabled() {
		return log4jLogger.isEnabledFor(Level.WARN);
	}

	public void setLoggerLevel(int level) {
		log4jLogger.setLevel(Level.toLevel(level));	
	}

	public static Hashtable getLoggers() {
		return loggers;
	}

	public static void setLoggers(Hashtable loggers) {
		Logger.loggers = loggers;
	}	
}
