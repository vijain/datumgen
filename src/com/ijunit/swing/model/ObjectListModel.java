/**
 * 
 */
package com.ijunit.swing.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractListModel;

import com.ijunit.common.Logger;
import com.ijunit.common.utility.CommonUtility;
import com.ijunit.swing.action.AddObjectAction;

/**
 * @author vicky
 * 
 */
public class ObjectListModel extends AbstractListModel {

	static Logger logger = Logger.getLogger(ObjectListModel.class);

	boolean isToDisplayDetail = false;

	Class dataClass;

	// Object[] obj;
	ArrayList objList;

	Map map;

	public ObjectListModel(Object[] obj, boolean isToDisplayDetail,
			Class dataClass) {
		// this.obj = obj;
		objList = new ArrayList(Arrays.asList(obj));
		this.dataClass = dataClass;
		this.isToDisplayDetail = isToDisplayDetail;
		createMap(obj);
	}

	public void createMap(Object[] obj) {
		logger.debug("In ObjectListModel.createMap message= Siz of Object to insert "+obj.length);
		map = new HashMap();
		if (dataClass != null && dataClass.equals(Method.class)) {
			for (int i = 0; i < obj.length; i++) {
				// map.put(((Method)objList.get(i)).getName(), obj[i]);
				map.put(CommonUtility.getMethodNameWithoutRType(obj[i].toString()),
						obj[i]);
			}
		} else if (dataClass != null && dataClass.equals(Field.class)) {
			for (int i = 0; i < obj.length; i++) {
				map.put(((Field) obj[i]).getName(), obj[i]);
			}
		}else if(dataClass != null && dataClass.equals(String.class)){
			for (int i = 0; i < obj.length; i++) {
				map.put(((String) obj[i]), obj[i]);
			}
		}
		logger.debug("In ObjectListModel.createMap message= Map created "+map);
	}

	
	public Object getElementAt(int i) {
		logger.debug("Entered in ObjectListModel.getElementAt() Index At "+i);
		if (dataClass != null && dataClass.equals(Method.class)) {
			if (isToDisplayDetail) {
				return objList.get(i);
			} else {
				logger.debug("In ObjectListModel.getElementAt message= Method "+objList.get(i));
				return CommonUtility.getMethodNameWithoutRType(((Method)objList.get(i)).toString());
			}

		} else if (dataClass != null && dataClass.equals(Field.class)) {
			logger.debug("In ObjectListModel.getElementAt message= Field "+objList.get(i));
			return isToDisplayDetail ? objList.get(i)
					: ((Field) objList.get(i)).getName();
		} else {
			return objList.get(i);
		}
	}

	public int getSize() {
		return objList.size();
	}

	public void remove(int row) {
		objList.remove(row);
		fireIntervalRemoved(this, row, row + 1);
	}
	
	public void remove(Object obj){
		int row = objList.indexOf(obj);
		objList.remove(obj);
		fireIntervalRemoved(this, row, row + 1);
	}
	
	public void add(String name) {
		logger.debug("In ObjectListModel.add message= Add Object "+name +" Value from map "+map.get(name));
		logger.debug("In ObjectListModel.add message= Map is "+map);
		objList.add(map.get(name));
		fireIntervalAdded(this, objList.size(), objList.size());
		//fireContentsChanged(this, 0, getSize());
	}

	public void stateChanged(boolean isToDisplayDetail) {
		this.isToDisplayDetail = isToDisplayDetail;
		fireContentsChanged(this, 0, getSize());
	}

	

	/**
	 * @return the objList
	 */
	public ArrayList getObjList() {
		return objList;
	}

//	/**
//	 * @param objList the objList to set
//	 */
//	public void setObjList(ArrayList objList) {
//		this.objList = objList;
//	}

	/**
	 * This method will return name of method without return type.
	 * 
	 * @param methodName
	 * @return
	 */
//	private String getMethodNameWithRType(String methodName) {
//		int index = methodName.trim().indexOf(dataClass);
//		if (index != -1) {
//			methodName = methodName.substring(index);
//		}
//		return methodName;
//	}

	public Object getObjectAt(int index){
		if (dataClass != null && dataClass.equals(Method.class)) {
			return objList.get(index);
		}else if (dataClass != null && dataClass.equals(Field.class)) {
			return objList.get(index);
		}else{
			return null;
		}
	}
	
}
