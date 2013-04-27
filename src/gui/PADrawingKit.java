package gui;

import java.awt.Color;
import javax.swing.JInternalFrame;

import model.PASystem;

/**
 *
 * @author KokHong
 *
 */
public class PADrawingKit extends JInternalFrame
{
    private PADrawingItem drawingKitButton;
    public PAMainFrame mainFrame;
    /**
     *
     */
    private static final long serialVersionUID = -918164000427896948L;

    /**
     * constructor to define PADrawingKit for PARootView
     */
    public PADrawingKit(PAMainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        initDrawingKit();
        setFrameAttribues();
    }

    /**
     * Initialize the drawkingKit panel
     */
    private void initDrawingKit()
    {
        drawingKitButton = new PADrawingItem(this);
    }

    /**
     * Set frame attributes
     */
    private void setFrameAttribues()
    {
        this.setLayout(null);
        this.setBackground(new Color(40, 40, 40));
        this.setVisible(true);
        this.setSize(100, 270);
        //Set location based on user's computer resolution
        this.setLocation(((int) (0.05 * PASystem.getScreenDimension().getWidth())),
                ((int) (0.2 * PASystem.getScreenDimension().getHeight())));
    }

}
