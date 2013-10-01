/**
 * 
 */
package com.ijunit.common.utility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.DataTypes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.data.KeyColumn;
import com.ijunit.common.data.TestData;
import com.ijunit.common.database.DBConnection;
import com.ijunit.common.database.TestDataDAO;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.parser.ObjectMappingParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.model.AttributeData;

/**
 * @author vcjain
 *
 */
public class CommonUtility {
	
	public static Logger logger = Logger.getLogger(CommonUtility.class);
	public static SimpleDateFormat hyphenDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
	public static int HYPHEN_DATEFORMAT = 1;
	public static int TIMESTAMP_DATEFORMAT = 2;
	public static String[] prefix = new String[]{"str","n","f","dt","l","b","d"};
	public static String[] packageToOmit = new String[]{"com.mbi"};
	
	public static Object[] filterMethods(Object[] methods){
		Method method = null;
		List list = new ArrayList();
		//Method[] methodsClone = methods.clone();
		for (int i = 0; i < methods.length; i++) {
			method = (Method)methods[i];
			if(method.getName().startsWith("set")){
				//logger.debug("Method Name to be shown "+method.getName());
				list.add(method);
			}
		}
		logger.debug("List of Methods to be shown "+list);
		return methods = list.toArray();
	}

	public static String removePrefix(String name){
		String temp = name;
		for (int i = 0; i < prefix.length; i++) {
			if(temp.startsWith(prefix[i])){
				temp = temp.substring(temp.indexOf(prefix[i])+prefix[i].length());
				break;
			}
		}
		/*
		 * this will check whether first character after prefix is UPPer or not. If First letter after prefix
		 * is not in upper case then we will consider it as prefix and will return the name as argument.
		 */ 
		if(String.valueOf(temp.charAt(0)).equals(String.valueOf(temp.charAt(0)).toUpperCase())){
			return temp;
		}else{
			return name;
		}
	}
	
