/**
 * 
 */
package model;

import java.awt.Color;
import java.util.LinkedList;

import org.w3c.dom.Node;

/**
 * @author bryantylai
 *
 */
public class PASVGGroup extends PASVGElement implements PAFillable
{
	private Color fill;
	private LinkedList<PASVGElement> elementList;
	
	public PASVGGroup()
	{
		
	}
	
	/**
	 * Constructor which receives a node
	 * @param node
	 */
	public PASVGGroup(Node node)
	{
		super(node);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readAttributes()
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return the fill
	 */
	public Color getFill() {
		return fill;
	}

	/**
	 * @param fill the fill to set
	 */
	public void setFill(Color fill) {
		this.fill = fill;
	}

	/**
	 * @return the elementList
	 */
	public LinkedList<PASVGElement> getElementList()
	{
		return elementList;
	}

	/**
	 * @param elementList the elementList to set
	 */
	public void setElementList(LinkedList<PASVGElement> elementList)
	{
		this.elementList = elementList;
	}
}
