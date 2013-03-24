package Controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import GUI.SVGRender;
public class PanAction extends MouseAdapter
{
	private SVGRender svgRenderPanel;
	private int mousePressedX;
	private int mousePressedY;
	public PanAction(JPanel panel)
	{
		this.svgRenderPanel = (SVGRender) panel;
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		System.out.println("Mouse pressed is y: "+e.getY()+"x is"+e.getX());
		mousePressedX = e.getX();
		mousePressedY = e.getY();
		svgRenderPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		System.out.println("Mouse released is y: "+e.getY()+"x is"+e.getX());
		
		svgRenderPanel.setXPosition(((double)e.getX()-mousePressedX));
		svgRenderPanel.setYPosition(((double)e.getY()-mousePressedY));
		svgRenderPanel.repaint();
	}

}
