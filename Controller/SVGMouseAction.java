package Controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.SVGRender;
import Model.Rectangles;

public class SVGMouseAction implements MouseListener {

	private SVGRender render;

	public SVGMouseAction(SVGRender svgRender) {
		// TODO Auto-generated constructor stub
		this.render = svgRender; 
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		Rectangles rect = new Rectangles();
		rect.setFill(Color.blue);
		rect.setStrokeColor(Color.red);
		rect.setStrokeWidth(2);
		rect.setHeight(100);
		rect.setWidth(100);
		rect.setX(e.getX());
		rect.setY(e.getY());
    	this.render.getDrawings().add(rect);
    	this.render.repaint();
		System.out.println("Clicked");
	}
}
