package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import model.PASystem;
import controller.PAShapeBarAction.FillCheckAction;
import controller.PAShapeBarAction.StrokeCheckAction;

/**
 *
 * @author KokHong
 *
 */
public class PAShapeBar extends JPanel
{
    /**
     *
     */
    private static final long serialVersionUID = 1474246142443123659L;
    private PAMainFrame mainFrame;
    public JButton strokeButton, fillButton;
    public JCheckBox fillCheck, strokeCheck;
    public JSpinner strokeWidthBox;

    /**
     * constructor to define PAShapeBar for PAMainFrame
     * @param mainFrame
     */
    public PAShapeBar(PAMainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        initPanel();
        initSubComponents();
        setPanelLayout();
        addAction();
    }

    /**
     * Init whole panel
     */
    private void initPanel()
    {
        this.setBackground(new Color(53, 53, 53));
        this.setPreferredSize(new Dimension(mainFrame.getWidth(), 30));
        this.setMaximumSize(new Dimension((int) PASystem.getScreenDimension().getWidth(), 30));
        this.setMinimumSize(new Dimension(mainFrame.getWidth(), 30));
        this.setVisible(true);
    }

    /**
     * Initialize all sub components
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
        attrItems.createSpinner(strokeWidthBox, new Dimension(50, 20), new Color(60, 60, 60), 1f);

        // JButton
        strokeButton = new JButton();
        fillButton = new JButton();

        attrItems.setButtonAttributes("Fill Color", fillButton, new Dimension(40, 17));
        attrItems.setButtonAttributes("Stroke Color", strokeButton, new Dimension(40, 17));
    }
    
    /**
     * Adding Action to the JComponents
     */
    
    private void addAction()
    {
    	// strokecheck
    	StrokeCheckAction strokeCheckAc = new StrokeCheckAction(mainFrame.getParentView(),strokeButton,"Stroke",strokeCheck,strokeWidthBox);
    	
    	// fillcheck
    	FillCheckAction fillCheckAc = new FillCheckAction(mainFrame.getParentView(),fillButton,"Fill",fillCheck);
    	
    }

    /**
     * Set the panel layout
     */
    private void setPanelLayout()
    {
        BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
        this.setLayout(layout);

        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(fillCheck);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(fillButton);
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(strokeCheck);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(strokeWidthBox);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(strokeButton);

    }

}
