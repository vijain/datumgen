/**
 * 
 */
package com.ijunit.swing.view;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.DataTypes;
import com.ijunit.common.utility.Validation;
import com.ijunit.swing.model.AttributeData;
import com.ijunit.swing.model.IAttributeDataModel;
import com.ijunit.swing.model.ObjectDataTableModel;
import com.ijunit.swing.model.TestDataTblModel;

/**
 * @author vcjain
 *
 */
public class TableRenderer extends DefaultTableCellRenderer{

	static Logger logger = Logger.getLogger(TableRenderer.class);
	private boolean isListAllowed = false;
	
	public static final int DATAOBJECT = 1;
	public static final int TESTDATA = 2;
	
	private int mode;
	
	public TableRenderer(boolean isListAllowed, int mode){
		this.isListAllowed = isListAllowed;
		this.mode = mode;
	}
	
	private JComboBox cmbBox = null;
	private JLabel label = null;
	private JCheckBox chkBox = null;
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) {
		String editValue = String.valueOf(value).trim();
		logger.debug("In TableRenderer.getTableCellEditorComponent message= editValue "+editValue);
		IAttributeDataModel model = null;
		if(mode == DATAOBJECT){
			model = (ObjectDataTableModel)table.getModel();
		}else if(mode == TESTDATA){
			model = (TestDataTblModel)table.getModel();
		}
		AttributeData data = (AttributeData)model.getAttributeDataAt(row);
		
		if(data.getType() == DataTypes.BOOLEAN || data.getType() == DataTypes.BOOLEAN_OBJ){
			chkBox = new JCheckBox();
			chkBox.setForeground(super.getForeground());
			chkBox.setBackground(super.getBackground());
			if(data.getColumnType() == 2){
				chkBox.setSelected(Validation.getBooleanFromInt(editValue));
			}else{
				chkBox.setSelected(Validation.getBooleanFromString(editValue));
			}
			
			return chkBox;
		}else if(editValue.indexOf(',') != -1 && isListAllowed){
			cmbBox = new JComboBox();
			cmbBox.setSelectedIndex(1);
			cmbBox.setEditable(true);
			cmbBox.setForeground(super.getForeground());
			cmbBox.setBackground(super.getBackground());
			return cmbBox;
		}else{
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}
	
}
