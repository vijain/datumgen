/**
 * 
 */
package com.ijunit.common.database;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.DataTypes;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.data.KeyColumn;
import com.ijunit.common.data.TestCondition;
import com.ijunit.common.data.TestData;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.CommonUtility;
import com.ijunit.common.utility.GTestClassLoader;
import com.ijunit.common.utility.Validation;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.parser.ObjectDataParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.model.AttributeData;

/**
 * @author vcjain
 *
 */
public class TestDataDAO 
{
	static Logger logger = Logger.getLogger(TestDataDAO.class);
	
	public static Map getTestConditions(String testClassName, Connection con) throws SQLException, IJunitException{
		logger.debug("Entered in TestDataDAO.getTestConditions()");
		PreparedStatement pStmt = null;
		Map map = new TreeMap();
		try{
			String query = "SELECT condition,conditiondesc FROM TESTCONDITION WHERE CLASS LIKE ?";
			
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, testClassName);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				map.put(rs.getString("condition"),rs.getString("conditiondesc"));
			}
			logger.debug("In TestDataDAO.getTestConditions message= condition Vector "+map);
			
			logger.debug("Exited from TestDataDAO.getTestConditions()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return map;
	}
	
	public static TestCondition getTestCondition(String testClassName, String condition, Connection con) 
				throws SQLException, IJunitException
	{
		logger.debug("Entered in TestDataDAO.getTestConditions(String, String)");
		PreparedStatement pStmt = null;
		TestCondition testCondition = null; 
		try{
			String query = "SELECT * FROM TestCondition WHERE class LIKE ? AND condition LIKE ?";
			
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, testClassName);
			pStmt.setString(2, condition);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()){
				testCondition = new TestCondition(rs.getInt("ID"), rs.getString("CLASS"),
										rs.getString("CONDITION"), rs.getString("CONDITIONDESC"));
			}
			logger.debug("In TestDataDAO.getTestConditions message= Test Condition  "+testCondition);
			
			logger.debug("Exited from TestDataDAO.getTestConditions()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return testCondition;
	}
	
	public static String getSQLFile(String testClassName, Connection con) throws Exception{
		logger.debug("Entered in TestDataDAO.getSQLFile()");
		PreparedStatement pStmt = null;
		String sqlFileName = "";
		try{
			String query = "SELECT FILENAME FROM SQLFILES WHERE TESTCLASS LIKE ?";
			
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, testClassName);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				sqlFileName = rs.getString(1);
			}
			
			logger.debug("Exited from TestDataDAO.getSQLFile()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.getSQLFile "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return sqlFileName;
	}
	
	public static String getDataFile(String dataClassName, Connection con) throws Exception{
		logger.debug("Entered in TestDataDAO.getDataFile()");
		PreparedStatement pStmt = null;
		String dataFileName = "";
		try{
			String query = "SELECT DATAFILE FROM DATACOLUMN WHERE CLASS LIKE ?";
			
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, dataClassName);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				dataFileName = rs.getString(1);
			}
			
