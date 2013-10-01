/**
 * 
 */
package com.ijunit.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IApplicationMsgCodes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.data.TestData;
import com.ijunit.common.database.DBConnection;
import com.ijunit.common.database.TestDataDAO;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.CommonUtility;
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.model.ConditionModel;
import com.ijunit.swing.model.SearchDataTblModel;
import com.ijunit.swing.view.GetValueDialog;

/**
 * @author vcjain
 *
 */
public class GetValueDialogAction implements ActionListener
{

	static Logger logger = Logger.getLogger(GetValueDialogAction.class);
	private GetValueDialog view = null;
	private SearchDataTblModel model = null;
	private String selectedValue = null;
	
	/**
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public GetValueDialogAction(GetValueDialog view){
		this.view = view;
	}

	public void actionPerformed(ActionEvent evt) {
		logger.debug("Entered in GetValueDialogAction.actionPerformed()");
		
		if(evt.getSource().equals(view.getConditionNameCmb())){
			conditionSelectedActionPerformed(evt);
		}else if(evt.getSource().equals(view.getTestDataCmb())){
			testDataCmbActionPerformed(evt);
		}else if(evt.getSource().equals(view.getTestClassCmb())){
			testClassCmbActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getGetValueBtn().getActionCommand())){
			getValueBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getCancelBtn().getActionCommand())){
			cancelBtnActionPerformed(evt);
		}else
		{
			logger.debug("No Action Matches");
		}
		logger.debug("Exited from GetValueDialogAction.actionPerformed()");
	}
	
	private void getValueBtnActionPerformed(java.awt.event.ActionEvent evt) {
		logger
				.debug("Entered in GetValueDialogAction.getValueBtnActionPerformed()");
		if(model.getSeletedIndex().size() == 0){
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SELECT_RECORD)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
		}else if(model.getSeletedIndex().size() > 1){
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SINGLE_SELECTION_MSG)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
		}else{
			int selectedIndex = (Integer)model.getSeletedIndex().get(0);
			selectedValue = (String)model.getValueAt(selectedIndex,2);
			logger
					.debug("In GetValueDialogAction.conditionSelectedActionPerformed message= Selected Value "+selectedValue);
		}
		view.setVisible(false);
		view.dispose();
		
		logger
				.debug("Exited from GetValueDialogAction.getValueBtnActionPerformed()");
	}

	private void testClassCmbActionPerformed(java.awt.event.ActionEvent evt) {
       logger
			.debug("Entered in GetValueDialogAction.testClassCmbActionPerformed()");
       try {
			 Map map = CommonUtility.populateConditions(view.getTestClassCmb().getSelectedItem().toString());
			 view.getConditionNameCmb().setModel(new ConditionModel(map));
			 if(map.size() > 0){
				 view.getConditionNameCmb().setSelectedIndex(0);
			 }
		} catch (IJunitException e) {
			JOptionPane.showMessageDialog(view,e.getUserErrorMsg()
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
       
       logger
			.debug("Exited from GetValueDialogAction.testClassCmbActionPerformed()");
    }
	
//    private void testClassTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
//       logger
//			.debug("Entered in GetValueDialogAction.testClassTxtFldActionPerformed()");
//       try {
//			 Map map = CommonUtility.populateConditions(view.getTestClassTxtFld().getText());
//			 view.getConditionNameCmb().setModel(new ConditionModel(map));
//			 if(map.size() > 1){
//				 view.getConditionNameCmb().setSelectedIndex(0);
//			 }
//		} catch (IJunitException e) {
//			JOptionPane.showMessageDialog(view,e.getUserErrorMsg()
//					,"Error Message",JOptionPane.ERROR_MESSAGE);
//		}
//       logger
//			.debug("Exited from GetValueDialogAction.browseTestClassBtnActionPerformed()");
//    }

//    private void browseTestClassBtnActionPerformed(java.awt.event.ActionEvent evt) {
//        logger
//				.debug("Entered in GetValueDialogAction.browseTestClassBtnActionPerformed()");
//        JFileChooser chooser = new JFileChooser();
//        String testClassFilePath = "";
//        chooser.setCurrentDirectory(new File(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)));
//		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		  if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {     		    	
//			  testClassFilePath = chooser.getSelectedFile().getAbsolutePath();	    	
//			  testClassFilePath = testClassFilePath.substring(testClassFilePath.indexOf(
//					  			ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR))
//					  			+ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR).length()+1,
//					  			testClassFilePath.lastIndexOf('.'));
//		    }
//		 view.getTestClassTxtFld().setText(testClassFilePath.replace('\\', '.'));
//		 
//		 //Obtainning condition from TestCondition table
//		 try {
//			 Map map = CommonUtility.populateConditions(view.getTestClassTxtFld().getText());
//			 view.getConditionNameCmb().setModel(new ConditionModel(map));
//			 view.getConditionNameCmb().setSelectedIndex(0);
//		} catch (Exception e) {
//			logger.error("In AddTestDataAction.browseTestClassBtnActionPerformed "
//					+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
//			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES)
//					,"Error Message",JOptionPane.ERROR_MESSAGE);
//		}
//        
//        logger
//				.debug("Exited from GetValueDialogAction.browseDataClassBtnActionPerformed()");
//    }

    private void conditionSelectedActionPerformed(ActionEvent evt) {
		logger
				.debug("Entered in AddTestDataAction.conditionSelectedActionPerformed()");
		String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
		String testClass = view.getTestClassCmb().getSelectedItem().toString();
		logger
				.debug("In GetValueDialogAction.conditionSelectedActionPerformed message= Condition Name selected "+conditionName);
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			model = (SearchDataTblModel)view.getDataTable().getModel();
			ArrayList list = TestDataDAO.getDataClass(testClass, conditionName, con);
			if(list.size() == 0){
				view.getTestDataCmb().setModel(new DefaultComboBoxModel());
				model.removeAll();
				JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.NO_MATCHING_DATACLASS)
						,"Message",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			view.getTestDataCmb().setModel(new DefaultComboBoxModel(list.toArray()));
			model.removeAll();
			view.getTestDataCmb().setSelectedIndex(0);
//			TestData testData = TestDataDAO.getKeyValue(testClass, view.getTestDataCmb().getSelectedItem().toString(), 
//								conditionName, con);
//			
//			logger.debug("In GetValueDialogAction.conditionSelectedActionPerformed message= KeyColumn Vector " +
//							testData.getKeyColumns());
//			model.setDataVector(testData.getKeyColumns());
			
		} catch (Exception e) {
			logger.error("In GetValueDialogAction.testDataCmbActionPerformed "+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		} 
		logger
				.debug("Exited from AddTestDataAction.conditionSelectedActionPerformed()");
	}
	
    private void testDataCmbActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	logger
				.debug("Entered in GetValueDialogAction.testDataCmbActionPerformed()");
    	String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
		String testClass = view.getTestClassCmb().getSelectedItem().toString();
		String testDataClass = view.getTestDataCmb().getSelectedItem().toString();
		Connection con = null;
		try{
			con = DBConnection.getConnection();
			TestData testData = TestDataDAO.getKeyValue(testClass, testDataClass, 
								conditionName, con);
			
			logger.debug("In GetValueDialogAction.conditionSelectedActionPerformed message= KeyColumn Vector " +
							testData.getKeyColumns());
			model.setDataVector(testData.getKeyColumns());
		} catch (Exception e) {
			logger.error("In GetValueDialogAction.testDataCmbActionPerformed "+ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_DATACLASS), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_DATACLASS)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		} finally{
			DBConnection.releaseConnection(con);
		}
    	logger
				.debug("Exited from GetValueDialogAction.testDataCmbActionPerformed()");
    }
    
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	logger
				.debug("Entered in GetValueDialogAction.cancelBtnActionPerformed()");
    	view.setVisible(false);
		view.dispose();
    	
    	logger
				.debug("Exited from GetValueDialogAction.cancelBtnActionPerformed()");
    }
    
//    public class KeyColumnModel extends AbstractTableModel {
//    	
//    	Vector vec = null;
//    	Map selectedIndexMap = new HashMap();
//    	String [] columnHeader = {" ","KeyColumn","Value"};
//    	
//    	public KeyColumnModel(){
//    		vec = new Vector();
//    	}
//    	
//    	public KeyColumnModel(Vector vec){
//    		this.vec = vec;
//    	}
//    	
//    	public int getColumnCount()
//    	{
//    		return columnHeader.length;
//    	}
//
//    	public int getRowCount() 
//    	{
//    		return vec.size();
//    	}
//
//    	@Override
//    	public String getColumnName(int column) {
//
//    		return columnHeader[column];
//    	}
//
//    	@Override
//    	public Class getColumnClass(int column) {
//    		if(column == 0 ){
//    			return Boolean.class;
//    		}else{
//    			return String.class;
//    		}
//    	}
//    	
//    	public Object getValueAt(int row, int column) 
//    	{
//			KeyColumn data = (KeyColumn)vec.get(row);
//			if(column == 0){
//				return selectedIndexMap.get(data.getColumn());
//			}else{
//	    		switch(column)
//	    		{
//	    			case 1: return data.getColumn() ;
//	    			case 2: return data.getValue();
//	    			default : return null;
//	    		}
//	    	}
//    	}
//    	
//    	public void setValueAt(Object obj, int row, int column) 
//    	{
//    		logger.debug("Entered in KeyColumnModel.setValueAt()");
//    		KeyColumn data = (KeyColumn)vec.get(row);
//    		if(column == 0){
//    			selectedIndexMap.put(data.getColumn(),((Boolean)obj).booleanValue());
//    		}else{
//	    		switch(column)
//	    		{
//	    			case 1: data.setColumn((String)obj); break;
//	    			case 2: data.setValue((String)obj); break;
//	    			default: break;
//	    		}
//    		}
//    		fireTableChanged(new TableModelEvent(this,TableModelEvent.UPDATE));
//    		logger.debug("Exited from KeyColumnModel.setValueAt()");
//    	}
//    	
//    	@Override
//    	public boolean isCellEditable(int row, int column) 
//    	{
//    		return column == 0;
//    	}
//    	
//    	public Vector getSeletedIndex(){
//    		Vector selectedIndexes = new Vector();
//    		for (int i = 0; i < vec.size(); i++) {
//    			Boolean value = (Boolean)getValueAt(i, 0);
//				if(value != null && value){
//					selectedIndexes.add(i);
//				}
//			}
//    		return selectedIndexes;
//    	}
//    	
//    	public void setDataVector(Vector vec){
//    		this.vec = vec;
//    		fireTableChanged(new TableModelEvent(this));
//    	}
//    }//end of inner class
}
