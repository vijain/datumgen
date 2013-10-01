/**
 * 
 */
package com.ijunit.swing.component;

import com.ijunit.common.constant.IFileConstant;
import com.ijunit.resource.ResourceManagerBase;
import com.ijunit.swing.model.HistoryModel;

/**
 * @author vcjain
 * 
 */
public class HistoryFactory {

	public static final int TESTCLASS_HISTORY = 1;
	public static final int TESTDATACLASS_HISTORY = 2;

	public static int MAX_HISTORY_ITEM = 50;
	public static int MAX_POPUP_ITEM = 8;

	private static HistoryModel testDataClassModel = null;

	private static HistoryModel testClassModel = null;

	private static String testClassFileName = ResourceManagerBase.datumgenHistoryPath
			+ ResourceManagerBase.FILE_SEPRATOR + IFileConstant.TESTCLASS_HISTORY_FILE;
	private static String testDataClassFileName = ResourceManagerBase.datumgenHistoryPath
			+ ResourceManagerBase.FILE_SEPRATOR + IFileConstant.TESTDATA_HISTORY_FILE;

	public static HistoryModel getInstance(int category) {
		switch (category) {
		case TESTCLASS_HISTORY:
			if (testClassModel == null) {
				testClassModel = new HistoryModel(MAX_HISTORY_ITEM,
						testClassFileName);
			}
			return testClassModel;
		case TESTDATACLASS_HISTORY:
			if (testDataClassModel == null) {
				testDataClassModel = new HistoryModel(MAX_HISTORY_ITEM,
						testDataClassFileName);
			}
			return testDataClassModel;
		default:
			return null;
		}
	}
}
