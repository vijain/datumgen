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
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.resource.ResourceManagerBase;
import com.ijunit.swing.view.ConfAppDBWizardView;
import com.ijunit.swing.view.ConfDBWizardView;
import com.ijunit.swing.view.ConfRootWizardView;
import com.ijunit.swing.view.ConfigurationWizard;

/**
 * @author vcjain
 *
 */
public class ConfigurationWizardAction implements ActionListener{
	
	static Logger logger = Logger.getLogger(ConfigurationWizardAction.class);
	
	Map<String, String> map = new HashMap<String, String>();
	ConfigurationWizard view = null;
	ConfRootWizardView rootWizard = null;
	ConfDBWizardView dbWizard = null;
	ConfAppDBWizardView appDBWizard = null;
	int btnValue = 0;
	
	public ConfigurationWizardAction(ConfigurationWizard view, ConfRootWizardView rootWizard,
			ConfDBWizardView dbWizard, ConfAppDBWizardView appDBWizard) {
		this.view = view;
		this.rootWizard = rootWizard;
		this.dbWizard = dbWizard;
		this.appDBWizard = appDBWizard;
	}
	
	/**
	 * @param appDBWizard the appDBWizard to set
	 */
	public void setAppDBWizard(ConfAppDBWizardView appDBWizard) {
		this.appDBWizard = appDBWizard;
	}

	/**
	 * @param dbWizard the dbWizard to set
	 */
	public void setDbWizard(ConfDBWizardView dbWizard) {
		this.dbWizard = dbWizard;
	}

	/**
	 * @param rootWizard the rootWizard to set
	 */
	public void setRootWizard(ConfRootWizardView rootWizard) {
		this.rootWizard = rootWizard;
	}

	public void actionPerformed(ActionEvent evt) {
		logger.debug("Entered in ConfigurationWizardAction.actionPerformed()");
		if(evt.getSource().equals(view.getCancelBtn())){
			cancelBtnActionPerformed(evt);
		}else if(evt.getSource().equals(view.getPreviousButton())){
			previousBtnAction(evt);
			btnValue--;
			if(btnValue < 0){
				btnValue = 0;
			}
		}else if(evt.getSource().equals(view.getNextBtn()))	{
			nextBtnAction(evt);
			btnValue++;
			if(btnValue > 2){
				btnValue = 2;
			}
		}else if(evt.getSource().equals(view.getFinishBtn())){
			finishBtnAction(evt);
		}else if(evt.getSource().equals(rootWizard.getClassPathBtn()))	{
			classPathBtnActionPerformed(evt);
		}else if(evt.getSource().equals(rootWizard.getLogPathBtn())){
			logPathBtnActionPerformed(evt);
		}else  if(evt.getSource().equals(rootWizard.getBrowseBtn())){
			browseBtnAction(evt);
		}else  if(evt.getSource().equals(dbWizard.getTestConfBtn())){
			testConfBtnAction(evt);
		}else  if(evt.getSource().equals(appDBWizard.getAppTestConfBtn())){
			appTestConfBtnAction(evt);
		}else 
		{
			logger.debug("No Action Matches");
		}

		logger.debug("Exited from ConfigurationWizardAction.actionPerformed()");
	}
	
	private void nextBtnAction(java.awt.event.ActionEvent evt){
		logger.debug("Entered in ConfigurationWizardAction.rootNextBtnAction() "+btnValue);
		switch(btnValue){
		case 0:
			view.getPreviousButton().setEnabled(true);
			view.getTab().setSelectedIndex(1);
			break;
		case 1:
			view.getNextBtn().setEnabled(false);
			view.getFinishBtn().setEnabled(true);
			view.getTab().setSelectedIndex(2);
			break;
		case 2:
			break;
		}
		
		logger.debug("Exited from ConfigurationWizardAction.enclosing_method()");
	}
	
