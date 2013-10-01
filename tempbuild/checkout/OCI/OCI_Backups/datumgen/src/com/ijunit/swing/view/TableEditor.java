/**
 * 
 */
package com.ijunit.swing.view;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.DataTypes;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.Validation;
import com.ijunit.swing.model.AttributeData;
import com.ijunit.swing.model.IAttributeDataModel;
import com.ijunit.swing.model.ObjectDataTableModel;
import com.ijunit.swing.model.TestDataTblModel;

/**
 * @author vcjain
 *
 */
public class TableEditor extends AbstractCellEditor implements TableCellEditor{

	static Logger logger = Logger.getLogger(TableEditor.class);
	
	public static final int DATAOBJECT = 1;
	public static final int TESTDATA = 2;
	
	
	private int mode;
	
	private JComboBox cmbBox = null;
	private JTextField txtField = null;
	private JCheckBox chkBox = null;
	private int row;
	private int column;
	String editValue;
	private JTable table = null; 
	
	public TableEditor(int mode){
		this.mode = mode;
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		logger.debug("Entered in TableEditor.getTableCellEditorComponent() row "+row);
		cmbBox = null;
		txtField = null;
		chkBox = null;
		editValue = String.valueOf(value).trim();
		logger.debug("In TableEditor.getTableCellEditorComponent message= editValue "+editValue);
		this.row = row;
		this.column = column;
		this.table = table;
		IAttributeDataModel model = null;
		if(mode == DATAOBJECT){
			model = (IAttributeDataModel)table.getModel();
		}else if(mode == TESTDATA){
			model = (TestDataTblModel)table.getModel();
		}
		AttributeData data = (AttributeData)model.getAttributeDataAt(row);
		if(data.getType() == DataTypes.BOOLEAN || data.getType() == DataTypes.BOOLEAN_OBJ){
//			chkBox = new JCheckBox();
//			return chkBox;
			txtField = new JTextField();
			txtField.setText(editValue);
			return txtField;
		}else if(editValue.indexOf(',') != -1){
			cmbBox = new JComboBox();
			cmbBox.setSelectedIndex(1);
			return cmbBox;
		}else{
			txtField = new JTextField();
			txtField.setText(editValue);
			return txtField;
		}
	}

	public Object getCellEditorValue() {
		logger.debug("Entered in GTCellEditor.getCellEditorValue()");
		if(txtField != null){
			logger.debug("Exited from GTCellEditor.getCellEditorValue() txtField");
			return txtField.getText();
		}else if(cmbBox != null){
			logger.debug("Exited from GTCellEditor.getCellEditorValue()-cmbBox");
			return cmbBox.getSelectedItem();
		}else if(chkBox != null){
			logger.debug("Exited from GTCellEditor.getCellEditorValue()-chkBox");
			return chkBox.isSelected();
		}
		logger.debug("Exited from GTCellEditor.getCellEditorValue()");
		return null;
	}

	public boolean stopCellEditing() {
		logger.debug("Entered in GTCellEditor.stopCellEditing() "+this);
		boolean isValid = false;
		String editedValue = String.valueOf(getCellEditorValue());
		logger.debug("In GTCellEditor.stopCellEditing message= Edited Value"+editedValue);
		IAttributeDataModel model = null;
		if(mode == DATAOBJECT){
			model = (IAttributeDataModel)table.getModel();
		}else if(mode == TESTDATA){
			model = (IAttributeDataModel)table.getModel();
		}
		AttributeData data = (AttributeData)model.getAttributeDataAt(row);
		isValid = validateValueType(editedValue, data);
		if(!isValid){
			fireEditingCanceled();
			table.setValueAt(editedValue, row, column);
			table.editCellAt(row, column);
			if(txtField != null){
				txtField.grabFocus();
			}else if(cmbBox != null){
				cmbBox.grabFocus();
			}else if(chkBox != null){
				chkBox.grabFocus();
			}
			return false;
		}
		logger.debug("In TableEditor.stopCellEditing message= getColumnName"+data.getColumn() );
		
		logger.debug("Exited from GTCellEditor.stopCellEditing()");
		return super.stopCellEditing();
	}
	
	public boolean validateValueType(String value, AttributeData data){
		logger.debug("Entered in TableEditor.validateValueType()");
		boolean isValid = false;
		if(Validation.isEmpty(value)){
			return true;
		}
		try{
			switch (data.getType()){
			case DataTypes.SHORT: 
			case DataTypes.INT:
			case DataTypes.LONG:
			case DataTypes.BIGINTEGER:	
				isValid = Validation.validateNumber(value, data.getPrecision(), data.getScale());
				break;
			case DataTypes.FLOAT:
			case DataTypes.DOUBLE:
			case DataTypes.FLOAT_OBJ:
			case DataTypes.DOUBLE_OBJ:
			case DataTypes.BIGDECIMAL:
				isValid = Validation.validateNumber(value, data.getPrecision(), data.getScale());
				break;
			case DataTypes.DATEUTIL:
			case DataTypes.DATESQL:
				isValid = Validation.validateDate(value);
				break;
			case DataTypes.STRING:
				isValid = Validation.validateLength(value, data.getPrecision());
				break;
			case DataTypes.BOOLEAN:
			case DataTypes.BOOLEAN_OBJ:
				System.out.println("Value "+value);
//				if(Validation.validateBoolean(value)){
//					if(data.getColumnType() == 2){
//						data.setValue(Validation.getIntFromBoolean(Boolean.parseBoolean(value)));
//					}
//					isValid = true;
//				}else{
//					isValid = false;
//				}
				isValid = Validation.validateBoolean(value);
				break;
			case DataTypes.TIMESTAMP:
				isValid = Validation.validateTimestamp(value);
				break;
			default :
				return true;
			}
		}catch (IJunitException e) {
			JOptionPane.showMessageDialog(null,e.getUserErrorMsg(),"Error Message",JOptionPane.ERROR_MESSAGE);
			txtField.grabFocus();
		}
			
		logger.debug("Exited from TableEditor.validateValueType() + Return Value "+isValid);
		return isValid;
	}
	
}
