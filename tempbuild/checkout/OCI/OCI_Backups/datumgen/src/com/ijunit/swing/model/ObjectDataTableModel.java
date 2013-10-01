/**
 * 
 */
package com.ijunit.swing.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.ijunit.common.Logger;
import com.ijunit.swing.action.AddObjectAction;

/**
 * @author vicky
 *
 */
public class ObjectDataTableModel extends AbstractTableModel implements IAttributeDataModel
{
	//static Logger logger = Logger.getLogger(AttributeDataModel.class);
	Vector vec = null;
	String [] columnHeader = {" ","Attributes","Setter Methods","DataBase Columns","Value","Key"};
	
	String className;
	
	boolean isKeyColumnEditable = true;
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	public ObjectDataTableModel()
	{
		vec = new Vector();
//		createModel();
	}
	
	public int getColumnCount()
	{
		return columnHeader.length;
	}

	public int getRowCount() 
	{
		return vec.size();
	}

	@Override
	public String getColumnName(int column) {

		return columnHeader[column];
	}
	
	@Override
	public Class getColumnClass(int column) {
		if(column == 0 || column == 5){
			return Boolean.class;
		}else{
			return String.class;
		}
	}
	
	public Object getValueAt(int row, int column) 
	{
		AttributeData data = (AttributeData)vec.get(row);
		switch(column)
		{
			case 0: return data.isToDelete();	
			case 1: return data.getName();
			case 2: return data.getSetMethod();
			case 3: return data.getColumn();
			case 4: return data.getValue();
			case 5: return data.isPrimaryKey();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object obj, int row, int column) 
	{
		//logger.debug("ENTER AttributeTableModel.setValueAt()");
		//logger.debug("Row to Update "+row+" "+column);
		//super.setValueAt(arg0, arg1, arg2);
		AttributeData data = (AttributeData)vec.get(row);
		switch(column)
		{
			case 0: data.setToDelete(((Boolean)obj).booleanValue()); break;	
			case 4: data.setValue(obj); break;
			case 5: data.setPrimaryKey(((Boolean)obj).booleanValue()); break;
			default: break;
		}
		data = (AttributeData)vec.get(row);
		fireTableChanged(new TableModelEvent(this,TableModelEvent.UPDATE));
		//logger.debug("EXIT AttributeTableModel.setValueAt()");
	}
	
	public void addAttributeData(AttributeData data)
	{
		vec.add(data);
		fireTableChanged(new TableModelEvent(this));
		
	}
	
	public AttributeData getAttributeDataAt(int row)
	{
		return (AttributeData)vec.elementAt(row);
	}
	
	
	@Override
	public boolean isCellEditable(int row, int column) 
	{
		return column == 0 || column == 4 || (column == 5 && isKeyColumnEditable);
	}

	public void removeAttributeData(int row) 
	{
		vec.remove(row);
		fireTableRowsDeleted(row, row);
		
	}

	public void removeAll() 
	{
		int size = vec.size();
		vec.clear();
		fireTableRowsDeleted(0, size-1);
	}


	public List getAllAttributeData() {
		return vec;
	}
	
	public int getAttributeDataIndex(String fieldName){
		List attributeData = getAllAttributeData();
		AttributeData element = null;
		int index = 0;
		boolean attributeDataFound = false;
		for (Iterator iter = attributeData.iterator(); iter.hasNext();) {
			element = (AttributeData) iter.next();
			if(element.getName().equalsIgnoreCase(fieldName)){
				attributeDataFound = true;
				break;
			}
			index++;
		}
		if(attributeDataFound)
			return index;
		else 
			return -1;
	}
	
	public void updateTable(int firstRow, int lastRow){
		fireTableRowsUpdated(firstRow, lastRow);
	}
	
	public void setKeyColumnEditable(boolean value){
		isKeyColumnEditable = value;
	}
	
}
