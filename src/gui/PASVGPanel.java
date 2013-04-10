package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
/**
 * 
 * @author KokHong
 *
 */
public class PASVGPanel extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 940764866671091035L;
	private PAMainFrame mainFrame;
	public PASVGPanel(PAMainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	public void initPanel()
	{
		this.setBackground(new Color(38,38,38));
		this.setPreferredSize(new Dimension(mainFrame.getWidth(),mainFrame.getHeight()-50));
	}
}
