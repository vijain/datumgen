/**
 * 
 */
package com.ijunit.swing.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IApplicationMsgCodes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.data.TestCondition;
import com.ijunit.common.data.TestData;
import com.ijunit.common.database.DBConnection;
import com.ijunit.common.database.TestDataDAO;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.CommonUtility;
import com.ijunit.common.utility.FileWriterFactory;
import com.ijunit.common.utility.Validation;
import com.ijunit.common.utility.writer.FileWriter;
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.parser.ObjectDataParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.component.HistoryTextField;
import com.ijunit.swing.model.AttributeData;
import com.ijunit.swing.model.ConditionModel;
import com.ijunit.swing.model.TestDataTblModel;
import com.ijunit.swing.view.GetValueDialog;
import com.ijunit.swing.view.ModifyTestDataView;
import com.ijunit.swing.view.Start;
/**
 * @author vcjain
 *
 */
public class ModifyTestDataAction implements ActionListener 
{
	static Logger logger = Logger.getLogger(ModifyTestDataAction.class);
	private ModifyTestDataView view = null;
	private TestDataTblModel model = null;
	static String  lastSelectedDir = null;
	String query = null;
	public ModifyTestDataAction(Component view, TestDataTblModel model)
	{
		this.view = (ModifyTestDataView)view;
		this.model = model;
	}

