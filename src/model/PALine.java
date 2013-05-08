package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * @author bryantylai
 *
 */
public class PALine extends PASVGElement
{
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Line2D.Double line2D;
    private Rectangle2D.Double[] lineHandleArray;

    /**
     *
     */
    public PALine()
    {
    }

    /**
     * Constructor which create a new PALine object with all attributes
     *
     * @param stroke
     * @param strokeWidth
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public PALine(Color stroke, double strokeWidth, double x1, double x2, double y1, double y2)
    {
        super(stroke, strokeWidth);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        setLine2D();
    }

    /**
     * Constructor which receives a node
     *
     * @param node
     */
    public PALine(Node node)
    {
        // TODO Auto-generated constructor stub
        super(node);
    }

    /**
     * Constructor which receives a node and groupStroke, groupWidth from parent
     * group
     *
     * @param node
     * @param groupStroke
     * @param groupWidth
     * @param parentGroup
     */
    public PALine(Node node, Color groupStroke,
            double groupWidth, PASVGGroup parentGroup)
    {
        // TODO Auto-generated constructor stub
        super(node, null, groupStroke, groupWidth, parentGroup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readAttributes()
    {
        // TODO Auto-generated method stub
        if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
        {
            Element eNode = (Element) this.getNode();

            this.setX1(PAUnit.setUnit(eNode.getAttribute("x1"), DEFAULT_LENGTH));
            this.setX2(PAUnit.setUnit(eNode.getAttribute("x2"), DEFAULT_LENGTH));
            this.setY1(PAUnit.setUnit(eNode.getAttribute("y1"), DEFAULT_LENGTH));
            this.setY2(PAUnit.setUnit(eNode.getAttribute("y2"), DEFAULT_LENGTH));
            setLine2D();
        }
    }

    @Override
    public void setFill(Color c)
    {
    }

    @Override
    public Color getFill()
    {
        return null;
    }

    /**
     * @return the x1
     */
    public double getX1()
    {
        return x1;
    }

    /**
     * @param x1 the x1 to set
     */
    public void setX1(double x1)
    {
        this.x1 = x1;
    }

    /**
     * @return the x2
     */
    public double getX2()
    {
        return x2;
    }

    /**
     * @param x2 the x2 to set
     */
    public void setX2(double x2)
    {
        this.x2 = x2;
    }

    /**
     * @return the y1
     */
    public double getY1()
    {
        return y1;
    }

    /**
     * @param y1 the y1 to set
     */
    public void setY1(double y1)
    {
        this.y1 = y1;
    }

    /**
     * @return the y2
     */
    public double getY2()
    {
        return y2;
    }

    /**
     * @param y2 the y2 to set
     */
    public void setY2(double y2)
    {
        this.y2 = y2;
    }

    /**
     * @return the line2D
     */
    public Line2D.Double getLine2D()
    {
        return line2D;
    }

    /**
     *
     */
    public void setLine2D()
    {
        line2D = new Line2D.Double(x1, y1, x2, y2);
    }
    
//    private void drawLineHighlight(Line2D r, PALine l)
//    {
//         TODO Auto-generated method stub
//        scale = drawPanel.getScale();
//        double x1 = r.getX1() * scale;
//        double x2 = r.getX2() * scale;
//        double y1 = r.getY1() * scale;
//        double y2 = r.getY2() * scale;
//
//        double w = l.getStrokeWidth() * scale;
//
//        Rectangle.Double rect1 = new Rectangle.Double(x1 - (w / 2), y1 - (w / 2), 6.0, 6.0);
//        g2D.setColor(Color.white);
//        g2D.fill(rect1);
//        g2D.setColor(Color.black);
//        g2D.draw(rect1);
//
//        Rectangle.Double rect2 = new Rectangle.Double(x2 + 3.0, y2 + 3.0, 6.0, 6.0);
//        g2D.setColor(Color.white);
//        g2D.fill(rect2);
//        g2D.setColor(Color.black);
//        g2D.draw(rect2);
//    }

    private Rectangle2D.Double[] createLineHandle(double scale)
    {
        double x_1 = line2D.getX1() * scale;
        double x_2 = line2D.getX2() * scale;
        double y_1 = line2D.getY1() * scale;
        double y_2 = line2D.getY2() * scale;
        double w = this.getStrokeWidth() * scale;

        Rectangle2D.Double rect1 = new Rectangle.Double(x_1 -(w/2) , y_1-(w/2), w*2, w*2);
        Rectangle.Double rect2 = new Rectangle.Double(x_2-(w/2), y_2-(w/2), w*2, w*2);
        
        return new Rectangle2D.Double[]
        {
            rect1,rect2
        };

    }

    public void setHandleArray(double scale)
    {
        this.lineHandleArray = createLineHandle(scale);
    }

    public Rectangle2D.Double[] getHandleArray()
    {
        return this.lineHandleArray;
    }

}
