package Controller;

//import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GUI.SVGDisplay;
//import Model.Rectangles;

public class SVGMouseAction implements MouseListener,MouseMotionListener 
{

	private SVGDisplay display;
	private static int initialMouseX;
	private static int initialMouseY;
	private int changeX = 0;
	private int changeY = 0;


	public SVGMouseAction(SVGDisplay svgRender) 
	{
		// TODO Auto-generated constructor stub
		this.display = svgRender; 
	}

	public void mousePressed(MouseEvent e) 
	{
		Point mousePoint = new Point();
		mousePoint = e.getPoint();
		initialMouseX = mousePoint.x;
		initialMouseY = mousePoint.y;
		this.display.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseReleased(MouseEvent e) 
	{
		this.display.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void mouseEntered(MouseEvent e) 
	{
	}

	public void mouseExited(MouseEvent e) 
	{
	}

	public void mouseClicked(MouseEvent e) 
	{
		/*Rectangles rect = new Rectangles();
		rect.setStrokeColor(Color.red);
		rect.setStrokeWidth(3);
		rect.setFill(Color.blue);
		rect.setHeight(100);
		rect.setWidth(100);
		rect.setX(e.getX());
		rect.setY(e.getY());
		this.display.getDrawings().add(rect);
		this.display.repaint();
		System.out.println("Clicked");*/
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		this.display.setXPosition((double)this.display.getXPosition() + changeX);
		this.display.setYPosition((double)this.display.getYPosition() + changeY);
		changeX = e.getX() - initialMouseX;
		changeY = e.getY() - initialMouseY;
		initialMouseX = e.getX();
		initialMouseY = e.getY();
		
		this.display.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		// TODO Auto-generated method stub

	}
}

