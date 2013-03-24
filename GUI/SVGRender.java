package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.Shape;
//import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
//import java.util.LinkedList;

import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.JPanel;

import Controller.SVGMouseAction;
import Model.Circles;
import Model.Drawings;
import Model.Lines;
import Model.Rectangles;
import Model.SVGReader;
import Model.Shapes;



public class SVGRender extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6848194504807933758L;
	private SVGReader reader;
	private double zoomScale;
	private double xPosition;
	private double yPosition;
	private String path;
	private LinkedHashSet<Drawings> drawCollection;

	public SVGRender(SVGReader read) 
	{
		// TODO Auto-generated constructor stub
		this.reader = read;
		this.setBackground(Color.white);
		this.drawCollection = this.reader.getDrawings();
		this.addMouseListener(new SVGMouseAction(this));
	}

	public SVGRender(SVGReader read, String path)
	{
		this.reader = read;
		this.reader.setDoc(path);
		this.drawCollection = this.reader.getDrawings();
		//
		this.path = path;
		//
		this.setBackground(Color.white);
		this.zoomScale = 1;
		this.xPosition = 0;
		this.yPosition =0;
		this.addMouseListener(new SVGMouseAction(this));
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//for anti-aliasing for better output.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(!drawCollection.isEmpty())
		{
			Iterator<Drawings> it = drawCollection.iterator();
			while(it.hasNext())
			{
				Drawings drawItem = it.next();
				if(drawItem instanceof Circles)
				{
					// creating 2D Shapes object 
					Ellipse2D.Double circleShape = new Ellipse2D.Double(((Circles) drawItem).getEllipse2DX(),
							((Circles) drawItem).getEllipse2DY(),((Circles) drawItem).getR()*2
							,((Circles) drawItem).getR()*2);

					g2d.setColor(((Shapes) drawItem).getFill());
					g2d.fill(circleShape);
					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()));
					g2d.draw(circleShape);
				}
				else if(drawItem instanceof Rectangles)
				{
					// creating 2D Shapes object 
					Rectangle2D.Double rectShape = new Rectangle2D.Double(((Rectangles) drawItem).getX(),
							((Rectangles) drawItem).getY(),((Rectangles) drawItem).getWidth()
							,((Rectangles) drawItem).getHeight());
					
					g2d.setColor(((Shapes) drawItem).getFill());
					g2d.fill(rectShape);
					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()));
					g2d.draw(rectShape);
				}
				else if(drawItem instanceof Lines)
				{
					// creating 2D Shapes object 
					
					Line2D.Double lineShape = new Line2D.Double(((Lines) drawItem).getX1(),
							((Lines) drawItem).getY1(),((Lines) drawItem).getX2(),
							((Lines) drawItem).getY2());
					
					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()));
					g2d.draw(lineShape);
				}
			}
		}			
	}

	public Dimension getPreferredSize()
	{
		try
		{
			return new Dimension((int)this.reader.getSVGElement().getWidth(),(int)this.reader.getSVGElement().getHeight());
		}
		catch(Exception ex)
		{
			return new Dimension(500,500);
		}
	}

	public void setZoomScale(double zoomScale)
	{
		this.zoomScale = zoomScale;
	}

	public double getZoomScale()
	{
		return zoomScale;
	}
	public double getYPosition() {
		return yPosition;
	}

	public void setYPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	public double getXPosition() {
		return xPosition;
	}

	public void setXPosition(double xPosition) {
		this.xPosition = xPosition;
	}
	
	public LinkedHashSet<Drawings> getDrawings()
	{
		return this.drawCollection;
	}
	
	public String getPath()
	{
		return this.path;
	}
}