			logger.debug("Exited from TestDataDAO.getDataFile()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.getDataFile "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return dataFileName;
	}
	public static int[] addDataColumnRec(List list, String className, String tableName, 
						List keyColumn, String dataFile, String dataHelperClass, Connection con) throws SQLException, IJunitException {
		logger.debug("Entered in TestDataDAO.addDataColumnRec()");
		PreparedStatement pStmt = null;
		int [] successCount;
		try{
	    	String query = "INSERT INTO DATACOLUMN VALUES (?,?,?,?,?,?)";
	    	pStmt = con.prepareStatement(query);
	    	
	    	pStmt.setString(2,className);
	    	pStmt.setString(3, tableName.toUpperCase());
	    	pStmt.setString(5, dataFile);
	    	pStmt.setString(6, dataHelperClass);
	    	for (Iterator iter = keyColumn.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				logger.debug("In AddObjectAction.saveBtnActionPerformed message= Primary key "+element); 
				pStmt.setInt(1,DBConnection.getDCSequenceNo());
				pStmt.setString(4, element);
		    	pStmt.addBatch();
			}
	    	successCount =  pStmt.executeBatch();
	    	logger.debug("Exited from TestDataDAO.addDataColumnRec()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
    	return successCount;
	}
	
	public static void modifyDataColumnRec(List list, String className,
			String tableName, List keyColumn, String dataFile,
			String dataHelperClass, Connection con) throws SQLException,
			IJunitException {
		logger.debug("Entered in TestDataDAO.modifyDataColumnRec()");
		deleteDataColumn(className, con);
		addDataColumnRec(list, className, tableName, keyColumn,  dataFile, dataHelperClass, con);
		
		logger.debug("Exited from TestDataDAO.modifyDataColumnRec()");
	}
	
	public static int deleteDataColumn(String className, Connection con) throws SQLException{
		logger.debug("Entered in TestDataDAO.deleteDataColumn()");
		int count = 0;
		PreparedStatement pStmt = null;
		try {
			String query = "DELETE FROM DataColumn WHERE class LIKE ? ";
			pStmt = con.prepareStatement(query);

			pStmt.setString(1, className);
			count = pStmt.executeUpdate();
			logger.debug("Exited from TestDataDAO.deleteDataColumn()");
		} finally {
			try {
				if (pStmt != null)
					pStmt.close();
			} catch (SQLException e) {
				logger.error("In TestDataDAO.addTestData "
						+ ErrorMessageParser.getMessage(
								IExceptionCodes.GENERIC_EXP, new String[] { e
										.getMessage() }), e);
			}
		}

		logger.debug("Exited from TestDataDAO.deleteDataColumn()");
		return count;
	}
	
	public static String getDataHelperClass(String dataClassName, Connection con) throws IJunitException, SQLException  {
		logger.debug("Entered in TestDataDAO.getDataHelperClass()");
		PreparedStatement pStmt = null;
		String helperClassName = "";
		try{
			String query = "SELECT datahelperclass FROM DATACOLUMN WHERE CLASS LIKE ?";
			
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, dataClassName);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				helperClassName = rs.getString("datahelperclass");
			}
			
			logger.debug("Exited from TestDataDAO.getDataHelperClass()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return helperClassName;
	}
	
	public static boolean execute(String query, Connection con) throws SQLException{
		logger.debug("Entered in TestDataDAO.executeQuery()");
		Statement stmt = null;
		boolean result = false;
		try{
		stmt = con.createStatement();
		
		logger.debug("In TestDataDAO.executeQuery message= Insert Query "+query);
		result = stmt.execute(query);
		logger.debug("In TestDataDAO.executeQuery message= Insert Query executed successfully "+result);
		
		logger.debug("Exited from TestDataDAO.executeQuery()");
		}finally {
			try{
				if(stmt != null) stmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return result;
	}
	
	public static ResultSet executeSelectQuery(String tableName, String oid, Connection con) throws SQLException{
		logger.debug("Entered in TestDataDAO.executeQuery()");
		Statement stmt = null;
		ResultSet result = null;
		String query = "Select * from "+tableName+" where OID = '"+oid+"'";
		stmt = con.createStatement();
		
		logger.debug("In TestDataDAO.executeQuery message= Query to execute"+query);
		result = stmt.executeQuery(query);
		
		logger.debug("Exited from TestDataDAO.executeQuery()");
		return result;
	}
	
	public static ResultSet executeQuery(String query, Connection con) throws SQLException{
		logger.debug("Entered in TestDataDAO.executeQuery()");
		Statement stmt = null;
		ResultSet result = null;
		stmt = con.createStatement();
		
		logger.debug("In TestDataDAO.executeQuery message= Query to execute"+query);
		result = stmt.executeQuery(query);
		
		logger.debug("Exited from TestDataDAO.executeQuery()");
		return result;
	}
	
	public static int addTestCondition(String testClass, String condition, String conditionDesc, Connection con) throws  IJunitException{
		logger.debug("Entered in TestDataDAO.addTestCondition()");
		
		String query = "INSERT INTO TESTCONDITION (ID, CLASS, CONDITION, CONDITIONDESC, CREATEDON) VALUES(?,?,?,?,?)";
		PreparedStatement pStmt = null;
		int testConditionID = -1;
		try {
			testConditionID = DBConnection.getTCSequenceNo();
			pStmt = con.prepareStatement(query);
			pStmt.setInt(1, testConditionID);
			pStmt.setString(2,testClass);
			pStmt.setString(3,condition);
			pStmt.setString(4,conditionDesc);
			pStmt.setDate(5,new Date(new java.util.Date().getTime()));
			pStmt.execute();
			
			logger.debug("Exited from enclosing_type.enclosing_method()");
		} catch (SQLException e) {
			logger.error("In TestDataDAO.addTestCondition "+ErrorMessageParser.getMessage(IExceptionCodes.ADD_TESTCONDITION_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.ADD_TESTCONDITION_EXP),e);
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return testConditionID;
	}
 
	public static void addSQLQuery(int testConditionID, String dataClass, String sql, Connection con) throws  IJunitException{
		logger.debug("Entered in TestDataDAO.addSQLQuery()");
		
		String query = "INSERT INTO SQLQUERY (TESTCONDITIONID, DATACLASS, SQLQUERY, VERSION) VALUES(?,?,?,?)";
		PreparedStatement pStmt = null;
		try {
			pStmt = con.prepareStatement(query);
			pStmt.setInt(1, testConditionID);
			pStmt.setString(2,dataClass);
			pStmt.setString(3,sql);
			pStmt.setString(4,ResourceManager.getProperty("version"));
			pStmt.execute();
			
			logger.debug("Exited from TestDataDAO.addSQLQuery()");
		} catch (SQLException e) {
			logger.error("In TestDataDAO.addSQLQuery "+ErrorMessageParser.getMessage(IExceptionCodes.ADD_SQLQUERY_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.ADD_SQLQUERY_EXP),e);
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addSQLQuery "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
	}
		
	public static void addSQLFILE(String testClass, String fileName, Connection con) throws IJunitException{
		logger.debug("Entered in TestDataDAO.addSQLFILE()");
		String query = "INSERT INTO SQLFILES (TESTCLASS, FILENAME) VALUES(?,?)";
		PreparedStatement pStmt = null;
		try {
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, testClass);
			pStmt.setString(2,fileName);
			pStmt.execute();
			logger.debug("Exited from enclosing_type.enclosing_method()");
		} catch (SQLException e) {
			logger.error("In TestDataDAO.addSQLFILE "+ErrorMessageParser.getMessage(IExceptionCodes.ADD_SQLFILE_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.ADD_SQLFILE_EXP),e);
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addSQLFILE "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
	}
	
	public static Map getPrimaryColumn(String dataClass, Connection con) throws SQLException, IJunitException{
		logger.debug("Entered in TestDataDAO.getPrimaryColumn()");
		PreparedStatement pStmt = null;
		try{
			Map map = new HashMap();
			String sql = "SELECT keycolumn, Id FROM datacolumn WHERE class LIKE ?";
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, dataClass);
			
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				map.put(rs.getString("keycolumn"),rs.getInt("ID"));
			}
			
			logger.debug("Exited from TestDataDAO.getPrimaryColumn()");
			return map;
		}finally
        {
            try
            {
                if(pStmt != null) pStmt.close();
            }
            catch (SQLException e)
            {
            	logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
            			IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
            }
        }
	}
	
	public static void addTestData(int testConditonID, Map map, Connection con) throws IJunitException{
		logger.debug("Entered in TestDataDAO.addTestData()");
		
		String query = "INSERT INTO TestData (TestConditionID, DataColumnID, value) VALUES(?,?,?)";
		PreparedStatement pStmt = null;
		try {
			pStmt = con.prepareStatement(query);
			Set keySet = map.keySet();
			for (Iterator iter = keySet.iterator(); iter.hasNext();) {
				int element = ((Integer) iter.next()).intValue();
				pStmt.setInt(1, testConditonID);
				pStmt.setInt(2,element);
				pStmt.setString(3,(String)map.get(element));
				pStmt.addBatch();
			}
			pStmt.executeBatch();
		} catch (SQLException e) {
			logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(IExceptionCodes.ADD_TESTDATA_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.ADD_TESTDATA_EXP),e);
		}finally
        {
            try
            {
                if(pStmt != null) pStmt.close();
            }
            catch (SQLException e)
            {
            	logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
            			IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
            }
        }
		
		logger.debug("Exited from TestDataDAO.addTestData()");
	}
	
	public static TestData getKeyValue(String testClass, String dataClass, String condition, Connection con) throws SQLException{
		TestData testData = new TestData(dataClass);
		PreparedStatement pStmt = null;
		try{
			KeyColumn column = null;
			Vector keyColumns = new Vector();
			String sql = "SELECT TestCondition.CLASS, DataColumn.CLASS DataClass, DataColumn.KEYCOLUMN, TestData.VALUE, " +
									"DataColumn.DATATABLE, DataColumn.DATAFILE, DataColumn.DATAHELPERCLASS "+ 
								  "FROM TestCondition, DataColumn, TestData "+ 
								 "WHERE TestCondition.ID = TestData.TESTCONDITIONID "+
								   "AND DataColumn.ID = TestData.DATACOLUMNID AND "+
								   "UPPER(TestCondition.CLASS) = ? AND UPPER(TestCondition.CONDITION) = ? AND UPPER(DataColumn.CLASS) = ?";
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1,testClass.toUpperCase());
			pStmt.setString(2,condition.toUpperCase());
			pStmt.setString(3,dataClass.toUpperCase());
			
			logger.debug("In TestDataDAO.getKeyValue message=Prameter "+testClass.toUpperCase()+", "+condition.toUpperCase()+", "+ dataClass.toUpperCase());
			logger.debug("In TestDataDAO.getKeyValue message=Query "+sql);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()){
				testData.setDataTable(rs.getString("DATATABLE"));
				testData.setDataFile(rs.getString("DATAFILE"));
				testData.setDataHelperClass(rs.getString("DATAHELPERCLASS"));
				column = new KeyColumn(rs.getString("KEYCOLUMN"),rs.getString("VALUE")); 
				keyColumns.add(column);
			}
			while(rs.next()){
				column = new KeyColumn(rs.getString("KEYCOLUMN"),rs.getString("VALUE")); 
				keyColumns.add(column);
			}
			testData.setKeyColumns(keyColumns);
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return testData;
	}
	
	public static String getDataTable(String dataClass, Connection con) throws SQLException, IJunitException{
		logger.debug("Entered in TestDataDAO.getDataTable()");
		PreparedStatement pStmt = null;
		String tableName = null;
		try{
			Map map = new HashMap();
			String sql = "SELECT dataTable FROM DataColumn WHERE class LIKE ?";
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, dataClass);
			
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()){
				tableName = rs.getString("DATATABLE");
			}else{
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.PREPARING_INSEERTQUERY_EXP));
			}
			
