/**
 * 
 */
package com.ijunit.swing.action;

import java.awt.Component;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.DataTypes;
import com.ijunit.common.constant.IApplicationMsgCodes;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.database.DBConnection;
import com.ijunit.common.database.TestDataDAO;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.CommonUtility;
import com.ijunit.common.utility.FileWriterFactory;
import com.ijunit.common.utility.GTestClassLoader;
import com.ijunit.common.utility.Validation;
import com.ijunit.common.utility.writer.FileWriter;
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.parser.ObjectDataParser;
import com.ijunit.parser.ObjectMappingParser;
import com.ijunit.resource.ResourceManager;
import com.ijunit.swing.model.AttributeData;
import com.ijunit.swing.model.IAttributeDataModel;
import com.ijunit.swing.model.ObjectListModel;
import com.ijunit.swing.view.ModifyObjectView;
import com.ijunit.swing.view.Start;

/**
 * @author vicky
 *
 */
public class ModifyObjectAction implements ActionListener, ListSelectionListener, DropTargetListener
{
	static Logger logger = Logger.getLogger(ModifyObjectAction.class);
	private ModifyObjectView view;
	private IAttributeDataModel model;
	
	
	public ModifyObjectAction(Component view, IAttributeDataModel model)
	{
		this.view = (ModifyObjectView)view;
		this.model = model;
	}
	
