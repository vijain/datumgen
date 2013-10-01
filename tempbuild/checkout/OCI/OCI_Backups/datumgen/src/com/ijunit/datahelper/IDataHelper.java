/**
 * 
 */
package com.ijunit.datahelper;

import java.sql.Connection;

/**
 * @author vcjain
 *
 */
public interface IDataHelper {

	public  Object execute(String OID) throws Exception;
	public  Object execute(String OID, Connection con) throws Exception;
	
}
