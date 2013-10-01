/**
 * 
 */
package com.ijunit.common.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * A POJO object for holding a test condition attributes. A Test
 * condition represent a test case or a scenario for testing a 
 * functionality. It holds class name whose functionality is
 * being tested and test data which are used to test functionality.  
 * 
 * @author Vikash Jain 
 */
public class TestCondition {
	/**
	 * Member variable for ID.
	 */
	private int ID;

	/**
	 * Member variable for test class.
	 */
	private String testClass;

	/**
	 * Member variable for condition name.
	 */
	private String conditionName;

	/**
	 * Member variable for condition description.
	 */
	private String conditionDesc;

	/**
	 * Member variable for collection of test data object.
	 */
	private Vector testDataVec;

	/**
	 * Default Constructor.
	 */
	public TestCondition() {
	}

	/**
	 * Constructor.
	 * 
	 * @param ID
	 *            - unique value for identification. This value will be
	 *            auto generated while creating test condition.
	 * @param testClass
	 *            - fully qualified name of Class for which test cases are
	 *            prepared
	 * @param conditionName
	 *            - a unique name to identify a condition
	 * @param conditionDesc
	 *            - description about condition
	 */
	public TestCondition(int ID, String testClass, String conditionName,
			String conditionDesc) {
		this.ID = ID;
		this.testClass = testClass;
		this.conditionName = conditionName;
		this.conditionDesc = conditionDesc;
	}

	/**
	 * Method to represent object in string form
	 */
	public String toString() {
		return "ID=" + ID + "\n" + "TestClass =" + testClass + "\n"
				+ "Condition Name=" + conditionName + "\n" + "Condition Desc="
				+ conditionDesc;
	}

	/**
	 * @return the conditionDesc
	 */
	public String getConditionDesc() {
		return conditionDesc;
	}

	/**
	 * @param conditionDesc
	 *            the conditionDesc to set
	 */
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}

	/**
	 * @return the conditionName
	 */
	public String getConditionName() {
		return conditionName;
	}

	/**
	 * @param conditionName
	 *            the conditionName to set
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param id
	 *            the iD to set
	 */
	public void setID(int id) {
		ID = id;
	}

	/**
	 * @return the testClass
	 */
	public String getTestClass() {
		return testClass;
	}

	/**
	 * @param testClass
	 *            the testClass to set
	 */
	public void setTestClass(String testClass) {
		this.testClass = testClass;
	}

	/**
	 * @return the testDataVec
	 */
	public Vector getTestDataVec() {
		return testDataVec;
	}

	/**
	 * @param testDataVec
	 *            the testDataVec to set
	 */
	public void setTestDataVec(Vector testDataVec) {
		this.testDataVec = testDataVec;
	}
}
