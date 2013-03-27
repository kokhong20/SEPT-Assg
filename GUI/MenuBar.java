package GUI;

import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import Controller.MenuAction;
import Controller.ZoomInOutAction;

public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = 291959286905303943L;
	
	int keyMask;
	JDesktopPane desktopPane;
	
	/* Menu */
	JMenu fileMenu;
	JMenu aboutMenu;
	
	/* File's Menu Item */
	JMenuItem newItem;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem saveAsItem;
	JMenuItem editItem;
	JMenuItem exitItem;
	
	/* About's Menu Item*/
	JMenuItem aboutItem;
	JMenuItem helpItem;
	
	/* View's Menu init */
	ViewMenu view;
	MenuAction fileMenuAction;

	public MenuBar(JDesktopPane desktopPane)
	{
		this.desktopPane = desktopPane;
		
		this.checkOS();
		this.initMenuBar();
		this.addMenuItem();
		this.addAcceleractor();
		this.addMenu();
		this.addActionListener();
		this.fileMenuAction.setMenuParent(this);
	}

	private void initMenuBar()
	{
		/* init Menu */
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		
		/* init File's Menu Item */
		newItem = new JMenuItem("New", KeyEvent.VK_N);
		openItem = new JMenuItem("Open...", KeyEvent.VK_O);
		saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveAsItem = new JMenuItem("Save As...", KeyEvent.VK_S);
		editItem = new JMenuItem("Edit, Select All", KeyEvent.VK_A);
		exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		
		/* init About's Menu Item */
		aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		helpItem = new JMenuItem("Help", KeyEvent.VK_H);
		
		/* init View Menu */
		view = new ViewMenu();
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
		
		/* add About's Menu Item */
		aboutMenu.add(aboutItem);
		aboutMenu.add(helpItem);
	}
	
	private void addMenu()
	{
		add(fileMenu);
		add(view);
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
		
		/* add About's Menu Item Accelerator */
		helpItem.setAccelerator(KeyStroke.getKeyStroke('H', keyMask));
	}
	
	private void addActionListener()
	{
		fileMenuAction = new MenuAction(desktopPane, fileMenu);
		newItem.addActionListener(fileMenuAction);
		openItem.addActionListener(fileMenuAction);
		saveItem.addActionListener(fileMenuAction);
		saveAsItem.addActionListener(fileMenuAction);
		editItem.addActionListener(fileMenuAction);
		exitItem.addActionListener(fileMenuAction);
		
		MenuAction aboutMenuAction = new MenuAction(desktopPane, aboutMenu);
		aboutItem.addActionListener(aboutMenuAction);
		
		ZoomInOutAction viewMenuAction = new ZoomInOutAction(view);
		view.setActionListener(viewMenuAction);
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
	
	public ViewMenu getViewMenu()
	{
		return this.view;
	}
}
