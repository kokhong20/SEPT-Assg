<<<<<<< HEAD
package model;

import java.awt.Color;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author LaiSaiHoo
 *
 */
public class PASVGTag implements PAAttributeConstant
{
    private double width;
    private double height;
    private double initX;
    private double initY;
    private double viewHeight;
    private double viewWidth;
    private double scaleX;
    private double scaleY;
    private Color stroke;
    private Color fill;
    private double strokeWidth;
    private Node node;
    private static double scale;

    /**
     * Constructor which receives a node
     *
     * @param node
     */
    public PASVGTag(Node node)
    {
        setNode(node);
        setSVGAttr();
    }

    /**
     * Set values to variables to current instance if an Element Node
     */
    private void setSVGAttr()
    {
        if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
        {
            Element eNode = (Element) this.getNode();

			this.setWidth(PAUnit.setUnit(eNode.getAttribute("width"), SVG_WIDTH));

			this.setHeight(PAUnit.setUnit(eNode.getAttribute("height"),
					SVG_HEIGHT));
			
			this.setViewbox(eNode.getAttribute("viewBox"));

		setScale(this.width, this.height, this.viewWidth, this.viewHeight);
		setScaleXY(getScaleX(), getScaleY());

		this.setStrokeWidth(PAUnit.setUnit(
				eNode.getAttribute("stroke-width"), STROKE_WIDTH));
		this.setStroke(PAColor.setColor(eNode.getAttribute("stroke"), STROKE));
		this.setFill(PAColor.setColor(eNode.getAttribute("fill"), FILL));
        }
    }

    /**
     * Read in value as value of svg viewbox attribute set it to current
     * instance variables
     *
     * @param value
     */
    public void setViewbox(String value)
    {
        if (Pattern.matches("\\d+ *,* *\\d+ *,* *\\d+ *,* *\\d+", value))
        {
            String values[] = value.split("[ *,* *]+");
            setInitX(PAUnit.setUnit(values[0], OTHER_UNIT));
            setInitY(PAUnit.setUnit(values[1], OTHER_UNIT));
            setViewWidth(PAUnit.setUnit(values[2], OTHER_UNIT));
            setViewHeight(PAUnit.setUnit(values[3], OTHER_UNIT));
        }
        else
        {
            setInitX(0);
            setInitY(0);
            setViewWidth(0);
            setViewHeight(0);
        }
    }

    /**
     * @return the width
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * @return the initX
     */
    public double getInitX()
    {
        return initX;
    }

    /**
     * @param initX the initX to set
     */
    public void setInitX(double initX)
    {
        this.initX = initX;
    }

    /**
     * @return the initY
     */
    public double getInitY()
    {
        return initY;
    }

    /**
     * @param initY the initY to set
     */
    public void setInitY(double initY)
    {
        this.initY = initY;
    }

    /**
     * @return the viewHeight
     */
    public double getViewHeight()
    {
        return viewHeight;
    }

    /**
     * @param viewHeight the viewHeight to set
     */
    public void setViewHeight(double viewHeight)
    {
        this.viewHeight = viewHeight;
    }

    /**
     * @return the viewWidth
     */
    public double getViewWidth()
    {
        return viewWidth;
    }

    /**
     * @param viewWidth the viewWidth to set
     */
    public void setViewWidth(double viewWidth)
    {
        this.viewWidth = viewWidth;
    }

    /**
     * @return the stroke
     */
    public Color getStroke()
    {
        return stroke;
    }

    /**
     * @param stroke the stroke to set
     */
    public void setStroke(Color stroke)
    {
        this.stroke = stroke;
    }