	public static  String getInsertQuery(List attributeDataList, String tableName) throws IJunitException  {
    	logger.debug("Entered in CommonUtility.getInsertQuery()");
    	StringBuffer buffer = new StringBuffer();

    	try{
	        ObjectMappingParser.resetParser();
	        StringBuffer columnBuffer = new StringBuffer();
	        StringBuffer valueBuffer = new StringBuffer();
	   
	    	for (Iterator iter = attributeDataList.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				if(element.isToInsert()){
					columnBuffer.append(element.getColumn()).append(",");
					if(DataTypes.isNumberType(element.getType()) && element.getColumnType() == DataTypes.DB_NUMBER){
						valueBuffer.append(element.getValue()).append(",");
					}else if(DataTypes.isDateType(element.getType()) && ((element.getColumnType() == DataTypes.DB_TIMESTAMP)||
							element.getColumnType() == DataTypes.DB_DATE)){
						valueBuffer.append("to_date(")
						.append("'")
						.append(element.getValue())
						.append("'")
						.append(",'").append(Validation.DATE_FORMAT).append("')")
						.append(",");
					}else if(DataTypes.isTimestampType(element.getType()) && ((element.getColumnType() == DataTypes.DB_TIMESTAMP)||
							element.getColumnType() == DataTypes.DB_DATE)){
						valueBuffer.append("to_date(")
						.append("'")
						.append(element.getValue())
						.append("'")
						.append(",'").append("YYYY-MM-DD HH.MI.SS").append("')")
						.append(",");
					}else if(DataTypes.isDateType(element.getType()) && element.getColumnType() == DataTypes.DB_VARCHAR2){
						valueBuffer.append("'").append(element.getValue()).append("'").append(",");
					}else if((element.getType() == DataTypes.STRING || element.getType() == DataTypes.CHAR)&& element.getColumnType() == DataTypes.DB_VARCHAR2){
						valueBuffer.append("'").append(element.getValue()).append("'").append(",");
					}else if(DataTypes.isBooleanType(element.getType()) && element.getColumnType() == DataTypes.DB_VARCHAR2){
						valueBuffer.append("'").append(element.getValue()).append("'").append(",");
					}else if(DataTypes.isBooleanType(element.getType()) && element.getColumnType() == DataTypes.DB_NUMBER){
						valueBuffer.append(Validation.getIntFromBoolean(Boolean.parseBoolean((String)element.getValue()))).append(",");
					}
				}
			}
	    	columnBuffer.replace(columnBuffer.lastIndexOf(","), columnBuffer.lastIndexOf(",")+1,"");
	    	valueBuffer.replace(valueBuffer.lastIndexOf(","), valueBuffer.lastIndexOf(",")+1,"");
			logger
					.debug("In CommonUtility.getInsertQuery message= columns "+columnBuffer);
			logger
					.debug("In CommonUtility.getInsertQuery message= values "+valueBuffer);
	    	buffer.append("INSERT INTO ").append(tableName)
									.append(" (").append(columnBuffer.toString()).append(") ")
									.append(" VALUES (").append(valueBuffer.toString()).append(")");
    	} catch (Exception e) {
			logger.error("In CommonUtility.getInsertQuery "
					+ErrorMessageParser.getMessage(IExceptionCodes.PREPARING_INSEERTQUERY_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.PREPARING_INSEERTQUERY_EXP));
		}
    	logger.debug("Exited from CommonUtility.getInsertQuery()");
    	return buffer.toString();
   }

	
	public static  String getUpdateQuery(List attributeDataList, TestData testData) throws IJunitException  {
    	logger.debug("Entered in CommonUtility.getUpdateQuery()");
    	StringBuffer buffer = new StringBuffer();

    	try{
	        //ObjectMappingParser.resetParser();
	        StringBuffer valueBuffer = new StringBuffer();
	   
	    	for (Iterator iter = attributeDataList.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				if(element.isToInsert()){
					if(DataTypes.isNumberType(element.getType()) && element.getColumnType() == DataTypes.DB_NUMBER){
						valueBuffer.append(element.getColumn()).append("=").append(element.getValue()).append(",");
					}else if(DataTypes.isDateType(element.getType()) && ((element.getColumnType() == DataTypes.DB_TIMESTAMP)||
							element.getColumnType() == DataTypes.DB_DATE)){
						valueBuffer.append(element.getColumn()).append("=")
						.append("to_date(")
						.append("'")
						.append(element.getValue())
						.append("'")
						.append(",'").append(Validation.DATE_FORMAT).append("')")
						.append(",");
					}else if(DataTypes.isTimestampType(element.getType()) && ((element.getColumnType() == DataTypes.DB_TIMESTAMP)||
							element.getColumnType() == DataTypes.DB_DATE)){
						valueBuffer.append(element.getColumn()).append("=")
						.append("to_date(")
						.append("'")
						.append(element.getValue())
						.append("'")
						.append(",'").append("YYYY-MM-DD HH.MI.SS").append("')")
						.append(",");
					}else if(DataTypes.isDateType(element.getType()) && element.getColumnType() == DataTypes.DB_VARCHAR2){
						valueBuffer.append(element.getColumn()).append("=").append("'").append(element.getValue()).append("'").append(",");
					}else if((element.getType() == DataTypes.STRING || element.getType() == DataTypes.CHAR) && element.getColumnType() == DataTypes.DB_VARCHAR2){
						valueBuffer.append(element.getColumn()).append("=").append("'").append(element.getValue()).append("'").append(",");
					}else if(DataTypes.isBooleanType(element.getType()) && element.getColumnType() == DataTypes.DB_VARCHAR2){
						valueBuffer.append(element.getColumn()).append("=").append("'").append(element.getValue()).append("'").append(",");
					}else if(DataTypes.isBooleanType(element.getType()) && element.getColumnType() == DataTypes.DB_NUMBER){
						String value = "";
						if (element.getValue() instanceof Boolean) {
							valueBuffer.append(element.getColumn()).append("=").append(Validation.getIntFromBoolean((Boolean)element.getValue())).append(",");	
						}else if (element.getValue() instanceof String) {
							valueBuffer.append(element.getColumn()).append("=").append(Validation.getIntFromBoolean((String)element.getValue())).append(",");	
						} 
						
					}
				}
				//valueBuffer.append("\n");
			}
	    	valueBuffer.replace(valueBuffer.lastIndexOf(","), valueBuffer.lastIndexOf(",")+1,"");
	    	buffer.append("UPDATE ").append(testData.getDataTable()).append(" SET ")
									.append(valueBuffer.toString());
	    	Vector keyColumns = testData.getKeyColumns();
	    	int size = keyColumns.size();
	    	buffer.append(" WHERE ");
			for (Iterator iter = keyColumns.iterator() ; iter.hasNext();) {
				KeyColumn element = (KeyColumn) iter.next();
				buffer.append(element.getColumn()).append(" = ").append("'").append(element.getValue()).append("'");
				size--;
				if(size > 0){
					buffer.append(" AND ");
				}
			}
			
	    	
	    	logger.debug("Exited from CommonUtility.getUpdateQuery() "+buffer.toString());
    	} catch (Exception e) {
			logger.error("In CommonUtility.getUpdateQuery "
					+ErrorMessageParser.getMessage(IExceptionCodes.PREPARING_INSEERTQUERY_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.PREPARING_INSEERTQUERY_EXP));
		}
    	return buffer.toString();
   }

	
	public static void populateRuntimeValue(List selectedAttributeList, String testDataClass, Connection con) throws IJunitException {
    	logger.debug("Entered in CommonUtility.populateRuntimeValue()");
    	
    	try {
    		for (Iterator iter = selectedAttributeList.iterator(); iter.hasNext();) {
    			AttributeData element = (AttributeData) iter.next();
    			String value = element.getValue().toString();
    			if(value.indexOf("${") != -1){
    				logger.debug("In CommonUtility.populateRuntimeValue message= runtime value Method Name "
    																			+value.substring(value.indexOf("${")+2, value.indexOf("}")));
    				String methodName = value.substring(value.indexOf("${")+2, value.indexOf("}"));
    				if(value.indexOf(':') != -1){
	    				String argumentString = value.substring(value.indexOf(':')+1);
	    				logger
								.debug("In CommonUtility.populateRuntimeValue message=argument String "+argumentString);
	    				String[] arguments = argumentString.split(",");
	    				element.setValue(getValue(methodName,arguments,testDataClass,con));
    				}else{
    					element.setValue(getValue(methodName,null,testDataClass,con));
    				}
    				
    			}
    		}
		} catch (Exception e) {
			logger.error("In CommonUtility.populateRuntimeValue "
					+ErrorMessageParser.getMessage(IExceptionCodes.POPULATE_RUNTIME_VALUE_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.POPULATE_RUNTIME_VALUE_EXP),e);
		}
    	
    	
    	logger.debug("Exited from CommonUtility.populateRuntimeValue()");
    }
    
