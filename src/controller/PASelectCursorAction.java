package controller;

import gui.PASVGPanel;
import gui.PAShapeBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
 * @since 2 May 2013
 * <p>This class creates a PASelectCursorAction to set action performed by clicking the select cursor
 * such as resize,select , move elements</p>
 */
public class PASelectCursorAction extends PADrawingShapeAction
{
    public static LinkedList<PASVGElement> elementTemp;
    private boolean isResize;
    private int elementIndex, changeX, changeY;
    private Graphics2D g2D;
    protected Point initialMouse, startSelect, endSelect, resizeStart;

    /**
     * Creates a new PASelectCursorAction which accept a PASVGPanel , a JToggleButton and a PAShapeBar items
     * @param drawPanel PASVGPanel 
     * @param button JToggleButton
     * @param shapeBar PAShapeBar
     */
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
                if ((elementTemp != null) && (elementTemp.size() == 1))
                {
                    detectElementBounds(elementTemp.getFirst(), e.getX(), e.getY());
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
                if (!elementTemp.isEmpty())
                {
                    drawPanel.reDrawImage(scale);
                    initialMouse = new Point(e.getX(), e.getY());
                }
                else
                {
                    drawPanel.reDrawImage(scale);
                }

                //for Rectangle helper use
                if (startDrag == null && endDrag == null)
                {
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
                // Resize to default
                if (isResize)
                {
                    startDrag = null;
                    endDrag = null;
                    startSelect = null;
                    endSelect = null;
                    isResize = false;
                    return;
                }

                drawBoundsForElements();
                startDrag = null;
                endDrag = null;
                startSelect = null;
                endSelect = null;
                drawPanel.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (!elementTemp.isEmpty() && !isResize)
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
                                    PALine lineList = ((PALine) elementCollection.get(i));
                                    lineList.setX1(lineList.getX1() + changeX);
                                    lineList.setX2(lineList.getX2() + changeX);
                                    lineList.setY1(lineList.getY1() + changeY);
                                    lineList.setY2(lineList.getY2() + changeY);
                                    lineList.setLine2D();
                                }
                                else if (element instanceof PACircle)
                                {
                                    PACircle circle = ((PACircle) element);
                                    PACircle circleList = ((PACircle) elementCollection.get(i));
                                    circleList.setCx(circle.getCx() + changeX);
                                    circleList.setCy(circle.getCy() + changeY);
                                    circleList.setEllipse2D();

                                }
                                else if (element instanceof PARectangle)
                                {
                                    PARectangle rect = ((PARectangle) element);
                                    PARectangle rectList = ((PARectangle) elementCollection.get(i));
                                    rectList.setX(rect.getX() + changeX);
                                    rectList.setY(rect.getY() + changeY);
                                    rectList.setRectangle2D();
                                }
                                else if (element instanceof PASVGGroup)
                                {
                                    PASVGGroup group = (PASVGGroup) element;
                                    moveAllGroupElements(group);
                                }
                                drawPanel.reDrawImage(scale);
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
                    resizeElement(elementTemp.getFirst(), changeX, changeY);
                    changeX = e.getX() - resizeStart.x;
                    changeY = e.getY() - resizeStart.y;

                    resizeStart = new Point(e.getX(), e.getY());
                }
                //For helper rectangle use
                else
                {
                    endDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                    endSelect = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                }

