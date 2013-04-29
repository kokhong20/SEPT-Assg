package model;

import java.awt.Shape;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author bryantylai/LaiSaiHoo
 *
 */
public class PASVGContainer
{
    private PASVGTag svgTag;
    private LinkedList<PASVGElement> svgContainer;
    
    private LinkedHashMap<Shape, PASVGElement> shapesCollection;

    /**
     * constructor with only svgTag such that from a newly created svg
     *
     * @param svgTag
     */
    public PASVGContainer(PASVGTag svgTag)
    {
        setSvgTag(svgTag);
        setSvgContainer(new LinkedList<PASVGElement>());
        setShapesCollection(new LinkedHashMap<Shape, PASVGElement>());
    }

    /**
     * constructor with a pre-created list of shapes such that from existing svg
     *
     * @param svgTag
     * @param shapes
     */
    public PASVGContainer(PASVGTag svgTag, LinkedList<PASVGElement> shapes)
    {
        setSvgTag(svgTag);
        setSvgContainer(shapes);
    }

    public PASVGContainer(PASVGTag svgTag, LinkedList<PASVGElement> shapes, LinkedHashMap<Shape, PASVGElement> shapeCollection)
    {
        setSvgTag(svgTag);
        setSvgContainer(shapes);
        setShapesCollection(shapeCollection);
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
	 * @return the shapesCollection
	 */
	public LinkedHashMap<Shape, PASVGElement> getShapesCollection()
	{
		return shapesCollection;
	}

	/**
	 * @param shapesCollection the shapesCollection to set
	 */
	public void setShapesCollection(LinkedHashMap<Shape, PASVGElement> shapesCollection)
	{
		this.shapesCollection = shapesCollection;
	}

	/**
     * Add a new shape
     * @param shape
     * @return true if shape is successfully added, return false otherwise
     */
    public boolean addNewShape(PASVGElement shape)
    {
    	return svgContainer.add(shape);
    }
    
    /**
     * Add a new group
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
     * @param shape
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
     * @param shapes
     * @return true if list of shapes is added, return false otherwise
     */
    public boolean createGroupAndAddShapes(LinkedList<PASVGElement> shapes)
    {
    	for(int index = 0; index < shapes.size(); index++)
    	{
    		PASVGElement element = shapes.get(index);
    		element.setGrouped(true);
    	}
    	
    	PASVGGroup newGroup = new PASVGGroup();
    	newGroup.addNewElementsToGroup(shapes);
        return svgContainer.add(newGroup);
    }

    /**
     * add a single shape to the end of an existing group
     *
     * @param group
     * @param shape
     * @return true if PASVGElement is added to group, return false otherwise
     */
    public boolean addShapeToGroup(PASVGGroup group, PASVGElement shape)
    {
    	return group.addNewElementToGroup(shape);
    }

    /**
     * add a group of shapes to the end of an existing group
     * 
     * @param group
     * @param shapes
     * @return true if list of PASVGElement is added to group, return false otherwise
     */
    public boolean addShapesToGroup(PASVGGroup group, LinkedList<PASVGElement> shapes)
    {
    	return group.addNewElementsToGroup(shapes);
    }

    /**
     * permanently remove an element
     * 
     * @param element
     * @return true if successfully removed, return false otherwise
     */
    public boolean removeElement(PASVGElement element)
    {
        return svgContainer.remove(element);
    }
    
    /**
     * Remove a particular PASVGElement from an existing group and add it directly
     * after that group
     *
     * @param group
     * @param shapeToRemove
     * @return true if PASVGElement successfully removed from group, return false
     * otherwise
     */
    public boolean removeFromGroup(PASVGGroup group, PASVGElement shapeToRemove)
    {
    	if(group.removeElementFromGroup(shapeToRemove))
    	{
    		svgContainer.add(svgContainer.indexOf(group) + 1, shapeToRemove);
    		return true;
    	}
    	
    	return false;
    }

    /**
     * remove a list of PASVGElement from an existing group and add the list directly
     * after that group
     *
     * @param group
     * @param shapes
     * @return true if list of PASVGElement successfully removed from group, return
     * false otherwise
     */
    public boolean removeListFromGroup(PASVGGroup group, LinkedList<PASVGElement> shapes)
    {
    	if(group.removeElementsFromGroup(shapes))
    	{
    		return svgContainer.addAll(svgContainer.indexOf(group) + 1, shapes);
    	}
    	
    	return false;
    }

    /**
     * remove all PASVGElement from an existing group and keep its position
     *
     * @param group
     * @return true if all PASVGElement successfully removed from group, return false
     * otherwise
     */
    public boolean removeAllFromGroup(PASVGGroup group)
    {
    	LinkedList<PASVGElement> elements = null;
    	
    	if((elements = group.removeAllElementsFromGroup()) != null)
    	{
    		return svgContainer.addAll(svgContainer.indexOf(group) + 1, elements);
    	}
    	
    	return false;
    }
    
    /**
     * move an element to other position
     * @param elementToMove
     * @param beforeThis
     * @return true if successfully moved, return false otherwise
     */
    public boolean moveElement(PASVGElement elementToMove, PASVGElement beforeThis)
    {
        if(svgContainer.remove(elementToMove))
        {
            svgContainer.add(svgContainer.indexOf(beforeThis), elementToMove);
        }
        
        return false;
        
    }
    
    /**
     * move a list of elements to other position
     * @param elementsToMove
     * @param beforeThis
     * @return true if successfully moved, return false otherwise
     */
    public boolean moveElements(LinkedList<PASVGElement> elementsToMove, PASVGElement beforeThis)
    {
        if(svgContainer.removeAll(elementsToMove))
        {
            svgContainer.addAll(svgContainer.indexOf(beforeThis), elementsToMove);
        }
        
        return false;
        
    }
}