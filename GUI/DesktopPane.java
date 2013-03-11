package GUI;

import Controller.MenuAction;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class DesktopPane extends JFrame {
	
	public static JMenuItem openItem;
	public static JDesktopPane desktopPane;
	
	public DesktopPane() {
		
		super("Assignment");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open...");
		desktopPane = new JDesktopPane();
		MenuAction action = new MenuAction();
		
		fileMenu.add(openItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		add(desktopPane);
		openItem.addActionListener(action);
	}
}
