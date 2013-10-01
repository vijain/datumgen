/**
 * 
 */
package com.ijunit.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.data.TestData;
import com.ijunit.common.database.DBConnection;
import com.ijunit.common.database.TestDataDAO;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.CommonUtility;
import com.ijunit.common.utility.Validation;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.model.ConditionModel;
import com.ijunit.swing.model.SearchDataTblModel;
import com.ijunit.swing.view.SearchDataView;
import com.ijunit.swing.view.Start;

/**
 * @author vcjain
 *
 */
public class SearchDataAction implements ActionListener, FocusListener
{
	static Logger logger = Logger.getLogger(SearchDataAction.class);
	
	private SearchDataView view = null;
	private SearchDataTblModel model = null;
	private int rowsCount = 5;
	private int startIndex = 0;
	private int endIndex = rowsCount;
	private int maxRecord = -1;
	
	public SearchDataAction(SearchDataView view){
		this.view = view;
	}
	
	
	
	public void actionPerformed(ActionEvent evt) {
		logger.debug("Entered in SearchDataAction.actionPerformed()");
		if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseDataClassBtn().getActionCommand())){
			browseDataClassBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseTestClassBtn().getActionCommand())){
			browseTestClassBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getConditionNameCmb().getActionCommand())){
			conditionSelectedActionPerformed(evt);
		}else if(evt.getSource().equals(view.getTestClassTxtFld())){
			testClassTxtFldActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getExecuteBtn().getActionCommand())){
			executeBtnActionPerformed(startIndex,endIndex);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getCancelBtn().getActionCommand())){
			cancelBtnActionPerformed(evt);
		}else if(evt.getSource().equals(view.getPreviousBtn())){
			previousBtnActionPerformed(evt);
		}else if(evt.getSource().equals(view.getNextBtn())){
			nextBtnActionPerformed(evt);
		}else
		{
			logger.debug("No Action Matches");
		}

		logger.debug("Exited from SearchDataAction.actionPerformed()");
	}

	public void focusGained(FocusEvent e) {
		logger.debug("Entered in SearchDataAction.focusGained()");
		
		logger.debug("Exited from SearchDataAction.focusGained()");
	}

	public void focusLost(FocusEvent e) {
		logger.debug("Entered in SearchDataAction.focusLost()");
		if(e.getSource().equals(view.getRowCountTextFld())){
			rowCountTextFldFocusLost(e);
		}
		logger.debug("Exited from SearchDataAction.testClassTxtFldActionPerformed()");
	}
	
	private void testClassTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
	       logger
				.debug("Entered in GetValueDialogAction.testClassTxtFldActionPerformed()");
	       try {
				 Map map = CommonUtility.populateConditions(view.getTestClassTxtFld().getText());
				 view.getConditionNameCmb().setModel(new ConditionModel(map));
				 view.getConditionNameCmb().setSelectedIndex(0);
			} catch (IJunitException e) {
				JOptionPane.showMessageDialog(view,e.getUserErrorMsg()
						,"Error Message",JOptionPane.ERROR_MESSAGE);
			}
	       logger
				.debug("Exited from GetValueDialogAction.browseTestClassBtnActionPerformed()");
	    }

	    private void browseTestClassBtnActionPerformed(java.awt.event.ActionEvent evt) {
	        logger
					.debug("Entered in GetValueDialogAction.browseTestClassBtnActionPerformed()");
	        JFileChooser chooser = new JFileChooser();
	        String testClassFilePath = "";
	        chooser.setCurrentDirectory(new File(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)));
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			  if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {     		    	
				  testClassFilePath = chooser.getSelectedFile().getAbsolutePath();	    	
				  testClassFilePath = testClassFilePath.substring(testClassFilePath.indexOf(
						  			ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR))
						  			+ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR).length()+1,
						  			testClassFilePath.lastIndexOf('.'));
			    }
			 view.getTestClassTxtFld().setText(testClassFilePath.replace('\\', '.'));
			 
			 //Obtainning condition from TestCondition table
			 try {
				 Map map = CommonUtility.populateConditions(view.getTestClassTxtFld().getText());
				 view.getConditionNameCmb().setModel(new ConditionModel(map));
				 view.getConditionNameCmb().setSelectedIndex(0);
			} catch (Exception e) {
				logger.error("In AddTestDataAction.browseTestClassBtnActionPerformed "
						+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES), e);
				JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_CONDITIONNAMES)
						,"Error Message",JOptionPane.ERROR_MESSAGE);
			}
	        
	        logger
					.debug("Exited from GetValueDialogAction.browseDataClassBtnActionPerformed()");
	    }

	    private void browseDataClassBtnActionPerformed(java.awt.event.ActionEvent evt) {
	        logger
					.debug("Entered in GetValueDialogAction.browseDataClassBtnActionPerformed()");
	        JFileChooser chooser = new JFileChooser();
	        String dataClassFilePath = "";
	        chooser.setCurrentDirectory(new File(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)));
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			  if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {     		    	
				  dataClassFilePath = chooser.getSelectedFile().getAbsolutePath();	    	
				  dataClassFilePath = dataClassFilePath.substring(dataClassFilePath.indexOf(
						  			ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR))
						  			+ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR).length()+1,
						  			dataClassFilePath.lastIndexOf('.'));
			    }
			 view.getTestDataTxtFld().setText(dataClassFilePath.replace('\\', '.'));
	        
	        logger
					.debug("Exited from GetValueDialogAction.conditionSelectedActionPerformed()");
	    }

	    private void conditionSelectedActionPerformed(ActionEvent evt) {
			logger
					.debug("Entered in AddTestDataAction.conditionSelectedActionPerformed()");
			String conditionName = (String)view.getConditionNameCmb().getSelectedItem();
			logger
					.debug("In GetValueDialogAction.conditionSelectedActionPerformed message= Condition Name selected "+conditionName);
			Connection con = null;
			try {
				con = DBConnection.getConnection();
				model = (SearchDataTblModel)view.getDataTable().getModel();
				TestData testData = TestDataDAO.getKeyValue(view.getTestClassTxtFld().getText(), view.getTestDataTxtFld().getText(), 
									conditionName, con);
				
				logger.debug("In GetValueDialogAction.conditionSelectedActionPerformed message= KeyColumn Vector " +
								testData.getKeyColumns());
				model.setDataVector(testData.getKeyColumns());
				
			} catch (IJunitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger
					.debug("Exited from AddTestDataAction.conditionSelectedActionPerformed()");
		}
		
	    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
	    	logger.debug("Entered in GetValueDialogAction.cancelBtnActionPerformed()");
	    	Start parent = (Start)view.getParentObject();
	    	parent.showLogo();
	    	logger.debug("Exited from GetValueDialogAction.cancelBtnActionPerformed()");
	    }

	    private void executeBtnActionPerformed(int startIndex,int endIndex) {
	        logger.debug("Entered in SearchDataAction.executeBtnActionPerformed() startIndex-endIndex "+startIndex+"-"+endIndex);
	        Connection appCon = null;
			try {
				view.getAppDataTable().setModel(new DefaultTableModel());
				
				appCon = DBConnection.getAppConnection();
				
				StringBuffer query = new StringBuffer("SELECT Count(*) FROM ( ");
				query.append(view.getQueryTextArea().getText());
				query.append(" ) ");
				ResultSet rs = TestDataDAO.getAppTestDataResultSet(query.toString(), null, appCon);
				rs.next();
				maxRecord = rs.getInt(1);
				logger
						.debug("In SearchDataAction.executeBtnActionPerformed message= Query Record Count "+maxRecord);
				
				query = new StringBuffer("SELECT * FROM (SELECT a.*, ROWNUM rnum FROM ( ");
				query.append(view.getQueryTextArea().getText());
				query.append(" ) a WHERE ROWNUM <= ?) WHERE rnum >= ?");
				ArrayList<Integer>bindVariable = new ArrayList<Integer>();
				bindVariable.add(endIndex);
				bindVariable.add(startIndex);
				
				
				rs = TestDataDAO.getAppTestDataResultSet(query.toString(), bindVariable, appCon);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				Vector<String> columnVec = new Vector<String>();
				Vector<Object> innerVector = null;
				Vector<Object> outerVector = new Vector<Object>();
				for(int i=0;i<columnCount;i++){
					//columnHeader[i] = metaData.getColumnLabel(i+1);
					columnVec.add(metaData.getColumnLabel(i+1));
				}
				
				logger
						.debug("In SearchDataAction.executeBtnActionPerformed message= Column Vector "+columnVec);
				while(rs.next()){
					innerVector = new Vector<Object>();
					for(int i=0;i<columnCount;i++){
						innerVector.add(rs.getObject(columnVec.get(i)));
					}
					outerVector.add(innerVector);
				}
				view.getAppDataTable().setModel(new DefaultTableModel(outerVector,columnVec));
			} catch (IJunitException e) {
				JOptionPane.showMessageDialog(view,e.getUserErrorMsg() ,"Error Message",JOptionPane.ERROR_MESSAGE);
			}catch(Exception e){
				logger.error("In SearchDataAction.executeBtnActionPerformed ", e);
				JOptionPane.showMessageDialog(view,e.getMessage() ,"Error Message",JOptionPane.ERROR_MESSAGE);
			}finally{
				DBConnection.releaseConnection(appCon);
			}
	        
	        
	        logger.debug("Exited from SearchDataAction.executeBtnActionPerformed()");
	    }
	    
	    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {
		    logger.debug("Entered in SearchDataAction.nextBtnActionPerformed()");
		    
		    if(endIndex+1 <= maxRecord){
		    	startIndex = endIndex+1;
			    endIndex = endIndex+rowsCount;
			    logger
				.debug("In SearchDataAction.nextBtnActionPerformed message= StartIndex & endIndex "+startIndex+"-"+endIndex);
		    	executeBtnActionPerformed(startIndex, endIndex);
		    }
		    logger.debug("Exited from SearchDataAction.previousBtnActionPerformed()");
	    }

	    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {
		    logger.debug("Entered in SearchDataAction.previousBtnActionPerformed()");
		    
		    if((endIndex-rowsCount) > 0){
			    endIndex = startIndex-1;
			    startIndex = startIndex-rowsCount>0?startIndex-rowsCount:0;
			    logger
						.debug("In SearchDataAction.previousBtnActionPerformed message= StartIndex & endIndex "+startIndex+"-"+endIndex);
		    	executeBtnActionPerformed(startIndex,endIndex);
		    }
		    		    
		    logger.debug("Exited from SearchDataAction.previousBtnActionPerformed()");
	    }

