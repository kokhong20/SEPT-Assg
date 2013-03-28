package GUI;

import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import Controller.ZoomInOutAction;

public class ViewMenu extends JMenu
{
	private static final long serialVersionUID = -1993241072473133182L;
	
	int keyMask;
	
	/* View's Menu Item */
	JMenuItem zoomInItem;
	JMenuItem zoomOutItem;
	JMenuItem oriSize;
	JMenuItem oriPos;
	
	ZoomInOutAction zoomAction;
	
	public ViewMenu ()
	{
		super("View");
		this.checkOS();
		this.initMenu();
		this.addMenu();
		this.addAcceleractor();
		this.buttonAvail(false);
	}
	
	private void initMenu()
	{
		/* init View's Menu Item */
		zoomInItem = new JMenuItem("Zoom In", KeyEvent.VK_EQUALS);
		zoomOutItem = new JMenuItem("Zoom Out", KeyEvent.VK_MINUS);
		oriSize = new JMenuItem("Orginal Size",KeyEvent.VK_L);
		oriPos = new JMenuItem("Orginal Position",KeyEvent.VK_K);
	}
	
	private void addMenu()
	{
		add(zoomInItem);
		add(zoomOutItem);
		add(oriSize);
		add(oriPos);
	}
	
	private void addAcceleractor()
	{
		/* add View's Menu Item Accelerator */
		zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,keyMask));
		zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,keyMask));
		oriSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,keyMask));
		oriPos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,keyMask));
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
	
	public void setActionListener(ZoomInOutAction ac)
	{
		zoomInItem.addActionListener(ac);
		zoomOutItem.addActionListener(ac);
		oriSize.addActionListener(ac);
		oriPos.addActionListener(ac);
		zoomAction = ac;
	}
	
	public void buttonAvail(boolean boo)
	{
		zoomInItem.setEnabled(boo);
		zoomOutItem.setEnabled(boo);
		oriSize.setEnabled(boo);
		oriPos.setEnabled(boo);
	}
	
	public void setDisplay(SVGDisplay panel)
	{
		zoomAction.setParentPane(panel);
	}
}
