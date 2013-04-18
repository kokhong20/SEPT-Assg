package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import gui.PAColorChooser;
import gui.PARootView;

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
	protected PAColorChooser colorChooser;
	protected JDesktopPane rootView;
	protected JColorChooser colorChooserObject;
	protected String title;
	
	// Constructor
	public PAShapeBarAction(JDesktopPane rootView , JButton button, String title)
	{
		this.title = title;
		this.button = button;
		this.rootView = rootView;
		
		settingAction();
	}
	
	public PAShapeBarAction(JButton button, JCheckBox checkBox)
	{
		this.button = button;
		this.checkBox = checkBox;
		
		settingAction();
	}
	
	public PAShapeBarAction(JButton button, JSpinner spinner,JCheckBox checkBox)
	{
		this.button = button;
		this.spinner = spinner;
		this.checkBox = checkBox;
		
		settingAction();
	}
	
	
	public void setFillEnable()
	{
		button.setEnabled(checkBox.isSelected());
	}
	
	public void setStrokeEnable()
	{
		button.setEnabled(checkBox.isSelected());
		spinner.setEnabled(checkBox.isSelected());
	}
	
	public void setColorChooser()
	{
		if(colorChooser == null)
		{
			colorChooser = new PAColorChooser(rootView);
			colorChooser.addInternalFrameListener(new InternalFrameAdapter()
			{
				@Override
				public void internalFrameClosed(InternalFrameEvent e) 
				{
					if(e.getSource()==colorChooser)
					{
						colorChooser = null;
					}
				}
			});
			colorChooser.setTitle(title);
			System.out.println(colorChooser.getTitle());
			colorChooserObject = colorChooser.getColorChooser();
			colorChooserObject.setColor(button.getBackground());
		}
		
	}
	
	public abstract void settingAction();
	
	/**
	 * Inner sub classes
	 */
	public static class FillCheckAction extends PAShapeBarAction
	{
		public FillCheckAction(JButton button,JCheckBox checkBox)
		{
			super(button,checkBox);
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==checkBox)
			{
				super.setFillEnable();
			}
		}
		@Override
		public void settingAction() 
		{
			checkBox.setAction(this);
			checkBox.setText("Fill:");
		}

	}
	
	public static class StrokeCheckAction extends PAShapeBarAction
	{
		public StrokeCheckAction(JButton button,JSpinner spinner ,JCheckBox checkBox)
		{
			super(button,spinner,checkBox);
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==checkBox)
			{
				
				super.setStrokeEnable();
			}
		}
		@Override
		public void settingAction() 
		{
			checkBox.setAction(this);
			checkBox.setText("Stroke:");
		}

	}
	
	public static class ColorAction extends PAShapeBarAction
	{
		public ColorAction(JDesktopPane parent ,JButton button,String title) 
		{
			super(parent,button,title);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			super.setColorChooser();
			colorChooserObject.getSelectionModel().addChangeListener(new ChangeListener()
			{
				@Override
				public void stateChanged(ChangeEvent e) 
				{
					Color color = colorChooserObject.getColor();
					button.setBackground(color);
				}
				
			});
		}

		@Override
		public void settingAction() 
		{
			button.setAction(this);
		}

	}
	
	
}
