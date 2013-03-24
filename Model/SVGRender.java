package Model;

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



public class SVGRender extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6848194504807933758L;
	private SVGReader reader;
<<<<<<< HEAD
	private double zoomScale;
	private double xPosition;
	private double yPosition;

	public SVGRender(SVGReader read) 
	{
=======
	private LinkedHashSet<Drawings> drawCollection;

	private String path;
	
	public SVGRender() {
>>>>>>> 019b8536711e2bce461b55a4a49cc69e0b703a2a
		// TODO Auto-generated constructor stub
		this.setBackground(Color.white);
<<<<<<< HEAD
=======
		this.drawCollection = new LinkedHashSet<Drawings>();
		
>>>>>>> 019b8536711e2bce461b55a4a49cc69e0b703a2a
		this.addMouseListener(new SVGMouseAction(this));
	}
	
	public SVGRender(SVGReader read, String path)
	{
		this.reader = read;
		this.reader.setDoc(path);
<<<<<<< HEAD
		this.setBackground(Color.white);
		this.zoomScale = 1;
		this.xPosition = 0;
		this.yPosition =0;
		//this.addMouseListener(new SVGMouseAction(this));
=======
		
		//
		this.path = path;
		//
		
		this.setBackground(Color.white);
		this.drawCollection = this.reader.getDrawings();
		
		this.addMouseListener(new SVGMouseAction(this));
>>>>>>> 019b8536711e2bce461b55a4a49cc69e0b703a2a
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
<<<<<<< HEAD
					System.out.println("xpostion is "+xPosition);
					// creating 2D Shapes object 
					Ellipse2D.Double circleShape = new Ellipse2D.Double(((Circles) drawItem).getEllipse2DX()+xPosition,
							((Circles) drawItem).getEllipse2DY()+yPosition,((Circles) drawItem).getR()*2*zoomScale
							,((Circles) drawItem).getR()*2*zoomScale);
					
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
=======
					Drawings drawItem = it.next();
					if(drawItem instanceof Circles)
					{
						// creating 2D Shapes object 
						Ellipse2D.Double circleShape = new Ellipse2D.Double(((Circles) drawItem).getEllipse2DX(),
								((Circles) drawItem).getEllipse2DY(),((Circles) drawItem).getR()*2
								,((Circles) drawItem).getR()*2);
>>>>>>> 019b8536711e2bce461b55a4a49cc69e0b703a2a
	
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
	
	public LinkedHashSet<Drawings> getDrawings()
	{
		return this.drawCollection;
	}
	
	public String getPath()
	{
		return this.path;
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
	
	class SVGMouseAction implements MouseListener {
		

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
	    	//Testing
	    	Rectangles rect = new Rectangles();
	    	rect.setFill(Color.blue);
	    	rect.setStrokeColor(Color.red);
	    	rect.setStrokeWidth(5);
	    	rect.setHeight(100);
	    	rect.setWidth(100);
	    	rect.setX(e.getX());
	    	rect.setY(e.getY());
	    	this.render.drawCollection.add(rect);
	    	repaint();
	    	System.out.println("Clicked");
	    	//Testing
	    }
}

}
