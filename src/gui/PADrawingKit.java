package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author KokHong
 *
 */
public class PADrawingKit extends JFrame
{

	private PADrawingKitButton drawingKitButton;
	private JPanel drawKitPanel;
	/**
	 * 
	 */
	private static final long serialVersionUID = -918164000427896948L;

	public PADrawingKit()
	{
//		this.setUndecorated(true);
		this.setResizable(true);
	}
	
	public void initDrawingKit()
	{
		drawKitPanel = new JPanel();
		drawingKitButton = new PADrawingKitButton(this);
		drawingKitButton.addButton();
		drawKitPanel.setBackground(new Color(40,40,40));
		this.add(drawKitPanel);
		drawKitPanel.setVisible(true);
		drawKitPanel.setSize(100, 200);		
	}
}
