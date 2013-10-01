/**
 * 
 */
package com.ijunit.swing.model;

import java.util.List;

import javax.swing.table.TableModel;

/**
 * @author vicky
 *
 */
public interface IAttributeDataModel extends TableModel
{
	public void addAttributeData(AttributeData data);
	
	public void removeAttributeData(int row);
	
	public AttributeData getAttributeDataAt(int row);
	
	public void setClassName(String className);
	
	public void removeAll();
	
	public String getClassName();
	
	public List getAllAttributeData();
	
	public int getAttributeDataIndex(String fieldName);
	
	public void updateTable(int firstRow, int lastRow);
	
	public void setKeyColumnEditable(boolean value);
}
