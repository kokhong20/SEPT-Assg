package Model;

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


public class SVGRender extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6848194504807933758L;
	private SVGReader reader;

	public SVGRender(SVGReader read) {
		// TODO Auto-generated constructor stub
		this.reader = read;
		this.setBackground(Color.white);
	}
	
	public SVGRender(SVGReader read, String path)
	{
		this.reader = read;
		this.reader.setDoc(path);
		
		this.setBackground(Color.white);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//for anti-aliasing for better output.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		LinkedHashSet<Drawings> drawCollection = this.reader.getDrawings();
		
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
}
