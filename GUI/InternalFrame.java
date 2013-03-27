package GUI;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import Controller.IFFocusListener;
import Controller.InternalFrameAction;
import Controller.MoveAction;

public class InternalFrame extends JInternalFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4520525531144579091L;
	
	JDesktopPane desktopPane;
	
	/* SVGDisplay Item */
	SVGDisplay svgPanel;
	String frameTitle;
	
	/* Internal Frame Item */
	JInternalFrame internalFrame;
	
	/* JScrollPane Item */
	JScrollPane scrollPane;
	
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
		initScrollPane(svgPanel);
		this.setFocusable(true);
		this.requestFocus();
		this.addActionListener(svgPanel);
	}
	
	private void initInternalFrame(String frameTitle)
	{
		internalFrame = new JInternalFrame(frameTitle,true,true,true,true);
	}
	
	private void initScrollPane(SVGDisplay svgPanel)
	{
		// Adding of viewport
		JViewport port = new JViewport();
		port.setView(svgPanel);
		port.addMouseListener(new MoveAction(port, svgPanel));
		port.addMouseMotionListener(new MoveAction(port, svgPanel));
		port.addMouseWheelListener(new MoveAction(port, svgPanel));
		//
		
		scrollPane = new JScrollPane(port, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(new Rectangle(10,10,100,100));
		this.add(scrollPane, BorderLayout.CENTER);	
	}
	
	private void addActionListener(SVGDisplay svgPanel)
	{
		InternalFrameAction svgFrameAction= new InternalFrameAction(this,svgPanel);
		this.addInternalFrameListener(svgFrameAction);
		this.addFocusListener(new IFFocusListener(this));
	}
	
	public String getFrameTitle()
	{
		return frameTitle;
	}
	
	

}
