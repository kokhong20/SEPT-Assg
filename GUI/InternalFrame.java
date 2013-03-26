package GUI;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import Controller.InternalFrameAction;

public class InternalFrame extends JInternalFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4520525531144579091L;
	
	JDesktopPane desktopPane;
	SVGDisplay svgPanel;
	
	/* Internal Frame Item */
	JInternalFrame internalFrame;
	
	
	public InternalFrame(JDesktopPane desktopPane , String frameTitle)
	{
		this.desktopPane = desktopPane;
		initInternalFrame(frameTitle);
	}
	
	public InternalFrame(JDesktopPane desktopPane, SVGDisplay svgPanel , String frameTitle)
	{
		this.desktopPane = desktopPane;
		this.svgPanel = svgPanel;
		initInternalFrame(frameTitle);
		this.addActionListener();
	}
	
	private void initInternalFrame(String frameTitle)
	{
		internalFrame = new JInternalFrame(frameTitle,true,true,true,true);
		desktopPane.add(internalFrame);
	}
	
	private void addActionListener()
	{
		InternalFrameAction svgFrameAction= new InternalFrameAction(this,svgPanel);
		this.addInternalFrameListener(svgFrameAction);
	}
	
	
	

}
