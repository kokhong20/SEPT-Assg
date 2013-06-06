package controller;

import gui.PADrawingItem;
import gui.PAMainFrame;
import gui.PAMenuBar;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.PASystem;

/**
 *
 * @author LiHao
 * @since 2 May 2013
 * <p>This class creates a PAInternalFrameAction to set action performed when
 * the internal frame is activated(focused)</p>
 */
public class PAInternalFrameAction extends InternalFrameAdapter
{
    private PAMainFrame mainFrame;
    private String selectCursorText;
    private String lineText;
    private String rectangleText;
    private String circleText;
    private String handText;
    private String fillText;

    /**
     * This constructor accepts a PAMainFrame object
     *
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
            selectCursorText = PASystem.getWord("SelectCursor");
            lineText = PASystem.getWord("Line");
            rectangleText = PASystem.getWord("Rectangle");
            circleText = PASystem.getWord("Circle");
            handText = PASystem.getWord("HandCursor");
            fillText = PASystem.getWord("Fill");

            if (PADrawingItem.buttonSelected.getToolTipText().equals(selectCursorText))
            {
                PADrawingItem.selectCursor.doClick();
                PADrawingItem.selectCursor.doClick();
            }
            else if (PADrawingItem.buttonSelected.getToolTipText().equals(lineText))
            {
                PADrawingItem.line.doClick();
                PADrawingItem.line.doClick();
            }
            else if (PADrawingItem.buttonSelected.getToolTipText().equals(rectangleText))
            {
                PADrawingItem.rectangle.doClick();
                PADrawingItem.rectangle.doClick();
            }
            else if (PADrawingItem.buttonSelected.getToolTipText().equals(circleText))
            {
                PADrawingItem.circle.doClick();
                PADrawingItem.circle.doClick();
            }
            else if (PADrawingItem.buttonSelected.getToolTipText().equals(handText))
            {
                PADrawingItem.handCursor.doClick();
                PADrawingItem.handCursor.doClick();
            }
            else if (PADrawingItem.buttonSelected.getToolTipText().equals(fillText))
            {
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
