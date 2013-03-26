package GUI;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DesktopPane extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 273712589857804924L;
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
		
		desktopPane = new JDesktopPane();
		MenuBar menuBar = new MenuBar(desktopPane);
		setJMenuBar(menuBar);
		add(desktopPane);
	}
}
