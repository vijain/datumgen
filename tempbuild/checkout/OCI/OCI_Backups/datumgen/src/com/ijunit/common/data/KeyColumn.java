/**
 * 
 */
package com.ijunit.common.data;

import com.ijunit.common.Logger;

/**
 * A POJO class to represent a primary column of a business
 * object. This class will holds a column name and its value for a
 * business object.
 * 
 * @author Vikash Jain 
 *         
 */
public class KeyColumn {

	static Logger logger = Logger.getLogger(KeyColumn.class);

	/**
	 * Member variable to hold column name
	 */
	private String column;
	
	/**
	 * Member variable to hold column value
	 */
	private String value;

	/**
	 * Default constructor
	 */
	public KeyColumn() {
	}

	/**
	 * Constructor.
	 * @param column
	 * @param value
	 */
	public KeyColumn(String column, String value) {
		this.column = column;
		this.value = value;
	}

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return column + "-" + value;
	}
}
