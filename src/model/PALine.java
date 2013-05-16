package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * @author bryantylai
 * @since 1.1
 * <p>This class creates a new PALine as a PASVGElement</p>
 */
public class PALine extends PASVGElement
{
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Line2D.Double line2D;
    private Rectangle2D.Double[] lineHandleArray;
    private final double rectangleHandleSize = 10f;

    /**
     * Creates a new empty PALine
     */
    public PALine()
    {
    }

    /**
     * Creates a new PALine with all attributes
     *
     * @param stroke
     * @param strokeWidth
     * @param x1 the x1 coordinate
     * @param x2 the x2 coordinate
     * @param y1 the y1 coordinate
     * @param y2 the y2 coordinate
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
     * Creates a new PALine which receives a node
     *
     * @param node
     */
    public PALine(Node node)
    {
        // TODO Auto-generated constructor stub
        super(node);
    }

    /**
     * Creates a new PALine which receives a node and groupStroke, groupWidth
     * from parent group
     *
     * @param node an Element node
     * @param groupStroke stroke of its group
     * @param groupWidth stroke width of its group
     * @param parentGroup the parent group
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
     * The Line2D.Double to set
     */
    public final void setLine2D()
    {
        line2D = new Line2D.Double(x1, y1, x2, y2);
    }

    /**
     * Drawing of line handles based on the scale
     *
     * @param scale the current scale
     * @return array of handles
     */
    private Rectangle2D.Double[] createLineHandle(double scale)
    {
        Rectangle2D.Double rect1, rect2;
        double x_1 = line2D.getX1() * scale;
        double x_2 = line2D.getX2() * scale;
        double y_1 = line2D.getY1() * scale;
        double y_2 = line2D.getY2() * scale;
        double strokewidth = this.getStrokeWidth();

        if (x_1 == x_2 || y_1 == y_2)
        {
            rect1 = new Rectangle2D.Double(x_1, y_1, rectangleHandleSize, rectangleHandleSize);
            rect2 = new Rectangle2D.Double(x_1, y_1, rectangleHandleSize, rectangleHandleSize);
            return new Rectangle2D.Double[]
            {
                rect1, rect2
            };
        }
        rect1 = new Rectangle.Double(x_1 < x_2 ? x_1 - (strokewidth / 2) : x_1 + (strokewidth / 2), y_1 < y_2 ? y_1 - (strokewidth / 2) : y_1 + (strokewidth / 2), rectangleHandleSize, rectangleHandleSize);

        rect2 = new Rectangle.Double(x_1 < x_2 ? x_2 + (strokewidth / 2) : x_2 - (strokewidth / 2), y_1 < y_2 ? y_2 + (strokewidth / 2) : y_2 - (strokewidth / 2), rectangleHandleSize, rectangleHandleSize);

        return new Rectangle2D.Double[]
        {
            rect1, rect2
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
