package controller;

import gui.PASVGPanel;
import gui.PAShapeBar;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import model.PAColor;
import model.PARectangle;
import model.PASVGElement;
import model.PAUnit;

/**
 * 
 * @author KokHong
 *
 */
public abstract class PAToolKitAction extends AbstractAction 
{
	protected JButton button;
	protected PASVGPanel drawPanel;
	protected BufferedImage drawImage;
	protected LinkedList<PASVGElement> elementCollection;
	protected PAShapeBar shapeBar;
	protected Color fill, stroke;
	protected double strokeWidth;

	public PAToolKitAction(PASVGPanel drawPanel, JButton button,
			PAShapeBar shapeBar) 
	{
		this.button = button;
		this.drawPanel = drawPanel;
		this.shapeBar = shapeBar;
		this.drawImage = drawPanel.svgImage;
		elementCollection = drawPanel.svgContainer.getSvgContainer();
		
		setShapeAttributes();
		button.setAction(this);
	}

	public void setShapeAttributes() 
	{
		fill = shapeBar.fillButton.isEnabled() ? shapeBar.fillButton
				.getBackground() : PAColor.DEFAULT_FILL;
		stroke = shapeBar.strokeButton.isEnabled() ? shapeBar.strokeButton
				.getBackground() : PAColor.DEFAULT_FILL;
		strokeWidth = shapeBar.strokeWidthBox.isEnabled() ? (double) shapeBar.strokeWidthBox
				.getValue() : PAUnit.DEFAULT_STROKE_WIDTH;
	}

	public abstract void addActionToComponents();

	/**
	 * 
	 * 
	 *
	 */
	public static class DrawRectangleAction extends PAToolKitAction 
	{
		Point startDrag, endDrag;
		PASVGElement rect;

		public DrawRectangleAction(PASVGPanel drawPanel, JButton button,
				PAShapeBar shapeBar) 
		{
			super(drawPanel, button, shapeBar);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			addActionToComponents();
		}
		

		@Override
		public void addActionToComponents() 
		{
			/* MouseListener */
			drawPanel.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent e) 
				{
					startDrag = new Point(e.getX(), e.getY());
					endDrag = startDrag;
					drawPanel.repaint();
				}
				
				@Override
				public void mouseReleased(MouseEvent e) 
				{
					rect = makeRectangle(fill, stroke, strokeWidth,
							startDrag.x, startDrag.y, e.getX(), e.getY());
					elementCollection.add(rect);
					startDrag = null;
					endDrag = null;
					drawPanel.repaint();
				}
			});
			
			/* MouseMotionListener */
			drawPanel.addMouseMotionListener(new MouseAdapter()
			{
				@Override
				public void mouseDragged(MouseEvent e) 
				{
					endDrag = new Point(e.getX(), e.getY());
					drawPanel.repaint();
				}
			});
		}

		private PARectangle makeRectangle(Color fill, Color stroke,
				double strokeWidth, int x1, int y1, int x2, int y2) 
		{
			return new PARectangle(fill, stroke, strokeWidth,
					(double) Math.min(x1, x2), (double) Math.min(y1, y2),
					(double) Math.abs(x1 - x2), (double) Math.abs(y1 - y2));
		}
		
	    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
	        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	      }
		
		private void overwriteImage()
		{
			double x = ((PARectangle)rect).getX();
            double y = ((PARectangle)rect).getY();
            double shapeWidth = ((PARectangle)rect).getWidth();
            double shapeHeight = ((PARectangle)rect).getHeight();
            Rectangle2D.Double rect2D = new Rectangle2D.Double(x, y, shapeWidth, shapeHeight);
            BasicStroke basicStroke = new BasicStroke((float)strokeWidth) ;
            
            Graphics2D g2d = drawImage.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2d.setColor(fill);
			g2d.fill(rect2D);
            g2d.setColor(stroke);
           
            g2d.setStroke(basicStroke);
            g2d.draw(rect2D);
            
            if (startDrag != null && endDrag != null) {
                g2d.setPaint(Color.LIGHT_GRAY);
                Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                g2d.draw(r);
              }
            
		}
	}
}
