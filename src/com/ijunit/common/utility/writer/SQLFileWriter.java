/**
 * 
 */
package com.ijunit.common.utility.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.constant.IFileConstant;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.resource.ResourceManager;

/**
 * @author vcjain
 *
 */
public class SQLFileWriter extends FileWriter {

	static String endTagTemplate = "--CONDITION_NAME$END$";
	static String startTagTemplate = "--CONDITION_NAME$START$";
	/* (non-Javadoc)
	 * @see com.ijunit.common.utility.writer.FileWriter#write(java.lang.Object, java.io.File)
	 */
	@Override
	public void write(Object obj, File file) throws IJunitException {
		FileReader fileReader = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;
		File srcFile = null;
		File destFile = null;
		
		Map<String, String> map = (Map)obj;
		
		String fileName = file.getName();
		String queryToInsert = map.get(IFileConstant.QUERYTOINSERT);
		String condition = map.get(IFileConstant.CONDITIONNAME);
		String str = "";
		boolean conditionFound = false;

		try{
			if(!file.exists()){
				file.createNewFile();
			}
			File tempFile = ResourceManager.getFile("temp.sql");
			if(tempFile.exists()){
				tempFile.delete();
			}
			file.renameTo(tempFile);
			
			srcFile = ResourceManager.getFile("temp.sql");
			destFile = ResourceManager.getFile(fileName);
			destFile.createNewFile();
			
			fileReader = new FileReader(srcFile);
			writer = new BufferedWriter(new java.io.FileWriter(destFile));
			reader = new BufferedReader(fileReader);
			
			String searchEndCondition = endTagTemplate+condition;
			//If condition exsit in file.
			while((str = reader.readLine()) != null){
				if(str.equalsIgnoreCase(searchEndCondition) ){
					conditionFound = true;
					writer.write("\t\t"+queryToInsert.replace('\n', ' ')+";\n");
				}
					writer.write(str+"\n");
			}
			
			// If condition is new and does not exist in file.
			if(!conditionFound){
				writer.write(startTagTemplate+condition+"\n");
				writer.write("\t\t"+queryToInsert.replace('\n', ' ')+"\n");
				writer.write(endTagTemplate+condition+"\n");
			}
			writer.flush();
		}catch(IOException ioException){
			ioException.printStackTrace();
			logger.error("In SQLFileWriter.write "+ioException.getMessage(), ioException);
			throw new IJunitException(ioException.getMessage(),ioException);
		}finally{
			try{
				writer.close();
				reader.close();
				srcFile.delete();
			}catch(Exception e){
				logger.error("In SQLFileWriter.write "+ErrorMessageParser.getMessage(IExceptionCodes.GENERIC_EXP), e);
			}
		}
	}

	public static void main(String[] args) throws IJunitException {
		SQLFileWriter writer = new SQLFileWriter();
		String query = "INSERT INTO Patient124"+"\n"+"values";
		Map<String, String> map = new HashMap<String,String>();
		map.put(IFileConstant.QUERYTOINSERT, query);
		map.put(IFileConstant.CONDITIONNAME, "MODI");
		writer.write(map, ResourceManager.getFile("Patient.sql"));
	}
	
}
