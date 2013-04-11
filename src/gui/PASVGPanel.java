package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 *
 * @author KokHong
 *
 */
public class PASVGPanel extends JPanel
{
    /**
     *
     */
    private static final long serialVersionUID = 940764866671091035L;
    private PAMainFrame mainFrame;

    public PASVGPanel(PAMainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        initPanel();
    }

    /*
     * Initialise Svg Panel
     */
    private void initPanel()
    {
        int width = mainFrame.getWidth() - 120;
        int height = mainFrame.getHeight() - 300;
        Dimension size = new Dimension(width, height);
        
        setBackground(Color.white);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }

}
