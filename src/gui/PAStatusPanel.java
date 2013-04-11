package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
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
	private JLabel statusMsg;
	public PAStatusPanel(PAMainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	/*
	 * Initialise status panel 
	 */
	
	public void initPanel()
	{
		statusMsg = new JLabel(" ",JLabel.CENTER);
		statusMsg.setVerticalTextPosition(JLabel.BOTTOM);
		statusMsg.setHorizontalTextPosition(JLabel.CENTER);
		statusMsg.setFont(new Font("Helvetica",10, 12));
		statusMsg.setForeground(Color.WHITE);
		
		this.setBackground(new Color(77,77,77));
		this.setPreferredSize(new Dimension(mainFrame.getWidth(),20));
		this.setMaximumSize(new Dimension((int)PASystem.getScreenDimension().getWidth(),20));
		this.setMinimumSize(new Dimension(mainFrame.getWidth(),20));
		
		this.add(statusMsg);
		
		//Test
		this.setLabelText("Testing");
		
	}
	/**
	 * Set Status Message
	 * @param msg
	 */
	
	public void setLabelText(String msg)
	{
		statusMsg.setText(msg);
	}
}
