package utils;

/**
 * author Natalia Dyubankova
 * 
 * version 15/12/2004
 */

import java.io.File;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class LoadProperties 
{
	private Properties properties = null;

	public LoadProperties(File f) throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(f));
	}

	public String getProperty(String key) 
	{
		return properties.getProperty(key);
	}

	public void setProperty(String key, String value) 
	{
		properties.setProperty(key, value);
	}

	public void list(PrintStream s) 
	{
		properties.list(s);
	}

	public void listWeb(PrintWriter s) 
	{
		properties.list(s);
	}

	public Enumeration elements() 
	{
		return properties.elements();
	}

	public Enumeration keys() 
	{
		return properties.keys();
	}

	public static LoadProperties getPropertiesFile(String file) 
	{
		LoadProperties loadProperties = null;
		try 
		{
			File f = new File(file);
			if (f.exists()) 
			{
				loadProperties = new LoadProperties(f);
			} else 
			{
				System.out.println("File " + f + " does not exist!");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		} 
			return loadProperties;
	}
}