                drawPanel.repaint();
            }

        };

        removeAllActions();

        if (button.isSelected())
        {
            drawPanel.addMouseListener(mouseAdapter);
            drawPanel.addMouseMotionListener(mouseAdapter);
        }
    }

    /**
     * Draw rectangle's handles
     * @param rectangle The PARectangle object that need to display handles
     */
    private void drawRectHighlight(PARectangle rectangle)
    {
        for (int i = 0; i < rectangle.getHandleArray().length; i++)
        {
            Rectangle2D.Double rect = rectangle.getHandleArray()[i];
            g2D.setColor(Color.white);
            g2D.fill(rect);
            g2D.setColor(Color.black);
            g2D.draw(rect);
        }
    }
    
    /**
     * Draw circle's handles
     * @param circle The PACircle object that need to display handles 
     */
    private void drawEllipseHighlight(PACircle circle)
    {
        for (int i = 0; i < circle.getHandleArray().length; i++)
        {
            Rectangle2D.Double rect = circle.getHandleArray()[i];
            g2D.setColor(i == 2 ? Color.white : Color.black);
            g2D.fill(rect);
            g2D.setColor(Color.black);
            g2D.draw(rect);
        }
    }

    /**
     * Draw line's handles
     * @param line The PALine objects that need to display handles
     */
    private void drawLineHighlight(PALine line)
    {
        for (int i = 0; i < line.getHandleArray().length; i++)
        {
            Rectangle2D.Double rect = line.getHandleArray()[i];
            g2D.setColor(Color.white);
            g2D.fill(rect);
            g2D.setColor(Color.black);
            g2D.draw(rect);
        }
    }

    /**
     * Draw group's handles
     * @param arrayOfDouble The selected group handles array for 4 points
     */
    private void drawGroupHighlight(double[] arrayOfDouble)
    {
        double startX = arrayOfDouble[0];
        double startY = arrayOfDouble[1];
        double endX = arrayOfDouble[2];
        double endY = arrayOfDouble[3];

        Rectangle.Double rect1 = new Rectangle.Double(startX - 3.0, startY - 3.0, 6.0,
                6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect1);
        g2D.setColor(Color.black);
        g2D.draw(rect1);

        Rectangle.Double rect2 = new Rectangle.Double(endX - 3.0, startY - 3.0,
                6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect2);
        g2D.setColor(Color.black);
        g2D.draw(rect2);

        Rectangle.Double rect3 = new Rectangle.Double(startX - 3.0, endY - 3.0,
                6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect3);
        g2D.setColor(Color.black);
        g2D.draw(rect3);

        Rectangle.Double rect4 = new Rectangle.Double(endX - 3.0, endY - 3.0,
                6.0, 6.0);
        g2D.setColor(Color.white);
        g2D.fill(rect4);
        g2D.setColor(Color.black);
        g2D.draw(rect4);
    }

    /**
     * Detect PASVGElement either by mouse clicked or mouse dragged
     * @param elementList The linked list that store all PASVGElement
     * @param start The mouse start point
     * @param end The mouse end point
     * @return LinkedList of PASVGElement
     */
    private LinkedList<PASVGElement> iterateContainer(LinkedList<PASVGElement> elementList, Point start, Point end)
    {
        LinkedList<PASVGElement> elementArray = new LinkedList<>();
        Rectangle2D.Double rectPoint = drawPanel.makeRectangle(start.x, start.y, end.x, end.y);
        scale = drawPanel.getScale();

        if (start == end)
        {
            scale = drawPanel.getScale();
            for (int index = elementList.size() - 1; index >= 0; index--)
            {
                PASVGElement element = elementList.get(index);

                if (element instanceof PALine)
                {
                    PALine line = ((PALine) element);
                    line.setHandleArray(scale);
                    Rectangle2D.Double lineRectangle = new Rectangle2D.Double((double) start.x - 3.0, (double) start.y - 3.0, 6.0, 6.0);
                    if (line.getLine2D().intersects(lineRectangle))
                    {
                        elementIndex = index;
                        elementArray.add(element);
                        break;
                    }
                }
                else if (element instanceof PACircle)
                {
                    PACircle ellipse = ((PACircle) element);
                    ellipse.setHandleArray(scale);
                    if (ellipse.getEllipse2D().contains(start.x, start.y))
                    {
                        elementIndex = index;
                        elementArray.add(element);
                        break;
                    }
                }
                else if (element instanceof PARectangle)
                {
                    PARectangle rect = ((PARectangle) element);
                    rect.setHandleArray(scale);
                    if (rect.getRectangle2D().contains(start.x, start.y))
                    {
                        elementIndex = index;
                        elementArray.add(element);
                        break;
                    }
                }
                else if (element instanceof PASVGGroup)
                {
                    PASVGGroup group = (PASVGGroup) element;
                    double[] tempXY = getTempPoint(element);
                    double[] gb = getGroupBounds(group, tempXY[0], tempXY[1]);
                    int[] groupBounds =
                    {
                        (int) gb[0], (int) gb[1], (int) gb[2], (int) gb[3]
                    };
                    Rectangle2D.Double rectGroup = drawPanel.makeRectangle(groupBounds[0], groupBounds[1], groupBounds[2], groupBounds[3]);

                    if (rectGroup.contains(start.x, start.y))
                    {
                        elementArray.add(element);
                        break;
                    }
                }
            }
        }
        else
        {
            for (int index = 0; index < elementList.size(); index++)
            {
                PASVGElement element = elementList.get(index);

                if (element instanceof PALine)
                {
                    PALine line = ((PALine) element);
                    line.setHandleArray(scale);
                    if (rectPoint.contains(line.getLine2D().getBounds2D()))
                    {
                        elementArray.add(element);
                    }
                }
                else if (element instanceof PACircle)
                {
                    PACircle ellipse = ((PACircle) element);
                    ellipse.setHandleArray(scale);
                    if (rectPoint.contains(ellipse.getEllipse2D().getBounds2D()))
                    {
                        elementArray.add(element);
                    }
                }
                else if (element instanceof PARectangle)
                {
                    PARectangle rect = ((PARectangle) element);
                    rect.setHandleArray(scale);
                    if (rectPoint.contains(rect.getRectangle2D()))
                    {
                        elementArray.add(element);
                    }
                }
                else if (element instanceof PASVGGroup)
                {
                    PASVGGroup group = (PASVGGroup) element;
                    double[] tempXY = getTempPoint(element);
                    double[] gb = getGroupBounds(group, tempXY[0], tempXY[1]);
                    int[] groupBounds =
                    {
                        (int) gb[0], (int) gb[1], (int) gb[2], (int) gb[3]
                    };
                    Rectangle2D.Double rectGroup = drawPanel.makeRectangle(groupBounds[0], groupBounds[1], groupBounds[2], groupBounds[3]);

                    if (rectPoint.contains(rectGroup))
                    {
                        elementArray.add(element);
                    }
                }
            }
        }

        return elementArray;
    }

    /**
     * Overwrite existing PASVGElement object by redrawing
     * @param elementItem The PASVGElement that need to be redraw
     * @param elementList The linked list that store all PASVGElement
     */
    private void overWriteListElement(PASVGElement elementItem, LinkedList<PASVGElement> elementList)
    {
        if (!elementItem.isGrouped())
        {
            if (elementItem instanceof PACircle)
            {
                PACircle element = ((PACircle) elementItem);
                PACircle listCircle = ((PACircle) elementList.get(elementIndex));
                listCircle.setCx(element.getCx());
                listCircle.setCy(element.getCy());
                listCircle.setEllipse2D();

                drawPanel.reDrawImage(scale);
            }
            else if (elementItem instanceof PARectangle)
            {
                PARectangle element = ((PARectangle) elementItem);
                PARectangle listRect = ((PARectangle) elementList.get(elementIndex));
                listRect.setX(element.getX());
                listRect.setY(element.getY());
                listRect.setWidth(element.getWidth());
                listRect.setHeight(element.getHeight());
                listRect.setRectangle2D();

                drawPanel.reDrawImage(scale);
            }
            else if (elementItem instanceof PALine)
            {
                PALine element = ((PALine) elementItem);
                PALine listLine = ((PALine) elementList.get(elementIndex));
                listLine.setX1(element.getX1());
                listLine.setX2(element.getX2());
                listLine.setY1(element.getY1());
                listLine.setY2(element.getY2());
                listLine.setLine2D();

                drawPanel.reDrawImage(scale);
            }
        }
    }

    /**
     * Draw Bounds for multiple elements
     */
    protected void drawBoundsForElements()
    {
        elementTemp.clear();
        
        if (!((elementTemp = iterateContainer(elementCollection, startSelect, endSelect)).isEmpty()))
        {
            for (int index = elementTemp.size() - 1; index >= 0; index--)
            {
                PASVGElement element = elementTemp.get(index);
                g2D = drawPanel.svgImage.createGraphics();
                drawBoundsChecking(element);
            }
        }
    }

    /**
     * Check the element is instance of which class to draw
     * @param element PASVGElement that need to draw handles
     */
    private void drawBoundsChecking(PASVGElement element)
    {
        if (element instanceof PARectangle)
        {
            drawRectHighlight(((PARectangle) element));
        }
        else if (element instanceof PACircle)
        {
            drawEllipseHighlight((PACircle) element);
        }
        else if (element instanceof PALine)
        {
            drawLineHighlight((PALine) element);
        }
        else if (element instanceof PASVGGroup)
        {
            PASVGGroup group = (PASVGGroup) element;
            double[] pointXY = getTempPoint(element);
            double[] groupBounds = getGroupBounds(group, pointXY[0], pointXY[1]);
            drawGroupHighlight(groupBounds);
        }
    }

    /**
     * Get temporary x,y point for element to determine group handle position
     * @param element The PASVGElement to determine temporary position point
     * @return array of double temporary point which use to determine group handle
     *         position
     */
    private double[] getTempPoint(PASVGElement element)
    {
        double tempX = 0, tempY = 0;
        PASVGGroup group = (PASVGGroup) element;

        if (group.getGroupElementList() != null)
        {
            PASVGElement eleObj = group.getGroupElementList().getFirst();

            if (eleObj instanceof PARectangle)
            {
                PARectangle rect = ((PARectangle) eleObj);
                tempX = rect.getX();
                tempY = rect.getY();
            }
            else if (eleObj instanceof PACircle)
            {
                PACircle circle = ((PACircle) eleObj);
                tempX = circle.getCx();
                tempY = circle.getCy();
            }
            else if (eleObj instanceof PALine)
            {
                PALine line = ((PALine) eleObj);
                tempX = line.getX1();
                tempY = line.getY1();
            }
            else if (eleObj instanceof PASVGGroup)
            {
                return getTempPoint(eleObj);
            }
        }
        
        double[] doubleArray =
        {
            tempX, tempY
        };

        return doubleArray;
    }
 
    /**
     * Change cursor for element's resize cursor
     * @param boundsElement The PASVGElement to determine
     * @param x Mouse X Position
     * @param y Mouse Y Position
     */
    private void detectElementBounds(PASVGElement boundsElement, int x, int y)
    {
        int handlePosition;

        if (boundsElement instanceof PARectangle)
        {
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
        else if (boundsElement instanceof PACircle)
        {
            PACircle circle = (PACircle) boundsElement;

            if (!circle.getHandleArray()[2].contains(x, y))
            {
                cursor = Cursor.getDefaultCursor();
                drawPanel.setCursor(cursor);
                isResize = false;
                return;
            }

            cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
            isResize = true;
            drawPanel.setCursor(cursor);
        }
        else if (boundsElement instanceof PALine)
        {
            PALine line = (PALine) boundsElement;
            Rectangle2D.Double handle = null;

            for (handlePosition = 0; handlePosition < line.getHandleArray().length; handlePosition++)
            {
                if (line.getHandleArray()[handlePosition].contains(x, y))
                {
                    handle = line.getHandleArray()[handlePosition];
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
                    cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
                    break;
                // North
                case 1:
                    cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
                    break;
            }

            isResize = true;
            drawPanel.setCursor(cursor);
        }
    }

    /**
     * Resize PASVGElemenet
     * @param boundsElement The PASVGElement to resize
     * @param changeX Mouse Change X value
     * @param changeY Mouse Change Y value
     */
    private void resizeElement(PASVGElement boundsElement, int changeX, int changeY)
    {
        if (boundsElement instanceof PARectangle)
        {
            PARectangle rect = ((PARectangle) boundsElement);
            double y = rect.getY();
            double height = rect.getHeight();
            double x = rect.getX();
            double width = rect.getWidth();
            switch (cursor.getType())
            {
                //North West
                case Cursor.NW_RESIZE_CURSOR:
                    rect.setX(x + changeX);
                    rect.setY(y + changeY);
                    rect.setHeight((height - changeY));
                    rect.setWidth((width - changeX));
                    break;
                // North
                case Cursor.N_RESIZE_CURSOR:
                    rect.setY(y + changeY);
                    rect.setHeight((height - changeY));
                    break;
                // North East
                case Cursor.NE_RESIZE_CURSOR:
                    rect.setY(y + changeY);
                    rect.setHeight((height - changeY));
                    rect.setWidth((width + changeX));

                    break;
                // East
                case Cursor.E_RESIZE_CURSOR:
                    rect.setWidth(width + changeX);
                    break;
                // South East
                case Cursor.SE_RESIZE_CURSOR:
                    rect.setHeight((height + changeY));
                    rect.setWidth((width + changeX));
                    break;
                // South
                case Cursor.S_RESIZE_CURSOR:
                    rect.setHeight(height + changeY);
                    break;
                // South West
                case Cursor.SW_RESIZE_CURSOR:
                    rect.setX(x + changeX);
                    rect.setHeight((height + changeY));
                    rect.setWidth((width - changeX));
                    break;
                // West
                case Cursor.W_RESIZE_CURSOR:
                    rect.setX(x + changeX);
                    rect.setWidth(width - changeX);

                    break;
            }
        }
        else if (boundsElement instanceof PACircle)
        {
            PACircle circle = ((PACircle) boundsElement);
            double radius = circle.getR();
            circle.setR(radius + (changeX > changeY ? changeX : changeY) / 2);


        }
        else if (boundsElement instanceof PALine)
        {
            PALine line = ((PALine) boundsElement);
            double x1 = line.getX1();
            double x2 = line.getX2();
            double y1 = line.getY1();
            double y2 = line.getY2();

            switch (cursor.getType())
            {
                case Cursor.N_RESIZE_CURSOR:
                    line.setX1(x1 + changeX);
                    line.setY1(y1 + changeY);
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    line.setX2(x2 + changeX);
                    line.setY2(y2 + changeY);
                    break;
            }
        }

        overWriteListElement(boundsElement, elementCollection);
    }

    /**
     * Determine group element bounds by point x and point y
     * @param groupElement The PASVGGroup element
     * @param x Temporary point x
     * @param y Temporary point y
     * @return array of double which consists of group scope
     */
    private double[] getGroupBounds(PASVGGroup groupElement, double x, double y)
    {
        LinkedList<PASVGElement> groupElementList = groupElement.getGroupElementList();
        double startX = x, startY = y, endX = x, endY = y;

        for (int index = groupElementList.size() - 1; index >= 0; index--)
        {
            PASVGElement element = groupElementList.get(index);

            if (element instanceof PALine)
            {
                PALine lineElement = (PALine) element;
                double x1 = lineElement.getX1() * scale;
                double x2 = lineElement.getX2() * scale;
                double y1 = lineElement.getY1() * scale;
                double y2 = lineElement.getY2() * scale;

                if (x1 > x2)
                {
                    startX = startX > x2 ? x2 : startX;
                    endX = x1 > endX ? x1 : endX;
                }
                else
                {
                    startX = startX > x1 ? x1 : startX;
                    endX = x2 > endX ? x2 : endX;
                }
                if (y1 > y2)
                {
                    startY = startY > y2 ? y2 : startY;
                    endY = y1 > endY ? y1 : endY;
                }
                else
                {
                    startY = startY > y1 ? y1 : startY;
                    endY = y2 > endY ? y2 : endY;
                }
            }
            else if (element instanceof PACircle)
            {
                PACircle circleElement = (PACircle) element;
                Rectangle2D rect = circleElement.getEllipse2D().getBounds2D();
                double startCX = rect.getX() * scale;
                double startCY = rect.getY() * scale;
                double endCX = startCX + rect.getWidth() * scale;
                double endCY = startCY + rect.getHeight() * scale;

                startX = startX > startCX ? startCX : startX;
                startY = startY > startCY ? startCY : startY;
                endX = endX > endCX ? endX : endCX;
                endY = endY > endCY ? endY : endCY;
            }
            else if (element instanceof PARectangle)
            {
                double startRX = ((PARectangle) element).getX() * scale;
                double startRY = ((PARectangle) element).getY() * scale;
                double endRX = startRX + ((PARectangle) element).getWidth() * scale;
                double endRY = startRY + ((PARectangle) element).getHeight() * scale;

                startX = startX > startRX ? startRX : startX;
                startY = startY > startRY ? startRY : startY;
                endX = endX > endRX ? endX : endRX;
                endY = endY > endRY ? endY : endRY;
            }
            else if (element instanceof PASVGGroup)
            {
                double[] arrayOfDouble = getGroupBounds((PASVGGroup) element, x, y);

                startX = startX > arrayOfDouble[0] ? arrayOfDouble[0] : startX;
                startY = startY > arrayOfDouble[1] ? arrayOfDouble[1] : startY;
                endX = endX > arrayOfDouble[2] ? endX : arrayOfDouble[2];
                endY = endY > arrayOfDouble[3] ? endY : arrayOfDouble[3];
            }
        }

        double[] arrayOfDouble =
        {
            startX, startY, endX, endY
        };

        return arrayOfDouble;
    }

    /**
     * Move all elements inside group 
     * @param svgGroup The PASVGGroup element to be moved
     */
    private void moveAllGroupElements(PASVGGroup svgGroup)
    {
        LinkedList<PASVGElement> groupElementList = svgGroup.getGroupElementList();

        for (int index = groupElementList.size() - 1; index >= 0; index--)
        {
            PASVGElement element = groupElementList.get(index);

            if (element instanceof PALine)
            {
                PALine line = ((PALine) element);
                line.setX1(line.getX1() + changeX);
                line.setX2(line.getX2() + changeX);
                line.setY1(line.getY1() + changeY);
                line.setY2(line.getY2() + changeY);
                line.setLine2D();
            }
            else if (element instanceof PACircle)
            {
                PACircle circle = ((PACircle) element);
                circle.setCx(circle.getCx() + changeX);
                circle.setCy(circle.getCy() + changeY);
                circle.setEllipse2D();
            }
            else if (element instanceof PARectangle)
            {
                PARectangle rect = ((PARectangle) element);
                rect.setX(rect.getX() + changeX);
                rect.setY(rect.getY() + changeY);
                rect.setRectangle2D();
            }
            else if (element instanceof PASVGGroup)
            {
                PASVGGroup group = ((PASVGGroup) element);
                moveAllGroupElements(group);
            }
        }
    }

}
