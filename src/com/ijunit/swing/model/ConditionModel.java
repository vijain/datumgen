/**
 * 
 */
package com.ijunit.swing.model;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;

/**
 * @author vcjain
 *
 */
public class ConditionModel extends DefaultComboBoxModel {
    	
    	Map dataMap = null;
    	
    	public ConditionModel(Map dataMap) {
			this.dataMap = dataMap;
			Set keySet = dataMap.keySet();
			for (Iterator iter = keySet.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				addElement(element);
			}
		}
    	
    	public void add(String key, String value){
    		dataMap.put(key, value);
    		addElement(key);
    	}
    	
    	public void remove(String key){
    		dataMap.remove(key);
    		removeElement(key);
    	}
    	
    	public String getDescription(String key){
    		return dataMap.get(key) != null ? dataMap.get(key).toString():"";
    	}
}
