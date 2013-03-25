package GUI;

import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import Controller.MenuAction;

public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = 291959286905303943L;
	
	int keyMask;
	JDesktopPane desktopPane;
	
	/* Menu */
	JMenu fileMenu;
	JMenu viewMenu;
	JMenu aboutMenu;
	
	/* File's Menu Item */
	JMenuItem newItem;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem saveAsItem;
	JMenuItem editItem;
	JMenuItem exitItem;
	
	/* View's Menu Item */
	JMenuItem zoomInItem;
	JMenuItem zoomOutItem;
	JMenuItem oriSize;
	JMenuItem oriPos;
	
	/* About's Menu Item*/
	JMenuItem aboutItem;
	JMenuItem helpItem;

	public MenuBar(JDesktopPane desktopPane)
	{
		this.desktopPane = desktopPane;
		
		this.checkOS();
		this.initMenuBar();
		this.addMenuItem();
		this.addAcceleractor();
		this.addMenu();
		this.addActionListener();
	}

	private void initMenuBar()
	{
		/* init Menu */
		fileMenu = new JMenu("File");
		viewMenu = new JMenu("View");
		aboutMenu = new JMenu("About");
		
		/* init File's Menu Item */
		newItem = new JMenuItem("New", KeyEvent.VK_N);
		openItem = new JMenuItem("Open...", KeyEvent.VK_O);
		saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveAsItem = new JMenuItem("Save As...", KeyEvent.VK_S);
		editItem = new JMenuItem("Edit, Select All", KeyEvent.VK_A);
		exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		
		/* init View's Menu Item */
		zoomInItem = new JMenuItem("Zoom In", KeyEvent.VK_EQUALS);
		zoomOutItem = new JMenuItem("Zoom Out", KeyEvent.VK_MINUS);
		oriSize = new JMenuItem("Orginal Size",KeyEvent.VK_L);
		oriPos = new JMenuItem("Orginal Position",KeyEvent.VK_K);
		
		/* init About's Menu Item */
		aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		helpItem = new JMenuItem("Help", KeyEvent.VK_H);
	}
	
	private void addMenuItem()
	{
		/* add File's Menu Item */
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(editItem);
		fileMenu.add(exitItem);
		
		/* add View's Menu Item */
		viewMenu.add(zoomInItem);
		viewMenu.add(zoomOutItem);
		viewMenu.add(oriSize);
		viewMenu.add(oriPos);
		
		/* add About's Menu Item */
		aboutMenu.add(aboutItem);
		aboutMenu.add(helpItem);
	}
	
	private void addMenu()
	{
		add(fileMenu);
		add(viewMenu);
		add(aboutMenu);
	}
	
	private void addAcceleractor()
	{
		/* add File's Menu Item Accelerator */
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, keyMask));
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, keyMask));
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, keyMask | Event.SHIFT_MASK));
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, keyMask));
		editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, keyMask));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, keyMask));
		
		/* add View's Menu Item Accelerator */
		zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,keyMask));
		zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,keyMask));
		oriSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,keyMask));
		oriPos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,keyMask));
		
		/* add About's Menu Item Accelerator */
		helpItem.setAccelerator(KeyStroke.getKeyStroke('H', keyMask));
	}
	
	private void addActionListener()
	{
		MenuAction fileMenuAction = new MenuAction(desktopPane, fileMenu);
		//MenuAction aboutMenuAction = new MenuAction(desktopPane, aboutMenu);
		newItem.addActionListener(fileMenuAction);
		openItem.addActionListener(fileMenuAction);
		saveItem.addActionListener(fileMenuAction);
		saveAsItem.addActionListener(fileMenuAction);
		editItem.addActionListener(fileMenuAction);
		exitItem.addActionListener(fileMenuAction);
	}

	private void checkOS()
	{
		//Check whether is Mac OS or Window OS
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
	}
}
