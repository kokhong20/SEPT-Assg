package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Coloring;

public class ColorBarPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -136533558913761705L;
	LinkedHashSet<JButton> colorButtons;
	
	public ColorBarPanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout());
		addButtons(this);
	}
	
	private void addButtons(ColorBarPanel panel)
	{

	}
	
	public String getButtonColor()
	{
		return "";
	}

}
