package gui;

import java.awt.Dimension;

import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;


import model.PASystem;

/**
 * 
 * @author KokHong
 *
 */

public class PAColorChooser extends JInternalFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7537988030356975339L;
	private JDesktopPane rootView;
	private JPanel chooserPanel;
	private JColorChooser colorChooser;
	
	public PAColorChooser(JDesktopPane rootView)
	{
		this.rootView = rootView;
		createColorChooser();
		initColorChooser();
	}
	
	/**
	 * Init the Internal Frame
	 */
	private void initColorChooser()
	{
		rootView.add(this);
		this.setClosable(true);
		this.setSize(new Dimension(700,300));
		this.setVisible(true);
		//Set location based on user's computer resolution
		this.setLocation(((int) (0.2 * PASystem.getScreenDimension().getWidth())), 
						((int) (0.2 * PASystem.getScreenDimension().getHeight())));
		
	}
	
	/**
	 * Create The Color Chooser and put it into a panel
	 */
	private void createColorChooser()
	{
		chooserPanel = new JPanel();
		colorChooser = new JColorChooser();
		// Disable the preview Panel
		colorChooser.setPreviewPanel(new JPanel());
		AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
		// Remove others color channel panel and only use "RGB" channel
		for(AbstractColorChooserPanel accp : panels)
		{
			if(!accp.getDisplayName().equals("RGB"))
			{
				colorChooser.removeChooserPanel(accp);
			}
		}
				
		chooserPanel.add(colorChooser);
		this.add(chooserPanel);
	}
	
	public JColorChooser getColorChooser()
	{
		return colorChooser;
	}
	
}
