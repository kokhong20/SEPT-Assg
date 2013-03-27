
import java.awt.Dimension;
import java.awt.Toolkit;

import Controller.CmdArgumentHandle;
import GUI.DesktopPane;
import Model.modelMain;

public class mainStart 
{	
	public static void main(String args [])
	{
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		  
		DesktopPane dp = new DesktopPane();
		dp.setVisible(true);
		dp.setSize(dim.width,dim.height);
		
		if (args.length != 0)
		{
			modelMain mMain = new modelMain();
			mMain.setCmdFileName(args[0]);
			CmdArgumentHandle cmdArgHandle = new CmdArgumentHandle(dp.getDesktopPane(),mMain);
			cmdArgHandle.initSVGDisplay();
		}
	}

}
