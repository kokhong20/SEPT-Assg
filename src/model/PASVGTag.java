
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
			this.setWidth(PAUnit.setUnit(
					eNode.getAttribute("width"), DEFAULT_SVG_SIZE));
			this.setHeight(PAUnit.setUnit(
					eNode.getAttribute("height"), DEFAULT_SVG_SIZE));

			setViewbox(eNode.getAttribute("viewBox"));

			this.setScale(this.width, this.height, this.viewWidth, this.viewHeight);
			this.setScaleXY(getScaleX(), getScaleY());
			this.setStrokeWidth(PAUnit.setUnit(eNode.getAttribute("stroke-width"),
					DEFAULT_STROKE_WIDTH));
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
			setInitX(PAUnit.setUnit(values[0], DEFAULT_LENGTH));
			setInitY(PAUnit.setUnit(values[1], DEFAULT_LENGTH));
			setViewWidth(PAUnit.setUnit(values[2], DEFAULT_LENGTH));
			setViewHeight(PAUnit.setUnit(values[3], DEFAULT_LENGTH));
		}
		else
		{
			setInitX(DEFAULT_LENGTH);
			setInitY(DEFAULT_LENGTH);
			setViewWidth(DEFAULT_LENGTH);
			setViewHeight(DEFAULT_LENGTH);
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

