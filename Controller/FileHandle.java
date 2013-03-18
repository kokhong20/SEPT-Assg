package Controller;

import java.io.File;
import Model.SVGReader;

public class FileHandle 
{
	// handle command line argument
	public static void cmdLine(String []s)
	{
		SVGReader reader = new SVGReader();
		reader.setDoc(s[0]);
	}
	
	// handle Open button to open file
	public static void menuOpen(String path, File selectedFile)
	{	
		if(path.endsWith(":\\"))
		{
			path += selectedFile.getName();
		}
		else
		{
			String nameOS = "os.name";
			
			if (System.getProperty(nameOS).equals("Mac OS X"))
			{
				path +=  "//" + selectedFile.getName();
			}
			
			else
			{
				path +=  "\\" + selectedFile.getName();
			}
		}
		
		SVGReader reader = new SVGReader();
		reader.setDoc(path);
	}
	

	public static String setPath(String path, File selectedFile)
	{	
		if(path.endsWith(":\\"))
		{
			path += selectedFile.getName();
		}
		else
		{
			String nameOS = "os.name";
			
			if (System.getProperty(nameOS).equals("Mac OS X"))
			{
				path +=  "//" + selectedFile.getName();
			}
			
			else
			{
				path +=  "\\" + selectedFile.getName();
			}
		}

		return path;
	}
}
