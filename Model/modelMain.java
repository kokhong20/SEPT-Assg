package Model;

import java.io.File;

public class modelMain
{
	String cmdFileName;
	
	public modelMain()
	{

	}

	// handle command line argument

	public String setPath(String path, File selectedFile)
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


	// handle command line argument
	public String getCmdFileName() 
	{
		return cmdFileName;
	}

	public void setCmdFileName(String cmdFileName) 
	{
		this.cmdFileName=cmdFileName;
	}
}
