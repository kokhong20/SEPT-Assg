package Model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Circles extends Shapes
{

	private double cx;
	private double cy;
	private double r;
	
	public Circles(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
	}
	
	public void setCX(double cx)
	{
		this.cx = cx;
	}

	public void setCY(double cy)
	{
		this.cy = cy;
	}

	public void setR(double r)
	{
		this.r = r;
	}
	
	public double getCX()
	{
		return cx;
	}
	
	public double getCY()
	{
		return cy;
	}
	
	public double getR()
	{
		return r;
	}
	
	
	//Read and process attributes
	@Override
	public void readAttributes()
	{
		// TODO Auto-generated method stub
		String attributeTag;
		System.out.println("\nCurrent Element :" + getNode().getNodeName());
		 
		if (getNode().getNodeType() == Node.ELEMENT_NODE) 
		{
 			
		}
	
			
	}
	
	//Check,validate and get attribute tag's unit
	//return 0 if it doesn't contain the tag element or attribute tag format is invalid
	//return its unit if the attribute tag format is valid
	public double getAttributeUnit(String attributeTag)
	{
		
		Element element = (Element) getNode();
		
		if(element.hasAttribute(attributeTag))
		{
			System.out.println("Got cx attributes");
			//check format of attributeTag
			String attribute = element.getAttribute(attributeTag);
			String[] splitedAttribute = new String[2];
			splitedAttribute = splitString(attribute);
			
			// if attribute is empty or attribute is equal to " "
			if((attribute.length()==0)||((splitedAttribute[0]=="")&&(splitedAttribute[1]=="")))
				return 0.0;
			
			switch(splitedAttribute[1])
			{
				case "px" :
							return Double.parseDouble(splitedAttribute[0]);
				
				case "pt" :
					
				case "pc" :
				
				case "mm" :
				
				case "cm" :
				
				case "in" :
				
				default   : return 0;
						
			}			
		}
		else
		{
			System.out.println("No this attribute tag.Default is 0");
			return 0.0;
		}
		
	}
	
	

}