			logger.debug("Exited from TestDataDAO.getPrimaryColumn()");
		}finally
        {
            try
            {
                if(pStmt != null) pStmt.close();
            }
            catch (SQLException e)
            {
            	logger.error("In TestDataDAO.getDataTable "+ErrorMessageParser.getMessage(
            			IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
            }
        }
		return tableName;
	}
	
	public static Object getAppTestData(TestData testData, Connection appCon) throws SQLException, IJunitException{
		logger.debug("Entered in TestDataDAO.getAppTestData(TestData,Connection)");
		Object obj = null;
		obj = getAppTestData(testData, appCon, null, null);
		logger.debug("Exited from TestDataDAO.getAppTestData(TestData,Connection)");
		return obj;
	}
	
	public static Object getAppTestData(TestData testData, Connection appCon, Class[] argumentType, Object[] values) throws SQLException, IJunitException{
		
		logger.debug("Entered in TestDataDAO.getAppTestData(TestData,Connection,Class[],Object[])");
		ResultSet rs = null;
		Object obj = null;
		PreparedStatement pStmt = null;
		try{
			StringBuffer sql = new StringBuffer("SELECT * FROM ");
			sql.append(testData.getDataTable()).append((" WHERE "));
			Vector keyColumns = testData.getKeyColumns();
			int size = keyColumns.size();
			for (Iterator iter = keyColumns.iterator() ; iter.hasNext();) {
				KeyColumn element = (KeyColumn) iter.next();
				sql.append(element.getColumn()).append(" = ").append("'").append(element.getValue()).append("'");
				size--;
				if(size > 0){
					sql.append(" AND ");
				}
			}
			logger.debug("In TestDataDAO.getAppTestData message= Query to fetch Test Data "+sql);
	
			pStmt = appCon.prepareStatement(sql.toString());
			rs = pStmt.executeQuery();
			if(rs.next()){
				obj = fillObject(testData, rs, argumentType, values);
			}
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		logger.debug("Exited from TestDataDAO.getAppTestData(TestData,Connection,Class[],Object[])");
		return obj;
	}
	
	
	public static Object fillObject(TestData testData, ResultSet rs,Class[] argumentType, Object[] values) throws IJunitException{
		logger.debug("Entered in TestDataDAO.fillObject()");
		Object dataObj = null;
		Method method = null;
		try {
			//Class dataClass = Class.forName(testData.getDataClass());
			Class dataClass = GTestClassLoader.getInstance().loadClass(testData.getDataClass());
			logger.debug("In TestDataDAO.fillObject message= Test Data Class "+testData.getDataClass());
	        //dataObj = dataClass.newInstance();
	        Constructor constructor = dataClass.getConstructor(argumentType);
			dataObj = constructor.newInstance(values);
	        
			ObjectDataParser parser = new ObjectDataParser();
			logger.debug("In TestDataDAO.fillObject message= Test Data file "+ResourceManager.getFile(testData.getDataFile()));

			parser.parse(ResourceManager.getFile(testData.getDataFile()).toString());
			Vector vec = parser.getAttributeDataList();
			for (Iterator iter = vec.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				logger.debug("In TestDataDAO.fillObject message= Attribute Data  "+element);
				if(!Validation.isEmpty( element.getSetMethod() )){
					if(!Validation.isEmpty( element.getColumn() )){
						Object objValue = CommonUtility.fetchAppDataValue(element.getColumn(),element.getType(),
								element.getColumnType(), rs);
						logger.debug("In TestDataDAO.fillObject message= Attribute Data Value  "+objValue);
						method = dataClass.getMethod(CommonUtility.removeArgument(element.getSetMethod()), new Class[]{DataTypes.getDataType(element.getType())});
						method.invoke(dataObj, new Object[]{objValue});
					}else{
						method = dataClass.getMethod(CommonUtility.removeArgument(element.getSetMethod()), new Class[]{DataTypes.getDataType(element.getType())});
						method.invoke(dataObj, new Object[]{CommonUtility.getActualTypeValue((String)element.getValue(), element.getType() )});
					}
				}
			}
		} catch (Exception e) {
			logger.error("In TestDataDAO.fillObject "+ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA),e);
		} 
		
		logger.debug("Exited from TestDataDAO.fillObject()");
		return dataObj;
	}
	
	public static Object[] getColumnNames(String tableName, Connection appCon) throws SQLException{
		TreeSet set = null;
		PreparedStatement pStmt = null;
		try {
			set = new TreeSet();
			String query = "SELECT * FROM "+tableName;
			pStmt = appCon.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			for(int i=1; i<=metaData.getColumnCount();i++){
				//logger.debug("In AddObjectAction.getColumnNames message= Column  "+i+"="+metaData.getColumnName(i));
				set.add(metaData.getColumnName(i));
			}
		} finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return set.toArray();
	}
	
	public static ResultSetMetaData getTableMetaData(String tableName,Connection appCon) throws SQLException{
		logger.debug("Entered in TestDataDAO.getTableMetaData()");
		
		ResultSetMetaData metaData = null;
		PreparedStatement pStmt = null;
		String query = "SELECT * FROM "+tableName;
		pStmt = appCon.prepareStatement(query);
		ResultSet rs = pStmt.executeQuery();
		metaData = rs.getMetaData();
	
		logger.debug("Exited from TestDataDAO.getTableMetaData()");
		return metaData;
	}
	
public static ResultSet getAppTestDataResultSet(TestData testData, Connection appCon) throws SQLException, IJunitException{
		
		logger.debug("Entered in TestDataDAO.getTestData()");
		ResultSet rs = null;
		Object obj = null;
		PreparedStatement pStmt = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		sql.append(testData.getDataTable()).append((" WHERE "));
		Vector keyColumns = testData.getKeyColumns();
		int size = keyColumns.size();
		for (Iterator iter = keyColumns.iterator() ; iter.hasNext();) {
			KeyColumn element = (KeyColumn) iter.next();
			sql.append(element.getColumn()).append(" = ").append("'").append(element.getValue()).append("'");
			size--;
			if(size > 0){
				sql.append(" AND ");
			}
		}
		logger.debug("In TestDataDAO.getTestData message= Query to fetch Test Data "+sql);

		pStmt = appCon.prepareStatement(sql.toString());
		rs = pStmt.executeQuery();
		logger.debug("Exited from TestDataDAO.getTestData()");
		return rs;
	}

	public static ResultSet getAppTestDataResultSet(String sql, ArrayList bindVariable, Connection appCon) throws IJunitException{
		logger.debug("Entered in TestDataDAO.getTestData()");
		ResultSet rs = null;
		Object obj = null;
		PreparedStatement pStmt = null;
		try{
			logger.debug("In TestDataDAO.getTestData message= Query to fetch Test Data "+sql);
			pStmt = appCon.prepareStatement(sql);
			if(bindVariable != null){
				registerBindVariable(pStmt, bindVariable);
			}
			rs = pStmt.executeQuery();
			logger.debug("Exited from TestDataDAO.getTestData()");
		}catch (SQLException e) {
			logger.error("In TestDataDAO.getAppTestDataResultSet "+e.getMessage(), e);
			throw new IJunitException(e.getMessage(),e);
		}
		return rs;
	}


	public static void deleteTestData(String testClass, String dataClass, String conditionName, Connection con) throws SQLException{
		logger.debug("Entered in TestDataDAO.deleteTestData()");
		PreparedStatement pStmt = null;
		try{
			String sql = "DELETE FROM TestData, TestCondition, DataColumn " +
					"WHERE TestCondition.ID = TestData.TestConditionID " +
					"AND DataColumn.ID = TestData.DataColumnID " +
					"AND TestCondition.class = ? AND DataColumn.class = ? " +
					"AND TestCondition.condition = ? ";
			pStmt = con.prepareStatement(sql);		
			pStmt.setString(1,testClass);
			pStmt.setString(2, dataClass);
			pStmt.setString(3, conditionName);
			
			pStmt.execute();
			logger.debug("Exited from enclosing_type.enclosing_method()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
	}

	/**
	 * This method will return a list of Data class name corrsponding to 
	 * TestClass and condition.
	 * @param testClass
	 * @param condition
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList getDataClass(String testClass, String condition, Connection con) throws SQLException{
		logger.debug("Entered in TestDataDAO.getDataClass()");
		ArrayList<String> list = null;
		PreparedStatement pStmt = null;
		try{
			String sql = "SELECT distinct(datacolumn.CLASS) "+
									  "FROM testcondition, testdata, datacolumn "+
									 "WHERE testdata.testconditionid = testcondition.ID "+
									   "AND testdata.datacolumnid = datacolumn.ID "+
									   "AND testcondition.CLASS = ? "+
									   "AND condition = ?";
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, testClass);
			pStmt.setString(2, condition);
			logger.debug("TestDataDAO.getDataClass() Query "+sql);
			logger.debug("TestDataDAO.getDataClass() bind Variable "+testClass+" ,"+condition);
			ResultSet rs = pStmt.executeQuery();
			list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("CLASS"));
			}
			logger.debug("Exited from TestDataDAO.getDataClass()");
		}finally {
			try{
				if(pStmt != null) pStmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return list;
	}
	
	/**
	 * This method will register the Prpeapred Statement with its actual values.
	 * Values will be passed as a collection ArrayList.
	 * 
	 * @param pStmt
	 * @param bindVariable
	 * @throws SQLException
	 */
	public static void registerBindVariable(PreparedStatement pStmt,
			ArrayList bindVariable) throws SQLException {
		
		// i-emphsys 4.1: Issue # 96: Added Double object type for registering
		// prepared statement.
		logger.debug("In TestDataDAO.registerBindVariable message= BindVariable to be registered "+bindVariable);
		for (int i = 1; i <= bindVariable.size(); i++) {
			Object objValue = bindVariable.get(i - 1);

			// i-emphsys 4.1: issue # 352: added null check for objValue and
			// used setNull to insert SQL Null in DB.
			if (objValue != null) {
				if (objValue instanceof java.lang.String) {
					pStmt.setString(i, (String) objValue);
				} else if (objValue instanceof java.lang.Float) {
					pStmt.setFloat(i, ((Float) objValue).floatValue());
				} else if (objValue instanceof java.lang.Integer) {
					pStmt.setInt(i, ((Integer) objValue).intValue());
				} else if (objValue instanceof java.sql.Date) {
					pStmt.setDate(i, (Date) objValue);
				} else if (objValue instanceof java.lang.Boolean) {
					pStmt.setBoolean(i, ((Boolean) objValue).booleanValue());
				} else if (objValue instanceof java.lang.Double) {
					pStmt.setDouble(i, ((Double) objValue).doubleValue());
				}
			} else {
				pStmt.setNull(i, Types.VARCHAR);
			}
		}// end of for loop
	}// end of method

	public static int[] executeBatchQueries(Object[] queries, Connection con) throws IJunitException{
		Statement stmt = null;
		int[] results = null;
		try {
			int size = queries.length;
			stmt = con.createStatement();
			if (queries != null && queries.length > 0) {
				int len = queries.length;
				logger.debug(" In TestDataDAO executeBatchQueries starts************");

				for (int i = 0; i < len; i++) {
					logger.debug("In TestDataDAO executeBatchQueries "+i+"->"+(String) queries[i]);
					stmt.addBatch((String) queries[i]);
				}
			}
			results = stmt.executeBatch();
			logger.debug(" In TestDataDAO executeBatchQueries ends************");
		} catch (SQLException e) {
			logger.error("In TestDataDAO.getAppTestDataResultSet "+e.getMessage(), e);
			throw new IJunitException(e.getMessage(),e);
		} finally {
			try{
				if(stmt != null) stmt.close();
			}catch (SQLException e)	{
				logger.error("In TestDataDAO.addTestData "+ErrorMessageParser.getMessage(
						IExceptionCodes.GENERIC_EXP,new String[]{e.getMessage()}), e);
			}
		}
		return results;
	}
	
}
