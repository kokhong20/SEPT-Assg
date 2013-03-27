package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import GUI.InternalFrame;
import GUI.ViewMenu;

public class IFFocusListener implements FocusListener
{

	InternalFrame svgInternalFrame;
	ViewMenu viewMenu;
	
	public IFFocusListener(InternalFrame svgInternalFrame)
	{
		this.svgInternalFrame = svgInternalFrame;
	}
	
	@Override
	public void focusGained(FocusEvent e) 
	{
		System.out.println(svgInternalFrame.getFrameTitle()+"Focus Gained");
		viewMenu.buttonAvail(true);
		viewMenu.setDisplay(svgInternalFrame.getDisplay());
	}
	
	@Override
	public void focusLost(FocusEvent e) 
	{
		System.out.println(svgInternalFrame.getFrameTitle()+"Focus Lost");
		//viewMenu.buttonAvail(false);
	}

	public void setViewMenu(ViewMenu viewMenu)
	{
		this.viewMenu = viewMenu;
	}
}
