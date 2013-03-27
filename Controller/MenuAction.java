package Controller;

import GUI.InternalFrame;
import GUI.SVGDisplay;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuAction implements ActionListener 
{
	private JFileChooser fileChooser;
	private InternalFrame fcInternal;
	private JDesktopPane desktopPane;
	private JMenu menu;
	private int keyMask;
	private Component [] itemArray;

	public MenuAction(JDesktopPane desktopPane, JMenu menu)
	{
		this.desktopPane = desktopPane;
		this.menu = menu;
		this.itemArray = menu.getMenuComponents();
	}

	public JMenuItem getAction(JMenuItem item)
	{
		return item;
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (menu.getText().equals("File"))
		{
			// Create new SVG
			if (e.getSource() == itemArray[0]) 
			{		
				SVGDisplay display = new SVGDisplay(new SVGRender());
				display.setPreferredSize(display.getRender().getPreferredSize());
				InternalFrame svgInternal = new InternalFrame(this.desktopPane,display,"New");

				//Determine Key Mask
				if (System.getProperty("os.name").equals("Mac OS X"))
				{
					// Mac OS command
					keyMask = Event.META_MASK;
				}

				// Window OS
				else
				{
					keyMask = Event.CTRL_MASK;
				}

				// Zoom In Zoom Out 
				JMenuBar menuBar = new JMenuBar();
				JMenu viewMenu = new JMenu("View");
				JMenuItem zoomIn = new JMenuItem("Zoom In",KeyEvent.VK_EQUALS);
				zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,keyMask));
				JMenuItem zoomOut = new JMenuItem("Zoom Out",KeyEvent.VK_MINUS);
				zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,keyMask));
				JMenuItem backOriginalSize = new JMenuItem("Orginal Size",KeyEvent.VK_L);
				backOriginalSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,keyMask));
				JMenuItem backOrginalPosition = new JMenuItem("Orginal Position",KeyEvent.VK_K);
				backOrginalPosition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,keyMask));
				viewMenu.add(zoomIn);
				viewMenu.add(zoomOut);
				viewMenu.add(backOriginalSize);
				viewMenu.add(backOrginalPosition);
				menuBar.add(viewMenu);
				svgInternal.setJMenuBar(menuBar);
				svgInternal.pack();
				svgInternal.setVisible(true);
				this.desktopPane.add(svgInternal);

				ZoomInOutAction zoomAction = new ZoomInOutAction(display, viewMenu);
				zoomIn.addActionListener(zoomAction);
				zoomOut.addActionListener(zoomAction);
				backOriginalSize.addActionListener(zoomAction);
				backOrginalPosition.addActionListener(zoomAction);


				Toolkit toolkit =  Toolkit.getDefaultToolkit ();
				if((toolkit.getScreenSize().height <= display.getPreferredSize().height)
						|| (toolkit.getScreenSize().width <= display.getPreferredSize().width))
				{
					try {
						svgInternal.setMaximum(true);
					} catch (PropertyVetoException pve) {
						// TODO Auto-generated catch block
						pve.printStackTrace();
					}
				}

				else if(svgInternal.getSize().equals(new Dimension(0,0)))
				{
					svgInternal.setSize(500,500);
				}
				else
					svgInternal.setSize(display.getPreferredSize());

			}

			// Open new SVG
			else if (e.getSource() == itemArray[1]) 
			{	
				fcInternal = new InternalFrame(this.desktopPane,"Open");
				fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				FileFilter allFilter = new FileNameExtensionFilter("All files", "svg", "xml");
				FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
				FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");

				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(xmlFilter);
				fileChooser.setFileFilter(svgFilter);
				fileChooser.setFileFilter(allFilter);

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

		else if (menu.getText().equals("About"))
		{
			if (e.getSource() == itemArray[0])
			{
				System.out.println("Hello");
			}
		}
	}
}
