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
	private LinkedList<PASVGElement> groupElementList;
	
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
		this.setGroupElementList(new LinkedList<PASVGElement>());
	}

	public PASVGGroup(Node node, Color groupFill, Color groupStroke, double groupWidth)
	{
		super(node, groupFill, groupStroke, groupWidth);		

		Element gElement = (Element)node;

		this.setFill(gElement.hasAttribute("fill") ? PAColor.setColor(gElement.getAttribute("fill"), FILL) : (this.isGrouped()) ? getFill() : null);
		this.setStroke(gElement.hasAttribute("stroke") ? PAColor.setColor(gElement.getAttribute("stroke"), STROKE) : (this.isGrouped()) ? getStroke() : null);
		this.setStrokeWidth(gElement.hasAttribute("stroke-width") ? PAUnit.setUnit(gElement.getAttribute("stroke-width"), DEFAULT_STROKE_WIDTH) : (this.isGrouped()) ? getStrokeWidth() : null);
		
		this.setGroupElementList(new LinkedList<PASVGElement>());
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
				
				groupElementList.add(nestedGroup);
				break;
			case "rect":
                PARectangle newRect = new PARectangle(node, groupFill, groupStroke, groupWidth);
                newRect.readAttributes();

                groupElementList.add(newRect);
				break;
			case "circle":
                PACircle newCirc = new PACircle(node, groupFill, groupStroke, groupWidth);
                newCirc.readAttributes();

                groupElementList.add(newCirc);
				break;
			case "line":
                PALine newLine = new PALine(node, groupStroke, groupWidth);
                newLine.readAttributes();

                groupElementList.add(newLine);
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
	 * @return the groupElementList
	 */
	public LinkedList<PASVGElement> getGroupElementList()
	{
		return groupElementList;
	}

	/**
	 * @param groupElementList the groupElementList to set
	 */
	public void setGroupElementList(LinkedList<PASVGElement> groupElementList)
	{
		this.groupElementList = groupElementList;
	}
}
