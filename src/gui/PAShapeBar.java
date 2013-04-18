package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import model.PASystem;
import controller.PAShapeBarAction.ColorAction;
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
    private JButton strokeButton, fillButton, inspectButton;
    private JCheckBox fillCheck, strokeCheck;
    private JSpinner strokeWidthBox;

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
        attrItems.createSpinner(strokeWidthBox, new Dimension(50, 20), new Color(60, 60, 60), 11);

        // JButton
        strokeButton = new JButton();
        fillButton = new JButton();
        inspectButton = new JButton();

        attrItems.setButtonAttributes("Fill Color", fillButton, new Dimension(40, 17));
        attrItems.setButtonAttributes("Stroke Color", strokeButton, new Dimension(40, 17));
        attrItems.setButtonAttributes("Inspect", inspectButton, new Dimension(70, 30));
        
        //Inspect Button customization	
        ImageIcon icon = new ImageIcon("resources/inspect.png");
        inspectButton.setBackground(this.getBackground());
        inspectButton.setIcon(icon);
        inspectButton.setBorder(null);
        inspectButton.setBorderPainted(false);

    }
    
    /**
     * Adding Action to the JComponents
     */
    
    private void addAction()
    {
    	// strokecheck
    	StrokeCheckAction strokeCheckAc = new StrokeCheckAction(strokeButton,strokeWidthBox,strokeCheck);
    	
    	// fillcheck
    	FillCheckAction fillCheckAc = new FillCheckAction(fillButton,fillCheck);
    
    	// fillbutton
    	ColorAction colorFillAction = new ColorAction(mainFrame.getParentView(),fillButton,"Fill");
    	
    	// strokebutton
    	
    	ColorAction colorStrokeAction = new ColorAction(mainFrame.getParentView(),strokeButton,"Stroke");
    	
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
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(inspectButton);

    }

}
