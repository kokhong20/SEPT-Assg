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
    Point initialMouse, startSelect, endSelect, resizeStart;
    int elementIndex, changeX, changeY;
    boolean isResize;
    LinkedList<PASVGElement> elementTemp;

    public PASelectCursorAction(PASVGPanel drawPanel, JToggleButton button, PAShapeBar shapeBar)
    {
        super(drawPanel, button, shapeBar);
        elementTemp = new LinkedList<>();
    }

    @Override
    public void addActionToComponents()
    {
        cursor = Cursor.getDefaultCursor();
        drawPanel.setCursor(cursor);
        MouseAdapter mouseAdapter = new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                if (selectedElement != null)
                {
                    detectElementBounds(selectedElement, e.getX(), e.getY());
                }

                drawPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                scale = drawPanel.getScale();

                // for resizing use
                if (isResize)
                {
                    resizeStart = new Point(e.getX(), e.getY());
                    drawPanel.repaint();
                    return;
                }

                // For panning object use
                if ((!elementTemp.isEmpty())
                        || ((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null))
                {
                    drawBoundsForElement();

                    initialMouse = new Point(e.getX(), e.getY());
                }
                else
                {
                    drawPanel.zoomInOutSVG(scale);
                }

                //for Rectangle helper use
                if (startDrag == null && endDrag == null)
                {
                    System.out.println("abc");
                    startDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                    endDrag = startDrag;

                }

                // For select group use
                if (startSelect == null && endSelect == null)
                {
                    startSelect = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                    endSelect = startSelect;

                }

                drawPanel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (isResize)
                {
                    isResize = false;
                    return;
                }

                if ((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null)
                {
                    drawBoundsForElement();
                }
                else
                {
                    if (!((elementTemp = iterateContainer(elementCollection, startSelect, endSelect)).isEmpty()))
                    {

                        for (int index = elementTemp.size() - 1; index >= 0; index--)
                        {
                            PASVGElement element = elementTemp.get(index);
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
                                drawRectHighlight(((PARectangle) element));
                            }
                            else if (element instanceof PASVGGroup)
                            {
                            }
                        }
                    }
                    startDrag = null;
                    endDrag = null;
                    startSelect = null;
                    endSelect = null;
                }

                drawPanel.repaint();

            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                // For panning single object
                if ((selectedElement != null) && (!isResize))
                {
                    ((PARectangle) selectedElement).setX(((PARectangle) selectedElement).getX() + changeX);
                    ((PARectangle) selectedElement).setY(((PARectangle) selectedElement).getY() + changeY);
                    overWriteListElement(selectedElement, elementCollection);
                    changeX = e.getX() - initialMouse.x;
                    changeY = e.getY() - initialMouse.y;

                    initialMouse = new Point(e.getX(), e.getY());

                }
                // For panning multiple object
                else if (!elementTemp.isEmpty())
                {
                    for (int index = elementTemp.size() - 1; index >= 0; index--)
                    {
                        for (int i = elementCollection.size() - 1; i >= 0; i--)
                        {
                            PASVGElement element = elementCollection.get(i);

                            if (element.equals(elementTemp.get(index)))
                            {
                                if (element instanceof PALine)
                                {
                                    Line2D line = ((PALine) element).getLine2D();



                                }
                                else if (element instanceof PACircle)
                                {
//                            Ellipse2D ellipse = ((PACircle) element).getEllipse2D();
//                            if (rectPoint.contains(ellipse.getBounds2D()))
//                            {
//                                handleLine = null;
//                                selectedLine = null;
//                                handleRectangle = ellipse.getBounds2D();
//                                elementIndex = index;
//                                if (!elementArray.contains(element))
//                                {
//                                    elementArray.add(element);
//                                }
//                            }
                                }
                                else if (element instanceof PARectangle)
                                {
                                    PARectangle rect = ((PARectangle) element);
                                    ((PARectangle) elementCollection.get(i)).setX(rect.getX() + changeX);
                                    ((PARectangle) elementCollection.get(i)).setY(rect.getY() + changeY);
                                    ((PARectangle) elementCollection.get(i)).setRectangle2D();
                                    drawPanel.zoomInOutSVG(scale);
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
                        }
                    }


                    changeX = e.getX() - initialMouse.x;
                    changeY = e.getY() - initialMouse.y;

                    initialMouse = new Point(e.getX(), e.getY());

                }
                // For Resize use
                else if (isResize)
                {
                    resizeElement(selectedElement, changeX, changeY);
                    changeX = e.getX() - resizeStart.x;
                    changeY = e.getY() - resizeStart.y;

                    resizeStart = new Point(e.getX(), e.getY());
                }
                //For helper rectangle use
                else
                {
                    System.out.println("mousedragged punya");
                    endDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                    endSelect = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));

                }



                drawPanel.repaint();
            }

//            @Override
//            public void mouseMoved(MouseEvent e)
//            {
//                if (button.isSelected())
//                {
//                    cursor = selectedElement != null ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getDefaultCursor();
//                    drawPanel.setCursor(cursor);
//                    drawPanel.repaint();
//                }
//            }
        };

        removeAllActions();

        if (button.isSelected())
        {
            drawPanel.addMouseListener(mouseAdapter);
            drawPanel.addMouseMotionListener(mouseAdapter);
        }

    }

    public void drawRectHighlight(PARectangle rectangle)
    {
        scale = drawPanel.getScale();
        rectangle.setHandleArray(scale);

        for (int i = 0; i < rectangle.getHandleArray().length; i++)
        {
            Rectangle2D.Double rect = rectangle.getHandleArray()[i];
            g2D.setColor(Color.white);
            g2D.fill(rect);
            g2D.setColor(Color.black);
            g2D.draw(rect);
        }
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
                    elementIndex = index;
                    return element;
                }
            }
            else if (element instanceof PACircle)
            {
                Ellipse2D ellipse = ((PACircle) element).getEllipse2D();
                if (ellipse.contains(x, y))
                {
                    elementIndex = index;
                    return element;
                }
            }
            else if (element instanceof PARectangle)
            {
                Rectangle2D rect = ((PARectangle) element).getRectangle2D();
                if (rect.contains(x, y))
                {
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

        return null;
    }

    private LinkedList<PASVGElement> iterateContainer(LinkedList<PASVGElement> elementList, Point start, Point end)
    {
        LinkedList<PASVGElement> elementArray = new LinkedList<>();
        Rectangle2D.Double rectPoint = drawPanel.makeRectangle(start.x, start.y, end.x, end.y);
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
                    handleLine = null;
                    selectedLine = null;
                    handleRectangle = rect.getBounds2D();
                    elementIndex = index;
                    if (!elementArray.contains(element))
                    {
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
            ((PARectangle) elementList.get(elementIndex)).setWidth(element.getWidth());
            ((PARectangle) elementList.get(elementIndex)).setHeight(element.getHeight());
            ((PARectangle) elementList.get(elementIndex)).setRectangle2D();

            drawPanel.zoomInOutSVG(scale);
        }
        else if (elementItem instanceof PALine)
        {
        }
    }

    private void drawBoundsForElement()
    {
        g2D = (Graphics2D) drawPanel.svgImage.createGraphics();
        if (selectedElement instanceof PARectangle)
        {
            drawRectHighlight(((PARectangle) selectedElement));
        }
        else if (selectedElement instanceof PACircle)
        {
            drawEllipseHighlight(((PACircle) selectedElement).getEllipse2D().getBounds2D());
        }
        else if (selectedElement instanceof PALine)
        {
            drawLineHighlight(((PALine) selectedElement).getLine2D(), ((PALine) selectedElement));
        }
    }

    private void detectElementBounds(PASVGElement boundsElement, int x, int y)
    {
        if (boundsElement instanceof PARectangle)
        {
            int handlePosition;
            PARectangle rect = (PARectangle) boundsElement;
            Rectangle2D.Double handle = null;
            for (handlePosition = 0; handlePosition < rect.getHandleArray().length; handlePosition++)
            {
                if (rect.getHandleArray()[handlePosition].contains(x, y))
                {
                    handle = rect.getHandleArray()[handlePosition];
                    break;
                }

            }

            if (handle == null)
            {
                cursor = Cursor.getDefaultCursor();
                drawPanel.setCursor(cursor);
                isResize = false;
                return;
            }

            switch (handlePosition)
            {
                // North West
                case 0:
                    cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
                    break;
                // North
                case 1:
                    cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
                    break;
                // North East
                case 2:
                    cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
                    break;
                // East
                case 3:
                    cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
                    break;
                // South East
                case 4:
                    cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
                    break;
                // South
                case 5:
                    cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
                    break;
                // South West
                case 6:
                    cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
                    break;
                // West
                case 7:
                    cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
                    break;

            }

            isResize = true;
            drawPanel.setCursor(cursor);


        }
    }

    private void resizeElement(PASVGElement boundsElement, int changeX, int changeY)
    {
        if (boundsElement instanceof PARectangle)
        {
            double y = ((PARectangle) selectedElement).getY();
            double height = ((PARectangle) selectedElement).getHeight();
            double x = ((PARectangle) selectedElement).getX();
            double width = ((PARectangle) selectedElement).getWidth();
            switch (cursor.getType())
            {
                //North West
                case Cursor.NW_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setX(x + changeX);
                    ((PARectangle) selectedElement).setY(y + changeY);
                    ((PARectangle) selectedElement).setHeight((height - changeY));
                    ((PARectangle) selectedElement).setWidth((width - changeX));
                    break;
                // North
                case Cursor.N_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setY(y + changeY);
                    ((PARectangle) selectedElement).setHeight((height - changeY));
                    break;
                // North East
                case Cursor.NE_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setY(y + changeY);
                    ((PARectangle) selectedElement).setHeight((height - changeY));
                    ((PARectangle) selectedElement).setWidth((width + changeX));

                    break;
                // East
                case Cursor.E_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setWidth(width + changeX);
                    break;
                // South East
                case Cursor.SE_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setHeight((height + changeY));
                    ((PARectangle) selectedElement).setWidth((width + changeX));
                    break;
                // South
                case Cursor.S_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setHeight(height + changeY);
                    break;
                // South West
                case Cursor.SW_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setX(x +changeX);
                    ((PARectangle) selectedElement).setHeight((height+ changeY));
                    ((PARectangle) selectedElement).setWidth((width -changeX));
                    break;
                // West
                case Cursor.W_RESIZE_CURSOR:
                    ((PARectangle) selectedElement).setX(x + changeX);
                    ((PARectangle) selectedElement).setWidth(width - changeX);

                    break;
            }
            overWriteListElement(selectedElement, elementCollection);
        }
    }

}
