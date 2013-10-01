/**
 * 
 */
package com.ijunit.common.constant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import com.ijunit.common.Logger;

/**
 * @author vcjain
 * 
 * This class defines constant for data types. Also provides method to
 * get the data types constant, String & class representation.
 */
public class DataTypes {

	static Logger logger = Logger.getLogger(DataTypes.class);

	public final static int SHORT = 1;
	public final static int INT = 2;
	public final static int LONG = 3;
	public final static int FLOAT = 4;
	public final static int DOUBLE = 5;
	public final static int SHORT_OBJ = 6;
	public final static int INT_OBJ = 7;
	public final static int LONG_OBJ = 8;
	public final static int FLOAT_OBJ = 9;
	public final static int DOUBLE_OBJ = 10;
	public final static int BIGDECIMAL = 11;
	public final static int BIGINTEGER = 12;
	public final static int DATEUTIL = 13;
	public final static int DATESQL = 14;
	public final static int STRING = 15;
	public final static int BOOLEAN = 16;
	public final static int BOOLEAN_OBJ = 17;
	public final static int TIMESTAMP = 18;
	public final static int OBJECT = 19;
	public final static int BITSET = 20;
	public final static int CHAR = 21;
	public final static int CHARACTER = 22;

	public final static int COLLECTION = 23;
	public final static int LIST = 24;
	public final static int VECTOR = 25;
	public final static int ARRAYLIST = 26;
	public final static int LINKEDLIST = 27;
	public final static int SET = 28;
	public final static int HASHSET = 29;
	public final static int TREESET = 30;
	public final static int LINKEDHASHSET = 31;

	public final static int MAP = 32;
	public final static int HASHMAP = 33;
	public final static int TREEMAP = 34;
	public final static int LINKEDHASHMAP = 35;
	public final static int HASHTABLE = 36;

	public final static int DB_NUMBER = 2;
	public final static int DB_VARCHAR2 = 12;
	public final static int DB_DATE = 91;
	public final static int DB_TIMESTAMP = 93;

	static Map dataTypeMap = new HashMap();

	static {
		dataTypeMap.put("short", SHORT);
		dataTypeMap.put("int", INT);
		dataTypeMap.put("long", LONG);
		dataTypeMap.put("float", FLOAT);
		dataTypeMap.put("double", DOUBLE);
		dataTypeMap.put("char", CHAR);
		dataTypeMap.put("java.lang.Short", SHORT_OBJ);
		dataTypeMap.put("java.lang.Integer", INT_OBJ);
		dataTypeMap.put("java.lang.Long", LONG_OBJ);
		dataTypeMap.put("java.lang.Float", FLOAT_OBJ);
		dataTypeMap.put("java.lang.Double", DOUBLE_OBJ);
		dataTypeMap.put("java.math.BigDecimal", BIGDECIMAL);
		dataTypeMap.put("java.math.BigInteger", BIGINTEGER);
		dataTypeMap.put("java.lang.boolean", BOOLEAN_OBJ);
		dataTypeMap.put("boolean", BOOLEAN);
		dataTypeMap.put("java.lang.String", STRING);
		dataTypeMap.put("java.util.Date", DATEUTIL);
		dataTypeMap.put("java.sql.Date", DATESQL);
		dataTypeMap.put("java.sql.Timestamp", TIMESTAMP);
		dataTypeMap.put("java.lang.Object", OBJECT);
		dataTypeMap.put("java.util.BitSet", BITSET);
		dataTypeMap.put("java.lang.Character", CHARACTER);
		dataTypeMap.put("java.util.Collection", COLLECTION);
		dataTypeMap.put("java.util.Set", SET);
		dataTypeMap.put("java.util.List", LIST);
		dataTypeMap.put("java.util.Map", MAP);
		dataTypeMap.put("java.util.HashSet", HASHSET);
		dataTypeMap.put("java.util.TreeSet", TREESET);
		dataTypeMap.put("java.util.LinkedHashSet", LINKEDHASHSET);
		dataTypeMap.put("java.util.Vector", VECTOR);
		dataTypeMap.put("java.util.ArrayList", ARRAYLIST);
		dataTypeMap.put("java.util.LinkedList", LINKEDLIST);
		dataTypeMap.put("java.util.HashMap", HASHMAP);
		dataTypeMap.put("java.util.Hashtable", HASHTABLE);
		dataTypeMap.put("java.util.LinkedHashMap", LINKEDHASHMAP);
		dataTypeMap.put("java.util.TreeMap", TREEMAP);
	}

	/**
	 * Method returns the integer value for a data Type.
	 * 
	 * @param type
	 *            - Data type
	 * @return - Integer constant for data type.
	 */
	public static int getTypeConstant(String type) {
		logger.debug("Entered in DataTypes.getTypeConstant() Key " + type);
		Object obj = dataTypeMap.get(type);
		obj = (obj == null) ? dataTypeMap.get("java.lang.Object") : obj;
		return ((Integer) obj).intValue();
	}

