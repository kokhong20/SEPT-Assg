package model;

import java.util.LinkedList;

/**
 * @author bryantylai/LaiSaiHoo
 * @since 1.0
 * @version 1.1
 * <p>This class creates a container which stores the svg tag and all svg elements</p>
 */
public class PASVGContainer
{
    private PASVGTag svgTag;
    private LinkedList<PASVGElement> svgContainer;

    /**
     * Creates a PASVGContainer with only svgTag such that from a newly created svg
     *
     * @param svgTag a PASVGTag created based on the svg tag of svg file
     */
    public PASVGContainer(PASVGTag svgTag)
    {
        setSvgTag(svgTag);
        setSvgContainer(new LinkedList<PASVGElement>());
    }

    /**
     * Creates a PASVGContainer with a pre-created list of shapes such that from existing svg
     *
     * @param svgTag a PASVGTag created based on the svg tag of svg file
     * @param shapes list of shapes from svg file
     */
    public PASVGContainer(PASVGTag svgTag, LinkedList<PASVGElement> shapes)
    {
        setSvgTag(svgTag);
        setSvgContainer(shapes);
    }

    /**
     * @return the svgTag
     */
    public PASVGTag getSvgTag()
    {
        return svgTag;
    }

    /**
     * @param svgTag the svgTag to set
     */
    private void setSvgTag(PASVGTag svgTag)
    {
        this.svgTag = svgTag;
    }

    /**
     * @return the svgContainer
     */
    public LinkedList<PASVGElement> getSvgContainer()
    {
        return svgContainer;
    }

    /**
     * @param svgContainer the svgContainer to set
     */
    private void setSvgContainer(LinkedList<PASVGElement> svgContainer)
    {
        this.svgContainer = svgContainer;
    }

    /**
     * Add a new shape
     *
     * @param shape
     * @return true if shape is successfully added, return false otherwise
     */
    public boolean addNewShape(PASVGElement shape)
    {
        return svgContainer.add(shape);
    }

    /**
     * Add a new group
     *
     * @param group
     * @return true if group is successfully added, return false otherwise
     */
    public boolean addNewGroup(PASVGGroup group)
    {
        return svgContainer.add(group);
    }

    /**
     * Create a new group and add a single PASVGElement to end of svgContainer
     *
     * @param shape a PASVGElement
     * @return true if shape is added, return false otherwise
     */
    public boolean createGroupAndAddShape(PASVGElement shape)
    {
        shape.setGrouped(true);
        PASVGGroup newGroup = new PASVGGroup();
        newGroup.addNewElementToGroup(shape);
        return svgContainer.add(newGroup);
    }

    /**
     * Create a new group and add a group of PASVGElement to end of svgContainer
     *
     * @param shapes list of PASVGElement
     * @return true if list of shapes is added, return false otherwise
     */
    public boolean createGroupAndAddShapes(LinkedList<PASVGElement> shapes)
    {
        for (int index = 0; index < shapes.size(); index++)
        {
            PASVGElement element = shapes.get(index);
            element.setGrouped(true);
        }

        PASVGGroup newGroup = new PASVGGroup();
        newGroup.addNewElementsToGroup(shapes);
        return svgContainer.add(newGroup);
    }

    /**
     * Add a single shape to the end of an existing group
     *
     * @param group existing PASVGGroup
     * @param shape a PASVGElement
     * @return true if PASVGElement is added to group, return false otherwise
     */
    public boolean addShapeToGroup(PASVGGroup group, PASVGElement shape)
    {
        return group.addNewElementToGroup(shape);
    }

    /**
     * Add a group of shapes to the end of an existing group
     *
     * @param group existing PASVGGroup
     * @param shape list of PASVGElement
     * @return true if list of PASVGElement is added to group, return false
     * otherwise
     */
    public boolean addShapesToGroup(PASVGGroup group, LinkedList<PASVGElement> shapes)
    {
        return group.addNewElementsToGroup(shapes);
    }

    /**
     * Permanently remove an element
     *
     * @param element a PASVGElement
     * @return true if successfully removed, return false otherwise
     */
    public boolean removeElement(PASVGElement element)
    {
        return svgContainer.remove(element);
    }

    /**
     * Remove a particular PASVGElement from an existing group and add it
     * directly after that group
     *
     * @param group existing PASVGGroup
     * @param shapeToRemove a PASVGElement
     * @return true if PASVGElement successfully removed from group, return
     * false otherwise
     */
    public boolean removeFromGroup(PASVGGroup group, PASVGElement shapeToRemove)
    {
        if (group.removeElementFromGroup(shapeToRemove))
        {
            svgContainer.add(svgContainer.indexOf(group) + 1, shapeToRemove);
            return true;
        }

        return false;
    }

    /**
     * Remove a list of PASVGElement from an existing group and add the list
     * directly after that group
     *
     * @param group existing PASVGGroup
     * @param shapes list of PASVGElement
     * @return true if list of PASVGElement successfully removed from group,
     * return false otherwise
     */
    public boolean removeListFromGroup(PASVGGroup group, LinkedList<PASVGElement> shapes)
    {
        if (group.removeElementsFromGroup(shapes))
        {
            return svgContainer.addAll(svgContainer.indexOf(group) + 1, shapes);
        }

        return false;
    }

    /**
     * Remove all PASVGElement from an existing group and keep its position
     *
     * @param group existing PASVGGroup
     * @return true if all PASVGElement successfully removed from group, return
     * false otherwise
     */
    public boolean removeAllFromGroup(PASVGGroup group)
    {
        LinkedList<PASVGElement> elements;

        if ((elements = group.removeAllElementsFromGroup()) != null)
        {
            return svgContainer.addAll(svgContainer.indexOf(group) + 1, elements);
        }

        return false;
    }

    /**
     * Move an element to other position
     *
     * @param elementToMove a PASVGElement 
     * @param beforeThis a PASVGElement to be placed before
     * @return true if successfully moved, return false otherwise
     */
    public boolean moveElement(PASVGElement elementToMove, PASVGElement beforeThis)
    {
        if (svgContainer.remove(elementToMove))
        {
            svgContainer.add(svgContainer.indexOf(beforeThis), elementToMove);
        }

        return false;
    }

    /**
     * Move a list of elements to other position
     *
     * @param elementsToMove list of PASVGElement
     * @param beforeThis a PASVGElement to be placed before
     * @return true if successfully moved, return false otherwise
     */
    public boolean moveElements(LinkedList<PASVGElement> elementsToMove, PASVGElement beforeThis)
    {
        if (svgContainer.removeAll(elementsToMove))
        {
            svgContainer.addAll(svgContainer.indexOf(beforeThis), elementsToMove);
        }

        return false;

    }
}