	/**
	 * This method inturn calls the corresponding action based on action event.
	 * @param ActionEvent evt.
	 * @return void
	 */
	public void actionPerformed(ActionEvent evt) 
	{
		logger.debug("ENTER: ModifyObjectAction.actionPerformed()");
		logger.debug("Action Command  "+evt.getActionCommand());
		
		if(evt.getActionCommand().equalsIgnoreCase(view.getSaveBtn().getActionCommand())){
			saveBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getCancelBtn().getActionCommand())){
			cancelBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getResetBtn().getActionCommand())){
			resetBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getDeleteBtn().getActionCommand())){
			deleteBtnActionPerformed(evt);
		}else  if(evt.getActionCommand().equalsIgnoreCase(view.getSelectBtn().getActionCommand())){
			selectBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase("TableDelete")){
			deleteBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getAutoMapBtn().getActionCommand())){
			autoMapBtnActionPerformed(evt);
		}else if(evt.getActionCommand().equalsIgnoreCase(view.getBrowseBtn().getActionCommand())){
			browseBtnActionPerformed(evt);
		}else if(evt.getSource().equals(view.getClassTextFld())){
			classTextFldActionPerformed(evt);
		}else
		{
			logger.debug("No Action Matches");
		}
		logger.debug("EXIT: ModifyObjectAction.actionPerformed()");
	}
	
	/**
	 * Action for Select button.
	 * @param evt
	 */
	private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) 
	{
		logger.debug("ENTER ModifyObjectAction.selectBtnActionPerformed()");
		Object [] fields = null;
		Object [] methods = null;
		Class classToRead = null; 
		Connection appCon = null;
		Object[] tableColumns;
		Method method = null;
		Field field = null;
		if(!(Validation.isEmpty(view.getClassTextFld().getText(), "Class", view.getClassTextFld()) 	&&
				Validation.isEmpty(view.getTableTextFld().getText(), "Table", view.getTableTextFld()) &&
				Validation.isEmpty(view.getDataFileTextFld().getText(), "Data File", view.getDataFileTextFld()) )){
			return;
		}
		try{
			try {
				
				classToRead = GTestClassLoader.getInstance(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)).loadClass(view.getClassTextFld().getText()); 
			} catch (ClassNotFoundException e) {
				logger.error("In ModifyObjectAction.selectBtnActionPerformed() "+ErrorMessageParser
											.getMessage(IExceptionCodes.DATAOBJECT_CLASS_NOTFOUND_EXP), e);
				view.getClassTextFld().grabFocus();
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.DATAOBJECT_CLASS_NOTFOUND_EXP),e);
			} 
			
			try {
				appCon = DBConnection.getAppConnection();
				tableColumns = TestDataDAO.getColumnNames(view.getTableTextFld().getText(),appCon);
			} catch (SQLException e) {
				logger.error("In .selectBtnActionPerformed "+ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_COLUMNNAMES_EXP), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_COLUMNNAMES_EXP),e);
			}
			
			Class superClass = (Class)classToRead.getSuperclass();
			TreeSet<Field> fieldSet = new TreeSet<Field>(new Comparator(){
				public int compare(Object obj1, Object obj2){
					Field field1 = (Field)obj1;
					Field field2 = (Field)obj2;
					return field1.getName().compareToIgnoreCase(field2.getName());
				}
			});
			fieldSet.addAll(Arrays.asList(superClass.getDeclaredFields()));
			fieldSet.addAll(Arrays.asList(classToRead.getDeclaredFields()));
			
			
			TreeSet<Method> methodSet = new TreeSet<Method>(new Comparator(){
				public int compare(Object obj1, Object obj2){
					Method method1 = (Method)obj1;
					Method method2 = (Method)obj2;
					return method1.getName().compareToIgnoreCase(method2.getName());
				}
			});
			methodSet.addAll(Arrays.asList(superClass.getDeclaredMethods()));
			methodSet.addAll(Arrays.asList(classToRead.getDeclaredMethods()));
			
			TreeSet<Object> columnSet = new TreeSet<Object>();
			columnSet.addAll(Arrays.asList(tableColumns));
			TreeSet<Field> columnFieldSet = new TreeSet<Field>(fieldSet);
			
			//Creating clone.
			TreeSet<Object> columnSetClone = (TreeSet<Object>)columnSet.clone();
			TreeSet<Object> methodSetClone = (TreeSet<Object>)methodSet.clone();
			TreeSet<Object> fieldSetClone = (TreeSet<Object>)fieldSet.clone();
			
			// Obtaining Data Object from Object file.
			 String objectDataFilePath = view.getDataFileTextFld().getText();
			 ObjectDataParser parser  = new ObjectDataParser();
			try {
				parser.parse(ResourceManager.getFile(objectDataFilePath).toString());
			} catch (Exception e) {
				logger.error("In AddTestDataAction.selectBtnActionPerformed "+
						ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,new String[]{objectDataFilePath}), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.OXD_PARSE_EXP,new String[]{objectDataFilePath}),e);
			}
			Vector attributeDataList = parser.getAttributeDataList();
			boolean methodFound = true;
			boolean columnFound = true;
			for (Iterator iter = attributeDataList.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				
				try {
					method = getMethod(CommonUtility.removeArgument(element.getSetMethod()), element.getType(), classToRead);
					field = getField(element.getName(), classToRead);
					fieldSet.remove(field);
					methodSet.remove(method);
				} catch (NoSuchMethodException e) {
					logger
							.debug("In ModifyObjectAction.selectBtnActionPerformed message= Method corresponding to field "+ field+ " not found.");
					methodFound = false;
				} catch (NoSuchFieldException e) {
					logger
					.debug("In ModifyObjectAction.selectBtnActionPerformed message= field "+ field+ " not found.");
					methodFound = false;
				}
				
				try {
					field = getField(element.getName(), classToRead);
					if(columnSet.remove(element.getColumn())) {
						columnFieldSet.remove(field);
					}
				} catch (NoSuchFieldException e) {
					logger
					.debug("In ModifyObjectAction.selectBtnActionPerformed message= field "+ field+ " not found.");
					columnFound = false;
				}
				if(methodFound || columnFound){
					model.addAttributeData(element);
				}
				methodFound = true;
				columnFound = true;
			}
			
			fields = fieldSet.toArray();
			methods = CommonUtility.filterMethods(methodSet.toArray());
			
			ObjectListModel fieldMethodModel = new ObjectListModel(fieldSet.toArray(),false,Field.class);
			ObjectListModel methodModel = new ObjectListModel(methods,false,Method.class);
			ObjectListModel fieldTableModel = new ObjectListModel(columnFieldSet.toArray(),false,Field.class);
			ObjectListModel tableColumnModel = new ObjectListModel(columnSet.toArray(),false,String.class);
			
			fieldMethodModel.createMap(fieldSetClone.toArray());
			methodModel.createMap(methodSetClone.toArray());
			fieldTableModel.createMap(fieldSetClone.toArray());
			tableColumnModel.createMap(columnSetClone.toArray());
			
			view.getMethodAttrList().setModel(fieldMethodModel);
			view.getTableAttrList().setModel(fieldTableModel);
			view.getSetMethodList().setModel(methodModel);
			view.getColumnList().setModel(tableColumnModel);
			model.setClassName(view.getClassTextFld().getText());
			
			//view.getAutoMapBtn().setEnabled(true);
			view.getResetBtn().setEnabled(true);
			view.getSaveBtn().setEnabled(true);
			view.getDeleteBtn().setEnabled(true);
		}catch (IJunitException e) {
			JOptionPane.showMessageDialog(view,e.getUserErrorMsg(),"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(appCon);
		}
		
		logger.debug("EXIT ModifyObjectAction.selectBtnActionPerformed()");
	}

	/**
	 * Action for Auto Map button.
	 * @param evt
	 */
	private void autoMapBtnActionPerformed(ActionEvent evt) {
		logger.debug("Entered in ModifyObjectAction.autoMapBtnActionPerformed()");
		
		AttributeData data = null;
		Connection appCon = null;
		ResultSetMetaData metaData = null;
		Method method = null;
		String column = null;

		Set<Field> fieldMatch = new HashSet<Field>();
		Set<Field> fieldTableMatch = new HashSet<Field>();
		Set<Method> methodMatch = new HashSet<Method>();
		Set<String> columnMatch = new HashSet<String>();

		try{
			
			Object[] fieldMethodArray = ((ObjectListModel)view.getMethodAttrList().getModel()).getObjList().toArray().clone();
			Object[] methodArray	= ((ObjectListModel)view.getSetMethodList().getModel()).getObjList().toArray().clone();
			Object[] columnArray	= ((ObjectListModel)view.getColumnList().getModel()).getObjList().toArray().clone();

			appCon = DBConnection.getAppConnection();
						
			try {
				metaData = TestDataDAO.getTableMetaData(view.getTableTextFld().getText(),appCon);
			} catch (SQLException e) {
				logger.error("In ModifyObjectAction.autoMapBtnActionPerformed "+ErrorMessageParser.getMessage(
																IExceptionCodes.FETCHING_COLUMNNAMES_EXP), e);
				throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_COLUMNNAMES_EXP),e);
			}
			
			for (int i = 0; i < fieldMethodArray.length; i++) {
				Field field = (Field) fieldMethodArray[i];
				
				logger.debug("In ModifyObjectAction.autoMapBtnActionPerformed message=FieldName "+CommonUtility.removePrefix(field.getName()));
				if(CommonUtility.isPackagetoOmit(field.getType().getCanonicalName())){
					
					//Matching Method to Field
						for (int j = 0; j < methodArray.length; j++) {
							method = (Method) methodArray[j];
							if(method.getName().equalsIgnoreCase("set"+CommonUtility.removePrefix(field.getName()))){
								fieldMatch.add(field);
								methodMatch.add(method);
								data = new AttributeData(field.getName(),DataTypes.getTypeConstant(field.getType().getCanonicalName()),
										CommonUtility.getMethodNameWithoutRType(method.toString()));
								model.addAttributeData(data);
								break;
							}
						}//end of method for
						
						//Matching Column to Field
						for (int j = 0; j < columnArray.length; j++) {
							column = (String) columnArray[j];
							if(column.equalsIgnoreCase(CommonUtility.removePrefix(field.getName()))){
								fieldTableMatch.add(field);
								columnMatch.add(column);
								if(data != null){
									data.setColumn(column);
								}else{
									data = new AttributeData(field.getName(),0,"",column);
									model.addAttributeData(data);
								}
								break;
							}//end of table coulmn for
						}
						logger.debug("In ModifyObjectAction.autoMapBtnActionPerformed message= Attribute data "+data);

						//	populate database column properties
						if(data != null){
							setColumnProperties(column,metaData, data);
						}
						data = null;
				}
			}//end of field for
			
			for (Iterator iter = methodMatch.iterator(); iter.hasNext();) {
				Method element = (Method) iter.next();
				((ObjectListModel)view.getSetMethodList().getModel()).remove(element);
			}
			for (Iterator iter = fieldMatch.iterator(); iter.hasNext();) {
				Field element = (Field) iter.next();
				((ObjectListModel)view.getMethodAttrList().getModel()).remove(element);
			}
			for (Iterator iter = columnMatch.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				((ObjectListModel)view.getColumnList().getModel()).remove(element);
			}
			for (Iterator iter = fieldTableMatch.iterator(); iter.hasNext();) {
				Field element = (Field) iter.next();
				((ObjectListModel)view.getTableAttrList().getModel()).remove(element);
			}

			view.getAutoMapBtn().setEnabled(false);
			view.getSaveBtn().setEnabled(true);
			view.getDeleteBtn().setEnabled(true);
			
			logger.debug("Exited from ModifyObjectAction.autoMapBtnActionPerformed()");
		}catch (IJunitException e) {
			JOptionPane.showMessageDialog(view,e.getUserErrorMsg(), "Error Message", JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(appCon);
		}
	}
	
	private void setColumnProperties(String columnName, ResultSetMetaData metaData, AttributeData data) throws IJunitException{
		logger.debug("Entered in ModifyObjectAction.setColumnProperties() "+columnName);
		int index = getColumnIndexInMetaData(columnName, metaData);
		try {
			data.setColumnDisplaySize(metaData.getColumnDisplaySize(index));
			data.setColumnType(metaData.getColumnType(index));
			data.setColumnTypeName(metaData.getColumnTypeName(index));
			data.setPrecision(metaData.getPrecision(index));
			data.setScale(metaData.getScale(index));
			data.setAutoIncrement(metaData.isAutoIncrement(index));
			data.setIsNullable(metaData.isNullable(index));
			if(data.getType() == DataTypes.BOOLEAN || data.getType() == DataTypes.BOOLEAN_OBJ){
				data.setValue(false);
			}
		} catch (SQLException e) {
			logger.error("In ModifyObjectAction.autoMapBtnActionPerformed "+ErrorMessageParser.getMessage(
					IExceptionCodes.FETCHING_COLUMNPROPS_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_COLUMNPROPS_EXP),e);
		}
		logger.debug("Exited from ModifyObjectAction.setColumnProperties()");
	}
	
	private int getColumnIndexInMetaData(String columnName, ResultSetMetaData metaData) throws IJunitException{
		int index = -1;
		try {
			for(int i=1; i<=metaData.getColumnCount();i++){
				//logger.debug("In ModifyObjectAction.getColumnNames message= Column  "+i+"="+metaData.getColumnName(i));
				if(metaData.getColumnName(i).equalsIgnoreCase(columnName)){
					index = i;
					break;
				}
			}
		} catch (SQLException e) {
			logger.error("In ModifyObjectAction.getColumnIndexInMetaData "+ErrorMessageParser.getMessage(
					IExceptionCodes.FETCHING_COLUMNPROPS_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_COLUMNPROPS_EXP),e);
		}	
		return index;
	}
	
		
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) 
    {
        AttributeData data = null;
        ArrayList deletedRecords = new ArrayList();
        
        //creating list of deleted AttributeData from table
        for (int i = 0; i < model.getRowCount(); i++) 
        {
        	
        	if((Boolean)view.getTable().getModel().getValueAt(i,0))
        	{
        		deletedRecords.add(new Integer(i));
        	}
		}//end of for
        logger.debug("In ModifyObjectAction.deleteBtnActionPerformed() message= Rows to be deleted "
				+deletedRecords);
        //Adding the values back to List and removing the row from table.
        for (int i = 0; i < deletedRecords.size(); i++) 
        {
        	data = model.getAttributeDataAt(((Integer)deletedRecords.get(i)).intValue()-i);
        	logger
					.debug("In ModifyObjectAction.deleteBtnActionPerformed message= Atrribute Data to delete "+data);
        	if(data.getSetMethod()!= null && !data.getSetMethod().equalsIgnoreCase(""))
        	{
        		((ObjectListModel)view.getSetMethodList().getModel()).add(data.getSetMethod());	
        		((ObjectListModel)view.getMethodAttrList().getModel()).add(data.getName());
        	}
        	
        	if(data.getColumn()!= null && !data.getColumn().equalsIgnoreCase(""))
        	{
        		((ObjectListModel)view.getTableAttrList().getModel()).add(data.getName());
        		((ObjectListModel)view.getColumnList().getModel()).add(data.getColumn());
        	}
        	model.removeAttributeData(((Integer)deletedRecords.get(i)).intValue()-i);
        	
        	logger
					.debug("Exited from ModifyObjectAction.deleteBtnActionPerformed()");
		}//end of for
        
    }

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) 
    {
        logger.debug("Entered in ModifyObjectAction.resetBtnActionPerformed()");

        view.getMethodAttrList().setModel(new DefaultListModel());
        view.getSetMethodList().setModel(new DefaultListModel());
        view.getTableAttrList().setModel(new DefaultListModel());
        view.getColumnList().setModel(new DefaultListModel());
        model.removeAll();
        
        view.getSaveBtn().setEnabled(false);
        view.getDeleteBtn().setEnabled(false);
        logger.debug("Exited from ModifyObjectAction.resetBtnActionPerformed()");
    }//end of Method

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) 
    {
    	logger.debug("Entered in ModifyObjectAction.saveBtnActionPerformed()");
    	FileWriter writer = null;
    	String className = "";
    	IAttributeDataModel tableModel = null;
		try {
			writer = FileWriterFactory.getFileWriter(IFileConstant.OBJECTXML_DATA_WRITER);
			tableModel = (IAttributeDataModel)view.getTable().getModel();
	    	className = tableModel.getClassName();
	    	className = className.substring(className.lastIndexOf('.')+1);
	    	logger.debug("In ModifyObjectAction.saveBtnActionPerformed message= File name "
	    			+ResourceManager.getFile(className+"."
							+ResourceManager.OBJECTXMLDATA_FILE_EXT));
	    	
	    	//Writing Object Data to XMl file
	    	writer.write(tableModel, ResourceManager.getFile(className+"."
	    									+ResourceManager.OBJECTXMLDATA_FILE_EXT));
	    	
	    	//Writing Object Configuration to XML Mapping file
	    	writer = FileWriterFactory.getFileWriter(IFileConstant.OBJECTXML_MAP_WRITER);
	    	
	    	Map<String,String> map = new HashMap<String,String>();
	    	map.put(FileWriter.OCM_OBJECTCLASS_VAR, view.getClassTextFld().getText());
	    	map.put(FileWriter.OCM_OBJECTTABLE_VAR,view.getTableTextFld().getText().trim());
	    	map.put(FileWriter.OCM_DATAFILE_VAR, className.substring(className.lastIndexOf('.')+1)
	    			+"."+ResourceManager.OBJECTXMLDATA_FILE_EXT);
	    	map.put(FileWriter.OCM_SQLFILE_VAR, className.substring(className.lastIndexOf('.')+1)
	    			+"."+ResourceManager.SQL_FILE_EXT);
	    	
	    	writer.write(map, ResourceManager.getFile(IFileConstant.OBJECTXMLDATA_MAP_FILE));
	    	ObjectMappingParser.resetParser();
	    	
	    	//Inserting a record in DATACOLUMN
	    /* This functionality is commented for time being.
	    	List list = tableModel.getAllAttributeData();
	    	ArrayList keyColumnList = new ArrayList();
	    	for (Iterator iter = list.iterator(); iter.hasNext();) {
				AttributeData element = (AttributeData) iter.next();
				if(element.isPrimaryKey()){
					logger
							.debug("In ModifyObjectAction.saveBtnActionPerformed message= Primary key "+element.getColumn()); 
					keyColumnList.add(element.getColumn());
				}
			}
	    	Connection con = DBConnection.getConnection();
	    	TestDataDAO.modifyDataColumnRec(list, view.getClassTextFld().getText()
	    																, view.getTableTextFld().getText()
	    																, keyColumnList
	    																, className.substring(className.lastIndexOf('.')+1)
	    												    						+"."+ResourceManager.OBJECTSQL_FILE_EXT
	    																, className.substring(className.lastIndexOf('.')+1)
	    												    					+"."+ResourceManager.OBJECTXMLDATA_FILE_EXT
	    																, "com.ijunit.datahelper.DataHelper"
	    																, con);
	    	logger.debug("In ModifyObjectAction.saveBtnActionPerformed message= Records modified ");
	    	con.commit();
	    	DBConnection.releaseConnection(con);
	    */
//	    	Success Message
			JOptionPane.showMessageDialog(view,ApplicationMessageParser
							.getMessage(IApplicationMsgCodes.SAVEXMLDATA_SUCCESS,new String[]{
									className+"."+ResourceManager.OBJECTXMLDATA_FILE_EXT})
					,"Message",JOptionPane.INFORMATION_MESSAGE);
			((IAttributeDataModel)view.getTable().getModel()).removeAll();

			
			Start parent = (Start)view.getParentObject();
			parent.showLogo();

	    	
		} catch (Exception e) {
			logger.error("In ModifyObjectAction.saveBtnActionPerformed "
					+ErrorMessageParser.getMessage(IExceptionCodes.SAVEDATAXML_EXP), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.SAVEDATAXML_EXP)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
		
				
		logger.debug("Exited from ModifyObjectAction.saveBtnActionPerformed()");
    }

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	logger.debug("ENTER ModifyObjectAction.cancelBtnActionPerformed()");
    	//System.exit(0);
    	Start parent = (Start)view.getParentObject();
    	parent.showLogo();
    	logger.debug("EXIT ModifyObjectAction.cancelBtnActionPerformed()");
    }

	public void valueChanged(ListSelectionEvent event) 
	{
		String name = ((JList)event.getSource()).getName();
		if(name.equalsIgnoreCase(view.getMethodAttrList().getName())){
			view.getSetMethodList().clearSelection();
		}else if(name.equalsIgnoreCase(view.getTableAttrList().getName())){
			view.getColumnList().clearSelection();
		}
		
	}

	public void dragEnter(DropTargetDragEvent arg0) {
		
	}

	public void dragExit(DropTargetEvent arg0) {
		
	}

	public void dragOver(DropTargetDragEvent event) {
		
		JList list = ((JList)((DropTarget)event.getSource()).getComponent());
		int index = list.locationToIndex(event.getLocation());
		list.ensureIndexIsVisible(index-1);
		list.ensureIndexIsVisible(index+1);
	}

	public void drop(DropTargetDropEvent event) {
		JList list = ((JList)((DropTarget)event.getSource()).getComponent());
		int index = list.locationToIndex(event.getLocation());
		Method method = null;
		
			
		list.setSelectedIndex(index);
		String attributeName = "";
		AttributeData data = null;
		Connection appCon = null;
		
		if(list.getName().equalsIgnoreCase(view.SETMETHODLIST_NAME))
		{
			int j = view.getMethodAttrList().getSelectedIndex();
			Field field = (Field)((ObjectListModel)view.getMethodAttrList().getModel()).getObjectAt(j);
			method = (Method)((ObjectListModel)view.getSetMethodList().getModel()).getObjectAt(index);
			int i = model.getAttributeDataIndex((String)view.getMethodAttrList().getSelectedValue());
			if(i == -1){
				data = new AttributeData(field.getName(),
						DataTypes.getTypeConstant(CommonUtility.getPrefferedType(field, method))
											,CommonUtility.getMethodNameWithoutRType(method.toString()));
				model.addAttributeData(data);
			}else{
				data = model.getAttributeDataAt(i);
				data.setSetMethod((String)list.getSelectedValue());
				data.setType(DataTypes.getTypeConstant(CommonUtility.getPrefferedType(field, method)));
				if(data.getType() == DataTypes.BOOLEAN || data.getType() == DataTypes.BOOLEAN_OBJ){
					data.setValue(false);
				}
				model.updateTable(i, i);
			}
			((ObjectListModel)view.getMethodAttrList().getModel()).remove(view.getMethodAttrList().getSelectedIndex());
			
		}else if(list.getName().equalsIgnoreCase(view.COLUMNLIST_NAME))
		{
			int i = model.getAttributeDataIndex((String)view.getTableAttrList().getSelectedValue());
			if(i == -1){
				data = new AttributeData((String)view.getTableAttrList().getSelectedValue(), 0, "",(String)list.getSelectedValue());
				model.addAttributeData(data);
			}else{
				data = model.getAttributeDataAt(i);
				data.setColumn(list.getSelectedValue().toString());
				model.updateTable(i, i);
			}
			((ObjectListModel)view.getTableAttrList().getModel()).remove(view.getTableAttrList().getSelectedIndex());
			try {
				appCon = DBConnection.getAppConnection();
				ResultSetMetaData metaData = TestDataDAO.getTableMetaData(view.getTableTextFld().getText(),appCon);
				setColumnProperties(data.getColumn(), metaData, data);
			} catch (Exception e) {
				logger.error("In ModifyObjectAction.drop "+ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_COLUMNPROPS_EXP), e);
				JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCHING_COLUMNPROPS_EXP)
						,"Error Message",JOptionPane.ERROR_MESSAGE);
			}finally {
				DBConnection.releaseConnection(appCon);
			}
		}
		((ObjectListModel)list.getModel()).remove(index);
		
	}

	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}

