package gui;

import static controller.PAToolKitAction.DrawRectangleAction.endDrag;
import static controller.PAToolKitAction.DrawRectangleAction.startDrag;
import static gui.PADrawingItem.buttonSelected;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import model.PASVGContainer;

/**
 *
 * @author KokHong
 *
 */
public class PASVGPanel extends JPanel
{
    private int svgWidth;
    private int svgHeight;
    public BufferedImage svgImage;
    private PAMainFrame mainFrame;
    public PASVGContainer svgContainer;

    /**
     * constructor to define PASVGPanel for PAMainFrame
     *
     * @param mainFrame
     */
    public PASVGPanel(PASVGContainer svgContainer, BufferedImage svgImage)
    {
        this.svgContainer = svgContainer;
        this.svgImage = svgImage;
        initPanel();
    }

    /**
     * Initialise Svg Panel
     */
    private void initPanel()
    {
        svgWidth = (int) svgContainer.getSvgTag().getWidth();
        svgHeight = (int) svgContainer.getSvgTag().getHeight();
        Dimension size = new Dimension(svgWidth, svgHeight);

        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(svgImage, 0, 0, this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (startDrag != null && endDrag != null)
        {
            switch (buttonSelected.getToolTipText())
            {

                case "Rectangle":
                    g2d.setPaint(Color.LIGHT_GRAY);
                    Rectangle2D.Double rect = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                    g2d.draw(rect);
                    break;

                case "Line":
                    g2d.setPaint(Color.LIGHT_GRAY);
                    g2d.setStroke(new BasicStroke(2));
                    Line2D.Double line = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                    g2d.draw(line);
                    break;

                case "Circle":
                    g2d.setPaint(Color.LIGHT_GRAY);
                    Ellipse2D.Double circle = makeCircle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                    g2d.draw(circle);
                    break;



            }

            System.out.println("drawwwwww");
        }
    }

    private Rectangle2D.Double makeRectangle(int x1, int y1, int x2, int y2)
    {
        return new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Line2D.Double makeLine(int x1, int y1, int x2, int y2)
    {
        return new Line2D.Double(x1, y1, x2, y2);
    }

    private Ellipse2D.Double makeCircle(int x1, int y1, int x2, int y2)
    {
        return new Ellipse2D.Double(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

}
