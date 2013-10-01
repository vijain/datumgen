/**
 * 
 */
package com.ijunit.swing.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.ijunit.common.Logger;
import com.ijunit.common.data.KeyColumn;

/**
 * @author vcjain
 *
 */
public class SearchDataTblModel extends AbstractTableModel
{
	static Logger logger = Logger.getLogger(SearchDataTblModel.class);
	
	Vector vec = null;
	Map selectedIndexMap = new HashMap();
	String [] columnHeader = {" ","KeyColumn","Value"};
	
	public SearchDataTblModel(){
		vec = new Vector();
	}
    	
	public SearchDataTblModel(Vector vec){
		this.vec = vec;
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
		if(column == 0 ){
			return Boolean.class;
		}else{
			return String.class;
		}
	}
	
	public Object getValueAt(int row, int column) 
	{
		KeyColumn data = (KeyColumn)vec.get(row);
		if(column == 0){
			return selectedIndexMap.get(data.getColumn());
		}else{
			switch(column)
			{
				case 1: return data.getColumn() ;
				case 2: return data.getValue();
				default : return null;
			}
		}
	}
	
	public void setValueAt(Object obj, int row, int column) 
	{
		logger.debug("Entered in KeyColumnModel.setValueAt()");
		KeyColumn data = (KeyColumn)vec.get(row);
		if(column == 0){
			selectedIndexMap.put(data.getColumn(),((Boolean)obj).booleanValue());
		}else{
			switch(column)
			{
				case 1: data.setColumn((String)obj); break;
				case 2: data.setValue((String)obj); break;
				default: break;
			}
		}
		fireTableChanged(new TableModelEvent(this,TableModelEvent.UPDATE));
		logger.debug("Exited from KeyColumnModel.setValueAt()");
	}
	
	@Override
	public boolean isCellEditable(int row, int column) 
	{
		return column == 0;
	}
	
	public Vector getSeletedIndex(){
		Vector selectedIndexes = new Vector();
		for (int i = 0; i < vec.size(); i++) {
			Boolean value = (Boolean)getValueAt(i, 0);
			if(value != null && value){
				selectedIndexes.add(i);
			}
		}
		return selectedIndexes;
	}
	
	public void setDataVector(Vector vec){
		this.vec = vec;
		fireTableChanged(new TableModelEvent(this));
	}
	
	public void removeAll(){
		this.vec.removeAllElements();
		fireTableChanged(new TableModelEvent(this));
	}

}
