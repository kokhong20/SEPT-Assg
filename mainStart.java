
import java.awt.Dimension;
import java.awt.Toolkit;
import GUI.DesktopPane;
import Model.modelMain;

public class mainStart 
{
	public static String[]s;
	
	public static void main(String args [])
	{
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		  
		DesktopPane dp = new DesktopPane();
		dp.setVisible(true);
		dp.setSize(dim.width,dim.height);
		
		if (args.length != 0)
		{
			s = args;
			modelMain.cmdLine(s);
		}
	}

}
