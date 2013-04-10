package model;

import java.util.LinkedHashSet;

/**
 * @author bryantylai
 *
 */
public class PAGroup {

	private String groupId;
	private LinkedHashSet<String> idList;

	public PAGroup()
	{
		setIdList(new LinkedHashSet<String>());
	}
	
	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the idList
	 */
	public LinkedHashSet<String> getIdList() {
		return idList;
	}

	/**
	 * @param idList the idList to set
	 */
	public void setIdList(LinkedHashSet<String> idList) {
		this.idList = idList;
	}
	
	/**
	 * @return newly generated groupId
	 */
	public String generateID()
	{
		/**
		 * Implementation of generate unique id not done
		 */
		
		return groupId;
	}
	
	/**
	 * Create a new empty group
	 * 
	 * @return
	 */
	public boolean createGroup()
	{
		return idList.add(generateID());
	}
	
	/**
	 * Check if a groupId currently exist
	 * 
	 * @param id
	 * @return true if id exist already, return false otherwise
	 */
	public boolean checkGroupExist(String id)
	{
		return idList.contains(id);
	}

	/**
	 * Delete a whole group of PAShapes
	 * 
	 * @param groupId
	 * @return true if successfully remove group, return false otherwise
	 */
	public boolean deleteGroup(String id)
	{
		return idList.remove(id);
	}
}
