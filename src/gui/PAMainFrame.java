package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import model.PASystem;
/**
 * 
 * @author KokHong
 *
 */
public class PAMainFrame extends JInternalFrame
{
	private PAStatusPanel statusPanel;
	private PASVGPanel svgPanel;
	private PAShapeAttributeBar attributeBar;
	private JPanel mainPanel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6966744942640238103L;
	
	public PAMainFrame()
	{
		setAttributes();
		initMainPanel();
		setFrameLayout();
	}
	
	/*
	 * Initialize the instance variables
	 */
	public void initMainPanel()
	{
		statusPanel = new PAStatusPanel(this);
		svgPanel = new PASVGPanel(this);
		attributeBar = new PAShapeAttributeBar(this);
		mainPanel = new JPanel();
		
		mainPanel.setPreferredSize(new Dimension(800,600));
		
		attributeBar.initPanel();
		svgPanel.initPanel();
		statusPanel.initPanel();	
		
		//Testing
		
	}
	
	/*
	 * Set the attributes of the Internal Frame
	 */
	public void setAttributes()
	{
		
		this.setTitle("New SVG");
		this.setResizable(true);
		this.setClosable(true);
		this.setMaximizable(true);
		
		
		this.setBackground(new Color(38,38,38));
		this.setVisible(true);
		this.setSize(800, 600);
		
		//Set location based on user's computer resolution
		this.setLocation(((int) (0.2 * PASystem.getScreenDimension().getWidth())), 
				((int) (0.1 * PASystem.getScreenDimension().getHeight())));
	}
	
	/*
	 * Set mainPanel layout
	 * 
	 */
	
	public void setFrameLayout()
	{
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		// Create a sequential group for the horizontal axis.

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(attributeBar)
				.addComponent(svgPanel)
				.addComponent(statusPanel));
	
		layout.setHorizontalGroup(hGroup);

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(attributeBar));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(svgPanel));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(statusPanel));
		
		layout.setVerticalGroup(vGroup);

		this.add(mainPanel);
		
	}
	
	
	
}
