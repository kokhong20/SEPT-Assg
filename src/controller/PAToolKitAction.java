package controller;

import gui.PADrawingItem;
import gui.PASVGPanel;
import gui.PAShapeBar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JToggleButton;

import model.PACircle;
import model.PAColor;
import model.PALine;
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
    public static Point startDrag, endDrag;
    protected JToggleButton button;
    protected PASVGPanel drawPanel;
    protected BufferedImage drawImage;
    protected LinkedList<PASVGElement> elementCollection;
    protected LinkedHashMap<Shape, PASVGElement> shapeCollection;
    protected PAShapeBar shapeBar;
    protected Color fill, stroke;
    protected double strokeWidth;
	protected static int height = -1, width = -1;
	protected static double scale = 1;

    public PAToolKitAction(PASVGPanel drawPanel, JToggleButton button, PAShapeBar shapeBar)
    {
        this.button = button;
        this.drawPanel = drawPanel;
        this.shapeBar = shapeBar;
        this.drawImage = drawPanel.svgImage;
        elementCollection = drawPanel.svgContainer.getSvgContainer();
        shapeCollection = drawPanel.svgContainer.getShapesCollection();
    }

    public void setShapeAttributes()
    {
        fill = shapeBar.fillCheck.isSelected() ? shapeBar.fillButton
                .getBackground() : PAColor.DEFAULT_FILL;
        stroke = shapeBar.strokeCheck.isSelected() ? shapeBar.strokeButton
                .getBackground() : PAColor.DEFAULT_FILL;
        strokeWidth = shapeBar.strokeWidthBox.isEnabled() ? (Double) shapeBar.strokeWidthBox
                .getValue() : PAUnit.DEFAULT_STROKE_WIDTH;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (button.isSelected())
        {
            if ((PADrawingItem.buttonSelected != null) && (!PADrawingItem.buttonSelected.equals(button)))
            {
                PADrawingItem.buttonSelected.setSelected(false);
                PADrawingItem.buttonSelected.setBorder(null);
            }
            button.setBorder(BorderFactory.createLineBorder(new Color(35, 192, 255, 100), 1));

            PADrawingItem.buttonSelected = button;
            addActionToComponents();
        }
        else
        {
            PADrawingItem.buttonSelected = null;
            button.setBorder(null);
        }


    }

    public abstract void addActionToComponents();

    /**
     *
     *
     *
     */
    public static class DrawRectangleAction extends PAToolKitAction
    {
        PASVGElement rect;

        public DrawRectangleAction(PASVGPanel drawPanel, JToggleButton button,
                PAShapeBar shapeBar)
        {
            super(drawPanel, button, shapeBar);
        }

        @Override
        public void addActionToComponents()
        {
            MouseAdapter mouseRectAction = new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        System.out.println("Mouse pressed" + startDrag + "End" + endDrag);
                        startDrag = new Point(e.getX(), e.getY());
                        endDrag = startDrag;
                        drawPanel.repaint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        setShapeAttributes();
                        System.out.println("Mouse Released" + startDrag + "End" + endDrag);
                        rect = makeRectangle(fill, stroke, strokeWidth,
                                startDrag.x, startDrag.y, e.getX(), e.getY());
                        elementCollection.add(rect);
                        overwriteImage();
                        startDrag = null;
                        endDrag = null;
                        drawPanel.repaint();
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        System.out.println("Mouse dragged" + startDrag + "End" + endDrag);
                        endDrag = new Point(e.getX(), e.getY());
                        drawPanel.repaint();
                    }
                }

            };

            drawPanel.addMouseListener(mouseRectAction);
            drawPanel.addMouseMotionListener(mouseRectAction);

        }

        private PARectangle makeRectangle(Color fill, Color stroke,
                double strokeWidth, int x1, int y1, int x2, int y2)
        {
            return new PARectangle(fill, stroke, (double) strokeWidth,
                    (double) Math.min(x1, x2), (double) Math.min(y1, y2),
                    (double) Math.abs(x1 - x2), (double) Math.abs(y1 - y2));
        }

        private void overwriteImage()
        {
            double x = ((PARectangle) rect).getX();
            double y = ((PARectangle) rect).getY();
            double shapeWidth = ((PARectangle) rect).getWidth();
            double shapeHeight = ((PARectangle) rect).getHeight();
            Rectangle2D.Double rect2D = new Rectangle2D.Double(x, y, shapeWidth, shapeHeight);
            BasicStroke basicStroke = new BasicStroke((float) strokeWidth);

            Graphics2D g2d = drawImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(fill);
            g2d.fill(rect2D);
            g2d.setColor(stroke);

            g2d.setStroke(basicStroke);
            g2d.draw(rect2D);

            shapeCollection.put(rect2D, (PARectangle) rect);
        }

    }

    /**
     *
     *
     */
    public static class DrawCircleAction extends PAToolKitAction
    {
        PASVGElement circle;

        public DrawCircleAction(PASVGPanel drawPanel, JToggleButton button,
                PAShapeBar shapeBar)
        {
            super(drawPanel, button, shapeBar);
        }

        @Override
        public void addActionToComponents()
        {
            MouseAdapter mouseRectAction = new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        System.out.println("Mouse pressed" + startDrag + "End" + endDrag);
                        startDrag = new Point(e.getX(), e.getY());
                        endDrag = startDrag;
                        drawPanel.repaint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        setShapeAttributes();
                        System.out.println("Mouse Released" + startDrag + "End" + endDrag);
                        circle = makeCircle(fill, stroke, strokeWidth,
                                startDrag.x, startDrag.y, e.getX(), e.getY());
                        elementCollection.add(circle);
                        overwriteImage();
                        startDrag = null;
                        endDrag = null;
                        drawPanel.repaint();
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        System.out.println("Mouse dragged" + startDrag + "End" + endDrag);
                        endDrag = new Point(e.getX(), e.getY());
                        drawPanel.repaint();
                    }
                }

            };

            drawPanel.addMouseListener(mouseRectAction);
            drawPanel.addMouseMotionListener(mouseRectAction);
        }

        private PACircle makeCircle(Color fill, Color stroke, double strokeWidth, int x1, int y1, int x2, int y2)
        {
            double cx, cy, r;
            r = (Math.abs(x1 - x2) > Math.abs(y1 - y2) ? Math.abs(x1 - x2) : Math.abs(y1 - y2)) / 2;
            cx = x1 + r;
            cy = y1 + r;
            return new PACircle(fill, stroke, (double) strokeWidth, cx, cy, r);
        }

        private void overwriteImage()
        {
            double x = ((PACircle) circle).getCx() - ((PACircle) circle).getR();
            double y = ((PACircle) circle).getCy() - ((PACircle) circle).getR();
            double diameter = ((PACircle) circle).getR() * 2;

            Ellipse2D.Double circle2D = new Ellipse2D.Double(x, y, diameter, diameter);

            BasicStroke basicStroke = new BasicStroke((float) strokeWidth);

            Graphics2D g2d = drawImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(fill);
            g2d.fill(circle2D);
            g2d.setColor(stroke);

            g2d.setStroke(basicStroke);
            g2d.draw(circle2D);

            shapeCollection.put(circle2D, (PACircle) circle);
        }

    }

    public static class DrawLineAction extends PAToolKitAction
    {
        PASVGElement line;

        public DrawLineAction(PASVGPanel drawPanel, JToggleButton button,
                PAShapeBar shapeBar)
        {
            super(drawPanel, button, shapeBar);
        }

        @Override
        public void addActionToComponents()
        {
            MouseAdapter mouseRectAction = new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        System.out.println("Mouse pressed" + startDrag + "End" + endDrag);
                        startDrag = new Point(e.getX(), e.getY());
                        endDrag = startDrag;
                        drawPanel.repaint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        setShapeAttributes();
                        System.out.println("Mouse Released" + startDrag + "End" + endDrag);
                        line = makeLine(stroke, strokeWidth,
                                startDrag.x, startDrag.y, e.getX(), e.getY());
                        elementCollection.add(line);
                        overwriteImage();
                        startDrag = null;
                        endDrag = null;
                        drawPanel.repaint();
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        System.out.println("Mouse dragged" + startDrag + "End" + endDrag);
                        endDrag = new Point(e.getX(), e.getY());
                        drawPanel.repaint();
                    }
                }

            };

            drawPanel.addMouseListener(mouseRectAction);
            drawPanel.addMouseMotionListener(mouseRectAction);
        }

        private PALine makeLine(Color stroke, double strokeWidth, int x1, int x2, int y1, int y2)
        {
            return new PALine(stroke, (double) strokeWidth, (double) x1, (double) y1, (double) x2, (double) y2);
        }

        private void overwriteImage()
        {
            double x1 = ((PALine) line).getX1();
            double y1 = ((PALine) line).getY1();
            double x2 = ((PALine) line).getX2();
            double y2 = ((PALine) line).getY2();
            Line2D.Double line2D = new Line2D.Double(x1, y1, x2, y2);

            BasicStroke basicStroke = new BasicStroke((float) strokeWidth);

            Graphics2D g2d = drawImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(stroke);

            g2d.setStroke(basicStroke);
            g2d.draw(line2D);

            shapeCollection.put(line2D, (PALine) line);
        }

    }
    
	public static class ZoomIn extends PAToolKitAction
	{

		public ZoomIn(PASVGPanel drawPanel, JToggleButton button,
				PAShapeBar shapeBar)
		{
			super(drawPanel, button, shapeBar);
			// TODO Auto-generated constructor stub
		}

		public void addActionToComponents()
		{
			setSize();
			DecimalFormat form = new DecimalFormat("#.#");  
			scale += 0.1;
			scale = Double.valueOf(form.format(scale));
			System.out.println(scale);
			overwriteImage();
			drawPanel.repaint();
			
		}

		private void overwriteImage()
		{
//			
//			AffineTransform at = new AffineTransform();
//			at.scale(scale, scale);
//			AffineTransformOp scaleOp = new AffineTransformOp(at,
//					AffineTransformOp.TYPE_BILINEAR);
//			BufferedImage before = drawPanel.svgImage;
//			int w = (int) (width * scale);
//			int h = (int) (height * scale);
//			BufferedImage after = new BufferedImage(w, h,
//					BufferedImage.TYPE_INT_ARGB);
//			after = scaleOp.filter(before, after);

//			drawPanel.svgImage = after;
			BufferedImage bi = new BufferedImage((int)(scale*width), (int)(scale*height),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D) bi.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2d.scale(scale,scale);
			g2d.drawImage(drawImage, 0, 0, null);
			drawPanel.paint(g2d);
			System.out.println(drawPanel.svgImage.getHeight());
			
			
		}
		private void setSize()
		{
			if (height == -1 && width == -1)
			{
				height = drawPanel.svgImage.getHeight();
				width = drawPanel.svgImage.getWidth();
			}
		}

	}
}
