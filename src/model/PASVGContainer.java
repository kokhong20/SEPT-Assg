package model;

import java.util.LinkedList;

/**
 * @author bryantylai/LaiSaiHoo
 *
 */
public class PASVGContainer
{
    private PAGroup svgGroup;
    private PASVGTag svgTag;
    private LinkedList<PAShape> svgContainer;

    /**
     * constructor with only svgTag such that from a newly created svg
     *
     * @param svgTag
     */
    public PASVGContainer(PASVGTag svgTag)
    {
        setSvgTag(svgTag);
        setSvgGroup(new PAGroup());
        setSvgContainer(new LinkedList<PAShape>());
    }

    /**
     * constructor with a pre-created list of shapes such that from existing svg
     *
     * @param svgTag
     * @param shapes
     */
    public PASVGContainer(PASVGTag svgTag, LinkedList<PAShape> shapes)
    {
        setSvgTag(svgTag);
        setSvgGroup(new PAGroup());
        setSvgContainer(shapes);
    }

    /**
     * @return the svgGroup
     */
    public PAGroup getSvgGroup()
    {
        return svgGroup;
    }

    /**
     * @param svgGroup the svgGroup to set
     */
    private void setSvgGroup(PAGroup svgGroup)
    {
        this.svgGroup = svgGroup;
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
    public LinkedList<PAShape> getSvgContainer()
    {
        return svgContainer;
    }

    /**
     * @param svgContainer the svgContainer to set
     */
    private void setSvgContainer(LinkedList<PAShape> svgContainer)
    {
        this.svgContainer = svgContainer;
    }

    /**
     * Create a new group and add a single PAShape to end of svgContainer
     *
     * @param shape
     * @return true if shape is added, return false otherwise
     */
    public boolean createGroupAndAddShape(PAShape shape)
    {
        shape.setId(svgGroup.generateID());
        shape.setGrouped(true);

        return svgContainer.add(shape);
    }

    /**
     * Create a new group and add a group of PAShape to end of svgContainer
     *
     * @param shapes
     * @return true if list of shapes is added, return false otherwise
     */
    public boolean createGroupAndAddShapes(LinkedList<PAShape> shapes)
    {
        for (int i = 0; i < shapes.size(); i++)
        {
            PAShape shape = shapes.get(i);
            if (i != 0)
            {
                shape.setId(shapes.getFirst().getId());
            }
            else
            {
                shape.setId(svgGroup.generateID());
            }
            shape.setGrouped(true);
        }

        return svgContainer.addAll(shapes);
    }

    /**
     * add a single shape to the end of an existing group
     *
     * @param groupId
     * @param shape
     * @return true if PAShape is added to group, return false otherwise
     */
    public boolean addShapeToGroup(String groupId, PAShape shape)
    {
        if (svgGroup.getIdList().contains(groupId))
        {
            for (int i = 0; i < svgContainer.size(); i++)
            {
                if (svgContainer.get(i).getId().equals(groupId))
                {
                    for (int j = i; j < svgContainer.size(); j++)
                    {
                        if (!svgContainer.get(j).equals(groupId) || !svgContainer.get(j).isGrouped())
                        {
                            svgContainer.add(j, shape);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * add a group of shapes to the end of an existing group
     *
     * @param groupId
     * @param shape
     * @return true if list of PAShape is added to group, return false otherwise
     */
    public boolean addShapesToGroup(String groupId, LinkedList<PAShape> shapes)
    {
        if (svgGroup.getIdList().contains(groupId))
        {
            for (int i = 0; i < svgContainer.size(); i++)
            {
                if (svgContainer.get(i).getId().equals(groupId))
                {
                    for (int j = i; j < svgContainer.size(); j++)
                    {
                        if (!svgContainer.get(j).equals(groupId) || !svgContainer.get(j).isGrouped())
                        {
                            svgContainer.addAll(j, shapes);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Remove a particular PAShape from an existing group and add it directly
     * after that group
     *
     * @param groupId
     * @param shapeToRemove
     * @return true if PAShape successfully removed from group, return false
     * otherwise
     */
    public boolean removeFromGroup(String groupId, PAShape shapeToRemove)
    {
        if (svgGroup.getIdList().contains(groupId))
        {
            for (int i = 0; i < svgContainer.size(); i++)
            {
                if (svgContainer.get(i).getId().equals(groupId))
                {
                    for (int j = i; j < svgContainer.size(); j++)
                    {
                        if (!svgContainer.get(j).equals(groupId) && !svgContainer.get(j).isGrouped())
                        {
                            shapeToRemove.setGrouped(false);
                            shapeToRemove.setId(null);
                            svgContainer.add(j - 1, shapeToRemove);
                            svgContainer.removeFirstOccurrence(shapeToRemove);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * remove a list of PAShape from an existing group and add the list directly
     * after that group
     *
     * @param groupId
     * @param shapes
     * @return true if list of PAShape successfully removed from group, return
     * false otherwise
     */
    public boolean removeListFromGroup(String groupId, LinkedList<PAShape> shapes)
    {
        if (svgGroup.getIdList().contains(groupId))
        {
            for (int i = 0; i < svgContainer.size(); i++)
            {
                if (svgContainer.get(i).getId().equals(groupId))
                {
                    for (int j = 0; j < shapes.size(); j++)
                    {
                        PAShape current = shapes.get(j);
                        if (current.getId().equals(groupId) && current.isGrouped())
                        {
                            current.setGrouped(false);
                            current.setId(null);
                        }
                    }

                    svgContainer.removeAll(shapes);
                    svgContainer.addAll(i + shapes.size() - 1, shapes);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * remove all PAShape from an existing group and keep its position
     *
     * @param groupId
     * @return true if all PAShape successfully removed from group, return false
     * otherwise
     */
    public boolean removeAllFromGroup(String groupId)
    {
        if (svgGroup.getIdList().contains(groupId))
        {
            for (int i = 0; i < svgContainer.size(); i++)
            {
                PAShape current = svgContainer.get(i);
                if (current.isGrouped() && current.getId().equals(groupId))
                {
                    for (int j = i; j < svgContainer.size(); j++)
                    {
                        current = svgContainer.get(j);
                        if (current.getId().equals(groupId) && current.isGrouped())
                        {
                            current.setGrouped(false);
                            current.setId(null);
                        }
                        else
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
