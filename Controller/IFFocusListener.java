package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import GUI.InternalFrame;

public class IFFocusListener implements FocusListener
{

	InternalFrame svgInternalFrame;
	
	public IFFocusListener(InternalFrame svgInternalFrame)
	{
		this.svgInternalFrame = svgInternalFrame;
	}
	@Override
	public void focusGained(FocusEvent e) 
	{
		System.out.println(svgInternalFrame.getFrameTitle()+"Focus Gained");
	}
	@Override
	public void focusLost(FocusEvent e) 
	{
		System.out.println(svgInternalFrame.getFrameTitle()+"Focus Lost");
	}

}
