package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import model.PASystem;

/**
 *
 * @author KokHong
 *
 */
public class PAMainFrame extends JInternalFrame
{
    private PAStatusPanel statusPanel;
    private PAShapeBar attributeBar;
    private PASVGPanel svgPanel;
    private JPanel mainPanel;
    private JPanel svgBackPanel;
    /**
     *
     */
    private static final long serialVersionUID = 6966744942640238103L;

    public PAMainFrame()
    {
        setAttributes();
        initialize();
        customize();
        setFrameLayout();
    }

    /**
     * 
     * Initialize the instance variables
     */
    private void initialize()
    {
        statusPanel = new PAStatusPanel(this);
        attributeBar = new PAShapeBar(this);
        svgPanel = new PASVGPanel(this);
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
        mainPanel.setPreferredSize(new Dimension(800, 600));
        attributeBar.initPanel();
        statusPanel.initPanel();
    }

    /**
     * 
     * Set the attributes of the Internal Frame
     */
    private void setAttributes()
    {
        //Set location based on user's computer resolution
        Dimension screenResolution = PASystem.getScreenDimension();
        int startX = (int) (0.15*screenResolution.getWidth());
        int startY = (int) (0.1*screenResolution.getHeight());
        Point startPoint = new Point(startX, startY);
        
        setTitle("New SVG");
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setBackground(new Color(38, 38, 38));
        setVisible(true);
        setSize(800, 600);
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
}
