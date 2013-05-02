/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PASVGPanel;
import gui.PAShapeBar;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import javax.swing.JToggleButton;
import model.PACircle;
import model.PALine;
import model.PARectangle;
import model.PASVGElement;
import model.PASVGGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author KokHong
 */
public class PASelectCursorAction extends PADrawingShapeAction
{
    PASVGElement selectedElement;
    Graphics2D g2D;
    Rectangle2D handleRectangle;
    Line2D handleLine;
    PALine selectedLine;

    public PASelectCursorAction(PASVGPanel drawPanel, JToggleButton button, PAShapeBar shapeBar)
    {
        super(drawPanel, button, shapeBar);
        g2D = (Graphics2D) drawPanel.getGraphics();
    }

    @Override
    public void addActionToComponents()
    {
        MouseAdapter mouseAdapter = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // TODO Auto-generated method stub
                if (button.isSelected())
                {
                    selectedElement = null;
                    System.out.println(elementCollection.getFirst());

                    if ((selectedElement = iterateContainer(elementCollection, e.getX(), e.getY())) != null)
                    {
                        drawPanel.repaint();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                // TODO Auto-generated method stub
//			for (Shape shapes : svgContainer.getShapesCollection().keySet())
//			{
//				if (shapes.contains(e.getX(), e.getY()))
//				{
//					selectedShape = shapes;
//					if (handleRectangle != null)
//					{
//						handleRectangle = shapes.getBounds2D();
//					}
//				} else
//				{
//					handleRectangle = null;
//				}
//				repaint();
//				x1 = e.getX();
//				y1 = e.getY();
//			}
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (button.isSelected())
                {
                    selectedElement = null;

                    if ((selectedElement = iterateContainer(elementCollection, e.getX(), e.getY())) != null)
                    {
                        drawPanel.repaint();
                    }
                    if (handleRectangle != null || handleLine != null)
                    {

                        g2D = (Graphics2D) drawPanel.svgImage.createGraphics();
                        if (selectedElement instanceof PARectangle)
                        {
                            drawRectHighlight(handleRectangle);
                        }
                        else if (selectedElement instanceof PACircle)
                        {
                            drawEllipseHighlight(handleRectangle);
                        }
                        else if (selectedElement instanceof PALine)
                        {
                            drawLineHighlight(handleLine, selectedLine);
                        }

                        drawPanel.repaint();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                // TODO Auto-generated method stub
//			for (Shape shapes : svgContainer.getShapesCollection().keySet())
//			{
//				if (shapes.contains(e.getX(), e.getY()))
//				{
//					PASVGElement svgElement;
//					svgElement = svgContainer.getShapesCollection().get(shapes);
//					int x = (int) ((PARectangle) svgElement).getX();
//					int y = (int) ((PARectangle) svgElement).getY();
//
//					handleRectangle = null;
//					selectedShape = shapes;
//					x2 = e.getX();
//					y2 = e.getY();
//
//					x = x + x2 - x1;
//					y = y + y2 - y1;
//
//					((PARectangle) svgElement).setX(x);
//					((PARectangle) svgElement).setY(y);
//
//					x1 = x2;
//					y1 = y2;
//					repaint();
//					return;
//				}
//			}
            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
            }

        };

        drawPanel.addMouseListener(mouseAdapter);
        drawPanel.addMouseMotionListener(mouseAdapter);
    }

    public void drawRectHighlight(Rectangle2D r)
    {
        System.out.println("Entered DrawRect");
        double x = r.getX();
        double y = r.getY();
        double w = r.getWidth();
        double h = r.getHeight();
        
        g2D.scale(1.5, 1.5);
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

        Rectangle.Double rect5 = new Rectangle.Double(x + (w / 2) - 3.0, y - 3.0, 6.0,
                6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect5);
        g2D.setColor(Color.black);
        g2D.draw(rect5);

        Rectangle.Double rect6 = new Rectangle.Double(x - 3.0, y + (h / 2) - 3.0,
                6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect6);
        g2D.setColor(Color.black);
        g2D.draw(rect6);

        Rectangle.Double rect7 = new Rectangle.Double(x + w - 3.0, y + (h / 2) - 3.0,
                6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect7);
        g2D.setColor(Color.black);
        g2D.draw(rect7);

        Rectangle.Double rect8 = new Rectangle.Double(x + (w / 2) - 3.0, y + h - 3.0,
                6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect8);
        g2D.setColor(Color.black);
        g2D.draw(rect8);
    }

    private void drawEllipseHighlight(Rectangle2D r)
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

    private void drawLineHighlight(Line2D r, PALine l)
    {
        // TODO Auto-generated method stub
        double x1 = r.getX1();
        double x2 = r.getX2();
        double y1 = r.getY1();
        double y2 = r.getY2();

        double w = l.getStrokeWidth();

        Rectangle.Double rect1 = new Rectangle.Double(x1 - (w / 2), y1 - (w / 2), 6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect1);
        g2D.setColor(Color.black);
        g2D.draw(rect1);

        Rectangle.Double rect2 = new Rectangle.Double(x2 + 3.0, y2 + 3.0, 6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect2);
        g2D.setColor(Color.black);
        g2D.draw(rect2);
    }

    private PASVGElement iterateContainer(LinkedList<PASVGElement> elementList, int x, int y)
    {
        for (int index = elementList.size() - 1; index >= 0; index--)
        {
            PASVGElement element = elementList.get(index);

            if (element instanceof PALine)
            {
                Line2D line = ((PALine) element).getLine2D();
                if (line.intersects(new Rectangle2D.Double((double) x - 3.0, (double) y - 3.0, 6.0, 6.0)))
                {
                    handleLine = line;
                    selectedLine = ((PALine) element);
                    handleRectangle = null;
                    return element;
                }
            }
            else if (element instanceof PACircle)
            {
                Ellipse2D ellipse = ((PACircle) element).getEllipse2D();
                if (ellipse.contains(x, y))
                {
                    handleLine = null;
                    selectedLine = null;
                    handleRectangle = ellipse.getBounds2D();
                    return element;
                }
            }
            else if (element instanceof PARectangle)
            {
                Rectangle2D rect = ((PARectangle) element).getRectangle2D();
                if (rect.contains(x, y))
                {
                    handleLine = null;
                    selectedLine = null;
                    handleRectangle = rect.getBounds2D();
                    return element;
                }
            }
            else if (element instanceof PASVGGroup)
            {
                LinkedList<PASVGElement> groupList = ((PASVGGroup) element).getGroupElementList();
                PASVGElement ele = null;
                if ((ele = iterateContainer(groupList, x, y)) != null)
                {
                    return ele;
                }
            }
        }

        return null;
    }

}
