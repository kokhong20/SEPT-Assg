package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import model.PASVGContainer;
import model.PASystem;

/**
 *
 * @author KokHong
 *
 */
public class PAMainFrame extends JInternalFrame
{
    private int svgWidth;
    private int svgHeight;
    private BufferedImage svgImage;
    private PAStatusPanel statusPanel;
    public PAShapeBar attributeBar;
    public PASVGPanel svgPanel;
    private PASVGContainer svgContainer;
    private JPanel mainPanel;
    private JPanel svgBackPanel;
    private JDesktopPane parent;
    /**
     *
     */
    private static final long serialVersionUID = 6966744942640238103L;

    /**
     * constructor to define PAMainFrame for PARootView
     * @param parent
     */
    public PAMainFrame(JDesktopPane parent, PASVGContainer svgContainer, BufferedImage svgImage)
    {
    	this.parent = parent;
        this.svgContainer = svgContainer;
        this.svgImage = svgImage;
        initialize();
        customize();
        setFrameLayout();
        setAttributes();
    }

    /**
     *
     * Initialize the instance variables
     */
    private void initialize()
    {
        statusPanel = new PAStatusPanel(this);
        attributeBar = new PAShapeBar(this);
        svgPanel = new PASVGPanel(svgContainer, svgImage);
        mainPanel = new JPanel();
        svgBackPanel = new JPanel();
    }

    private void customize()
    {
        Dimension minSize = new Dimension(getWidth(), getHeight() - 100);
        Box box = new Box(BoxLayout.Y_AXIS);

        box.setBackground(new Color(38, 38, 38));
        box.add(Box.createVerticalGlue());
        box.add(svgPanel);
        box.add(Box.createVerticalGlue());
        svgBackPanel.setLayout(new BorderLayout());
        svgBackPanel.add(box, BorderLayout.CENTER);
        svgBackPanel.setMinimumSize(minSize);
        svgBackPanel.setBackground(new Color(38, 38, 38));
        //mainPanel.setPreferredSize(new Dimension(800, 600));
    }

    /**
     *
     * Set the attributes of the Internal Frame
     */
    private void setAttributes()
    {
        svgWidth = (int) svgContainer.getSvgTag().getWidth();
        svgHeight = (int) svgContainer.getSvgTag().getHeight();
        
        if(svgWidth < 500)
        {
            System.out.println("haha");
            svgWidth = 500;
        }
        
        if (svgHeight < 500)
        {
            svgHeight = 500;
        }

        int mainFrameWidth = svgWidth + 23;
        int mainFrameHeight = svgHeight + 30 + 24 *2 + 20;
        Dimension mainFrameSize = new Dimension(mainFrameWidth, mainFrameHeight);
        
        //Set location based on user's computer resolution
        Dimension screenResolution = PASystem.getScreenDimension();
        int startX = (int) (screenResolution.getWidth() - mainFrameWidth)/2;
        int startY = (int) (screenResolution.getHeight() - mainFrameHeight)/2;
        Point startPoint = new Point(startX, startY);

        setTitle("New SVG");
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setBackground(new Color(38, 38, 38));
        setVisible(true);
        System.out.println(mainFrameSize);
        setSize(mainFrameSize);
        setLocation(startPoint);
    }

    /**
     *
     * Set mainPanel layout
     */
    private void setFrameLayout()
    {
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);

        // Create a sequential group for the horizontal axis.

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(attributeBar)
                .addComponent(svgBackPanel)
                .addComponent(statusPanel));

        layout.setHorizontalGroup(hGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(attributeBar));

        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(svgBackPanel));

        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(statusPanel));

        layout.setVerticalGroup(vGroup);

        this.add(mainPanel);
    }
    
    public JDesktopPane getParentView()
    {
    	return this.parent;
    }

}