	private void previousBtnAction(java.awt.event.ActionEvent evt){
		logger.debug("Entered in ConfigurationWizardAction.rootPreviousBtnAction()");
		switch(btnValue){
		case 0:
			break;
		case 1:
			view.getPreviousButton().setEnabled(false);
			view.getTab().setSelectedIndex(0);
			break;
		case 2:
			view.getNextBtn().setEnabled(true);
			view.getFinishBtn().setEnabled(false);
			view.getTab().setSelectedIndex(1);
			break;
		}
		
		logger.debug("Exited from ConfigurationWizardAction.rootPreviousBtnAction()");
	}
	
    private void browseBtnAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnAction
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File("."));
        int returnValue = fileChooser.showDialog(rootWizard,"Choose");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = fileChooser.getSelectedFile();
        	rootWizard.getRootTextFld().setText(selectedFile.toString());
        }
    }//GEN-LAST:event_browseBtnAction

        private void classPathBtnActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File("."));
        int returnValue = fileChooser.showDialog(rootWizard,"Choose");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          rootWizard.getClasspathTextFld().setText(selectedFile.toString());
        }else{
        	return;
        }
    }

    private void logPathBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File(rootWizard.getRootTextFld().getText()));
        int returnValue = fileChooser.showDialog(rootWizard,"Choose");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          rootWizard.getLogTextFld().setText(selectedFile.toString());
        }else{
        	return;
        }
    }
    
    private void testConfBtnAction(java.awt.event.ActionEvent evt) {
    	logger.debug("Entered in ConfigurationAction.testConfBtnAction()");
    	
        Driver driver = null;
        try {
            driver = (Driver) Class.forName(dbWizard.getDriverTextFld().getText()).newInstance();
            Properties dbProperties = new Properties();
            dbProperties.setProperty("user",dbWizard.getUserNameTextFld().getText() );
            dbProperties.setProperty("password",new String(dbWizard.getPasswordTextFld().getPassword()));
            String url = dbWizard.getDbURLTextFld().getText()+":"+"@"+dbWizard.getHostTextFld().getText()+":"
                                                +dbWizard.getPortTextFld().getText()+":"
                                                +dbWizard.getDatabaseTextFld().getText();
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

    private void appTestConfBtnAction(java.awt.event.ActionEvent evt) {
    	logger.debug("Entered in ConfigurationAction.appTestConfBtnAction()");
    	
    	Driver driver = null;
        try {
            driver = (Driver) Class.forName(appDBWizard.getAppDriverTextFld().getText()).newInstance();
            Properties dbProperties = new Properties();
            dbProperties.setProperty("user",appDBWizard.getAppUserNameTextFld().getText() );
            dbProperties.setProperty("password",new String(appDBWizard.getAppPasswordTextFld().getPassword()));
            String url = appDBWizard.getAppDBURLTextFld().getText()+":"+"@"+appDBWizard.getAppHostTextFld().getText()+":"
                                                +appDBWizard.getAppPortTextFld().getText()+":"
                                                +appDBWizard.getAppDatabaseTextFld().getText();
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
    
    private void finishBtnAction(java.awt.event.ActionEvent evt) 
    {
    	
    	//Writting iJunit.properties file
    	try{
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	String fileName = JOptionPane.showInputDialog(null, "Specify name of file (such as datumgen_52.properties): ",	"Input", 1);
			String ext = fileName.substring(fileName.indexOf('.')+1);
			if(!(ext.equalsIgnoreCase(ResourceManagerBase.PROPERTY_FILE_EXT))){
				throw new IJunitException("Extension of file name is not correct. Please specify .properties as extension");
			}

	    	map.put(IConfigurationConstant.ROOT_PRP, rootWizard.getRootTextFld().getText().replace('\\','/'));
			map.put(IConfigurationConstant.CLASSPATH_DIR, rootWizard.getClasspathTextFld().getText().replace('\\','/'));
			map.put(IConfigurationConstant.TESTCLASSES_DIR, rootWizard.getTestClasspathTextFld().getText().replace('\\','/'));
			
			//map.put(IConfigurationConstant.RUNNINGMODE_PRP, rootWizard.getRunModeChkBox().isSelected()?"true":"false");
			map.put(IConfigurationConstant.RUNNINGMODE_PRP, "true");
			map.put(IConfigurationConstant.LOG_DIR, rootWizard.getLogTextFld().getText().replace('\\','/'));
			map.put(IConfigurationConstant.VERSION, rootWizard.getVersionTextField().getText());
    		
			File dir = new File(ResourceManagerBase.resourcePath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			FileWriterFactory.getFileWriter(IFileConstant.RESOURCE_CONF_WRITER)
								.write(map, new File(ResourceManagerBase.resourcePath+ResourceManagerBase.FILE_SEPRATOR+fileName));
			map.clear();
			
			//Writting database.cfg.xml file
			Map<String,String> innerMap = new HashMap<String,String>();
			innerMap.put(IConfigurationConstant.CONFIGURATION_NAME_TMP, IConfigurationConstant.APP_CONF);
			innerMap.put(IConfigurationConstant.DATABASE_TMP,dbWizard.getDatabaseTextFld().getText());
			innerMap.put(IConfigurationConstant.DRIVER_TMP,dbWizard.getDriverTextFld().getText());
			innerMap.put(IConfigurationConstant.DBURL_TMP,dbWizard.getDbURLTextFld().getText());
			innerMap.put(IConfigurationConstant.HOST_TMP,dbWizard.getHostTextFld().getText());
			innerMap.put(IConfigurationConstant.PORT_TMP,dbWizard.getPortTextFld().getText());
			innerMap.put(IConfigurationConstant.USER_TMP,dbWizard.getUserNameTextFld().getText());
			innerMap.put(IConfigurationConstant.PASSWORD_TMP,new String(dbWizard.getPasswordTextFld().getPassword()));
			map.put(IConfigurationConstant.APP_CONF, innerMap);
			
			innerMap = new HashMap<String,String>();
			innerMap.put(IConfigurationConstant.CONFIGURATION_NAME_TMP, IConfigurationConstant.TESTAPP_CONF);
			innerMap.put(IConfigurationConstant.DATABASE_TMP,appDBWizard.getAppDatabaseTextFld().getText());
			innerMap.put(IConfigurationConstant.DRIVER_TMP,appDBWizard.getAppDriverTextFld().getText());
			innerMap.put(IConfigurationConstant.DBURL_TMP,appDBWizard.getAppDBURLTextFld().getText());
			innerMap.put(IConfigurationConstant.HOST_TMP,appDBWizard.getAppHostTextFld().getText());
			innerMap.put(IConfigurationConstant.PORT_TMP,appDBWizard.getAppPortTextFld().getText());
			innerMap.put(IConfigurationConstant.USER_TMP,appDBWizard.getAppUserNameTextFld().getText());
			innerMap.put(IConfigurationConstant.PASSWORD_TMP,new String(appDBWizard.getAppPasswordTextFld().getPassword()));
			map.put(IConfigurationConstant.TESTAPP_CONF, innerMap);
			
			File file = new File(rootWizard.getRootTextFld().getText().replace('\\','/')
																	+ResourceManagerBase.FILE_SEPRATOR+ResourceManagerBase.DATABASE_DIR
																	+ResourceManagerBase.FILE_SEPRATOR+IFileConstant.DATABASE_CONF_FILE);
    		FileWriterFactory.getFileWriter(IFileConstant.DATABASE_CONF_WRITER)
								.write(map, file);
			
	        JOptionPane.showMessageDialog(view, "Please restart datumgen","Restart Message",JOptionPane.PLAIN_MESSAGE);
	        System.exit(0);
		}catch(IJunitException e){
			JOptionPane.showMessageDialog(view,e.getUserErrorMsg(), "Error Message",JOptionPane.ERROR_MESSAGE);
		}
		
    }
    
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
    	System.exit(0);
    }

}
