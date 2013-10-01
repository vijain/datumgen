package com.ijunit.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
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
import com.ijunit.common.utility.writer.DBConfigurationXMLWriter;
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.view.ConfigurationDialog;

public class ConfigurationDialogAction implements ActionListener 
{
	static Logger logger = Logger.getLogger(ConfigurationDialogAction.class);
	
	ConfigurationDialog view;
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
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getClassPathBtn().getActionCommand()))
		{
			classPathBtnActionPerformed(evt);
		}else  if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseBtn().getActionCommand()))
		{
			browseBtnAction(evt);
		}else 
		{
			logger.debug("No Action Matches");
		}
		logger.debug("EXIT: Method actionPerformed");
	}

	public ConfigurationDialogAction(ConfigurationDialog view)
	{
		this.view = view;
	}
	
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) 
    {
        int i = JOptionPane.showConfirmDialog(view, "Configuration properties are required for " +
                "functioning.\n Are you still want to exit?",""
                ,JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void submitBtnAction(java.awt.event.ActionEvent evt) 
    {
    	//Writting iJunit.properties file
    	try{
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put(IConfigurationConstant.ROOT_PRP, view.getRootTextFld().getText());
			map.put(IConfigurationConstant.CLASSPATH_DIR, view.getClasspathTextFld().getText());
			map.put(IConfigurationConstant.RUNNINGMODE_PRP, ResourceManager.getProperty(IConfigurationConstant.RUNNINGMODE_PRP));
    		FileWriterFactory.getFileWriter(IFileConstant.RESOURCE_CONF_WRITER)
								.write(map, ResourceManager.getFile(IFileConstant.RESOURCE_FILE));
			map.clear();
			
	//		Writting database.cfg.xml file
	    	map.put(IConfigurationConstant.DATABASE_TMP,view.getDatabaseTextFld().getText());
	    	map.put(IConfigurationConstant.DRIVER_TMP,view.getDriverTextFld().getText());
	    	map.put(IConfigurationConstant.DBURL_TMP,view.getDbURLTextFld().getText());
	    	map.put(IConfigurationConstant.HOST_TMP,view.getHostTextFld().getText());
	    	map.put(IConfigurationConstant.PORT_TMP,view.getPortTextFld().getText());
	    	map.put(IConfigurationConstant.USER_TMP,view.getUserNameTextFld().getText());
	    	map.put(IConfigurationConstant.PASSWORD_TMP,view.getPasswordTextFld().getPassword());
	    	
    		FileWriterFactory.getFileWriter(IFileConstant.DATABASE_CONF_WRITER)
								.write(map, ResourceManager.getFile(IFileConstant.DATABASE_CONF_FILE));
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

    private void testConfBtnAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testConfBtnAction
        Driver driver;
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
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP)
                    ,"Error Message",JOptionPane.ERROR_MESSAGE);
            logger.error(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), ex);
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP)
                    ,"Error Message",JOptionPane.ERROR_MESSAGE);
            logger.error(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP)
                    ,"Error Message",JOptionPane.ERROR_MESSAGE);
            logger.error(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP)
                    ,"Error Message",JOptionPane.ERROR_MESSAGE);
            logger.error(ErrorMessageParser.getMessage(IExceptionCodes.DATABASE_CON_EXP), ex);
        }

    }//GEN-LAST:event_testConfBtnAction

    private void classPathBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classPathBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File("."));
        int returnValue = fileChooser.showDialog(view,"Choose");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          view.getClasspathTextFld().setText(selectedFile.toString());
        }

}//GEN-LAST:event_classPathBtnActionPerformed

	
}
