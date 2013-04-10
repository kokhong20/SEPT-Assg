package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
/**
 * 
 * @author KokHong
 *
 */
public class PADrawingKit extends JPanel
{

	private PADrawingKitButton drawingKitButton;
	/**
	 * 
	 */
	private static final long serialVersionUID = -918164000427896948L;

	public PADrawingKit()
	{
		initDrawingKit();
		setPanelAttribues();
	}
	
	public void initDrawingKit()
	{
		drawingKitButton = new PADrawingKitButton(this);
		drawingKitButton.addButton();
	}
	
	public void setPanelAttribues()
	{
		this.setLayout(null);
		this.setBackground(new Color(40,40,40));
		this.setVisible(true);
		this.setSize(80, 220);
		//Set location based on user's computer resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int)(0.05*screenSize.width)),((int)(0.2*screenSize.height)));
	}
}
