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
public class PAShapeAttributeBar extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1474246142443123659L;
	private PAMainFrame mainFrame;
	public PAShapeAttributeBar(PAMainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	public void initPanel()
	{	
		this.setBackground(new Color(53,53,53));
		this.setPreferredSize(new Dimension(mainFrame.getWidth(),30));
		this.setMaximumSize(new Dimension((int)PASystem.getScreenDimension().getWidth(),30));
		this.setMinimumSize(new Dimension(mainFrame.getWidth(),30));
	}

}