    public static Object getValue(String methodName, String dataClass, Connection con) throws IJunitException, SQLException, 
    			ClassNotFoundException, SecurityException, NoSuchMethodException, 
    			IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException  {
    	logger.debug("Entered in CommonUtility.getValue()");
    	
		String helpClassName = TestDataDAO.getDataHelperClass(dataClass,con);
		Class helperClass = GTestClassLoader.getInstance().loadClass(helpClassName);
		Method method = helperClass.getMethod(methodName,new Class[]{});
        Object value = method.invoke(helperClass.newInstance(),null);
        logger.debug("In CommonUtility.getValue message= Value after executing helper method "+value);
        
        logger.debug("Exited from CommonUtility.getValue()");
        return value;
    }
    
    public static Object getValue(String methodName, Object arguments[], String dataClass, Connection con) throws IJunitException, SQLException, 
			ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException  {
		logger.debug("Entered in CommonUtility.getValue()");
		Class[] argumentClassArr = null;
		String helpClassName = TestDataDAO.getDataHelperClass(dataClass,con);
		Class helperClass = GTestClassLoader.getInstance().loadClass(helpClassName);
		
		if(arguments != null){
			argumentClassArr = new Class[arguments.length];
			for (int i = 0; i<arguments.length;i++){
				argumentClassArr[i] = String.class;
				logger.debug("In CommonUtility.getValue message= argument Value "+i+" "+arguments[i]);
			}
		}
		Method method = helperClass.getMethod(methodName,argumentClassArr);
		Object value = method.invoke(helperClass.newInstance(),arguments);
		logger.debug("In CommonUtility.getValue message= Value after executing helper method "+value);
		
		logger.debug("Exited from CommonUtility.getValue()");
		return value;
	}
    
