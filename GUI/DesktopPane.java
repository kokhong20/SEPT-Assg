package GUI;

import Controller.MenuAction;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;


@SuppressWarnings("serial")
public class DesktopPane extends JFrame 
{
	
	public static JMenuItem openItem, saveItem, exitItem, helpItem, aboutItem;
	public static JDesktopPane desktopPane;
	
	public DesktopPane() {
		
		super("Assignment");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");
		openItem = new JMenuItem("Open...", KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
		saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke('W', CTRL_DOWN_MASK));
		helpItem = new JMenuItem("Help", KeyEvent.VK_H);
		helpItem.setAccelerator(KeyStroke.getKeyStroke('H', CTRL_DOWN_MASK));
		aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', CTRL_DOWN_MASK));
		desktopPane = new JDesktopPane();
		MenuAction action = new MenuAction();
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
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
	}
}
