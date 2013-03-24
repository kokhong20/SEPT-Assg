package GUI;

import Controller.MenuAction;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class DesktopPane extends JFrame 
{
	
	public static JMenuItem openItem, saveItem, helpItem, aboutItem, newItem, saveAsItem, editItem, exitItem;
	JDesktopPane desktopPane;
	public int keyMask = 0;
	
	public DesktopPane() 
	{
		
		super("Assignment");
		
		// move to controller
		addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });
		
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
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");
		
		openItem = new JMenuItem("Open...", KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, keyMask));
		saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, keyMask));
		saveAsItem = new JMenuItem("Save As...", KeyEvent.VK_S);
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, keyMask | Event.SHIFT_MASK));
		newItem = new JMenuItem("New", KeyEvent.VK_N);
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, keyMask));
		editItem = new JMenuItem("Edit, Select All", KeyEvent.VK_A);
		editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, keyMask));
		exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, keyMask));
		helpItem = new JMenuItem("Help", KeyEvent.VK_H);
		helpItem.setAccelerator(KeyStroke.getKeyStroke('H', keyMask));
		aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		desktopPane = new JDesktopPane();
		MenuAction action = new MenuAction(desktopPane);

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(editItem);
		fileMenu.add(exitItem);
		aboutMenu.add(aboutItem);
		aboutMenu.add(helpItem);
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		setJMenuBar(menuBar);
		add(desktopPane);
		
		openItem.addActionListener(action);
		saveItem.addActionListener(action);
		exitItem.addActionListener(action);
		editItem.addActionListener(action);
		saveAsItem.addActionListener(action);
		newItem.addActionListener(action);
	}
}
