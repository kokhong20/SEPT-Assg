package GUI;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class DesktopPane extends JFrame 
{
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
		
		int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
		System.out.println("Your DPI is :" + dpi);
	}
}
