/**
 * 
 */
package com.ijunit.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IApplicationMsgCodes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.FileWriterFactory;
import com.ijunit.common.utility.Validation;
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.DBConfigurationParser;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.view.ConfigurationView;
import com.ijunit.swing.view.Start;

/**
 * @author vcjain
 *
 */
public class ConfigurationAction implements ActionListener
{

	static Logger logger = Logger.getLogger(ConfigurationAction.class);
	
	private ConfigurationView view = null;
	
	public ConfigurationAction(ConfigurationView view){
		this.view = view; 
	}
	
	public void actionPerformed(ActionEvent evt) 
	{
		logger.debug("ENTER: Method actionPerformed");
		logger.debug("Action Command  "+evt.getActionCommand());
		if(evt.getActionCommand().equalsIgnoreCase(view.getSubmitBtn().getActionCommand()))
		{
			submitBtnAction(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getCancelBtn().getActionCommand()))
		{
			cancelBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getTestConfBtn().getActionCommand()))
		{
			testConfBtnAction(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getAppTestConfBtn().getActionCommand()))
		{
			appTestConfBtnAction(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getClassPathBtn().getActionCommand()))
		{
			classPathBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getLogPathBtn().getActionCommand()))
		{
			logPathBtnActionPerformed(evt);
		}else  if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseBtn().getActionCommand()))
		{
			browseBtnAction(evt);
		}else 
		{
			logger.debug("No Action Matches");
		}
		logger.debug("EXIT: Method actionPerformed");
	}

	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) 
    {
		logger
				.debug("Entered in ConfigurationAction.cancelBtnActionPerformed()");
        Start parent = (Start)view.getParentObject();
        parent.showLogo();
        
        logger.debug("Exited from ConfigurationAction.cancelBtnActionPerformed()");
    }

    private void submitBtnAction(java.awt.event.ActionEvent evt) 
    {
    	
    	//Writting iJunit.properties file
    	try{
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put(IConfigurationConstant.ROOT_PRP, view.getRootTextFld().getText().replace('\\','/'));
			map.put(IConfigurationConstant.CLASSPATH_DIR, view.getClasspathTextFld().getText().replace('\\','/'));
			map.put(IConfigurationConstant.RUNNINGMODE_PRP, view.getRunModeChkBox().isSelected()?"true":"false");
			map.put(IConfigurationConstant.LOG_DIR, view.getLogTextFld().getText().replace('\\','/'));
    		FileWriterFactory.getFileWriter(IFileConstant.RESOURCE_CONF_WRITER)
								.write(map, ResourceManager.getFile(IFileConstant.RESOURCE_FILE));
			map.clear();
			
			//Writting database.cfg.xml file
			Map<String,String> innerMap = new HashMap<String,String>();
			innerMap.put(IConfigurationConstant.CONFIGURATION_NAME_TMP, IConfigurationConstant.APP_CONF);
			innerMap.put(IConfigurationConstant.DATABASE_TMP,view.getDatabaseTextFld().getText());
			innerMap.put(IConfigurationConstant.DRIVER_TMP,view.getDriverTextFld().getText());
			innerMap.put(IConfigurationConstant.DBURL_TMP,view.getDbURLTextFld().getText());
			innerMap.put(IConfigurationConstant.HOST_TMP,view.getHostTextFld().getText());
			innerMap.put(IConfigurationConstant.PORT_TMP,view.getPortTextFld().getText());
			innerMap.put(IConfigurationConstant.USER_TMP,view.getUserNameTextFld().getText());
			innerMap.put(IConfigurationConstant.PASSWORD_TMP,new String(view.getPasswordTextFld().getPassword()));
			map.put(IConfigurationConstant.APP_CONF, innerMap);
			
			innerMap = new HashMap<String,String>();
			innerMap.put(IConfigurationConstant.CONFIGURATION_NAME_TMP, IConfigurationConstant.TESTAPP_CONF);
			innerMap.put(IConfigurationConstant.DATABASE_TMP,view.getAppDatabaseTextFld().getText());
			innerMap.put(IConfigurationConstant.DRIVER_TMP,view.getAppDriverTextFld().getText());
			innerMap.put(IConfigurationConstant.DBURL_TMP,view.getAppDBURLTextFld().getText());
			innerMap.put(IConfigurationConstant.HOST_TMP,view.getAppHostTextFld().getText());
			innerMap.put(IConfigurationConstant.PORT_TMP,view.getAppPortTextFld().getText());
			innerMap.put(IConfigurationConstant.USER_TMP,view.getAppUserNameTextFld().getText());
			innerMap.put(IConfigurationConstant.PASSWORD_TMP,new String(view.getAppPasswordTextFld().getPassword()));
			map.put(IConfigurationConstant.TESTAPP_CONF, innerMap);
			
    		FileWriterFactory.getFileWriter(IFileConstant.DATABASE_CONF_WRITER)
								.write(map, ResourceManager.getFile(IFileConstant.DATABASE_CONF_FILE));
			
			int i = JOptionPane.showConfirmDialog(view, ApplicationMessageParser.getMessage(IApplicationMsgCodes.CONFIGURATION_CHANGE_MSG),
	        		"Confirmation Message" ,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	        if(i == JOptionPane.YES_OPTION){
	            System.exit(0);
	        }else{
	        	Start parent = (Start)view.getParentObject();
	        	parent.showLogo();
	        }
		}catch(IJunitException e){
			JOptionPane.showMessageDialog(view,e.getUserErrorMsg(), "Error Message",JOptionPane.ERROR_MESSAGE);
		}
		
    }

    private void browseBtnAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnAction
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File("."));
        int returnValue = fileChooser.showDialog(view,"Choose");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = fileChooser.getSelectedFile();
        	view.getRootTextFld().setText(selectedFile.toString());
        }
    }//GEN-LAST:event_browseBtnAction

    private void testConfBtnAction(java.awt.event.ActionEvent evt) {
    	logger.debug("Entered in ConfigurationAction.testConfBtnAction()");
    	
        Driver driver = null;
        try {
            driver = (Driver) Class.forName(view.getDriverTextFld().getText()).newInstance();
            Properties dbProperties = new Properties();
            dbProperties.setProperty("user",view.getUserNameTextFld().getText() );
            dbProperties.setProperty("password",new String(view.getPasswordTextFld().getPassword()));
            String url = view.getDbURLTextFld().getText()+":"+"@"+view.getHostTextFld().getText()+":"
                                                +view.getPortTextFld().getText()+":"
                                                +view.getDatabaseTextFld().getText();
            logger.debug("DataBase connection URL "+url);
            Connection con = driver.connect(url, dbProperties);
            con.setAutoCommit(false);
            String query = "SELECT * FROM DUAL";
            Statement stmt = con.createStatement();
            stmt.execute(query);
            JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.CONNECTION_SUCCESS)
                    ,"Message",JOptionPane.INFORMATION_MESSAGE);
            logger.debug("Exited from ConfigurationAction.testConfBtnAction()");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP)
                    ,"Error Message",JOptionPane.ERROR_MESSAGE);
            logger.error(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), ex);
        } 
    }

    private void classPathBtnActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File("."));
        int returnValue = fileChooser.showDialog(view,"Choose");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          view.getClasspathTextFld().setText(selectedFile.toString());
        }
    }

    private void logPathBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File(view.getRootTextFld().getText()));
        int returnValue = fileChooser.showDialog(view,"Choose");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          view.getLogTextFld().setText(selectedFile.toString());
        }
    }

    private void runModeChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runModeChkBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_runModeChkBoxActionPerformed

    private void appTestConfBtnAction(java.awt.event.ActionEvent evt) {
    	logger.debug("Entered in ConfigurationAction.appTestConfBtnAction()");
    	
    	Driver driver = null;
        try {
            driver = (Driver) Class.forName(view.getAppDriverTextFld().getText()).newInstance();
            Properties dbProperties = new Properties();
            dbProperties.setProperty("user",view.getAppUserNameTextFld().getText() );
            dbProperties.setProperty("password",new String(view.getAppPasswordTextFld().getPassword()));
            String url = view.getAppDBURLTextFld().getText()+":"+"@"+view.getAppHostTextFld().getText()+":"
                                                +view.getAppPortTextFld().getText()+":"
                                                +view.getAppDatabaseTextFld().getText();
            logger.debug("DataBase connection URL "+url);
            Connection con = driver.connect(url, dbProperties);
            con.setAutoCommit(false);
            String query = "SELECT * FROM DUAL";
            Statement stmt = con.createStatement();
            stmt.execute(query);
            JOptionPane.showMessageDialog(view,ApplicationMessageParser.getMessage(IApplicationMsgCodes.CONNECTION_SUCCESS)
                    ,"Message",JOptionPane.INFORMATION_MESSAGE);
            logger.debug("Exited from ConfigurationAction.appTestConfBtnAction()");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP)
                    ,"Error Message",JOptionPane.ERROR_MESSAGE);
            logger.error(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), ex);
        }
    }
    
    public void populateFields(){
    	view.getRootTextFld().setText(ResourceManager.getProperty(IConfigurationConstant.ROOT_PRP));
    	view.getClasspathTextFld().setText(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR));
    	view.getLogTextFld().setText(ResourceManager.getProperty(IConfigurationConstant.LOG_DIR));
    	view.getRunModeChkBox().setSelected(Validation.getBooleanFromString(
    						ResourceManager.getProperty(IConfigurationConstant.RUNNINGMODE_PRP)));
    	
    	DBConfigurationParser dbParser = DBConfigurationParser.getInstance();
    	view.getAppDatabaseTextFld().setText(dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.DATABASE));
    	view.getAppDBURLTextFld().setText(dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.DATABASEURL));
    	view.getAppDriverTextFld().setText(dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.DRIVER));
    	view.getAppHostTextFld().setText(dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.HOST));
    	view.getAppPasswordTextFld().setText(dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.PASSWORD));
    	view.getAppPortTextFld().setText(dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.PORT));
    	view.getAppUserNameTextFld().setText(dbParser.getProperty(IConfigurationConstant.TESTAPP_CONF, IConfigurationConstant.USER));
    	
    	view.getDatabaseTextFld().setText(dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.DATABASE));
    	view.getDbURLTextFld().setText(dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.DATABASEURL));
    	view.getDriverTextFld().setText(dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.DRIVER));
    	view.getHostTextFld().setText(dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.HOST));
    	view.getPasswordTextFld().setText(dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.PASSWORD));
    	view.getPortTextFld().setText(dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.PORT));
    	view.getUserNameTextFld().setText(dbParser.getProperty(IConfigurationConstant.APP_CONF, IConfigurationConstant.USER));
    	
    }
    
}
