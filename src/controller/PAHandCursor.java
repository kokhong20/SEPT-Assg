/**
 * 
 */
package controller;

import gui.PASVGPanel;
import gui.PAShapeBar;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JViewport;

/**
 * @author SaiHoo
 * 
 */

public class PAHandCursor extends PADrawingShapeAction
{

    public PAHandCursor(PASVGPanel drawPanel, JToggleButton button,
            PAShapeBar shapeBar)
    {
        super(drawPanel, button, shapeBar);
    }

    @Override
    public void addActionToComponents()
    {
        MouseAdapter mousePanAction = new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {

                scale = drawPanel.getScale();
                startDrag = new Point((int) (e.getX() / scale),
                        (int) (e.getY() / scale));
                endDrag = startDrag;
                drawPanel.repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                startDrag = null;
                endDrag = null;
                drawPanel.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                JViewport vp = (JViewport) drawPanel.getParent().getParent();
                JComponent component = (JComponent) vp.getView();
                endDrag = new Point((int) (e.getX() / scale),
                        (int) (e.getY() / scale));

                Point viewPoint = vp.getViewPosition();

                viewPoint.translate(startDrag.x - endDrag.x, startDrag.y
                        - endDrag.y);

                component.scrollRectToVisible(new Rectangle(viewPoint, vp
                        .getSize()));
                drawPanel.repaint();
            }

        };
        cursor = button.isSelected() ? Cursor
                .getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor
                .getDefaultCursor();
        drawPanel.setCursor(cursor);
        removeAllActions();

        if (button.isSelected())
        {
            drawPanel.addMouseListener(mousePanAction);
            drawPanel.addMouseMotionListener(mousePanAction);
        }
    }

}
