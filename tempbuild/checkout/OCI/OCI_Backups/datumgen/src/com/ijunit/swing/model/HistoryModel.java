/**
 * 
 */
package com.ijunit.swing.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.common.utility.FileWriterFactory;
import com.ijunit.common.utility.reader.FileReader;
import com.ijunit.common.utility.writer.FileWriter;
import com.ijunit.swing.component.HistoryFactory;
import com.ijunit.swing.component.HistoryTextField;

/**
 * @author vcjain
 * 
 */
public class HistoryModel extends TreeSet {

	static Logger logger = Logger.getLogger(HistoryModel.class);
	
	File serealizedFile = null;
	public HashSet frequentUsedHistorySet = new HashSet();

	/**
	 * Constructor.
	 * @param capacity - intial capacity
	 * @param fileName - file to be serealized
	 */
	public HistoryModel(int capacity, String fileName) {
		super();
		serealizedFile = new File(fileName);
		populateHistory();
	}

	/**
	 * Method will read the history from file system and 
	 * pouplate the HistoryModel.
	 */
	public void populateHistory() {
		try {
			ArrayList<String> list = FileReader.getLines(serealizedFile
					.toString());
			for (Iterator it = list.iterator(); it.hasNext();) {
				String string = (String) it.next();
				if(!(string.trim().equals(""))){
					add(string);
				}
			}
		} catch (IJunitException e) {
			logger.debug(" In HistoryModel.populateHistory : Error in reading History File :"
					+ serealizedFile.toString());
		}
	}

	/**
	 * Method will persist the history in file system. 
	 * @throws Exception
	 */
	public void serealizeHistory() throws Exception {
		FileWriter writer = FileWriterFactory
				.getFileWriter(IFileConstant.BASICFILE_WRITER);
		writer.appendToFile(getAsString(), serealizedFile);
	}
	
	/**
	 * Method represent the HistoryModel as String 
	 */
	public String getAsString() {
		logger.debug(" Entered In HistoryModel.getAsString : ");
		int count = 0;
		StringBuffer buff = new StringBuffer();
		Object[] historyArray = frequentUsedHistorySet.toArray();
		logger.debug(" In HistoryModel.getAsString : Size of frequent history: "+historyArray.length);
		//Saving most used first
		for (int i = historyArray.length-1; i >= 0 ; i--) {
			String value = (String) historyArray[i];
			logger.debug(" In HistoryModel.getAsString : Iterart on Frequent history: "+value);
			buff.append(value).append("\n");
			count++;
			remove(value);
		}
		
		//Saving other history
		for (Iterator it = this.iterator(); it.hasNext();) {
			String value = (String) it.next();
			logger.debug(" In HistoryModel.getAsString : Iterart on other history: "+value);
			buff.append(value).append("\n");
			count++;
			if(count > HistoryFactory.MAX_HISTORY_ITEM){
				break;
			}
		}
		logger.debug(" Exited from HistoryModel.getAsString : String : "+buff.toString());
		return buff.toString();
	}

	public void addToHistory(String value){
		
    	logger.debug("In HistoryModel adding value to history: "+value);
    	if(!(value.trim().equals(""))){
	    	add(value);
	    	addFrequentHistory(value);
    	}
    }
	
	public void addFrequentHistory(String value){
		if(!(value.trim().equals(""))){
			logger.debug("In HistoryModel addFrequentHistory adding value to history: "+value);
			frequentUsedHistorySet.add(value);	
		}
		
	}
}
