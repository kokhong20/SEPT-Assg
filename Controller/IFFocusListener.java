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
		viewMenu.buttonAvail(true);
		viewMenu.setDisplay(svgInternalFrame.getDisplay());
	}
	
	@Override
	public void focusLost(FocusEvent e) 
	{
		//viewMenu.buttonAvail(false);
	}

	public void setViewMenu(ViewMenu viewMenu)
	{
		this.viewMenu = viewMenu;
	}
}
