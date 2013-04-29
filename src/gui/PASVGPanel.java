package gui;

import static controller.PAToolKitAction.DrawRectangleAction.endDrag;
import static controller.PAToolKitAction.DrawRectangleAction.startDrag;
import static gui.PADrawingItem.buttonSelected;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.PACircle;
import model.PALine;
import model.PASVGContainer;
import model.PASVGElement;
import model.PARectangle;

/**
 * 
 * @author KokHong
 * 
 */
public class PASVGPanel extends JPanel
{
	private int svgWidth;
	private int svgHeight;
	public BufferedImage svgImage;
	private PAMainFrame mainFrame;
	public PASVGContainer svgContainer;

	private Shape selectedShape;
	private Rectangle2D handleRectangle;
	private Cursor curCursor;
	private int x1, y1, x2, y2;

	/**
	 * constructor to define PASVGPanel for PAMainFrame
	 * 
	 * @param mainFrame
	 */
	public PASVGPanel(PASVGContainer svgContainer, BufferedImage svgImage)
	{
		this.svgContainer = svgContainer;
		this.svgImage = svgImage;
		initPanel();

		this.addMouseListener(new MyMouseListener());
		this.addMouseMotionListener(new MyMouseListener());
	}

	/**
	 * Initialise Svg Panel
	 */
	private void initPanel()
	{
		svgWidth = (int) svgContainer.getSvgTag().getWidth();
		svgHeight = (int) svgContainer.getSvgTag().getHeight();
		Dimension size = new Dimension(svgWidth, svgHeight);

		setMaximumSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(svgImage, 0, 0, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if (startDrag != null && endDrag != null)
		{
			switch (buttonSelected.getToolTipText())
			{

			case "Rectangle":
				g2d.setPaint(Color.LIGHT_GRAY);
				Rectangle2D.Double rect = makeRectangle(startDrag.x,
						startDrag.y, endDrag.x, endDrag.y);
				g2d.draw(rect);
				break;

			case "Line":
				g2d.setPaint(Color.LIGHT_GRAY);
				g2d.setStroke(new BasicStroke(2));
				Line2D.Double line = makeLine(startDrag.x, startDrag.y,
						endDrag.x, endDrag.y);
				g2d.draw(line);
				break;

			case "Circle":
				g2d.setPaint(Color.LIGHT_GRAY);
				Ellipse2D.Double circle = makeCircle(startDrag.x, startDrag.y,
						endDrag.x, endDrag.y);
				g2d.draw(circle);
				break;

			}
		}

		if (handleRectangle != null)
		{
			if (selectedShape instanceof Rectangle2D)
				drawRectHighlight((Graphics2D) g, handleRectangle);
			else if (selectedShape instanceof Ellipse2D)
				drawEllipseHighlight((Graphics2D) g, handleRectangle);
			else if (selectedShape instanceof Line2D)
				drawLineHighlight((Graphics2D) g, handleRectangle);
		}
	}

	public void drawRectHighlight(Graphics2D g2D, Rectangle2D r)
	{
		double x = r.getX();
		double y = r.getY();
		double w = r.getWidth();
		double h = r.getHeight();

		Rectangle.Double rect1 = new Rectangle.Double(x - 3.0, y - 3.0, 6.0,
				6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect1);
		g2D.setColor(Color.black);
		g2D.draw(rect1);

		Rectangle.Double rect2 = new Rectangle.Double(x + w - 3.0, y - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect2);
		g2D.setColor(Color.black);
		g2D.draw(rect2);

		Rectangle.Double rect3 = new Rectangle.Double(x - 3.0, y + h - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect3);
		g2D.setColor(Color.black);
		g2D.draw(rect3);

		Rectangle.Double rect4 = new Rectangle.Double(x + w - 3.0, y + h - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect4);
		g2D.setColor(Color.black);
		g2D.draw(rect4);

		Rectangle.Double rect5 = new Rectangle.Double(x + (h / 2) - 3.0, y - 3.0, 6.0,
				6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect5);
		g2D.setColor(Color.black);
		g2D.draw(rect5);

		Rectangle.Double rect6 = new Rectangle.Double(x - 3.0, y + (w / 2) - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect6);
		g2D.setColor(Color.black);
		g2D.draw(rect6);
		
		Rectangle.Double rect7 = new Rectangle.Double(x + w - 3.0, y + (w / 2) - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect7);
		g2D.setColor(Color.black);
		g2D.draw(rect7);

		Rectangle.Double rect8 = new Rectangle.Double(x + (h / 2) - 3.0, y + h - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect8);
		g2D.setColor(Color.black);
		g2D.draw(rect8);
	}

	private void drawEllipseHighlight(Graphics2D g2D, Rectangle2D r)
	{
		// TODO Auto-generated method stub
		double x = r.getX();
		double y = r.getY();
		double w = r.getWidth();
		double h = r.getHeight();

		Rectangle.Double rect1 = new Rectangle.Double(x - 3.0, y - 3.0, 6.0,
				6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect1);
		g2D.setColor(Color.black);
		g2D.draw(rect1);

		Rectangle.Double rect2 = new Rectangle.Double(x + w - 3.0, y - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect2);
		g2D.setColor(Color.black);
		g2D.draw(rect2);

		Rectangle.Double rect3 = new Rectangle.Double(x - 3.0, y + h - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect3);
		g2D.setColor(Color.black);
		g2D.draw(rect3);

		Rectangle.Double rect4 = new Rectangle.Double(x + w - 3.0, y + h - 3.0,
				6.0, 6.0);
		g2D.setColor(Color.white);
		g2D.fill(rect4);
		g2D.setColor(Color.black);
		g2D.draw(rect4);
	}

	private void drawLineHighlight(Graphics2D g2D, Rectangle2D r)
	{
		// TODO Auto-generated method stub

	}

	private Rectangle2D.Double makeRectangle(int x1, int y1, int x2, int y2)
	{
		return new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

	private Line2D.Double makeLine(int x1, int y1, int x2, int y2)
	{
		return new Line2D.Double(x1, y1, x2, y2);
	}

	private Ellipse2D.Double makeCircle(int x1, int y1, int x2, int y2)
	{
		double width = (Math.abs(x1 - x2) > Math.abs(y1 - y2) ? Math.abs(x1
				- x2) : Math.abs(y1 - y2));
		return new Ellipse2D.Double(x1, y1, width, width);
	}

	class MyMouseListener implements MouseListener, MouseMotionListener
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub
			for (Shape shapes : svgContainer.getShapesCollection().keySet())
			{
				if (shapes instanceof Line2D)
				{
					Line2D line = (Line2D) shapes;

				} else
				{
					if (shapes.contains(e.getX(), e.getY()))
					{
						System.out.println(shapes + " is clicked");

						selectedShape = shapes;
						handleRectangle = shapes.getBounds2D();

						repaint();
						return;
					}
				}
			}

			if (handleRectangle != null)
				handleRectangle = null;
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			for (Shape shapes : svgContainer.getShapesCollection().keySet())
			{
				if (shapes.contains(e.getX(), e.getY()))
				{
					selectedShape = shapes;
					if (handleRectangle != null)
					{
						handleRectangle = shapes.getBounds2D();
					}
				} else
				{
					handleRectangle = null;
				}
				repaint();
				x1 = e.getX();
				y1 = e.getY();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			for (Shape shapes : svgContainer.getShapesCollection().keySet())
			{
				if (shapes.contains(e.getX(), e.getY()))
				{
					handleRectangle = shapes.getBounds2D();
					selectedShape = shapes;
					repaint();
					return;
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			// TODO Auto-generated method stub
			for (Shape shapes : svgContainer.getShapesCollection().keySet())
			{
				if (shapes.contains(e.getX(), e.getY()))
				{
					PASVGElement svgElement;
					svgElement = svgContainer.getShapesCollection().get(shapes);
					int x = (int) ((PARectangle) svgElement).getX();
					int y = (int) ((PARectangle) svgElement).getY();

					handleRectangle = null;
					selectedShape = shapes;
					x2 = e.getX();
					y2 = e.getY();

					x = x + x2 - x1;
					y = y + y2 - y1;

					((PARectangle) svgElement).setX(x);
					((PARectangle) svgElement).setY(y);

					x1 = x2;
					y1 = y2;
					repaint();
					return;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
		}
	}
}
