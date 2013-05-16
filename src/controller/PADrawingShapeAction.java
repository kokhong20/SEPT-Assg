package controller;

import gui.PADrawingItem;
import gui.PASVGPanel;
import gui.PAShapeBar;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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
 * @since 1.1
 * <p>This class creates a PADrawingShapeAction to set action performed to draw
 * each shape such as Line, Rectangle and circle</p>
 */
public abstract class PADrawingShapeAction extends AbstractAction
{
    public static Point startDrag, endDrag;
    protected JToggleButton button;
    protected PASVGPanel drawPanel;
    protected BufferedImage drawImage;
    protected LinkedList<PASVGElement> elementCollection;
    protected PAShapeBar shapeBar;
    protected Color fill, stroke;
    protected double strokeWidth;
    protected double scale;
    protected Cursor cursor;

    /**
     * Creates a new PADrawingShapeAction which accept a PASVGPanel , a
     * JToggleButton and a PAShapeBar items
     *
     * @param drawPanel PASVGPanel
     * @param button JToggleButton
     * @param shapeBar PAShapeBar
     */
    public PADrawingShapeAction(PASVGPanel drawPanel, JToggleButton button, PAShapeBar shapeBar)
    {
        this.button = button;
        this.drawPanel = drawPanel;
        this.shapeBar = shapeBar;
        this.drawImage = drawPanel.svgImage;
        elementCollection = drawPanel.svgContainer.getSvgContainer();
        scale = drawPanel.getScale();
    }

    /**
     * Get shape attributes like fill,stroke and stroke width
     */
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
        scale = drawPanel.getScale();
        drawPanel.reDrawImage(scale);
        if (button.isSelected())
        {
            if ((PADrawingItem.buttonSelected != null) && (!PADrawingItem.buttonSelected.equals(button)))
            {
                PADrawingItem.buttonSelected.setSelected(false);
                PADrawingItem.buttonSelected.setBorder(null);
            }

            button.setBorder(BorderFactory.createLineBorder(new Color(35, 192, 255, 100), 1));
            PADrawingItem.buttonSelected = button;
        }
        else
        {
            PADrawingItem.buttonSelected = null;
            button.setBorder(null);
        }

