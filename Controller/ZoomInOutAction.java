package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import GUI.SVGRender;


public class ZoomInOutAction implements ActionListener
{
	private SVGRender svgRenderPanel;
	private JMenuItem zoomIn;
	private JMenuItem zoomOut;
	private JMenuItem size;
	private JMenuItem position;
	
	public ZoomInOutAction(JPanel panel,JMenuItem zoomIn, JMenuItem zoomOut,JMenuItem size,JMenuItem position)
	{
		this.svgRenderPanel = (SVGRender) panel;
		this.zoomIn = zoomIn;
		this.zoomOut = zoomOut;
		this.size = size;
		this.position = position;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()== zoomIn)
		{
			svgRenderPanel.setZoomScale(svgRenderPanel.getZoomScale()*1.1);
			System.out.println("zoom IN!");
			svgRenderPanel.repaint();
		}
		else if (e.getSource() == zoomOut)
		{
			svgRenderPanel.setZoomScale(svgRenderPanel.getZoomScale()*0.9);
			System.out.println("zoom Out!");
			svgRenderPanel.repaint();
		}
		else if(e.getSource() == size)
		{
			svgRenderPanel.setZoomScale(1);
			svgRenderPanel.repaint();
		}
		else if(e.getSource()==position)
		{
			svgRenderPanel.setXPosition(0);
			svgRenderPanel.setYPosition(0);
			svgRenderPanel.repaint();
		}
			
	}
}