    /**
     * @return the fill
     */
    public Color getFill()
    {
        return fill;
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(Color fill)
    {
        this.fill = fill;
    }

    /**
     * @return the strokeWidth
     */
    public double getStrokeWidth()
    {
        return strokeWidth;
    }

    /**
     * @param strokeWidth the strokeWidth to set
     */
    public void setStrokeWidth(double strokeWidth)
    {
        this.strokeWidth = strokeWidth;
    }

    /**
     * @return the node
     */
    public Node getNode()
    {
        return node;
    }

    /**
     * @param node the node to set
     */
    private void setNode(Node node)
    {
        this.node = node;
    }

    /**
     * @return the scaleX
     */
    public double getScaleX()
    {
        return scaleX;
    }

    /**
     * @param scaleX the scaleX to set
     */
    public void setScaleX(double scaleX)
    {
        this.scaleX = scaleX;
    }

    /**
     * @return the scaleY
     */
    public double getScaleY()
    {
        return scaleY;
    }

    /**
     * @param scaleY the scaleY to set
     */
    public void setScaleY(double scaleY)
    {
        this.scaleY = scaleY;
    }

    /**
     *
     * @param oriWidth
     * @param oriHeight
     * @param viewWidth
     * @param viewHeight
     */
    public void setScale(double oriWidth, double oriHeight, double viewWidth,
            double viewHeight)
    {
        if (this.viewWidth == 0 && this.viewHeight == 0)
        {
            this.setScaleX(1);
            this.setScaleY(1);
        }
        else
        {
            this.setScaleX((oriWidth / viewWidth));
            this.setScaleY((oriHeight / viewHeight));
        }
    }

    public static double getScaleXY()
    {
        return scale;
    }

    public static void setScaleXY(double scaleX, double scaleY)
    {
        if (scaleX > scaleY)
        {
            PASVGTag.scale = scaleY;
        }
        else
        {
            PASVGTag.scale = scaleX;
        }
    }

}
=======
package model;

import java.awt.Color;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @author LaiSaiHoo
 * 
 */
public class PASVGTag
{
	private double width;
	private double height;
	private double initX;
	private double initY;
	private double viewHeight;
	private double viewWidth;
	private double scaleX;
	private double scaleY;
	private Color stroke;
	private Color fill;
	private double strokeWidth;
	private Node node;
	private double scale;

	/**
	 * Constructor which receives a node
	 * 
	 * @param node
	 */
	public PASVGTag(Node node)
	{
		setNode(node);
		setSVGAttr();
	}

	/**
	 * Set values to variables to current instance if an Element Node
	 */
	private void setSVGAttr()
	{
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();

			// if (eNode.hasAttribute("width") == true)
			// {
			// if (eNode.getAttribute("width").matches("\\d+%{1}"))
			// {
			// setWidth(PAUnit.setPercentage(eNode.getAttribute("width"),
			// 500));
			// }
			// else
			// {
			// setWidth(PAUnit.setUnit(eNode.getAttribute("width"), false));
			// }
			// }
			// else
			// {
			// setWidth(500);
			// }
			//
			// if (eNode.hasAttribute("height") == true)
			// {
			// if (eNode.getAttribute("height").matches("\\d+%{1}"))
			// {
			// setHeight(PAUnit.setPercentage(
			// eNode.getAttribute("height"), 500));
			// }
			// else
			// {
			// setHeight(PAUnit.setUnit(eNode.getAttribute("height"),
			// false));
			// }
			// }
			// else
			// {
			// setHeight(500);
			// }

			setWidth(eNode.hasAttribute("width") ? PAUnit.setUnit(
					eNode.getAttribute("width"), false, 500) : 500);
			setHeight(eNode.hasAttribute("height") ? PAUnit.setUnit(
					eNode.getAttribute("height"), false, 500) : 500);

			if (eNode.hasAttribute("viewBox"))
			{
				setViewbox(eNode.getAttribute("viewBox"));
			}
			else
			{
				setInitX(0);
				setInitY(0);
				setViewWidth(0);
				setViewHeight(0);
			}

			setScale(this.width, this.height, this.viewWidth, this.viewHeight);
			setScaleXY(getScaleX(), getScaleY());
			setStrokeWidth(PAUnit.setUnit(eNode.getAttribute("stroke-width"),
					true, 0));
			setStroke(PAColor.setColor(eNode.getAttribute("stroke"), true));
			setFill(PAColor.setColor(eNode.getAttribute("fill"), false));
		}
	}