	public static boolean isObjectLoaded(String dataClass) throws IJunitException{
		Connection con = null;
		boolean isLoaded = false;
		 try{
			con = DBConnection.getConnection(); 
			String dataFile = TestDataDAO.getDataFile(dataClass, con);
			isLoaded = !Validation.isEmpty(dataFile);
		 }catch (Exception e) {
			 logger.error("In AddObjectAction.browseBtnActionPerformed "+ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP),e);
		}finally{
			DBConnection.releaseConnection(con);
		}
		return isLoaded;
	}
	
	/**
	 * This method will fetch the value from ResultSet passed as argument based on the type and Database type 
	 * of the field. 
	 * @param columnLabel
	 * @param type
	 * @param dbType
	 * @param rs
	 * @return
	 * @throws IJunitException
	 */
	public static Object fetchAppDataValue(String columnLabel, int type, int dbType, ResultSet rs) throws IJunitException{
		try{
			switch(type)
			{
				case 1:
				case 6:	
					return rs.getShort(columnLabel);
				case 2:
				case 7:
				case 12:	
					return rs.getInt(columnLabel);
				case 3:
				case 8:	
					return rs.getLong(columnLabel);
				case 4:
				case 9:	
					return rs.getFloat(columnLabel);
				case 5:
				case 10:	
					return rs.getDouble(columnLabel);
				case 11: 
					return rs.getBigDecimal(columnLabel);
				case 13: 
				case 14: 
					return rs.getDate(columnLabel);
				case 15: 
					return rs.getString(columnLabel);
				case 16: 
				case 17: 
					if(dbType == DataTypes.DB_NUMBER){
						return Validation.getBooleanFromInt(rs.getInt(columnLabel)+"");
					}else{
						return Validation.getBooleanFromString(rs.getString(columnLabel));
					}
				case 18: 
					return rs.getTimestamp(columnLabel);
				case 19: 
					return rs.getObject(columnLabel);
				case 21:
					return rs.getString(columnLabel).charAt(0);	
				default:
					return null;
			}
		}catch(SQLException e){
			logger.error("In CommonUtility.fetchAppDataValue "+ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA),e);
		}
	}
	
	/**
	 * This method will return the actual type of Value string passed as argument. When a Object Xml data is read
	 * then all the value are in String form. This method will convert a string to its actual type 
	 * using the Type passed as argument.
	 * @param value
	 * @param type
	 * @return
	 * @throws IJunitException
	 */
	public static Object getActualTypeValue(String value, int type) throws IJunitException{
		logger.debug("Entered in CommonUtility.getActualTypeValue() Value - Type "+value+"-"+type);
		switch(type)
		{
			case 1:
			case 6:	
				value = Validation.isEmpty(value)?"0":value;
				return Short.valueOf(value);
			case 2:
			case 7:
			case 12:	
				value = Validation.isEmpty(value)?"0":value;
				return Integer.valueOf(value);
			case 3:
			case 8:	
				value = Validation.isEmpty(value)?"0":value;
				return Long.valueOf(value);
			case 4:
			case 9:	
				value = Validation.isEmpty(value)?"0.0":value;
				return Float.valueOf(value);
			case 5:
			case 10:	
				value = Validation.isEmpty(value)?"0.0":value;
				return Double.valueOf(value);
			case 11: 
				value = Validation.isEmpty(value)?"0.0":value;
				return new BigDecimal(value);
			case 13: 
				return getDateFromString(value,1);
			case 14:  
				return new java.sql.Date(getDateFromString(value,1).getTime());
			case 15:
				return value;
			case 16: 
			case 17: 
				value = Validation.isEmpty(value)?"false":value;
				return Validation.getBooleanFromString(value);
			case 18: 
				return new Timestamp(getDateFromString(value,2).getTime());
			case 19: 
				return value;
			case 21:
				return value.charAt(0);
			default:
				return null;
		}
	}
	
	/**
	 * This method will return all condition corressponding to a TestClass.
	 * @param testClass
	 * @return
	 * @throws IJunitException
	 */
	public static Map populateConditions(String testClass) throws IJunitException {
    	logger.debug("Entered in AddTestDataAction.populateConditions()");
    	Connection con =null;
    	Map returnMap = null;
		try {
			con = DBConnection.getConnection();
	    	returnMap = TestDataDAO.getTestConditions(testClass,con);
			logger.debug("Exited from CommonUtility.populateConditions()");
		} catch (IJunitException e) {
			logger.error("In GetValueDialogAction.populateConditions "
					+ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), e);
			throw e;
		} catch (SQLException e) {
			logger.error("In GetValueDialogAction.populateConditions "
					+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
		}finally {
				DBConnection.releaseConnection(con);
		}	
		return returnMap;
		
    }
