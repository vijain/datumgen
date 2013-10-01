/**
 * 
 */
package com.ijunit.common.utility.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.ijunit.common.exception.IJunitException;

/**
 * @author vcjain
 * 
 */
public class FileReader {

	File file = null;
	java.io.FileReader in = null;
	BufferedReader bReader = null;

	/**
	 * Constructor.
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public FileReader(String fileName) throws FileNotFoundException {
		file = new File(fileName);
		in = new java.io.FileReader(fileName);
		bReader = new BufferedReader(in);
	}

	/**
	 * Method to read file line by line
	 * @param fileName
	 * @return
	 * @throws IJunitException
	 */
	public static ArrayList<String> getLines(String fileName) throws IJunitException{
		ArrayList list = new ArrayList();
		try{
			FileReader fr = new FileReader(fileName);
			String nextLine = null;
			while((nextLine = fr.bReader.readLine())!= null){
				list.add(nextLine);
			}
		}catch(FileNotFoundException fnfException){
			//fnfException.printStackTrace();
			throw new IJunitException("History file not found", fnfException);
		} catch (IOException ioException) {
			//ioException.printStackTrace();
			throw new IJunitException("History file not found", ioException);
		}finally{
			return list;
		}
	}
}