        addActionToComponents();
    }

    /**
     * remove all existing mouse action listener for draw panel
     */
    public void removeAllActions()
    {
        for (int i = 0; i < drawPanel.getMouseListeners().length; i++)
        {
            drawPanel.removeMouseListener(drawPanel.getMouseListeners()[i]);
        }

        for (int i = 0; i < drawPanel.getMouseMotionListeners().length; i++)
        {
            drawPanel.removeMouseMotionListener(drawPanel.getMouseMotionListeners()[i]);
        }
    }

    /**
     * Add actions to JPanel
     */
    public abstract void addActionToComponents();

    /**
     *
     * This class is use to draw rectangle shape by mouse drag
     */
    public static class DrawRectangleAction extends PADrawingShapeAction
    {
        PARectangle rect;

        /**
         * Creates a new DrawRectangleAction which accept a PASVGPanel , a
         * JToggleButton and a PAShapeBar items
         *
         * @param drawPanel PASVGPanel
         * @param button JToggleButton
         * @param shapeBar PAShapeBar
         */
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

                    scale = drawPanel.getScale();
                    Point temp = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                    startDrag = temp;
                    endDrag = startDrag;
                    drawPanel.repaint();

                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    setShapeAttributes();
                    rect = makeRectangle(fill, stroke, strokeWidth,
                            startDrag.x, startDrag.y, endDrag.x, endDrag.y);

                    elementCollection.add(rect);
                    overwriteImage();

                    startDrag = null;
                    endDrag = null;
                    drawPanel.repaint();



                }

                @Override
                public void mouseDragged(MouseEvent e)
                {
                    endDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));

                    drawPanel.repaint();

                }

            };

            cursor = button.isSelected() ? Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR) : Cursor.getDefaultCursor();
            drawPanel.setCursor(cursor);
            removeAllActions();

            if (button.isSelected())
            {
                drawPanel.addMouseListener(mouseRectAction);
                drawPanel.addMouseMotionListener(mouseRectAction);
            }

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
            Rectangle2D.Double rect2D = rect.getRectangle2D();

            drawImage = drawPanel.svgImage;
            Graphics2D g2d = drawImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.scale(drawPanel.getScale(), drawPanel.getScale());
            g2d.setColor(fill);
            g2d.fill(rect2D);
            g2d.setColor(stroke);
            g2d.setStroke(new BasicStroke((float) rect.getStrokeWidth()));
            g2d.draw(rect2D);

        }

    }

    /**
     *
     * This class is use to draw circle shape by mouse drag
     */
    public static class DrawCircleAction extends PADrawingShapeAction
    {
        PACircle circle;

        /**
         * Creates a new DrawCircleAction which accept a PASVGPanel , a
         * JToggleButton and a PAShapeBar items
         *
         * @param drawPanel PASVGPanel
         * @param button JToggleButton
         * @param shapeBar PAShapeBar
         */
        public DrawCircleAction(PASVGPanel drawPanel, JToggleButton button,
                PAShapeBar shapeBar)
        {
            super(drawPanel, button, shapeBar);
        }

        @Override
        public void addActionToComponents()
        {
            MouseAdapter mouseCircleAction = new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        scale = drawPanel.getScale();
                        startDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
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
                        circle = makeCircle(fill, stroke, strokeWidth,
                                startDrag.x, startDrag.y, endDrag.x, endDrag.y);
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
                        endDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                        drawPanel.repaint();
                    }
                }

            };

            cursor = button.isSelected() ? Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR) : Cursor.getDefaultCursor();
            drawPanel.setCursor(cursor);
            removeAllActions();

            if (button.isSelected())
            {
                drawPanel.addMouseListener(mouseCircleAction);
                drawPanel.addMouseMotionListener(mouseCircleAction);
            }
        }

        private PACircle makeCircle(Color fill, Color stroke, double strokeWidth, int x1, int y1, int x2, int y2)
        {
            double cx, cy, r, x, y;
            x = x2 < x1 ? x2 : x1;
            y = y2 < y1 ? y2 : y1;
            r = (Math.abs(x1 - x2) > Math.abs(y1 - y2) ? Math.abs(x1 - x2) : Math.abs(y1 - y2)) / 2;
            cx = x + r;
            cy = y + r;
            return new PACircle(fill, stroke, (double) strokeWidth, cx, cy, r);
        }

        private void overwriteImage()
        {
            Ellipse2D.Double circle2D = circle.getEllipse2D();
            BasicStroke basicStroke = new BasicStroke((float) circle.getStrokeWidth());

            drawImage = drawPanel.svgImage;
            Graphics2D g2d = drawImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.scale(drawPanel.getScale(), drawPanel.getScale());
            g2d.setColor(fill);
            g2d.fill(circle2D);
            g2d.setColor(stroke);
            g2d.setStroke(basicStroke);
            g2d.draw(circle2D);
        }

    }

    /**
     * This class is use to draw line shape by mouse drag
     */
    public static class DrawLineAction extends PADrawingShapeAction
    {
        PALine line;

        /**
         * Creates a new DrawLineAction which accept a PASVGPanel , a
         * JToggleButton and a PAShapeBar items
         *
         * @param drawPanel PASVGPanel
         * @param button JToggleButton
         * @param shapeBar PAShapeBar
         */
        public DrawLineAction(PASVGPanel drawPanel, JToggleButton button,
                PAShapeBar shapeBar)
        {
            super(drawPanel, button, shapeBar);
        }

        @Override
        public void addActionToComponents()
        {
            MouseAdapter mouseLineAction = new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    if (button.isSelected())
                    {
                        scale = drawPanel.getScale();
                        startDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
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
                        line = makeLine(stroke, strokeWidth,
                                startDrag.x, startDrag.y, endDrag.x, endDrag.y);
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
                        endDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                        drawPanel.repaint();
                    }
                }

            };

            cursor = button.isSelected() ? Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR) : Cursor.getDefaultCursor();
            drawPanel.setCursor(cursor);
            removeAllActions();

            if (button.isSelected())
            {
                drawPanel.addMouseListener(mouseLineAction);
                drawPanel.addMouseMotionListener(mouseLineAction);
            }
        }

        private PALine makeLine(Color stroke, double strokeWidth, int x1, int x2, int y1, int y2)
        {
            return new PALine(stroke, (double) strokeWidth, (double) x1, (double) y1, (double) x2, (double) y2);
        }

        private void overwriteImage()
        {
            Line2D.Double line2D = line.getLine2D();

            BasicStroke basicStroke = new BasicStroke((float) line.getStrokeWidth());

            drawImage = drawPanel.svgImage;
            Graphics2D g2d = drawImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.scale(drawPanel.getScale(), drawPanel.getScale());
            g2d.setColor(stroke);
            g2d.setStroke(basicStroke);
            g2d.draw(line2D);
        }

    }
}
