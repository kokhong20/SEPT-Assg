package GUI;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Model.Coloring;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DesktopPane extends JFrame 
{
	/**
	 * 
	 */
	
	MenuBar menuBar;
	private static final long serialVersionUID = 273712589857804924L;
	JDesktopPane desktopPane;
	public int keyMask = 0;
	
	JPanel newDrawingPanel, colorPanel;
	
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
		
		this.setBackground(Coloring.setColor("white"));
		colorPanel = new ColorBarPanel();
		newDrawingPanel = new NewDrawingPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("DRAWING", newDrawingPanel);
		tabbedPane.addTab("COLOR", colorPanel);
		
		desktopPane = new JDesktopPane();
		MenuBar menuBar = new MenuBar(desktopPane);
		setJMenuBar(menuBar);
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.NORTH);
		add(desktopPane, BorderLayout.CENTER);
	}
}
