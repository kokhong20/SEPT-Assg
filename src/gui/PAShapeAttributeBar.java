package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
	
	private void createSpinner()
	{
		strokeWidthBox = new JSpinner();
		strokeWidthBox.setPreferredSize(new Dimension(50,20));
		strokeWidthBox.setMaximumSize(new Dimension(50,20));
		strokeWidthBox.setMinimumSize(new Dimension(50,20));
		
        SpinnerModel yearModel = new SpinnerNumberModel(10, //initial value
                0, //min
                100, //max
                1); //step
        
        strokeWidthBox.setModel(yearModel);

		JTextField tf = ((JSpinner.NumberEditor) strokeWidthBox.getEditor()).getTextField();
	    tf.setBackground(this.getBackground());
		tf.setForeground(Color.WHITE);
		tf.setBorder(BorderFactory.createLineBorder(new Color(60,60,60),1));
		
	}
	
	private void createCheckBox()
	{
		fillCheck = new JCheckBox("Fill:");
		strokeCheck = new JCheckBox("Stroke:");
		
		fillCheck.setForeground(Color.WHITE);
		strokeCheck.setForeground(Color.WHITE);
		fillCheck.setContentAreaFilled(false);
		fillCheck.setOpaque(false);
		strokeCheck.setContentAreaFilled(false);
		strokeCheck.setOpaque(false);
		
		fillCheck.putClientProperty("JComponent.sizeVariant", "small");
		strokeCheck.putClientProperty("JComponent.sizeVariant", "small");

	}
	
	private void createButton()
	{
		strokeButton = new JButton();
		fillButton = new JButton();
		inspectButton = new JButton();
		
		setButtonAttributes("Fill Color",fillButton,new Dimension(40,17));
		setButtonAttributes("Stroke Color",strokeButton,new Dimension(40,17));
		setButtonAttributes("Inspect",inspectButton,new Dimension(70,30));
		
		//Inspect Button customazation
		ImageIcon icon = new ImageIcon("resources/inspect.png");
		inspectButton.setIcon(icon);
		inspectButton.setBorder(null);
		inspectButton.setBorderPainted(false);
	}
	

	private void setButtonAttributes(String toolTip,JButton button,Dimension dim)
	{
		button.setPreferredSize(dim);
		button.setMaximumSize(dim);
		button.setMinimumSize(dim);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorder(BorderFactory.createLineBorder(new Color(60,60,60),2));
		button.setToolTipText(toolTip);
	}
	
	private void initSubComponents()
	{
		createSpinner();
		createCheckBox();
		createButton();
	}
	
	private void setPanelLayout()
	{
		initSubComponents();
		BoxLayout layout = new BoxLayout(this,BoxLayout.LINE_AXIS);
		this.setLayout(layout);
		
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.add(fillCheck);
		this.add(Box.createRigidArea(new Dimension(5,0)));
		this.add(fillButton);
		this.add(Box.createRigidArea(new Dimension(30,0)));
		this.add(strokeCheck);
		this.add(Box.createRigidArea(new Dimension(5,0)));
		this.add(strokeWidthBox);
		this.add(Box.createRigidArea(new Dimension(5,0)));
		this.add(strokeButton);
		this.add(Box.createRigidArea(new Dimension(30,0)));
		this.add(inspectButton);
		
	}
	
	
	
	// Editable JComboBox
	// JDialog
	// LayredPane

}
