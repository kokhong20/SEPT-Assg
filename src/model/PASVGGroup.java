/**
 * 
 */
package model;

import java.awt.Color;
import java.util.LinkedList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author bryantylai
 *
 */
public class PASVGGroup extends PASVGElement
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
		this.setElementList(new LinkedList<PASVGElement>());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readAttributes()
	{
		// TODO Auto-generated method stub
		/**
		 * Reading of child nodes in <g>
		 */
		Node gNode = this.getNode();
		Element gElement = (Element)gNode;
		
		this.setFill(gElement.hasAttribute("fill") ? PAColor.setColor(gElement.getAttribute("fill"), FILL) : null);
		this.setStroke(gElement.hasAttribute("stroke") ? PAColor.setColor(gElement.getAttribute("stroke"), STROKE) : null);
		this.setStrokeWidth(gElement.hasAttribute("stroke-width") ? PAUnit.setUnit(gElement.getAttribute("stroke-width"), DEFAULT_STROKE_WIDTH) : null);
		
		createGroups(gNode.getChildNodes(), getFill(), getStroke(), getStrokeWidth());
	}
	
	private void createGroups(NodeList gList, Color groupFill, Color groupStroke, double groupWidth)
	{
		for(int index = 0; index < gList.getLength(); index++)
		{
			Node node = gList.item(index);
			switch(node.getNodeName())
			{
			case "g":
				PASVGGroup nestedGroup = new PASVGGroup(node);
				nestedGroup.readAttributes();
				
				elementList.add(nestedGroup);
				break;
			case "rect":
                PARectangle newRect = new PARectangle(node, groupFill, groupStroke, groupWidth);
                newRect.readAttributes();

                elementList.add(newRect);
				break;
			case "circle":
                PACircle newCirc = new PACircle(node, groupFill, groupStroke, groupWidth);
                newCirc.readAttributes();

                elementList.add(newCirc);
				break;
			case "line":
                PALine newLine = new PALine(node, groupStroke, groupWidth);
                newLine.readAttributes();

                elementList.add(newLine);
				break;
			}
		}
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