	/**
	 * Method returns the data type represented by a Integer constant passed as
	 * argument.
	 * 
	 * @param type
	 *            - Integer constant for a data type
	 * @return - Data type (String name)
	 */
	public static String getDataTypeName(int type) {
		switch (type) {
		case 1:
			return short.class.getName();
		case 2:
			return int.class.getName();
		case 3:
			return long.class.getName();
		case 4:
			return float.class.getName();
		case 5:
			return double.class.getName();
		case 6:
			return Short.class.getName();
		case 7:
			return Integer.class.getName();
		case 8:
			return Long.class.getName();
		case 9:
			return Float.class.getName();
		case 10:
			return Double.class.getName();
		case 11:
			return BigDecimal.class.getName();
		case 12:
			return BigInteger.class.getName();
		case 13:
			return java.util.Date.class.getName();
		case 14:
			return java.sql.Date.class.getName();
		case 15:
			return String.class.getName();
		case 16:
			return boolean.class.getName();
		case 17:
			return Boolean.class.getName();
		case 18:
			return Timestamp.class.getName();
		case 19:
			return Object.class.getName();
		case 20:
			return BitSet.class.getName();
		case 21:
			return char.class.getName();
		case 22:
			return Character.class.getName();
		case 23:
			return Collection.class.getName();
		case 24:
			return List.class.getName();
		case 25:
			return Vector.class.getName();
		case 26:
			return ArrayList.class.getName();
		case 27:
			return LinkedList.class.getName();
		case 28:
			return Set.class.getName();
		case 29:
			return HashSet.class.getName();
		case 30:
			return TreeSet.class.getName();
		case 31:
			return LinkedHashSet.class.getName();
		case 32:
			return Map.class.getName();
		case 33:
			return HashMap.class.getName();
		case 34:
			return TreeMap.class.getName();
		case 35:
			return LinkedHashMap.class.getName();
		case 36:
			return Hashtable.class.getName();
		default:
			return "";
		}
	}

	/**
	 * Method returns the data type represented by a Integer constant passed as
	 * argument.
	 * 
	 * @param type
	 *            - Integer constant for a data type
	 * @return - Class instance of Data type
	 */
	public static Class getDataType(int type) {
		switch (type) {
		case 1:
			return short.class;
		case 2:
			return int.class;
		case 3:
			return long.class;
		case 4:
			return float.class;
		case 5:
			return double.class;
		case 6:
			return Short.class;
		case 7:
			return Integer.class;
		case 8:
			return Long.class;
		case 9:
			return Float.class;
		case 10:
			return Double.class;
		case 11:
			return BigDecimal.class;
		case 12:
			return BigInteger.class;
		case 13:
			return java.util.Date.class;
		case 14:
			return java.sql.Date.class;
		case 15:
			return String.class;
		case 16:
			return boolean.class;
		case 17:
			return Boolean.class;
		case 18:
			return Timestamp.class;
		case 19:
			return Object.class;
		case 20:
			return BitSet.class;
		case 21:
			return char.class;
		case 22:
			return Character.class;
		case 23:
			return Collection.class;
		case 24:
			return List.class;
		case 25:
			return Vector.class;
		case 26:
			return ArrayList.class;
		case 27:
			return LinkedList.class;
		case 28:
			return Set.class;
		case 29:
			return HashSet.class;
		case 30:
			return TreeSet.class;
		case 31:
			return LinkedHashSet.class;
		case 32:
			return Map.class;
		case 33:
			return HashMap.class;
		case 34:
			return TreeMap.class;
		case 35:
			return LinkedHashMap.class;
		case 36:
			return Hashtable.class;
		default:
			return null;
		}
	}

	/**
	 * Method to check if a data type constant belongs to a Number category
	 * 
	 * @param type
	 *            - Integer constant for data type
	 * @return - true if data type is of Number type otherwise false.
	 */
	public static boolean isNumberType(int type) {
		switch (type) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Method to check if a data type constant belongs to a Boolean category
	 * 
	 * @param type
	 *            - Integer constant for data type
	 * @return - true if data type is of Boolean type otherwise false.
	 */
	public static boolean isBooleanType(int type) {
		switch (type) {
		case 16:
		case 17:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Method to check if a data type constant belongs to a Date category
	 * 
	 * @param type
	 *            - Integer constant for data type
	 * @return - true if data type is of Date type otherwise false.
	 */
	public static boolean isDateType(int type) {
		switch (type) {
		case 13:
		case 14:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Method to check if a data type constant belongs to a timestamp category
	 * 
	 * @param type
	 *            - Integer constant for data type
	 * @return - true if data type is of timestamp type otherwise false.
	 */
	public static boolean isTimestampType(int type) {
		switch (type) {
		case 18:
			return true;
		default:
			return false;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 22; i++) {
			System.out.println(getDataType(i));
		}
	}

}
