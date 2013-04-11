package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;

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
	private String[] strokeWidthValues;

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
	}
	
	public void createButton()
	{
		strokeButton = new JButton();
		fillButton = new JButton();
		inspectButton = new JButton();
		
		strokeButton.setBackground(this.getBackground());
		fillButton.setBackground(this.getBackground());
		inspectButton.setBackground(this.getBackground());
		
		strokeButton.setPreferredSize(new Dimension(50,20));
		fillButton.setPreferredSize(new Dimension(50,20));
		inspectButton.setPreferredSize(new Dimension(50,20));
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
		layout.setAutoCreateGaps(true);
		
		// Create a sequential group for the horizontal axis.
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(fillCheck)
				.addComponent(fillButton));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(strokeCheck)
				.addComponent(strokeWidthBox)
				.addComponent(strokeButton));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(inspectButton));
	
		layout.setHorizontalGroup(hGroup);

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(fillCheck)
				.addComponent(fillButton)
				.addComponent(strokeCheck)
				.addComponent(strokeWidthBox)
				.addComponent(strokeButton)
				.addComponent(inspectButton));

		layout.setVerticalGroup(vGroup);
		
	}
	
	
	
	// Editable JComboBox
	// JDialog
	// LayredPane

}