//	public void stateChanged(ChangeEvent event) 
//	{
//		if(view.getDetailCheckBox()==event.getSource())
//		{
//			((ObjectListModel)view.getMethodAttrList().getModel()).stateChanged(view.getDetailCheckBox().isSelected());
//			((ObjectListModel)view.getTableAttrList().getModel()).stateChanged(view.getDetailCheckBox().isSelected());
//			((ObjectListModel)view.getSetMethodList().getModel()).stateChanged(view.getDetailCheckBox().isSelected());
////			((ObjectListModel)view.getColumnList().getModel()).stateChanged(view.getDetailCheckBox().isSelected());
//		}
//	}

	private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {
		logger.debug("Entered in ModifyObjectAction.browseBtnActionPerformed()");
		
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
		    }else{
		    	return;
		    }
		 view.getClassTextFld().setText(dataClassFilePath.replace('\\', '.'));
		 
		 //Fetching Data file Name
		 try {
			 con = DBConnection.getConnection();
			 view.getDataFileTextFld().setText(TestDataDAO.getDataFile(view.getClassTextFld().getText(),con));
			 view.getTableTextFld().setText(TestDataDAO.getDataTable(view.getClassTextFld().getText(),con));
			 DBConnection.releaseConnection(con);
			 logger
					.debug("Exited from AddTestDataAction.browseDataClassBtnActionPerformed()");
		} catch (Exception e) {
			logger.error("In AddTestDataAction.browseDataClassBtnActionPerformed "
								+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_SQLFILE), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_SQLFILE)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(con);
		}
		 
		 logger.debug("Exited from ModifyObjectAction.enclosing_method()");
	}
	
	private void prefixTextFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void packageTextFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    private void classTextFldActionPerformed(java.awt.event.ActionEvent evt) {
    	 //Fetching Data file Name
    	Connection con = null;
		 try {
			 con = DBConnection.getConnection();
			 view.getDataFileTextFld().setText(TestDataDAO.getDataFile(view.getClassTextFld().getText(),con));
			 view.getTableTextFld().setText(TestDataDAO.getDataTable(view.getClassTextFld().getText(),con));
			 DBConnection.releaseConnection(con);
			 logger
					.debug("Exited from AddTestDataAction.classTextFldActionPerformed()");
		} catch (Exception e) {
			logger.error("In AddTestDataAction.browseDataClassBtnActionPerformed "
								+ErrorMessageParser.getMessage(IExceptionCodes.FETCH_SQLFILE), e);
			JOptionPane.showMessageDialog(view,ErrorMessageParser.getMessage(IExceptionCodes.FETCH_SQLFILE)
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			DBConnection.releaseConnection(con);
		}
    }

	private Field getField(String name, Class classToRead) throws NoSuchFieldException{
		logger.debug("Entered in ModifyObjectAction.getField() Field Name "+name);
		Field field = null;
		try {
			field = classToRead.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
				field = classToRead.getSuperclass().getDeclaredField(name);
		} 
		
		logger.debug("Exited from ModifyObjectAction.getField()");
		return field;
	}
	
	private Method getMethod(String name, int type, Class classToRead) throws NoSuchMethodException{
		logger.debug("Entered in ModifyObjectAction.getMethod() Method Name "+name);
		Method method = null;
		try {
			method = classToRead.getDeclaredMethod(name, new Class[]{DataTypes.getDataType(type)});
		} catch (NoSuchMethodException e) {
				method = classToRead.getSuperclass().getDeclaredMethod(name, new Class[]{DataTypes.getDataType(type)});
		} 
		
		logger.debug("Exited from ModifyObjectAction.getMethod()");
		return method;
	}
	
    public static void main(String[] args) {
		try {
			Class c = GTestClassLoader.getInstance(ResourceManager.getProperty(IConfigurationConstant.CLASSPATH_DIR)).loadClass("com.mbi.common.data.PatientData");
			 
			c.getField("checkExclusiveUpdate");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
