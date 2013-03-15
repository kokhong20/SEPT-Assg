package Model;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.regex.*;


public class Lines extends Drawings
{

	private double x1;
	private double x2;
	private double y1;
	private double y2;
	
	public Lines(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
		
		this.x1 = readAttributes((Element) node, "x1");
		this.x2 = readAttributes((Element) node, "x2");
		this.y1 = readAttributes((Element) node, "y1");
		this.y2 = readAttributes((Element) node, "y2");
	}
	
	public void setX1(double x1)
	{
		this.x1 = x1;
	}

	public void setX2(double x2)
	{
		this.x2 = x2;
	}

	public void setY1(double y1)
	{
		this.y1 = y1;
	}

	public void setY2(double y2)
	{
		this.y2 = y2;
	}
	
	public double getX1()
	{
		return this.x1;
	}
	
	public double getX2()
	{
		return this.x2;
	}
	
	public double getY1()
	{
		return this.y1;
	}
	
	public double getY2()
	{
		return this.y2;
	}

	@Override
	public void readAttributes() {
		// TODO Auto-generated method stub
		
	}

	
	// read elements and search for attribute
	// if not exist, return 0
	// otherwise, convert to double and return
	public double readAttributes(Element ele, String att)
	{
		if(ele.hasAttribute(att))
		{
			return Double.parseDouble(checkAttribute(ele.getAttribute(att)));
		}
		
		return 0.0;
	}
	
	
	// Check whether attributes is valid
	// 1. If empty, return 0 straight away
	// 2. Else, check whether it matches regular expression
	// 3. If not, return 0, otherwise, convert to double
	// then convert to appropriate unit then return new value
	public String checkAttribute(String att)
	{
		if(!att.isEmpty())
		{
			if(Pattern.matches("(\\d+.\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att) || Pattern.matches("(\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att))
			{
				if(att.contains("em"))
				{
					return Double.toString(Double.parseDouble(att.replace("em", "")) * 1);
				}
				else if(att.contains("ex"))
				{
					return Double.toString(Double.parseDouble(att.replace("ex", "")) * 1);
				}
				else if(att.contains("px"))
				{
					return Double.toString(Double.parseDouble(att.replace("px", "")) * 1);
				}
				else if(att.contains("in"))
				{
					return Double.toString(Double.parseDouble(att.replace("in", "")) * 1);
				}
				else if(att.contains("cm"))
				{
					return Double.toString(Double.parseDouble(att.replace("cm", "")) * 1);
				}
				else if(att.contains("mm"))
				{
					return Double.toString(Double.parseDouble(att.replace("mm", "")) * 1);
				}
				else if(att.contains("pt"))
				{
					return Double.toString(Double.parseDouble(att.replace("pt", "")) * 1);
				}
				else if(att.contains("pc"))
				{
					return Double.toString(Double.parseDouble(att.replace("pc", "")) * 1);
				}
			}
			else if(Pattern.matches("(\\d+)", att) || Pattern.matches("(\\d+.\\d+)", att))
			{
				return att;
			}
		}
			
		return "0";
	}
}