	public void actionPerformed(ActionEvent evt) {
		logger.debug("Entered in ModifyTestDataAction.actionPerformed()");
		if(evt.getActionCommand().equalsIgnoreCase(view.getCancelBtn().getActionCommand())){
			cancelBtnActionPerformed(evt);
		}else  if(evt.getActionCommand().equalsIgnoreCase(view.getExecuteBtn().getActionCommand())){
			executeBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseTestClassBtn().getActionCommand())){
			browseTestClassBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getPreviewBtn().getActionCommand())){
			previewBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getGetValueBtn().getActionCommand())){
			getValueBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getConditionNameCmb().getActionCommand())){
			conditionSelectedActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getTestDataCmb().getActionCommand())){
			testDataCmbActionPerformed(evt);
		}else if(evt.getSource().equals(view.getTestClassTxtFld())){
			testClassTxtFldActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getSelectBtn().getActionCommand())){
			selectBtnActionPerformed(evt);
		}else
		{
			logger.debug("No Action Matches");
		}
		
		logger.debug("Exited from ModifyTestDataAction.actionPerformed()");
	}
	
	private void testClassTxtFldActionPerformed(ActionEvent evt) {
		logger
				.debug("Entered in ModifyTestDataAction.testClassTxtFldActionPerformed()");
		 try {
			 String className = view.getTestClassTxtFld().getText();
			 ((HistoryTextField)view.getTestClassTxtFld()).addToHistory(view.getTestClassTxtFld(), className);
			 view.getSqltFileTxtFld().setText(className.substring(className.lastIndexOf('.')+1)
					 +"."+ResourceManager.SQL_FILE_EXT);
			 populateConditions();
		} catch (Exception e) {
			logger.error("In ModifyTestDataAction.testClassTxtFldActionPerformed "
					+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
		
		logger
				.debug("Exited from ModifyTestDataAction.testClassTxtFldActionPerformed()");
	}
	
	private void conditionSelectedActionPerformed(ActionEvent evt){
		logger
				.debug("Entered in ModifyTestDataAction.conditionSelectedActionPerformed()");
		Connection con = null;
		try{
			con = DBConnection.getConnection();
			String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
			ArrayList list = TestDataDAO.getDataClass(view.getTestClassTxtFld().getText(), conditionName, con);
			view.getTestDataCmb().setModel(new DefaultComboBoxModel(list.toArray()));
			view.getTestDataCmb().setSelectedIndex(0);
			view.getConditionDescTxtArea().setText(
						((ConditionModel)view.getConditionNameCmb().getModel()).getDescription(conditionName));
			logger
					.debug("In ModifyTestDataAction.conditionSelectedActionPerformed message= Selected Value "+conditionName);
			
			logger
					.debug("Exited from ModifyTestDataAction.conditionSelectedActionPerformed()");
			if(model.getRowCount() >1){
				model.removeAll();
			}
		}catch(Exception e){
			logger.error("In ModifyTestDataAction.conditionSelectedActionPerformed "+ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_DATACLASS), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_DATACLASS)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(con);
		}
	}

	private void testDataCmbActionPerformed(java.awt.event.ActionEvent evt) {
		//		Fetching SQL file Name
		Connection con = null;
		String testClass = view.getTestClassTxtFld().getText(); 
		 try {
			 con = DBConnection.getConnection();
			 view.getSqltFileTxtFld().setText(TestDataDAO.getSQLFile(testClass,con));
			 logger
					.debug("Exited from ModifyTestDataAction.browseDataClassBtnActionPerformed()");
//			Clearing Model Data
				if(model.getRowCount() >1){
					model.removeAll();
				}
		} catch (Exception e) {
			logger.error("In ModifyTestDataAction.browseDataClassBtnActionPerformed "
								+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_SQLFILE), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_SQLFILE)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(con);
		}
    }
	
	private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {
	    logger.debug("Entered in ModifyTestDataAction.selectBtnActionPerformed()");
	    try{
		    String testClass = view.getTestClassTxtFld().getText();
			String dataClass = view.getTestDataCmb().getSelectedItem().toString();
			String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
			List selectedAttributeList = null;
			
			//Performing Validation
		    if(testClass != null && testClass.trim().equals("")){
		    	JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTCLASS_BLANK)
						,"Message",JOptionPane.INFORMATION_MESSAGE);
		    	view.getTestClassTxtFld().grabFocus();
		    	return;
		    }else if(dataClass != null && dataClass.trim().equals("")){
		    	JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATACLASS_BLANK)
						,"Message",JOptionPane.INFORMATION_MESSAGE);
		    	view.getTestDataCmb().grabFocus();
		    	return;
		    }else if(conditionName == null || conditionName.trim().equals("")){
		    	JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.CONDITION_BLANK)
						,"Message",JOptionPane.INFORMATION_MESSAGE);
		    	view.getConditionNameCmb().grabFocus();
		    	return;
		    }
		    
		    //Obtaining TestData and ApplicationData.
		    Connection con = null;
		    Connection appCon = null;
		    TestData testData = null;
		    ResultSet rs = null;
			try {
				con = DBConnection.getConnection();
				appCon = DBConnection.getAppConnection();
				testData = TestDataDAO.getKeyValue(testClass, dataClass, conditionName, con);
				rs = TestDataDAO.getAppTestDataResultSet(testData, appCon);
				if(!rs.next()){
					JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.NO_APPDATA_MSG)
							,"Message",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				logger.debug("Exited from ObjectBuilder.getObject()");
			} catch (SQLException e) {
				logger.error("In ObjectBuilder.getObject "+ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.GET_TESTDATA),e);
			}finally{
				DBConnection.releaseConnection(con);
			}
			
			//Clearing Model Data
			if(model.getRowCount() >1){
				model.removeAll();
			}
			
			//Obtaining XML Object Data.
			ObjectDataParser parser  = new ObjectDataParser();
			parser.parse(ResourceManager.getFile(testData.getDataFile()).toString());
			Vector attributeDataList = parser.getAttributeDataList();
			model.setClassName(parser.getDataClassName());
			
			for (Iterator iter = attributeDataList.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				if(!(Validation.isEmpty(element.getColumn()) || Validation.isEmpty(element.getSetMethod()))){
					element.setValue(CommonUtility.fetchAppDataValue(element.getColumn(), element.getType(), element.getColumnType(), rs));
					model.addAttributeData(element);
				}
			}
	  	   view.enableBtn();
		    logger.debug("Exited from ModifyTestDataAction.selectBtnActionPerformed()");
	    }catch (IJunitException e) {
			JOptionPane.showMessageDialog(view, e.getUserErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
    private void browseTestClassBtnActionPerformed(ActionEvent evt) {
    	logger
				.debug("Entered in ModifyTestDataAction.browseTestClassBtnActionPerformed()");

    	JFileChooser chooser = new JFileChooser();
        String testClassFilePath = "";
        if(lastSelectedDir == null){
        	chooser.setCurrentDirectory(new File(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)));
        }else{
        	chooser.setCurrentDirectory(new File(lastSelectedDir));
        }
        	
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		  if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {     		    	
			  testClassFilePath = chooser.getSelectedFile().getAbsolutePath();	
			  lastSelectedDir = testClassFilePath;
			  testClassFilePath = testClassFilePath.substring(testClassFilePath.indexOf(
					  			ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR))
					  			+ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR).length()+2,
					  			testClassFilePath.lastIndexOf('.'));
		    }else{
		    	return;
		    }
		 view.getTestClassTxtFld().setText(testClassFilePath.replace('\\', '.'));
		 
		 //Obtainning condition from TestCondition table
		 try {
			 String className = view.getTestClassTxtFld().getText();
			 ((HistoryTextField)view.getTestClassTxtFld()).addToHistory(view.getTestClassTxtFld(), className);
			 view.getSqltFileTxtFld().setText(className.substring(className.lastIndexOf('.')+1)
					 +"."+ResourceManager.SQL_FILE_EXT);
			 populateConditions();
		} catch (Exception e) {
			logger.error("In ModifyTestDataAction.browseTestClassBtnActionPerformed "
					+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
		logger
				.debug("Exited from ModifyTestDataAction.browseTestClassBtnActionPerformed()");
    }

    private void populateConditions() throws Exception{
    	logger.debug("Entered in ModifyTestDataAction.populateConditions()");
    	Connection con = DBConnection.getConnection();
    	
    	Map map = TestDataDAO.getTestConditions(view.getTestClassTxtFld().getText(),con);
		view.getConditionNameCmb().setModel(new ConditionModel(map));
		if(view.getConditionNameCmb().getModel().getSize() > 0){
			 view.getConditionNameCmb().setSelectedIndex(0);
		 }else{
			 view.getConditionNameCmb().setSelectedIndex(-1);
		 }
		
		DBConnection.releaseConnection(con);
		logger.debug("Exited from ModifyTestDataAction.enclosing_method()");
    }
    
    private void previewBtnActionPerformed(ActionEvent evt) {
    	logger
				.debug("Entered in ModifyTestDataAction.previewBtnActionPerformed()");
    	
    	if(model.getAllAttributeData() == null){
    		JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATA_TABLE_BLANK)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
    		view.getSelectBtn().grabFocus();
    		return;
    	}
    	
    	if(model.getAllAttributeData() != null){
			if(model.getAllAttributeData().size() == 0){
	    		JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATA_TABLE_BLANK)
						,"Message",JOptionPane.INFORMATION_MESSAGE);
	    		view.getSelectBtn().grabFocus();
	    		return;
			}
			if(model.getAllSelectedAttributeData().size() == 0){
				JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SELECT_TESTDATA_FOR_UPDATE)
						,"Message",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
    	}

    	Connection con = null;
    	String dataClass = view.getTestDataCmb().getSelectedItem().toString();
    	try {
    		con = DBConnection.getConnection();
    		TestData testData = TestDataDAO.getKeyValue(view.getTestClassTxtFld().getText(), dataClass, 
    				view.getConditionNameCmb().getSelectedItem().toString(), con);
//			String tableName = TestDataDAO.getDataTable(model.getClassName(), con);
			view.getSqlQueryTxtArea().setText(CommonUtility.getUpdateQuery(model.getAllSelectedAttributeData(),testData));
			logger
					.debug("Exited from ModifyTestDataAction.previewBtnActionPerformed()");
		} catch (Exception e) {
			logger.error("In ModifyTestDataAction.previewBtnActionPerformed "
					+ErrorMessageParser.getMessage(IExceptionCodes.PREVIEW_EXP), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.PREVIEW_EXP)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(con);
		}
		
    }

        
    private void getValueBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	logger
				.debug("Entered in ModifyTestDataAction.getValueBtnActionPerformed()");
    	
    	if(model.getAllSelectedAttributeData().size() == 0){
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SELECT_RECORD)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
		}else if(model.getAllSelectedAttributeData().size() > 1) {
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SINGLE_SELECTION_MSG)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
		}else{
	    	GetValueDialog dialog = new GetValueDialog(view.getParentObject(),true);
	    	dialog.getTestClassCmb().setModel(new DefaultComboBoxModel(new String[]{view.getTestClassTxtFld().getText(),
	    			IFileConstant.GLOBALNAME}));
	    	dialog.getTestClassCmb().setSelectedIndex(0);
	        dialog.setVisible(true);
	        logger
					.debug("In ModifyTestDataAction.getValueBtnActionPerformed message= After GetValue dialog"+dialog.getSelectedValue());
	        AttributeData attributeData = (AttributeData)model.getAllSelectedAttributeData().get(0);
	        int row = model.getIndexOf(attributeData);
	        model.setValueAtRow(dialog.getSelectedValue(),row);
		}
    	logger
				.debug("Exited from ModifyTestDataAction.getValueBtnActionPerformed()");
    }
    
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	logger.debug("Entered in ModifyTestDataAction.saveBtnActionPerformed()");
        try {
        	Map<String, String> map = new HashMap<String,String>();
    		map.put(IFileConstant.QUERYTOINSERT, query);
    		map.put(IFileConstant.CONDITIONNAME, view.getConditionNameCmb().getSelectedItem().toString());
    		
			FileWriter writer = FileWriterFactory.getFileWriter(IFileConstant.SQLFILE_WRITER);
			writer.write(map, ResourceManager.getFile(view.getSqltFileTxtFld().getText()));
			logger.debug("Exited from ModifyTestDataAction.saveBtnActionPerformed()");
			 
		} catch (IJunitException e) {
			logger.error("In ModifyTestDataAction.saveBtnActionPerformed "+e.getMessage(), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.SQLFILE_WRITE_EXP,
					new String[]{view.getSqltFileTxtFld().getText()})	,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
    }

    private void executeBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	logger
				.debug("Entered in ModifyTestDataAction.executeBtnActionPerformed()");

    	logger.debug("In ModifyTestDataAction.executeBtnActionPerformed message= Conditon Selected "
    															+view.getConditionNameCmb().getSelectedItem());
    	String testClass = view.getTestClassTxtFld().getText();
    	String dataClass = view.getTestDataCmb().getSelectedItem().toString();
    	String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
    	List selectedAttributeList = null;
