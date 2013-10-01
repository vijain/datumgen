/**
 * 
 */
package com.ijunit.swing.model;

import java.io.Serializable;

/**
 * @author vicky
 *
 */
public class AttributeData implements Serializable 
{
	/**
	 * Name of Java field
	 */
	private String name;
	
	/**
	 * Name of Setter method
	 */
	private String setMethod;
	
	/**
	 * Name of Getter method
	 */
	private String getMethod;
	
	/**
	 * Java Type of field
	 */
	private int type;
	
	/**
	 * Indicate is to delete attribute data 
	 */
	private boolean isToDelete = false;
	
	/**
	 * Value to be hold by field
	 */
	private Object value;
	
	/**
	 * Indicate whether field is part of Primary Key.
	 */
	private boolean isPrimaryKey;
	
	/**
	 * Indicates whether to save field as part of AddObject.
	 */
	private boolean isToInsert;
	
	//Database column's property
	
	/**
	 * Name of Database column
	 */
	private String column;
	
	/**
	 * Indicates the designated column's normal maximum width in characters.
	 */
	private int columnDisplaySize;
	
	/**
	 * Retrieves the designated column's SQL type.
	 */
	private int columnType;
	
	/**
	 * Retrieves the designated column's database-specific type name.
	 */
	private String columnTypeName;
	
	/**
	 * Get the designated column's specified column size.
	 */
	private int precision;
	
	/**
	 * Gets the designated column's number of digits to right of the decimal point.
	 */
	private int scale;
	
	/**
	 * Indicates whether the designated column is automatically numbered.
	 */
	private boolean isAutoIncrement;
	
	/**
	 *  Indicates the nullability of values in the designated column.
	 */
	private int isNullable;
	
	
	/**
	 * @return the isToInsert
	 */
	public boolean isToInsert() {
		return isToInsert;
	}
	/**
	 * @param isToInsert the isToInsert to set
	 */
	public void setToInsert(boolean isToInsert) {
		this.isToInsert = isToInsert;
	}
	/**
	 * @return the isPrimaryKey
	 */
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	/**
	 * @param isPrimaryKey the isPrimaryKey to set
	 */
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	
	/**
	 * @return the columnDisplaySize
	 */
	public int getColumnDisplaySize() {
		return columnDisplaySize;
	}
	/**
	 * @param columnDisplaySize the columnDisplaySize to set
	 */
	public void setColumnDisplaySize(int columnDisplaySize) {
		this.columnDisplaySize = columnDisplaySize;
	}
	/**
	 * @return the columnType
	 */
	public int getColumnType() {
		return columnType;
	}
	/**
	 * @param columnType the columnType to set
	 */
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	/**
	 * @return the columnTypeName
	 */
	public String getColumnTypeName() {
		return columnTypeName;
	}
	/**
	 * @param columnTypeName the columnTypeName to set
	 */
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	/**
	 * @return the isAutoIncrement
	 */
	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}
	/**
	 * @param isAutoIncrement the isAutoIncrement to set
	 */
	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}
	/**
	 * @return the isNullable
	 */
	public int getIsNullable() {
		return isNullable;
	}
	/**
	 * @param isNullable the isNullable to set
	 */
	public void setIsNullable(int isNullable) {
		this.isNullable = isNullable;
	}
	/**
	 * @return the precision
	 */
	public int getPrecision() {
		return precision;
	}
	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	/**
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}
	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
	/**
	 * @return the getMethod
	 */
	public String getGetMethod() {
		return getMethod;
	}
	/**
	 * @param getMethod the getMethod to set
	 */
	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}
	/**
	 * @return the isToDelete
	 */
	public boolean isToDelete() {
		return isToDelete;
	}
	/**
	 * @param isToDelete the isToDelete to set
	 */
	public void setToDelete(boolean isToDelete) {
		this.isToDelete = isToDelete;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the setMethod
	 */
	public String getSetMethod() {
		return setMethod;
	}
	/**
	 * @param setMethod the setMethod to set
	 */
	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	public AttributeData(){
		
	}
	
	public AttributeData(String name, int type, String setMethod, String column, Object value)
	{
		this.name = name;
		this.type = type;
		this.column = column;
		this.setMethod = setMethod;
		this.value = value;
	}
	
	public AttributeData(String name, int type, String setMethod, String column)
	{
		this(name,type,setMethod,column,"");
	}
	
	public AttributeData(String name, int type, String setMethod){
		this(name,type,setMethod,"");
	}
	
	
	public String toString(){
		return "Name="+name
				+", type="+type
				+", SetMethod="+setMethod
				+", Column="+column
				+", Value="+value;
	}
	
	
}
