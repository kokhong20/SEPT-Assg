package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.JPanel;
import model.PASVGContainer;
import model.PASVGElement;

/**
 *
 * @author KokHong
 *
 */
public class PASVGPanel extends JPanel
{
    private int svgWidth;
    private int svgHeight;
    private double zoomScale;
    private double xPosition;
    private double yPosition;
    public BufferedImage svgImage;
    private PAMainFrame mainFrame;
    public PASVGContainer svgContainer;
    private LinkedList<PASVGElement> elementCollection;

    /**
     * constructor to define PASVGPanel for PAMainFrame
     *
     * @param mainFrame
     */
    public PASVGPanel(PASVGContainer svgContainer, BufferedImage svgImage)
    {
        this.svgContainer = svgContainer;
        this.svgImage = svgImage;
        this.elementCollection = svgContainer.getSvgContainer();
        zoomScale = 1;
        xPosition = 0;
        yPosition = 0;
        initPanel();
    }

    /**
     * Initialise Svg Panel
     */
    private void initPanel()
    {
        svgWidth = (int) svgContainer.getSvgTag().getWidth();
        svgHeight = (int) svgContainer.getSvgTag().getHeight();
        Dimension size = new Dimension(svgWidth, svgHeight);

        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(svgImage, 0, 0, this);
    }

}
