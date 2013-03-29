package GUI;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DesktopPane extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 273712589857804924L;
	
	private JDesktopPane desktopPane;
	private MenuBar menuBar;
	
	public DesktopPane() 
	{
		super("Assignment");
		this.initDesktop();
		setJMenuBar(menuBar);
		setLayout(new BorderLayout());
		add(desktopPane, BorderLayout.CENTER);
		
		// move to controller
		addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });
	}
	
	private void initDesktop()
	{
		this.setBackground(Color.WHITE);
		this.desktopPane = new JDesktopPane();
		this.menuBar = new MenuBar(desktopPane);
	}
	
	public JDesktopPane getDesktopPane()
	{
		return desktopPane;
	}
	
	public ViewMenu getViewMenu()
	{
		return menuBar.getViewMenu();
	}
}
