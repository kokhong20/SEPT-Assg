package Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;

import GUI.InternalFrame;
import GUI.SVGDisplay;
import Model.modelMain;

public class CmdArgumentHandle 
{
	private JDesktopPane desktopPane;
	private modelMain mMain;
	
	public CmdArgumentHandle(JDesktopPane desktopPane,modelMain mMain)
	{
		this.desktopPane = desktopPane;
		this.mMain = mMain;
	}
	
	public void initSVGDisplay()
	{
		System.out.println("abcadasd"+mMain.getCmdFileName());
		SVGDisplay display = new SVGDisplay(new SVGRender(mMain.getCmdFileName()));
		display.setPreferredSize(display.getRender().getPreferredSize());
		InternalFrame svgInternal = new InternalFrame(this.desktopPane,display,mMain.getCmdFileName());
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
			svgInternal.setSize(display.getPreferredSize());
		
	}


}
