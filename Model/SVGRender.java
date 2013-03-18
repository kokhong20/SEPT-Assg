package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
//import java.awt.Shape;
//import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
//import java.util.LinkedList;

import java.util.Iterator;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SVGRender extends JPanel
{
	public SVGRender()
	{
		this.setBackground(Color.white);
		this.setSize(500, 500);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//for anti-aliasing for better output.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(!SVGReader.drawingCollection.isEmpty())
		{
			Iterator<Drawings> it = SVGReader.drawingCollection.iterator();
			while(it.hasNext())
			{
				Drawings drawItem = it.next();
				if(drawItem instanceof Circles)
				{
					// creating 2D Shapes object 
					Ellipse2D.Double circleShape = new Ellipse2D.Double(((Circles) drawItem).getEllipse2DX(),
							((Circles) drawItem).getEllipse2DY(),((Circles) drawItem).getR()*2
							,((Circles) drawItem).getR()*2);

					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()));
					g2d.draw(circleShape);
					g2d.setColor(((Shapes) drawItem).getFill());
					g2d.fill(circleShape);
				}
				else if(drawItem instanceof Rectangles)
				{
					// creating 2D Shapes object 
					Rectangle2D.Double rectShape = new Rectangle2D.Double(((Rectangles) drawItem).getX(),
							((Rectangles) drawItem).getY(),((Rectangles) drawItem).getWidth()
							,((Rectangles) drawItem).getHeight());

					g2d.setColor(((Drawings) drawItem).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth()));
					g2d.draw(rectShape);
					g2d.setColor(((Shapes) drawItem).getFill());
					g2d.fill(rectShape);
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
			
			/*
			for(int i =0 ;i<SVGReader.drawingCollection.size();i++)
			{
				
				if(SVGReader.drawingCollection.get(i) instanceof Circles)
				{
					// creating 2D Shapes object 
					Ellipse2D.Double circleShape = new Ellipse2D.Double(((Circles) SVGReader.drawingCollection.get(i)).getEllipse2DX(),
							((Circles) SVGReader.drawingCollection.get(i)).getEllipse2DY(),((Circles) SVGReader.drawingCollection.get(i)).getR()*2
							,((Circles) SVGReader.drawingCollection.get(i)).getR()*2);

					g2d.setColor(((Drawings) SVGReader.drawingCollection.get(i)).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) SVGReader.drawingCollection.get(i)).getStrokeWidth()));
					g2d.draw(circleShape);
					g2d.setColor(((Shapes) SVGReader.drawingCollection.get(i)).getFill());
					g2d.fill(circleShape);
				}
				else if(SVGReader.drawingCollection.get(i) instanceof Rectangles)
				{
					// creating 2D Shapes object 
					Rectangle2D.Double rectShape = new Rectangle2D.Double(((Rectangles) SVGReader.drawingCollection.get(i)).getX(),
							((Rectangles) SVGReader.drawingCollection.get(i)).getY(),((Rectangles) SVGReader.drawingCollection.get(i)).getWidth()
							,((Rectangles) SVGReader.drawingCollection.get(i)).getHeight());

					g2d.setColor(((Drawings) SVGReader.drawingCollection.get(i)).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) SVGReader.drawingCollection.get(i)).getStrokeWidth()));
					g2d.draw(rectShape);
					g2d.setColor(((Shapes) SVGReader.drawingCollection.get(i)).getFill());
					g2d.fill(rectShape);
				}
				else if(SVGReader.drawingCollection.get(i) instanceof Lines)
				{
					// creating 2D Shapes object 
					
					Line2D.Double lineShape = new Line2D.Double(((Lines) SVGReader.drawingCollection.get(i)).getX1(),
							((Lines) SVGReader.drawingCollection.get(i)).getY1(),((Lines) SVGReader.drawingCollection.get(i)).getX2(),
							((Lines) SVGReader.drawingCollection.get(i)).getY2());
					
					g2d.setColor(((Drawings) SVGReader.drawingCollection.get(i)).getStrokeColor());
					g2d.setStroke(new BasicStroke(((Drawings) SVGReader.drawingCollection.get(i)).getStrokeWidth()));
					g2d.draw(lineShape);
				}
			}*/
		}
	}
}
