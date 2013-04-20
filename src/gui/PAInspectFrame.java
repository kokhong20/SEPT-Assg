package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import model.PASystem;

/**
 *
 * @author KokHong
 *
 */
public class PAInspectFrame extends JInternalFrame
{
    /**
     *
     */
    private static final long serialVersionUID = 7343927563831687660L;
    private JDesktopPane rootView;
    private JPanel circlePanel, rectPanel, linePanel, groupPanel;
    private JButton strokeButton, fillButton;
    private JLabel width, height, radius, thumbnail;
    private JCheckBox fillCheck, strokeCheck;
    private JSpinner strokeWidthBox;
    private JTextField widthText, heightText, radText;

    /**
     * constructor to define PAInspectFrame for PARootView
     *
     * @param rootView
     */
    public PAInspectFrame(JDesktopPane rootView)
    {
        this.rootView = rootView;

        initInspectFrame();
        initSubComponents();
    }

    /**
     * Init the internal frame
     */
    public void initInspectFrame()
    {
        this.setClosable(true);
        this.setSize(new Dimension(240, 270));
        this.setBackground(new Color(40, 40, 40));
        this.setVisible(true);
        //Set location based on user's computer resolution
        this.setLocation(((int) (0.80 * PASystem.getScreenDimension().getWidth())),
                ((int) (0.10 * PASystem.getScreenDimension().getHeight())));

    }

    /**
     * Pop out circle inspect panel
     */
    public void initCirclePanel()
    {
        circlePanel = new JPanel();
        circlePanel.setBackground(new Color(40, 40, 40));
        GroupLayout layout = new GroupLayout(circlePanel);
        circlePanel.setLayout(layout);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        //Adding Component

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(fillCheck)
                .addComponent(strokeCheck)
                .addComponent(radius))
                .addGroup(layout.createParallelGroup()
                .addComponent(thumbnail)
                .addComponent(fillButton)
                .addComponent(strokeButton)
                .addComponent(radText))
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(strokeWidthBox)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addComponent(thumbnail)
                .addGap(40)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(fillCheck)
                .addComponent(fillButton))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(strokeCheck)
                .addComponent(strokeButton)
                .addComponent(strokeWidthBox))
                .addGap(20)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(radius)
                .addComponent(radText)));

        this.add(circlePanel);
    }

    /**
     * Pop out rectangle inspect panel
     */
    public void initRectPanel()
    {
        rectPanel = new JPanel();
        rectPanel.setBackground(new Color(40, 40, 40));
        GroupLayout layout = new GroupLayout(rectPanel);
        rectPanel.setLayout(layout);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        //Adding Component

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(fillCheck)
                .addComponent(strokeCheck)
                .addComponent(width)
                .addComponent(height))
                .addGroup(layout.createParallelGroup()
                .addComponent(thumbnail)
                .addComponent(fillButton)
                .addComponent(strokeButton)
                .addComponent(widthText)
                .addComponent(heightText))
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(strokeWidthBox)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addComponent(thumbnail)
                .addGap(40)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(fillCheck)
                .addComponent(fillButton))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(strokeCheck)
                .addComponent(strokeButton)
                .addComponent(strokeWidthBox))
                .addGap(20)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(width)
                .addComponent(widthText))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(height)
                .addComponent(heightText)));

        this.add(rectPanel);
    }

    /**
     * Pop out line inspect panel
     */
    public void initLinePanel()
    {
        linePanel = new JPanel();
        linePanel.setBackground(new Color(40, 40, 40));
        GroupLayout layout = new GroupLayout(linePanel);
        linePanel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        //Adding Component

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(strokeCheck))
                .addGroup(layout.createParallelGroup()
                .addComponent(thumbnail)
                .addComponent(strokeButton))
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(strokeWidthBox)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addComponent(thumbnail)
                .addGap(40)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(strokeCheck)
                .addComponent(strokeButton)
                .addComponent(strokeWidthBox)));

        this.add(linePanel);
    }

    /**
     * Pop out group inspect panel
     */
    public void initGroupPanel()
    {
        groupPanel = new JPanel();
        groupPanel.setBackground(new Color(40, 40, 40));
        GroupLayout layout = new GroupLayout(groupPanel);
        groupPanel.setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(fillCheck)
                .addComponent(strokeCheck))
                .addGroup(layout.createParallelGroup()
                .addComponent(thumbnail)
                .addComponent(fillButton)
                .addComponent(strokeButton))
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                .addComponent(strokeWidthBox)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addComponent(thumbnail)
                .addGap(40)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(fillCheck)
                .addComponent(fillButton))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(strokeCheck)
                .addComponent(strokeButton)
                .addComponent(strokeWidthBox)));

        this.add(groupPanel);
    }

    /**
     * Init all the sub components
     */
    private void initSubComponents()
    {
        PAAttributeItems attrItems = new PAAttributeItems(this);

        // JCheckBox
        fillCheck = new JCheckBox();
        strokeCheck = new JCheckBox();
        attrItems.createCheckBox(fillCheck, "Fill:", Color.WHITE);
        attrItems.createCheckBox(strokeCheck, "Stroke:", Color.WHITE);

        // JSpinner
        strokeWidthBox = new JSpinner();
        attrItems.createSpinner(strokeWidthBox, new Dimension(50, 20), new Color(60, 60, 60), 11);

        // JButton
        strokeButton = new JButton();
        fillButton = new JButton();

        attrItems.setButtonAttributes("Fill Color", fillButton, new Dimension(40, 17));
        attrItems.setButtonAttributes("Stroke Color", strokeButton, new Dimension(40, 17));

        // JTextField
        widthText = new JTextField();
        heightText = new JTextField();
        radText = new JTextField();

        attrItems.createTextField(widthText, new Dimension(40, 18), Color.WHITE);
        attrItems.createTextField(heightText, new Dimension(40, 18), Color.WHITE);
        attrItems.createTextField(radText, new Dimension(40, 18), Color.WHITE);

        // JLabel

        width = new JLabel("Width:");
        height = new JLabel("Height:");
        radius = new JLabel("Radius:");

        thumbnail = new JLabel();

        attrItems.createLabel(width, Color.WHITE, new Font("Helvetica", 10, 12));
        attrItems.createLabel(height, Color.WHITE, new Font("Helvetica", 10, 12));
        attrItems.createLabel(radius, Color.WHITE, new Font("Helvetica", 10, 12));

        // Customization Of Title labels
        attrItems.createLabel(thumbnail, new Dimension(50, 50));

    }

}