//    	String query = null;
        Connection appCon = null;
        Connection con = null;
    	try{
    		con = DBConnection.getConnection();
    		appCon = DBConnection.getAppConnection();
        	selectedAttributeList = model.getAllSelectedAttributeData();
			CommonUtility.populateRuntimeValue(selectedAttributeList, dataClass, con);
			String tableName = TestDataDAO.getDataTable(model.getClassName(), con);

			try {
				//TestDataDAO.deleteTestData(testClass, dataClass, conditionName, con);
				TestData testData = TestDataDAO.getKeyValue(view.getTestClassTxtFld().getText(), dataClass, 
	    				view.getConditionNameCmb().getSelectedItem().toString(), con);
				query = CommonUtility.getUpdateQuery(selectedAttributeList,testData);
		    	TestDataDAO.execute(query, appCon);
			} catch (Exception e) {
				logger.error("In ModifyTestDataAction.executeBtnActionPerformed "
						+ErrorMessageParser.getMessage(IExceptionCodes.UPDATEQUERY_EXECUTION_EXP), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.UPDATEQUERY_EXECUTION_EXP)
						+"\nDetails are"+e.getMessage(),e);
			}

			Map testDataRecMap = new HashMap();
			int testConditionID = -1;
			
			TestCondition testCondition = TestDataDAO.getTestCondition(testClass, conditionName, con);
			if(testCondition == null){
				testConditionID = TestDataDAO.addTestCondition(testClass, conditionName, view.getConditionDescTxtArea().getText(), con);
			}else{
				testConditionID = testCondition.getID();
			}
			TestDataDAO.addSQLQuery(testConditionID, dataClass, query, con);
			logger
					.debug("In ModifyTestDataAction.executeBtnActionPerformed message=Test ConditionID "+testConditionID);
			Map map = TestDataDAO.getPrimaryColumn(dataClass,con);
			
			logger
					.debug("In ModifyTestDataAction.executeBtnActionPerformed message=Primary key column with DataColumn ID "+map);
			Set keySet = map.keySet();
			for (Iterator iter = keySet.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				for (Iterator iterator = selectedAttributeList.iterator(); iterator	.hasNext();) {
					AttributeData data = (AttributeData) iterator.next();
					if(data.getColumn().equalsIgnoreCase(element)){
						testDataRecMap.put(map.get(element), data.getValue());
						break;
					}
				}
			}
			
			logger
					.debug("In ModifyTestDataAction.executeBtnActionPerformed message= TestData to be inserted "+testDataRecMap);
			
			TestDataDAO.addTestData(testConditionID, testDataRecMap, con);
			
			con.commit();
			appCon.commit();
			saveBtnActionPerformed(evt);
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATA_INSERT_SUCCESS)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
			cancelBtnActionPerformed(evt);
    	}catch(IJunitException ijException){
    		JOptionPane.showMessageDialog(view,ijException.getUserErrorMsg()
					,"Error Message",JOptionPane.ERROR_MESSAGE);
    			DBConnection.rollback(con);
				DBConnection.rollback(appCon);
    	}catch(Exception e){
			logger.error("In ModifyTestDataAction.executeBtnActionPerformed "+"Exception occur ", e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP
													,new String[]{e.getMessage()}),"Error Message",JOptionPane.ERROR_MESSAGE);
			DBConnection.rollback(con);
			DBConnection.rollback(appCon);
		}finally{
			DBConnection.releaseConnection(appCon);
	    	DBConnection.releaseConnection(con);
		}
        logger.debug("Exited from ModifyTestDataAction.enclosing_method()");
    }

    
    
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
        logger.debug("Entered in ModifyTestDataAction.cancelBtnActionPerformed()");
        Start parent = (Start)view.getParentObject();
    	parent.showLogo();
        logger.debug("Exited from enclosing_type.enclosing_method()");
    }

    
    }
