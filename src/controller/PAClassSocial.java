/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.PASVGContainer;

/**
 *
 * @author KokHong
 */

public class PAClassSocial
{
    private PASVGContainer svgContainer;
    private PAMenuAction menuAction;
    
    public PAClassSocial()
    {
    	
    }
    
    public PAClassSocial(PAMenuAction menuAction, PASVGContainer svgContainer)
    {
    	setMenuAction(menuAction);
    	setSvgContainer(svgContainer);
    }

	/**
	 * @return the svgContainer
	 */
	public PASVGContainer getSvgContainer()
	{
		return svgContainer;
	}

	/**
	 * @param svgContainer the svgContainer to set
	 */
	public void setSvgContainer(PASVGContainer svgContainer)
	{
		this.svgContainer = svgContainer;
	}

	/**
	 * @return the menuAction
	 */
	public PAMenuAction getMenuAction()
	{
		return menuAction;
	}

	/**
	 * @param menuAction the menuAction to set
	 */
	public void setMenuAction(PAMenuAction menuAction)
	{
		this.menuAction = menuAction;
	}
}
