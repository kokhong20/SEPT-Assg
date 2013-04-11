package model;

import java.util.LinkedHashSet;

/**
 * @author bryantylai
 *
 */
public class PAGroup {

	private String groupId;
	private LinkedHashSet<String> idList;

	/**
	 * constructor to create a new list of unique groupId
	 */
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
	 * generates a unique id for a svg group
	 * 
	 * @return newly generated groupId
	 */
	public String generateID()
	{
		if(idList.isEmpty())
		{
			return "PA1";
		}
		
		Object[] idArr = idList.toArray();
		
		int index = idArr.length - 1;
		for(; index >= 0; index--)
		{
			if(((String)idArr[index]).startsWith("PA"))
				break;
		}
		String lastString = (String)idArr[index];
		String[] split = lastString.split("PA");
		int lastId = Integer.parseInt(split[1]);
		
		return "PA" + (lastId + 1);
	}
	
	/**
	 * Create a new empty group
	 * 
	 * @return true if successfully created new id and added to idList, return false otherwise
	 */
	public boolean createGroup()
	{
		return idList.add(generateID());
	}
	
	/**
	 * Create a new group with existing group Id from svg
	 * 
	 * @param idFromSVG id obtained from svg group tag
	 * @return true if groupId successfully added to idList, return false otherwise
	 */
	public boolean createGroup(String idFromSVG)
	{
		return idList.add(idFromSVG);
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
