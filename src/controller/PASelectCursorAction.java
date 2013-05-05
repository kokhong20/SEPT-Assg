/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.PADrawingShapeAction.endDrag;
import static controller.PADrawingShapeAction.startDrag;
import gui.PASVGPanel;
import gui.PAShapeBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
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
    Point initialMouse;
    Cursor cursor;
    int elementIndex, changeX, changeY;
    LinkedList<PASVGElement> elementTemp;

    public PASelectCursorAction(PASVGPanel drawPanel, JToggleButton button, PAShapeBar shapeBar)
    {
        super(drawPanel, button, shapeBar);
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
                    scale = drawPanel.getScale();
                    if (((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null))
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
                    else
                    {
                        drawPanel.zoomInOutSVG(scale);
                        drawPanel.repaint();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                if (button.isSelected())
                {
                    if (selectedElement != null)
                    {
                        scale = drawPanel.getScale();
                        initialMouse = new Point(e.getX(), e.getY());
                        drawPanel.repaint();
                    }
                    else
                    {
                        scale = drawPanel.getScale();
                        startDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                        endDrag = startDrag;
                        drawPanel.repaint();
                    }
                }
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
                    if ((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null)
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
                    else
                    {

                        if ((elementTemp = iterateContainer(elementCollection, startDrag, endDrag)) != null)
                        {

                            for (int index = elementTemp.size() - 1; index >= 0; index--)
                            {
                                PASVGElement element = elementTemp.get(index);
                                System.out.println("Element in elementList"+element);
                                g2D = drawPanel.svgImage.createGraphics();
                                if (element instanceof PALine)
                                {
                                    drawLineHighlight(handleLine, selectedLine);

                                }
                                else if (element instanceof PACircle)
                                {
                                    drawEllipseHighlight(handleRectangle);
                                }
                                else if (element instanceof PARectangle)
                                {
                                    drawRectHighlight(((PARectangle) element).getRectangle2D().getBounds2D());
                                }
                                else if (element instanceof PASVGGroup)
                                {
                                }
                            }
                        }
                        startDrag = null;
                        endDrag = null;
                        drawPanel.repaint();
                    }
//                    selectedElement = null;
//
//                    if ((selectedElement = iterateContainer(elementCollection, e.getX(), e.getY())) != null)
//                    {
//                        drawPanel.repaint();
//                    }
//                    if (handleRectangle != null || handleLine != null)
//                    {
////                        g2D = (Graphics2D) drawPanel.getGraphics();
//                        g2D = (Graphics2D) drawPanel.svgImage.createGraphics();
//                        if (selectedElement instanceof PARectangle)
//                        {
//                            drawRectHighlight(handleRectangle);
//                        }
//                        else if (selectedElement instanceof PACircle)
//                        {
//                            drawEllipseHighlight(handleRectangle);
//                        }
//                        else if (selectedElement instanceof PALine)
//                        {
//                            drawLineHighlight(handleLine, selectedLine);
//                        }
//
//                        drawPanel.repaint();
//                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (button.isSelected())
                {
                    if ((handleRectangle != null || handleLine != null))
                    {
                        ((PARectangle) selectedElement).setX(((PARectangle) selectedElement).getX() + changeX);
                        ((PARectangle) selectedElement).setY(((PARectangle) selectedElement).getY() + changeY);

                        changeX = e.getX() - initialMouse.x;
                        changeY = e.getY() - initialMouse.y;

                        initialMouse = new Point(e.getX(), e.getY());

                        overWriteListElement(selectedElement, elementCollection);
                        drawPanel.repaint();
                    }
                    else
                    {
                        endDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                        drawPanel.repaint();
                    }
                }
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
                if (button.isSelected())
                {
                    cursor = selectedElement != null ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getDefaultCursor();
                    drawPanel.setCursor(cursor);
                    drawPanel.repaint();
                }
            }

        };

        for (int i = 0; i < drawPanel.getMouseListeners().length; i++)
        {
            drawPanel.removeMouseListener(drawPanel.getMouseListeners()[i]);
        }

        for (int i = 0; i < drawPanel.getMouseMotionListeners().length; i++)
        {
            drawPanel.removeMouseMotionListener(drawPanel.getMouseMotionListeners()[i]);
        }

        drawPanel.addMouseListener(mouseAdapter);

        drawPanel.addMouseMotionListener(mouseAdapter);

    }

    public void drawRectHighlight(Rectangle2D r)
    {
        scale = drawPanel.getScale();
        double x = r.getX() * scale;
        double y = r.getY() * scale;
        double w = r.getWidth() * scale;
        double h = r.getHeight() * scale;

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
        scale = drawPanel.getScale();
        double x = r.getX() * scale;
        double y = r.getY() * scale;
        double w = r.getWidth() * scale;
        double h = r.getHeight() * scale;

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
        scale = drawPanel.getScale();
        double x1 = r.getX1() * scale;
        double x2 = r.getX2() * scale;
        double y1 = r.getY1() * scale;
        double y2 = r.getY2() * scale;

        double w = l.getStrokeWidth() * scale;

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
                    elementIndex = index;
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
                    elementIndex = index;
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
                    elementIndex = index;
                    return element;
                }
            }
            else if (element instanceof PASVGGroup)
            {
                LinkedList<PASVGElement> groupList = ((PASVGGroup) element).getGroupElementList();
                PASVGElement ele = null;
                if ((ele = iterateContainer(groupList, x, y)) != null)
                {
                    elementIndex = index;
                    return ele;
                }
            }
        }
        handleRectangle = null;
        handleLine = null;
        return null;
    }

    private LinkedList<PASVGElement> iterateContainer(LinkedList<PASVGElement> elementList, Point start, Point end)
    {
        LinkedList<PASVGElement> elementArray = new LinkedList<>();
        Rectangle2D.Double rectPoint = drawPanel.makeRectangle(start.x, start.y, end.x, end.y);
        System.out.println(rectPoint.getBounds2D());
        for (int index = elementList.size() - 1; index >= 0; index--)
        {
            PASVGElement element = elementList.get(index);


            if (element instanceof PALine)
            {
                Line2D line = ((PALine) element).getLine2D();
                if (line.intersects(new Rectangle2D.Double((double) start.x - 3.0, (double) start.y - 3.0, 6.0, 6.0)))
                {
                    handleLine = line;
                    selectedLine = ((PALine) element);
                    handleRectangle = null;
                    elementIndex = index;
                    if (!elementArray.contains(element))
                    {
                        elementArray.add(element);
                    }
                }
            }
            else if (element instanceof PACircle)
            {
                Ellipse2D ellipse = ((PACircle) element).getEllipse2D();
                if (rectPoint.contains(ellipse.getBounds2D()))
                {
                    handleLine = null;
                    selectedLine = null;
                    handleRectangle = ellipse.getBounds2D();
                    elementIndex = index;
                    if (!elementArray.contains(element))
                    {
                        elementArray.add(element);
                    }
                }
            }
            else if (element instanceof PARectangle)
            {
                Rectangle2D rect = ((PARectangle) element).getRectangle2D();
                if (rectPoint.contains(rect))
                {
                    System.out.println("DEteceted");
                    handleLine = null;
                    selectedLine = null;
                    handleRectangle = rect.getBounds2D();
                    elementIndex = index;
                    if (!elementArray.contains(element))
                    {
                        System.out.println("ADded");
                        elementArray.add(element);
                    }
                }
            }
//            else if (element instanceof PASVGGroup)
//            {
//                LinkedList<PASVGElement> groupList = ((PASVGGroup) element).getGroupElementList();
//                PASVGElement ele = null;
//                if ((ele = iterateContainer(groupList, x, y)) != null)
//                {
//                    elementIndex = index;
//                                        if(!elementArray.contains(element))
//                    {
//                        elementArray.add(element);
//                    }
//                }
//            }
        }
//        handleRectangle = null;
//        handleLine = null;
        return elementArray;
    }

    private void overWriteListElement(PASVGElement elementItem, LinkedList<PASVGElement> elementList)
    {
        if (elementItem instanceof PACircle)
        {
        }
        else if (elementItem instanceof PARectangle)
        {
            PARectangle element = ((PARectangle) elementItem);
            ((PARectangle) elementList.get(elementIndex)).setX(element.getX());
            ((PARectangle) elementList.get(elementIndex)).setY(element.getY());
            ((PARectangle) elementList.get(elementIndex)).setRectangle2D();

            System.out.println();
            drawPanel.zoomInOutSVG(scale);
        }
        else if (elementItem instanceof PALine)
        {
        }
    }

}