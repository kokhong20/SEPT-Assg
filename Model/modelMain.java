package Model;

import java.io.File;

public class modelMain
{
	public modelMain()
	{

	}

	// handle command line argument
	public static void cmdLine(String []s)
	{
		SVGReader reader = new SVGReader();
		reader.setDoc(s[0]);
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
