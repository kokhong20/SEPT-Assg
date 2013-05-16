package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import model.PASystem;

/**
 *
 * @author KokHong
 * @since 10 April 2013
 * <p>This class creates a PAStatusPanel for design purposes </p>
 */
public class PAStatusPanel extends JPanel
{
    /**
     *
     */
    private static final long serialVersionUID = 7022375506076289923L;
    private PAMainFrame mainFrame;

    /**
     * constructor to define PAStatusPanel for PAMainFrame
     *
     * @param mainFrame
     */
    public PAStatusPanel(PAMainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        initPanel();
    }

    /**
     * Init status panel
     */
    private void initPanel()
    {
        setBackground(new Color(77, 77, 77));
        setPreferredSize(new Dimension(mainFrame.getWidth(), 20));
        setMaximumSize(new Dimension((int) PASystem.getScreenDimension().getWidth(), 20));
        setMinimumSize(new Dimension(mainFrame.getWidth(), 20));
    }
}
