package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Dimension;
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

	public SVGRender() 
	{
		// TODO Auto-generated constructor stub
		this.drawCollection = new LinkedHashSet<Drawings>();

		this.setBackground(Color.white);
		this.zoomScale = 1;
		
		this.addMouseListener(new SVGMouseAction(this));
		//this.addMouseMotionListener(new SVGMouseAction(this));
	}

	public SVGRender(SVGReader read, String path)
	{
		this.reader = read;
		this.reader.setDoc(path);
		this.drawCollection = read.getDrawings();
		this.path = path;
		this.setBackground(Color.white);
		this.zoomScale = 1;
		this.xPosition = 0;
		this.yPosition =0;
		this.addMouseMotionListener(new SVGMouseAction(this));
		this.addMouseListener(new SVGMouseAction(this));
		this.setFocusable(true);
		
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
					System.out.println("xpostion is "+xPosition);
					// creating 2D Shapes object 
					Ellipse2D.Double circleShape = new Ellipse2D.Double(((Circles) drawItem).getEllipse2DX()*zoomScale+xPosition,
							((Circles) drawItem).getEllipse2DY()*zoomScale+yPosition,((Circles) drawItem).getR()*2*zoomScale
							,((Circles) drawItem).getR()*2*zoomScale);

					g2d.setColor(((Shapes) drawItem).getFill());
					g2d.fill(circleShape);
					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()*((float)zoomScale)));
					g2d.draw(circleShape);
				}
				else if(drawItem instanceof Rectangles)
				{
					// creating 2D Shapes object 
					Rectangle2D.Double rectShape = new Rectangle2D.Double(((Rectangles) drawItem).getX()*zoomScale+xPosition,
							((Rectangles) drawItem).getY()*zoomScale+yPosition,((Rectangles) drawItem).getWidth()*zoomScale
							,((Rectangles) drawItem).getHeight()*zoomScale);

					g2d.setColor(((Shapes) drawItem).getFill());
					g2d.fill(rectShape);
					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()*((float)zoomScale)));
					g2d.draw(rectShape);
				}
				else if(drawItem instanceof Lines)
				{
					// creating 2D Shapes object 

					Line2D.Double lineShape = new Line2D.Double(((Lines) drawItem).getX1()*zoomScale+xPosition,
							((Lines) drawItem).getY1()*zoomScale+yPosition,((Lines) drawItem).getX2()*zoomScale+xPosition,
							((Lines) drawItem).getY2()*zoomScale+yPosition);

					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()*((float)zoomScale)));
					g2d.draw(lineShape);
				}
			}

		}
	}

	public void paintComponent(Graphics g, Rectangles rectangles) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//for anti-aliasing for better output.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Rectangle2D.Double rectShape = new Rectangle2D.Double(rectangles.getX(),
				rectangles.getY(),rectangles.getWidth()
				,rectangles.getHeight());

		g2d.setColor(rectangles.getFill());
		g2d.fill(rectShape);
		g2d.setColor(rectangles.getStrokeColor());
		g2d.setStroke(new BasicStroke(rectangles.getStrokeWidth()));
		g2d.draw(rectShape);		
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
	public double getYPosition() 
	{
		return yPosition;
	}

	public void setYPosition(double yPosition) 
	{
		this.yPosition = yPosition;
	}
	public double getXPosition() {
		return xPosition;
	}

	public void setXPosition(double xPosition) 
	{
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
