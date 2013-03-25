package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GUI.SVGRender;
import Model.Rectangles;

public class SVGMouseAction implements MouseListener,MouseMotionListener 
{

	private SVGRender render;
	private static int initialMouseX;
	private static int initialMouseY;
	private int changeX = 0;
	private int changeY = 0;


	public SVGMouseAction(SVGRender svgRender) 
	{
		// TODO Auto-generated constructor stub
		this.render = svgRender; 
	}

	public void mousePressed(MouseEvent e) 
	{
		Point mousePoint = new Point();
		mousePoint = e.getPoint();
		initialMouseX = mousePoint.x;
		initialMouseY = mousePoint.y;
		this.render.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseReleased(MouseEvent e) 
	{
		this.render.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void mouseEntered(MouseEvent e) 
	{
	}

	public void mouseExited(MouseEvent e) 
	{
	}

	public void mouseClicked(MouseEvent e) 
	{
		Rectangles rect = new Rectangles();
		rect.setStrokeColor(Color.red);
		rect.setStrokeWidth(3);
		rect.setFill(Color.blue);
		rect.setHeight(100);
		rect.setWidth(100);
		rect.setX(e.getX());
		rect.setY(e.getY());
		this.render.getDrawings().add(rect);
		this.render.repaint();
		System.out.println("Clicked");
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		this.render.setXPosition((double)this.render.getXPosition() + changeX);
		this.render.setYPosition((double)this.render.getYPosition() + changeY);
		changeX = e.getX() - initialMouseX;
		changeY = e.getY() - initialMouseY;
		initialMouseX = e.getX();
		initialMouseY = e.getY();
		/*
		if(e.getX()<initialMouseX) //Move from left to right
		{
			this.render.setXPosition((double)this.render.getXPosition()-5);
		}
		else // move from right to left
		{
			this.render.setXPosition((double)this.render.getXPosition()+5);
		}
		
		if(e.getY()<initialMouseY) //move from btm to up
		{
			this.render.setYPosition((double)this.render.getYPosition()-5);
		}
		else // move from up to btm
		{
			this.render.setYPosition((double)this.render.getYPosition()+5);
		}*/
		
		this.render.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		// TODO Auto-generated method stub

	}
}

