package controller;

import gui.PASVGPanel;
import gui.PAShapeBar;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import javax.swing.JToggleButton;
import model.PACircle;
import model.PALine;
import model.PARectangle;
import model.PASVGElement;
import model.PASVGGroup;

/**
 *
 * @author KokHong
 * @since 9 May 2013
 * <p>This class creates a PAFillBucketAction which can fill a element
 * </p>
 */
public class PAFillBucketAction extends PADrawingShapeAction
{
    int elementIndex;
    PASVGElement selectedElement;

    /**
     * Creates a new PAFillBucketAction which accept a PASVGPanel , a
     * JToggleButton and a PAShapeBar items
     *
     * @param drawPanel PASVGPanel
     * @param button JToggleButton
     * @param shapeBar PAShapeBar
     */
    public PAFillBucketAction(PASVGPanel drawPanel, JToggleButton button, PAShapeBar shapeBar)
    {
        super(drawPanel, button, shapeBar);
    }

    @Override
    public void addActionToComponents()
    {
        cursor = Cursor.getDefaultCursor();
        drawPanel.setCursor(cursor);
        MouseAdapter mouseAdapter = new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                scale = drawPanel.getScale();

                if (((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null))
                {
                    drawPanel.reDrawImage(scale);
                }

                drawPanel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                // If Single object is selected
                if ((selectedElement = iterateContainer(elementCollection, (int) (e.getX() / scale), (int) (e.getY() / scale))) != null)
                {
                    setShapeAttributes();
                    PASVGElement element = elementCollection.get(elementIndex);
                    if (element instanceof PARectangle)
                    {
                        PARectangle rect = ((PARectangle) element);
                        rect.setFill(fill);
                        rect.setRectangle2D();
                    }
                    else if (element instanceof PACircle)
                    {
                        PACircle circle = ((PACircle) element);
                        circle.setFill(fill);
                        circle.setEllipse2D();
                    }

                    drawPanel.reDrawImage(scale);

                }
                drawPanel.repaint();
                selectedElement = null;
            }

        };

        removeAllActions();

        if (button.isSelected())
        {
            drawPanel.addMouseListener(mouseAdapter);
            drawPanel.addMouseMotionListener(mouseAdapter);
        }

    }

    private PASVGElement iterateContainer(LinkedList<PASVGElement> elementList, int x, int y)
    {
        scale = drawPanel.getScale();
        for (int index = elementList.size() - 1; index >= 0; index--)
        {
            PASVGElement element = elementList.get(index);

            if (element instanceof PALine)
            {
                PALine line = ((PALine) element);
                line.setHandleArray(scale);
                Rectangle2D.Double lineRectangle = new Rectangle2D.Double((double) x - 3.0, (double) y - 3.0, 6.0, 6.0);
                if (line.getLine2D().intersects(lineRectangle))
                {
                    elementIndex = index;
                    return element;
                }
            }
            else if (element instanceof PACircle)
            {
                PACircle ellipse = ((PACircle) element);
                ellipse.setHandleArray(scale);
                if (ellipse.getEllipse2D().contains(x, y))
                {
                    elementIndex = index;
                    return element;
                }
            }
            else if (element instanceof PARectangle)
            {
                PARectangle rect = ((PARectangle) element);
                rect.setHandleArray(scale);
                if (rect.getRectangle2D().contains(x, y))
                {
                    elementIndex = index;
                    return element;
                }
            }
            else if (element instanceof PASVGGroup)
            {
                LinkedList<PASVGElement> groupList = ((PASVGGroup) element).getGroupElementList();
                PASVGElement ele;
                if ((ele = iterateContainer(groupList, x, y)) != null)
                {
                    elementIndex = index;
                    return ele;
                }
            }
        }

        return null;
    }

}
