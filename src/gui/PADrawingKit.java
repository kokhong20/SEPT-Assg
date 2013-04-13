package gui;

import java.awt.Color;

import javax.swing.JPanel;

import model.PASystem;
/**
 * 
 * @author KokHong
 *
 */
public class PADrawingKit extends JPanel
{

	private PADrawingItems drawingKitButton;
	/**
	 * 
	 */
	private static final long serialVersionUID = -918164000427896948L;

	public PADrawingKit()
	{
		initDrawingKit();
		setPanelAttribues();
	}
	
	/*
	 * Initialize the drawkingKit panel
	 */
	public void initDrawingKit()
	{
		drawingKitButton = new PADrawingItems(this);
		drawingKitButton.initPanel();
	}
	
	/*
	 * Set panel attributes
	 */
	private void setPanelAttribues()
	{
		this.setLayout(null);
		this.setBackground(new Color(40,40,40));
		this.setVisible(true);
		this.setSize(80, 240);
		//Set location based on user's computer resolution
		this.setLocation(((int) (0.05 * PASystem.getScreenDimension().getWidth())), 
				((int) (0.2 * PASystem.getScreenDimension().getHeight())));
	}
}
