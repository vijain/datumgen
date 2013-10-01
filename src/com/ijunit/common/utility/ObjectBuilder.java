/**
 * 
 */
package com.ijunit.common.utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.DataTypes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.data.TestData;
import com.ijunit.common.database.ConnectionFactory;
import com.ijunit.common.database.DBConnection;
import com.ijunit.common.database.TestDataDAO;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.parser.ObjectDataParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.model.AttributeData;

/**
 * @author vcjain
 *
 */
public class ObjectBuilder 
{
	static Logger logger = Logger.getLogger(ObjectBuilder.class);
	
	/**
	 * This method will build Object and populate data from database based on the arguments provided. 
	 * @param testClassName - Full package className for class for whih testcase is written.
	 * @param dataClassName - full package classname for class whose Object we want
	 * @param testConditionName - name of test condition
	 * @return - an instance of dataClassName class
	 * @throws IJunitException
	 */
	public static Object getObject(String testClassName, String dataClassName, String testConditionName
								,Connection appCon) throws IJunitException
	{
		logger.debug("Entered in ObjectBuilder.getObject()");
		
		Object obj = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			
			TestData testData = TestDataDAO.getKeyValue(testClassName, dataClassName, testConditionName, con);
			obj = TestDataDAO.getAppTestData(testData, appCon);
			logger.debug("Exited from ObjectBuilder.getObject()");
		} catch (SQLException e) {
			logger.error("In ObjectBuilder.getObject "+ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA),e);
		}finally{
			DBConnection.releaseConnection(con);
		}
		return obj;
	}
	
	
	/**
	 * This method will build Object and populate data from database based on the arguments provided. 
	 * @param testClassName - Full package className for class for whih testcase is written.
	 * @param dataClassName - full package classname for class whose Object we want
	 * @param testConditionName - name of test condition
	 * @return - an instance of dataClassName class
	 * @throws IJunitException
	 */
	public static Object getObject(String testClassName, String dataClassName, String testConditionName) throws IJunitException
	{
		logger.debug("Entered in ObjectBuilder.getObject(String, String, String)");
		
		Object obj = null;
		Connection appCon = null;
		try {
			appCon = DBConnection.getAppConnection();
			obj = getObject(testClassName,dataClassName,testConditionName,null,null);
		}finally{
			DBConnection.releaseConnection(appCon);
			logger.debug("Exited from ObjectBuilder.getObject(String, String, String)");
		}
		return obj;
	}
	
	/**
	 * This method will build Object and populate data from database based on the arguments provided. 
	 * @param testClassName - Full package className for class for whih testcase is written.
	 * @param dataClassName - full package classname for class whose Object we want
	 * @param testConditionName - name of test condition
	 * @param argumentType - argument type for constructor
	 * @param argumentValues - argument value for constructor 
	 * @return - an instance of dataClassName class
	 * @throws IJunitException
	 */
	public static Object getObject(String testClassName, String dataClassName, String testConditionName, Class[] argumentType, Object[] argumentValues) throws IJunitException{
		logger.debug("Entered in ObjectBuilder.getObject(String,String,String,Class[],Object[])");
		
		Connection appCon = null;
		try {
			appCon = DBConnection.getAppConnection(); 
			return getObject(testClassName, dataClassName, testConditionName, argumentType, argumentValues, appCon);
		}finally{
			DBConnection.releaseConnection(appCon);
			logger.debug("Exited from ObjectBuilder.getObject(String,String,String,Class[],Object[])");
		}
	}
	
	/**
	 * This method will build Object and populate data from database based on the arguments provided. 
	 * @param testClassName - Full package className for class for whih testcase is written.
	 * @param dataClassName - full package classname for class whose Object we want
	 * @param testConditionName - name of test condition
	 * @param argumentType - argument type for constructor
	 * @param argumentValues - argument value for constructor
	 * @param Connection con - Application connection object. 
	 * @return - an instance of dataClassName class
	 * @throws IJunitException
	 */
	private static Object getObject(String testClassName, String dataClassName, String testConditionName, Class[] argumentType, Object[] argumentValues, Connection appCon) throws IJunitException{
		logger.debug("Entered in ObjectBuilder.getObject(String,String,String,Class[],Object[],Connection)");
		
		Object obj = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			TestData testData = TestDataDAO.getKeyValue(testClassName, dataClassName, testConditionName, con);
			obj = TestDataDAO.getAppTestData(testData, appCon,argumentType,argumentValues);
			logger.debug("Exited from ObjectBuilder.getObject(String,String,String,Class[],Object[],Connection)");
		} catch (SQLException e) {
			logger.error("In ObjectBuilder.getObject "+ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA),e);
		}finally{
			DBConnection.releaseConnection(con);
		}
		return obj;
	}


	/**
	 * This method will return the modified Object corressponding to argument dataClassName after executing the
	 * test method. 
	 * @param testClassName - package classname for Test class
	 * @param dataClassName - package class name of Object that you want as return type
	 * @param testConditionName - condition name usually a method name. 
	 * @return
	 * @throws IJunitException
	 */
	public static Object getModifiedObject(String testClassName, String dataClassName, 
			String testConditionName) throws IJunitException 
	{
		return getModifiedObject(testClassName, dataClassName, testConditionName, 0);
	}
	
	/**
	 * This method will return the modified Object corressponding to argument dataClassName after executing the
	 * test method. 
	 * @param testClassName - package classname for Test class
	 * @param dataClassName - package class name of Object that you want as return type
	 * @param testConditionName - condition name usually a method name. 
	 * @param connectionIndex - index of connection object. Index here denotes the sequence in which 
	 * the connection object created in your method call. 
	 * @return
	 * @throws IJunitException
	 */
	public static Object getModifiedObject(String testClassName, String dataClassName, 
			String testConditionName, int connectionIndex) throws IJunitException 
	{
		logger.debug("Entered in ObjectBuilder.getModifiedObject()");
		Object obj = getModifiedObject(testClassName, dataClassName, testConditionName, connectionIndex, null, null);
		logger.debug("Exited from ObjectBuilder.getModifiedObject()");
		return obj;
	}
	
	/**
	 * This method will return the modified Object corressponding to argument dataClassName after executing the
	 * test method. 
	 * @param testClassName - package classname for Test class
	 * @param dataClassName - package class name of Object that you want as return type
	 * @param testConditionName - condition name usually a method name.
	 * @param argumentType - argument type for constructor
	 * @param argumentValues - argument value for constructor 
	 * @return
	 * @throws IJunitException
	 */
	public static Object getModifiedObject(String testClassName, String dataClassName, 
			String testConditionName, Class[] argumentType, Object[] argumentValues) throws IJunitException 
	{
		return getModifiedObject(testClassName, dataClassName, testConditionName, 0, argumentType, argumentValues);
	}
	
	/**
	 * This method will return the modified Object corressponding to argument dataClassName after executing the
	 * test method. 
	 * @param testClassName - package classname for Test class
	 * @param dataClassName - package class name of Object that you want as return type
	 * @param testConditionName - condition name usually a method name. 
	 * @param connectionIndex - index of connection object. Index here denotes the sequence in which 
	 * the connection object created in your method call. 
	 * @return
	 * @throws IJunitException
	 */
	public static Object getModifiedObject(String testClassName, String dataClassName, 
			String testConditionName, int connectionIndex, Class[] argumentType, Object[] argumentValues) 
	throws IJunitException {
		logger.debug("Entered in ObjectBuilder.getModifiedObject(argumentType, argumentValues)");
		Object obj = null;
		Connection appCon = null;
		
		appCon =  ConnectionFactory.getConnection(Thread.currentThread().getName()+connectionIndex);
		logger.debug("In ObjectBuilder.getModifiedObject message= Connection used to fetch Modified Object "+appCon);
		obj = ObjectBuilder.getObject(testClassName, dataClassName, testConditionName, argumentType,argumentValues,appCon);
		
		logger.debug("Exited from ObjectBuilder.getModifiedObject()");
		return obj;
	}

	/**
	 * This method will return the modified Object corressponding to argument dataClassName after executing the
	 * test method. 
	 * @param testClassName - package classname for Test class
	 * @param dataClassName - package class name of Object that you want as return type
	 * @param testConditionName - condition name usually a method name. 
	 * @param connectionIndex - index of connection object. Index here denotes the sequence in which 
	 * the connection object created in your method call. 
	 * @param appCon- Connection on which you want to retrive object.  
	 * @return
	 * @throws IJunitException
	 */
	private static Object getModifiedObject(String testClassName, String dataClassName, 
			String testConditionName, int connectionIndex, Connection appCon) throws IJunitException 
	{
		logger.debug("Entered in ObjectBuilder.getModifiedObject()");
		Object obj = null;
		if(appCon == null){
			appCon =  ConnectionFactory.getConnection(Thread.currentThread().getName()+connectionIndex);
			logger.debug("In ObjectBuilder.getModifiedObject message= Connection used to fetch Modified Object "+appCon);
		}
		obj = ObjectBuilder.getObject(testClassName, dataClassName, testConditionName, null,null,appCon);
		
		logger.debug("Exited from ObjectBuilder.getModifiedObject()");
		return obj;
	}
	
	/**
	 * This method is used for getting dummy data object. Object will be created using
	 * the DataObject xml file and its attribute will have default values.
	 * @param dataClassName - Class name for Object to be return.
	 * @return- an Object of type dataClassName.
	 * @throws IJunitException - Exception thrown while the creating Object.
	 * It may throw ClassNotFoundExectpion and some of Reflection exception.
	 */
	public static Object getTemplateObject(String dataClassName,String fileName) throws IJunitException{
		logger.debug("Entered in ObjectBuilder.getObject(String,String)");
		Object obj = getTemplateObject(dataClassName, fileName, null,null);
		logger.debug("Exited from ObjectBuilder.getObject(String,String)");
		return obj;
	} 

	/**
	 * This method is used for getting dummy data object. Object will be created using
	 * the DataObject xml file and its attribute will have default values.
	 * @param dataClassName - Class name for Object to be return.
	 * @param argumentType - argument type for constructor
	 * @param argumentValues - argument value for constructor 
	 * @return- an Object of type dataClassName.
	 * @throws IJunitException - Exception thrown while the creating Object.
	 * It may throw ClassNotFoundExectpion and some of Reflection exception.
	 */
	public static Object getTemplateObject(String dataClassName,String fileName,Class[] argumentType,Object[] argumentValues) throws IJunitException{
		logger.debug("Entered in ObjectBuilder.getObject(String,String,Class[],Object[])");
		Object obj = null;
		Connection con = null;
		Method method = null;
		Class dataClass = null;
		try{
			dataClass = GTestClassLoader.getInstance(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)).loadClass(dataClassName);
			//dataClass = Class.forName(dataClassName);
			
			Constructor constructor = dataClass.getConstructor(argumentType);
			obj = constructor.newInstance(argumentValues);
			
			ObjectDataParser parser  = new ObjectDataParser();
			parser.parse(ResourceManager.getFile(fileName).toString());
			Vector attributeDataList = parser.getAttributeDataList();
			for (Iterator iter = attributeDataList.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				logger.debug("In ObjectBuilder.getObject: AttributeData "+element);
				String value = element.getValue().toString();
    			if(!Validation.isEmpty(element.getSetMethod())){
					logger.debug("In ObjectBuilder.getObject: DataType "+DataTypes.getDataType(element.getType()));
					if( (value.indexOf("${") != -1) && (DataTypes.isNumberType(element.getType())) ){
						value = "0";
					}
					method = dataClass.getMethod(CommonUtility.removeArgument(element.getSetMethod())
							, new Class[]{DataTypes.getDataType(element.getType())});
					method.invoke(obj, new Object[]{
							CommonUtility.getActualTypeValue(value,element.getType())});
				}
			}
			logger.debug("Exited from ObjectBuilder.getObject(String,String,Class[],Object[])");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("In ObjectBuilder.getObject "+e.getMessage(), e);
			throw new IJunitException(e.getMessage(),e);
		}finally{
			DBConnection.releaseConnection(con);
		}
		return obj;
	}
	
	
	public static void main(String[] args) throws Exception {
		String dataClassName = "com.mbi.common.data.patient.PatientData";
		String dataClassName1 = "com.mbi.common.data.patient.PhoneData";
		//Class dataClass = Class.forName(dataClassName);1
		Class dataClass = GTestClassLoader.getInstance(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)).loadClass(dataClassName);
		Class dataClass1 = GTestClassLoader.getInstance(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)).loadClass(dataClassName1);
		Constructor c = dataClass.getConstructor(null);
		Object obj = c.newInstance(new Object[]{null});
		System.out.println("obj "+obj);
		Constructor c1 = dataClass1.getConstructor(new Class[]{String.class});
		Object obj1 = c1.newInstance("H");
		System.out.println("obj "+obj1);

	}
}