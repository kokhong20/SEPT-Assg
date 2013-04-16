package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;

/**
 * 
 * @author KokHong
 *
 */
public abstract class PAShapeBarAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4244444316412694205L;
	protected JButton button;
	protected JSpinner spinner;
	protected JCheckBox checkBox;
	protected boolean isSelected;

	public PAShapeBarAction()
	{
		
	}
	
	public PAShapeBarAction(JButton button, JCheckBox checkBox)
	{
		this.button = button;
		this.checkBox = checkBox;
	}
	
	public PAShapeBarAction(JButton button, JSpinner spinner,JCheckBox checkBox)
	{
		this.button = button;
		this.spinner = spinner;
		this.checkBox = checkBox;
	}
	
	public void setFillEnable(boolean isSelected)
	{
		if(isSelected)
			button.setEnabled(false);
		else
			button.setEnabled(true);
	}
	
	public void setStrokeEnable(boolean isSelected)
	{
		if(isSelected)
		{
			button.setEnabled(false);
			spinner.setEnabled(false);
		}
		else
		{
			button.setEnabled(true);
			spinner.setEnabled(true);
		}
	}
	
	/**
	 * 
	 */
	public static class FillCheck extends PAShapeBarAction
	{
		public FillCheck(JButton button,JCheckBox checkBox)
		{
			super(button,checkBox);
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==checkBox)
			{
				super.setFillEnable(checkBox.isEnabled());
			}
		}
	}
	
	public static class FillColor extends PAShapeBarAction
	{
		
		public FillColor(JButton button,JSpinner spinner ,JCheckBox checkBox)
		{
			super(button,spinner,checkBox);
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==checkBox)
			{
				super.setStrokeEnable(checkBox.isEnabled());
			}
		}
	}
	
	public static class StrokeCheck extends PAShapeBarAction
	{
		public StrokeCheck(JButton button,JSpinner spinner ,JCheckBox checkBox)
		{
			super(button,spinner,checkBox);
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==checkBox)
			{
				System.out.println("Check Box "+checkBox.isEnabled());
				super.setStrokeEnable(checkBox.isEnabled());
			}
		}
	}
	
	public static class StrokeColor extends PAShapeBarAction
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	}
	
	
}
