/**
 * 
 */
package com.ijunit.common.constant;

/**
 * @author vcjain
 *
 */
public interface IExceptionCodes 
{
	int PARSER_EXP = 1;
	int DATAHELPER_EXCEUTION_EXP = 2;
	int DATABASE_CON_EXP = 3;
	int FILEWRITER_EXP = 4;
	int DATAOBJECT_CLASS_NOTFOUND_EXP = 5;
	int SAVEDATAXML_EXP = 6;
	int OXD_PARSE_EXP = 7;
	int OXD_FILEOPEN_EXP = 8;
	int FETCH_SQLFILE = 9;
	int FETCH_CONDITIONNAMES = 10;
	int PREVIEW_EXP = 11;
	int POPULATE_RUNTIME_VALUE_EXP = 12;
	int PREPARING_INSEERTQUERY_EXP = 13;
	int INSERTQUERY_EXECUTION_EXP = 14;
	int ADD_TESTCONDITION_EXP = 15;
	int ADD_TESTDATA_EXP = 16;
	int GENERIC_EXP = 17;
	int CON_ROLLBACK_EXP = 18;
	
	/**
	 * This exception occur while fetching testdata from Application DB at the time of running test case. 
	 */
	int GET_TESTDATA = 19;
	
	/**
	 * 
	 */
	int FETCHING_COLUMNNAMES_EXP = 20;
	int FETCHING_COLUMNPROPS_EXP = 21;
	
	int NOT_A_NUMBER_MSG = 22;
	int MAX_LENGTH_MSG = 23;
	int MAX_LENGTH_AFTER_DECIMAL_MSG = 24;
	int DATE_FORMAT_EXP = 25;
	
	int SELECT_MODIFYDATA_EXP = 26;
	int BOOLEAN_FORMAT_EXP = 27;
	
	int FILENOT_FOUND_EXP = 28;
	
	int UPDATEQUERY_EXECUTION_EXP = 29;
	int NOT_A_INTEGER = 30;
	int TIMESTAMP_FORMAT_EXP  = 31;
	int ADD_SQLFILE_EXP = 32;
	int SQLFILE_WRITE_EXP = 33;
	int FETCHING_DATACLASS = 34;
	int MAX_LENGTH_BEFORE_DECIMAL_MSG = 35;
	int ADD_SQLQUERY_EXP = 36;
	int RESOURCE_INCORRECT_FILENAME_EXP = 37;
}
