/**
 * 
 */
package com.ijunit.common.utility;

import com.ijunit.swing.component.HistoryFactory;
import com.ijunit.swing.model.HistoryModel;

/**
 * @author vcjain
 * Class act as Shutdown hook. This class
 * will be called when JVM terminates.
 */
public class ShutDownHook extends Thread {

	public void run() {
		System.out.println("ShutDown thread started");
		try {
			HistoryModel model = HistoryFactory
					.getInstance(HistoryFactory.TESTCLASS_HISTORY);
			model.serealizeHistory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
