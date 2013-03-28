package Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;

import GUI.InternalFrame;
import GUI.SVGDisplay;
import GUI.ViewMenu;
import Model.modelMain;

public class CmdArgumentHandle 
{
	private JDesktopPane desktopPane;
	private modelMain mMain;
	private ViewMenu viewMenu;
	
	public CmdArgumentHandle(JDesktopPane desktopPane,ViewMenu viewMenu,modelMain mMain)
	{
		this.desktopPane = desktopPane;
		this.mMain = mMain;
		this.viewMenu = viewMenu;
	}
	
	public void initSVGDisplay()
	{
		SVGRender render = new SVGRender(mMain.getCmdFileName());
		SVGDisplay display = new SVGDisplay(render);
		display.setPreferredSize(display.getRender().getPreferredSize());
		InternalFrame svgInternal = new InternalFrame(this.desktopPane,display,mMain.getCmdFileName());
		svgInternal.setViewMenu(viewMenu);
		svgInternal.pack();
		this.desktopPane.add(svgInternal);
		svgInternal.setVisible(true);
		
		
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		if((toolkit.getScreenSize().height <= display.getPreferredSize().height)
				|| (toolkit.getScreenSize().width <= display.getPreferredSize().width))
		{
				try {
					svgInternal.setMaximum(true);
				} catch (PropertyVetoException pve) {
					// TODO Auto-generated catch block
					pve.printStackTrace();
				}
		}
		
		else if(svgInternal.getSize().equals(new Dimension(0,0)))
		{
			svgInternal.setSize(500,500);
		}
		
		else
		{
			svgInternal.setSize(display.getPreferredSize());	
		}
	}
}
