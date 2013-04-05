package src.Model;

/**
 * @author bryantylai
 *
 */
public class PAGroup {

	private String groupId;

	/**
	 * @return newly generated groupId
	 */
	public String generateID()
	{
		return groupId;
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
}
