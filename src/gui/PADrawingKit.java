package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.PASystem;
/**
 * 
 * @author KokHong
 *
 */
public class PADrawingKit extends JFrame
{
	private PADrawingKitButton drawingKitButton;
	private JPanel drawKitPanel;
	private static final long serialVersionUID = -918164000427896948L;

	/**
	 * constructor which set undecorated is true and resizable is false
	 */
	public PADrawingKit()
	{
		this.setUndecorated(true);
		this.setResizable(false);
	}
	
	/**
	 * initialize drawing kit
	 */
	public void initDrawingKit()
	{
		drawKitPanel = new JPanel();
		drawingKitButton = new PADrawingKitButton(this);
		drawKitPanel.setBackground(new Color(40,40,40));
		drawingKitButton.addButton();
		this.add(drawKitPanel);
		drawKitPanel.setVisible(true);
		drawKitPanel.setSize(80, 220);
		this.setLocation(((int) (0.05 * PASystem.getScreenDimension().getWidth())), 
				((int) (0.2 * PASystem.getScreenDimension().getHeight())));
	}
}
