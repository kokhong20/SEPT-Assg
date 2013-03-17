package Model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Circles extends Shapes
{
	private double cx;
	private double cy;
	private double r;

	// Constructor which receives a node
	// Call setUnit method to set value 
	public Circles(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
		
		this.cx = Units.setUnit(((Element)node).getAttribute("cx"));
		this.cy = Units.setUnit(((Element)node).getAttribute("cy"));
		this.r = Units.setUnit(((Element)node).getAttribute("r"));
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
	
	public double getEllipse2DX()
	{
		return (this.getCX()-this.getR());
		//return (this.getCX()-(Math.cos(Math.PI/4)*this.getR()));
	}
	
	public double getEllipse2DY()
	{
		return (this.getCY()-this.getR());
		//return (this.getCY()-(Math.cos(Math.PI/4)*this.getR()));
	}
	
	/*
	//Read and process attributes
	@Override
	public void readAttributes()
	{
		// TODO Auto-generated method stub
		double cx = getAttributeUnit("cx");
		double cy = getAttributeUnit("cy");
		double r = getAttributeUnit("r");
			
		setCX(cx);
		setCY(cy);
		setR(r);
		
		System.out.println("Set cx, cy and r");
	}
	
	//Check,validate and get attribute tag's unit
	//return 0 if it doesn't contain the tag element or attribute tag format is invalid
	//return its unit if the attribute tag format is valid
	public double getAttributeUnit(String attributeTag)
	{
		
		Element element = (Element) getNode();
		if(element.hasAttribute(attributeTag))
		{
			System.out.println("Got attributes");
			//check format of attributeTag
			String attribute = element.getAttribute(attributeTag);
			String[] splitedAttribute = new String[2];
			splitedAttribute = splitString(attribute);
			
			System.out.println("String 1 is"+splitedAttribute[0]);
			
			System.out.println("string 2 is"+splitedAttribute[1]);
			// if attribute is empty or attribute is equal to " "
			if((attribute.length()==0)||((splitedAttribute[0].equals(""))&&(splitedAttribute[1].equals(""))))
			{
				System.out.println("exit");
				return 0.0;
			}
			
			
			switch(splitedAttribute[1])
			{
				case "px" :
							return Double.parseDouble(splitedAttribute[0]);
				
				case "pt" :
					
				case "pc" :
				
				case "mm" :
				
				case "cm" :
				
				case "in" :
					
				case ""   : return Double.parseDouble(splitedAttribute[0]);
				
				default   : return 0.0;
						
			}			
		}
		else
		{
			System.out.println("No this attribute tag.Default is 0");
			return 0.0;
		}
		
	}*/
	
	

}