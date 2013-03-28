package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import GUI.SVGDisplay;
import Model.Rectangles;

public class MoveAction implements MouseListener, MouseMotionListener, MouseWheelListener{

	private SVGDisplay display;
	private int initialMouseX;
	private int initialMouseY;
	private int changeX;
	private int changeY;
	
	public MoveAction(SVGDisplay svgPanel) {
		// TODO Auto-generated constructor stub
		this.display = svgPanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//this.display.setLocation(e.getX(), e.getY());		
		this.display.setXPosition((double)this.display.getXPosition() + changeX);
		this.display.setYPosition((double)this.display.getYPosition() + changeY);
		this.display.setViewPosition(new Point((int)this.display.getXPosition(), (int)this.display.getYPosition()));
		changeX = e.getX() - initialMouseX;
		changeY = e.getY() - initialMouseY;
		initialMouseX = e.getX();
		initialMouseY = e.getY();
		
		this.display.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		// Testing that location point works even though svg is panned,
		// earlier doesn't follow exact location point
		/*Rectangles rect = new Rectangles();
		rect.setStrokeColor(Color.red);
		rect.setStrokeWidth(3);
		rect.setFill(Color.blue);
		rect.setHeight(100);
		rect.setWidth(100);
		rect.setX(e.getX());
		rect.setY(e.getY());
		this.display.getRender().getDrawings().add(rect);
		this.display.repaint();
		System.out.println("Clicked");*/
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		initialMouseX = e.getX();
		initialMouseY = e.getY();
		this.display.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		if(e.isControlDown())
		{
			if(e.getWheelRotation() < 0)
				this.display.setZoomScale(this.display.getZoomScale() * 2);
			else
				this.display.setZoomScale( this.display.getZoomScale() * 0.5);
		}
	}
}
