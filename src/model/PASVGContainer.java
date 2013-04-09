package model;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * @author bryantylai/LaiSaiHoo
 *
 */
public class PASVGContainer {
	private PASVGTag svgTag;
	private LinkedHashMap<String, LinkedHashSet<PAShape>> groupedSVGCollection;
	
	/**
	 * Create a new group for PAShape
	 */
	public void createGroup()
	{
		
	}
	
	/**
	 * Delete a whole group of PAShapes
	 * @param groupId
	 */
	public boolean deleteGroup(String groupId)
	{
		if(groupedSVGCollection.containsKey(groupId))
		{
			groupedSVGCollection.remove(groupId);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Remove a particular PAShape from a group
	 * @param shapeToRemove
	 * @return return true if successfully removed, return null otherwise
	 */
	public boolean removeFromGroup(PAShape shapeToRemove)
	{
		String gID = shapeToRemove.getId();
		if(groupedSVGCollection.containsKey(gID))
		{
			LinkedHashSet<PAShape> collection = groupedSVGCollection.get(gID);
			collection.remove(shapeToRemove);
			groupedSVGCollection.put(gID, collection);
			return true;
		}

		return false;
	}
	
	/**
	 * Return LinkedHashSet of the groupId, otherwise return null
	 * @param groupId
	 * @return
	 */
	public LinkedHashSet<PAShape> getGroupedSVG(String groupId)
	{
		if(groupedSVGCollection.containsKey(groupId))
			return groupedSVGCollection.get(groupId);
		else 
			return null;
	}
	
	/**
	 * @return the svgTag
	 */
	public PASVGTag getSvgTag() {
		return svgTag;
	}
	
	/**
	 * @param svgTag the svgTag to set
	 */
	public void setSvgTag(PASVGTag svgTag) {
		this.svgTag = svgTag;
	}
	
	/**
	 * @return the groupedSVGCollection
	 */
	public LinkedHashMap<String, LinkedHashSet<PAShape>> getGroupedSVGCollection() {
		return groupedSVGCollection;
	}
	
	/**
	 * @param groupedSVGCollection the groupedSVGCollection to set
	 */
	public void setGroupedSVGCollection(LinkedHashMap<String, LinkedHashSet<PAShape>> groupedSVGCollection) {
		this.groupedSVGCollection = groupedSVGCollection;
	}
}
