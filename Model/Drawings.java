package Model;
import java.awt.*;
import java.util.regex.Pattern;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class Drawings 
{

	private Color stroke;
	private double strokeWidth;
	private Node node;
	
	public Drawings(Node node) 
	{
		// TODO Auto-generated constructor stub
		this.node = node;
		
		this.stroke = Coloring.setColor(((Element)node).getAttribute("stroke"));
		this.strokeWidth = readAttributes((Element)node, "stroke-width");
	}
	
	public void setStrokeColor(Color color)
	{
		stroke = color;
	}
	
	public Color getStrokeColor()
	{
		return stroke;
	}

	public void setStrokeWidth(double width)
	{
		strokeWidth = width;
	}
	
	public double getStrokeWidth()
	{
		return strokeWidth;
	}
	
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public abstract void readAttributes();
	/*
	 * This function take one string as parameter and split the string into array of string
	 * which contain two new string with the splited contain.
	 * -string 1 contain only numeric character
	 * -string 2 contain only alphabetic character
	 * -If either string is empty means the inputString contain either no numeric character or alphabetic character
	 * -If both strings are empty means there only contain character " "
	 * 
	 * empty string can be checked by if(string.equals(""))
	 */
	public String[] splitString(String inputString)
	{
		char ch;
		int stringIndex=0;
		int count =0;
		String temp1,temp2;
		
		// Check whether inputString contain only one character 
		if(inputString.length()==1)
		{
			ch=inputString.charAt(0);
			
			//check whether ch is digit
			if(!Character.isDigit(ch))
			{
				if(ch==' ')
				{
					//return two empty string
					return new String[]{"",""};	
				}
				else
				{
					//return string 1 as empty string and string 2 which contain non-numeric character
					return new String[]{"",Character.toString(ch)};	
				}
			}
			else
			{
				//return string 1 as string which contain numeric character and string 2 as empty string
				return new String[]{Character.toString(ch),""};	
			}
		}
		//----------------------------------------------------//
		
		// If inputString contain more than 1 character
		for(int i =0;i<inputString.length();i++)
		{
			ch = inputString.charAt(i);
			
			if(!Character.isDigit(ch))
			{
				//check for character '.' 
				if(ch=='.')
				{
					if(count==0)
					{
						count++;
					}
					else
					{
						stringIndex=i;
						break;
					}
					
				}
				else
				{
					stringIndex=i;
					break;
				}
			}
			
			stringIndex++;
		}
		
		//creating 1st string which contain only integer or double
		temp1=inputString.substring(0,stringIndex);
		//creating 2nd string which contain only non-numeric character
		//if(stringIndex ==0) means that the string only contain numeric number
		if(stringIndex==0)
			temp2="";
		else
			temp2=inputString.substring(stringIndex);
		
		return new String[]{temp1,temp2};
	}

	// read elements and search for attribute
	// if not exist, return 0
	// otherwise, convert to double and return
	public double readAttributes(Element ele, String att)
	{
		if(ele.hasAttribute(att))
		{
			att = ele.getAttribute(att);
			if(!att.isEmpty())
			{
				if(Pattern.matches("(\\d+.\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att) || Pattern.matches("(\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att))
				{
					switch(att.substring(att.length() - 2))
					{
						case "em":
						case "ex":
							return 0;
						case "px":
							return Double.parseDouble(att.replace("px", ""));
						case "in":
							return Units.convertIN(att.replace("in", ""));
						case "cm":
							return Units.convertCM(att.replace("cm", ""));
						case "mm":
							return Units.convertMM(att.replace("mm", ""));
						case "pt":
							return Units.convertPT(att.replace("pt", ""));
						case "pc":
							return Units.convertPC(att.replace("pc", ""));
					}
				}
				else if(Pattern.matches("(\\d+)", att) || Pattern.matches("(\\d+.\\d+)", att))
				{
					return Double.parseDouble(att);
				}	
			}
		}
		
		return 0;
	}
}
