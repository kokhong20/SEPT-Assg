package Controller;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import GUI.SVGDisplay;
//import Model.SVGSaver;

public class InternalFrameAction implements InternalFrameListener
{
	private JInternalFrame internalFrame;
	//private SVGDisplay display;

	public InternalFrameAction(JInternalFrame svgInternal, SVGDisplay display) 
	{
		// TODO Auto-generated constructor stub
		this.internalFrame = svgInternal;
		//this.display = display;	
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) 
	{
		// TODO Auto-generated method stub
		Object[] choices = {"Yes", "No", "Cancel"};
		int dialogButton = JOptionPane.showOptionDialog(null, "Do you want to save before closing this frame?", "Save before closing", JOptionPane.YES_NO_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, choices, "Yes");

		if (dialogButton == 0)
		{
			System.out.println("save");
			//new SVGSaver(display.getDrawings(), display.getPath());
			this.internalFrame.dispose();
		}
		
		else if(dialogButton == 1)
		{
			System.out.println("don't save");
			this.internalFrame.dispose();
		}
		
		else
		{
			System.out.println("do nothing");
		}
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) 
	{
		// TODO Auto-generated method stub

	}	
}
