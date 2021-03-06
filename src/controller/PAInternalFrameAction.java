package controller;

import gui.PADrawingItem;
import gui.PAMainFrame;
import gui.PAMenuBar;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author LiHao
 * @since 2 May 2013
 * <p>This class creates a PAInternalFrameAction to set action performed when the 
 * internal frame is activated(focused)</p>
 */
public class PAInternalFrameAction extends InternalFrameAdapter
{
    private PAMainFrame mainFrame;

    /**
     * This constructor accepts a PAMainFrame object
     * @param mainFrame PAMainFrame
     */
    public PAInternalFrameAction(PAMainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e)
    {
        PAMenuBar.updateAction(mainFrame);
        PADrawingItem.addAction(mainFrame);
        PADrawingItem.setUpButton();

        if (PADrawingItem.buttonSelected != null)
        {
            switch (PADrawingItem.buttonSelected.getToolTipText())
            {
                case "Select Cursor":
                    PADrawingItem.selectCursor.doClick();
                    PADrawingItem.selectCursor.doClick();
                    break;

                case "Line":
                    PADrawingItem.line.doClick();
                    PADrawingItem.line.doClick();
                    break;

                case "Rectangle":
                    PADrawingItem.rectangle.doClick();
                    PADrawingItem.rectangle.doClick();
                    break;

                case "Circle":
                    PADrawingItem.circle.doClick();
                    PADrawingItem.circle.doClick();
                    break;
                
                case "Hand Cursor":
                    PADrawingItem.handCursor.doClick();
                    PADrawingItem.handCursor.doClick();
                    break;
                    
                case "Fill":
                    PADrawingItem.fill.doClick();
                    PADrawingItem.fill.doClick();
            }
        }
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e)
    {
        PAMenuBar.removeUpdatedAction();
    }
}
