package gui;

import controller.PAInternalFrameAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.PASVGContainer;
import model.PASystem;

/**
 *
 * @author KokHong
 *
 */
public class PAMainFrame extends JInternalFrame
{
    public PAShapeBar attributeBar;
    public PASVGPanel svgPanel;
    private Box box;
    private String fileName;
    private BufferedImage svgImage;
    private PAStatusPanel statusPanel;
    private PASVGContainer svgContainer;
    private JPanel mainPanel;
    private JScrollPane svgBackPanel;
    private JDesktopPane parent;
    /**
     *
     */
    private static final long serialVersionUID = 6966744942640238103L;

    /**
     * constructor to define PAMainFrame for PARootView
     *
     * @param parent
     */
    public PAMainFrame(JDesktopPane parent, PASVGContainer svgContainer, String fileName)
    {
        super(fileName);
        this.parent = parent;
        this.svgContainer = svgContainer;
        this.fileName = fileName;
        initialize();
        customize();
        setFrameLayout();
        setUpMainFrame();
    }

    /**
     *
     * Initialize the instance variables
     */
    private void initialize()
    {
        statusPanel = new PAStatusPanel(this);
        attributeBar = new PAShapeBar(this);
        svgPanel = new PASVGPanel(svgContainer);
        box = new Box(BoxLayout.Y_AXIS);
        mainPanel = new JPanel();
        svgBackPanel = new JScrollPane(box, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        PAInternalFrameAction internalFrameAction = new PAInternalFrameAction(this);
        this.addInternalFrameListener(internalFrameAction);
    }

    private void customize()
    {
        Dimension minSize = new Dimension(getWidth(), getHeight() - 100);

        box.setBackground(new Color(38, 38, 38));
        box.add(Box.createVerticalGlue());
        box.add(svgPanel);
        box.add(Box.createVerticalGlue());
        //svgBackPanel.setLayout(new BorderLayout());
        //svgBackPanel.add(box, BorderLayout.CENTER);
        svgBackPanel.setMinimumSize(minSize);
        svgBackPanel.revalidate();
        svgBackPanel.setBorder(BorderFactory.createLineBorder(new Color(38, 38, 38), 1));
        svgBackPanel.getViewport().setBackground(new Color(38, 38, 38));
        //mainPanel.setPreferredSize(new Dimension(800, 600));
    }

    /**
     *
     * Set the attributes of the Internal Frame
     */
    private void setUpMainFrame()
    {
        Dimension screenResolution = PASystem.getScreenDimension();
        int mainFrameWidth = (int) svgContainer.getSvgTag().getWidth();
        int mainFrameHeight = (int) svgContainer.getSvgTag().getHeight();
        int maxViewWidth = (int) (screenResolution.getWidth() / 1.4);
        int maxViewHeight = (int) (screenResolution.getHeight() / 1.4);

        if (mainFrameWidth < 500)
        {
            mainFrameWidth = 500;
        }

        if (mainFrameHeight < 500)
        {
            mainFrameHeight = 500;
        }
        
        if (mainFrameWidth > maxViewWidth)
        {
            mainFrameWidth = maxViewWidth;
        }
        
        if (mainFrameHeight > maxViewHeight)
        {
            mainFrameHeight = maxViewHeight;
        }
        
        if (PASystem.currentOS.indexOf("mac") >= 0)
        {
            int screenWidth = (int) screenResolution.getWidth();
            int screenHeight = (int) screenResolution.getHeight() - 86;
            screenResolution = new Dimension(screenWidth, screenHeight);
            mainFrameWidth += 28;
            mainFrameHeight += 25;
        }
        
        mainFrameHeight = mainFrameHeight + 30 + 24 * 2;
        Dimension mainFrameSize = new Dimension(mainFrameWidth, mainFrameHeight);

        //Set location based on user's computer resolution
        int startX = (int) (screenResolution.getWidth() - mainFrameWidth) / 2;
        int startY = (int) (screenResolution.getHeight() - mainFrameHeight) / 2;
        Point startPoint = new Point(startX, startY);

        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setBackground(new Color(38, 38, 38));
        setVisible(true);

        System.out.println(mainFrameSize);
        setSize(mainFrameSize);
        setMinimumSize(new Dimension(300,300));
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

        add(mainPanel);
        
    }

    public JDesktopPane getParentView()
    {
        return this.parent;
    }

}
