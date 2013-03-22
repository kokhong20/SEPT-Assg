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
	
	public DesktopPane() {
		
		super("Assignment");
		
		// move to controller
		addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");
		openItem = new JMenuItem("Open...", KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		saveAsItem = new JMenuItem("Save As...", KeyEvent.VK_S);
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK | Event.SHIFT_MASK));
		newItem = new JMenuItem("New", KeyEvent.VK_N);
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		editItem = new JMenuItem("Edit, Select All", KeyEvent.VK_A);
		editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Event.CTRL_MASK));
		helpItem = new JMenuItem("Help", KeyEvent.VK_H);
		helpItem.setAccelerator(KeyStroke.getKeyStroke('H', Event.CTRL_MASK));
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
