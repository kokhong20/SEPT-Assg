package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import javax.swing.JPanel;

public class RenderSVG extends JPanel
{
	public RenderSVG()
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
			}
		}
//		if(!SVGReader.rectShapeList.isEmpty())
//		{
//			for(int i=0;i<SVGReader.rectShapeList.size() ; i++)
//			{
//				System.out.println(SVGReader.rectShapeList.get(i));
//				g2d.draw(SVGReader.rectShapeList.get(i));
//			}
//		}
//		
//		if(!SVGReader.circleShapeList.isEmpty())
//		{
//			for(int i=0;i<SVGReader.circleShapeList.size() ; i++)
//			{
//				System.out.println(SVGReader.circleShapeList.get(i));
//				g2d.fill(SVGReader.circleShapeList.get(i));
//			}
//		}
	}
}
