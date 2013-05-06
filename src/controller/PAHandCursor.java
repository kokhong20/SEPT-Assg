/**
 * 
 */
package controller;

import gui.PASVGPanel;
import gui.PAShapeBar;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;

/**
 * @author Owner
 * 
 */

public class PAHandCursor extends PADrawingShapeAction
{
	public static Point startDrag, endDrag;
	public static Point oriPosition;
	public Point diff;
	public double scale;

	public PAHandCursor(PASVGPanel drawPanel, JToggleButton button,
			PAShapeBar shapeBar)
	{
		super(drawPanel, button, shapeBar);

	}

	public void addActionToComponents()
	{
		MouseAdapter mouseRectAction = new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (button.isSelected())
				{
					scale = drawPanel.getScale();
					startDrag = new Point((int) (e.getX() / scale),
							(int) (e.getY() / scale));
					endDrag = startDrag;
					drawPanel.repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				if (button.isSelected())
				{
					startDrag = null;
					endDrag = null;
					oriPosition = null;
					drawPanel.repaint();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				if (button.isSelected())
				{
					oriPosition = new Point(drawPanel.getX(), drawPanel.getY());
					endDrag = new Point((int) (e.getX() / scale),
							(int) (e.getY() / scale));
					diff = new Point((endDrag.x - startDrag.x),
							(endDrag.y - startDrag.y));
					int maxHeight = drawPanel.getRootPane().getHeight() - 30 - 24 - 15;
					int maxWidth = drawPanel.getRootPane().getWidth() - 15;
					Point set = new Point(oriPosition.x + diff.x, oriPosition.y
							+ diff.y);
					if (set.x > 0)
					{
						set.x = 0;
					}
					if (set.y > 0)
					{
						set.y = 0;
					}

					if ((maxWidth - set.x) >= drawPanel.getWidth())
					{
						System.out.println("in");
						set.x = -(drawPanel.getWidth() - maxWidth);
					}
					if ((maxHeight - set.y) >= drawPanel.getHeight())
					{
						System.out.println("out");
						set.y = -(drawPanel.getHeight() - maxHeight);
					}
					drawPanel.setLocation(set.x, set.y);
					drawPanel.repaint();
				}
			}

		};
		cursor = button.isSelected() ? Cursor
				.getPredefinedCursor(Cursor.MOVE_CURSOR) : Cursor
				.getDefaultCursor();
		drawPanel.setCursor(cursor);
		
		removeAllActions();
		if (button.isSelected())
		{
			drawPanel.addMouseListener(mouseRectAction);
			drawPanel.addMouseMotionListener(mouseRectAction);
		}
	}

}
