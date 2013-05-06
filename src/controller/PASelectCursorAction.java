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
    Point initialMouse, startSelect, endSelect;
    Cursor cursor;
    int elementIndex, changeX, changeY;
    boolean isDraged;
    LinkedList<PASVGElement> elementTemp;

    public PASelectCursorAction(PASVGPanel drawPanel, JToggleButton button, PAShapeBar shapeBar)
    {
        super(drawPanel, button, shapeBar);
        elementTemp = new LinkedList<>();
    }

    @Override
    public void addActionToComponents()
    {
        MouseAdapter mouseAdapter = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
//                scale = drawPanel.getScale();
//                if (((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null))
//                {
//                    
//                }
//                else
//                {
//                   
//                }
//
//                drawPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                scale = drawPanel.getScale();

                if (startSelect == null && endSelect == null)
                {
                    startSelect = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                    endSelect = startSelect;

                }


                if ((!elementTemp.isEmpty())
                        || ((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null))
                {
//                    startDrag = null;
//                    endDrag = null;
                    drawBoundsForElement();

                    initialMouse = new Point(e.getX(), e.getY());
                }
                else
                {
                    drawPanel.zoomInOutSVG(scale);
                }


                if (startDrag == null && endDrag == null)
                {
                    startDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));
                    endDrag = startDrag;

                }

                drawPanel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if ((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null)
                {
                    drawBoundsForElement();
                }
                else
                {
                    if (!((elementTemp = iterateContainer(elementCollection, startDrag, endDrag)).isEmpty()))
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
                                drawRectHighlight(((PARectangle) element).getRectangle2D().getBounds2D());
                            }
                            else if (element instanceof PASVGGroup)
                            {
                            }
                        }
                    }
                    startDrag = null;
                    endDrag = null;
                }

                drawPanel.repaint();

            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                if ((selectedElement != null))
                {
                    ((PARectangle) selectedElement).setX(((PARectangle) selectedElement).getX() + changeX);
                    ((PARectangle) selectedElement).setY(((PARectangle) selectedElement).getY() + changeY);
                    overWriteListElement(selectedElement, elementCollection);
                    changeX = e.getX() - initialMouse.x;
                    changeY = e.getY() - initialMouse.y;

                    initialMouse = new Point(e.getX(), e.getY());

                }
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
                else
                {
                    endDrag = new Point((int) (e.getX() / scale), (int) (e.getY() / scale));

                }



                drawPanel.repaint();
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

            removeAllActions();

            if (button.isSelected())
            {
                drawPanel.addMouseListener(mouseAdapter);
                drawPanel.addMouseMotionListener(mouseAdapter);
            }

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
        handleRectangle = null;
        handleLine = null;
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
            drawRectHighlight(((PARectangle) selectedElement).getRectangle2D().getBounds2D());
        }
        else if (selectedElement instanceof PACircle)
        {
            drawRectHighlight(((PACircle) selectedElement).getEllipse2D().getBounds2D());
        }
        else if (selectedElement instanceof PALine)
        {
            drawLineHighlight(((PALine) selectedElement).getLine2D(), ((PALine) selectedElement));
        }
    }

}
