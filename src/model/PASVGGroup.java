/**
 *
 */
package model;

import java.awt.Color;
import java.util.LinkedList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author bryantylai
 * @since 1.1
 * <p>This class creates a new PASVGGroup element to be stored in the svg file
 * for grouped svg elements</p>
 */
public class PASVGGroup extends PASVGElement
{
    private LinkedList<PASVGElement> groupElementList;

    /**
     * Creates a new PASVGGroup which has an empty list
     */
    public PASVGGroup()
    {
        this.groupElementList = new LinkedList<>();
    }

    /**
     * Creates a new PASVGGroup which receives a node
     *
     * @param node an Element node
     */
    public PASVGGroup(Node node)
    {
        super(node);
        this.groupElementList = new LinkedList<>();
    }

    /**
     * Creates a new PASVGGroup which receives a node, groupFill, groupStroke,
     * groupWidth and parentGroup
     *
     * @param node an Element node
     * @param groupFill fill of its group
     * @param groupStroke stroke of its group
     * @param groupWidth stroke width of its group
     * @param parentGroup the parent group
     */
    public PASVGGroup(Node node, Color groupFill, Color groupStroke, double groupWidth, PASVGGroup parentGroup)
    {
        super(node, groupFill, groupStroke, groupWidth, parentGroup);
        this.groupElementList = new LinkedList<>();
    }

    /**
     * Creates a new PASVGGroup which receives a node, groupFill, groupStroke,
     * groupWidth and parentGroup
     *
     * @param groupElementList groupList
     * @param groupFill fill of its group
     * @param groupStroke stroke of its group
     * @param groupWidth stroke width of its group
     * @param parentGroup the parent group
     * @param id group id
     */
    public PASVGGroup(LinkedList<PASVGElement> groupElementList, Color groupFill, Color groupStroke, double groupWidth, PASVGGroup parentGroup, String id)
    {
        super(groupFill, groupStroke, groupWidth, parentGroup, id);
        this.groupElementList = groupElementList;
    }

    /**
     * Creates a new PASVGGroup with existing elements
     *
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

}
