package gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.PASystem;
/**
 * 
 * @author KokHong
 *
 */
public class PAShapeAttributeBar extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1474246142443123659L;
	private PAMainFrame mainFrame;
	private JButton strokeButton,fillButton,inspectButton;
	private JCheckBox fillCheck,strokeCheck;
	private JSpinner strokeWidthBox;
//	private String[] strokeWidthValues;

	public PAShapeAttributeBar(PAMainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	public void initPanel()
	{	
		this.setBackground(new Color(53,53,53));
		this.setPreferredSize(new Dimension(mainFrame.getWidth(),30));
		this.setMaximumSize(new Dimension((int)PASystem.getScreenDimension().getWidth(),30));
		this.setMinimumSize(new Dimension(mainFrame.getWidth(),30));
		
		setPanelLayout();
		
	}
	
	public void createSpinner()
	{
		strokeWidthBox = new JSpinner();
		strokeWidthBox.setPreferredSize(new Dimension(50,20));
		strokeWidthBox.setMaximumSize(new Dimension(50,20));
		strokeWidthBox.setMinimumSize(new Dimension(50,20));
	}
	
	public void createCheckBox()
	{
		fillCheck = new JCheckBox("Fill:");
		strokeCheck = new JCheckBox("Stroke:");
		
		fillCheck.setForeground(Color.WHITE);
		strokeCheck.setForeground(Color.WHITE);
		fillCheck.setContentAreaFilled(false);
		fillCheck.setOpaque(false);
		strokeCheck.setContentAreaFilled(false);
		strokeCheck.setOpaque(false);
	}
	
	public void createButton()
	{
		strokeButton = new JButton();
		fillButton = new JButton();
		inspectButton = new JButton();
		
		setButtonAttributes("Fill Color",fillButton,new Dimension(40,20));
		setButtonAttributes("Stroke Color",strokeButton,new Dimension(40,20));
		setButtonAttributes("Inspect",inspectButton,new Dimension(40,20));
	}
	

	public void setButtonAttributes(String toolTip,JButton button,Dimension dim)
	{
		button.setPreferredSize(dim);
		button.setMaximumSize(dim);
		button.setMinimumSize(dim);
		//button.setBackground(Color.WHITE);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorder(BorderFactory.createLineBorder(new Color(70,70,70),1));
	}
	
	public void initSubComponents()
	{
		createSpinner();
		createCheckBox();
		createButton();
	}
	
	public void setPanelLayout()
	{
		// Create All the components
		initSubComponents();
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateContainerGaps(true);
		
		// Create a sequential group for the horizontal axis.
		
	    layout.setHorizontalGroup(layout.createSequentialGroup()
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(fillCheck))
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(fillButton))
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(strokeCheck))
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(strokeButton))
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(strokeWidthBox))
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(inspectButton))
	    				);
	    
		// Create a sequential group for the vertical axis.
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addComponent(fillCheck)
				.addComponent(fillButton)
				.addComponent(strokeCheck)
				.addComponent(strokeWidthBox)
				.addComponent(strokeButton)
				.addComponent(inspectButton)));
	}
	
	
	
	// Editable JComboBox
	// JDialog
	// LayredPane

}
