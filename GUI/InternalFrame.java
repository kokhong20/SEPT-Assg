package GUI;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import Controller.IFFocusListener;
import Controller.InternalFrameAction;

public class InternalFrame extends JInternalFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4520525531144579091L;
	
	JDesktopPane desktopPane;
	SVGDisplay svgPanel;
	String frameTitle;
	
	/* Internal Frame Item */
	JInternalFrame internalFrame;
	
	
	public InternalFrame(JDesktopPane desktopPane , String frameTitle)
	{
		this.desktopPane = desktopPane;
		this.frameTitle = frameTitle;
		initInternalFrame(frameTitle);
	}
	
	public InternalFrame(JDesktopPane desktopPane, SVGDisplay svgPanel , String frameTitle)
	{
		this.desktopPane = desktopPane;
		this.svgPanel = svgPanel;
		this.frameTitle = frameTitle;
		initInternalFrame(frameTitle);
		this.setFocusable(true);
		this.requestFocus();
		this.addActionListener();
		this.addFocusListener(new IFFocusListener(this));
		System.out.println("abccc");
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
	
	public String getFrameTitle()
	{
		return frameTitle;
	}
	
	

}