	/**
	 * Read in value as value of svg viewbox attribute set it to current
	 * instance variables
	 * 
	 * @param value
	 */
	public void setViewbox(String value)
	{
		if (Pattern.matches("\\d+ *,* *\\d+ *,* *\\d+ *,* *\\d+", value))
		{
			String values[] = value.split("[ *,* *]+");
			setInitX(PAUnit.setUnit(values[0], false, 0));
			setInitY(PAUnit.setUnit(values[1], false, 0));
			setViewWidth(PAUnit.setUnit(values[2], false, 0));
			setViewHeight(PAUnit.setUnit(values[3], false, 0));
		}
		else
		{
			setInitX(0);
			setInitY(0);
			setViewWidth(0);
			setViewHeight(0);
		}
	}

	/**
	 * @return the width
	 */
	public double getWidth()
	{
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(double width)
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public double getHeight()
	{
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(double height)
	{
		this.height = height;
	}

	/**
	 * @return the initX
	 */
	public double getInitX()
	{
		return initX;
	}

	/**
	 * @param initX
	 *            the initX to set
	 */
	public void setInitX(double initX)
	{
		this.initX = initX;
	}

	/**
	 * @return the initY
	 */
	public double getInitY()
	{
		return initY;
	}

	/**
	 * @param initY
	 *            the initY to set
	 */
	public void setInitY(double initY)
	{
		this.initY = initY;
	}

	/**
	 * @return the viewHeight
	 */
	public double getViewHeight()
	{
		return viewHeight;
	}

	/**
	 * @param viewHeight
	 *            the viewHeight to set
	 */
	public void setViewHeight(double viewHeight)
	{
		this.viewHeight = viewHeight;
	}

	/**
	 * @return the viewWidth
	 */
	public double getViewWidth()
	{
		return viewWidth;
	}

	/**
	 * @param viewWidth
	 *            the viewWidth to set
	 */
	public void setViewWidth(double viewWidth)
	{
		this.viewWidth = viewWidth;
	}

	/**
	 * @return the stroke
	 */
	public Color getStroke()
	{
		return stroke;
	}

	/**
	 * @param stroke
	 *            the stroke to set
	 */
	public void setStroke(Color stroke)
	{
		this.stroke = stroke;
	}

	/**
	 * @return the fill
	 */
	public Color getFill()
	{
		return fill;
	}

	/**
	 * @param fill
	 *            the fill to set
	 */
	public void setFill(Color fill)
	{
		this.fill = fill;
	}

	/**
	 * @return the strokeWidth
	 */
	public double getStrokeWidth()
	{
		return strokeWidth;
	}

	/**
	 * @param strokeWidth
	 *            the strokeWidth to set
	 */
	public void setStrokeWidth(double strokeWidth)
	{
		this.strokeWidth = strokeWidth;
	}

	/**
	 * @return the node
	 */
	public Node getNode()
	{
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	private void setNode(Node node)
	{
		this.node = node;
	}

	/**
	 * @return the scaleX
	 */
	public double getScaleX()
	{
		return scaleX;
	}

	/**
	 * @param scaleX
	 *            the scaleX to set
	 */
	public void setScaleX(double scaleX)
	{
		this.scaleX = scaleX;
	}

	/**
	 * @return the scaleY
	 */
	public double getScaleY()
	{
		return scaleY;
	}

	/**
	 * @param scaleY
	 *            the scaleY to set
	 */
	public void setScaleY(double scaleY)
	{
		this.scaleY = scaleY;
	}

	/**
	 * 
	 * @param oriWidth
	 * @param oriHeight
	 * @param viewWidth
	 * @param viewHeight
	 */
	public void setScale(double oriWidth, double oriHeight, double viewWidth,
			double viewHeight)
	{
		if (this.viewWidth == 0 && this.viewHeight == 0)
		{
			this.setScaleX(1);
			this.setScaleY(1);
		}
		else
		{
			this.setScaleX((oriWidth / viewWidth));
			this.setScaleY((oriHeight / viewHeight));
		}
	}

	public double getScaleXY()
	{
		return scale;
	}

	public void setScaleXY(double scaleX, double scaleY)
	{
		if (scaleX > scaleY)
		{
			this.scale = scaleY;
		}
		else
		{
			this.scale = scaleX;
		}
	}

}
>>>>>>> PAShape
