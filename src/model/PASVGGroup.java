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

    /**
     *
     */
    public PASVGGroup()
    {
        this.groupElementList = new LinkedList<>();
    }

    /**
     * Constructor which receives a node
     *
     * @param node
     */
    public PASVGGroup(Node node)
    {
        super(node);
        this.groupElementList = new LinkedList<>();
    }

    /**
     * Constructor which receives a node and groupFill, groupStroke, groupWidth
     * from parent group
     *
     * @param node
     * @param groupFill
     * @param groupStroke
     * @param groupWidth
     * @param parentGroup
     */
    public PASVGGroup(Node node, Color groupFill, Color groupStroke, double groupWidth, PASVGGroup parentGroup)
    {
        super(node, groupFill, groupStroke, groupWidth, parentGroup);
        this.groupElementList = new LinkedList<>();
    }
    
    /**
     * Constructor create new Group
     * @param elementList 
     */
    public PASVGGroup(LinkedList<PASVGElement> elementList)
    {
        super(null, null, 0);
        this.groupElementList = elementList;
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

        NodeList gList = gNode.getChildNodes();
        Color groupFill = getFill();
        Color groupStroke = getStroke();
        double groupWidth = getStrokeWidth();

        for (int index = 0; index < gList.getLength(); index++)
        {
            Node listNode = gList.item(index);
            switch (listNode.getNodeName())
            {
                case "g":
                    PASVGGroup nestedGroup = new PASVGGroup(listNode, groupFill, groupStroke, groupWidth, this);
                    nestedGroup.readAttributes();
                    groupElementList.add(nestedGroup);
                    break;
                    
                case "rect":
                    PARectangle newRect = new PARectangle(listNode, groupFill, groupStroke, groupWidth, this);
                    newRect.readAttributes();
                    groupElementList.add(newRect);                
                    break;
                    
                case "circle":
                    PACircle newCirc = new PACircle(listNode, groupFill, groupStroke, groupWidth, this);
                    newCirc.readAttributes();
                    groupElementList.add(newCirc);
                    break;
                    
                case "line":
                    PALine newLine = new PALine(listNode, groupStroke, groupWidth, this);
                    newLine.readAttributes();
                    groupElementList.add(newLine);
                    break;
            }
        }
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
    private void setGroupElementList(LinkedList<PASVGElement> groupElementList)
    {
        this.groupElementList = groupElementList;
    }

    /**
     * add a new element to group
     *
     * @param element PASVGElement to add
     * @return true if successfully added to groupElementList, return false
     * otherwise
     */
    public boolean addNewElementToGroup(PASVGElement element)
    {
        return groupElementList.add(element);
    }

    /**
     * add a new list of elements to group
     *
     * @param elementsList list of PASVGElements to add
     * @return true if successfully added to groupElementList, return false
     * otherwise
     */
    public boolean addNewElementsToGroup(LinkedList<PASVGElement> elementsList)
    {
        return groupElementList.addAll(elementsList);
    }

    /**
     * remove a specific element from PASVGGroup
     *
     * @param element PASVGElement to remove
     * @return true if successfully removed from groupElementList, return false
     * otherwise
     */
    public boolean removeElementFromGroup(PASVGElement element)
    {
        return groupElementList.remove(element);
    }

    /**
     * remove a specific list of element from PASVGGroup
     *
     * @param elementList list of PASVGElement to remove
     * @return true if successfully removed from groupElementList, return false
     * otherwise
     */
    public boolean removeElementsFromGroup(LinkedList<PASVGElement> elementsList)
    {
        return groupElementList.removeAll(elementsList);
    }

    /**
     * remove all elements from PASVGGroup
     *
     * @return all elements removed from PASVGGroup
     */
    public LinkedList<PASVGElement> removeAllElementsFromGroup()
    {
        LinkedList<PASVGElement> elements = groupElementList;
        groupElementList.clear();
        return elements;
    }

}
