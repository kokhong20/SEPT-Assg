package model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

/**
 * @author bryantylai
 *
 */
public class PASystem {
	
	public static String currentOS = System.getProperty("os.name");
	public static Toolkit toolkit =  Toolkit.getDefaultToolkit ();
	public static Dimension dimension = toolkit.getScreenSize();
	public static int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
	public static double screenHeight = dimension.getHeight();
	public static double screenWidth = dimension.getWidth();
	public static double screenSize = Math.sqrt((screenHeight * screenWidth));
	public static double screenRatio = (screenResolution / screenSize) + 1;
	public static final double dotsPerInch = screenResolution * screenRatio;
	
	/**
	 * Concat name of selected file with path based on current OS	 
	 * 
	 * @param path the path leading to the selected file
	 * @param selectedFile the file selected from JFileChooser
	 * @return a concatenated path
	 */
	public static String setPath(String path, File selectedFile)
	{	
		if(path.endsWith(":\\"))
		{
			path += selectedFile.getName();
		}
		else
		{
			if (currentOS.toLowerCase().contains("mac"))
			{
				path +=  "//" + selectedFile.getName();
			}
			
			else if (currentOS.toLowerCase().contains("windows"))
			{
				path +=  "\\" + selectedFile.getName();
			}
		}

		return path;
	}
}
