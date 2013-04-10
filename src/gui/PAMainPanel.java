package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
/**
 * 
 * @author KokHong
 *
 */
public class PAMainPanel extends JPanel
{
	private PAStatusPanel statusPanel;
	private PASVGPanel svgPanel;
	private PAShapeAttributeBar attributeBar;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6966744942640238103L;
	
	public PAMainPanel()
	{
		
	}
	
	/*
	 * Initialize the instance variables
	 */
	public void initMainPanel()
	{
		statusPanel = new PAStatusPanel(this);
		svgPanel = new PASVGPanel(this);
		attributeBar = new PAShapeAttributeBar(this);
	}
	
	public void setPanelAtrributes()
	{
		this.setBackground(new Color(40,40,40));
		this.setVisible(true);
		this.setSize(80, 220);
		//Set location based on user's computer resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int)(0.05*screenSize.width)),((int)(0.2*screenSize.height)));
	}
	
	
	
}
