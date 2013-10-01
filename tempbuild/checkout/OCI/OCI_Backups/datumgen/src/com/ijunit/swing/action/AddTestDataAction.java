/**
 * 
 */
package com.ijunit.swing.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IApplicationMsgCodes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.data.TestCondition;
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
import com.ijunit.swing.view.AddTestDataView;
import com.ijunit.swing.view.GetValueDialog;
import com.ijunit.swing.view.Start;
/**
 * @author vcjain
 *
 */
public class AddTestDataAction implements ActionListener 
{
	static Logger logger = Logger.getLogger(AddTestDataAction.class);
	private AddTestDataView view = null;
	private TestDataTblModel model = null;
	boolean isSQLSaved = false;
	static String  lastSelectedDir = null;
	String query = null;

	public AddTestDataAction(Component view, TestDataTblModel model)
	{
		this.view = (AddTestDataView)view;
		this.model = model;
	}

	public void actionPerformed(ActionEvent evt) {
		logger.debug("Entered in AddTestDataAction.actionPerformed()");
		if(evt.getActionCommand().equalsIgnoreCase(view.getSaveBtn().getActionCommand())){
			saveBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getCancelBtn().getActionCommand())){
			cancelBtnActionPerformed(evt);
		}else  if(evt.getActionCommand().equalsIgnoreCase(view.getExecuteBtn().getActionCommand())){
			executeBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseBtn().getActionCommand())){
			browseBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseDataClassBtn().getActionCommand())){
			browseDataClassBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseTestClassBtn().getActionCommand())){
			browseTestClassBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getPreviewBtn().getActionCommand())){
			previewBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getGetValueBtn().getActionCommand())){
			getValueBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getConditionNameCmb().getActionCommand())){
			conditionSelectedActionPerformed(evt);
		}else if(evt.getSource().equals(view.getTestClassTxtFld())){
			testClassTxtFldActionPerformed(evt);
		}else
		{
			logger.debug("No Action Matches");
		}
		
		logger.debug("Exited from AddTestDataAction.actionPerformed()");
	}
	
	private void testClassTxtFldActionPerformed(ActionEvent evt) {
		logger
				.debug("Entered in AddTestDataAction.testClassTxtFldActionPerformed()");
		Connection con = null;
		 try {
			 String className = view.getTestClassTxtFld().getText();
			 addToHistory(view.getTestClassTxtFld(), className);
			 con = DBConnection.getConnection();
			 view.getSqltFileTxtFld().setText(TestDataDAO.getSQLFile(className, con));
			 populateConditions();
		} catch (Exception e) {
			logger.error("In AddTestDataAction.testClassTxtFldActionPerformed "
					+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(con);
		}
		logger
				.debug("Exited from AddTestDataAction.testClassTxtFldActionPerformed()");
	}
	
	private void conditionSelectedActionPerformed(ActionEvent evt){
		logger
				.debug("Entered in AddTestDataAction.conditionSelectedActionPerformed()");
		
		String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
		view.getConditionDescTxtArea().setText(
					((ConditionModel)view.getConditionNameCmb().getModel()).getDescription(conditionName));
		logger
				.debug("In AddTestDataAction.conditionSelectedActionPerformed message= Selected Value "+conditionName);
		
		logger
				.debug("Exited from AddTestDataAction.conditionSelectedActionPerformed()");
	}
	
	private void browseDataClassBtnActionPerformed(ActionEvent evt) {
		logger
				.debug("Entered in AddTestDataAction.browseDataClassBtnActionPerformed()");
		Connection con = null;
		JFileChooser chooser = new JFileChooser();
        String dataClassFilePath = "";
        chooser.setCurrentDirectory(new File(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		  if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {     		    	
			  dataClassFilePath = chooser.getSelectedFile().getAbsolutePath();	    	
			  dataClassFilePath = dataClassFilePath.substring(dataClassFilePath.indexOf(
					  			ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR))
					  			+ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR).length()+2,
					  			dataClassFilePath.lastIndexOf('.'));
		    }
		 view.getTestDataTxtFld().setText(dataClassFilePath.replace('\\', '.'));
	}

    private void browseTestClassBtnActionPerformed(ActionEvent evt) {
    	logger
				.debug("Entered in AddTestDataAction.browseTestClassBtnActionPerformed()");

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
		Connection con = null;
		 //Obtainning condition from TestCondition table
		 try {
			 String className = view.getTestClassTxtFld().getText();
			 addToHistory(view.getTestClassTxtFld(), className);
			 con = DBConnection.getConnection();
			 view.getSqltFileTxtFld().setText(TestDataDAO.getSQLFile(className, con));
			 populateConditions();
		} catch (Exception e) {
			logger.error("In AddTestDataAction.browseTestClassBtnActionPerformed "
					+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(con);
		}
		logger
				.debug("Exited from AddTestDataAction.browseTestClassBtnActionPerformed()");
    }

    private void populateConditions() throws Exception{
    	logger.debug("Entered in AddTestDataAction.populateConditions()");
    	Connection con = DBConnection.getConnection();
    	
    	Map map = TestDataDAO.getTestConditions(view.getTestClassTxtFld().getText(),con);
		view.getConditionNameCmb().setModel(new ConditionModel(map));
		view.getConditionNameCmb().setSelectedIndex(0);
		
		DBConnection.releaseConnection(con);
		logger.debug("Exited from AddTestDataAction.enclosing_method()");
    }
    
    private void browseBtnActionPerformed(ActionEvent evt) {
    	try{
	        JFileChooser chooser = new JFileChooser();
	        String objectDataFilePath = "";
	        chooser.setCurrentDirectory(ResourceManager.getFile(IFileConstant.OBJECTXMLDATA_MAP_FILE).getParentFile());
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			  if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {     		    	
				  objectDataFilePath = chooser.getSelectedFile().toString();	    	
			 }else{
				 return;
			 }
			 view.getDataFileTxtFld().setText(objectDataFilePath);
			  
			ObjectDataParser parser  = new ObjectDataParser();
			parser.parse(objectDataFilePath);
			Vector attributeDataList = parser.getAttributeDataList();
			model.setClassName(parser.getDataClassName());
			for (Iterator iter = attributeDataList.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				if(!(Validation.isEmpty(element.getColumn()) || Validation.isEmpty(element.getSetMethod()))){
					model.addAttributeData(element);
				}
			}
	  	   view.enableBtn();
	  	   view.getTestDataTxtFld().setText(parser.getDataClassName());
    	}catch (IJunitException e) {
			JOptionPane.showMessageDialog(view,e.getUserErrorMsg() ,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
    }

    private void previewBtnActionPerformed(ActionEvent evt) {
    	if(model.getAllAttributeData() == null){
    		JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATA_TABLE_BLANK)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
    		view.getBrowseBtn().grabFocus();
    	}else if(model.getAllAttributeData() != null){
			if(model.getAllAttributeData().size() == 0){
	    		JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATA_TABLE_BLANK)
						,"Message",JOptionPane.INFORMATION_MESSAGE);
	    		view.getBrowseBtn().grabFocus();
			}else{
				if(model.getAllSelectedAttributeData().size() == 0){
					JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SELECT_TESTDATA_FOR_INSER)
							,"Message",JOptionPane.INFORMATION_MESSAGE);
				}else{
					Connection con = null;
		        	try {
		        		 con = DBConnection.getConnection();
		    			String tableName = TestDataDAO.getDataTable(model.getClassName(), con);
						view.getSqlQueryTxtArea().setText(CommonUtility.getInsertQuery(model.getAllSelectedAttributeData(),tableName));
					} catch (Exception e) {
						logger.error("In AddTestDataAction.previewBtnActionPerformed "
								+ErrorMessageParser.getMessage(IExceptionCodes.PREVIEW_EXP), e);
						JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.PREVIEW_EXP)
								,"Error Message",JOptionPane.ERROR_MESSAGE);
					}finally{
						DBConnection.releaseConnection(con);
					}
				}
			}
    	} 
    }

        
    private void getValueBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	logger
				.debug("Entered in AddTestDataAction.getValueBtnActionPerformed()");
    	
    	if(model.getAllSelectedAttributeData().size() == 0){
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SELECT_RECORD)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
			return;
		}else if(model.getAllSelectedAttributeData().size() > 1) {
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SINGLE_SELECTION_MSG)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
			return;
		}else if(Validation.isEmpty(view.getTestClassTxtFld().getText())){
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTCLASS_BLANK)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
			view.getTestClassTxtFld().grabFocus();
			return;
		}
    	GetValueDialog dialog = new GetValueDialog(view.getParentObject(),true);
    	//dialog.getTestClassTxtFld().setText(view.getTestClassTxtFld().getText());
    	dialog.getTestClassCmb().setModel(new DefaultComboBoxModel(new String[]{view.getTestClassTxtFld().getText(),
    			IFileConstant.GLOBALNAME}));
    	dialog.getTestClassCmb().setSelectedIndex(0);
        dialog.setVisible(true);
        logger
				.debug("In AddTestDataAction.getValueBtnActionPerformed message= After GetValue dialog"+dialog.getSelectedValue());
        AttributeData attributeData = (AttributeData)model.getAllSelectedAttributeData().get(0);
        int row = model.getIndexOf(attributeData);
        model.setValueAtRow(dialog.getSelectedValue(),row);
		logger
				.debug("Exited from AddTestDataAction.getValueBtnActionPerformed()");
    }
    
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
        logger.debug("Entered in AddTestDataAction.saveBtnActionPerformed()");
        try {
        	Map<String, String> map = new HashMap<String,String>();
    		map.put(IFileConstant.QUERYTOINSERT, query);
    		map.put(IFileConstant.CONDITIONNAME, view.getConditionNameCmb().getSelectedItem().toString());
    		
			FileWriter writer = FileWriterFactory.getFileWriter(IFileConstant.SQLFILE_WRITER);
			writer.write(map, ResourceManager.getFile(view.getSqltFileTxtFld().getText()));
			isSQLSaved = true;
			logger.debug("Exited from AddTestDataAction.saveBtnActionPerformed()");
			
		} catch (IJunitException e) {
			logger.error("In AddTestDataAction.saveBtnActionPerformed "+e.getMessage(), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.SQLFILE_WRITE_EXP,
					new String[]{view.getSqltFileTxtFld().getText()})	,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
    }

    private void executeBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	logger
				.debug("Entered in AddTestDataAction.executeBtnActionPerformed()");
    	//Performing Validation
    	logger.debug("In AddTestDataAction.executeBtnActionPerformed message= Conditon Selected "
    															+view.getConditionNameCmb().getSelectedItem());
    	String testClass = view.getTestClassTxtFld().getText();
    	String dataClass = view.getTestDataTxtFld().getText();
    	String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
    	String sqlFileName = view.getSqltFileTxtFld().getText();
    	List selectedAttributeList = null;
        if(testClass != null && testClass.trim().equals("")){
        	JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTCLASS_BLANK)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
        	view.getTestClassTxtFld().grabFocus();
        	return;
        }else if(dataClass != null && dataClass.trim().equals("")){
        	JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATACLASS_BLANK)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
        	view.getTestDataTxtFld().grabFocus();
        	return;
        }else if(conditionName == null || conditionName.trim().equals("")){
        	JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.CONDITION_BLANK)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
        	view.getConditionNameCmb().grabFocus();
        	return;
        }
