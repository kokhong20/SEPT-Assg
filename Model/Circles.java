package Model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Circles extends Shapes
{
	private double cx;
	private double cy;
	private double r;

	public Circles()
	{
		
	}
	
	// Constructor which receives a node
	// Call setUnit method to set value 
	public Circles(Node node) 
	{
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
	
	
	//Read and process attributes
	@Override
	public void readAttributes()
	{
		// TODO Auto-generated method stub
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			this.setCX(Units.setUnit(eNode.getAttribute("cx")));
			this.setCY(Units.setUnit(eNode.getAttribute("cy")));
			this.setR(Units.setUnit(eNode.getAttribute("r")));
		}
	}


}