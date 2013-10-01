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

/**
 * @author vcjain
 *
 */
public class TestDataTblModel extends AbstractTableModel implements IAttributeDataModel{

	static Logger logger = Logger.getLogger(TestDataTblModel.class);
	Vector attributeDataVec = null;
	String [] columnHeader = {" ","Attributes","Value"};
	String dataClassName = null;
	

	public TestDataTblModel(){
		attributeDataVec = new Vector();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnHeader.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return attributeDataVec.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		AttributeData data = (AttributeData)attributeDataVec.get(rowIndex);
		switch(columnIndex)
		{
			case 0: return data.isToInsert();
			case 1: return data.getName();
			case 2: return data.getValue();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return columnHeader[column];
	}
	
	@Override
	public Class getColumnClass(int column) {
		if(column == 0){
			return Boolean.class;
		}else{
			return String.class;
		}
	}
	
	@Override
	public void setValueAt(Object obj, int row, int column) 
	{
		AttributeData data = (AttributeData)attributeDataVec.get(row);
		switch(column)
		{
			case 0: data.setToInsert(((Boolean)obj).booleanValue()); break;	
			case 2: data.setValue(obj); break;
			default: break;
		}		fireTableChanged(new TableModelEvent(this,TableModelEvent.UPDATE));
	}
	
	public void addAttributeData(Vector vec)
	{
		attributeDataVec.addAll(vec);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public AttributeData getAttributeDataAt(int row)
	{
		return (AttributeData)attributeDataVec.elementAt(row);
	}
	
	
	@Override
	public boolean isCellEditable(int row, int column) 
	{
		return column == 0 || column == 2;
	}

	public void removeAttributeData(int row) 
	{
		attributeDataVec.remove(row);
		fireTableRowsDeleted(row, row);
		
	}

	public void removeAll() 
	{
		int size = attributeDataVec.size();
		attributeDataVec.clear();
		fireTableRowsDeleted(0, size-1);
	}


	public List getAllAttributeData() {
		return attributeDataVec;
	}

	public List getAllSelectedAttributeData(){
		ArrayList list = new ArrayList();
		boolean insertFound = false;
		for (Iterator iter = attributeDataVec.iterator(); iter.hasNext();) {
			AttributeData element = (AttributeData) iter.next();
			if(element.isToInsert()){
				list.add(element);
			}
		}
		return list;
	}
	
	public int getIndexOf(Object obj){
		return attributeDataVec.indexOf(obj);
	}
	
	public void setValueAtRow(String value, int row){
		AttributeData data = (AttributeData)attributeDataVec.get(row);
		data.setValue(value);
		fireTableChanged(new TableModelEvent(this));
	}

	public void addAttributeData(AttributeData data) {
		attributeDataVec.add(data);
		fireTableRowsInserted(getRowCount(), getRowCount());
	}

	public int getAttributeDataIndex(String fieldName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getClassName() {
		return dataClassName;
	}

	public void setClassName(String className) {
		this.dataClassName = className;
	}

	public void updateTable(int firstRow, int lastRow){
		fireTableRowsUpdated(firstRow, lastRow);
	}
	
	public void setKeyColumnEditable(boolean value){
		
	}
}
