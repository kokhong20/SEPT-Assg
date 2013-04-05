package gui;

import javax.swing.JButton;
/**
 * 
 * @author KokHong
 *
 */
public class PADrawingKitButton extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1493410390416700500L;
	private PADrawingKit drawKitPanel;
	private JButton fill;
	private JButton handCursor;
	private JButton selectCursor;
	private JButton selectAllCursor;
	private JButton line;
	private JButton rectangle;
	private JButton circle;
	private JButton group;
	private JButton ungroup;
	
	
	
	
	public PADrawingKitButton(PADrawingKit drawKitPanel)
	{
		this.drawKitPanel = drawKitPanel;
	}
	
	public void addButton()
	{
		
	}
	
	public void createButton()
	{
		fill = new JButton();
		handCursor = new JButton();
		selectCursor = new JButton();
		selectAllCursor = new JButton();
		line = new JButton();
		rectangle = new JButton();
		circle = new JButton();
		group = new JButton();
		ungroup = new JButton();
		
		
		
	}

}
