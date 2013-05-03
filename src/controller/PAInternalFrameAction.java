/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PAMainFrame;
import gui.PAMenuBar;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author LiHao
 */
public class PAInternalFrameAction extends InternalFrameAdapter
{
    private PAMainFrame mainFrame;
    
    public PAInternalFrameAction(PAMainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }
    
    @Override
    public void internalFrameActivated(InternalFrameEvent e)
    {
        PAMenuBar.updateAction(mainFrame);
    }
    
    @Override
    public void internalFrameDeactivated(InternalFrameEvent e)
    {
        PAMenuBar.removeUpdatedAction();
    }
}