//	    private void rowCountTextFldActionPerformed(java.awt.event.ActionEvent evt) {
//	        logger
//					.debug("Entered in SearchDataAction.rowCountTextFldActionPerformed()");
//	        String value = view.getRowCountTextFld().getText();
//	        if(!Validation.validateNumeric(value)){
//	        	JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.NOT_A_NUMBER_MSG)
//						,"Error Message",JOptionPane.ERROR_MESSAGE);
//	        	return;
//	        }
//	        rowsCount = Integer.valueOf(value);
//	        logger
//					.debug("Exited from SearchDataAction.rowCountTextFldActionPerformed()");
//	    }

	    private void rowCountTextFldFocusLost(java.awt.event.FocusEvent evt) {
	        logger
					.debug("Entered in SearchDataAction.rowCountTextFldFocusLost()");
	        try{
		        String value = view.getRowCountTextFld().getText();
		        if(!Validation.validateNumber(value, 4, 0)){
		        	JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.NOT_A_NUMBER_MSG)
							,"Error Message",JOptionPane.ERROR_MESSAGE);
		        	return;
		        }
		        rowsCount = Integer.valueOf(value);
		        startIndex = 0;
		        endIndex = rowsCount;
		        logger.debug("Exited from SearchDataAction.getRowsCount() rowsCount "+rowsCount);
	        }catch(IJunitException e){
	        	JOptionPane.showMessageDialog(view,e.getUserErrorMsg(), "Error Message",JOptionPane.ERROR_MESSAGE);
	        	view.getRowCountTextFld().setText(String.valueOf(rowsCount));
	        	view.getRowCountTextFld().grabFocus();
	        }
	    }

		/**
		 * @return the rowsCount
		 */
		public int getRowsCount() {
			return rowsCount;
		}



		

}
