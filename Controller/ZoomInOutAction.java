package Controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import GUI.SVGDisplay;

public class ZoomInOutAction implements ActionListener
{
	private SVGDisplay svgDisplayPanel;
	private Component [] itemArray;
	
	public ZoomInOutAction(JMenu menu)
	{
		this.itemArray = menu.getMenuComponents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == itemArray[0])
		{
			svgDisplayPanel.setZoomScale(svgDisplayPanel.getZoomScale()*1.1);
			System.out.println("zoom IN!");
			svgDisplayPanel.repaint();
		}
		
		else if (e.getSource() == itemArray[1])
		{
			svgDisplayPanel.setZoomScale(svgDisplayPanel.getZoomScale()*0.9);
			System.out.println("zoom Out!");
			svgDisplayPanel.repaint();
		}
		
		else if(e.getSource() == itemArray[2])
		{
			svgDisplayPanel.setZoomScale(1);
			svgDisplayPanel.repaint();
		}
		
		else if(e.getSource() == itemArray[3])
		{
			svgDisplayPanel.setXPosition(0);
			svgDisplayPanel.setYPosition(0);
			svgDisplayPanel.setLocation(new Point((int)svgDisplayPanel.getXPosition(), (int)svgDisplayPanel.getYPosition()));
			svgDisplayPanel.repaint();
		}	
	} 
	
	public void setParentPane(SVGDisplay panel)
	{
		this.svgDisplayPanel = panel;
	}
}
