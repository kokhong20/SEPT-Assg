package Model;
import java.awt.*;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class Drawings 
{

	private Color stroke;
	private double strokeWidth;
	
	// Constructor which receives a node
	// Call setColor and setUnit method to set value 
	public Drawings(Node node) 
	{
		// TODO Auto-generated constructor stub
		
		this.stroke = Coloring.setColor(((Element)node).getAttribute("stroke"));
		this.strokeWidth = Units.setUnit(((Element)node).getAttribute("stroke-width"));
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
	
	//public abstract void readAttributes();
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
	/*public String[] splitString(String inputString)
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
	}*/
}
