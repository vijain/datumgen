package com.ijunit.common.utility;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IConfigurationConstant;
import com.ijunit.resource.ResourceManager;

/**
 * Represent a datumgen classloader
 * 
 * @author vcjain
 */
public class GTestClassLoader extends ClassLoader {

	private static ArrayList<String> rootDirList = new ArrayList<String>();
	private static GTestClassLoader loader = null;

	static Logger logger = Logger.getLogger(GTestClassLoader.class);

	/**
	 * Default constructor
	 */
	public GTestClassLoader() {
		rootDirList.add(ResourceManager
				.getProperty(IConfigurationConstant.CLASSPATH_DIR));
		rootDirList.add(ResourceManager
				.getProperty(IConfigurationConstant.TESTCLASSES_DIR));
	}

	/**
	 * Constructor
	 * 
	 * @param parent
	 *            - parent classloader
	 */
	public GTestClassLoader(ClassLoader parent) {
		super(parent);
		rootDirList.add(ResourceManager
				.getProperty(IConfigurationConstant.CLASSPATH_DIR));
		rootDirList.add(ResourceManager
				.getProperty(IConfigurationConstant.TESTCLASSES_DIR));
	}

	/**
	 * Implements Singleton pattern to obtain classloader instance.
	 * 
	 * @param rootDir
	 *            - classpath directory
	 * @return - classloader instance
	 * @deprecated
	 */
	public static synchronized GTestClassLoader getInstance(String rootDir) {
		if (loader == null) {
			loader = new GTestClassLoader();
		}
		return loader;
	}

	/**
	 * Implements Singleton pattern to obtain classloader instance.
	 * 
	 * @param parent
	 *            - parent classloader
	 * @return - classloader instance
	 */
	public static synchronized GTestClassLoader getInstance(ClassLoader parent) {
		if (loader == null) {
			loader = new GTestClassLoader(parent);
		}
		return loader;
	}

	/**
	 * Implements Singleton pattern to obtain classloader instance.
	 * 
	 * @return - classloader instance
	 */
	public static synchronized GTestClassLoader getInstance() {
		if (loader == null) {
			loader = new GTestClassLoader();
		}
		return loader;
	}

	@Override
	/**
	 * Override method for ClassLoader class. This method provides
	 * functionality to load a class file.
	 * @param name - resource name 
	 * @return - class definition of resource
	 */
	protected Class findClass(String name) throws ClassNotFoundException {
		logger.debug("Entered in GTestClassLoader.findClass()");

		Class c = null;
		String filename = name.replace('.', File.separatorChar) + ".class";

		try {
			byte data[] = loadClassData(filename);
			c = defineClass(name, data, 0, data.length);
			if (c == null)
				throw new ClassNotFoundException(name);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ClassNotFoundException("Error reading file: " + filename);
		}

		logger.debug("Exited from GTestClassLoader.findClass() Value " + c);
		return c;
	}

	/**
	 * Override method for ClassLoader class. This method provides functionality
	 * to load a class file.
	 * 
	 * @param name
	 *            - resource name
	 * @return - byte array of class data
	 */
	private byte[] loadClassData(String filename) throws IOException {
		logger.debug("Entered in GTestClassLoader.loadClassData()");

		File f = null;
		int count = 0;
		for (Iterator it = rootDirList.iterator(); it.hasNext();) {
			String path = (String) it.next();
			f = new File(path+File.separator+filename);
			logger.debug("In GTestClassLoader.loadClass message: Try : "+count++ +" File to be loaded :"+  f);
			if(f.exists()){
				break;
			}
			
		}
		/*
		 * String filename1 = rootDirArray[1]+File.separator+filename.trim();
		 * String dir = filename1.substring(0,
		 * filename1.lastIndexOf(File.separator)); String [] filelIst = new
		 * File(dir).list(); if(filelIst != null){ for (int i = 0; i <
		 * filelIst.length; i++) {
		 * logger.debug("In GTestClassLoader.loadClass File"
		 * +i+": "+filelIst[i]); System.out.println("File"+i+": "+filelIst[i] );
		 * } }else{
		 * logger.debug("In GTestClassLoader.loadClass File directory is empty"
		 * ); }
		 */
		logger.debug("In GTestClassLoader.loadClass Is file exist = "
				+ f.exists());
		int size = (int) f.length();
		byte buff[] = new byte[size];
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);

		dis.readFully(buff);
		dis.close();

		return buff;
	}
}
