package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.SVGRender;
import Model.Rectangles;

public class SVGMouseAction implements MouseListener {

	private SVGRender render;
	private int mousePressedX;
	private int mousePressedY;

	public SVGMouseAction(SVGRender svgRender) {
		// TODO Auto-generated constructor stub
		this.render = svgRender; 
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed is y: " + e.getY() + " x is " + e.getX());
		mousePressedX = e.getX();
		mousePressedY = e.getY();
		this.render.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released is y: " + e.getY() + " x is " + e.getX());
		
		this.render.setXPosition(((double)e.getX() - mousePressedX));
		this.render.setYPosition(((double)e.getY() - mousePressedY));
		this.render.repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
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
}

