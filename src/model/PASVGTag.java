package model;

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
	//private Color stroke;
	//private Color fill;
	//private double strokeWidth;
	
	private Node node;
	
	public PASVGTag()
	{
		
	}
	
	public PASVGTag(Node node)
	{
		this.node = node;
	}
	
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	/*public void setStrokeWidth(double strokeWidth)
	{
		this.strokeWidth = strokeWidth;
	}
	
	public double getStrokeWidth()
	{
		return this.strokeWidth;
	}*/
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	/*public Color getFill()
	{
		return this.fill;
	}
	
	public void setFill(Color color)
	{
		this.fill = color;
	}

	public Color getStroke()
	{
		return this.stroke;
	}
	
	public void setStroke(Color color)
	{
		this.stroke = color;
	}*/
	
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	public Node getNode()
	{
		return this.node;
	}
	
	public void setSVGAttr()
	{
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			if(eNode.hasAttribute("width"))
				this.setWidth(PAUnit.setUnit(eNode.getAttribute("width"),false));
			else
				this.setWidth(500);
				
			if(eNode.hasAttribute("height"))
				this.setHeight(PAUnit.setUnit(eNode.getAttribute("height"),false));
			else
				this.setHeight(500);
			if (eNode.hasAttribute("viewBox"))
			{
				setViewbox(eNode.getAttribute("viewBox"));
			}

			//this.setStrokeWidth(Units.setUnit(eNode.getAttribute("stroke-width")));
			//this.setFill(Coloring.setColor(eNode.getAttribute("fill")));
			//this.setStroke(Coloring.setColor(eNode.getAttribute("stroke")));
		}
	}
	
	public double getInitX()
	{
		return this.initX;
	}

	public void setInitX(double initX)
	{
		this.initX = initX;
	}

	public double getInitY()
	{
		return this.initY;
	}

	public void setInitY(double initY)
	{
		this.initY = initY;
	}

	public double getViewHeight()
	{
		return this.viewHeight;
	}

	public void setViewHeight(double viewHeight)
	{
		this.viewHeight = viewHeight;
	}

	public double getViewWidth()
	{
		return this.viewWidth;
	}

	public void setViewWidth(double viewWidth)
	{
		this.viewWidth = viewWidth;
	}

	public void setViewbox(String value)
	{
		if (Pattern.matches("\\d+ *,* *\\d+ *,* *\\d+ *,* *\\d+", value))
		{
			value = value.replace(",", ";");
			value = value.replace(" ", ";");
			String values[]  = value.split(";");
			String attValues[] = new String[4];
			int j = 0;
			for(int i = 0; i < values.length; i++)
				if(!values[i].isEmpty())
					attValues[j++] = values[i];
			
			setInitX(PAUnit.setUnit(attValues[0],false));
			setInitY(PAUnit.setUnit(attValues[1], false));
			setViewWidth(PAUnit.setUnit(attValues[2], false));
			setViewHeight(PAUnit.setUnit(attValues[3], false));
		}
	}

}
