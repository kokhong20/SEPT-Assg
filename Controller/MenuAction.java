package Controller;

import GUI.SVGRender;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuAction implements ActionListener 
{
	private JFileChooser fileChooser;
	private JInternalFrame fcInternal;
	private JDesktopPane desktopPane;
	private Component [] itemArray;

	public MenuAction(JDesktopPane desktopPane, JMenu menu)
	{
		this.desktopPane = desktopPane;
		this.itemArray = menu.getMenuComponents();
	}

	public JMenuItem getAction(JMenuItem item)
	{
		return item;
	}

	public void actionPerformed(ActionEvent e) 
	{
		// Create new SVG
		if (e.getSource() == itemArray[0]) 
		{		
			JScrollPane scrollPane = new JScrollPane(new SVGRender(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame svgInternal = new JInternalFrame("New", true, true, true, true);
			svgInternal.add(scrollPane, BorderLayout.CENTER);
			svgInternal.pack();
			this.desktopPane.add(svgInternal);
			svgInternal.setVisible(true);
			svgInternal.setSize(500,500);	
		}

		// Open new SVG
		else if (e.getSource() == itemArray[1]) 
		{	
			fcInternal = new JInternalFrame("Open", true, true, true, true);
			fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileFilter fileFilter = new FileNameExtensionFilter("SVG files","svg");
			fileChooser.setFileFilter(fileFilter);

			fcInternal.add(fileChooser, BorderLayout.CENTER);
			fcInternal.pack();
			this.desktopPane.add(fcInternal);
			fcInternal.setVisible(true);
			FileChooserAction fcAction = new FileChooserAction(this.desktopPane, this.fcInternal, this.fileChooser);
			fileChooser.addActionListener(fcAction);
		}

		// Save SVG
		else if (e.getSource() == itemArray[2]) 
		{

		}

		// Edit/Select All SVG
		else if (e.getSource() == itemArray[4]) 
		{		
		}

		// Save as SVG
		else if (e.getSource() == itemArray[3]) 
		{
		}

		// Exit software
		else if (e.getSource() == itemArray[6]) 
		{
			Object[] choices = {"Yes", "No"};
			int dialogButton = JOptionPane.showOptionDialog(null, "Are You Sure You Want To Exit?", "Exit", JOptionPane.YES_NO_OPTION, 
					JOptionPane.PLAIN_MESSAGE, null, choices, "Yes");

			if (dialogButton == 0)
			{
				System.exit(0);
			}
		}
	}
}
