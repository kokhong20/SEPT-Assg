package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		this.setUndecorated(true);
		this.setResizable(false);
	}
	
	public void initDrawingKit()
	{
		drawKitPanel = new JPanel();
		drawingKitButton = new PADrawingKitButton(this);
		drawKitPanel.setBackground(new Color(40,40,40));
		drawingKitButton.addButton();
		this.add(drawKitPanel);
		drawKitPanel.setVisible(true);
		drawKitPanel.setSize(80, 220);		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(((int)(0.25*screenSize.width))+"abc"+((int)(0.25*screenSize.height)));
		
		this.setLocation(((int)(0.05*screenSize.width)),((int)(0.2*screenSize.height)));
	}
}
