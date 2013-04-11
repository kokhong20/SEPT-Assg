package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.PASystem;
/**
 * 
 * @author KokHong
 *
 */
public class PAStatusPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7022375506076289923L;
	private PAMainFrame mainFrame;
	public PAStatusPanel(PAMainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	public void initPanel()
	{
		this.setBackground(new Color(77,77,77));
		this.setPreferredSize(new Dimension(mainFrame.getWidth(),20));
		this.setMaximumSize(new Dimension((int)PASystem.getScreenDimension().getWidth(),20));
		this.setMinimumSize(new Dimension(mainFrame.getWidth(),20));
	}
}
