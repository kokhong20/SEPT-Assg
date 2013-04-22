package model;

import org.w3c.dom.Node;

/**
 * @author bryantylai
 *
 */
public abstract class PAShape extends PASVGElement {

	private String id;
	private boolean isGrouped;
	
	public PAShape()
	{
		
	}
	
	/**
	 * Constructor which receives a node 
	 * Call setColor and setUnit method to set value 
	 * 
	 * @param node
	 */
	public PAShape(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
	}

	/**
	 * @return the isGrouped
	 */
	public boolean isGrouped() {
		return isGrouped;
	}

	/**
	 * @param isGrouped the isGrouped to set
	 */
	public void setGrouped(boolean isGrouped) {
		this.isGrouped = isGrouped;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
