package Controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import GUI.SVGRender;

public class ZoomInOutAction implements ActionListener
{
	private SVGRender svgRenderPanel;
	private Component [] itemArray;
	
	public ZoomInOutAction(SVGRender panel, JMenu menu)
	{
		this.svgRenderPanel = panel;
		this.itemArray = menu.getMenuComponents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == itemArray[0])
		{
			svgRenderPanel.setZoomScale(svgRenderPanel.getZoomScale()*1.1);
			System.out.println("zoom IN!");
			svgRenderPanel.repaint();
		}
		
		else if (e.getSource() == itemArray[1])
		{
			svgRenderPanel.setZoomScale(svgRenderPanel.getZoomScale()*0.9);
			System.out.println("zoom Out!");
			svgRenderPanel.repaint();
		}
		
		else if(e.getSource() == itemArray[2])
		{
			svgRenderPanel.setZoomScale(1);
			svgRenderPanel.repaint();
		}
		
		else if(e.getSource() == itemArray[3])
		{
			svgRenderPanel.setXPosition(0);
			svgRenderPanel.setYPosition(0);
			svgRenderPanel.repaint();
		}	
	}
}