//	public static String replaceCharacter(String value, String strReplace, String strReplaceWith){
//		value.replace('\\','/');
//	}
	
	/**
	 * This method will remove the TYpe of Parameter. For ex. argument will as "class/interface java.lang.String"
	 * This method will return "java.lang.String"
	 */
	public static String removeType(String dataType){
		String returnValue = "";
		int index = -1;
		index = dataType.indexOf("class") != -1 ? dataType.indexOf("class") +"class".length(): 
					dataType.indexOf("interface") != -1? dataType.indexOf("interface")+"interface".length() : -1;
		returnValue = dataType.substring(index+1);
		return returnValue;
	}
	
	/**
	 * This method will return the most preffered type for Attribute data. If both field TYpe and 
	 * method type are same then fieldTYpe will return otherwise method type will return.
	 * @param field
	 * @param method
	 * @return
	 */
	public static String getPrefferedType(Field field, Method method){
		logger.debug("Entered in CommonUtility.getPrefferedType() Field Name "+field.getName());
		String fieldType = field.getType().getCanonicalName();
		String methodType = removeType(method.getGenericParameterTypes()[0].toString());
		logger.debug("In CommonUtility.getPrefferedType message= filed TYpe - Method Type "+fieldType+"-"+methodType);
		if(fieldType.equalsIgnoreCase(methodType)){
			return fieldType;
		}else{
			return methodType;
		}
		
	}
	/**
	 * This method will return name of method without return type.
	 * 
	 * @param methodName
	 * @return
	 */
	public static String getMethodNameWithoutRType(String methodName) {
		int index = methodName.trim().indexOf("set");
		if (index != -1) {
			methodName = methodName.substring(index);
		}
		return methodName;
	}	
	
	/**
	 * Returns a String with comma seprarted prefix.
	 * @return
	 */
	public static String getPrefixString(){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<prefix.length;i++){
			buffer.append(prefix[i]);
			if(i < prefix.length-1){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
	/**
	 * Set the array of prefixes using the comma separated prefix String. 
	 * @param prefixString
	 */
	public static void setPrefixes(String prefixString){
		prefix = prefixString.split(",");
	}
	
	/**
	 * Returns a String value containning comma seprarated value of omitting packages. 
	 * @return
	 */
	public static String getPackagesToOmitString(){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<packageToOmit.length;i++){
			buffer.append(packageToOmit[i]);
			if(i < packageToOmit.length-1){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
	/**
	 * Set the array of packages to omit.
	 * @param strPackageToOmit
	 */
	public static void setPackagesToOmit(String strPackageToOmit){
		packageToOmit = strPackageToOmit.split(",");
	}
	
	public static boolean isPackagetoOmit(String name){
		boolean isValid = true;
		for(int i=0;i<packageToOmit.length;i++){
			if(name.indexOf(packageToOmit[i]) != -1){
				isValid = false;
			}
		}
		return isValid;
	}
	
	public static String removeArgument(String methodName){
		return (methodName.indexOf("(") != -1) ?	 methodName.substring(0,methodName.indexOf("(")) : methodName;
	}
	
	public static Date getDateFromString(String strDate,int dateFormat){
		java.util.Date date = null;
		if(strDate == null || strDate.equalsIgnoreCase("")){
			return new Date();
		}
		try{
			if(dateFormat == 1){
				date = hyphenDateFormat.parse(strDate);
			}else{
				date = timestampFormat.parse(strDate);
			}
			
		}catch (ParseException e) {
			date = new Date();
		} 
		return date;
	}
	
	public static void main(String[] args) {
		System.out.println(getDateFromString("2010-06-12 12.45.45",2));
	}
}