//        else if(sqlFileName == null || sqlFileName.trim().equals("")){
//        	JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.SQLFILENAME_BLANK_MSG)
//					,"Message",JOptionPane.INFORMATION_MESSAGE);
//        	view.getSqltFileTxtFld().grabFocus();
//        	return;
//        }
        
    	//String query = null;
        Connection appCon = null;
        Connection con = null;
    	try{
    		con = DBConnection.getConnection();
    		appCon = DBConnection.getAppConnection();
        	selectedAttributeList = model.getAllSelectedAttributeData();
        	String tableName = TestDataDAO.getDataTable(model.getClassName(), con);
        	String preQuery = CommonUtility.getInsertQuery(selectedAttributeList,tableName);
			CommonUtility.populateRuntimeValue(selectedAttributeList, view.getTestDataTxtFld().getText(), con);
			query = CommonUtility.getInsertQuery(selectedAttributeList,tableName);
	    	
			try {
				
		    	TestDataDAO.execute(query, appCon);
		    	
			} catch (Exception e) {
				logger.error("In AddTestDataAction.executeBtnActionPerformed "
						+ErrorMessageParser.getMessage(IExceptionCodes.INSERTQUERY_EXECUTION_EXP), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.INSERTQUERY_EXECUTION_EXP)
						+"\nDetails are"+e.getMessage(),e);
			}

			Map testDataRecMap = new HashMap();
			int testConditionID = -1;
			
			TestCondition testCondition = TestDataDAO.getTestCondition(testClass, conditionName, con);
			if(testCondition == null){
				testConditionID = TestDataDAO.addTestCondition(testClass, conditionName, view.getConditionDescTxtArea().getText(), con);
				String fileName = TestDataDAO.getSQLFile(testClass, con);
				if(Validation.isEmpty(fileName)){
					TestDataDAO.addSQLFILE(testClass, view.getSqltFileTxtFld().getText(), con);
				}
			}else{
				testConditionID = testCondition.getID();
			}
			
			TestDataDAO.addSQLQuery(testConditionID, dataClass, query, con);
			logger
					.debug("In AddTestDataAction.executeBtnActionPerformed message=Test ConditionID "+testConditionID);
			Map map = TestDataDAO.getPrimaryColumn(dataClass,con);
			
			logger
					.debug("In AddTestDataAction.executeBtnActionPerformed message=Primary key column with DataColumn ID "+map);
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
					.debug("In AddTestDataAction.executeBtnActionPerformed message= TestData to be inserted "+testDataRecMap);
			
			TestDataDAO.addTestData(testConditionID, testDataRecMap, con);
			
			con.commit();
			appCon.commit();
			//saveBtnActionPerformed(evt);
			JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.TESTDATA_INSERT_SUCCESS)
					,"Message",JOptionPane.INFORMATION_MESSAGE);
			cancelBtnActionPerformed(evt);
    	}catch(IJunitException ijException){
    		JOptionPane.showMessageDialog(view,ijException.getUserErrorMsg()
					,"Error Message",JOptionPane.ERROR_MESSAGE);
    			DBConnection.rollback(con);
				DBConnection.rollback(appCon);
    	}catch(Exception e){
			logger.error("In AddTestDataAction.executeBtnActionPerformed "+"Exception occur ", e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP
													,new String[]{e.getMessage()}),"Error Message",JOptionPane.ERROR_MESSAGE);
			DBConnection.rollback(con);
			DBConnection.rollback(appCon);
		}finally{
			DBConnection.releaseConnection(appCon);
	    	DBConnection.releaseConnection(con);
		}
      
        logger.debug("Exited from AddTestDataAction.enclosing_method()");
    }

    
    
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
        logger.debug("Entered in AddTestDataAction.cancelBtnActionPerformed()");
        Start parent = (Start)view.getParentObject();
    	parent.showLogo();
        logger.debug("Exited from enclosing_type.enclosing_method()");
    }
    
    public void addToHistory(JTextField txtField, String value){
    	logger.debug("In HistoryTextField adding value to history: "+value);
    	((HistoryTextField)txtField).addToHistory(txtField,value);
    }
    }
