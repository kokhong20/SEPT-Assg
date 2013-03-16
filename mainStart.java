import Controller.FileHandle;
import GUI.DesktopPane;
import Model.SVGReader;

public class mainStart 
{
	public static String[]s;
	
	public static void main(String args [])
	{
		DesktopPane dp = new DesktopPane();
		dp.setVisible(true);
		dp.setSize(600,600);
		
		if (args.length != 0)
		{
			s = args;
			FileHandle.cmdLine(s);
		}
	}

}
