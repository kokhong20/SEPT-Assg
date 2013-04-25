package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import model.PASVGContainer;

/**
 *
 * @author KokHong
 *
 */
public class PASVGPanel extends JPanel
{
    private PAMainFrame mainFrame;
    private PASVGContainer svgContainer;

    /**
     * constructor to define PASVGPanel for PAMainFrame
     * @param mainFrame
     */
    public PASVGPanel(PASVGContainer svgContainer)
    {
        this.svgContainer = svgContainer;
        initPanel();
    }

    /**
     * Initialise Svg Panel
     */
    private void initPanel()
    {
        int width = (int)svgContainer.getSvgTag().getWidth();
        int height = (int)svgContainer.getSvgTag().getHeight();
        Dimension size = new Dimension(width, height);

        setBackground(Color.white);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }

}
