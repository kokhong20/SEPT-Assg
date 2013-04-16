package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout.Alignment;

import model.PASystem;


public class PAInspectFrame extends JInternalFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7343927563831687660L;
	
	private PARootView rootView;
	private JPanel circlePanel,rectPanel,linePanel,groupPanel;
	private JButton strokeButton,fillButton;
	private JLabel width,height,radius,circleTitle,groupTitle,lineTitle,rectTitle;
	private JCheckBox fillCheck,strokeCheck;
	private JSpinner strokeWidthBox;
	private JTextField widthText,heightText,radText;
	
	public PAInspectFrame(PARootView rootView)
	{
		this.rootView = rootView;
	}
	
	public void initInspectFrame()
	{
		rootView.add(this);
		this.setSize(new Dimension(300,200));
		this.setBackground(new Color(40,40,40));
		this.setVisible(true);
		//Set location based on user's computer resolution
		this.setLocation(((int) (0.75 * PASystem.getScreenDimension().getWidth())), 
				((int) (0.1 * PASystem.getScreenDimension().getHeight())));	
		
		initSubComponents();
		
	}
	
	public void initCirclePanel()
	{
		circlePanel = new JPanel();
		circlePanel.setBackground(new Color(40,40,40));
		GroupLayout layout = new GroupLayout(circlePanel);
		circlePanel.setLayout(layout);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		//Adding Component
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
										.addComponent(fillCheck)
										.addComponent(strokeCheck)
										.addComponent(radius))
									.addGroup(layout.createParallelGroup()
										.addComponent(circleTitle)
										.addComponent(fillButton)
										.addComponent(strokeButton)
										.addComponent(radText))
									.addGap(20)
									.addComponent(strokeWidthBox)
								);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
									.addComponent(circleTitle)
									.addGap(30)
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
										.addComponent(radText))
								);
		
		this.add(circlePanel);
	}
	
	public void initRectPanel()
	{
		rectPanel = new JPanel();
		GroupLayout layout = new GroupLayout(rectPanel);
		rectPanel.setLayout(layout);
		
		this.add(rectPanel);
	}
	
	public void initLinePanel()
	{
		linePanel = new JPanel();
		GroupLayout layout = new GroupLayout(linePanel);
		linePanel.setLayout(layout);
		
		this.add(linePanel);
	}
	
	public void initGroupPanel()
	{
		groupPanel = new JPanel();
		GroupLayout layout = new GroupLayout(groupPanel);
		groupPanel.setLayout(layout);
		
		this.add(groupPanel);
	}
	
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
		attrItems.createSpinner(strokeWidthBox,new Dimension(50,20), new Color(60,60,60), 11);
		
		// JButton
		strokeButton = new JButton();
		fillButton = new JButton();
		
		attrItems.setButtonAttributes("Fill Color",fillButton,new Dimension(40,17));
		attrItems.setButtonAttributes("Stroke Color",strokeButton,new Dimension(40,17));
		
		// JTextField
		widthText = new JTextField();
		heightText = new JTextField();
		radText = new JTextField();
		
		attrItems.createTextField(widthText, new Dimension(50,20), Color.WHITE);
		attrItems.createTextField(heightText, new Dimension(50,20), Color.WHITE);
		attrItems.createTextField(radText, new Dimension(50,20), Color.WHITE);
		
		// JLabel
		
		width = new JLabel("Width:");
		height = new JLabel("Height:");
		radius = new JLabel("Radius:");
		
		lineTitle = new JLabel("Line");
		rectTitle = new JLabel("Rectangle");
		circleTitle = new JLabel("Circle");
		groupTitle = new JLabel("Group");
		
		attrItems.createLabel(width, Color.WHITE, new Font("Helvetica",10, 12));
		attrItems.createLabel(height, Color.WHITE, new Font("Helvetica",10, 12));
		attrItems.createLabel(radius, Color.WHITE, new Font("Helvetica",10, 12));
		
		attrItems.createLabel(lineTitle, Color.WHITE, new Font("Helvetica",10, 20));
		attrItems.createLabel(rectTitle, Color.WHITE, new Font("Helvetica",10, 20));
		attrItems.createLabel(circleTitle, Color.WHITE, new Font("Helvetica",10, 20));
		attrItems.createLabel(groupTitle, Color.WHITE, new Font("Helvetica",10, 20));	
	}	


}
