package controller;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PAColorChooserAction implements ChangeListener
{

	private JColorChooser colorChooser;
	private JButton button;
	
	public PAColorChooserAction(JColorChooser colorChooser, JButton button)
	{
		this.colorChooser = colorChooser;
		this.button = button;
	}
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		Color color = colorChooser.getColor();
		button.setBackground(color);
		System.out.println("ABC"+button.getBackground());
	}

}
